package m3._02typecheck

import m3.Ast0._
import m3._01parse.ParseMeta._
import m3._02typecheck.TCMeta._
import m3.{AstInfo, ThUtil, TypeInfer}

import scala.collection.immutable.ArraySeq
import scala.collection.mutable.{ArrayBuffer, Buffer, HashMap}

case class Equation(selfType: (Seq[AstInfo], GenericTh),
                    fnName: String,
                    args: ArraySeq[(Seq[AstInfo], TypeHint)],
                    ret: (Seq[AstInfo], TypeHint)) {
  override def toString: String = selfType._2 + "::" + fnName + args.map(_._2).mkString("(", ", ", ")") + " -> " + ret._2
}

class Equations(val typeParams: ArrayBuffer[GenericTh] = ArrayBuffer()) {
  val eqSeq = Buffer[Equation]()
  var idSeq = 0

  def nextAnonType(): GenericTh = {
    idSeq += 1
    GenericTh("a" + idSeq, isAnon = true)
  }

  def addEq(eq: Equation): Unit =
    eqSeq += eq

  override def toString: String = eqSeq.mkString("[ ", " , ", " ]")
}

class CallInfer(val ctx: PassContext,
                val findSelfDef: (PassContext, Seq[AstInfo], TypeHint, String) => (Seq[ImportEntry], Module, Def, FnTh, Equations),
                val caller: ParseNode,
                val eqCallee: Equations) {

  var anonIdSeq = eqCallee.idSeq
  var eqSeq = eqCallee.eqSeq
  val tInfer = new TypeInfer(ctx.level, ctx.module)

  def check(lctx: PassContext, infer: TypeInfer, eq: Equation): Buffer[Equation] = {
    val (eqSelfLocation, eqSelfTh) = eq.selfType
    val eqSelfSpec = ThUtil.spec(eqSelfTh, infer.specMap)

    if (!eqSelfSpec.isInstanceOf[GenericTh] && !ThUtil.containsAny(eqSelfSpec)) {
      lctx.log(s"check $eq vs $eqSelfSpec")

      val (ieSeq, mod, fn, fnTh, fnEq) = findSelfDef(lctx, eqSelfLocation, eqSelfSpec, eq.fnName)
      caller.addResolvedSelfDef(eqSelfSpec, eq.fnName, mod, fn)

      val selfFnInfer = new TypeInfer(lctx.level, lctx.module)
      selfFnInfer.infer(Seq() /* ??? */ , fnTh.args(0), eqSelfSpec)

      val selfFn = ThUtil.spec(fnTh, selfFnInfer.specMap, gth => gth).asInstanceOf[FnTh]

      val localInfer = new TypeInfer(lctx.level, lctx.module)

      ((eq.ret +: eq.args) zip (selfFn.ret +: selfFn.args.drop(1)))
        .foreach { case ((eqArgLocation, eqArgTh), fnArgTh) =>
          localInfer.infer(eqArgLocation, eqArgTh, fnArgTh)
        }

      val reversed = localInfer.specMap
        .filter { case (_, v) => v.isInstanceOf[GenericTh]}
        .map[GenericTh, TypeHint] { case (k, v) => (v.asInstanceOf[GenericTh], k.asInstanceOf[TypeHint]) }

      val highOrder = fnEq.eqSeq.flatMap(eq => check(lctx.deeperExpr(), selfFnInfer, eq))

      def mySmartSpec(th: TypeHint): TypeHint = {
        ThUtil.spec(th, reversed, onNotFound = { gth =>
          anonIdSeq += 1 // FIXME BUG
          val bound = GenericTh("a" + anonIdSeq, isAnon = true)
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
        val spec = ThUtil.spec(inferedTh, reversed)

        if (!spec.isInstanceOf[GenericTh])
          infer.infer(Seq() /* ??? */ , eqTh, spec)
      }

      lctx.log(s"checkspec ${localInfer.specMap.mkString("{", " , ", "}")}")
      res
    } else
      Buffer(eq)
  }

  def infer(location: Seq[AstInfo], thCallee: TypeHint, thCaller: TypeHint): Unit = {
    tInfer.infer(location, thCallee, thCaller)
    eqSeq = eqSeq.flatMap(eq => check(ctx.deeperExpr(), tInfer, eq))
  }

  def advice(thCallee: TypeHint): TypeHint =
    ThUtil.spec(thCallee, tInfer.specMap)

  def lift(eqCaller: Equations): Unit = {
    def mySmartSpec(th: TypeHint): TypeHint = {
      ThUtil.spec(th, tInfer.specMap, onNotFound = { gth =>
        if (gth.isAnon) {
          anonIdSeq += 1
          val bound = GenericTh("a" + anonIdSeq, isAnon = true)
          tInfer.specMap.put(gth, bound)
          bound
        } else throw new RuntimeException("oops???")
      })
    }

    eqCaller.eqSeq ++= eqSeq.map { eq =>
      val lifted = Equation((eq.selfType._1, mySmartSpec(eq.selfType._2).asInstanceOf[GenericTh]), eq.fnName, eq.args.map { arg =>
        (arg._1, mySmartSpec(arg._2))
      }, (eq.ret._1, mySmartSpec(eq.ret._2)))

      ctx.log(s"lift $lifted")
      lifted
    }

    eqCaller.idSeq = anonIdSeq
  }
}

trait InferTask {
  def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint)
}

class Invoker(val findSelfDef: (PassContext, Seq[AstInfo], TypeHint, String) => (Seq[ImportEntry], Module, Def, FnTh, Equations)) {

  def invokePrototype(ctx: PassContext, caller: ParseNode, eqCaller: Equations, eqCallee: Equations,
                      retTh: TypeHint, fnTh: FnTh, args: ArraySeq[InferTask]): TypeHint = {

    val location = caller.location

    if (fnTh.args.length != args.length)
      throw TCE.ArgsCountMismatch(Seq(location), fnTh.args.length, args.length)

    val eqInfer = new CallInfer(ctx, findSelfDef, caller, eqCallee)


    if (retTh != AnyTh) {
      ctx.log(s"passret ${fnTh.ret} <= $retTh")

      ThUtil.isUnion(ctx.level, ctx.module, retTh) match {
        case Some(variants) =>
          val allMismatched = variants.forall { vth =>
            try {
              eqInfer.infer(Seq(location), fnTh.ret, vth)
              false
            } catch {
              case _: TCE.TypeMismatch => true
            }
          }
          if (allMismatched)
            throw TCE.TypeMismatch(Seq(location), retTh, fnTh.ret)
        case None =>
          eqInfer.infer(Seq(location), fnTh.ret, retTh)
      }

      ctx.log("callspec " + eqInfer.tInfer.specMap.mkString("{", " , ", "}"))
    }

    val inferedArgs =
      (fnTh.args zip args).zipWithIndex.map { case ((defArgTh, argTask), idx) =>
        val advice = ThUtil.spec(eqInfer.advice(defArgTh),
          HashMap()) /* sieve generics??? */

        ctx.log(s"inferarg $idx adviced $advice")
        val (argLocation, argTh) = argTask.infer(eqInfer.ctx, eqCaller, advice)

        ctx.log(s"passarg $idx $defArgTh <= $argTh")
        eqInfer.infer(Seq(argLocation), defArgTh, argTh)
        ctx.log("callspec " + eqInfer.tInfer.specMap.mkString("{", " , ", "}"))

        eqInfer.advice(defArgTh)
      }

    eqInfer.lift(eqCaller)

    val inferedRet = eqInfer.advice(fnTh.ret)
    caller.setCallSpecMap(eqInfer.tInfer.specMap)
    inferedRet
  }

  def invokeDef(ctx: PassContext, call: Call, eqCaller: Equations,
                retTh: TypeHint, toCall: Def, args: ArraySeq[InferTask]): TypeHint =
    invokePrototype(ctx, call, eqCaller, toCall.getEquations, retTh, toCall.getTypeHint, args)

  def invokeSelfDef(ctx: PassContext, eqCaller: Equations, call: ParseNode,
                    retTh: TypeHint, fnName: String, self: InferTask, args: ArraySeq[InferTask]): TypeHint = {

    val location = call.location
    val (selfLocation, selfTh) = self.infer(ctx, eqCaller, AnyTh)

    selfTh match {
      // FIXME: try to resolve generic function locally at first? <---
      // FIXME: variant 2 - declare self function on ScalarTh only
      case gth: GenericTh =>
        val argsTh = args.map(argTask => argTask.infer(ctx, eqCaller, AnyTh))
          .map { case (loc, th) => (Seq(loc), th) }

        val eqRet = if (retTh != AnyTh) retTh else eqCaller.nextAnonType()


        eqCaller.addEq(Equation((Seq(selfLocation), gth), fnName, argsTh, (Seq(location), eqRet)))
        call.setCallType(SelfCallPolymorphic(FnTh(gth +: argsTh.map(_._2), eqRet)))

        eqRet
      case _ =>
        val (ieSeq, mod, fn, fnTh, fnEq) = findSelfDef(ctx, Seq(location), selfTh, fnName)
        if (mod != null && mod.pkg != ctx.module.pkg) // null for builtin
          call.setCallType(SelfCallImport(mod, fn))
        else
          call.setCallType(SelfCallLocal(fn))

        invokePrototype(ctx, call, eqCaller, fnEq, retTh, fnTh, new InferTask {
          override def infer(ctx: PassContext, eq: Equations, th: TypeHint) = (selfLocation, selfTh)
        } +: args)
    }
  }
}