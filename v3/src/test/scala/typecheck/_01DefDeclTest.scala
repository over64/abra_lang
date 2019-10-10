package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _01DefDeclTest extends FunSuite {
  test("def decl: trivial") {
    val module = astForCode("""def main = .""")
    assertTh("() -> None", module.function("main"))
  }

  test("def decl: trivial returns none") {
    val module = astForCode("""def main = none .""")
    assertTh("() -> None", module.function("main"))
  }

  test("def decl: trivial native") {
    astForCode(
      """
         def bar = native
           ; asm .Int
      """)
  }

  test("def decl: native - ret typehint required (fail)") {
    assertThrows[TCE.RetTypeHintRequired] {
      astForCode(
        """
         def bar = native
           ; asm .
      """)
    }
  }

  test("def decl: incorrect arg typehint (fail)") {
    assertThrows[TCE.NoSuchType] {
      astForCode(
        """
         def bar = x: Foo do .
      """)
    }
  }

  test("def decl: incorrect ret typehint (fail)") {
    assertThrows[TCE.NoSuchType] {
      astForCode(
        """
         def bar = .Bar
      """)
    }
  }
}

