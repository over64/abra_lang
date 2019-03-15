package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _00TypeDeclTest extends FunSuite {
  test("type decl(fail): builtin redeclaration") {
    assertThrows[TCE.BuiltinTypeRedeclare] {
      astForCode(
        """
        type Int = llvm i32 .
        type String = ref llvm i32 .
      """)
    }
  }

  test("type decl: native") {
    astForCode(
      """
        type Ptr = llvm i8* .
        type PtrPtr = ref llvm i8* .
      """)
  }

  test("type decl: struct") {
    astForCode(
      """
        type Vec2 = (x: Int, y: Int)
      """)
  }

  test("type decl: struct with unknown field type (fail)") {
    assertThrows[TCE.NoSuchType] {
      astForCode(
        """
        type Vec2 = (x: Foo, y: Bar)
      """)
    }
  }

  test("type decl: struct with unknown field generic type (fail)") {
    assertThrows[TCE.NoSuchParameter] {
      astForCode(
        """
        type A[a]= (x: b, y: b)
      """)
    }
  }

  test("type decl: struct with self reference") {
    astForCode(
      """
        type Node = (value: Int, next: Node | None)
      """)
  }

  test("type decl: struct field with params") {
    astForCode(
      """
        type A[a] = (value: a)
        type B[a, b] = (val1: A[a], val2: b)
      """)
  }

  test("type decl: struct field with params count mismatch (fail)") {
    assertThrows[TCE.ParamsCountMismatch] {
      astForCode(
        """
        type A[a] = (value: a)
        type B[a, b] = (value: A[a, b])
      """)
    }
  }

  test("type decl: union with duplicated members (fail)") {
    assertThrows[TCE.UnionMembersNotUnique] {
      astForCode(
        """
        type A[a] = (value: a)
        type U = A[Int] | A[Int]
      """)
    }
  }
}

