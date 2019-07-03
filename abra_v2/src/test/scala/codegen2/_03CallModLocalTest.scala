package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _03CallModLocalTest extends FunSuite {

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

  test("call self def: operator") {
    compile(
      """
        def + = self: Int, other: Int do llvm
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def main =
          1 + 1 .
      """)
  }

  test("call self def: get") {
    compile(
      """
        def get = self: Int, idx: Int do idx .

        def main =
          x = 1
          x(42) .
      """)
  }

  test("call self def: set") {
    compile(
      """
        def set = self: Int, idx: Int, value: Int do value .

        def main =
          x = 1
          x(0) = 42 .
      """)
  }
}
