package integration

import org.scalatest.FunSuite

class _25Collections  extends FunSuite with IntegrationUtil {
  test("array seq") {
    assertCodeEquals(
      """
        import
          /universe with None, Int, String
          /array with Array
          /iter .

        def main =
          array1 = array.mk(5, lambda i -> i)
          lazyComp = array1
            .map(lambda x -> x * 2)
            .filter(lambda x -> x mod 2 == 0)

          # Filter[Map[Array[Int], Int, Float], Float]
          # ------------->

          it = lazyComp.iterator()

          # FilterIter[MapIter[ArrayIter[Int], Int, Float], Float]
          # ------------->

          it.next()
          it.next()
          it.next()

          value = it.next() unless is None do -1 .
          value .
  """, exit = Some(6))
  }
}
