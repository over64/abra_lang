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

  def hasDef(name: String): Boolean = defs.exists(d => d.name == name)
  def hasSelfDef(name: String, selfType: TypeHint): Boolean = false
  def hasMod(name: String): Boolean = false

  // go left to right ->
  // def map[U] = \self: Seq[Int], mapper: \Int -> U -> // declaration for specialized generic type disallowed
  // def map[T, U] = \self: Seq[T], mapper: \T -> U ->
  //   .
  // s.map(\x -> x.toString)
  // def bar[T] = \t: T ->
  //   (t, 1) .(x: T, y: T)
  // val z = bar(1.toLong)
  // val l = Long(1231231231424)
  // val l: Long = 123123123123
  // val pi: Double = 3.14
  // val pi = Double(3.14)
  // type Long = llvm i64 .
  // macro Long = \ctx: Context, args: Seq[Expr] ->
  //   if args.len != 1 do raise MacroErr('expected 1 arg of IConst') .
  //   match args(0)
  //     case IConst(v) do
  //     else e do raise MacroErr('expected IConst') ..

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
    val _def = toCall.spec(specMap.values.toSeq, this)
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
    }.get
    _invokeDef(scope, toCall, params, args, ret, inferCallback)
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