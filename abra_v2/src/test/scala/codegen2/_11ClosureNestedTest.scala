package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _11ClosureNestedTest extends FunSuite {
  test("closure nested: local") {
    compile(
      """
        def main =
          x1 = 0

          lambda
            lambda
              x1 = 42
              x1 .()
            .()
          x1 .
      """)
  }

  test("closure nested: param") {
    compile(
      """
        def some = x: String | None do
          lambda
            lambda
              y = x
              x .()
          .()
        .

        def main =
          some('hello')
          42 .
      """)
  }
}