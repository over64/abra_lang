package codegen

import org.scalatest.FunSuite
import _00Types_Test._
import m3.codegen.Ast2._
import m3.codegen.ConstGen
import m3.codegen.IrUtil.Mod

class _08While_Test extends FunSuite with LowUtil {
  val testBase = "/tmp/"

  // def main =
  //   x = 1
  //   while x < 42 do
  //     x = x + 1 .
  //   x .
  test("while loop") {
    val mod = Mod()
    val i42 = ConstGen.int(mod, "42")
    val i1 = ConstGen.int(mod, "1")

    mod.defineType(Fn("\\Int, Int -> Int", Seq.empty, Seq(int, int), int))
    mod.defineType(Fn("\\Int, Int -> Bool", Seq.empty, Seq(int, int), bool))

    mod.defineDef(Def("add", TypeRef("\\Int, Int -> Int"), Seq.empty, Seq("x", "y"), LLCode(
      """%1 = add nsw i32 %x, %y
         ret i32 %1"""
    )))

    mod.defineDef(Def("less", TypeRef("\\Int, Int -> Bool"), Seq.empty, Seq("x", "y"), LLCode(
      """%1 = icmp slt i32 %x, %y
         %2 = zext i1 %1 to i8
         ret i8 %2"""
    )))

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map("x" -> int, "c42" -> int, "c1" -> int, "w" -> bool),
      stats = Seq(
        Store(init = true, Id("c42"), Call(i42)),
        Store(init = true, Id("c1"), Call(i1)),
        Store(init = true, Id("x"), Call(i1)),
        While(Id("w"),
          head = Seq(
            Store(init = false, Id("w"), Call(Id("less"), Seq(Id("x"), Id("c42"))))),
          body = Seq(
            Store(init = false, Id("x"), Call(Id("add"), Seq(Id("x"), Id("c1")))))),
        Ret(Some("x"))
      ))))

    mod.assertRunEquals(exit = Some(42))
  }
}
