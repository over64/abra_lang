package integration

import org.scalatest.FunSuite

class _09ReturnTest extends FunSuite with IntegrationUtil {
  test("ret scalar value") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .

        f const = 42 .
        f main = const() .
      """, exit = Some(42))
  }

  test("ret scalar ref") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        ref type String = llvm i8* .

        f const = 'hello, abra-kadabra!' .
        f main = const(); 42 .
      """, exit = Some(42))
  }

  test("ret struct value") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        type Vec2 = (x: Int, y: Int)

        f const = Vec2(1, 42) .
        f main = const().y .
      """, exit = Some(42))
  }

  test("ret struct ref") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        ref type String = llvm i8* .
        type Vec2 = (x: String, y: Int)

        f const = Vec2('hi', 42) .
        f main = const().y .
      """, exit = Some(42))
  }

  test("ret struct anon") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .

        f const = (42, 13) .(x: Int, y: Int)
        f main = const().y .
      """, exit = Some(13))
  }

  test("ret union") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        ref type String = llvm i8* .
        type U1 = Int | String

        f const = 'hi' .U1
        f main = const(); 13 .
      """, exit = Some(13))
  }

  test("ret union anon") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        ref type String = llvm i8* .

        f const = 'hi' .Int | String
        f main = const(); 13 .
      """, exit = Some(13))
  }

  test("ret function pointer") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int = llvm i32 .
        ref type String = llvm i8* .

        f bar = 42 .
        f const = bar .
        f main = const(); 13 .
      """, exit = Some(13))
  }
}
