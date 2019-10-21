package parse

import grammar.M2Parser
import m3.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
 * Created by over on 01.05.17.
 */
class _05TypesParse extends FunSuite {
  val parserType = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.`type`() }
  }

  import parserType._

  test("scalar") {
    withStr("type Int = native i32 .", ScalarDecl(false, Seq.empty, "Int", "i32"))
    withStr("type Int32 = native i32 .", ScalarDecl(false, Seq.empty, "Int32", "i32"))
    withStr("type I32Bar = ref native i8* .", ScalarDecl(true, Seq.empty, "I32Bar", "i8*"))
    withStr("type FooBar = native [i32 x 5] .", ScalarDecl(false, Seq.empty, "FooBar", "[i32 x 5]"))
  }

  test("struct") {
    withStr("type S = (x: Int)", StructDecl(Seq(), "S", Seq(FieldDecl(false, "x", ScalarTh(Seq(), "Int", None)))))
    withStr("type Seq[t] = (length: Int, ptr: Ptr)", StructDecl(Seq(GenericTh("t")), "Seq", Seq(
      FieldDecl(false, "length", ScalarTh(Seq(), "Int", None)),
      FieldDecl(false, "ptr", ScalarTh(Seq(), "Ptr", None))
    )))
  }

  test("union") {
    withStr("type StringOpt = String | None", UnionDecl(Seq(), "StringOpt", Seq(
      ScalarTh(Seq(), "String", None),
      ScalarTh(Seq(), "None", None))))

    withStr("type U2 = String | None | Bool", UnionDecl(Seq(), "U2", Seq(
      ScalarTh(Seq(), "String", None),
      ScalarTh(Seq(), "None", None),
      ScalarTh(Seq(), "Bool", None))))

    // Disabled now
    //    withStr("type Option[t] = t | None", UnionDecl(Seq(GenericTh("t")), "Option", Seq(
    //      GenericTh("t"),
    //      ScalarTh(Seq(), "None", Seq.empty)
    //    )))
  }
}