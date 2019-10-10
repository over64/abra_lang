package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _02LiteralTest extends FunSuite {
  test("literal expr") {
    val ast = astForCode(
      """
         def main =
           none
           true
           false
           3.14
           'hello'
           2 .
      """)

    assertTh("() -> Int", ast.function("main"))
  }

  test("integer type by range select") {
    val ast = astForCode(
      """
         def main =
           x = 42 -- Int
           intMax = 2147483647 -- Int
           y = 2147483648 -- Long
           longMax = 9223372036854775807 -- Long
           .
      """)

    val main = ast.function("main")
    assertTh("Int", main.varDecl("x"))
    assertTh("Int", main.varDecl("intMax"))
    assertTh("Long", main.varDecl("y"))
    assertTh("Long", main.varDecl("longMax"))
  }

  test("integer type typehint") {
    val ast = astForCode(
      """
         def main =
           a: Byte  = 10
           b: Short = 10
           c: Int = 10
           d: Long = 10 .
      """)

    val main = ast.function("main")
    assertTh("Byte", main.varDecl("a"))
    assertTh("Short", main.varDecl("b"))
    assertTh("Int", main.varDecl("c"))
    assertTh("Long", main.varDecl("d"))
  }

  test("long overflow") {
    assertThrows[TCE.IntegerLiteralOutOfRange] {
      astForCode(
        """
         def main =
           longOverflow = 9223372036854775808 .
      """)
    }
  }

  test("long underflow") {
    assertThrows[TCE.IntegerLiteralOutOfRange] {
      astForCode(
        """
         def main =
           longUnderflow = -9223372036854775809 .
      """)
    }
  }

  test("int overflow") {
    assertThrows[TCE.IntegerLiteralOutOfRange] {
      astForCode(
        """
         def main =
           intOverflow: Int = 2147483648 .
      """)
    }
  }

  test("int underflow") {
    assertThrows[TCE.IntegerLiteralOutOfRange] {
      astForCode(
        """
         def main =
           intUnderflow: Int = -2147483649 .
      """)
    }
  }

  test("short overflow") {
    assertThrows[TCE.IntegerLiteralOutOfRange] {
      astForCode(
        """
         def main =
           shortOverflow: Short = 32768 .
      """)
    }
  }

  test("short underflow") {
    assertThrows[TCE.IntegerLiteralOutOfRange] {
      astForCode(
        """
         def main =
           shortUnderflow: Short = -32769 .
      """)
    }
  }

  test("byte overflow") {
    assertThrows[TCE.IntegerLiteralOutOfRange] {
      astForCode(
        """
         def main =
           byteOverflow: Byte = 128 .
      """)
    }
  }

  test("byte underflow") {
    assertThrows[TCE.IntegerLiteralOutOfRange] {
      astForCode(
        """
         def main =
           byteUnderflow: Byte = -129 .
      """)
    }
  }

  test("float type by range select") {
    val ast = astForCode(
      """
         def main =
           x = 1.0
           floatMax = 3.40282347E+38
           y = 3.40282347E+39
           doubleMax = 1.79769313486231570E+308 .
      """)

    val main = ast.function("main")
    assertTh("Float", main.varDecl("x"))
    assertTh("Float", main.varDecl("floatMax"))
    assertTh("Double", main.varDecl("y"))
    assertTh("Double", main.varDecl("doubleMax"))
  }

  test("float overflow") {
    assertThrows[TCE.FloatingLiteralOutOfRange] {
      astForCode(
        """
         def main =
           floatOverflow: Float = 3.40282347E+39 .
      """)
    }
  }

  test("float underflow") {
    assertThrows[TCE.FloatingLiteralOutOfRange] {
      astForCode(
        """
         def main =
           floatUnderflow: Float = -3.40282347E+39 .
      """)
    }
  }

  test("double overflow") {
    assertThrows[TCE.FloatingLiteralOutOfRange] {
      astForCode(
        """
         def main =
           doubleOverflow: Double = 1.79769313486231570E+309 .
      """)
    }
  }

  test("double underflow") {
    assertThrows[TCE.FloatingLiteralOutOfRange] {
      astForCode(
        """
         def main =
           doubleUnderflow: Double = -1.79769313486231570E+309 .
      """)
    }
  }
}
