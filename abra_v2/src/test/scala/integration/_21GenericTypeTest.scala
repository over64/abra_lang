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
}