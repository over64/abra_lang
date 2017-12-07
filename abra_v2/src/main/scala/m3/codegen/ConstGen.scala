package m3.codegen

import m3.codegen.Ast2.Fn

object ConstGen {

  val fnBool = Fn("\\ -> Bool", Seq.empty, Seq.empty, Ast2.TypeRef("Bool"))
  val fnInt = Fn("\\ -> Int", Seq.empty, Seq.empty, Ast2.TypeRef("Int"))
  val fnString = Fn("\\ -> String", Seq.empty, Seq.empty, Ast2.TypeRef("String"))
  val fnFloat = Fn("\\ -> Float", Seq.empty, Seq.empty, Ast2.TypeRef("Float"))
  val types = Seq(fnBool, fnInt, fnString, fnFloat)

  def genBoolConst(id: String, value: String): Ast2.Def = {
    val v = if (value == "true") 1 else 0
    Ast2.Def(id, Ast2.TypeRef("\\ -> Bool"), Seq.empty, Seq.empty, Ast2.LLCode(
      s"ret i8 $v"
    ), isAnon = true)
  }
  def genIntConst(id: String, value: String): Ast2.Def =
    Ast2.Def(id, Ast2.TypeRef("\\ -> Int"), Seq.empty, Seq.empty, Ast2.LLCode(
      s"ret i32 $value"
    ), isAnon = true)

  def genFloatConst(id: String, value: String): Ast2.Def = {
    val d = (new java.lang.Float(value)).toDouble
    Ast2.Def(id, Ast2.TypeRef("\\ -> Float"), Seq.empty, Seq.empty, Ast2.LLCode(
      s"ret float ${"0x" + java.lang.Long.toHexString(java.lang.Double.doubleToLongBits(d))}"
    ), isAnon = true)
  }

  def genStringConst(id: String, value: String): (Ast2.Def, String) = {
    val name = id + "$const"
    val encoded = HexUtil.singleByteNullTerminated(value.getBytes())
    val len = encoded.bytesLen

    val lowCode = s"""@$name = private unnamed_addr constant [${encoded.bytesLen} x i8] c"${encoded.str}""""
    val lowDef = Ast2.Def(id, Ast2.TypeRef("\\ -> String"), Seq.empty, Seq.empty, Ast2.LLCode(
      s"""
         |%1 = call i8* @rcAlloc(i64 6)
         |call void @llvm.memcpy.p0i8.p0i8.i64(i8* %1, i8* getelementptr inbounds ([$len x i8], [$len x i8]* @$name, i32 0, i32 0), i64 6, i32 1, i1 false)
         |call void @rcInc(i8* %1)
         |ret i8* %1
            """.stripMargin
    ), isAnon = true)

    (lowDef, lowCode)
  }
}
