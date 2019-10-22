package typecheck

import m3._02typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _03TupleLiteralTest extends FunSuite {
  test("tuple literals: default") {
    val ast = astForCode(
      """
         def main =
           value = (10, 'haha') .
      """)

    val main = ast.function("main")
    assertTh("(x0: Int, x1: String)", main.varDecl("value"))
  }

  test("tuple literals: named type hint") {
    val ast = astForCode(
      """
         def main =
           value: (age: Int, name: String) = (10, 'haha') .
      """)

    val main = ast.function("main")
    assertTh("(age: Int, name: String)", main.varDecl("value"))
  }

  test("tuple literals (fail): type mismatch") {
    assertThrows[TCE.TypeMismatch] {
      astForCode(
        """
         def main =
           value: (age: Int, name: String) = (10, false) .
      """)
    }
  }
}
