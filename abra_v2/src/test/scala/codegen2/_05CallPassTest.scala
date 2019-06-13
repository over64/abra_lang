package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _05CallPassTest extends FunSuite {
  test("call: pass value scalar") {
    compile(
      """
        def bar = x1: Int do .

        def main =
          bar(1) .
      """)
  }

  test("call: pass ref scalar") {
    compile(
      """
        def bar = x1: String do .

        def main =
          bar('hello') .
      """)
  }

  test("call: pass value struct") {
    compile(
      """
        type A[t, u] = (x: t, y: u)
        def bar = x1: A[Int, Int] do .

        def main =
          bar(A(1, 1)) .
      """)
  }

  test("call: pass ref struct") {
    compile(
      """
        type A[t, u] = (x: t, y: u)
        def bar = x1: A[Int, String] do .

        def main =
          bar(A(1, 'hello')) .
      """)
  }

  test("call: pass value union") {
    compile(
      """
        def bar = x1: Int | None do .
        def main = bar(42) .
      """)
  }

  test("call: pass value union (none)") {
    compile(
      """
        def bar = x1: Int | None do .
        def main = bar(none) .
      """)
  }

  test("call: pass ref union (value)") {
    compile(
      """
        def bar = x1: Int | String | None do .
        def main = bar(42) .
      """)
  }

  test("call: pass ref union (ref)") {
    compile(
      """
        def bar = x1: Int | String | None do .
        def main = bar('hello') .
      """)
  }

  test("call: pass ref union (none)") {
    compile(
      """
        def bar = x1: Int | String | None do .
        def main = bar(none) .
      """)
  }

  test("call: pass nullable union (ref)") {
    compile(
      """
        def bar = x1: String | None do .
        def main = bar('hello') .
      """)
  }

  test("call: pass nullable union (none)") {
    compile(
      """
        def bar = x1: String | None do .
        def main = bar(none) .
      """)
  }
}
