package codegen

import m3.codegen.Ast2._
import m3.codegen.ConstGen
import m3.codegen.IrUtil.Mod
import org.scalatest.FunSuite
import _00Types_Test._

class _05Closure_CaptureLocal_Test extends FunSuite with LowUtil {
  val testBase = "/tmp/"

  test("capture local: value scalar") {
    //  def main =
    //    x = 13
    //    -> x = 42 .()
    //    x .
    val mod = Mod()
    val (i42, i13) = (ConstGen.int(mod, "42"), ConstGen.int(mod, "13"))

    mod.defineType(Fn("Closure1", closure = Seq(Local(int)), args = Seq.empty, nil))

    mod.defineDef(Def("closure", TypeRef("Closure1"), Seq("x"), Seq.empty, AbraCode(
      vars = Map(),
      stats = Seq(
        Store(init = false, Id("x"), Call(i42)),
        Ret(None)
      ))))

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "x" -> int,
        "cl" -> TypeRef("Closure1")),
      stats = Seq(
        Store(init = true, Id("x"), Call(i13)),
        Closure("cl", "closure"),
        Call(Id("cl")),
        Ret(Some("x"))
      ))))

    mod.assertRunEquals(exit = Some(42))
  }

  test("capture local: ref scalar") {
    //  def main =
    //    x = 'hell'
    //    -> x = 'boy' .()
    //    42 .
    val mod = Mod()
    val (sHell, sBoy) = (ConstGen.string(mod, "hell"), ConstGen.string(mod, "boy"))
    val i42 = ConstGen.int(mod, "42")

    mod.defineType(Fn("Closure1", closure = Seq(Local(string)), args = Seq.empty, nil))

    mod.defineDef(Def("closure", TypeRef("Closure1"), Seq("x"), Seq.empty, AbraCode(
      vars = Map(),
      stats = Seq(
        Store(init = false, Id("x"), Call(sBoy)),
        Ret(None)
      ))))

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "x" -> string,
        "cl" -> TypeRef("Closure1"),
        "r" -> int),
      stats = Seq(
        Store(init = true, Id("x"), Call(sHell)),
        Closure("cl", "closure"),
        Call(Id("cl")),
        Free(Id("x")),
        Store(init = true, Id("r"), Call(i42)),
        Ret(Some("r"))
      ))))

    mod.assertRunEquals(exit = Some(42))
  }

  test("capture local: value struct") {
    //  def main =
    //    x = Vec2(42, 42)
    //    -> x.x = 13 .()
    //    x.x .
    val mod = Mod()
    val (i42, i13) = (ConstGen.int(mod, "42"), ConstGen.int(mod, "13"))

    mod.defineType(Fn("Closure1", closure = Seq(Local(vec2)), args = Seq.empty, nil))
    mod.defineType(tVec2)

    mod.defineDef(Def("closure", TypeRef("Closure1"), Seq("x"), Seq.empty, AbraCode(
      vars = Map(),
      stats = Seq(
        Store(init = false, Id("x", Seq("x")), Call(i13)),
        Ret(None)
      ))))

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "$0" -> int,
        "x" -> vec2,
        "cl" -> TypeRef("Closure1"),
        "r" -> int),
      stats = Seq(
        Store(init = true, Id("$0"), Call(i42)),
        Store(init = true, Id("x"), Cons(vec2, Seq(Id("$0"), Id("$0")))),
        Closure("cl", "closure"),
        Call(Id("cl")),
        Store(init = true, Id("r"), Id("x", Seq("x"))),
        Ret(Some("r"))
      ))))

    mod.assertRunEquals(exit = Some(13))
  }

  test("capture local: ref struct") {
    //  def main =
    //    x = (42, 'hell')
    //    ->
    //      x.x = 13
    //      x.y = 'boy' .()
    //    x.x .
    val mod = Mod()
    val (i42, i13) = (ConstGen.int(mod, "42"), ConstGen.int(mod, "13"))
    val (sHell, sBoy) = (ConstGen.string(mod, "hell"), ConstGen.string(mod, "boy"))

    mod.defineType(Fn("Closure1", closure = Seq(Local(intAndString)), args = Seq.empty, nil))
    mod.defineType(tIntAndString)

    mod.defineDef(Def("closure", TypeRef("Closure1"), Seq("x"), Seq.empty, AbraCode(
      vars = Map(),
      stats = Seq(
        Store(init = false, Id("x", Seq("x")), Call(i13)),
        Store(init = false, Id("x", Seq("y")), Call(sBoy)),
        Ret(None)
      ))))

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "$0" -> int,
        "$1" -> string,
        "x" -> intAndString,
        "cl" -> TypeRef("Closure1"),
        "r" -> int),
      stats = Seq(
        Store(init = true, Id("$0"), Call(i42)),
        Store(init = true, Id("$1"), Call(sHell)),
        Store(init = true, Id("x"), Cons(intAndString, Seq(Id("$0"), Id("$1")))),
        Closure("cl", "closure"),
        Call(Id("cl")),
        Store(init = true, Id("r"), Id("x", Seq("x"))),
        Free(Id("$1")),
        Free(Id("x")),
        Ret(Some("r"))
      ))))

    mod.assertRunEquals(exit = Some(13))
  }

  test("capture local: union") {
    //  def main =
    //    x: Int | String = 'hi'
    //    -> x = 42 .()
    //    # "cast" to int for test only
    //    x .
    val mod = Mod()
    val i42 = ConstGen.int(mod, "42")
    val sHi = ConstGen.string(mod, "hi")

    mod.defineType(tU1)
    mod.defineType(Fn("Closure1", closure = Seq(Local(u1)), Seq.empty, nil))

    mod.defineDef(Def("closure", TypeRef("Closure1"), Seq("x"), Seq.empty, AbraCode(
      vars = Map(),
      stats = Seq(
        Store(init = false, Id("x"), Call(i42)),
        Ret(None)
      ))))

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "x" -> u1,
        "cl" -> TypeRef("Closure1"),
        "r" -> int),
      stats = Seq(
        Store(init = true, Id("x"), Call(sHi)),
        Closure("cl", "closure"),
        Call(Id("cl")),

        Store(init = true, Id("r"), Id("x", Seq("1"))),

        Free(Id("x")),
        Ret(Some("r"))
      ))))

    mod.assertRunEquals(exit = Some(42))
  }

}
