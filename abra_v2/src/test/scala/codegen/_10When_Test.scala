package codegen

import m3.codegen.Ast2._
import org.scalatest.FunSuite
import _00Types_Test._
import m3.codegen.ConstGen
import m3.codegen.IrUtil.Mod

import scala.collection.mutable.ListBuffer

class _10When_Test extends FunSuite with LowUtil {
  val testBase = "/tmp/"

  // #ABRA with style
  // def main =
  //   u: Bool | Int | String = 'hello'
  //   when u
  //     is i: Int    do print('world'); i
  //     is s: String do print(s); 2
  //     else 0 ..
  test("when") {
    val mod = Mod(lowCode = ListBuffer(
      "declare i32 @printf(i8*, ...)",
      "@.str = private constant [6 x i8] c\"hello\\00\", align 1",
      "@.str2 = private constant [6 x i8] c\"world\\00\", align 1"
    ))

    mod.defineType(Fn("\\String -> None", closure = Seq.empty, args = Seq(string), nil))
    mod.defineDef(Def("print", TypeRef("\\String -> None"), closure = Seq.empty, args = Seq("s"), LLCode(
      """
        ret void
      """), isAnon = false))

    val i0 = ConstGen.int(mod, "0")
    val i1 = ConstGen.int(mod, "1")
    val i2 = ConstGen.int(mod, "2")

    mod.defineType(tU4)
    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map("r" -> int, "u" -> u4, "s" -> string, "i" -> int),
      stats = Seq(
        Store(init = true, Id("u"), Call(i1)),
        When(Id("u"),
          is = Seq(
            Is("i", int, Seq(
              Store(init = false, Id("r"), Id("i")))),
            Is("s", string, Seq(
              Call(Id("print"), Seq(Id("s"))),
              Store(init = false, Id("r"), Call(i2))))),
          _else = Seq(
            Store(init = false, Id("r"), Call(i0)))),
        Ret(Some("r"))
      ))))

    mod.assertRunEquals(exit = Some(1))
  }
}
