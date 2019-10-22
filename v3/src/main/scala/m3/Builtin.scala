package m3

import m3.Ast0._
import m3._02typecheck.{TCE, TCMeta}
import m3._01parse.ParseMeta._
import TCMeta.TypeDeclTCMetaImplicit

import scala.collection.immutable.ArraySeq
import scala.collection.mutable.Buffer

object Builtin {
  val thLong = ScalarTh(params = ArraySeq.empty, "Long", None, "prelude")
  val thInt = ScalarTh(params = ArraySeq.empty, "Int", None, "prelude")
  val thShort = ScalarTh(params = ArraySeq.empty, "Short", None, "prelude")
  val thByte = ScalarTh(params = ArraySeq.empty, "Byte", None, "prelude")

  val thDouble = ScalarTh(params = ArraySeq.empty, "Double", None, "prelude")
  val thFloat = ScalarTh(params = ArraySeq.empty, "Float", None, "prelude")
  val thBool = ScalarTh(params = ArraySeq.empty, "Bool", None, "prelude")
  val thString = ScalarTh(params = ArraySeq.empty, "String", None, "prelude")
  val thNil = ScalarTh(params = ArraySeq.empty, "None", None, "prelude")
  val thUnreachable = ScalarTh(params = ArraySeq.empty, "Unreachable", None, "prelude")
  val thArraySize = ScalarTh(params = ArraySeq.empty, "ArraySize", None, "prelude")
  val thArraySizes = UnionTh(ArraySeq(thByte, thShort, thInt, thLong))

  val builtInMod = Module("prelude", Import(ArraySeq.empty), ArraySeq.empty, Map.empty, Map.empty, Map.empty)


  val builtinTypeDecl: Map[String, TypeDecl] = (
    Seq("Unreachable", "None", "Bool", "ArraySize", "Byte", "Short", "Int", "Long", "Float", "Double").map { name =>
      ScalarDecl(ref = false, ArraySeq.empty, name, "builtin")
    } ++ Seq(
      ScalarDecl(ref = true, ArraySeq.empty, "String", "builtin")
    )).map(td => (td.name, td)).toMap

  def isArrayThName(name: String): Boolean =
    name == "Array" || name.matches("^Array[0-9]+$")

  def isDeclaredBuiltIn(name: String) =
    builtinTypeDecl.contains(name) || isArrayThName(name)

  def resolveBuiltinType(th: ScalarTh): TypeDecl =
    th.name match {
      case name if isArrayThName(name) =>
        val decl = StructDecl(ArraySeq(GenericTh("t")), name, ArraySeq.empty)
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
