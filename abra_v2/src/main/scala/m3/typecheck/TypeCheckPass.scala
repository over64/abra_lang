package m3.typecheck

import m3.parse.Ast0._
import m3.parse.ParseMeta._
import m3.parse.{AstInfo, Level}
import m3.typecheck.Scope2._
import m3.typecheck.TCE.NoSuchSelfDef
import m3.typecheck.TCMeta._
import m3.typecheck.Utils._
import m3.typecheck.Builtin._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

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

    if (loc.line == loc.lineEnd) {
      val record = line.substring(loc.col, loc.colEnd + 1)
      log(record)
    } else {
      val first = " " * loc.col + line.substring(loc.col)
      val tail = loc.source.drop(loc.line).take(loc.lineEnd - loc.line)
      val minCol = (loc.col +: tail.map(l => l.indexWhere(c => !c.isSpaceChar))).min

      (first +: tail).foreach { l =>
        val eBegin = l.indexWhere(c => !c.isSpaceChar)
        val (prefix, start) =
          if (eBegin < minCol) (" " * (minCol - eBegin), eBegin)
          else ("", minCol)

        log(prefix + l.substring(start))
      }
    }
  }
}

sealed trait SpecResult
case object NewSpec extends SpecResult
case class Specified(th: TypeHint) extends SpecResult
class MismatchLocal extends Exception
class TypeChecker2(ctx: PassContext,
                   genericSpec: (GenericTh, TypeHint) => SpecResult = (adv, th) => Specified(adv),
                   withTransaction: (() => Unit) => Unit = { callback => callback() }) {

  def isEqualSeq(expected: Seq[TypeHint], has: Seq[TypeHint]): Unit = {
    if (expected.length != has.length) throw new MismatchLocal
    (expected zip has).foreach { case (e, h) => isEqual(e, h) }
  }

  def isEqual(self: TypeHint, other: TypeHint): Unit =
    (self, other) match {
      case (th, AnyTh) => // ok
      case (AnyTh, th) => // ok
      case (adv: ScalarTh, th: ScalarTh) =>
        val (_, advMod, advDecl) = resolveType(ctx.level, ctx.module, adv)
        val (_, thMod, sthDecl) = resolveType(ctx.level, ctx.module, th)

        if (advMod.pkg != thMod.pkg || adv.name != th.name) throw new MismatchLocal
        if (adv.params.length != th.params.length) throw TCE.ParamsCountMismatch(th.location)
        isEqualSeq(adv.params, th.params)
      case (adv: UnionTh, th: UnionTh) =>
        isEqualSeq(adv.seq, th.seq)
      case (adv: StructTh, th: StructTh) =>
        isEqualSeq(adv.seq.map(_.typeHint), th.seq.map(_.typeHint))
      case (adv: FnTh, th: FnTh) =>
        isEqualSeq(adv.args, th.args)
        isEqual(adv.ret, th.ret)
      case (adv: GenericTh, th) =>
        genericSpec(adv, th) match {
          case NewSpec =>
          case Specified(specified: GenericTh) =>
            if (th.isInstanceOf[GenericTh]) {
              val gth = th.asInstanceOf[GenericTh]
              if (specified.typeName != gth.typeName) throw new MismatchLocal
            } else throw new MismatchLocal
          case Specified(specifiedTh) =>
            isEqual(specifiedTh, th)
        }
      case (adv, th) => throw new MismatchLocal
    }

  def checkUnionMember(ud: UnionDecl, adv: ScalarTh, th: TypeHint): Unit = {

    if (ud.params.length != adv.params.length) throw TCE.ParamsCountMismatch(adv.location)

    val specMapInternal = makeSpecMap(ud.params, adv.params)
    ud.variants.exists { udVariant =>
      try {
        withTransaction(() => isEqual(udVariant.spec(specMapInternal), th));
        true
      } catch {
        case ex: Exception => false
      }
    }
  }

  def doCheck(expected: TypeHint, has: TypeHint): Unit =
    (expected, has) match {
      case (th, AnyTh) =>
      case (AnyTh, th) =>
      case (arraySize, sth: ScalarTh) if arraySize == thArraySize =>
        doCheck(thArraySizes, sth)
      case (array1: ScalarTh, array2: ScalarTh) if array1.isArray && array2.isArray =>
        val (_, _, decl1) = resolveType(ctx.level, ctx.module, array1)
        val (_, _, decl2) = resolveType(ctx.level, ctx.module, array2)

        decl1.getBuiltinArrayLen match {
          case None => // ok
          case has =>
            if (has != decl2.getBuiltinArrayLen)
              throw new MismatchLocal
        }

        isEqualSeq(array1.params, array2.params)
      case (adv: ScalarTh, sth: ScalarTh) =>
        val (_, advMod, advDecl) = resolveType(ctx.level, ctx.module, adv)
        val (_, sthMod, sthDecl) = resolveType(ctx.level, ctx.module, sth)
        if (advMod.pkg == sthMod.pkg && adv.name == sth.name) {
          if (adv.params.length != sth.params.length) throw TCE.ParamsCountMismatch(sth.location)
          isEqualSeq(adv.params, sth.params)
        }
        else {
          resolveType(ctx.level, ctx.module, adv) match {
            case (_, _, ud: UnionDecl) => checkUnionMember(ud, adv, sth)
            case _ => throw new MismatchLocal
          }
        }
      case (adv: ScalarTh, uth: UnionTh) => throw new MismatchLocal
      case (adv: ScalarTh, th) =>
        resolveType(ctx.level, ctx.module, adv) match {
          case (_, _, ud: UnionDecl) => checkUnionMember(ud, adv, th)
          case _ => throw new MismatchLocal
        }
      case (adv: UnionTh, th: ScalarTh) =>
        adv.seq.exists { advVariant =>
          try {
            withTransaction(() => isEqual(advVariant, th));
            true
          } catch {
            case ex: MismatchLocal => false
          }
        }
      case (adv: UnionTh, th: UnionTh) =>
        if (adv.seq.length != th.seq.length) throw new MismatchLocal
        (adv.seq zip th.seq).forall {
          case (v1, v2) =>
            try {
              doCheck(v1, v2);
              true
            } catch {
              case ex: MismatchLocal => false
            }
        }
      case (adv, th) => isEqual(expected, has)
    }

  def check(location: Seq[AstInfo], expected: TypeHint, has: TypeHint): Unit = {
    try {
      withTransaction(() => doCheck(expected, has))
    } catch {
      case ex: MismatchLocal =>
        if (expected == thArraySize)
          throw TCE.ArraySizeExpected(location, thArraySizes, has)
        else
          throw TCE.TypeMismatch(location, expected, has)
    }
  }
}

class TypeInfer(val ctx: PassContext,
                val onSpec: (TypeInfer, GenericTh, TypeHint) => Unit = (self, gth, th) => {}) {

  var specMap = mutable.HashMap[GenericTh, TypeHint]()

  def withTransaction(lambda: () => Unit): Unit = {
    val backupSpecMap = mutable.HashMap[GenericTh, TypeHint](specMap.toSeq: _*)

    try {
      lambda()
    } catch {
      case ex: Exception =>
        specMap = backupSpecMap
        throw ex;
    }
  }

  def infer(location: Seq[AstInfo], expected: TypeHint, has: TypeHint): Unit = {
    new TypeChecker2(
      ctx,
      genericSpec = (adv, th) => {
        specMap.get(adv) match {
          case Some(specified) => Specified(specified)
          case None =>
            specMap.put(adv, th); onSpec(this, adv, th); NewSpec
        }
      },
      withTransaction).check(location, expected, has)
  }
}

case class Equation(selfType: (Seq[AstInfo], GenericTh),
                    fnName: String,
                    args: Seq[(Seq[AstInfo], TypeHint)],
                    ret: (Seq[AstInfo], TypeHint)) {
  override def toString: String = selfType._2 + "::" + fnName + args.map(_._2).mkString("(", ", ", ")") + " -> " + ret._2
}

class Equations(val typeParams: mutable.ListBuffer[GenericTh] = mutable.ListBuffer()) {
  val eqSeq = mutable.ListBuffer[Equation]()
  var idSeq = 0

  def nextAnonType(): GenericTh = {
    idSeq += 1
    GenericTh("a" + idSeq, isAnon = true)
  }

  def addEq(eq: Equation): Unit =
    eqSeq += eq

  override def toString: String = if (eqSeq.length < 2) eqSeq.mkString("[ ", "", " ]") else eqSeq.mkString("[ ", " , ", " ]")
}

class TypeCheckPass {
  trait InferTask {
    def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint)
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
          st.fields.foreach(f => f.th.assertCorrect(ctx, st.params))
        case un: UnionDecl =>
          if (un.variants.length != un.variants.toSet.size)
            throw TCE.UnionMembersNotUnique(un.location)
      }
    }
  }

  class CallInfer(val ctx: PassContext,
                  val callLocation: Seq[AstInfo],
                  val eqCallee: Equations) {

    var anonIdSeq = eqCallee.idSeq
    var eqSeq = eqCallee.eqSeq
    val tInfer = new TypeInfer(ctx)

    def check(lctx: PassContext, infer: TypeInfer, eq: Equation): Seq[Equation] = {
      val (eqSelfLocation, eqSelfTh) = eq.selfType
      val eqSelfSpec = eqSelfTh.spec(infer.specMap)

      if (!eqSelfSpec.isInstanceOf[GenericTh] && !eqSelfSpec.containsAny) {
        lctx.log(s"check $eq vs $eqSelfSpec")

        val (fnTh, fnEq) = Invoker.findSelfDef(lctx, eqSelfLocation, eqSelfSpec, eq.fnName)

        val selfFnInfer = new TypeInfer(lctx)
        selfFnInfer.infer(Seq() /* ??? */ , fnTh.args(0), eqSelfSpec)

        val selfFn = fnTh.spec(selfFnInfer.specMap, gth => gth).asInstanceOf[FnTh]


        val localInfer = new TypeInfer(lctx)

        ((eq.ret +: eq.args) zip (selfFn.ret +: selfFn.args.drop(1)))
          .foreach { case ((eqArgLocation, eqArgTh), fnArgTh) =>
            localInfer.infer(eqArgLocation, eqArgTh, fnArgTh)
          }

        val reversed = localInfer.specMap.filter { case (_, v) =>
          v.isInstanceOf[GenericTh]
        }.map({ case (k, v) => (v.asInstanceOf[GenericTh], k.asInstanceOf[TypeHint]) })

        val highOrder = fnEq.eqSeq.flatMap(eq => check(lctx.deeperExpr(), selfFnInfer, eq))

        def mySmartSpec(th: TypeHint): TypeHint = {
          th.spec(reversed, onNotFound = { gth =>
            anonIdSeq += 1 // FIXME BUG
            val bound = GenericTh("a" + (anonIdSeq + 1) /*FIXME BUG */ , isAnon = true)
            localInfer.specMap.put(bound, gth)
            reversed.put(gth, bound)
            bound
          })
        }

        val res = highOrder.map { eq =>
          val lifted = Equation((eq.selfType._1, mySmartSpec(eq.selfType._2).asInstanceOf[GenericTh]), eq.fnName, eq.args.map { arg =>
            (arg._1, mySmartSpec(arg._2))
          }, (eq.ret._1, mySmartSpec(eq.ret._2)))

          lctx.log(s"lift $lifted")
          lifted
        }

        localInfer.specMap.foreach { case (eqTh, inferedTh) =>
          val spec = inferedTh.spec(reversed)

          if (!spec.isInstanceOf[GenericTh])
            infer.infer(Seq() /* ??? */ , eqTh, spec)
        }

        lctx.log(s"checkspec ${localInfer.specMap.mkString("{", " , ", "}")}")
        res
      } else
        Seq(eq)
    }

    def infer(location: Seq[AstInfo], thCallee: TypeHint, thCaller: TypeHint): Unit = {
      tInfer.infer(location, thCallee, thCaller)
      eqSeq = eqSeq.flatMap(eq => check(ctx.deeperExpr(), tInfer, eq))
    }

    def advice(thCallee: TypeHint): TypeHint =
      thCallee.spec(tInfer.specMap)

    def lift(eqCaller: Equations): Unit =
      eqCaller.eqSeq ++= eqSeq.map { eq =>
        val lifted = Equation((eq.selfType._1, eq.selfType._2.spec(tInfer.specMap).asInstanceOf[GenericTh]), eq.fnName, eq.args.map { arg =>
          (arg._1, arg._2.spec(tInfer.specMap))
        }, (eq.ret._1, eq.ret._2.spec(tInfer.specMap)))

        ctx.log(s"lift $lifted")
        lifted
      }
  }

  object Invoker {
    def isSelfApplicable(ctx: PassContext, selfTh: TypeHint, selfArgTh: TypeHint): Boolean =
      try {
        new TypeInfer(ctx).infer(Seq(selfTh.location), selfTh, selfArgTh);
        true
      } catch {
        case ex: Exception => false
      }

    def findSelfDef(ctx: PassContext, location: Seq[AstInfo], selfTh: TypeHint, fnName: String): (FnTh, Equations) = {
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
              (fn.getTypeHint, fn.getEquations)
            case None =>
              ctx.module.imports.seq.flatMap { ie =>
                val mod = ctx.level.findMod(ie.path).getOrElse(throw TCE.NoSuchModulePath(ie.location))
                mod.selfDefs.getOrElse(fnName, Seq()).map(fn => (ie, fn))
              }.find { case (ie, fn) => isSelfApplicable(ctx, fn.lambda.args.head.typeHint.moveToMod(Seq(ie)), selfTh) } match {
                case Some((ie, fn)) =>
                  (fn.getTypeHint, fn.getEquations)
                case None =>
                  (resolveBuiltinSelfDef(location, selfTh, fnName), new Equations())
              }
          }
      }
    }

    def findSelfDefOpt(ctx: PassContext, location: Seq[AstInfo], selfTh: TypeHint, fnName: String): Option[(FnTh, Equations)] =
      try {
        Some(findSelfDef(ctx, location, selfTh, fnName))
      } catch {
        case _: NoSuchSelfDef => None
      }

    def invokePrototype(ctx: PassContext, eqCaller: Equations, eqCallee: Equations, location: AstInfo,
                        retTh: TypeHint, fnTh: FnTh, args: Seq[InferTask]): TypeHint = {

      if (fnTh.args.length != args.length)
        throw TCE.ArgsCountMismatch(Seq(location), fnTh.args.length, args.length)

      val eqInfer = new CallInfer(ctx, Seq(location), eqCallee)


      if (retTh != AnyTh) {
        ctx.log(s"passret ${fnTh.ret} <= $retTh")
        eqInfer.infer(Seq(location), fnTh.ret, retTh)
        ctx.log("callspec " + eqInfer.tInfer.specMap.mkString("{", " , ", "}"))
      }


      (fnTh.args zip args).zipWithIndex.foreach { case ((defArgTh, argTask), idx) =>
        val advice = eqInfer.advice(defArgTh)
          .spec(mutable.HashMap()) /* sieve generics??? */

        ctx.log(s"inferarg $idx adviced $advice")
        val (argLocation, argTh) = argTask.infer(eqInfer.ctx, eqCaller, advice)

        ctx.log(s"passarg $idx $defArgTh <= $argTh")
        eqInfer.infer(Seq(argLocation), defArgTh, argTh)
        ctx.log("callspec " + eqInfer.tInfer.specMap.mkString("{", " , ", "}"))
      }

      eqInfer.lift(eqCaller)
      eqInfer.advice(fnTh.ret)
    }

    def invokeDef(ctx: PassContext, eqCaller: Equations, location: AstInfo,
                  retTh: TypeHint, toCall: Def, args: Seq[InferTask]): TypeHint =
      invokePrototype(ctx, eqCaller, toCall.getEquations, location, retTh, toCall.getTypeHint, args)

    def invokeSelfDef(ctx: PassContext, eqCaller: Equations, call: ParseNode,
                      retTh: TypeHint, fnName: String, self: InferTask, args: Seq[InferTask]): TypeHint = {

      val location = call.location
      val (selfLocation, selfTh) = self.infer(ctx, eqCaller, AnyTh)

      selfTh match {
        // FIXME: try to resolve generic function locally at first?
        // FIXME: variant 2 - declare self function on ScalarTh only
        case gth: GenericTh =>
          val argsTh = args.map(argTask => argTask.infer(ctx, eqCaller, AnyTh))
            .map { case (loc, th) => (Seq(loc), th) }
          val eqRet = if (retTh != AnyTh) retTh else eqCaller.nextAnonType()

          eqCaller.addEq(Equation((Seq(selfLocation), gth), fnName, argsTh, (Seq(location), eqRet)))
          eqRet
        case _ =>
          val (fnTh, fnEq) = findSelfDef(ctx, Seq(location), selfTh, fnName)
          call.setCallFnTh(fnTh)
          invokePrototype(ctx, eqCaller, fnEq, location, retTh, fnTh, new InferTask {
            override def infer(ctx: PassContext, eq: Equations, th: TypeHint) = (selfLocation, selfTh)
          } +: args)
      }
    }
  }


  def passExpr(ctx: PassContext, scope: Scope2.BlockScope, eq: Equations, th: TypeHint, expr: Expression): TypeHint = {
    def foldFields(from: TypeHint, fields: Seq[lId]): TypeHint =
      fields.foldLeft(from) {
        case (sth: ScalarTh, fieldId) =>
          resolveType(ctx.level, ctx.module, sth) match {
            case (_, _, sd: StructDecl) =>
              sd.fields
                .find(fd => fd.name == fieldId.value)
                .getOrElse(throw TCE.NoSuchField(fieldId.location, sth, fieldId.value))
                .th.spec(makeSpecMap(sd.params, sth.params))
            case (_, _, td) => throw TCE.NoSuchField(fieldId.location, sth, fieldId.value)
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
                    Invoker.invokePrototype(ctx, eq, new Equations(), call.location, th, fth, args.map { arg =>
                      new InferTask {
                        override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                          (arg.location, passExpr(ctx.deeperExpr(), scope, eq, th, arg))
                      }
                    })
                  case selfTh =>
                    call.setCallType(SelfCallModLocal) // FIXME: not true if self-def in another module
                    Invoker.invokeSelfDef(ctx, eq, call, th, "get",
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

                call.setCallType(CallModLocal)

                Invoker.invokeDef(ctx, eq, call.location, th, toCall, args.map { arg =>
                  new InferTask {
                    override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                      (arg.location, passExpr(ctx.deeperExpr(), scope, eq, th, arg))
                  }
                })
            }
          case lambda: Lambda =>
            val lambdaTh = passExpr(ctx, scope, eq, FnTh(Seq.empty, lambda.args.map(_ => AnyTh), th), lambda).asInstanceOf[FnTh]

            call.setCallType(CallFnPtr)

            Invoker.invokePrototype(ctx, eq, new Equations(), call.location, th, lambdaTh, args.map { arg =>
              new InferTask {
                override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                  (arg.location, passExpr(ctx, scope, eq, th, arg))
              }
            })
          case expr =>
            throw TCE.ExpressionNotCallable(expr.location)
        }
      case call@SelfCall(fnName, self, args) =>
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

            call.setCallType(CallModImport)

            Invoker.invokePrototype(ctx, eq, toCall.getEquations, call.location, th, toCall.getTypeHint, argTasks).moveToMod(Seq(ie))
          case _expr =>
            val selfTh = passExpr(ctx.deeperExpr(), scope, eq, AnyTh, _expr)
            val selfTask = new InferTask {
              override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                (self.location, selfTh)
            }

            def default() = {
              call.setCallType(SelfCallModLocal) //FIXME: not true if self-def in another mod
              Invoker.invokeSelfDef(ctx, eq, call, th, fnName, selfTask, argTasks)
            }

            def callField(fth: FnTh) = {
              call.setCallType(CallFnPtr)
              Invoker.invokePrototype(ctx, eq, new Equations(), call.location, th, fth, argTasks)
            }

            selfTh match {
              case sth: ScalarTh =>
                resolveType(ctx.level, ctx.module, sth) match {
                  case (_, _, sd: StructDecl) =>
                    sd.fields.find(fd => fd.name == fnName) match {
                      case Some(field) =>
                        field.th.spec(makeSpecMap(sd.params, sth.params)) match {
                          case fth: FnTh =>
                            callField(fth)
                          case gettableTh if Invoker.findSelfDefOpt(ctx, Seq(call.location), gettableTh, "get") != None =>
                            val (selfFth, selfEq) = Invoker.findSelfDef(ctx, Seq(call.location), gettableTh, "get")
                            call.setCallType(SelfCallModLocal) //FIXME: not true if self-def in another mod

                            //FIXME: why not invoke self def???
                            Invoker.invokePrototype(ctx, eq, selfEq, call.location, th, selfFth, new InferTask {
                              override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) = (self.location, gettableTh)
                            } +: argTasks)
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
              case _ => default()
            }
        }
      case cons@Cons(sth, args) =>
        val sd = resolveType(ctx.level, ctx.module, sth) match {
          case (_, _, sd: StructDecl) => sd
          case _ => throw TCE.StructTypeRequired(cons.location)
        }

        if (sd.isBuiltinArray) {
          val argsTh = args.map(arg => passExpr(ctx.deeperExpr(), scope, eq, sth.params.headOption.getOrElse(AnyTh), arg))
          val thSet = argsTh.toSet

          val inferedElTh =
            if (thSet.size == 1) thSet.head
            else UnionTh(thSet.toSeq)


          sth.params.headOption match {
            case Some(elTh) =>
              new TypeChecker2(ctx).doCheck(elTh, inferedElTh)
            case None =>
          }

          sd.getBuiltinArrayLen match {
            case Some(len) =>
              ScalarTh(Seq(inferedElTh), "Array" + len, Seq.empty)
            case None =>
              ScalarTh(Seq(inferedElTh), "Array", Seq.empty)
          }
        } else {
          val realParams =
            if (th.isInstanceOf[ScalarTh]) { // FIXME: check types more pedantic
              th.asInstanceOf[ScalarTh].params
            } else if (sth.params.nonEmpty) {
              if (sth.params.length != sd.params.length) throw TCE.ParamsCountMismatch(sth.location)
              sth.params
            } else
              sd.params

          val realTh = ScalarTh(realParams, sth.name, sth.mod)
          realTh.setLocation(sth.location)
          val specMap = makeSpecMap(sd.params, realTh.params)
          val consTh = FnTh(Seq.empty, sd.fields.map(f => f.th.spec(specMap)), realTh)

          Invoker.invokePrototype(ctx, eq, new Equations(), cons.location, AnyTh, consTh, args.map { arg =>
            new InferTask {
              override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                (arg.location, passExpr(ctx.deeperExpr(), scope, eq, th, arg))
            }
          })
        }
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
        val eth = passExpr(ctx, scope, eq, AnyTh, from)
        foldFields(eth, props)
      case store@Store(varTh, to, what) =>
        varTh match {
          case gth: GenericTh if !eq.typeParams.contains(gth) => eq.typeParams += gth
          case _ =>
        }
        varTh.assertCorrect(ctx, eq.typeParams)
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
                try {
                  new TypeChecker2(ctx).isEqual(expectedTh, argTh);
                  argTh
                } catch {
                  case ex: MismatchLocal => throw TCE.TypeMismatch(Seq(argTh.location), expectedTh, argTh)
                }
            }
            (inferedArgsTh, fth.ret)
          case AnyTh =>
            val inferedArgsTh = args.map { arg =>
              if (arg.typeHint.containsAny) throw TCE.TypeHintRequired(arg.location)
              arg.typeHint
            }
            (inferedArgsTh, AnyTh)
          case _ =>
            throw TCE.TypeMismatch(Seq(lambda.location), th, FnTh(Seq.empty, argsTh, AnyTh))
        }

        val inferedRetTh = body match {
          case AbraCode(seq) =>
            val lambdaScope = LambdaScope(scope, (args.map(_.name) zip inferedArgsTh).toMap)
            val lambdaBlock = new Scope2.BlockScope(lambdaScope)

            seq.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), lambdaBlock, eq, AnyTh, expr))
            seq.lastOption.foreach { last =>
              lambdaBlock.addRetType(passExpr(ctx.deeperExpr(), lambdaBlock, eq, AnyTh, last))
            }

            lambda.setClosure(lambdaScope.closure
              .map { case (name, (th, vt)) => (name, th, vt) }
              .toSeq.sortBy { case (name, _, _) => name })

            lambdaScope.retTypes.distinct match {
              case Seq() => thNil
              case Seq(th) => th
              case seq => UnionTh(seq)
            }
          case _ =>
            throw TCE.LambdaWithNativeCode(lambda.location)
        }

        FnTh(Seq.empty, inferedArgsTh, inferedRetTh)
      case andOr: AndOr =>
        passExpr(ctx, scope, eq, thBool, andOr.left)
        passExpr(ctx, scope, eq, thBool, andOr.right)
        thBool
      case While(cond, _do) =>
        passExpr(ctx.deeperExpr(), scope, eq, thBool, cond)
        val block = new Scope2.WhileScope(scope)
        _do.foreach { expr => passExpr(ctx.deeperExpr(), block, eq, AnyTh, expr) }
        thNil
      case bc@(Break() | Continue()) =>
        def findWhileScope(sc: Scope2.Scope): Option[Scope2.WhileScope] =
          sc match {
            case ws: Scope2.WhileScope => Some(ws)
            case ds: Scope2.DefScope => None
            case ls: Scope2.LambdaScope => None
            case bs: Scope2.BlockScope => findWhileScope(bs.parent)
          }

        findWhileScope(scope).getOrElse(throw TCE.NoWhileForBreakOrContinue(bc.location))
        thNil
      case self@If(cond, _do, _else) =>
        passExpr(ctx.deeperExpr(), scope, eq, thBool, cond)

        val doBlock = new Scope2.BlockScope(scope)
        _do.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), doBlock, eq, AnyTh, expr))
        val doTh = _do.lastOption.map { last =>
          passExpr(ctx.deeperExpr(), doBlock, eq, AnyTh, last)
        }.getOrElse(thNil)

        val elseBlock = new Scope2.BlockScope(scope)
        _else.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), elseBlock, eq, AnyTh, expr))
        val elseTh = _else.lastOption.map { last =>
          passExpr(ctx.deeperExpr(), elseBlock, eq, AnyTh, last)
        }.getOrElse(thNil)

        self.setBranchTh((doTh, elseTh))

        Seq(doTh, elseTh).filter(th => th != thUnreachable).distinct match {
          case Seq(one) => one
          case many => UnionTh(many)
        }
      case self@Unless(expr, isSeq) =>
        val tc = new TypeChecker2(ctx)
        val exprTh = passExpr(ctx.deeperExpr(), scope, eq, AnyTh, expr)

        val exprUnionVariants = exprTh match {
          case uth: UnionTh => uth.seq
          case sth: ScalarTh =>
            resolveType(ctx.level, ctx.module, sth) match {
              case (_, _, ud: UnionDecl) => ud.variants.map(v => v.spec(makeSpecMap(ud.params, sth.params)))
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

          val blockScope = new Scope2.BlockScope(scope)
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

        val overallType = (uncoveredTypes ++ mappedType).toSeq

        overallType match {
          case Seq(one) => one
          case many => UnionTh(many)
        }
    }

    expr.setTypeHint(exprTh)
    new TypeChecker2(ctx).check(Seq(expr.location), th, exprTh)
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
      arg.typeHint.assertCorrect(ctx, eq.typeParams)
      (arg.name, arg.typeHint)
    }.toMap
    fn.retTh.assertCorrect(ctx, eq.typeParams)

    val defScope = DefScope(args)

    fn.lambda.body match {
      case llVm(_) =>
        if (fn.retTh.containsAny) throw TCE.RetTypeHintRequired(fn.location)

        fn.setEquations(eq)
        fn.setTypeHint(FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), fn.retTh))
      case AbraCode(seq) =>
        val declaredRetTh =
          if (fn.retTh != AnyTh) {
            fn.setTypeHint(FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), fn.retTh))
            fn.setEquations(new Equations())
            Some(fn.retTh)
          } else None

        val bodyScope = new Scope2.BlockScope(defScope)

        seq.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), bodyScope, eq, AnyTh, expr))
        seq.lastOption.foreach { last =>
          bodyScope.addRetType(passExpr(ctx.deeperExpr(), bodyScope, eq, fn.retTh, last))
        }

        val retTh = defScope.retTypes.distinct match {
          case Seq() => thNil
          case Seq(th) => th
          case seq => UnionTh(seq)
        }

        declaredRetTh match {
          case Some(declared) =>
            new TypeChecker2(ctx).check(Seq(declared.location), declared, retTh)
          case None =>
            fn.setTypeHint(FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), retTh))
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
          other.location != selfDef.location && Invoker.isSelfApplicable(ctx, selfTh, other.lambda.args.head.typeHint)
        } match {
          case Some(other) =>
            throw TCE.DoubleSelfDefDeclaration(other.location, selfTh)
          case None =>
        }
      }
    }
  }

  def pass(root: Level) =
    root.eachModule((level, module) => {
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
}