package integration

import org.scalatest.FunSuite

class _09ResultTypeTest extends FunSuite with IntegrationUtil {
  test("ret scalar value") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int  = llvm i32 .

        def const = 42 .
        def main = const() .
      """, exit = Some(42))
  }

  test("ret scalar ref") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .

        def const = 'hello, abra-kadabra!' .
        def main = const(); 42 .
      """, exit = Some(42))
  }

  test("ret struct value") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int  = llvm i32 .
        type Vec2 = (x: Int, y: Int)

        def const = Vec2(1, 42) .
        def main = const().y .
      """, exit = Some(42))
  }

  test("ret struct ref") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .
        type Vec2   = (x: String, y: Int)

        def const = Vec2('hi', 42) .
        def main = const().y .
      """, exit = Some(42))
  }

  test("ret struct anon") {
    assertCodeEquals(
      """
        type None = llvm void .
        type Int  = llvm i32 .

        def const = (42, 13) .(x: Int, y: Int)
        def main = const().y .
      """, exit = Some(13))
  }

  test("ret union") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .
        type U1     = Int | String

        def const = 'hi' .U1
        def main = const(); 13 .
      """, exit = Some(13))
  }

  test("ret union anon") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .

        def const = 'hi' .Int | String
        def main = const(); 13 .
      """, exit = Some(13))
  }

  test("ret function pointer") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .

        def bar = 42 .
        def const = bar .
        def main = const(); 13 .
      """, exit = Some(13))
  }
}
