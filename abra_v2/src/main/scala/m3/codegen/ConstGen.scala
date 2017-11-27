package m3.codegen

object ConstGen {

  def genBoolConst(id: String, value: String): Ast2.Def = {
    val v = if (value == "true") 1 else 0
    Ast2.Def(id, Seq.empty, Seq.empty, Ast2.ScalarRef("Bool"), Ast2.LLCode(
      s"ret i8 $v"
    ), isAnon = true)
  }
  def genIntConst(id: String, value: String): Ast2.Def =
    Ast2.Def(id, Seq.empty, Seq.empty, Ast2.ScalarRef("Int"), Ast2.LLCode(
      s"ret i32 $value"
    ), isAnon = true)

  def genFloatConst(id: String, value: String): Ast2.Def = {
    val d = (new java.lang.Float(value)).toDouble
    Ast2.Def(id, Seq.empty, Seq.empty, Ast2.ScalarRef("Float"), Ast2.LLCode(
      s"ret float ${"0x" + java.lang.Long.toHexString(java.lang.Double.doubleToLongBits(d))}"
    ), isAnon = true)
  }

  def genStringConst(id: String, value: String): (Ast2.Def, String) = {
    val name = id + "$const"
    val encoded = HexUtil.singleByteNullTerminated(value.getBytes())
    val len = encoded.bytesLen

    val lowCode = s"""@$name = private unnamed_addr constant [${encoded.bytesLen} x i8] c"${encoded.str}""""
    val lowDef = Ast2.Def(id, Seq.empty, Seq.empty, Ast2.ScalarRef("String"), Ast2.LLCode(
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
