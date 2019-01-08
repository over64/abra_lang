package integration

import org.scalatest.FunSuite

class _17UnlessTest extends FunSuite with IntegrationUtil {
  test("when expression") {
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

        def main =
          x: Int | String | None = 'hello'
          y = x unless
            is i: Int do i
            is s: String do print(s); 'world'
            is n: None do 42 .

          z = y unless
            is i: Int do i
            is s: String do print('haha'); 13 .
          z .
      """, exit = Some(13))

//    def main =
//      x: Int | String | None = 'hello'
//      y = x unless
//        is i: Int do i
//        is s: String do print(s); 'world'
//        is n: None do 42 .
//
//      z = y unless
//        is i: Int do i
//        is s: String do print('haha'); 13 .
//      z .
  }
}