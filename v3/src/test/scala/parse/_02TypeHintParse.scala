package parse

import grammar.M2Parser
import m3.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

import scala.collection.immutable.ArraySeq

/**
 * Created by over on 29.04.17.
 */
class _02TypeHintParse extends FunSuite {
  val parser = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.typeHint() }
  }

  import parser._

  test("scalar") {
    withStr("Int", ScalarTh(ArraySeq.empty, "Int", None, "prelude"))
    withStr("Map[K, V]", ScalarTh(ArraySeq(
      ScalarTh(ArraySeq.empty, "K", None, "test"),
      ScalarTh(ArraySeq.empty, "V", None, "test")),
      "Map", None, "test"))
  }

  test("fn") {
    withStr("() -> None", FnTh(args = ArraySeq.empty, ScalarTh(ArraySeq.empty, "None", None, "prelude")))

    withStr("(Int) -> None", FnTh(
      args = ArraySeq(ScalarTh(ArraySeq.empty, "Int", None, "prelude")),
      ret = ScalarTh(ArraySeq.empty, "None", None, "prelude")))

    withStr("(Int, Float) -> None", FnTh(
      args = ArraySeq(
        ScalarTh(ArraySeq.empty, "Int", None, "prelude"),
        ScalarTh(ArraySeq.empty, "Float", None, "prelude")),
      ret = ScalarTh(ArraySeq.empty, "None", None, "prelude")))
  }

  test("struct") {
    withStr("(x: Int, y: Seq[Int])", StructTh(ArraySeq(
      FieldTh("x", ScalarTh(ArraySeq.empty, "Int", None, "prelude")),
      FieldTh("y", ScalarTh(ArraySeq(ScalarTh(ArraySeq.empty, "Int", None, "prelude")), "Seq", None, "test")))))
  }

  test("union") {
    withStr("Int | Float", UnionTh(ArraySeq(
      ScalarTh(ArraySeq.empty, "Int", None, "prelude"),
      ScalarTh(ArraySeq.empty, "Float", None, "prelude")
    )))
  }

  test("union with union") {
    withStr("(Int | None) | None", UnionTh(ArraySeq(
      UnionTh(ArraySeq(
        ScalarTh(ArraySeq.empty, "Int", None, "prelude"),
        ScalarTh(ArraySeq.empty, "None", None, "prelude")
      )),
      ScalarTh(ArraySeq.empty, "None", None, "prelude")
    )))
  }
}
