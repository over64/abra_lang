package codegen2

import org.scalatest.FunSuite

class _14OIfElseTest extends FunSuite {
  test("if: true") {
    CodeGenUtil.run(
      """def main =
           if true do 1 else 2 ..
      """.stripMargin, exitCode = 1)
  }

  test("if: false") {
    CodeGenUtil.run(
      """def main =
           x = if false do 1 else 2 ..
      """.stripMargin, exitCode = 2)
  }

  test("if: none vs unreachable") {
    CodeGenUtil.run(
      """
        llvm
          declare void @exit(i32) .

        def panic = code: Int do llvm
          call void @exit(i32 %code)
          ret void .Unreachable

        def main =
          if true do none else panic(666) .
          42 .
      """.stripMargin, exitCode = 42)
  }

  test("if: value vs ref") {
    CodeGenUtil.run(
      """
         type A = (x: Int, y: Int)
         def mkA = A(1, 1) .
         def main =
           x = A(0, 0)
           y = if true do x else mkA() .
           42 .
      """.stripMargin, exitCode = 42)
  }

  test("if: different types") {
    CodeGenUtil.run(
      """
         type A = (x: Int, y: Int)
         def mkA = A(1, 1) .
         def main =
           x: A | String = A(0, 0)
           y = if true do 'hello' else x .
           42 .
      """.stripMargin, exitCode = 42)
  }

  test("if: one branch") {
    CodeGenUtil.run(
      """
         def main =
           x = if true do 1 .
           42 .
      """.stripMargin, exitCode = 42)
  }

  test("if: block vars clash") {
    CodeGenUtil.run(
      """
         def main =
           if true do
             x = 42 .
           if true do
             x = 'hello' .
           42 .
      """.stripMargin, exitCode = 42)
  }
}
