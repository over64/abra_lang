package integration

import org.scalatest.FunSuite

class _06AnonStructStoreTest extends FunSuite with IntegrationUtil {
  test("anon struct store") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type String = ref llvm i8* .
        type Int    = llvm i32 .

        def main =
          vec: (x: String, y: Int) = ('hi', 42)
          vec.x = 'hello'
          vec.y .
      """, exit = Some(42))
  }
}