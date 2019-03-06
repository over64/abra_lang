package typecheck

import m3.typecheck.TCE
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
           apply(42, lambda x -> x) .
      """)

    assertTh("() -> Int", ast.function("main"))
  }

  test("call: anon lambda") {
    val ast = astForCode(
      """
         def main =
           lambda x: Int -> x .(1) .
      """)

    assertTh("() -> Int", ast.function("main"))
  }

  test("call: local lambda") {
    val ast = astForCode(
      """
         def main =
           closure = lambda x: Int -> x .
           closure(1) .
      """)

    assertTh("() -> Int", ast.function("main"))
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

