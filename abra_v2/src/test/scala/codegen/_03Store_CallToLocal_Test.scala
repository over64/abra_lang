package codegen

import m3.codegen.Ast2._
import m3.codegen.ConstGen
import m3.codegen.IrUtil.Mod
import org.scalatest.FunSuite
import _00Types_Test._

import scala.collection.mutable

class _03Store_CallToLocal_Test extends FunSuite with LowUtil {
  val testBase = "/tmp/"

  test("store call -> local: literals") {
    val mod = Mod()
    val bFalse = ConstGen.bool(mod, "true")
    val i42 = ConstGen.int(mod, "0")
    val fPi = ConstGen.float(mod, "3.14")
    val sHello = ConstGen.string(mod, "hello!")

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "x" -> bool,
        "y" -> int,
        "z" -> float,
        "w" -> string),
      stats = Seq(
        Store(init = true, Id("x"), Call(bFalse)),
        Store(init = true, Id("y"), Call(i42)),
        Store(init = true, Id("z"), Call(fPi)),
        Store(init = true, Id("w"), Call(sHello)),

        Free(Id("w")),
        Ret(Some("y"))
      ))))

    mod.assertRunEquals(exit = Some(0))
  }

  test("store call(cons) -> local: value struct") {
    val mod = Mod()
    val i42 = ConstGen.int(mod, "0")

    mod.defineType(tVec2)

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "x" -> vec2,
        "y" -> int),
      stats = Seq(
        Store(init = true, Id("y"), Call(i42)),
        Store(init = true, Id("x"), Cons(vec2, Seq(Id("y"), Id("y")))),
        Ret(Some("y"))
      ))))

    mod.assertRunEquals(exit = Some(0))
  }

  test("store call(cons) -> local: ref struct") {
    val mod = Mod()
    val i0 = ConstGen.int(mod, "0")
    val sHi = ConstGen.string(mod, "hi")

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

        Free(Id("y")),
        Free(Id("z")),

        Ret(Some("x"))
      ))))

    mod.assertRunEquals(exit = Some(0))
  }

  test("store call -> local: union") {
    val mod = Mod()
    val i0 = ConstGen.int(mod, "0")

    mod.defineType(tU1)
    mod.defineType(Fn("\\ -> U1", Seq.empty, Seq.empty, u1))

    mod.defineDef(Def("bar", TypeRef("\\ -> U1"), Seq.empty, Seq.empty, LLCode(
      """
        %x = alloca %U1
        %xx = load %U1, %U1* %x
        ret %U1 %xx
      """
    ), isAnon = false))

    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map(
        "x" -> int,
        "y" -> u1),
      stats = Seq(
        Store(init = true, Id("x"), Call(i0, Seq.empty)),
        Store(init = true, Id("y"), Call(Id("bar"))),
        Ret(Some("x"))
      ))))

    mod.assertRunEquals(exit = Some(0))
  }

}
