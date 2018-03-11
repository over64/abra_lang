package integration

import org.scalatest.FunSuite

class _00LowDeclTest extends FunSuite with IntegrationUtil {
  test("low function definition") {
    assertCodeEquals(
      """
        type Int = llvm i32 .

        def + = self: Int, other: Int -> llvm
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def main = 1 + 1 .
      """, exit = Some(2))
  }


  test("literals declaration") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Bool   = llvm i8 .
        type Int    = llvm i32 .
        type Float  = llvm float .
        type String = ref llvm i8* .

        def main =
          none
          true
          false
          3.14
          'hello'
          2 .
      """, exit = Some(2))
  }
}
