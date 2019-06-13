package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _06CallModLocalTest extends FunSuite {

  test("call def") {
    compile(
      """
        def bar = x1: Int, x2: Int do
          42 .

        def main =
          bar(1, 1) .
      """)
  }

  test("call self def") {
    compile(
      """
        def some = self: Int, x: Int do
          42 .

        def main =
          1.some(42) .
      """)
  }
}
