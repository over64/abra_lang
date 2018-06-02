package typecheck

import m3.parse.Ast0._
import m3.typecheck.Namespace
import org.scalatest.FunSuite

import scala.collection.mutable

class ArgsInferTest extends FunSuite {
  val tInt = ScalarDecl("universe", ref = false, Seq(), "Int", "i32")
  val tString = ScalarDecl("universe", ref = true, Seq(), "String", "i8*")
  val tBool = ScalarDecl("universe", ref = true, Seq(), "Bool", "i8")

  val gT = GenericType("T")
  val gU = GenericType("U")

  val tU1 = UnionDecl("universe", Seq(gT, gU), "U1", Seq(thInt, thT, thU))

  val thInt = ScalarTh(Seq.empty, "Int", mod = None)
  val thT = ScalarTh(Seq.empty, "T", mod = None)
  val thU = ScalarTh(Seq.empty, "U", mod = None)


//  test("infer call args as union compatible") {
  //    // type U1[T, U] = Int | T | U
  //    // def bar[T, U] = x: U1[T, U], y: U1[T, U], z: U1[T, U] do .
  //    // def main =
  //    //   bar(42, 's', true) . # must be infered as bar[String, Bool]
  //    val ns = new Namespace("", Seq.empty, Seq.empty, Seq(
  //      tInt, tString, tBool, tU1
  //    ), Map())
  //
  //    val specMap = mutable.HashMap[GenericType, TypeHint](
  //      gT -> ScalarTh(Seq.empty, "T*", None),
  //      gU -> ScalarTh(Seq.empty, "U*", None))
  //
  //    assert(ns.checkAndInfer(specMap,
  //      expected = ScalarTh(Seq(ScalarTh(Seq.empty, "T*", pkg = None), ScalarTh(Seq.empty, "U*", pkg = None)), "U1", None),
  //      has = thInt) === true)
  //
  //  }

}
