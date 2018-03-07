package integration

import org.scalatest.FunSuite

class _13OrAndTest extends FunSuite with IntegrationUtil {
  val common =
    """
      llvm declare i32 @puts(i8*) .

      type None = llvm void .
      ref type String = llvm i8* .
      type Bool = llvm i8 .

      def print = self: String -> llvm
        %1 = call i32 @puts(i8* %self)
        ret void .None

      def exprTrue = print('expr true'); true .
      def exprFalse = print('expr false'); false .

    """

  test("boolean or expr test: true true") {
    assertCodeEquals(
      common + """def main = exprTrue() || exprTrue() .""",
      exit = Some(1), stdout = Some("expr true"))
  }

  test("boolean or expr test: true false") {
    assertCodeEquals(
      common + """def main = exprTrue() || exprFalse() .""",
      exit = Some(1), stdout = Some("expr true"))
  }

  test("boolean or expr test: false true") {
    assertCodeEquals(
      common + """def main = exprFalse() || exprTrue() .""",
      exit = Some(1), stdout = Some("expr falseexpr true"))
  }

  test("boolean or expr test: false false") {
    assertCodeEquals(
      common + """def main = exprFalse() || exprFalse() .""",
      exit = Some(0), stdout = Some("expr falseexpr false"))
  }

  ////////////////  AND  //////////////////////////

  test("boolean and expr test: true true") {
    assertCodeEquals(
      common + """def main = exprTrue() && exprTrue() .""",
      exit = Some(1), stdout = Some("expr trueexpr true"))
  }

  test("boolean and expr test: true false") {
    assertCodeEquals(
      common + """def main = exprTrue() && exprFalse() .""",
      exit = Some(0), stdout = Some("expr trueexpr false"))
  }

  test("boolean and expr test: false true") {
    assertCodeEquals(
      common + """def main = exprFalse() && exprTrue() .""",
      exit = Some(0), stdout = Some("expr false"))
  }

  test("boolean and expr test: false false") {
    assertCodeEquals(
      common + """def main = exprFalse() && exprFalse() .""",
      exit = Some(0), stdout = Some("expr false"))
  }
}