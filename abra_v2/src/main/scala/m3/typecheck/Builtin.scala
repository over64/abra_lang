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
    if (name == "==") {
      FnTh(Seq.empty, Seq(selfTh, selfTh), thBool)
    } else if (name == "clone") {
      FnTh(Seq.empty, Seq(selfTh), selfTh)
    } else
      selfTh match {
        case sth: ScalarTh if isArrayThName(sth.name) =>
          name match {
            case "len" => FnTh(Seq.empty, Seq(sth), thLong) // ??? why not thArraySize
            case "get" => FnTh(Seq.empty, Seq(sth, thArraySize), sth.params.head)
            case "set" => FnTh(Seq.empty, Seq(sth, thArraySize, sth.params.head), thNil)
          }
        case th =>
          throw TCE.NoSuchSelfDef(location, name, selfTh)
      }
}
