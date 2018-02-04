package codegen

import codegen._00Types_Test.{bool, int}
import m3.codegen.Ast2._
import m3.codegen.ConstGen
import m3.codegen.IrUtil.Mod
import org.scalatest.FunSuite

class _09If_Test extends FunSuite with LowUtil {
  val testBase = "/tmp/"

  // def main =
  //   if true do 1 else 2
  test("if-do-else") {
    val mod = Mod()
    val bTrue = ConstGen.bool(mod, "true")
    val i1 = ConstGen.int(mod, "1")
    val i2 = ConstGen.int(mod, "2")

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map("r" -> int, "c" -> bool),
      stats = Seq(
        Store(init = true, Id("c"), Call(bTrue)),
        If(Id("c"),
          onTrue = Seq(
            Store(init = true, Id("r"), Call(i1))),
          onFalse = Seq(
            Store(init = true, Id("r"), Call(i2)))),
        Ret(Some("r"))
      ))))

    mod.assertRunEquals(exit = Some(1))
  }
}
