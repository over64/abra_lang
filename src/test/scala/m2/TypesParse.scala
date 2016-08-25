package m2

import grammar2.M2Parser
import lang_m2.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

class TypesParse extends FunSuite {
  val scalarTypeParser = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.scalarType() }
  }

  test("parse scalar type") {
    import scalarTypeParser._
    withInput("type Int = llvm { i32 }", ScalarType("Int", "i32"))
    withInput("type SimdVec4f = llvm  {   <4 x float>   } ", ScalarType("SimdVec4f", "<4 x float>"))
  }
}
