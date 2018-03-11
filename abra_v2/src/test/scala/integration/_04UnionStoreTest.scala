package integration

import org.scalatest.FunSuite

class _04UnionStoreTest extends FunSuite with IntegrationUtil {
  test("store union member -> union") {
    assertCodeEquals(
      """
        type None = llvm void .
        type String = ref llvm i8* .
        type Int = llvm i32 .

        type IntOrString = Int | String

        def main =
          x: IntOrString = 'hi'
          x = 42
          42 .
      """, exit = Some(42))
  }


  test("store anon union -> another anon union") {
    assertCodeEquals(
      """
        type Bool = llvm i8 .
        type Int = llvm i32 .
        type String = ref llvm i8* .
        type U1 = String | Int
        type U2 = Bool | Int | String

        def main =
          x: Int | String = 42
          y: Bool | Int | String = x # check declaration
          y = x     # check store
          42 .
      """, exit = Some(42))
  }

  test("store anon union -> compatible union") {
    assertCodeEquals(
      """
        type Bool = llvm i8 .
        type Int = llvm i32 .
        type String = ref llvm i8* .
        type U1 = String | Int
        type U2 = Bool | Int | String

        def main =
          x: Int | String = 42
          y: U2 = x # check declaration
          y = x     # check store
          42 .
      """, exit = Some(42))
  }


  test("store union -> same type union") {
    assertCodeEquals(
      """
        type None = llvm void .
        type String = ref llvm i8* .
        type Int = llvm i32 .

        type IntOrString = Int | String

        def main =
          x: IntOrString = 'hi'
          y: IntOrString = x
          42 .
      """, exit = Some(42))
  }
}

