package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil.astNoPrelude

class _01DefDeclTest extends FunSuite {
  test("def decl: trivial") {
    astNoPrelude("""def main = .""")
  }

  test("def decl: trivial native") {
    astNoPrelude(
      """
         type Int = llvm i32 .
         def bar = llvm
           ; asm .Int
      """)
  }

  test("def decl: native - ret typehint required (fail)") {
    assertThrows[TCE.RetTypeHintRequired] {
      astNoPrelude(
        """
         def bar = llvm
           ; asm .
      """)
    }
  }

  test("def decl: incorrect arg typehint (fail)") {
    assertThrows[TCE.NoSuchType] {
      astNoPrelude(
        """
         def bar = x: None do .
      """)
    }
  }

  test("def decl: incorrect ret typehint (fail)") {
    assertThrows[TCE.NoSuchType] {
      astNoPrelude(
        """
         def bar = .None
      """)
    }
  }
}

