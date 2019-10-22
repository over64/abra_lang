package m3

import m3.Ast0._
import m3.typecheck.{TCE, TCMeta}
import m3.parse.ParseMeta._
import TCMeta.TypeDeclTCMetaImplicit

object Builtin {
  val thLong = ScalarTh(params = Seq.empty, "Long", None, "prelude")
  val thInt = ScalarTh(params = Seq.empty, "Int", None, "prelude")
  val thShort = ScalarTh(params = Seq.empty, "Short", None, "prelude")
  val thByte = ScalarTh(params = Seq.empty, "Byte", None, "prelude")

  val thDouble = ScalarTh(params = Seq.empty, "Double", None, "prelude")
  val thFloat = ScalarTh(params = Seq.empty, "Float", None, "prelude")
  val thBool = ScalarTh(params = Seq.empty, "Bool", None, "prelude")
  val thString = ScalarTh(params = Seq.empty, "String", None, "prelude")
  val thNil = ScalarTh(params = Seq.empty, "None", None, "prelude")
  val thUnreachable = ScalarTh(params = Seq.empty, "Unreachable", None, "prelude")
  val thArraySize = ScalarTh(params = Seq.empty, "ArraySize", None, "prelude")
  val thArraySizes = UnionTh(Seq(thByte, thShort, thInt, thLong))

  val builtInMod = Module("prelude", Import(Seq.empty), Seq.empty, Map.empty, Map.empty, Map.empty)


  val builtinTypeDecl: Map[String, TypeDecl] = (
    Seq("Unreachable", "None", "Bool", "ArraySize", "Byte", "Short", "Int", "Long", "Float", "Double").map { name =>
      ScalarDecl(ref = false, Seq.empty, name, "builtin")
    } ++ Seq(
      ScalarDecl(ref = true, Seq.empty, "String", "builtin")
    )).map(td => (td.name, td)).toMap

  def isArrayThName(name: String): Boolean =
    name == "Array" || name.matches("^Array[0-9]+$")

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
        decl.setBuiltinArray(len) // FIXME: don't do this
        decl
      case name =>
        builtinTypeDecl.getOrElse(name, {
          var x = 1
          throw TCE.NoSuchType(th.location, th.ie.toSeq, th.name)
        })
    }
}
