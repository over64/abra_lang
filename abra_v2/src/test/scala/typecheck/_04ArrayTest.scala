package typecheck

import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _04ArrayTest extends FunSuite {
  test("array: cons literal") {
    val ast = astForCode(
      """
           def main =
             array = Array[Byte](10) .
        """)

    val main = ast.function("main")
    assertTh("Array10[Byte]", main.varDecl("array"))
  }

  test("array: cons") {
    val ast = astForCode(
      """
           def main =
             iSize: Int   = 10
             lSize: Long  = 10
             sSize: Short = 10
             bSize: Byte  = 10

             Array[String](iSize)
             Array[String](lSize)
             Array[String](sSize)
             Array[String](bSize) .
        """)

    val main = ast.function("main")
    assertTh("() -> Array[String]", main)
  }

  test("array: len") {
    val ast = astForCode(
      """
           def main =
             size = 10
             array = Array[String](size)
             array.len() .
        """)

    val main = ast.function("main")
    assertTh("() -> Long", main)
  }


  test("array: get") {
    val ast = astForCode(
      """
           def main =
             iSize: Int   = 10
             lSize: Long  = 10
             sSize: Short = 10
             bSize: Byte  = 10

             array = Array[Byte](100)

             array(iSize)
             array(lSize)
             array(sSize)
             array(bSize) .
        """)

    val main = ast.function("main")
    assertTh("() -> Byte", main)
  }

  test("array: set") {
    val ast = astForCode(
      """
           def main =
             iSize: Int   = 10
             lSize: Long  = 10
             sSize: Short = 10
             bSize: Byte  = 10

             array = Array[Byte](100)

             array(iSize) = 0
             array(lSize) = 0
             array(sSize) = 0
             array(bSize) = 0 .
        """)

    val main = ast.function("main")
    assertTh("() -> None", main)
  }
}
