package m3.typecheck

import m3.codegen.Ast2
import m3.parse.Ast0.{TypeHint, _}
import m3.typecheck.Util._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by over on 20.10.17.
  */
case class DefSpec(params: Seq[TypeHint], th: FnTh, lowName: String)

case class DefCont(fn: Def, specs: mutable.ListBuffer[DefSpec])

trait InferTask {
  def infer(expected: Option[ThAdvice]): (TypeHint, String, Seq[Ast2.Stat])
}

class Namespace(val pkg: String,
                val imports: Map[String, Namespace] = Map(),
                val typeImports: Map[String, String] = Map(),
                val lowCode: Seq[llVm] = Seq(),
                val selfDefs: Map[String, Seq[DefCont]] = Map(),
                val defs: Map[String, DefCont] = Map(),
                val types: Seq[TypeDecl] = Seq()) {

  def findType(name: String): TypeDecl = {
    types.find(td => td.name == name) match {
      case Some(td) => td
      case None =>
        val inMod = typeImports.getOrElse(name, throw new RuntimeException(s"no such type with name $name"))
        imports(inMod).findType(name)
    }
  }

  def hasDef(name: String): Boolean = defs.contains(name)

  def checkAndInferSeq(specMap: mutable.HashMap[GenericType, TypeHint], expected: Seq[TypeHint], has: Seq[TypeHint]): Boolean = {
    if (expected.length != has.length) return false
    (expected zip has).forall { case (e, h) => checkAndInfer(specMap, e, h) }
  }

  def checkAndInfer(specMap: mutable.HashMap[GenericType, TypeHint], expected: TypeHint, has: TypeHint): Boolean =
    (expected, has) match {
      case (adv: ScalarTh, th: ScalarTh) =>
        if (adv.name.contains("*"))
          specMap.put(GenericType(adv.name.replace("*", "")), th) match {
            case Some(ScalarTh(_, name, pkg)) if name.contains("*") => true // ok replacement
            case _ => false
          }
        else if (adv.name != th.name) {
          types.find(t => t.name == adv.name).getOrElse(throw new RuntimeException(s"no such type ${adv}")) match {
            case ud: UnionDecl =>
              if (ud.params.length != adv.params.length) throw new RuntimeException(s"expected ${ud.params.length} for $ud has ${adv.params.length}")

              ud.variants.exists { udVariant =>
                val (variantSpec, isSpec) = ud.params.zipWithIndex
                  .find { case (g, idx) => g.name == udVariant.name }
                  .map { case (g, idx) => (adv.params(idx), true) }
                  .getOrElse((udVariant, false))

                checkAndInfer(specMap, variantSpec, th)
              }
            case _ => false
          }
        } else checkAndInferSeq(specMap, adv.params, th.params)
      case (adv: ScalarTh, uth: UnionTh) =>
        types.find(t => t.name == adv.name).getOrElse(throw new RuntimeException(s"no such type ${adv}")) match {
          case ud: UnionDecl =>
            if (ud.params.length != adv.params.length) throw new RuntimeException(s"expected ${ud.params.length} for $ud has ${adv.params.length}")

            uth.seq.forall { variant =>
              ud.variants.exists { udVariant =>
                checkAndInfer(specMap, udVariant, variant)
              }
            }
          case _ => false
        }

      case (adv: UnionTh, th: ScalarTh) =>
        adv.seq.exists { advVariant => checkAndInfer(specMap, advVariant, th) }
      case (adv: UnionTh, th: UnionTh) =>
        th.seq.forall { variant => checkAndInfer(specMap, adv, variant) }
      case (adv: StructTh, th: StructTh) =>
        checkAndInferSeq(specMap, adv.seq.map(_.typeHint), th.seq.map(_.typeHint))
      case (adv: FnTh, th: FnTh) =>
        checkAndInferSeq(specMap, adv.args, th.args)
        checkAndInfer(specMap, adv.ret, th.ret)
      case (adv, th) => false
    }

  def _invokeDef(ctx: TContext,
                 scope: BlockScope,
                 cont: DefCont,
                 params: Seq[TypeHint],
                 args: Iterator[InferTask],
                 ret: Option[ThAdvice],
                 inferCallback: (DefCont, Seq[TypeHint]) => (DefHeader, Ast2.Def)): (TypeHint, String, Seq[Ast2.Stat]) = {

    val toCall = cont.fn
    val specMap: mutable.HashMap[GenericType, TypeHint] =
      if (params.nonEmpty) {
        if (toCall.params.length != params.length)
          throw new RuntimeException(s"expected ${toCall.params.length} params but has ${params.length}")
        mutable.HashMap(toCall.params zip params: _*)
      } else
        mutable.HashMap(toCall.params.map(p => (p, ScalarTh(Seq.empty, p.name + "*", None))): _*)

    val defArgs = toCall.lambda.args.toIterator
    val argsStats = mutable.ListBuffer[Ast2.Stat]()
    val argsVars = mutable.ListBuffer[(String, TypeHint)]()

    while (defArgs.hasNext) {
      val defArg = defArgs.next()
      val arg = if (!args.hasNext) throw new RuntimeException("too least args") else args.next()

      val argTh = defArg.typeHint.get.spec(specMap.toMap)
      val argAdvice = defArg.typeHint.get.toAdviceOpt(specMap)
      val (th, vName, stats) = arg.infer(argAdvice)

      if (!checkAndInfer(specMap, argTh, th))
        throw new RuntimeException(s"expected ${defArg.typeHint} has $th")

      argsVars += ((vName, th))
      argsStats ++= stats
    }

    if (args.hasNext) throw new RuntimeException("too much args")

    specMap.values.foreach {
      case ScalarTh(_, name, _) =>
        if (name.contains("*"))
          throw new RuntimeException(s"explicit type parameter for call ${toCall.name} expected. Like ${toCall.name}[${name.replace("*", "")}]")
      case _ =>
    }

    val bridgedArgs =
      (toCall.lambda.args zip argsVars).map {
        case (defArg, (argVarName, argVarTh)) =>
          val argTh = defArg.typeHint.get.spec(specMap.toMap)
          if (argTh != argVarTh && (!argTh.isInstanceOf[FnTh] && !argVarTh.isInstanceOf[FnTh])) {
            val vName = "_bridge" + ctx.nextAnonId()
            scope.addLocal(mut = false, vName, argTh)
            argsStats += Ast2.Store(init = true, Ast2.Id(vName), Ast2.Id(argVarName))
            vName
          } else argVarName
      }

    val flatSpecs = toCall.params.map(cp => specMap(cp))
    val defSpec =
      cont.specs.find(ds => ds.params == flatSpecs) match {
        case Some(spec) => spec
        case None =>
          val (header, lowDef) = inferCallback(cont, flatSpecs)
          cont.specs.find(ds => ds.params == flatSpecs).get
      }

    val anonVar = "$c" + ctx.nextAnonId()
    scope.addLocal(mut = false, anonVar, defSpec.th.ret)

    (
      defSpec.th.ret,
      anonVar,
      argsStats :+ Ast2.Store(
        init = true,
        Ast2.Id(anonVar),
        Ast2.Call(
          Ast2.Id(defSpec.lowName),
          bridgedArgs.map(av => Ast2.Id(av))))
    )
  }

  def invokeProto(ctx: TContext,
                  scope: BlockScope,
                  vName: String,
                  proto: FnTh,
                  args: Iterator[InferTask]): (TypeHint, String, ListBuffer[Ast2.Stat]) = {

    val specMap = mutable.HashMap[GenericType, TypeHint]()
    val defArgs = proto.args.toIterator
    val argsStats = mutable.ListBuffer[Ast2.Stat]()
    val argsVars = mutable.ListBuffer[String]()

    while (defArgs.hasNext) {
      val defArg = defArgs.next()
      val arg = if (!args.hasNext) throw new RuntimeException("too least args") else args.next()

      val argTh = defArg.spec(specMap.toMap)
      val argAdvice = defArg.toAdviceOpt(specMap)
      val (th, vName, stats) = arg.infer(argAdvice)

      argsVars += vName
      argsStats ++= stats

      if (!checkAndInfer(specMap, argTh, th))
        throw new RuntimeException(s"expected ${defArg} has $th")
    }

    if (args.hasNext) throw new RuntimeException("too much args")


    val anonVar = "$c" + ctx.nextAnonId()
    scope.addLocal(mut = false, anonVar, proto.ret)

    (
      proto.ret,
      anonVar,
      argsStats :+ Ast2.Store(
        init = true,
        Ast2.Id(anonVar),
        Ast2.Call(
          Ast2.Id(vName),
          argsVars.map(av => Ast2.Id(av))))
    )
  }

  def invokeDef(ctx: TContext,
                scope: BlockScope,
                name: String,
                params: Seq[TypeHint],
                args: Iterator[InferTask],
                ret: Option[ThAdvice],
                inferCallback: (DefCont, Seq[TypeHint]) => (DefHeader, Ast2.Def)): (TypeHint, String, Seq[Ast2.Stat]) = {
    val cont = defs.getOrElse(name, throw new RuntimeException(s"no such function with name $name"))
    _invokeDef(ctx, scope, cont, params, args, ret, inferCallback)
  }

  def invokeSelfDef(ctx: TContext,
                    scope: BlockScope,
                    name: String,
                    params: Seq[TypeHint],
                    selfType: TypeHint,
                    args: Iterator[InferTask],
                    ret: Option[ThAdvice],
                    inferCallback: (DefCont, Seq[TypeHint]) => (DefHeader, Ast2.Def)): (TypeHint, String, Seq[Ast2.Stat]) = {
    selfDefs.get(name) match {
      case Some(bucket) =>
        val cont = bucket.find { _cont =>
          val th = _cont.fn.lambda.args(0).typeHint.get
          (th, selfType) match {
            case (sth1: ScalarTh, sth2: ScalarTh) => sth1.name == sth2.name
            case (th1, th2) => th1 == th2
          }
        }.getOrElse(throw new RuntimeException(s"no function with name $name for type $selfType"))
    }

    _invokeDef(ctx, scope, cont, params, args, ret, inferCallback)
  }

  def invokeConstructor(ctx: TContext,
                        scope: BlockScope,
                        typeName: String,
                        params: Seq[TypeHint],
                        args: Iterator[InferTask]): (TypeHint, String, Seq[Ast2.Stat]) = {
    val consType =
      types.find(t => t.name == typeName).getOrElse(throw new RuntimeException(s"has no type with name $typeName")) match {
        case sd: StructDecl => sd
        case _ => throw new RuntimeException(s"$typeName is not struct type")
      }
    val specMap: mutable.HashMap[GenericType, TypeHint] =
      if (params.nonEmpty) {
        if (consType.params.length != params.length)
          throw new RuntimeException(s"expected ${consType.params.length} params but has ${params.length}")
        mutable.HashMap(consType.params zip params: _*)
      } else
        mutable.HashMap(consType.params.map(p => (p, ScalarTh(Seq.empty, p.name + "*", None))): _*)

    val defArgs = consType.fields.map(f => Arg(f.name, Some(f.th))).toIterator
    val argsStats = mutable.ListBuffer[Ast2.Stat]()
    val argsVars = mutable.ListBuffer[(String, TypeHint)]()

    while (defArgs.hasNext) {
      val defArg = defArgs.next()
      val arg = if (!args.hasNext) throw new RuntimeException("too least args") else args.next()

      val argTh = defArg.typeHint.get.spec(specMap.toMap)
      val argAdvice = defArg.typeHint.get.toAdviceOpt(specMap)
      val (th, vName, stats) = arg.infer(argAdvice)

      argsVars += ((vName, th))
      argsStats ++= stats

      if (!checkAndInfer(specMap, argTh, th))
        throw new RuntimeException(s"expected ${defArg.typeHint} has $th")
    }

    if (args.hasNext) throw new RuntimeException("too much args")

    val bridgedArgs =
      (consType.fields.map(f => Arg(f.name, Some(f.th))) zip argsVars).map {
        case (defArg, (argVarName, argVarTh)) =>
          val argTh = defArg.typeHint.get.spec(specMap.toMap)
          if (argTh != argVarTh && (!argTh.isInstanceOf[FnTh] && !argVarTh.isInstanceOf[FnTh])) {
            val vName = "_bridge" + ctx.nextAnonId()
            scope.addLocal(mut = false, vName, argTh)
            argsStats += Ast2.Store(init = true, Ast2.Id(vName), Ast2.Id(argVarName))
            vName
          } else argVarName
      }


    val flatSpecs = consType.params.map(cp => specMap(cp))
    val retTh = ScalarTh(flatSpecs, consType.name, None)

    // code generation will be performed on codegen part
    val virtualArgs = consType.fields.map(f => Arg(f.name, Some(f.th)))
    val virtualDef = Def(consType.params, consType.name, Lambda(virtualArgs, AbraCode(Seq.empty)), Some(retTh))
    val _def = virtualDef.spec(flatSpecs, ctx, this)

    val anonVar = "$c" + ctx.nextAnonId()
    scope.addLocal(mut = false, anonVar, retTh)

    (
      retTh,
      anonVar,
      argsStats :+ Ast2.Store(
        init = true,
        Ast2.Id(anonVar),
        Ast2.Call(
          Ast2.Id(_def.name + ".$cons"),
          bridgedArgs.map(av => Ast2.Id(av))))
    )
  }

  def invokeAnonConstructor(ctx: TContext,
                            scope: BlockScope,
                            forType: StructTh,
                            args: Iterator[InferTask]): (TypeHint, String, Seq[Ast2.Stat]) = {
    val specMap = mutable.HashMap[GenericType, TypeHint]()

    val defArgs = forType.seq.map(f => Arg(f.name, Some(f.typeHint))).toIterator
    val argsStats = mutable.ListBuffer[Ast2.Stat]()
    val argsVars = mutable.ListBuffer[String]()
    val argsInferedTh = mutable.ListBuffer[TypeHint]()

    while (defArgs.hasNext) {
      val defArg = defArgs.next()
      val arg = if (!args.hasNext) throw new RuntimeException("too least args") else args.next()

      val argTh = defArg.typeHint.get.spec(specMap.toMap)
      val argAdvice = defArg.typeHint.get.toAdviceOpt(specMap)
      val (th, vName, stats) = arg.infer(argAdvice)

      argsVars += vName
      argsStats ++= stats

      if (!checkAndInfer(specMap, argTh, th))
        throw new RuntimeException(s"expected ${defArg.typeHint} has $th")

      argsInferedTh += th
    }

    if (args.hasNext) throw new RuntimeException("too much args")

    // code generation will be performed on codegen part

    val anonVar = "$c" + ctx.nextAnonId()
    scope.addLocal(mut = false, anonVar, forType)

    (
      forType,
      anonVar,
      argsStats :+ Ast2.Store(
        init = true,
        Ast2.Id(anonVar),
        Ast2.Call(
          Ast2.Id(forType.toLow(ctx, this).name + ".$cons"),
          argsVars.map(av => Ast2.Id(av))))
    )
  }

  def invokeLambda(ctx: TContext,
                   scope: BlockScope,
                   name: String,
                   th: FnTh,
                   args: Iterator[InferTask]) = {

    val specMap = mutable.HashMap[GenericType, TypeHint]()

    val defArgs = th.args.toIterator
    val argsStats = mutable.ListBuffer[Ast2.Stat]()
    val argsVars = mutable.ListBuffer[String]()
    val argsInferedTh = mutable.ListBuffer[TypeHint]()

    while (defArgs.hasNext) {
      val defArg = defArgs.next()
      val arg = if (!args.hasNext) throw new RuntimeException("too least args") else args.next()

      val argTh = defArg.spec(specMap.toMap)
      val argAdvice = defArg.toAdviceOpt(specMap)
      val (th, vName, stats) = arg.infer(argAdvice)

      argsVars += vName
      argsStats ++= stats

      if (!checkAndInfer(specMap, argTh, th))
        throw new RuntimeException(s"expected ${defArg} has $th")

      argsInferedTh += th
    }

    if (args.hasNext) throw new RuntimeException("too much args")

    val anonVar = "$c" + ctx.nextAnonId()
    scope.addLocal(mut = false, anonVar, th.ret)

    (
      th.ret,
      anonVar,
      argsStats :+ Ast2.Store(
        init = true,
        Ast2.Id(anonVar),
        Ast2.Call(
          Ast2.Id(name),
          argsVars.map(av => Ast2.Id(av)))))
  }

  def invokeMod(ctx: TContext,
                scope: BlockScope,
                modName: String,
                fnName: String,
                params: Seq[TypeHint],
                args: Iterator[InferTask],
                ret: Option[ThAdvice]): (TypeHint, String, Seq[Ast2.Stat]) = {
    val ns = imports(modName)
    if (!ns.hasDef(fnName))
      throw new RuntimeException(s"no such function $fnName in module $modName")

    ns.invokeDef(ctx, scope, fnName, params, args, ret, {
      case (cont, specs) => TypeChecker.evalDef(ctx, ns, scope.mkChild(p => new FnScope(None)), cont, specs)
    })
  }
}