package codegen

import codegen._00Types_Test.tVec2
import m3.codegen.Ast2._
import m3.codegen.ConstGen
import m3.codegen.IrUtil.Mod
import org.scalatest.FunSuite
import _00Types_Test._

class _04Store_LocalToLocal_Test extends FunSuite with LowUtil {
  val testBase = "/tmp/"


  test("store local -> local: value struct & field") {
    val mod = Mod()
    val i0 = ConstGen.int(mod, "0")

    mod.defineType(tNil)
    mod.defineType(tInt)
    mod.defineType(tVec2)

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "x" -> vec2,
        "y" -> vec2,
        "ret" -> int),
      stats = Seq(
        Store(init = false, Id("x", Seq("y")), Call(i0)),
        Store(init = true, Id("y"), Id("x")),
        Store(init = true, Id("ret"), Id("x", Seq("y"))),
        Ret(Some("ret"))
      ))))

    mod.assertRunEquals(exit = Some(0))
  }

  test("store local -> local: ref struct & field") {
    val mod = Mod()
    val i0 = ConstGen.int(mod, "0")
    val sHi = ConstGen.string(mod, "hi")

    mod.defineType(tNil)
    mod.defineType(tInt)
    mod.defineType(tString)
    mod.defineType(tIntAndString)

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "x" -> int,
        "y" -> string,
        "z" -> intAndString,
        "ret" -> int),
      stats = Seq(
        Store(init = true, Id("x"), Call(i0, Seq.empty)),
        Store(init = true, Id("y"), Call(sHi, Seq.empty)),
        Store(init = true, Id("z"), Cons(intAndString, Seq(Id("x"), Id("y")))),
        // self store resistance check
        Store(init = false, Id("z"), Id("z")),
        Store(init = false, Id("z", Seq("x")), Id("x")),
        Store(init = false, Id("z", Seq("y")), Id("y")),

        Store(init = true, Id("ret"), Id("z", Seq("x"))),

        Free(Id("y")),
        Free(Id("z")),

        Ret(Some("ret"))
      ))))

    mod.assertRunEquals(exit = Some(0))
  }


  test("local store: unions") {
    val mod = Mod()
    val i0 = ConstGen.int(mod, "0")
    val sHi = ConstGen.string(mod, "hi")

    mod.defineType(tNil)
    mod.defineType(tInt)
    mod.defineType(tString)
    mod.defineType(tU1)
    mod.defineType(tU2)

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "r" -> int,
        "s" -> string,
        "u1" -> u1,
        "u2" -> u2),
      stats = Seq(
        Store(init = true, Id("u1"), Call(i0)),
        Store(init = true, Id("u2"), Call(sHi)),
        Store(init = false, Id("u2"), Id("u1")),

        Store(init = true, Id("r"), Call(i0)),
        Free(Id("u1")),
        Free(Id("u2")),
        Ret(Some("r"))
      ))))

    mod.assertRunEquals(exit = Some(0))
  }

  test("local store: union to another compatible union type") {
    // u1 is part of u4
    // store u1 -> u4 must be possible
    // U1 = String | Int
    // U4 = Bool | Int | String

    val mod = Mod()
    val i0 = ConstGen.int(mod, "0")
    val sHi = ConstGen.string(mod, "hi")

    mod.defineType(tNil)
    mod.defineType(tBool)
    mod.defineType(tInt)
    mod.defineType(tString)
    mod.defineType(tU1)
    mod.defineType(tU4)

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "r" -> int,
        "s" -> string,
        "u1" -> u1,
        "u4" -> u4),
      stats = Seq(
        Store(init = true, Id("u1"), Call(i0)),
        Store(init = true, Id("u4"), Call(sHi)),
        Store(init = false, Id("u4"), Id("u1")),

        Store(init = true, Id("r"), Call(i0)),
        Free(Id("u1")),
        Free(Id("u4")),
        Ret(Some("r"))
      ))))

    mod.assertRunEquals(exit = Some(0))
  }
}
