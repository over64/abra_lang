package codegen

import codegen._00Types_Test._
import m3.codegen.Ast2._
import m3.codegen.ConstGen
import m3.codegen.IrUtil.Mod
import org.scalatest.FunSuite

class _02Closure_Test extends FunSuite with LowUtil {
  val testBase = "/tmp/"

  test("capture") {

    // def bazz = \x1: Int, x2: Vec2, ... do
    //   l1: Int
    //   l2: Vec2 ...
    //
    //  ->  #closure1
    //    ->  #closure2
    //    .()
    //  .()
    //

    val mod = Mod()
    val i0 = ConstGen.int(mod, "0")

    mod.defineType(tVec2)
    mod.defineType(tU3)
    mod.defineType(tU1)
    mod.defineType(tIntAndString)
    mod.defineType(Fn("\\ -> None", Seq.empty, Seq.empty, nil))
    mod.defineType(Fn("Closure1", Seq(Local(int)), Seq.empty, int))

    mod.defineType(Fn("TBazz",
      closure = Seq.empty,
      args = Seq(int, vec2, u3, TypeRef("\\ -> None"), TypeRef("\\ -> None"), string, intAndString, u1, TypeRef("\\ -> Int")),
      ret = nil))

    mod.defineType(Fn("TClosure",
      closure = Seq(
        Local(int),
        Local(vec2),
        Local(u3),
        Local(TypeRef("\\ -> None")),
        Local(TypeRef("Closure1")),
        Local(string),
        Local(intAndString),
        Local(u1),
        Param(int),
        Param(vec2),
        Param(u3),
        Param(TypeRef("\\ -> None")),
        Param(TypeRef("\\ -> None")),
        Param(string),
        Param(intAndString),
        Param(u1)
      ),
      args = Seq.empty,
      ret = nil))


    mod.defineDef(Def("closure1", TypeRef("TClosure"),
      closure = Seq(
        "l1", "l2", "l3", "l4", "l5", "l6", "l7", "l8",
        "x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8"),
      args = Seq.empty,
      code = AbraCode(
        vars = Map(
          "c" -> TypeRef("TClosure")
        ),
        stats = Seq(
          Closure("c", "closure2"),
          Call(Id("c")),
          Ret(None))
      )))

    mod.defineDef(Def("closure2", TypeRef("TClosure"),
      closure = Seq(
        "l1", "l2", "l3", "l4", "l5", "l6", "l7", "l8",
        "x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8"),
      args = Seq.empty,
      code = AbraCode(
        vars = Map(),
        stats = Seq(
          Ret(None))
      )))

    mod.defineDef(Def("bazz", TypeRef("TBazz"), Seq.empty,
      args = Seq("x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8"),
      code = AbraCode(
        vars = Map(
          "l1" -> int,
          "l2" -> vec2,
          "l3" -> u3,
          "l4" -> TypeRef("\\ -> None"),
          "l5" -> TypeRef("Closure1"),
          "l6" -> string,
          "l7" -> intAndString,
          "l8" -> u1,
          "c" -> TypeRef("TClosure")
        ),
        stats = Seq(
          Closure("c", "closure1"),
          Call(Id("c")),
          Ret(None)
        ))))

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
        Call(Id("bazz"), Seq(Id("x1"), Id("x2"), Id("x3"), Id("x4"), Id("x5"), Id("x6"), Id("x7"), Id("x8"))),
        Store(init = true, Id("x1"), Call(i0)),
        Ret(Some("x1"))
      ))))

    mod.assertRunEquals(exit = Some(0))
  }

}
