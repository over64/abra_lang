package integration

import org.scalatest.FunSuite

class _16WhenTest extends FunSuite with IntegrationUtil {
  test("when expression") {
    assertCodeEquals(
      """
        llvm declare i32 @puts(i8*) .

        type Bool   = llvm i8 .
        type Int    = llvm i32 .
        type String = ref llvm i8* .
        type None   = llvm void .

        def print = self: String -> llvm
          %1 = call i32 @puts(i8* %self)
          ret void .None

        def main =
          x: Int | String | None = 'hello'
          y = when x
            is i: Int do i
            is s: String do print(s); 'world'
            is n: None do 42 .

          z = when y
            is i: Int do i
            is s: String do 13 .
          z .
      """, exit = Some(13))
  }
}