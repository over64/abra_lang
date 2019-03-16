package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _13UnlessTest extends FunSuite {
  test("unless: simple") {
    val ast = astForCode(
      """
        def main =
          x: Int | String | None = 'hello'
          x unless
            is s: String do 'haha'
            is n: None do 42 ..
      """)

    assertTh("() -> Int | String", ast.function("main"))
  }

  test("unless: one overall type") {
    val ast = astForCode(
      """
        def main =
          x: Int | String = 'hello'
          x unless
            is i: Int do 1
            is s: String do 0 ..
      """)

    assertTh("() -> Int", ast.function("main"))
  }

  test("unless (fail): case already covered") {
    assertThrows[TCE.CaseAlreadyCovered] {
      astForCode(
        """
        def main =
          x: Int | String | None = 'hello'
          x unless
            is s: String do 'haha'
            is s: String do 'hihi' ..
      """)
    }
  }

  test("unless (fail): expected union type") {
    assertThrows[TCE.ExpectedUnionType] {
      astForCode(
        """
        def main =
          x = 'hello'
          x unless
            is s: String do 'haha'
            is s: String do 'hihi' ..
      """)
    }
  }

  test("unless (fail): is type out of variants") {
    assertThrows[TCE.UnlessExpectedOneOf] {
      astForCode(
        """
        def main =
          x: Int | String = 'hello'
          x unless
            is None do 'haha'
            is s: String do 'hihi' ..
      """)
    }
  }
}
