package codegen

import codegen._00Types_Test.{nil, tU1, u1}
import m3.codegen.Ast2._
import m3.codegen.ConstGen
import m3.codegen.IrUtil.Mod
import org.scalatest.FunSuite
import _00Types_Test._


class _11DisclosureTest extends FunSuite with LowUtil {
  val testBase = "/tmp/"

  test("pass closure as param") {
    //  def bar = fn: \Int, Int -> Int do
    //    fn(1, 1) .
    //  def main =
    //    xx = 42
    //    bar(lambda x, y do xx) .

    val mod = Mod()
    mod.defineType(tNil)
    mod.defineType(tInt)
    mod.defineType(tString)
    val i1 = ConstGen.int(mod, "1")
    val i42 = ConstGen.int(mod, "42")

    mod.defineType(tU1)
    mod.defineType(Fn("(Int, Int) -> Int", closure = Seq.empty, Seq(int, int), int))
    mod.defineType(Fn("((Int, Int) -> Int) -> Int", Seq.empty, Seq(TypeRef("(Int, Int) -> Int")), int))
    mod.defineType(Fn("Closure1", Seq(Local(int)), Seq(int, int), int))

    mod.defineDef(Def("bar", TypeRef("((Int, Int) -> Int) -> Int"), Seq.empty, Seq("fn"), AbraCode(
      vars = Map("x" -> int, "l" -> int),
      stats = Seq(
        Store(init = true, Id("l"), Call(i1)),
        Store(init = true, Id("x"), Call(Id("fn"), Seq(Id("l"), Id("l")))),
        Ret(Some("x"))
      ))))

    mod.defineDef(Def("closure", TypeRef("Closure1"), Seq("xx"), Seq("x", "y"), AbraCode(
      vars = Map(),
      stats = Seq(
        Ret(Some("xx"))
      ))))

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "xx" -> int,
        "cl" -> TypeRef("Closure1"),
        "r" -> int),
      stats = Seq(
        Store(init = true, Id("xx"), Call(i42)),
        Store(init = true, Id("cl"), Id("closure")),
        Store(init = true, Id("r"), Call(Id("bar"), Seq(Id("cl")))),
        Ret(Some("r"))
      ))))

    mod.assertRunEquals(exit = Some(42))
  }
}
