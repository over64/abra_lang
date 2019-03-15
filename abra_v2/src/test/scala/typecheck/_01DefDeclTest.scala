package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil.astForCode

class _01DefDeclTest extends FunSuite {
  test("def decl: trivial") {
    astForCode("""def main = .""")
  }

  test("def decl: trivial native") {
    astForCode(
      """
         def bar = llvm
           ; asm .Int
      """)
  }

  test("def decl: native - ret typehint required (fail)") {
    assertThrows[TCE.RetTypeHintRequired] {
      astForCode(
        """
         def bar = llvm
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

