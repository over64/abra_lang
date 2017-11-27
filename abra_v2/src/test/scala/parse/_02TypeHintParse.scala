package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
  * Created by over on 29.04.17.
  */
class _02TypeHintParse extends FunSuite {
  val parser = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.typeHint() }
  }

  import parser._

//  test("scalar") {
//    withStr("Int", ScalarTh(None, "Int", ""))
//    withStr("*Int", ScalarTh(None, "Int", ""))
//    withStr("Map[K, V]", ScalarTh(Some(TypeParams(Seq(
//      ScalarTh(None, "K", ""),
//      ScalarTh(None, "V", "")))),
//      "Map", ""))
//  }
//
//  test("fn") {
//    withStr("() -> None", FnTh(Seq(), ScalarTh(None, "None", "")))
//    withStr("(Int) -> None", FnTh(
//      args = Seq(ScalarTh(None, "Int", "")),
//      ret = ScalarTh(None, "None", "")))
//
//    withStr("(Int, Float) -> None", FnTh(
//      args = Seq(
//        ScalarTh(None, "Int", ""),
//        ScalarTh(None, "Float", "")),
//      ret = ScalarTh(None, "None", "")))
//  }
//
////  test("struct") {
////    withStr("(x: Int, y: Seq[Int])", StructTh(Seq(
////      ScalarTh(None, "Int", ""),
////      ScalarTh(Seq(ScalarTh("Int")), "Seq", ""))))
////  }
//
//  test("union") {
//    withStr("Int | Float", UnionTh(Seq(
//      ScalarTh(None, "Int", ""),
//      ScalarTh(None, "Float", "")
//    )))
//  }
}
