package integration

import org.scalatest.FunSuite

class _10ClosureLocalTest extends FunSuite with IntegrationUtil {
  test("closure and store scalar value") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int  = llvm i32 .

        def main =
          x = 1
          (lambda x = 2)()
          lambda x = 3 .()
          x .
      """, exit = Some(3))
  }

  test("closure and store scalar ref") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .


        def main =
          x = 'hello'
          lambda x = 'world' .()
          3 .
      """, exit = Some(3))
  }

  test("closure and store struct value") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int  = llvm i32 .
        type Vec2 = (x: Int, y: Int)


        def main =
          x = Vec2(1, 1)
          lambda x = Vec2(1, 3) .()
          x.y .
      """, exit = Some(3))
  }

  test("closure and store struct ref") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .
        type Vec2   = (x: String, y: Int)


        def main =
          x = Vec2('hello', 1)
          lambda x = Vec2('world', 3) .()
          x.y .
      """, exit = Some(3))
  }

  test("closure and store struct field ref") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .
        type Vec2   = (x: String, y: Int)


        def main =
          x = Vec2('hello', 3)
          lambda x.x = 'world' .()
          x.y .
      """, exit = Some(3))
  }

  test("closure and store struct field value") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .
        type Vec2   = (x: String, y: Int)


        def main =
          x = Vec2('hello', 3)
          lambda x.y = 4 .()
          x.y .
      """, exit = Some(4))
  }

  test("closure and store union") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .
        type U1     = Int | String

        def main =
          x: U1 = 'hello'
          lambda
            x = 'world'
            x = 42 .()
          4 .
      """, exit = Some(4))
  }
}