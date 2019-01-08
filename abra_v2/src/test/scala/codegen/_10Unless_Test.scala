package codegen

import codegen._00Types_Test._
import m3.codegen.Ast2._
import m3.codegen.ConstGen
import m3.codegen.IrUtil.Mod
import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

class _10Unless_Test extends FunSuite with LowUtil {
  val testBase = "/tmp/"

  // #ABRA with style
  // def main =
  //   u: Bool | Int | String = 'hello'
  //   u unless
  //     is Bool do 0
  //     is s: String do print(s); 2 ..
  test("unless") {
    val mod = Mod(lowCode = ListBuffer(
      "declare i32 @printf(i8*, ...)",
      """@.printfStr = private constant [3 x i8] c"%s\00", align 1""",
      "@.str = private constant [6 x i8] c\"hello\\00\", align 1",
      "@.str2 = private constant [6 x i8] c\"world\\00\", align 1"
    ))

    mod.defineType(tNil)
    mod.defineType(tBool)
    mod.defineType(tInt)
    mod.defineType(tString)
    mod.defineType(Fn("\\String -> None", closure = Seq.empty, args = Seq(string), nil))
    mod.defineDef(Def("print", TypeRef("\\String -> None"), closure = Seq.empty, args = Seq("s"), LLCode(
      """
        %format = bitcast [3 x i8]* @.printfStr to i8*
        call i32 (i8*,...) @printf(i8* %format, i8* %s)
        ret void
      """), isAnon = false))

    val i0 = ConstGen.int(mod, "0")
    val i1 = ConstGen.int(mod, "1")
    val i2 = ConstGen.int(mod, "2")
    val sHello = ConstGen.string(mod, "hello\n")

    mod.defineType(tU4)
    mod.defineDef(Def("main", TypeRef("\\ -> Int"), Seq.empty, Seq.empty, AbraCode(
      vars = Map("r" -> int, "u" -> u4, "s" -> string, "i" -> int),
      stats = Seq(
        Store(init = true, Id("u"), Call(sHello)),
        Unless(
          dest = "r",
          src = Id("u"),
          is = Seq(
            Is(None, bool, Seq(
              Store(init = false, Id("r"), Call(i0)))),
            Is(Some("s"), string, Seq(
              Call(Id("print"), Seq(Id("s"))),
              Store(init = false, Id("r"), Call(i2)),
              Free(Id("s")))))),
        Free(Id("u")),
        Ret(Some("r"))
      ))))

    mod.assertRunEquals(exit = Some(2))
  }
}
