package integration

import org.scalatest.FunSuite

class _05FnStoreTest  extends FunSuite with IntegrationUtil {
  test("fn ptr store to local") {
    assertCodeEquals(
      """
    type None = llvm void .
    type Int = llvm i32 .

    f bar = 42 .
    f main =
      ptr: -> Int = bar
      1 .
  """, exit = Some(1))
  }
}