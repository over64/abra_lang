package m3.codegen

import java.util.Base64

import m3.codegen.Ast2.{Fn, Id}
import m3.codegen.IrUtil.Mod

object ConstGen {

  val fnBool = Fn("\\ -> Bool", Seq.empty, Seq.empty, Ast2.TypeRef("Bool"))
  val fnInt = Fn("\\ -> Int", Seq.empty, Seq.empty, Ast2.TypeRef("Int"))
  val fnString = Fn("\\ -> String", Seq.empty, Seq.empty, Ast2.TypeRef("String"))
  val fnFloat = Fn("\\ -> Float", Seq.empty, Seq.empty, Ast2.TypeRef("Float"))
  val types = Seq(fnBool, fnInt, fnString, fnFloat)

  def bool(mod: Mod, value: String): Ast2.Id = {
    val v = if (value == "true") 1 else 0
    val id = "$bool." + value

    mod.types.put(fnBool.name, fnBool)
    mod.defs.put(id, Ast2.Def(id, Ast2.TypeRef("\\ -> Bool"), Seq.empty, Seq.empty, Ast2.LLCode(
      s"ret i8 $v"
    ), isAnon = true))

    Id(id)
  }
  def int(mod: Mod, value: String): Ast2.Id = {
    val id = "$int." + value
    mod.types.put(fnInt.name, fnInt)
    mod.defs.put(id,
      Ast2.Def(id, Ast2.TypeRef("\\ -> Int"), Seq.empty, Seq.empty, Ast2.LLCode(
        s"ret i32 $value"
      ), isAnon = true))
    Id(id)
  }

  def float(mod: Mod, value: String): Ast2.Id = {
    val id = "$float." + value
    mod.types.put(fnFloat.name, fnFloat)

    val d = (new java.lang.Float(value)).toDouble
    mod.defs.put(id, Ast2.Def(id, Ast2.TypeRef("\\ -> Float"), Seq.empty, Seq.empty, Ast2.LLCode(
      s"ret float ${"0x" + java.lang.Long.toHexString(java.lang.Double.doubleToLongBits(d))}"
    ), isAnon = true))

    Id(id)
  }

  def string(mod: Mod, value: String): Ast2.Id = {
    val hash = new String(Base64.getEncoder.encode(value.getBytes())).replace("=", "$")
    val id = "$string." + hash
    val name = "$string.const." + hash

    val encoded = HexUtil.singleByteNullTerminated(value.getBytes())
    val len = encoded.bytesLen

    val lowCode = s"""@$name = private unnamed_addr constant [${encoded.bytesLen} x i8] c"${encoded.str}""""
    val lowDef = Ast2.Def(id, Ast2.TypeRef("\\ -> String"), Seq.empty, Seq.empty, Ast2.LLCode(
      s"""
         |%1 = load i8* (i64)*, i8* (i64)** @rcAlloc
         |%2 = call i8* %1(i64 $len)
         |call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* getelementptr inbounds ([$len x i8], [$len x i8]* @$name, i32 0, i32 0), i64 $len, i32 1, i1 false)
         |ret i8* %2
            """.stripMargin
    ), isAnon = true)

    mod.types.put(fnString.name, fnString)
    mod.defs.put(lowDef.name, lowDef)
    mod.addLow(lowCode)

    Id(lowDef.name)
  }
}
