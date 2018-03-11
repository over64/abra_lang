package integration

import org.scalatest.FunSuite

class _08PassTest extends FunSuite with IntegrationUtil {
  test("pass local as arg") {
    assertCodeEquals(
      """
        type None        = llvm void .
        type String      = ref llvm i8* .
        type Int         = llvm i32 .
        type Vec2        = (x: Int, y: String)
        type IntOrString = Int | String

        def pass = x1: Int,
                 x2: String,
                 x3: (x: Int, y: String),
                 x4: Vec2,
                 x5: Int | String,
                 x6: IntOrString,
                 x7: Int | String,
                 x8: IntOrString,
                 x9: \Int, Int -> Int  ->
          .

        def + = self: Int, other: Int -> llvm
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def sum = x: Int, y: Int -> x + y .

        def main =
          x:  Int | String = 42
          x2: IntOrString  = 42
          pass(1, 'hi', (2, 'hello'), Vec2(3, 'hhh'), 'string from union', 42, x, x2, sum)
          42 .
      """, exit = Some(42))
  }

  test("literal -> union") {
    assertCodeEquals(
      """
        type None        = llvm void .
        type String      = ref llvm i8* .
        type Int         = llvm i32 .
        type U1 = Int | String

        def pass = x1: U1 -> .

        def main =
          pass('hi')
          42 .
      """, exit = Some(42))
  }

  test("literal -> anon union") {
    assertCodeEquals(
      """
        type None        = llvm void .
        type String      = ref llvm i8* .
        type Int         = llvm i32 .

        def pass = x1: Int | String -> .

        def main =
          pass('hi')
          42 .
      """, exit = Some(42))
  }

  test("pass union member -> union") {
    assertCodeEquals(
      """
        type None        = llvm void .
        type String      = ref llvm i8* .
        type Int         = llvm i32 .
        type U1 = Int | String

        def pass = x1: U1 -> .

        def main =
          x: String = 'hi'
          pass(x)
          42 .
      """, exit = Some(42))
  }

  test("pass anon union -> union") {
    assertCodeEquals(
      """
        type None        = llvm void .
        type String      = ref llvm i8* .
        type Int         = llvm i32 .
        type U1 = Int | String | None

        def pass = x1: U1 -> .

        def main =
          x: Int | String = 'hi'
          pass(x)
          42 .
      """, exit = Some(42))
  }

  test("pass union member -> anon union") {
    assertCodeEquals(
      """
        type None        = llvm void .
        type String      = ref llvm i8* .
        type Int         = llvm i32 .

        def pass = x1: Int | String | None -> .

        def main =
          x: String = 'hi'
          pass(x)
          42 .
      """, exit = Some(42))
  }

  test("pass anon union -> anon union") {
    assertCodeEquals(
      """
        type None        = llvm void .
        type String      = ref llvm i8* .
        type Int         = llvm i32 .

        def pass = x1: Int | String | None -> .

        def main =
          x: Int | String = 'hi'
          pass(x)
          42 .
      """, exit = Some(42))
  }
}