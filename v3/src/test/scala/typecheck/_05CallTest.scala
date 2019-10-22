package typecheck

import m3._02typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._


class _05CallTest extends FunSuite {
  test("call: global function") {
    val ast = astForCode(
      """
         def foo = x: Int, y: Int do 42 .
         def main =
           foo(1, 2) .
      """)

    assertTh("(Int, Int) -> Int", ast.function("foo"))
    assertTh("() -> Int", ast.function("main"))
  }

  test("call: lambda param") {
    val ast = astForCode(
      """
         def apply = x: Int, lmb: (Int) -> Int do
           lmb(x) .

         def main =
           apply(42, |x| x) .
      """)

    assertTh("() -> Int", ast.function("main"))
  }

  test("call: anon lambda") {
    val ast = astForCode(
      """
         def main =
           |x: Int| x .(1) .
      """)

    assertTh("() -> Int", ast.function("main"))
  }

  test("call: local lambda") {
    val ast = astForCode(
      """
         def main =
           closure = |x: Int| x .
           closure(1) .
      """)

    assertTh("() -> Int", ast.function("main"))
  }

  test("call: struct field") {
    val ast = astForCode(
      """
         type Some = (callback: (Int) -> Int)
         def simple = x: Int do x .

         def main =
           some = Some(simple)
           some.callback(42) .
      """)

    val main = ast.function("main")
    assertTh("Some", main.varDecl("some"))
    assertTh("() -> Int", main)
  }

  test("call: anon struct field") {
    val ast = astForCode(
      """
         type Some = (callback: (Int) -> Int)
         def simple = x: Int do x .

         def main =
           tuple = (simple, 42)
           tuple.x0(42) .
      """)

    val main = ast.function("main")
    assertTh("(x0: (Int) -> Int, x1: Int)", main.varDecl("tuple"))
    assertTh("() -> Int", main)
  }

  test("call: with union type hint") {
    val ast = astForCode(
      """
         def simple = 42 .

         def main =
           x: Int | String = simple()
           x .
      """)

    val main = ast.function("main")
    assertTh("() -> Int | String", main)
  }

  test("call fail: arg type mismatch") {
    assertThrows[TCE.TypeMismatch] {
      astForCode(
        """
         def bar = x: Int do x .
         def main =
           bar(1.0) .
      """)
    }
  }
}

