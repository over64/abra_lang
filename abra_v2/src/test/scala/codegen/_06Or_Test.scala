package codegen

import m3.codegen.Ast2._
import m3.codegen.IrUtil.Mod
import org.scalatest.FunSuite
import _00Types_Test._

import scala.collection.mutable.ListBuffer

class _06Or_Test extends FunSuite with LowUtil {
  val testBase = "/tmp/"

  // def bar = print('hello'); true .
  // def baz = print('world'); false .
  // def main =
  //   bar || baz
  //   baz || bar .

  def mkMod() = {
    val mod = Mod(lowCode = ListBuffer(
      "declare i32 @printf(i8*, ...)",
      "@.str = private constant [6 x i8] c\"hello\\00\", align 1",
      "@.str2 = private constant [6 x i8] c\"world\\00\", align 1"
    ))
    mod.defineType(Fn("\\ -> Bool", Seq.empty, Seq.empty, bool))
    mod.defineDef(Def("bar", TypeRef("\\ -> Bool"), Seq.empty, Seq.empty, LLCode(
      """
        %1 = bitcast [6 x i8]* @.str to i8*
        %2 = call i32 (i8*, ...) @printf(i8* %1)
        ret i8 1
      """
    )))
    mod.defineDef(Def("baz", TypeRef("\\ -> Bool"), Seq.empty, Seq.empty, LLCode(
      """
        %1 = bitcast [6 x i8]* @.str2 to i8*
        %2 = call i32 (i8*, ...) @printf(i8* %1)
        ret i8 0
      """
    )))
    mod
  }

  test("Or: left only") {
    val mod = mkMod()
    mod.defineDef(Def("main", TypeRef("\\ -> Bool"), Seq.empty, Seq.empty, AbraCode(
      vars = Map("x" -> bool),
      stats = Seq(
        Or(Id("x"),
          checkLeft = Seq(
            Store(init = true, Id("x"), Call(Id("bar")))),
          checkRight = Seq(
            Store(init = true, Id("x"), Call(Id("baz"))))),
        Ret(Some("x"))
      ))))

    mod.assertRunEquals(exit = Some(1), stdout = Some("hello"))
  }

  test("Or: left and right") {
    val mod = mkMod()
    mod.defineDef(Def("main", TypeRef("\\ -> Bool"), Seq.empty, Seq.empty, AbraCode(
      vars = Map("x" -> bool),
      stats = Seq(
        Or(Id("x"),
          checkLeft = Seq(
            Store(init = true, Id("x"), Call(Id("baz")))),
          checkRight = Seq(
            Store(init = true, Id("x"), Call(Id("bar"))))),
        Ret(Some("x"))
      ))))

    mod.assertRunEquals(exit = Some(1), stdout = Some("worldhello"))
  }
}
