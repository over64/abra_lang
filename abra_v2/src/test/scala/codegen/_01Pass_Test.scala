package codegen

import codegen._00Types_Test.tVec2
import m3.codegen.Ast2._
import m3.codegen.ConstGen
import m3.codegen.IrUtil.Mod
import org.scalatest.FunSuite
import _00Types_Test._

class _01Pass_Test extends FunSuite with LowUtil {
  val testBase = "/tmp/"

  test("pass local -> args -> args") {
    val mod = Mod()
    val i0 = ConstGen.int(mod, "0")

    mod.defineType(tNil)
    mod.defineType(tBool)
    mod.defineType(tInt)
    mod.defineType(tString)
    mod.defineType(tVec2)
    mod.defineType(tU3)
    mod.defineType(tU1)
    mod.defineType(tIntAndString)
    mod.defineType(Fn("\\ -> None", Seq.empty, Seq.empty, nil))
    mod.defineType(Fn("Closure1", Seq(Local(int)), Seq.empty, int))
    mod.defineType(Fn("\\ -> Int", Seq.empty, Seq.empty, int))
    mod.defineType(Fn("TBar",
      closure = Seq.empty,
      args = Seq(int, vec2, u3, TypeRef("\\ -> None"), TypeRef("\\ -> Int"), string, intAndString, u1, TypeRef("\\ -> Int")),
      ret = nil))

    mod.defineDef(Def("baz", TypeRef("TBar"), Seq.empty,
      args = Seq("x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8"),
      code = AbraCode(
        vars = Map(),
        stats = Seq(Ret(None))
      )))

    mod.defineDef(Def("bar", TypeRef("TBar"), Seq.empty,
      args = Seq("x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8"),
      code = AbraCode(
        vars = Map(),
        stats = Seq(
          Call(Id("baz"), Seq(Id("x1"), Id("x2"), Id("x3"), Id("x4"), Id("x5"), Id("x6"), Id("x7"), Id("x8"))),
          Ret(None))
      )))

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "x1" -> int,
        "x2" -> vec2,
        "x3" -> u3,
        "x4" -> TypeRef("\\ -> None"),
        "x5" -> TypeRef("Closure1"),
        "x6" -> string,
        "x7" -> intAndString,
        "x8" -> u1
      ),
      stats = Seq(
        Call(Id("bar"), Seq(Id("x1"), Id("x2"), Id("x3"), Id("x4"), Id("x5"), Id("x6"), Id("x7"), Id("x8"))),
        Store(init = true, Id("x1"), Call(i0)),
        Ret(Some("x1"))
      ))))

    mod.assertRunEquals(exit = Some(0))
  }
}
