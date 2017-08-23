package codegen

import org.scalatest.{FunSuite, Matchers}
import m3.codegen.Ast1._

/**
  * Created by over on 29.06.17.
  */
class SpecTest extends FunSuite with Matchers {
  test("specs for struct") {
    val T = 'T.virt
    val A = 'A.virt
    val B = 'B.virt

    val tInt = Scalar("i32")
    val tFloat = Scalar("float")
    val tBoolean = Scalar("i8")

    // type S[T] = (x: T, y: Int)
    // type Pair[A, B] = (a: S[A], b: S[B])
    val tS = Struct(T)('S, 'x is T, 'y is tInt)
    val tPair = Struct(A, B)('Pair, 'a is tS(A), 'b is tS(B))
    val tPairFloatBoolean = tPair(tFloat, tBoolean)
    val expected = new Struct(Seq(tFloat, tBoolean), "Pair", Seq(
      Field("a", new Struct(Seq(tFloat), "S", Seq(Field("x", tFloat), Field("y", tInt)))),
      Field("b", new Struct(Seq(tBoolean), "S", Seq(Field("x", tBoolean), Field("y", tInt))))
    ))

    assert(tPairFloatBoolean === expected)
  }

  test("specs for union") {
    val T = 'T.virt
    val A = 'A.virt
    val B = 'B.virt

    val tInt = Scalar("i32")
    val tFloat = Scalar("float")
    val tBoolean = Scalar("i8")

    // type U1[T] = Float | T
    // type U2[A, B] = Int | U1[A] | A | B
    val tU1 = Union(T)('U1, tFloat, T)
    val tU2 = Union(A, B)('U2, tInt, tU1(A), A, B)
    val tU2FloatBoolean = tU2(tFloat, tBoolean)

    val expected = new Union(Seq(tFloat, tBoolean), "U2", Seq(
      tInt,
      new Union(Seq(tFloat), "U1", Seq(tFloat, tFloat)),
      tFloat,
      tBoolean))

    assert(tU2FloatBoolean === expected)
  }

  test("specs for fn pointer") {
    val T = 'T.virt
    val A = 'A.virt
    val B = 'B.virt

    val tNone = Scalar("void")
    val tInt = Scalar("i32")
    val tFloat = Scalar("float")
    val tBoolean = Scalar("i8")

    // ((T) -> Boolean) -> None
    val tEachFn = FnPtr(tNone, FnPtr(tBoolean, T))
    val tEachFnInt = tEachFn.specDeep(Map(T -> tInt))
    val expected = FnPtr(tNone, FnPtr(tBoolean, tInt))

    assert(tEachFnInt === expected)
  }
}
