package integration

import org.scalatest.FunSuite

class _10ClosureLocalTest extends FunSuite with IntegrationUtil {
  test("closure and store scalar value") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .

        f main =
          x = 1
          (f x = 2)()
          f x = 3 .()
          x .
      """, exit = Some(3))
  }

  test("closure and store scalar ref") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        ref type String = llvm i8* .


        f main =
          x = 'hello'
          f x = 'world' .()
          3 .
      """, exit = Some(3))
  }

  test("closure and store struct value") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        type Vec2 = (x: Int, y: Int)


        f main =
          x = Vec2(1, 1)
          f x = Vec2(1, 3) .()
          x.y .
      """, exit = Some(3))
  }

  test("closure and store struct ref") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        ref type String = llvm i8* .

        type Vec2 = (x: String, y: Int)


        f main =
          x = Vec2('hello', 1)
          f x = Vec2('world', 3) .()
          x.y .
      """, exit = Some(3))
  }

  test("closure and store struct field ref") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        ref type String = llvm i8* .

        type Vec2 = (x: String, y: Int)


        f main =
          x = Vec2('hello', 3)
          f x.x = 'world' .()
          x.y .
      """, exit = Some(3))
  }

  test("closure and store struct field value") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        ref type String = llvm i8* .

        type Vec2 = (x: String, y: Int)


        f main =
          x = Vec2('hello', 3)
          f x.y = 4 .()
          x.y .
      """, exit = Some(4))
  }

  test("closure and store union") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        ref type String = llvm i8* .
        type U1 = Int | String

        f main =
          x: U1 = 'hello'
          f
            x = 'world'
            x = 42 .()
          4 .
      """, exit = Some(4))
  }
}