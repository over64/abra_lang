package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _14OIfElseTest extends FunSuite {
  test("if: true") {
    compile(
      """def main =
           x = if true do 1 else 2 ..
      """.stripMargin)
  }

  test("if: false") {
    compile(
      """def main =
           x = if false do 1 else 2 ..
      """.stripMargin)
  }

  test("if: none vs unreachable") {
    compile(
      """
        llvm
          declare void @exit(i32) .

        def panic = code: Int do llvm
          call void @exit(i32 %code)
          ret void .Unreachable

        def main =
          if true do none else panic(666) .
          42 .
      """.stripMargin)
  }

  test("if: value vs ref") {
    compile(
      """
         type A = (x: Int, y: Int)
         def mkA = A(1, 1) .
         def main =
           x = A(0, 0)
           y = if true do x else mkA() .
           42 .
      """.stripMargin)
  }

  test("if: different types") {
    compile(
      """
         type A = (x: Int, y: Int)
         def mkA = A(1, 1) .
         def main =
           x: A | String = A(0, 0)
           y = if true do 'hello' else x .
           42 .
      """.stripMargin)
  }

  test("if: block vars clash") {
    compile(
      """
         def main =
           if true do
             x = 42 .
           if true do
             x = 'hello' .
           42 .
      """.stripMargin)
  }
}
