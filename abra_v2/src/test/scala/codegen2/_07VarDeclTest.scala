package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _07VarDeclTest extends FunSuite {
  test("var decl") {
    compile(
      """
        def main =
          x0 = 42
          x1 = 'hello'
          x2 = (1, 1)
          x3 = (1, 'world')
          x4: Int | None = 42
          x5: Int | None = none
          x6: Int | String | None = 'hello'
          x7: Int | String | None = none
          x8: String | None = 'hello'
          x9: String | None = none .
      """)
  }
}
