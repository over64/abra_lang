package integration

import org.scalatest.FunSuite

class _15IfTest extends FunSuite with IntegrationUtil {
  test("if do else test") {
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
          if true do
            print('hello')
            42
          else
            print('world')
            13 ..
      """, exit = Some(42))
  }

  test("if do else with one branch test") {
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
          if true do
            print('hello')
            42 .
          3 .
      """, exit = Some(3))
  }

  test("if do else returns ref test") {
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
          msg = if true do 'hello' else 'world' .
          print(msg)
          3 .
      """, exit = Some(3))
  }
}