package m3.typecheck

import m3.parse.Ast0._
import m3.parse.ParseMeta._
import m3.typecheck.TCMeta._
import m3.parse.AstInfo

object Builtin {
  val thLong = ScalarTh(params = Seq.empty, "Long", Seq.empty)
  val thInt = ScalarTh(params = Seq.empty, "Int", Seq.empty)
  val thShort = ScalarTh(params = Seq.empty, "Short", Seq.empty)
  val thByte = ScalarTh(params = Seq.empty, "Byte", Seq.empty)

  val thDouble = ScalarTh(params = Seq.empty, "Double", Seq.empty)
  val thFloat = ScalarTh(params = Seq.empty, "Float", Seq.empty)
  val thBool = ScalarTh(params = Seq.empty, "Bool", Seq.empty)
  val thString = ScalarTh(params = Seq.empty, "String", Seq.empty)
  val thNil = ScalarTh(params = Seq.empty, "None", Seq.empty)
  val thUnreachable = ScalarTh(params = Seq.empty, "Unreachable", Seq.empty)
  val thArraySize = ScalarTh(params = Seq.empty, "ArraySize", Seq.empty)
  val thArraySizes = UnionTh(Seq(thByte, thShort, thInt, thLong))

  val builtinTypeDecl: Map[String, TypeDecl] = (
    Seq("Unreachable", "None", "Bool", "ArraySize", "Byte", "Short", "Int", "Long", "Float", "Double").map { name =>
      ScalarDecl(ref = false, Seq.empty, name, "builtin")
    } ++ Seq(
      ScalarDecl(ref = true, Seq.empty, "String", "builtin")
    )).map(td => (td.name, td)).toMap

  def binaryOps(th: TypeHint) = Seq("+", "-", "*", "/")
    .map(op => (op, FnTh(Seq.empty, Seq(th, th), th)))

  def compareOps(th: TypeHint) = Seq(
    ("<", FnTh(Seq.empty, Seq(th, th), thBool)),
    (">", FnTh(Seq.empty, Seq(th, th), thBool)))

  def equalityOps(th: TypeHint) = Seq(
    ("==", FnTh(Seq.empty, Seq(th, th), thBool)),
    ("!=", FnTh(Seq.empty, Seq(th, th), thBool)))

  def upscaleOps(th: ScalarTh, order: Seq[ScalarTh]) = {
    val idx = order.indexOf(th)
    order.drop(idx + 1).map { to => ("to" + to.name, FnTh(Seq.empty, Seq(th), to)) }
  }

  def upscaleIntOps(th: ScalarTh) =
    upscaleOps(th, Seq(thByte, thShort, thInt, thLong))

  def upscaleFloatOps(th: ScalarTh) =
    upscaleOps(th, Seq(thFloat, thDouble))

  def ops[T <: TypeHint](th: T, gens: (T => Seq[(String, FnTh)])*) =
    (th, gens.flatMap { gen => gen(th) }.toMap)

  val builtinSelfDefs: Map[TypeHint, Map[String, FnTh]] = Seq(
    ops(thBool, equalityOps),
    ops(thByte, binaryOps, compareOps, equalityOps, upscaleIntOps),
    ops(thShort, binaryOps, compareOps, equalityOps, upscaleIntOps),
    ops(thInt, binaryOps, compareOps, equalityOps, upscaleIntOps),
    ops(thLong, binaryOps, compareOps, equalityOps, upscaleIntOps),
    ops(thFloat, binaryOps, compareOps, equalityOps, upscaleFloatOps),
    ops(thDouble, binaryOps, compareOps, equalityOps, upscaleFloatOps)
  ).toMap

  def isArrayThName(name: String): Boolean =
    name.matches("^Array[0-9]*$")

  def isDeclaredBuiltIn(name: String) =
    builtinTypeDecl.contains(name) || isArrayThName(name)

  def resolveBuiltinType(th: ScalarTh): TypeDecl =
    th.name match {
      case name if isArrayThName(name) =>
        val decl = StructDecl(Seq(GenericTh("t")), name, Seq.empty)
        val len = name.replace("Array", "") match {
          case "" => None
          case sLen => Some(sLen.toLong)
        }
        decl.setBuiltinArray(len)
        decl
      case name =>
        builtinTypeDecl.getOrElse(name, throw TCE.NoSuchType(th.location, th.mod, th.name))
    }

  def resolveBuiltinSelfDef(location: Seq[AstInfo], selfTh: TypeHint, name: String): FnTh =
    selfTh match {
      case sth: ScalarTh if isArrayThName(sth.name) =>
        name match {
          case "len" => FnTh(Seq.empty, Seq(sth), thLong)
          case "get" => FnTh(Seq.empty, Seq(sth, thArraySize), sth.params.head)
          case "set" => FnTh(Seq.empty, Seq(sth, thArraySize, sth.params.head), thNil)
        }
      case th =>
        builtinSelfDefs.getOrElse(th, throw TCE.NoSuchSelfDef(location, name, selfTh))
          .getOrElse(name, throw TCE.NoSuchSelfDef(location, name, selfTh))
    }
}
