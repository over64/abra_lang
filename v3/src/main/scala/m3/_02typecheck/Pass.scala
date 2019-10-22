package m3._02typecheck

import m3.Ast0._
import m3.Builtin._
import m3._01parse.ParseMeta._
import m3._02typecheck.TCE.NoSuchSelfDef
import m3._02typecheck.TCMeta._
import m3._02typecheck.Utils._
import m3.{Level, _}

import scala.collection.immutable.ArraySeq
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Buffer}
import scala.reflect.ClassTag

case class PassContext(prefix: String, colorId: Int,
                       level: Level, module: Module,
                       defStack: List[(Option[TypeHint], String)] = List.empty) {

  val colorReset = """\u001b[0m"""
  val colors = Seq("""\u001b[48;5;153m""", """\u001b[48;5;85m""", """\u001b[48;5;228m""",
    """\u001b[48;5;253m""", """\u001b[48;5;219m""", """\u001b[48;5;216m""")

  //  val colors = (1 to 255).map(c => s"""\u001b[48;5;${c}m""")

  var forkColor = colorId

  def spinForkColor() = {
    val origForkColor = forkColor
    forkColor = (forkColor + 1) % colors.length
    while (forkColor == colorId || forkColor == origForkColor)
      forkColor = (forkColor + 1) % colors.length
  }

  def deeperExpr(): PassContext = {
    spinForkColor()
    this.copy(
      prefix = this.prefix + colors(colorId) + "  " + colorReset,
      colorId = this.forkColor)
  }

  // FIXME: смешана логика вывода на экран и логика работы со стеком вызовов
  def deeperDef(selfTh: Option[TypeHint], name: String, notAddPrefix: Boolean = false): PassContext = {
    spinForkColor()
    val realPrefix = if (notAddPrefix) prefix else prefix + colors(colorId) + "  " + colorReset
    this.copy(
      prefix = realPrefix,
      colorId = this.forkColor,
      defStack = (selfTh, name) :: this.defStack)
  }

  def log(msg: String): Unit = println(prefix + colors(colorId) + "  " + msg + colorReset)

  def logExpr(expr: Expression): Unit = {
    val loc = expr.location
    val line = loc.source(loc.line - 1)

    try {
      if (loc.line == loc.lineEnd) {
        val record = line.substring(loc.col, loc.colEnd + 1)
        log(record)
      } else {
        val first = " " * loc.col + line.substring(loc.col)
        val tail = loc.source.drop(loc.line).take(loc.lineEnd - loc.line)
        val minCol = (loc.col +: tail.map(l => l.indexWhere(c => !c.isSpaceChar)).filter(i => i != -1)).min

        (first +: tail).foreach { l =>
          val eBegin = Math.max(0, l.indexWhere(c => !c.isSpaceChar))
          val (prefix, start) =
            if (eBegin < minCol) (" " * (minCol - eBegin), eBegin)
            else ("", minCol)

          log(prefix + l.substring(start))
        }
      }
    } catch {
      case ex: StringIndexOutOfBoundsException =>
        var x = 1
        throw ex
    }
  }
}


class Pass {
  implicit class ArrayBufferConv[T](self: ArrayBuffer[T])(implicit m: ClassTag[T]) {
    def asSeq: ArraySeq[T] = ArraySeq.unsafeWrapArray(self.toArray) // NOT SO GOOD
  }

  //FIXME: move to TypeHintPass
  def assertCorrect(ctx: PassContext, self: TypeHint, params: ArraySeq[GenericTh]): Unit =
    self match {
      case sth: ScalarTh =>
        val (_, decl) = typeDecl(ctx.level, sth)

        if (decl.params.length != sth.params.length)
          throw TCE.ParamsCountMismatch(sth.location)

        sth.params.foreach(p => assertCorrect(ctx, p, params))
      case sth: StructTh =>
        val fieldNames = sth.seq.map(_.name)
        if (fieldNames.length != fieldNames.toSet.size)
          throw TCE.FieldNameNotUnique(sth.location)
        sth.seq.foreach(f => assertCorrect(ctx, f.typeHint, params))
      case uth: UnionTh =>
        if (uth.seq.length != uth.seq.toSet.size)
          throw TCE.UnionMembersNotUnique(uth.location)
        uth.seq.foreach(th => assertCorrect(ctx, th, params))
      case fth: FnTh =>
        fth.args.foreach(th => assertCorrect(ctx, th, params))
        assertCorrect(ctx, fth.ret, params)
      case gth: GenericTh =>
        if (!params.contains(gth))
          throw TCE.NoSuchParameter(gth.location, gth)
      case AnyTh =>
    }

  def checkTypeDecl(ctx: PassContext): Unit = {
    ctx.module.types.values.foreach { td =>
      if (Builtin.isDeclaredBuiltIn(td.name))
        throw TCE.BuiltinTypeRedeclare(td.location, td.name)

      ctx.module.imports.seq.find(ie => ie.withTypes.contains(td.name)) match {
        case Some(ie) => throw TCE.TypeAlreadyLocalDefined(ie.location, td.name)
        case None =>
      }

      td match {
        case sc: ScalarDecl =>
        case st: StructDecl =>
          val fieldNames = st.fields.map(_.name)
          if (fieldNames.length != fieldNames.toSet.size) throw TCE.FieldNameNotUnique(st.location)
          st.fields.foreach(f => assertCorrect(ctx, f.th, st.params))
        case un: UnionDecl =>
          if (un.variants.length != un.variants.toSet.size)
            throw TCE.UnionMembersNotUnique(un.location)
      }
    }
  }

  def isSelfApplicable(ctx: PassContext, selfTh: TypeHint, selfArgTh: TypeHint): Boolean =
    try {
      new TypeInfer(ctx.level, ctx.module).infer(Seq(selfTh.location), selfTh, selfArgTh);
      true
    } catch {
      case ex: Exception => false
    }

  // Move from type infer to symbol resolve system?
  def findSelfDef(ctx: PassContext, location: Seq[AstInfo], selfTh: TypeHint, fnName: String): (Seq[ImportEntry], Module, Def, FnTh, Equations) = {
    selfTh match {
      case gth: GenericTh =>
        throw new RuntimeException("Unexpected to be here")
      case _ =>
        ctx.module.selfDefs.get(fnName).flatMap { defs =>
          defs.find(d => isSelfApplicable(ctx, d.lambda.args.head.typeHint, selfTh))
        } match {
          case Some(fn) =>
            if (fn.getTypeHintOpt == None) passDef(ctx.deeperDef(Some(selfTh), fnName), fn)
            else {
              val dctx = ctx.deeperExpr()
              dctx.log(s"compiled ${fn.getTypeHint.asInstanceOf[FnTh].args(0)}::$fnName")
              dctx.log(s":${fn.getTypeHint}")
              dctx.log(s"eq ${fn.getEquations}")
            }
            (Seq.empty, ctx.module, fn, fn.getTypeHint, fn.getEquations)
          case None =>
            ctx.module.imports.seq.flatMap { ie =>
              val mod = ctx.level.findMod(ie.path).getOrElse(throw TCE.NoSuchModulePath(ie.location))
              mod.selfDefs.getOrElse(fnName, Seq()).map(fn => (mod, ie, fn))
            }.find { case (mod, ie, fn) => isSelfApplicable(ctx, fn.lambda.args.head.typeHint, selfTh) } match {
              case Some((mod, ie, fn)) =>
                (Seq(ie), mod, fn, fn.getTypeHint, fn.getEquations)
              case None =>
                throw TCE.NoSuchSelfDef(location, fnName, selfTh)
            }
        }
    }
  }

  def findSelfDefOpt(ctx: PassContext, location: Seq[AstInfo], selfTh: TypeHint, fnName: String): Option[(Seq[ImportEntry], Module, Def, FnTh, Equations)] =
    try {
      Some(findSelfDef(ctx, location, selfTh, fnName))
    } catch {
      case _: NoSuchSelfDef => None
    }

  def passExpr(ctx: PassContext, scope: BlockScope, eq: Equations, th: TypeHint, expr: Expression): TypeHint = {
    def foldFields(from: TypeHint, fields: ArraySeq[lId]): TypeHint =
      fields.foldLeft(from) {
        case (sth: ScalarTh, fieldId) =>
          typeDecl(ctx.level, sth) match {
            case (_, sd: StructDecl) =>
              ThUtil.spec(sd.fields
                .find(fd => fd.name == fieldId.value)
                .getOrElse(throw TCE.NoSuchField(fieldId.location, sth, fieldId.value))
                .th, ThUtil.makeSpecMap(sd.params, sth.params))
            case (_, td) => throw TCE.NoSuchField(fieldId.location, sth, fieldId.value)
          }
        case (sth: StructTh, fieldId) =>
          sth.seq
            .find(f => f.name == fieldId.value)
            .getOrElse(throw TCE.NoSuchField(fieldId.location, sth, fieldId.value)).typeHint
        case (th, fieldId) =>
          throw TCE.NoSuchField(fieldId.location, th, fieldId.value)
      }

    def tryInt(lit: Literal, fn: String => Unit, th: TypeHint,
               or: TCE.IntegerLiteralOutOfRange => TypeHint = ex => throw ex): TypeHint =
      try {
        fn(lit.value);
        th
      } catch {
        case _: NumberFormatException => or(TCE.IntegerLiteralOutOfRange(lit.location, Some(th)))
      }

    def tryFloat(lit: Literal, fn: String => Any, th: TypeHint,
                 or: TCE.FloatingLiteralOutOfRange => TypeHint = ex => throw ex): TypeHint =
      fn(lit.value) match {
        case f: Float if f == Float.PositiveInfinity || f == Float.NegativeInfinity =>
          or(TCE.FloatingLiteralOutOfRange(lit.location, Some(th)))
        case d: Double if d == Double.PositiveInfinity || d == Double.NegativeInfinity =>
          or(TCE.FloatingLiteralOutOfRange(lit.location, Some(th)))
        case _ => th
      }

    ctx.logExpr(expr)

    val exprTh = expr match {
      case lit: lInt =>
        def default() =
          tryInt(lit, _.toInt, thInt, { _ =>
            tryInt(lit, _.toLong, thLong, { _ =>
              throw TCE.IntegerLiteralOutOfRange(lit.location, None)
            })
          })

        if (th == thLong) tryInt(lit, _.toLong, thLong)
        else if (th == thInt) tryInt(lit, _.toInt, thInt)
        else if (th == thShort) tryInt(lit, _.toShort, thShort)
        else if (th == thByte) tryInt(lit, _.toByte, thByte)
        else default()
      case lit: lFloat =>
        def default() =
          tryFloat(lit, _.toFloat, thFloat, { _ =>
            tryFloat(lit, _.toDouble, thDouble, { _ =>
              throw TCE.FloatingLiteralOutOfRange(lit.location, None)
            })
          })

        if (th == thDouble) tryFloat(lit, _.toDouble, thDouble)
        else if (th == thFloat) tryFloat(lit, _.toFloat, thFloat)
        else default()
      case _: lBoolean => thBool
      case _: lString => thString
      case _: lNone => thNil
      case id: lId =>
        scope.findVar(id.value) match {
          case Some((varTh, vt)) =>
            id.setVarLocation(vt)
            id.setTypeHint(varTh)
            varTh
          case None =>
            val fn = ctx.module.defs.getOrElse(id.value, throw TCE.NoSuchSymbol(id.location, id.value))

            id.setVarLocation(VarDefLocal(fn))
            if (fn.getTypeHintOpt == None) passDef(ctx.deeperDef(None, id.value), fn)
            fn.getTypeHint
        }
      case Ret(opt) =>
        val retTh = opt.map(exp => passExpr(ctx.deeperExpr(), scope, eq, th, exp)).getOrElse(thNil)
        scope.addRetType(retTh)
        thUnreachable
      case call@Call(expr, args) =>
        expr match {
          case id: lId =>
            scope.findVar(id.value) match {
              case Some((varTh, vt)) =>
                varTh match {
                  case fth: FnTh =>
                    call.setCallType(CallFnPtr)
                    id.setVarLocation(vt)
                    id.setTypeHint(fth)
                    //FIXME: может быть тут не нужен invokePrototype?
                    new Invoker(findSelfDef).invokePrototype(ctx, call, eq, new Equations(), th, fth, args.map { arg =>
                      new InferTask {
                        override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                          (arg.location, passExpr(ctx.deeperExpr(), scope, eq, th, arg))
                      }
                    })
                    fth.ret
                  case selfTh =>
                    new Invoker(findSelfDef).invokeSelfDef(ctx, eq, call, th, "get",
                      new InferTask {
                        override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                          (id.location, passExpr(ctx.deeperExpr(), scope, eq, th, id))
                      },
                      args.map { arg =>
                        new InferTask {
                          override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                            (arg.location, passExpr(ctx.deeperExpr(), scope, eq, th, arg))
                        }
                      })
                }
              case None =>
                val toCall = ctx.module.defs.getOrElse(id.value, throw TCE.NoSuchCallable(id.location, id.value))
                if (toCall.getTypeHintOpt == None)
                  passDef(ctx.deeperDef(None, id.value), toCall)

                call.setCallType(CallLocal(toCall))

                new Invoker(findSelfDef).invokeDef(ctx, call, eq, th, toCall, args.map { arg =>
                  new InferTask {
                    override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                      (arg.location, passExpr(ctx.deeperExpr(), scope, eq, th, arg))
                  }
                })
            }
          case lambda: Lambda =>
            val lambdaTh = passExpr(ctx, scope, eq, FnTh(lambda.args.map(_ => AnyTh), th), lambda).asInstanceOf[FnTh]

            call.setCallType(CallFnPtr)

            new Invoker(findSelfDef).invokePrototype(ctx, call, eq, new Equations(), th, lambdaTh, args.map { arg =>
              new InferTask {
                override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                  (arg.location, passExpr(ctx, scope, eq, th, arg))
              }
            })
          case expr =>
            throw TCE.ExpressionNotCallable(expr.location)
        }
      case call@SelfCall(fnName, self, args) =>
        var x = 1
        val argTasks = args.map { arg =>
          new InferTask {
            override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
              (arg.location, passExpr(ctx.deeperExpr(), scope, eq, th, arg))
          }
        }

        self match {
          case id: lId if ctx.module.imports.seq.exists(ie => ie.modName == id.value) =>
            val ie = ctx.module.imports.seq.find(ie => ie.modName == id.value).get
            val mod = ctx.level.findMod(ie.path).getOrElse(throw TCE.NoSuchModulePath(ie.location))
            val toCall = mod.defs.getOrElse(fnName, throw TCE.NoSuchDef(Seq(call.location), fnName))

            call.setCallType(CallImport(mod, toCall))

            new Invoker(findSelfDef).invokePrototype(ctx, call, eq, toCall.getEquations, th, toCall.getTypeHint, argTasks)
          case _expr =>
            val selfTh = passExpr(ctx.deeperExpr(), scope, eq, AnyTh, _expr)
            val selfTask = new InferTask {
              override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                (self.location, selfTh)
            }

            def default() = {
              new Invoker(findSelfDef).invokeSelfDef(ctx, eq, call, th, fnName, selfTask, argTasks)
            }

            def callField(fth: FnTh) = {
              call.setCallType(CallFnPtr)
              new Invoker(findSelfDef).invokePrototype(ctx, call, eq, new Equations(), th, fth, argTasks)
              fth.ret
            }

            selfTh match {
              case sth: ScalarTh =>
                typeDecl(ctx.level, sth) match {
                  case (_, sd: StructDecl) =>
                    sd.fields.find(fd => fd.name == fnName) match {
                      case Some(field) =>
                        ThUtil.spec(field.th, ThUtil.makeSpecMap(sd.params, sth.params)) match {
                          case fth: FnTh =>
                            callField(fth)
                          case gettableTh if findSelfDefOpt(ctx, Seq(call.location), gettableTh, "get") != None =>
                            new Invoker(findSelfDef).invokeSelfDef(ctx, eq, call, th, "get", new InferTask {
                              override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) = (self.location, gettableTh)
                            }, argTasks)
                          case _ => default()
                        }
                      case _ => default()
                    }
                  case _ => default()
                }
              case sth: StructTh =>
                sth.seq.find(fd => fd.name == fnName) match {
                  case Some(field) =>
                    field.typeHint match {
                      case fth: FnTh => callField(fth)
                      case _ => default()
                    }
                  case _ => default()
                }
              case _ =>
                default()
            }
        }
      case cons@Cons(consTh, args) =>
        val (declaredIn, sd) = typeDecl(ctx.level, consTh) match {
          case (mod, sd: StructDecl) => (mod, sd)
          case _ => throw TCE.StructTypeRequired(cons.location)
        }

        val advicedParams = th match {
          case sth: ScalarTh =>
            if (sth.name != consTh.name || sth.declaredIn != declaredIn.pkg)
              throw TCE.TypeMismatch(Seq(cons.location), sth, consTh)
            Some(sth.params)
          case _ =>
            if (consTh.params.nonEmpty) {
              if (consTh.params.length != sd.params.length)
                throw TCE.ParamsCountMismatch(consTh.location)
              Some(consTh.params)
            } else
              None
        }

        val specMap = advicedParams match {
          case Some(params) => ThUtil.makeSpecMap(sd.params, params)
          case None => ThUtil.makeSpecMap(sd.params, sd.params.map(_ => AnyTh))
        }

        val tInfer = new TypeInfer(ctx.level, ctx.module)

        (sd.fields zip args).foreach { case (field, arg) =>
          val advice = ThUtil.spec(field.th, specMap)
          val argTh = passExpr(ctx, scope, eq, advice, arg)
          new TypeChecker(ctx.level, ctx.module).check(Seq(arg.location), advice, argTh)
          tInfer.infer(Seq(arg.location), field.th, argTh)
        }

        val resTh = advicedParams match {
          case Some(params) => ScalarTh(params, consTh.name, consTh.ie, consTh.declaredIn)
          case None => ScalarTh(sd.params.map(p => tInfer.specMap(p)), consTh.name, consTh.ie, consTh.declaredIn)
        }

        resTh.setLocation(consTh.location)
        resTh
      case Tuple(seq) =>
        val expectedSeq =
          th match {
            case sth: StructTh if sth.seq.length == seq.length => sth.seq.map(_.typeHint)
            case _ => seq.map(_ => AnyTh)
          }

        StructTh((expectedSeq zip seq).zipWithIndex.map { case ((expected, fieldExpr), idx) =>
          FieldTh("x" + idx, passExpr(ctx, scope, eq, expected, fieldExpr))
        })
      case Prop(from, props) =>
        val eth = passExpr(ctx.deeperExpr(), scope, eq, AnyTh, from)
        foldFields(eth, props)
      case store@Store(varTh, to, what) =>
        varTh match {
          case gth: GenericTh if !eq.typeParams.contains(gth) => eq.typeParams += gth
          case _ =>
        }
        assertCorrect(ctx, varTh, eq.typeParams.asSeq)
        // x: Int = 5 # ok
        // x = 6 # ok
        // x.y: Int = 8 # fail
        // x.y = 8 # ok
        varTh match {
          case AnyTh =>
            scope.findVar(to.head.value) match {
              case None =>
                if (to.length != 1) throw TCE.NoSuchVar(to.head.location)

                val whatTh = passExpr(ctx.deeperExpr(), scope, eq, varTh, what)
                scope.addLocal(to.head.value, whatTh)
                store.setDeclTh(whatTh)
                to.head.setTypeHint(whatTh)
                to.head.setVarLocation(VarLocal)
              case Some((toVarTh, vt)) =>
                val toTh = foldFields(toVarTh, to.drop(1))
                to.head.setTypeHint(toVarTh)
                to.head.setVarLocation(vt)
                passExpr(ctx.deeperExpr(), scope, eq, toTh, what)
            }
          case th =>
            if (to.length != 1) throw TCE.TypeHintUnexpected(th.location)

            if (scope.findVar(to.head.value) != None)
              throw TCE.VarAlreadyDeclared(to.head.location, to.head.value)

            passExpr(ctx.deeperExpr(), scope, eq, th, what)
            scope.addLocal(to.head.value, th)
            store.setDeclTh(th)
            to.head.setTypeHint(th)
            to.head.setVarLocation(VarLocal)
        }
        thNil
      case lambda@Lambda(args, body) =>
        val argsTh = lambda.args.map(_.typeHint)
        val (inferedArgsTh, retTh) = th match {
          case fth: FnTh if fth.args.length == argsTh.length =>
            val inferedArgsTh = (fth.args zip argsTh).map {
              case (AnyTh, argTh) => argTh
              case (expectedTh, AnyTh) => expectedTh
              case (expectedTh, argTh) =>
                if (expectedTh != argTh)
                  throw TCE.TypeMismatch(Seq(argTh.location), expectedTh, argTh)
                argTh
            }
            (inferedArgsTh, fth.ret)
          case AnyTh =>
            val inferedArgsTh = args.map { arg =>
              if (ThUtil.containsAny(arg.typeHint)) throw TCE.TypeHintRequired(arg.location)
              arg.typeHint
            }
            (inferedArgsTh, AnyTh)
          case _ =>
            throw TCE.TypeMismatch(Seq(lambda.location), th, FnTh(argsTh, AnyTh))
        }

        val inferedRetTh: TypeHint = body match {
          case AbraCode(seq) =>
            val lambdaScope = LambdaScope(scope, (args.map(_.name) zip inferedArgsTh).toMap)
            val lambdaBlock = new BlockScope(lambdaScope)

            seq.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), lambdaBlock, eq, AnyTh, expr))
            seq.lastOption.foreach { last =>
              lambdaBlock.addRetType(passExpr(ctx.deeperExpr(), lambdaBlock, eq, AnyTh, last))
            }

            lambda.setClosure(
              lambdaScope.closure
                .map[(String, TypeHint, VarType)] { case (name, (th, vt)) => (name, th, vt) }
                .to(ArrayBuffer).sortInPlaceBy { case (name, _, _) => name }.asSeq)

            lambdaScope.retTypes.distinct match {
              case ArrayBuffer() => thNil
              case ArrayBuffer(th) => th
              case ab => UnionTh(ab.asSeq)
            }
          case _ =>
            throw TCE.LambdaWithNativeCode(lambda.location)
        }

        FnTh(inferedArgsTh, inferedRetTh)
      case andOr: AndOr =>
        passExpr(ctx, scope, eq, thBool, andOr.left)
        passExpr(ctx, scope, eq, thBool, andOr.right)
        thBool
      case While(cond, _do) =>
        val b = Buffer[Int]()
        val s: ArraySeq[Int] = Array(1, 2, 3).to(ArraySeq)

        passExpr(ctx.deeperExpr(), scope, eq, thBool, cond)
        val block = new WhileScope(scope)
        _do.foreach { expr => passExpr(ctx.deeperExpr(), block, eq, AnyTh, expr) }
        thNil
      case bc@(Break() | Continue()) =>
        def findWhileScope(sc: Scope): Option[WhileScope] =
          sc match {
            case ws: WhileScope => Some(ws)
            case ds: DefScope => None
            case ls: LambdaScope => None
            case bs: BlockScope => findWhileScope(bs.parent)
          }

        findWhileScope(scope).getOrElse(throw TCE.NoWhileForBreakOrContinue(bc.location))
        thNil
      case self@If(cond, _do, _else) =>
        passExpr(ctx.deeperExpr(), scope, eq, thBool, cond)

        val doBlock = new BlockScope(scope)
        _do.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), doBlock, eq, AnyTh, expr))
        val doTh = _do.lastOption.map { last =>
          passExpr(ctx.deeperExpr(), doBlock, eq, AnyTh, last)
        }.getOrElse(thNil)

        val elseBlock = new BlockScope(scope)
        _else.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), elseBlock, eq, AnyTh, expr))
        val elseTh = _else.lastOption.map { last =>
          passExpr(ctx.deeperExpr(), elseBlock, eq, AnyTh, last)
        }.getOrElse(thNil)

        self.setBranchTh((doTh, elseTh))

        ArrayBuffer(doTh, elseTh).filter(th => th != thUnreachable).distinct match {
          case ArrayBuffer(one) => one
          case many => UnionTh(many.asSeq)
        }
      case self@Unless(expr, isSeq) =>
        val tc = new TypeChecker(ctx.level, ctx.module)
        val exprTh = passExpr(ctx.deeperExpr(), scope, eq, AnyTh, expr)

        val exprUnionVariants = exprTh match {
          case uth: UnionTh => uth.seq
          case sth: ScalarTh =>
            typeDecl(ctx.level, sth) match {
              case (_, ud: UnionDecl) => ud.variants.map(v => ThUtil.spec(v, ThUtil.makeSpecMap(ud.params, sth.params)))
              case _ => throw TCE.ExpectedUnionType(expr.location, exprTh)
            }
          case _ => throw TCE.ExpectedUnionType(expr.location, exprTh)
        }

        isSeq.indices.foreach { i =>
          isSeq.indices.drop(i + 1).foreach { j =>
            if (isSeq(i).typeRef == isSeq(j).typeRef)
              throw TCE.CaseAlreadyCovered(isSeq(j).location, isSeq(j).typeRef, isSeq(i).location)
          }
        }

        val mappedSeq = isSeq.map { is =>
          exprUnionVariants.find { th =>
            try {
              tc.check(Seq(is.typeRef.location), th, is.typeRef);
              true
            } catch {
              case _: TCE.TypeMismatch => false
            }
          }.getOrElse(throw TCE.UnlessExpectedOneOf(is.typeRef.location, exprUnionVariants, is.typeRef))

          val blockScope = new BlockScope(scope)
          is.vName.foreach(vName => blockScope.addLocal(vName.value, is.typeRef))

          is._do.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), blockScope, eq, AnyTh, expr))
          is._do.lastOption.map { last =>
            passExpr(ctx.deeperExpr(), blockScope, eq, AnyTh, last)
          }.getOrElse(thNil)
        }

        val coveredType = isSeq.map(is => is.typeRef).toSet
        val mappedType = mappedSeq.filter(x => x != thUnreachable).toSet
        val uncoveredTypes = exprUnionVariants.toSet -- coveredType
        self.setUncovered(uncoveredTypes.toSeq)

        val overallType = (mappedType ++ uncoveredTypes).to(ArraySeq)

        overallType match {
          case ArraySeq(one) => one
          case many => UnionTh(many)
        }
    }

    expr.setTypeHint(exprTh)
    new TypeChecker(ctx.level, ctx.module).check(Seq(expr.location), th, exprTh)
    ctx.log(":" + exprTh)
    exprTh
  }

  def passDef(ctx: PassContext, fn: Def): Unit = {
    if (ctx.defStack.tail.contains(ctx.defStack.head))
      throw TCE.RetTypeHintRequired(fn.location)

    fn.lambda.args.headOption match {
      case Some(head) if head.name == "self" =>
        ctx.log(s"function ${head.typeHint}::${fn.name}")
      case _ =>
        ctx.log(s"function ${fn.name}")
    }

    val eq = new Equations(fn.params)

    val args = fn.lambda.args.map { arg =>
      assertCorrect(ctx, arg.typeHint, eq.typeParams.asSeq)
      (arg.name, arg.typeHint)
    }.toMap
    assertCorrect(ctx, fn.retTh, eq.typeParams.asSeq)

    val defScope = DefScope(args)

    fn.lambda.body match {
      case llVm(_) =>
        if (ThUtil.containsAny(fn.retTh)) throw TCE.RetTypeHintRequired(fn.location)

        fn.setEquations(eq)
        fn.setTypeHint(FnTh(fn.lambda.args.map(_.typeHint), fn.retTh))
      case AbraCode(seq) =>
        val declaredRetTh =
          if (fn.retTh != AnyTh) {
            fn.setTypeHint(FnTh(fn.lambda.args.map(_.typeHint), fn.retTh))
            fn.setEquations(new Equations())
            Some(fn.retTh)
          } else None

        val bodyScope = new BlockScope(defScope)

        seq.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), bodyScope, eq, AnyTh, expr))
        seq.lastOption.foreach { last =>
          bodyScope.addRetType(passExpr(ctx.deeperExpr(), bodyScope, eq, fn.retTh, last))
        }

        val retTh = defScope.retTypes.distinct match {
          case ArrayBuffer() => thNil
          case ArrayBuffer(th) => th
          case ab => UnionTh(ab.asSeq)
        }

        declaredRetTh match {
          case Some(declared) =>
            new TypeChecker(ctx.level, ctx.module).check(Seq(declared.location), declared, retTh)
          case None =>
            fn.setTypeHint(FnTh(fn.lambda.args.map(_.typeHint), retTh))
        }

        fn.setEquations(eq)
    }
    ctx.log(s"eq ${fn.getEquations}")
    ctx.log(s":${fn.getTypeHint}")
  }

  def checkDoubleSelfDeclare(ctx: PassContext): Unit = {
    ctx.module.selfDefs.values.foreach { selfDefs =>
      selfDefs.foreach { selfDef =>
        val selfTh = selfDef.lambda.args.head.typeHint

        selfDefs.find { other =>
          other.location != selfDef.location && isSelfApplicable(ctx, selfTh, other.lambda.args.head.typeHint)
        } match {
          case Some(other) =>
            throw TCE.DoubleSelfDefDeclaration(other.location, selfTh)
          case None =>
        }
      }
    }
  }

  def pass(root: Level) = {
    val m1 = System.currentTimeMillis()
    root.eachModule((level, module) => {
      println(s"__Typecheck__ pass for ${module.pkg}")
      val ctx = PassContext("", 0, level, module)

      checkTypeDecl(ctx)
      checkDoubleSelfDeclare(ctx)

      module.defs.values.foreach { fn =>
        if (fn.getEquationsOpt == None)
          passDef(ctx.deeperDef(None, fn.name, notAddPrefix = true), fn)
      }

      module.selfDefs.values.foreach { defs =>
        defs.foreach { fn =>
          if (fn.getEquationsOpt == None)
            passDef(ctx.deeperDef(Some(fn.lambda.args(0).typeHint), fn.name, notAddPrefix = true), fn)
        }
      }
    })

    val m2 = System.currentTimeMillis()
    println(s"__Typecheck__ pass elapsed: ${m2 - m1}ms")
  }
}