package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _00TypeDeclTest extends FunSuite {
  test("type decl: native") {
    astNoPrelude(
      """
        type Int = llvm i32 .
        type String = ref llvm i32 .
      """)
  }

  test("type decl: struct") {
    astNoPrelude(
      """
        type Int = llvm i32 .
        type Vec2 = (x: Int, y: Int)
      """)
  }

  test("type decl: struct with unknown field type (fail)") {
    assertThrows[TCE.NoSuchType] {
      astNoPrelude(
        """
        type Int = llvm i32 .
        type Vec2 = (x: Int, y: String)
      """)
    }
  }

  test("type decl: struct with unknown field generic type (fail)") {
    assertThrows[TCE.NoSuchParameter] {
      astNoPrelude(
        """
        type A[a]= (x: b, y: b)
      """)
    }
  }

  test("type decl: struct with self reference") {
    astNoPrelude(
      """
        type None = llvm void .
        type Int = llvm i32 .
        type Node = (value: Int, next: Node | None)
      """)
  }

  test("type decl: struct field with params") {
    astNoPrelude(
      """
        type Int = llvm i32 .
        type A[a] = (value: a)
        type B[a, b] = (val1: A[a], val2: b)
      """)
  }

  test("type decl: struct field with params count mismatch (fail)") {
    assertThrows[TCE.ParamsCountMismatch] {
      astNoPrelude(
        """
        type Int = llvm i32 .
        type A[a] = (value: a)
        type B[a, b] = (value: A[a, b])
      """)
    }
  }

  test("type decl: union with duplicated members (fail)") {
    assertThrows[TCE.UnionMembersNotUnique] {
      astNoPrelude(
        """
        type Int = llvm i32 .
        type A[a] = (value: a)
        type U = A[Int] | A[Int]
      """)
    }
  }
}

