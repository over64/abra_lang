package integration

import org.scalatest.FunSuite

class _17ReturnTest extends FunSuite with IntegrationUtil {
  test("return from if branch") {
    assertCodeEquals(
      """
        llvm declare i32 @puts(i8*) .

        type Bool   = llvm i8 .
        type Int    = llvm i32 .
        type String = ref llvm i8* .
        type None   = llvm void .

        def print = s: String do llvm
         %1 = call i32 @puts(i8* %s)
         ret void .None

        def some =
          if true do return 'hello'
          else 3 .

          4 .

        def main =
          some()
          13 .
      """, exit = Some(13))
  }
}