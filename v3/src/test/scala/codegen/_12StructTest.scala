package codegen

import org.scalatest.FunSuite

class _12StructTest extends FunSuite {
  test("struct: load value prop") {
    CodeGenUtil.run(
      """
        type S = (x: Int, y: Int)
        def main =
          s = S(42, 1)
          s.x .
      """, exitCode = 42)
  }

  test("struct: load ref prop") {
    CodeGenUtil.run(
      """
        native
          declare void @puts(i8*)  .

        def print = str: String native
          call void @puts(i8* %str)
          ret void .None

        type S = (x: String, y: Int)
        def main =
          s = S('hello', 1)
          print(s.x)
          42 .
      """, exitCode = 42, stdout = Some("hello\n"))
  }

  test("struct: store value prop") {
    CodeGenUtil.run(
      """
        type S = (x: Int, y: Int)
        def main =
          s = S(1, 1)
          s.x = 42
          s.x .
      """, exitCode = 42)
  }

  test("struct: store ref prop") {
    CodeGenUtil.run(
      """
        native
          declare void @puts(i8*)  .

        def print = str: String native
          call void @puts(i8* %str)
          ret void .None

        type S = (x: String, y: Int)
        def main =
          s = S('hello', 1)
          s.x = 'world'
          print(s.x)
          42 .
      """, exitCode = 42, stdout = Some("world\n"))
  }

  test("struct: load deep value prop") {
    CodeGenUtil.run(
      """
        type A = (b: B, y: Int)
        type B = (x: Int, y: Int)
        def main =
          a = A(B(42, 1), 1)
          a.b.x .
      """, exitCode = 42)
  }
}