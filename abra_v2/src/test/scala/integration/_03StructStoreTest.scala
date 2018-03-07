package integration

import org.scalatest.FunSuite

class _03StructStoreTest extends FunSuite with IntegrationUtil {
  test("struct store") {
    assertCodeEquals(
      """
        type None = llvm void .
        type String = ref llvm i8* .
        type Int = llvm i32 .
        type Vec2 = (x: String, y: Int)

        def main =
          v = Vec2('i', 42)
          v.x = 'hellooooooooooooooooooooooooooooo'
          v.y .
      """, exit = Some(42))
  }
}
