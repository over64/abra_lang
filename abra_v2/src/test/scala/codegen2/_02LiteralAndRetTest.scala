package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _02LiteralAndRetTest extends FunSuite {
  test("return literal") {
    compile(
      """
        def bLiteral = true .
        def iLiteral = 42 .
        def fLiteral = 3.14 .
        def dLiteral = 3.40282347E+39 .
        def sLiteral = 'hello' .
        def nLiteral = none .
      """)
  }

  test("plain old main!") {
    compile(
      """
        def main = 0 .
      """)
  }
}