package codegen2

import org.scalatest.FunSuite

class _11ClosureNestedTest extends FunSuite {
  test("closure nested: local") {
    CodeGenUtil.run(
      """
        def main =
          x1 = 0

          lambda
            lambda
              x1 = 42
              x1 .()
            .()
          x1 .
      """, exitCode = 42)
  }

  test("closure nested: param") {
    CodeGenUtil.run(
      """
        def some = x: String | None do
          lambda
            lambda
              y = x
              x .()
          .()
        .

        def main =
          some('hello') # no move analysis, so poor performance
          42 .
      """, exitCode = 42)
  }
}