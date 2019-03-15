package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _10OrAndTest extends FunSuite {
  test("AndOr") {
    val ast = astForCode(
      """
         def main =
           true || false
           true && false .
      """)

    assertTh("() -> Bool", ast.function("main"))
  }

  test("AndOr(fail): types mismatch") {
    assertThrows[TCE.TypeMismatch] {
      astForCode(
        """
         def main =
           1 || false .
      """)
    }
  }
}
