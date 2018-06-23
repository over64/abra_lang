package integration

import org.scalatest.FunSuite

class _24Collections  extends FunSuite with IntegrationUtil {
  test("array seq") {
    assertCodeEquals(
      """
        import
          /universe with None, Int, String
          /array
          /iter .

        def main =
          array1 = array.mk[Int](5, lambda i: Int -> i)
          it = array1.map(lambda x: Int -> x * 2).iterator()
          it.next()
          it.next()
          it.next() .
  """, exit = Some(4))
  }
}
