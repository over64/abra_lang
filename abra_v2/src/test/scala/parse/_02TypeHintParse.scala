package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
  * Created by over on 29.04.17.
  */
class _02TypeHintParse extends FunSuite {
  val parser = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.typeHint() }
  }

  import parser._

  test("scalar") {
    withStr("Int", ScalarTh(Seq.empty, "Int", None))
    withStr("Map[K, V]", ScalarTh(Seq(
      ScalarTh(Seq.empty, "K", None),
      ScalarTh(Seq.empty, "V", None)),
      "Map", None))
  }

  test("fn") {
    withStr(" -> None", FnTh(closure = Seq.empty, args = Seq.empty, ScalarTh(Seq.empty, "None", None)))
    withStr("\\Int -> None", FnTh(
      closure = Seq.empty,
      args = Seq(ScalarTh(Seq.empty, "Int", None)),
      ret = ScalarTh(Seq.empty, "None", None)))

    withStr(" -> None", FnTh(
      closure = Seq.empty,
      args = Seq.empty,
      ret = ScalarTh(Seq.empty, "None", None)))

    withStr("\\Int, Float -> None", FnTh(
      closure = Seq.empty,
      args = Seq(
        ScalarTh(Seq.empty, "Int", None),
        ScalarTh(Seq.empty, "Float", None)),
      ret = ScalarTh(Seq.empty, "None", None)))
  }

  test("struct") {
    withStr("(x: Int, y: Seq[Int])", StructTh(Seq(
      FieldTh("x", ScalarTh(Seq.empty, "Int", None)),
      FieldTh("y", ScalarTh(Seq(ScalarTh(Seq.empty, "Int", None)), "Seq", None)))))
  }

  test("union") {
    withStr("Int | Float", UnionTh(Seq(
      ScalarTh(Seq.empty, "Int", None),
      ScalarTh(Seq.empty, "Float", None)
    )))
  }
}
