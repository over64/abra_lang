package integration

import org.scalatest.FunSuite

class _07AnonUnionStore extends FunSuite with IntegrationUtil {
  test("anon union store") {
    assertCodeEquals(
      """
        type None = llvm void .
        ref type String = llvm i8* .
        type Int = llvm i32 .

        f main =
          x: Int | String = 'hi'
          x = 42
          42 .
      """, exit = Some(42))
  }
}