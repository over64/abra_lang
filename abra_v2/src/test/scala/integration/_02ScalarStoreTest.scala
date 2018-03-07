package integration

import org.scalatest.FunSuite

class _02ScalarStoreTest extends FunSuite with IntegrationUtil {
  test("scalar store") {
    assertCodeEquals(
      """
    type None   = llvm void .
    type String = ref llvm i8* .
    type Int    = llvm i32 .

    def bar = 42 .

    def main =
      s = 'hi'
      s = 'hii'
      x = 1
      ptr: -> Int = bar
      x .
  """, exit = Some(1))
  }
}