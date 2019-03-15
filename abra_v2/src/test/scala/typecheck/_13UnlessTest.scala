package typecheck

import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _13UnlessTest extends FunSuite {
  test("unless: simple") {
    val ast = astForCode(
      """
        def main =
          x: Int | String | None = 'hello'
          unless
            is s: String do 0
            is n: None do 42 ..
      """)

    assertTh("() -> Int | String", ast.function("main"))
  }
}
