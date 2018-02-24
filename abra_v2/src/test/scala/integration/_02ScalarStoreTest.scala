package integration

import org.scalatest.FunSuite

class _02ScalarStoreTest extends FunSuite with IntegrationUtil {
  test("scalar store") {
    assertCodeEquals(
      """
    type None = llvm void .
    ref type String = llvm i8* .
    type Int = llvm i32 .

    f bar = 42 .

    f main =
      s = 'hi'
      s = 'hii'
      x = 1
      ptr: -> Int = bar
      x .
  """, exit = Some(1))
  }
}