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
          # must be like this when type infer will become smarter
          # array1 = array.mk(5, lambda i -> 0)
          # array2 = array.mk(5, lambda i -> 'hello')

          array1 = array.mk[Int](5, lambda i: Int -> 0)
          array2 = array.mk[String](1000, lambda i: Int -> 'hello')

          array1(1) = 13
          array2(1) = 'world'
          array1(1) .
  """, exit = Some(13))
  }
}