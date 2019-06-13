package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _03ConsTest extends FunSuite {
  test("construction: value struct") {
    compile(
      """
        type Foo = (x: Int, y: Int)

        def main =
          Foo(1, 2) .
      """)
  }

  test("construction: ref struct") {
    compile(
      """
        type Bar = (x: Int, y: String)

        def main =
          Bar(1, 'hello') .
      """)
  }

  test("construction: generic value struct") {
    compile(
      """
        type A[t, u] = (x: t, y: u)

        def main =
          A(1, 1) .
      """)
  }

  test("construction: generic ref struct") {
    compile(
      """
        type A[t, u] = (x: t, y: u)

        def main =
          A(1, 'hello') .
      """)
  }

  test("construction: struct recursive") {
    compile(
      """
        type Bar = (x: Int, y: String)
        type A[t, u] = (x: t, y: u)

        def main =
          A(1, Bar(1, 'hello')) .
      """)
  }

  test("construction: value array") {
    compile(
      """
        def main =
          Array3(1, 2, 3) .
      """)
  }

  test("construction: ref array") {
    compile(
      """
        def main =
          Array(1, 2, 3) .
      """)
  }

  test("construction: value union field conv") {
    compile(
      """
        type A[t, u] = (x: t, y: u)

        def main =
          A[Int, Int | Bool](1, false) .
      """)
  }

  test("construction: ref union field conv") {
    compile(
      """
        type A[t, u] = (x: t, y: u)

        def main =
          A[Int, Int | String](1, 'world') .
      """)
  }

  // FIX ABI: arrays & strings as plain ptr

  test("construction: nullable union field conv") {
    compile(
      """
        type A[t, u] = (x: t, y: u)

        def main =
          A[Int, String | None](1, 'world')
          A[Int, String | None](1, none) .
      """)
  }

  test("construction: value tuple") {
    compile(
      """
    def main =
      (1, 42) .
    """)
  }

  test("construction: ref tuple") {
    compile(
      """
        def main =
          (1, 42, 'hello') .
      """)
  }
}