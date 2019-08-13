package codegen2
import org.scalatest.FunSuite

class _02ConsTest extends FunSuite {
  test("construction: value struct") {
    CodeGenUtil.run(
      """
        type Foo = (x: Int, y: Int)
        def main =
          Foo(1, 1)
          42.
      """, exitCode = 42)
  }

  test("construction: ref struct") {
    CodeGenUtil.run(
      """
        type Foo = (x: Int, y: String)
        def main =
          Foo(1, 'hello')
          42.
      """, exitCode = 42)
  }

  test("construction: generic value struct") {
    CodeGenUtil.run(
      """
        type A[t, u]= (x: t, y: u)
        def main =
          A(1, 1)
          42.
      """, exitCode = 42)
  }

  test("construction: generic ref struct") {
    CodeGenUtil.run(
      """
        type A[t, u]= (x: t, y: String)
        def main =
          A(1, 'hello')
          42.
      """, exitCode = 42)
  }

  test("construction: struct recursive") {
    CodeGenUtil.run(
      """
        type Bar = (x: Int, y: String)
        type A[t, u] = (x: t, y: u)

        def main =
          A(1, Bar(1, 'hello'))
          42 .
      """, exitCode = 42)
  }

  test("construction: value array") {
    CodeGenUtil.run(
      """
        def main =
          Array3(1, 2, 3)
          42 .
      """, exitCode = 42)
  }

  test("construction: ref array") {
    CodeGenUtil.run(
      """
        def main =
          Array(1, 2, 3)
          42 .
      """, exitCode = 42)
  }

  test("construction: value union field conv") {
    CodeGenUtil.run(
      """
        type A[t, u] = (x: t, y: u)

        def main =
          A[Int, Int | Bool](1, false)
          42 .
      """, exitCode = 42)
  }

  test("construction: ref union field conv") {
    CodeGenUtil.run(
      """
        type A[t, u] = (x: t, y: u)

        def main =
          A[Int, Int | String](1, 'world')
          42 .
      """, exitCode = 42)
  }

  test("construction: nullable union field conv") {
    CodeGenUtil.run(
      """
        type A[t, u] = (x: t, y: u)

        def main =
          A[Int, String | None](1, 'world')
          A[Int, String | None](1, none)
          42 .
      """, exitCode = 42)
  }

  test("construction: value tuple") {
    CodeGenUtil.run(
      """
    def main =
      (1, 42)
      42 .
    """, exitCode = 42)
  }

  test("construction: ref tuple") {
    CodeGenUtil.run(
      """
        def main =
          (1, 42, 'hello')
          42 .
      """, exitCode = 42)
  }
}