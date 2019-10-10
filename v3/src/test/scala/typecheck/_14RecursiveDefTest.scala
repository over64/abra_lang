package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _14RecursiveDefTest extends FunSuite {
  test("recursive def: simple") {
    val ast = astForCode(
      """
        def * = self: Int, other: Int native
          ; native code
          .Int

        def - = self: Int, other: Int native
          ; native code
          .Int

        def fact = x: Int do
          if x == 1 do 1 else x * fact(x - 1) ..Int

        def main = fact(4) .
      """)

    assertTh("(Int) -> Int", ast.function("fact"))
    assertTh("() -> Int", ast.function("main"))
  }

  test("recursive def(fail): ret type hint required") {
    assertThrows[TCE.RetTypeHintRequired] {
      astForCode(
        """
        def * = self: Int, other: Int native
          ; native code
          .Int

        def - = self: Int, other: Int native
          ; native code
          .Int

        def fact = x: Int do
          if x == 1 do 1 else x * fact(x - 1) ..

        def main = fact(4) .
      """)
    }
  }
}