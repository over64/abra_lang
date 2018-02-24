package integration

import org.scalatest.FunSuite

/**
  * Created by over on 23.10.17.
  */
class _01HelloWorldTest extends FunSuite with IntegrationUtil {
  test("hello world") {
    assertCodeEquals(
      """
        llvm declare i32 @puts(i8*) .
        
        type None = llvm void .
        ref type String = llvm i8* .
        type Int = llvm i32 .

        f print = self: String -> llvm
          %1 = call i32 @puts(i8* %self)
          ret void .None

        f main = 'hello, world'.print() .
      """, stdout = Some("hello, world"))
  }
}
