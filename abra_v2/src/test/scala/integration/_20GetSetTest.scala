package integration

import org.scalatest.FunSuite

class _20GetSetTest extends FunSuite with IntegrationUtil {
  test("get & set") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int  = llvm i32 .
        type String = ref llvm i8* .

        def get = self: Int, idx: Int do
          42 .

        def set = self: Int, idx: Int, value: String do .

        def main =
          x = 13
          x(14) = 'xxx'
          x(100500) .
      """, exit = Some(42))
  }
}