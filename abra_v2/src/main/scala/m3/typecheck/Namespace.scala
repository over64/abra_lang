package m3.typecheck

import m3.codegen.{Ast2, IrUtil}
import m3.parse.Ast0._

import scala.collection.mutable
import Util._

/**
  * Created by over on 20.10.17.
  */
case class DefSpec(name: String, tparams: Seq[TypeHint])
trait InferTask {
  def infer(expected: Option[ThAdvice]): (TypeHint, String, Seq[Ast2.Stat])
}
class Namespace(val pkg: String,
                val lowCode: Seq[llVm],
                val defs: Seq[Def],
                val types: Seq[TypeDecl],
                val mods: Map[String, ModHeader]) {

  var nextAnonId = new (() => Int) {
    var idSeq = 0
    override def apply(): Int = {
      idSeq += 1
      idSeq
    }
  }

  val anonDefs = mutable.ListBuffer[Def]()

  val inferedDefs = mutable.HashMap[DefSpec, DefHeader]()
  val inferedSelfDefs = mutable.HashMap[DefSpec, DefHeader]()

  val lowMod = IrUtil.Mod()

  lowCode.foreach { l => lowMod.addLow(l.code) }

  def hasDef(name: String): Boolean = defs.exists(d => d.name == name)
  def hasSelfDef(name: String, selfType: TypeHint): Boolean = false
  def hasMod(name: String): Boolean = false

  def checkAndInfer(specMap: mutable.HashMap[GenericType, TypeHint], expected: TypeHint, has: TypeHint): Boolean =
    (expected, has) match {
      case (adv: ScalarTh, th: ScalarTh) =>
        if (adv.name.contains("*"))
          specMap.put(GenericType(adv.name.replace("*", "")), th) match {
            case Some(ScalarTh(_, name, pkg)) if name.contains("*") => true // ok replacement
            case _ => false
          }
        else {
          if (adv.name != th.name) return false
          if (adv.params.length != th.params.length) return false

          (adv.params zip th.params).forall {
            case (_expected, _has) => checkAndInfer(specMap, _expected, _has)
          }
        }
      case (adv: StructTh, th: StructTh) =>
        if (adv.seq.length != th.seq.length) return false

        (adv.seq zip th.seq).forall {
          case (_expected, _has) => checkAndInfer(specMap, _expected.typeHint, _has.typeHint)
        }
      case (adv: UnionTh, th: UnionTh) =>
        if (adv.seq.length != th.seq.length) return false

        (adv.seq zip th.seq).forall {
          case (_expected, _has) => checkAndInfer(specMap, _expected, _has)
        }
      case (adv: FnTh, th: FnTh) =>
        if (adv.args.length != th.args.length) return false
        (adv.args zip th.args).forall {
          case (_expected, _has) => checkAndInfer(specMap, _expected, _has)
        }
        checkAndInfer(specMap, adv.ret, th.ret)
      case (adv, th) => false
    }

  def _invokeDef(scope: BlockScope,
                 toCall: Def,
                 params: Seq[TypeHint],
                 args: Iterator[InferTask],
                 ret: Option[ThAdvice],
                 inferCallback: (FnAdvice, Def) => (DefHeader, Ast2.Def)): (TypeHint, String, Seq[Ast2.Stat]) = {

    val specMap: mutable.HashMap[GenericType, TypeHint] =
      if (params.nonEmpty) {
        if (toCall.params.length != params.length)
          throw new RuntimeException(s"expected ${toCall.params.length} params but has ${params.length}")
        mutable.HashMap(toCall.params zip params: _*)
      } else
        mutable.HashMap(toCall.params.map(p => (p, ScalarTh(Seq.empty, p.name + "*", None))): _*)

    val defArgs = toCall.lambda.args.toIterator
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

    val advice = FnAdvice(argsInferedTh.map(th => th.toAdviceOpt(specMap)), ret)
    val flatSpecs = toCall.params.map(cp => specMap(cp))
    val _def = toCall.spec(flatSpecs, this)
    val (header, lowDef) = inferCallback(advice, _def)

    // FIXME: inferedDefs.put()
    lowMod.defineDef(lowDef)

    val anonVar = "$c" + nextAnonId()
    scope.addLocal(mut = false, anonVar, header.th.ret)

    (
      header.th.ret,
      anonVar,
      argsStats :+ Ast2.Store(
        init = true,
        Ast2.Id(anonVar),
        Ast2.Call(
          Ast2.Id(lowDef.name),
          argsVars.map(av => Ast2.Id(av))))
    )
  }

  def invokeProto(scope: BlockScope,
                   vName: String,
                   proto: FnTh,
                   args: Iterator[InferTask]) = {

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


    val anonVar = "$c" + nextAnonId()
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

  def inferCompatibleDef(toSpec: Def,
                         advice: FnAdvice,
                         inferCallback: (FnAdvice, Def) => (DefHeader, Ast2.Def)): DefHeader = {
    val specMap = mutable.HashMap[GenericType, TypeHint]()
    val advice = FnAdvice(
      toSpec.lambda.args.map(arg => arg.typeHint.flatMap(x => x.toAdviceOpt(specMap))),
      toSpec.retTh.flatMap(x => x.toAdviceOpt(specMap)))
    inferCallback(advice, toSpec)._1
  }

  def invokeDef(scope: BlockScope,
                name: String,
                params: Seq[TypeHint],
                args: Iterator[InferTask],
                ret: Option[ThAdvice],
                inferCallback: (FnAdvice, Def) => (DefHeader, Ast2.Def)): (TypeHint, String, Seq[Ast2.Stat]) = {
    val toCall = defs.find(d => d.name == name).get
    _invokeDef(scope, toCall, params, args, ret, inferCallback)
  }

  def invokeSelfDef(scope: BlockScope,
                    name: String,
                    params: Seq[TypeHint],
                    selfType: TypeHint,
                    args: Iterator[InferTask],
                    ret: Option[ThAdvice],
                    inferCallback: (FnAdvice, Def) => (DefHeader, Ast2.Def)): (TypeHint, String, Seq[Ast2.Stat]) = {
    val toCall = defs.find { d =>
      if (d.name != name) false
      else
        d.lambda.args.headOption match {
          case None => false
          case Some(arg) => arg.name == "self" && arg.typeHint.get == selfType
        }
    }.getOrElse(throw new RuntimeException(s"no function with name $name"))
    _invokeDef(scope, toCall, params, args, ret, inferCallback)
  }

  // type S[T, U] = (t1: T, t2: T, u: U)
  // S[String, Long]
  def invokeConstructor(scope: BlockScope,
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

    val flatSpecs = consType.params.map(cp => specMap(cp))
    val retTh = ScalarTh(flatSpecs, consType.name, None)

    // code generation will be performed on codegen part
    val virtualArgs = consType.fields.map(f => Arg(f.name, Some(f.th)))
    val virtualDef = Def(consType.params, consType.name, Lambda(virtualArgs, AbraCode(Seq.empty)), Some(retTh))
    val _def = virtualDef.spec(flatSpecs, this)

    val anonVar = "$c" + nextAnonId()
    scope.addLocal(mut = false, anonVar, retTh)

    (
      retTh,
      anonVar,
      argsStats :+ Ast2.Store(
        init = true,
        Ast2.Id(anonVar),
        Ast2.Call(
          Ast2.Id(_def.name + ".$cons"),
          argsVars.map(av => Ast2.Id(av))))
    )
  }

  def invokeAnonConstructor(scope: BlockScope,
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

    val anonVar = "$c" + nextAnonId()
    scope.addLocal(mut = false, anonVar, forType)

    (
      forType,
      anonVar,
      argsStats :+ Ast2.Store(
        init = true,
        Ast2.Id(anonVar),
        Ast2.Call(
          Ast2.Id(forType.toLow(this).name + ".$cons"),
          argsVars.map(av => Ast2.Id(av))))
    )
  }

  def invokeLambda(scope: BlockScope,
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

    val anonVar = "$c" + nextAnonId()
    scope.addLocal(mut = false, anonVar, th.ret)

    (
      th.ret,
      anonVar,
      argsStats :+ Ast2.Store(
        init = true,
        Ast2.Id(anonVar),
        Ast2.Call(
          Ast2.Id(name),
          argsVars.map(av => Ast2.Id(av))))
    )
  }

  def invokeMod(name: String,
                params: Seq[TypeHint],
                args: Iterator[InferTask],
                ret: Option[TypeHint],
                inferCallback: (Def) => (DefHeader, Ast2.Def)): (TypeHint, String, Seq[Ast2.Stat]) = {
    null
  }

  def dumpHeader: ModHeader = {
    null
  }
}