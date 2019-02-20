package typecheck

import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _00LiteralTest extends FunSuite {
  test("literal expr") {
    val ast = astForCode(
      """
         def main =
           none
           true
           false
           3.14
           'hello'
           2 .
      """)

    assertTh("() -> prelude.Int", ast.function("main"))
  }
}
