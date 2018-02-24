package integration

import org.scalatest.FunSuite

class _03StructStoreTest extends FunSuite with IntegrationUtil {
  test("struct store") {
    assertCodeEquals(
      """
        type None = llvm void .
        ref type String = llvm i8* .
        type Int = llvm i32 .
        type Vec2 = (x: String, y: Int)

        f main =
          v = Vec2('i', 42, bar)
          v.x = 'hellooooooooooooooooooooooooooooo'
          v.y .
      """, exit = Some(42))
  }
}
