package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _15ReturnTest extends FunSuite {
  test("return: if") {
    val ast = astForCode(
      """
        def main =
          x = if true do return 'xxx' else 1 .
          42 .
      """)

    val main = ast.function("main")
    assertTh("() -> String | Int", main)
    assertTh("Int", main.varDecl("x"))
  }

  test("return: while") {
    val ast = astForCode(
      """
        def main =
          while true do
            return 42 ..
      """)

    val main = ast.function("main")
    assertTh("() -> Int | None", main)
  }

  test("return: unless") {
    val ast = astForCode(
      """
        def main =
          x: Int | String = 42
          y = x unless
            is i: Int do return i
            is s: String do s .

          41 .
      """)

    val main = ast.function("main")
    assertTh("() -> Int", main)
    assertTh("String", main.varDecl("y"))
  }
}
