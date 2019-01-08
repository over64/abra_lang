package integration

import org.scalatest.FunSuite

class _24Collections  extends FunSuite with IntegrationUtil {
  test("array seq") {
    assertCodeEquals(
      """
        import
          /universe with None, Int, String
          /array with Array
          /iter .

        def main =
          array1: Array[Int] = array.mk(5, lambda i -> i)
          it = array1.map(lambda x -> x * 2).iterator()

          it.next()
          it.next()
          it.next()

          value = it.next() unless is None do -1 .
          value .
  """, exit = Some(6))
  }
}
