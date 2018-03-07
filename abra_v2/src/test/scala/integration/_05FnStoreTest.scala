package integration

import org.scalatest.FunSuite

class _05FnStoreTest  extends FunSuite with IntegrationUtil {
  test("fn ptr store to local") {
    assertCodeEquals(
      """
    type None = llvm void .
    type Int = llvm i32 .

    def bar = 42 .
    def main =
      ptr: -> Int = bar
      1 .
  """, exit = Some(1))
  }
}