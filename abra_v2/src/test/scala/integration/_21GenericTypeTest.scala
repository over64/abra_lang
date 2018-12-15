package integration

import org.scalatest.FunSuite

class _21GenericTypeTest extends FunSuite with IntegrationUtil {
  test("simplest array impl") {
    assertCodeEquals(
      """
        import
          /universe with None, Int, String
          /array .

        def main =
          array1 = array.mk(10, lambda i -> 0)
          array2 = array.mk(1000, lambda i -> 'hello')

          array1(1) = 13
          array2(1) = 'world'
          array1(1) .
  """, exit = Some(13))
  }

  test("vec") {
    assertCodeEquals(
      """
        import
          /universe with None, Int, String
          /vec with Vec .

        def main =
          v: Vec[Int] = vec.mk(1)
          v.push(1)
          v.push(2)
          v(1) .
  """, exit = Some(2))
  }

  test("simple alloca test") {
    assertCodeEquals(
      """
        import
          /universe with None, Int, String .

        def bar = x: Int do none .
        def main =
          x = 1
          bar(x)
          0 .
  """, exit = Some(0))
  }

  test("simple polimorphism") {
    assertCodeEquals(
      """
        import
          /universe with None, Int, String, Float .

        llvm
          @.printfInt = private constant [3 x i8] c"%d\00", align 1
          declare i32 @printf(i8*,...) .


        def iadd = x: Int, other: Int do llvm
          %1 = add nsw i32 %x, %other
          ret i32 %1 .Int

        def + = self: Int, other: num do
          iadd(self, other.toInt()) .

        def add = x: num1, y: num2 do
            x + y .

        def printInt = i: Int do llvm
          %format = bitcast [3 x i8]* @.printfInt to i8*
          call i32 (i8*,...) @printf(i8* %format, i32 %i)
          ret void .None

        def main =
          print('ввведите x целое')
          x = readInt()
          print(x) .

         #include <stdio.h>
         void main() {
            int x;
            scanf("%d", &x);
         }
  """, exit = Some(0))
  }
}