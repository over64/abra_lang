package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _04ArrayTest extends FunSuite {
  test("array pass: arrayN as array") {
    val ast = astForCode(
      """
         def mkArray100 = native
           ; asm .Array100[Byte]

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
         def mkArray = native
           ; asm .Array[Byte]

         def bar = array: Array100[Byte] do .

         def main =
           bar(mkArray()) .
        """)
    }
  }
}
