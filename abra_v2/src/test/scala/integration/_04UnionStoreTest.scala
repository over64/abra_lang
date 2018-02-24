package integration

import org.scalatest.FunSuite

class _04UnionStoreTest extends FunSuite with IntegrationUtil {
  test("union store") {
    assertCodeEquals(
      """
        type None = llvm void .
        ref type String = llvm i8* .
        type Int = llvm i32 .

        type IntOrString = Int | String

        f main =
          x: IntOrString = 'hi'
          x = 42
          42 .
      """, exit = Some(42))
  }
}

