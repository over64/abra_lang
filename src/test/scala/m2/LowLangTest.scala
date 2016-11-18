package m2

import lang_m2.Ast1._
import org.scalatest.FunSuite


class LowLangTest extends FunSuite with LowUtil {
  override val testBase: String = System.getProperty("java.io.tmpdir")

  val tVoid = Scalar("void")
  val tBool = Scalar("i1")
  val tInt = Scalar("i32")
  val tVec3 = Struct("Vec3", Seq(Field("x", tInt), Field("y", tInt), Field("z", tInt)))
  val tFnPlus = FnPointer(args = Seq(Field("self", tInt), Field("other", tInt)), ret = tInt)
  val tFnMore = FnPointer(args = Seq(Field("self", tInt), Field("other", tInt)), ret = tBool)

  val fPlus = Fn("+_for_Int", tFnPlus, IrInline(
    """
      |  %1 = add nsw i32 %other, %self
      |  ret i32 %1 """.stripMargin))

  val fMore = Fn(">_for_Int", tFnMore, IrInline(
    """|    %1 = icmp sgt i32 %self, %other
      |    ret i1 %1 """.stripMargin))


  test("return scalar type") {
    Module(
      functions = Seq(
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(stats = Seq(
          Ret(lInt("42"))
        )))
      )).assertRunEquals(Some(42))
  }

  test("local var store") {
    Module(
      functions = Seq(
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(
          vars = Map("a" -> tInt),
          stats = Seq(
            Store(lLocal("a"), Seq(), lInt("42")),
            Ret(lLocal("a"))
          )))
      )).assertRunEquals(Some(42))
  }

  test("call global") {
    Module(
      functions = Seq(
        fPlus,
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(stats = Seq(
          Ret(Call(lGlobal("+_for_Int"), Seq(lInt("1"), lInt("2"))))
        )))
      )).assertRunEquals(Some(3))
  }

  test("call local") {
    Module(
      functions = Seq(
        fPlus,
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(
          vars = Map("a" -> tFnPlus),
          stats = Seq(
            Store(lLocal("a"), Seq(), lGlobal("$plus_for_Int")),
            Ret(Call(lLocal("a"), Seq(lInt("1"), lInt("2"))))
          )))
      )).assertRunEquals(Some(3))
  }

  test("call param") {
    val tFnFoo = FnPointer(Seq(Field("self", tFnPlus)), tInt)
    Module(
      functions = Seq(
        fPlus,
        Fn("foo", tFnFoo, Block(stats = Seq(
          Ret(Call(lParam("self"), Seq(lInt("1"), lInt("2"))))
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(stats = Seq(
          Ret(Call(lGlobal("foo"), Seq(lGlobal("$plus_for_Int"))))
        )))
      )).assertRunEquals(Some(3))
  }

  test("return scalar value from function") {
    val tFnBar = FnPointer(Seq(), tInt)
    Module(
      functions = Seq(
        Fn("bar", tFnBar, Block(stats = Seq(
          Ret(lInt("42"))
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(stats = Seq(
          Ret(Call(lGlobal("bar"), Seq()))
        )))
      )).assertRunEquals(Some(42))
  }

  test("store / access on struct field") {
    val tFoo = Struct("Foo", Seq(Field("x", tInt)))
    Module(
      structs = Seq(tFoo),
      functions = Seq(
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(
          vars = Map("foo" -> tFoo),
          stats = Seq(
            Store(lLocal("foo"), Seq("x"), lInt("42")),
            Ret(Access(lLocal("foo"), "x"))
          )))
      )).assertRunEquals(Some(42))
  }

  test("pass struct value to function") {
    val tFoo = Struct("Foo", Seq(Field("x", tInt)))
    val tFnFooX = FnPointer(Seq(Field("self", tFoo)), tInt)
    Module(
      structs = Seq(tFoo),
      functions = Seq(
        Fn("f_x", tFnFooX, Block(stats = Seq(
          Ret(Access(lParam("self"), "x"))
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(
          vars = Map("foo" -> tFoo),
          stats = Seq(
            Store(lLocal("foo"), Seq("x"), lInt("42")),
            Ret(Call(lGlobal("f_x"), Seq(lLocal("foo"))))
          )))
      )).assertRunEquals(Some(42))
  }

  //FIXME: too bad IR, Optimize store!
  test("return struct value from function") {
    val tFoo = Struct("Foo", Seq(Field("x", tInt)))
    val tFnFoo = FnPointer(Seq(Field("ret", tFoo)), Scalar("void"))
    Module(
      structs = Seq(tFoo),
      functions = Seq(
        Fn("Foo", tFnFoo, Block(stats = Seq(
          Store(lParam("ret"), Seq("x"), lInt("42")),
          RetVoid()
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(
          vars = Map("foo" -> tFoo),
          stats = Seq(
            Call(lGlobal("Foo"), Seq(lLocal("foo"))),
            Ret(Access(lLocal("foo"), "x"))
          )))
      )).assertRunEquals(Some(42))
  }

  test("conditions") {
    Module(
      functions = Seq(
        fMore,
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(
          vars = Map("c" -> tInt),
          stats = Seq(
            Cond(Call(lGlobal(">_for_Int"), Seq(lInt("1"), lInt("2"))),
              _if = Seq(Store(lLocal("c"), Seq(), lInt("1"))),
              _else = Seq(Store(lLocal("c"), Seq(), lInt("2")))
            ),
            Ret(lLocal("c"))
          )))
      )).assertRunEquals(Some(2))
  }

  test("while loop") {
    Module(
      functions = Seq(
        fPlus, fMore,
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(
          vars = Map("a" -> tInt),
          stats = Seq(
            Store(lLocal("a"), Seq(), lInt("0")),
            While(Call(lGlobal(">_for_Int"), Seq(lInt("255"), lLocal("a"))), Seq(
              Store(lLocal("a"), Seq(), Call(lGlobal("+_for_Int"), Seq(lLocal("a"), lInt("1"))))
            )),
            Ret(lLocal("a"))
          )))
      )).assertRunEquals(Some(255))
  }

  def toBoolean(i: Int): Boolean = i match {
    case 0 => false
    case 1 => true
    case _ => throw new Exception("oops")
  }

  def toInt(b: Boolean) = if (b) 1 else 0

  test("boolean || lazy evaluation test") {
    for (i <- 0 to 1; j <- 0 to 1) {
      //println(i, j)
      Module(
        functions = Seq(
          Fn("main", FnPointer(args = Seq(), ret = tBool), Block(stats = Seq(
            Ret(BoolAnd(lInt(i.toString), lInt(j.toString)))
          )))
        )).assertRunEquals(Some(toInt(toBoolean(i) && toBoolean(j))))
    }
  }

  test("boolean && lazy evaluation test") {
    for (i <- 0 to 1; j <- 0 to 1) {
      //println(i, j)
      Module(
        functions = Seq(
          Fn("main", FnPointer(args = Seq(), ret = tBool), Block(stats = Seq(
            Ret(BoolOr(lInt(i.toString), lInt(j.toString)))
          )))
        )).assertRunEquals(Some(toInt(toBoolean(i) || toBoolean(j))))
    }
  }

  // def main = {
  //   var x = 0
  //   { x = 42 }()
  //   x
  // }: Int
  test("closure local") {
    val tclosure1 = Closure("tclosure1", FnPointer(args = Seq(), ret = Scalar("void")), vals = Seq(ClosureVal(lLocal("x"), tInt)))
    Module(
      functions = Seq(
        Fn("anonFn1", tclosure1, Block(stats = Seq(
          Store(lClosure("x"), Seq(), lInt("42")),
          RetVoid()
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(
          vars = Map("x" -> tInt, "fn" -> tclosure1),
          stats = Seq(
            Store(lLocal("x"), Seq(), lInt("0")),
            StoreEnclosure(lLocal("fn"), lGlobal("anonFn1")),
            Call(lLocal("fn"), Seq()),
            Ret(lLocal("x"))
          )))
      )).assertRunEquals(Some(42))
  }

  // def foo = \fn: () -> Unit ->
  //   fn()
  //
  // def main = {
  //   var x = 0
  //   foo({ x = 42 })
  //   x
  // }: Int
  test("closure param") {
    val tUnit = Scalar("void")
    val tclosure1 = Closure("tclosure1", FnPointer(args = Seq(), ret = tUnit), vals = Seq(ClosureVal(lLocal("x"), tInt)))
    val tdisclosure1 = Disclosure(FnPointer(args = Seq(), ret = tUnit))
    Module(
      functions = Seq(
        Fn("anonFn1", tclosure1, Block(stats = Seq(
          Store(lClosure("x"), Seq(), lInt("42")),
          RetVoid()
        ))),
        Fn("foo", FnPointer(args = Seq(Field("fn", tdisclosure1)), ret = tUnit), Block(stats = Seq(
          Call(lParam("fn"), Seq()),
          RetVoid()
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(
          vars = Map("x" -> tInt, "anon1" -> tclosure1),
          stats = Seq(
            Store(lLocal("x"), Seq(), lInt("0")),
            StoreEnclosure(lLocal("anon1"), lGlobal("anonFn1")),
            Call(lGlobal("foo"), Seq(lLocal("anon1"))),
            Ret(lLocal("x"))
          )))
      )).assertRunEquals(Some(42))
  }
}
