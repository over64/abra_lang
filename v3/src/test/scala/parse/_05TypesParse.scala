package parse

import grammar.M2Parser
import m3.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

import scala.collection.immutable.ArraySeq

/**
 * Created by over on 01.05.17.
 */
class _05TypesParse extends FunSuite {
  val parserType = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.`type`() }
  }

  import parserType._

  test("scalar") {
    withStr("type Int = native i32 .", ScalarDecl(false, ArraySeq.empty, "Int", "i32"))
    withStr("type Int32 = native i32 .", ScalarDecl(false, ArraySeq.empty, "Int32", "i32"))
    withStr("type I32Bar = ref native i8* .", ScalarDecl(true, ArraySeq.empty, "I32Bar", "i8*"))
    withStr("type FooBar = native [i32 x 5] .", ScalarDecl(false, ArraySeq.empty, "FooBar", "[i32 x 5]"))
  }

  test("struct") {
    withStr("type S = (x: Int)", StructDecl(ArraySeq(), "S", ArraySeq(FieldDecl(false, "x", ScalarTh(ArraySeq(), "Int", None, "prelude")))))
    withStr("type ArraySeq[t] = (length: Int, ptr: Ptr)", StructDecl(ArraySeq(GenericTh("t")), "ArraySeq", ArraySeq(
      FieldDecl(false, "length", ScalarTh(ArraySeq(), "Int", None, "prelude")),
      FieldDecl(false, "ptr", ScalarTh(ArraySeq(), "Ptr", None, "test"))
    )))
  }

  test("union") {
    withStr("type StringOpt = String | None", UnionDecl(ArraySeq(), "StringOpt", ArraySeq(
      ScalarTh(ArraySeq(), "String", None, "prelude"),
      ScalarTh(ArraySeq(), "None", None, "prelude"))))

    withStr("type U2 = String | None | Bool", UnionDecl(ArraySeq(), "U2", ArraySeq(
      ScalarTh(ArraySeq(), "String", None, "prelude"),
      ScalarTh(ArraySeq(), "None", None, "prelude"),
      ScalarTh(ArraySeq(), "Bool", None, "prelude"))))

    // Disabled now
    //    withStr("type Option[t] = t | None", UnionDecl(ArraySeq(GenericTh("t")), "Option", ArraySeq(
    //      GenericTh("t"),
    //      ScalarTh(ArraySeq(), "None", ArraySeq.empty)
    //    )))
  }
}