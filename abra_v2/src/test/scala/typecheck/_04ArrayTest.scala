package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _04ArrayTest extends FunSuite {
  test("array: cons") {
    val ast = astForCode(
      """
           def main =
             array1 = Array[Byte](10)
             array2 = Array(10, 20)
             .
        """)

    val main = ast.function("main")
    assertTh("Array1[Byte]", main.varDecl("array1"))
    assertTh("Array2[Int]", main.varDecl("array2"))
  }

  test("array: len") {
    val ast = astForCode(
      """
           def main =
             Array('hello', 'world').len() .
        """)

    val main = ast.function("main")
    assertTh("() -> Long", main)
  }


  test("array: get") {
    val ast = astForCode(
      """
           def main =
             Array(1, 2, 3)(0) .
        """)

    val main = ast.function("main")
    assertTh("() -> Int", main)
  }

  test("array: set") {
    val ast = astForCode(
      """
           def main =
             array = Array[Byte](1, 2, 3)
             array(0) = 0 .
        """)

    val main = ast.function("main")
    assertTh("() -> None", main)
  }

  test("array pass: arrayN as array") {
    val ast = astForCode(
      """
         def mkArray100 = llvm
           ; native .Array100[Byte]

         def bar = array: Array[Byte] do .

         def main =
           bar(mkArray100()) .
        """)

    val main = ast.function("main")
    assertTh("() -> None", main)
  }

  test("array pass(fail): array as arrayN") {
    assertThrows[TCE.TypeMismatch] {
      astForCode(
        """
         def mkArray = llvm
           ; native .Array[Byte]

         def bar = array: Array100[Byte] do .

         def main =
           bar(mkArray()) .
        """)
    }
  }
}
