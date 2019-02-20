package typecheck

import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _04PassPrimitiveTest extends FunSuite {
  test("pass simple main") {
    astPrint(astForCode("def main = 42 ."))
  }
}
