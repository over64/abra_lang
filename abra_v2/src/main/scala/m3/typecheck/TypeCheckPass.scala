package m3.typecheck

import m3.parse.Ast0.{Ret, _}
import m3.parse.{AstInfo, Level}
import m3.typecheck.Scope2._
import scala.collection.mutable

object Utils {
  implicit class ThExtension(self: TypeHint) {
    def isSame(other: TypeHint): Boolean = (self, other) match {
      case (g1: GenericTh, g2: GenericTh) => g1.typeName == g2.typeName
      case (sth1: ScalarTh, sth2: ScalarTh) =>
        sth1.name == sth2.name &&
          sth1.params.length == sth2.params.length &&
          (sth1.params zip sth2.params).forall { case (th1, th2) => th1.isSame(th2) }
      case (s1: StructTh, s2: StructTh) =>
        s1.seq.length == s2.seq.length &&
          (s1.seq zip s2.seq).forall { case (f1, f2) => f1.typeHint.isSame(f2.typeHint) }
      case (u1: UnionTh, u2: UnionTh) =>
        u1.seq.length == u2.seq.length &&
          (u1.seq zip u2.seq).forall { case (th1, th2) => th1.isSame(th2) }
      case (AnyTh, AnyTh) => true
      case _ => false
    }

    def assertCorrect(level: Level, module: Module): Unit = {
      module
    }

    def spec(specMap: mutable.HashMap[GenericTh, TypeHint],
             onNotFound: GenericTh => TypeHint = gth => AnyTh): TypeHint =
      self match {
        case ScalarTh(params, name, pkg) =>
          specMap.get(GenericTh(name)) match {
            case Some(th) => th
            case None => ScalarTh(params.map(p => p.spec(specMap, onNotFound)), name, pkg)
          }
        case StructTh(fields) =>
          StructTh(fields.map { field =>
            FieldTh(field.name, field.typeHint.spec(specMap, onNotFound))
          })
        case UnionTh(variants) =>
          UnionTh(variants.map { th =>
            th.spec(specMap, onNotFound)
          })
        case FnTh(closure, args, ret) =>
          FnTh(closure, args.map(arg => arg.spec(specMap, onNotFound)), ret.spec(specMap, onNotFound))
        case gth: GenericTh => specMap.getOrElse(gth, onNotFound(gth))
        case AnyTh => AnyTh
      }

    def containsAny: Boolean =
      self match {
        case ScalarTh(params, name, pkg) =>
          params.exists(p => p.containsAny)
        case StructTh(fields) =>
          fields.exists(f => f.typeHint.containsAny)
        case UnionTh(variants) =>
          variants.exists(v => v.containsAny)
        case FnTh(closure, args, ret) =>
          args.exists(a => a.containsAny) || ret.containsAny
        case gth: GenericTh => false
        case AnyTh => true
      }
  }

  def resolveType(level: Level, module: Module, th: ScalarTh): (Module, TypeDecl) = {
    val declModule = th.mod.foldLeft(module) {
      case (module, modName) =>
        val ie = module.imports.seq.find(ie => ie.modName == modName).getOrElse(throw TCE.NoSuchModulePath(th.location))
        level.findMod(ie.path).get
    }
    (declModule, declModule.types.getOrElse(th.name, throw TCE.NoSuchType(th.location, th.mod, th.name)))
  }

  def makeSpecMap(gen: Seq[GenericTh], params: Seq[TypeHint]) = {
    if (gen.length != params.length)
      throw new RuntimeException("params length mismatch")

    mutable.HashMap((gen zip params): _*)
  }
}

import Utils._

case class PassContext(deep: Int, level: Level, module: Module) {
  def deeper() = this.copy(deep = this.deep + 1)

  def log(msg: String): Unit = println("\t" * deep + msg)
}

class TypeChecker(ctx: PassContext,
                  genericSpec: (GenericTh, TypeHint) => TypeHint = (adv, th) => adv,
                  withTransaction: (() => Unit) => Unit) {
  class MismatchLocal extends Exception

  def isEqualSeq(expected: Seq[TypeHint], has: Seq[TypeHint]): Unit = {
    if (expected.length != has.length) throw new MismatchLocal
    (expected zip has).foreach { case (e, h) => isEqual(e, h) }
  }

  def isEqual(self: TypeHint, other: TypeHint): Unit =
    (self, other) match {
      case (th, AnyTh) => // ok
      case (AnyTh, th) => // ok
      case (adv: ScalarTh, th: ScalarTh) =>
        if (adv.name != th.name) throw new MismatchLocal
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
          case specified: GenericTh if th.isInstanceOf[GenericTh] =>
            val gth = th.asInstanceOf[GenericTh]
            if (specified.typeName != gth.typeName) throw new MismatchLocal
          case specifiedTh =>
            isEqual(specifiedTh, th)
        }
      case (adv, th) => throw new MismatchLocal
    }

  def checkUnionMember(ud: UnionDecl, adv: ScalarTh, th: TypeHint): Unit = {

    if (ud.params.length != adv.params.length) throw TCE.ParamsCountMismatch(adv.location)

    val specMapInternal = makeSpecMap(ud.params, adv.params)
    ud.variants.exists { udVariant =>
      try {
        withTransaction(() => isEqual(udVariant.spec(specMapInternal), th)); true
      } catch {
        case ex: Exception => false
      }
    }
  }

  def doCheck(expected: TypeHint, has: TypeHint): Unit =
    (expected, has) match {
      case (th, AnyTh) =>
      case (AnyTh, th) =>
      case (adv: ScalarTh, sth: ScalarTh) =>
        if (adv.name == sth.name) {
          if (adv.params.length != sth.params.length) throw TCE.ParamsCountMismatch(sth.location)
          isEqualSeq(adv.params, sth.params)
        }
        else {
          resolveType(ctx.level, ctx.module, adv) match {
            case (_, ud: UnionDecl) => checkUnionMember(ud, adv, sth)
            case _ => throw new MismatchLocal
          }
        }
      case (adv: ScalarTh, uth: UnionTh) => throw new MismatchLocal
      case (adv: ScalarTh, th) =>
        resolveType(ctx.level, ctx.module, adv) match {
          case (_, ud: UnionDecl) => checkUnionMember(ud, adv, th)
          case _ => throw new MismatchLocal
        }
      case (adv: UnionTh, th: ScalarTh) =>
        adv.seq.exists { advVariant =>
          try {
            withTransaction(() => isEqual(advVariant, th)); true
          } catch {
            case ex: MismatchLocal => false
          }
        }
      case (adv: UnionTh, th: UnionTh) =>
        if (adv.seq.length != th.seq.length) throw new MismatchLocal
        (adv.seq zip th.seq).forall {
          case (v1, v2) =>
            try {
              doCheck(v1, v2); true
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
      case ex: MismatchLocal => throw new TCE.TypeMismatch(location, expected, has)
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
    new TypeChecker(
      ctx,
      genericSpec = (adv, th) => {
        specMap.get(adv) match {
          case Some(specified) => specified
          case None => specMap.put(adv, th); onSpec(this, adv, th); th
        }
      },
      withTransaction).check(location, expected, has)
  }
}

case class Equation(selfType: (Seq[AstInfo], GenericTh),
                    fnName: String,
                    args: Seq[(Seq[AstInfo], TypeHint)],
                    ret: (Seq[AstInfo], TypeHint)) {
  override def toString: String = selfType + " :: " + fnName + args.mkString("(", ", ", ")") + " -> " + ret
}

class Equations {
  val eqSeq = mutable.ListBuffer[Equation]()
  var idSeq = 0

  def nextAnonType(): GenericTh = {
    idSeq += 1
    GenericTh("a" + idSeq, isAnon = true)
  }

  def addEq(eq: Equation): Unit =
    eqSeq += eq

  override def toString: String = if (eqSeq.length < 2) eqSeq.mkString("[ ", "", " ]") else eqSeq.mkString("[\n\t", "\n\t", "\n]")
}

class TypeCheckPass {
  trait InferTask {
    def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint)
  }

  val thInt = ScalarTh(params = Seq.empty, "Int", Seq("prelude"))
  val thFloat = ScalarTh(params = Seq.empty, "Float", Seq("prelude"))
  val thBool = ScalarTh(params = Seq.empty, "Bool", Seq("prelude"))
  val thString = ScalarTh(params = Seq.empty, "String", Seq("prelude"))
  val thNil = ScalarTh(params = Seq.empty, "None", Seq("prelude"))
  val thUnreachable = ScalarTh(params = Seq.empty, "Unreachable", Seq("prelude"))

  def checkTypeDecl(level: Level, module: Module): Unit = {
    module.types.values.foreach {
      case sc: ScalarDecl =>
      case st: StructDecl =>
        val fieldNames = st.fields.map(_.name)
        if (fieldNames.length != fieldNames.toSet.size) throw TCE.FieldNameNotUnique(st.location)
        st.fields.foreach(f => f.th.assertCorrect(level, module))
      case un: UnionDecl =>

    }
  }

  class EqInfer(val ctx: PassContext, val callLocation: Seq[AstInfo], myEq: Equations) {
    case class ApplyIndex(var idx: Int)

    def printInstance(eq: Equation, fth: FnTh) = {
      val instanceLike = fth.args.head + s" :: ${eq.fnName}" + fth.args.drop(1).mkString("(", ", ", ")") + " -> " + fth.ret
      s"$eq   like   $instanceLike"
    }

    val eqMap = mutable.HashMap[Equation, (FnTh, ApplyIndex, EqInfer)]()
    val tInfer = new TypeInfer(ctx)

    def anonSpec(th: TypeHint): TypeHint =
      th.spec(tInfer.specMap)

    def infer(location: Seq[AstInfo], expected: TypeHint, has: TypeHint): Unit = {
      tInfer.infer(location, expected, has)

      myEq.eqSeq.foreach { eq =>
        if (eqMap.get(eq).isEmpty) {
          val (selfLocation, selfType) = eq.selfType
          val specSelf = anonSpec(selfType)

          if (!specSelf.isInstanceOf[GenericTh] && !specSelf.containsAny) {
            ctx.log(s"instance eq $eq")

            val fn = Invoker.findSelfDef(ctx, specSelf, eq.fnName)
            if (fn.getTypeHintOpt == None) passDef(ctx, fn)
            val fnTh: FnTh = fn.getTypeHint

            if (fnTh.args.drop(1).length != eq.args.length)
              throw new TCE.ArgsCountMismatch(selfLocation, fnTh.args.drop(1).length, eq.args.length)

            ctx.log("instance   " + printInstance(eq, fn.getTypeHint))

            val next = new EqInfer(ctx.deeper(), selfLocation, fn.getEquations)
            eqMap.put(eq, (fn.getTypeHint, ApplyIndex(0), next))
          }
        }
      }

      var changed = true
      while (changed) {
        changed = false

        eqMap.foreach { case (eq, (fnTh, applyIdx, next)) =>
          def idxToArg(idx: Int) =
            idx match {
              case 0 => "self"
              case 1 => "ret"
              case i => s"arg(${i - 1})"
            }

          ((fnTh.args.head +: fnTh.ret +: fnTh.args.drop(1)) zip (eq.selfType +: eq.ret +: eq.args))
            .zipWithIndex
            .drop(applyIdx.idx).take(1)
            .foreach { case ((argTh, eqTh), idx) =>
              eqTh match {
                case (_, generic: GenericTh) if generic.isAnon =>
                  val argSpec = next.anonSpec(argTh)
                  if (!argSpec.containsAny) {
                    ctx.log(s"anon       ${printInstance(eq, fnTh)}   where   ${idxToArg(idx)} $eqTh => $argTh")
                    tInfer.specMap.put(generic, argSpec)
                    applyIdx.idx += 1
                    changed = true
                  }
                case (glocation, generic: GenericTh) =>
                  tInfer.specMap.get(generic).foreach { specified =>
                    ctx.log(s"infer      ${printInstance(eq, fnTh)}   where   ${idxToArg(idx)} $eqTh @ $specified => $argTh")
                    next.infer(glocation ++ location, argTh, specified)
                    applyIdx.idx += 1
                    changed = true
                  }
                case other =>
                  val (othetLocation, otherTh) = other
                  val otherSpec = this.anonSpec(otherTh)
                  if (!otherSpec.containsAny) {
                    ctx.log(s"infer      ${printInstance(eq, fnTh)}   where   ${idxToArg(idx)} $otherSpec => $argTh")
                    next.infer(othetLocation ++ location, argTh, otherSpec)
                    applyIdx.idx += 1
                    changed = true
                  }
              }
            }
        }
      }
    }

    def drainEq(idSeq: Int): (Int, Seq[Equation]) = {
      var anonSeq = idSeq
      val local = myEq.eqSeq.filter(eq => !eqMap.contains(eq)).map { eq =>
        def spec[T <: TypeHint](locAndTh: (Seq[AstInfo], T)) = {
          val (location, th) = locAndTh
          (location ++ callLocation, th.spec(tInfer.specMap, gth => {
            anonSeq += 1
            val anon = GenericTh("a" + anonSeq, true)
            tInfer.specMap.put(gth, anon)
            anon
          }).asInstanceOf[T])
        }

        Equation(
          spec(eq.selfType),
          eq.fnName,
          eq.args.map(th => spec(th)),
          spec(eq.ret)
        )
      }

      eqMap.values.map(_._3).foldLeft((anonSeq, local)) { case ((idSeq, eqList), next) =>
        def addCallLocation[T <: TypeHint](eqMember: (Seq[AstInfo], T)) =
          (eqMember._1 ++ callLocation, eqMember._2)

        val (nextIdSeq, nextList) = next.drainEq(idSeq)
        val nextListWithLoc = nextList.map { eq =>
          Equation(
            addCallLocation(eq.selfType),
            eq.fnName,
            eq.args.map(arg => addCallLocation(arg)),
            addCallLocation(eq.ret)
          )
        }
        (nextIdSeq, eqList ++ nextListWithLoc)
      }
    }
  }

  object Invoker {
    def findSelfDef(ctx: PassContext, selfTh: TypeHint, fnName: String): Def = {
      def isSelfApplicable(selfTh: TypeHint, selfArgTh: TypeHint): Boolean =
        try {
          new TypeInfer(ctx.deeper()).infer(Seq(selfTh.location), selfTh, selfArgTh); true
        } catch {
          case ex: Exception => false
        }

      selfTh match {
        case gth: GenericTh =>
          throw new RuntimeException("Unexpected to be here")
        case _ =>
          ctx.module.selfDefs.get(fnName).flatMap { defs =>
            defs.find(d => isSelfApplicable(d.lambda.args.head.typeHint, selfTh))
          } match {
            case Some(fn) => fn
            case None =>
              throw new RuntimeException("mod self call not impelemented")
          }
      }
    }

    def invokePrototype(ctx: PassContext, eqCaller: Equations, eqCallee: Equations, location: AstInfo,
                        retTh: TypeHint, fnTh: FnTh, args: Seq[InferTask]): TypeHint = {

      if (fnTh.args.length != args.length)
        throw TCE.ArgsCountMismatch(Seq(location), fnTh.args.length, args.length)

      val eqInfer = new EqInfer(ctx, Seq(location), eqCallee)

      eqInfer.infer(Seq(location), fnTh.ret, retTh)

      (fnTh.args zip args).foreach { case (defArgTh, argTask) =>
        val (argLocation, argTh) = argTask.infer(eqInfer.ctx, eqCaller, eqInfer.anonSpec(defArgTh))
        eqInfer.infer(Seq(argLocation), defArgTh, argTh)
      }

      val (newIdSeq, eqList) = eqInfer.drainEq(eqCaller.idSeq)

      eqCaller.idSeq = newIdSeq
      eqCaller.eqSeq ++= eqList

      eqInfer.anonSpec(fnTh.ret)
    }

    def invokeDef(ctx: PassContext, eqCaller: Equations, location: AstInfo,
                  retTh: TypeHint, toCall: Def, args: Seq[InferTask]): TypeHint =
      invokePrototype(ctx, eqCaller, toCall.getEquations, location, retTh, toCall.getTypeHint, args)

    def invokeSelfDef(ctx: PassContext, eqCaller: Equations, location: AstInfo,
                      retTh: TypeHint, fnName: String, self: InferTask, args: Seq[InferTask]): TypeHint = {

      val (selfLocation, selfTh) = self.infer(ctx.deeper(), eqCaller, AnyTh)

      selfTh match {
        case gth: GenericTh =>
          val argsTh = args.map(argTask => argTask.infer(ctx.deeper(), eqCaller, AnyTh))
            .map { case (loc, th) => (Seq(loc), th) }
          val eqRet = if (retTh != AnyTh) retTh else eqCaller.nextAnonType()

          eqCaller.addEq(Equation((Seq(selfLocation), gth), fnName, argsTh, (Seq(location), eqRet)))
          eqRet
        case _ =>
          val fn = findSelfDef(ctx, selfTh, fnName)

          if (fn.getEquationsOpt == None) passDef(ctx.deeper(), fn)

          invokeDef(ctx, eqCaller, location, retTh, fn, new InferTask {
            override def infer(ctx: PassContext, eq: Equations, th: TypeHint) = (selfLocation, selfTh)
          } +: args)
      }
    }
  }


  def passExpr(ctx: PassContext, scope: Scope2.BlockScope, eq: Equations, th: TypeHint, expr: Expression): TypeHint = {
    ctx.log(s"pass expr $expr")
    val exprTh = expr match {
      case _: lInt => thInt
      case _: lFloat => thFloat
      case _: lBoolean => thBool
      case _: lString => thString
      case _: lNone => thNil
      case id: lId =>
        scope.findVar(id.value) match {
          case Some((varTh, _)) => varTh
          case None =>
            val fn = ctx.module.defs.getOrElse(id.value, throw TCE.NoSuchSymbol(id.location, id.value))

            if (fn.retTh == AnyTh) passDef(ctx.deeper(), fn)

            FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), fn.retTh)
        }
      case Ret(opt) =>
        val retTh = passExpr(ctx.deeper(), scope, eq, th, opt.getOrElse(lNone()))
        scope.addRetType(retTh)
        retTh
      case call@Call(expr, args) =>
        expr match {
          case id: lId =>
            scope.findVar(id.value) match {
              case Some((varTh, _)) =>
                varTh match {
                  case fth: FnTh => Invoker.invokePrototype(ctx, eq, new Equations(), call.location, th, fth, args.map { arg =>
                    new InferTask {
                      override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                        (arg.location, passExpr(ctx, scope, eq, th, arg))
                    }
                  })
                  case selfTh => Invoker.invokeSelfDef(ctx, eq, call.location, th, "get",
                    new InferTask {
                      override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                        (id.location, passExpr(ctx, scope, eq, th, id))
                    },
                    args.map { arg =>
                      new InferTask {
                        override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                          (arg.location, passExpr(ctx, scope, eq, th, arg))
                      }
                    })
                }
              case None =>
                val toCall = ctx.module.defs.getOrElse(id.value, throw TCE.NoSuchCallable(id.location, id.value))
                if (toCall.getEquationsOpt == None)
                  passDef(ctx.deeper(), toCall)

                Invoker.invokeDef(ctx, eq, call.location, th, toCall, args.map { arg =>
                  new InferTask {
                    override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                      (arg.location, passExpr(ctx, scope, eq, th, arg))
                  }
                })
            }
          case _expr => throw new RuntimeException("not implemented")
        }
      case call@SelfCall(fnName, self, args) =>
        self match {
          case id: lId =>
            Invoker.invokeSelfDef(ctx, eq, call.location, th, fnName,
              new InferTask {
                override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                  (self.location, passExpr(ctx, scope, eq, th, self))
              },
              args.map { arg =>
                new InferTask {
                  override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                    (arg.location, passExpr(ctx, scope, eq, th, arg))
                }
              })
          case _expr: Expression =>
            Invoker.invokeSelfDef(ctx, eq, call.location, th, fnName,
              new InferTask {
                override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                  (self.location, passExpr(ctx, scope, eq, th, self))
              },
              args.map { arg =>
                new InferTask {
                  override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                    (arg.location, passExpr(ctx, scope, eq, th, arg))
                }
              })
        }
      case store@Store(varTh, to, what) =>
        val inferedVarTh = passExpr(ctx.deeper(), scope, eq, varTh, what)
        scope.addLocal(to.head.value, inferedVarTh)
        store.setDeclTh(inferedVarTh)
        thNil
      case lambda@Lambda(args, body) =>
        val fn = Def("anonLambda", lambda, AnyTh)

        th match {
          case fth: FnTh if fth.args.length == lambda.args.length =>
            (fth.args zip lambda.args).foreach {
              case (AnyTh, arg) =>
              case (ght: GenericTh, arg) =>
              case (th, arg) => arg.typeHint = th
            }
            fn.retTh = fth.ret
          case _ =>
        }

        passDef(ctx.deeper(), fn)

        val resultTh = FnTh(Seq.empty, lambda.args.map(_.typeHint), fn.retTh)
        new TypeInfer(ctx.deeper()).infer(Seq(fn.location), th, resultTh)

        resultTh
    }
    expr.setTypeHint(exprTh)
    exprTh
  }

  def passDef(ctx: PassContext, fn: Def): Unit = {
    ctx.log(s"pass function ${fn.name}")

    val args = fn.lambda.args.map { arg => (arg.name, arg.typeHint) }.toMap
    val defScope = DefScope(args)

    fn.lambda.body match {
      case llVm(code) =>
        if (fn.retTh.containsAny) throw TCE.RetTypeHintRequired(fn.location)

        fn.setEquations(new Equations())
        fn.setTypeHint(FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), fn.retTh))
      case AbraCode(seq) =>
        val bodyScope = BlockScope(Some(defScope))
        val eq = new Equations()

        if (seq.isEmpty)
          passExpr(ctx.deeper(), bodyScope, eq, fn.retTh, Ret(None))
        else {
          seq.last match {
            case ret: Ret =>
              seq.dropRight(1).foreach(expr => passExpr(ctx.deeper(), bodyScope, eq, AnyTh, expr))
              passExpr(ctx.deeper(), bodyScope, eq, fn.retTh, ret)
            case other =>
              seq.dropRight(1).foreach(expr => passExpr(ctx.deeper(), bodyScope, eq, AnyTh, expr))
              passExpr(ctx.deeper(), bodyScope, eq, fn.retTh, Ret(Some(other)))
          }
        }

        val retTh = defScope.retTypes match {
          case Seq() => thNil
          case Seq(th) => th
          case seq => UnionTh(seq) // FIXME: infer super type?
        }

        fn.setEquations(eq)
        fn.setTypeHint(FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), retTh))
    }
    ctx.log("****")
  }

  def pass(root: Level) =
    root.eachModule((level, module) => {
      checkTypeDecl(level, module)
      module.defs.values.foreach(fn =>
        if (fn.getEquationsOpt == None)
          passDef(PassContext(0, level, module), fn))
    })
}