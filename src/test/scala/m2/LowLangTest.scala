package m2

import java.io.{FileOutputStream, InputStream, PrintStream}
import java.util.Scanner

import lang_m2.Ast1.{Store, Var, _}
import lang_m2.IrGen
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
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Ret(tInt, lInt("42"))
        )))
      )).assertRunEquals(Some(42))
  }

  test("local var store") {
    Module(
      functions = Seq(
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Var("a", tInt),
          Store(lLocal("a"), Seq(), tInt, lInt("42")),
          Ret(tInt, lLocal("a"))
        )))
      )).assertRunEquals(Some(42))
  }

  test("call global") {
    Module(
      functions = Seq(
        fPlus,
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Ret(tInt, Call(lGlobal("+_for_Int"), tFnPlus, Seq(lInt("1"), lInt("2"))))
        )))
      )).assertRunEquals(Some(3))
  }

  test("call local") {
    Module(
      functions = Seq(
        fPlus,
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Var("a", tFnPlus),
          Store(lLocal("a"), Seq(), tFnPlus, lGlobal("$plus_for_Int")),
          Ret(tInt, Call(lLocal("a"), tFnPlus, Seq(lInt("1"), lInt("2"))))
        )))
      )).assertRunEquals(Some(3))
  }

  test("call param") {
    val tFnFoo = FnPointer(Seq(Field("self", tFnPlus)), tInt)
    Module(
      functions = Seq(
        fPlus,
        Fn("foo", tFnFoo, Block(Seq(
          Ret(tInt, Call(lParam("self"), tFnPlus, Seq(lInt("1"), lInt("2"))))
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Ret(tInt, Call(lGlobal("foo"), tFnFoo, Seq(lGlobal("$plus_for_Int"))))
        )))
      )).assertRunEquals(Some(3))
  }

  test("return scalar value from function") {
    val tFnBar = FnPointer(Seq(), tInt)
    Module(
      functions = Seq(
        Fn("bar", tFnBar, Block(Seq(
          Ret(tInt, lInt("42"))
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Ret(tInt, Call(lGlobal("bar"), tFnBar, Seq()))
        )))
      )).assertRunEquals(Some(42))
  }

  test("store / access on struct field") {
    val tFoo = Struct("Foo", Seq(Field("x", tInt)))
    Module(
      structs = Seq(tFoo),
      functions = Seq(
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Var("foo", tFoo),
          Store(lLocal("foo"), Seq("x"), tFoo, lInt("42")),
          Ret(tInt, Access(lLocal("foo"), tFoo, "x"))
        )))
      )).assertRunEquals(Some(42))
  }

  test("pass struct value to function") {
    val tFoo = Struct("Foo", Seq(Field("x", tInt)))
    val tFnFooX = FnPointer(Seq(Field("self", tFoo)), tInt)
    Module(
      structs = Seq(tFoo),
      functions = Seq(
        Fn("f_x", tFnFooX, Block(Seq(
          Ret(tInt, Access(lParam("self"), tFoo, "x"))
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Var("foo", tFoo),
          Store(lLocal("foo"), Seq("x"), tFoo, lInt("42")),
          Ret(tInt, Call(lGlobal("f_x"), tFnFooX, Seq(lLocal("foo"))))
        )))
      )).assertRunEquals(Some(42))
  }

  //FIXME: too bad IR
  test("return struct value from function") {
    val tFoo = Struct("Foo", Seq(Field("x", tInt)))
    val tFnFoo = FnPointer(Seq(), tFoo)
    Module(
      structs = Seq(tFoo),
      functions = Seq(
        Fn("Foo", tFnFoo, Block(Seq(
          Store(lParam("ret"), Seq("x"), tFoo, lInt("42")),
          RetVoid()
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Var("foo", tFoo),
          Store(lLocal("foo"), Seq(), tFoo, Call(lGlobal("Foo"), tFnFoo, Seq())),
          Ret(tInt, Access(lLocal("foo"), tFoo, "x"))
        )))
      )).assertRunEquals(Some(42))
  }

  test("conditions") {
    Module(
      functions = Seq(
        fMore,
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Var("c", tInt),
          Cond(Call(lGlobal(">_for_Int"), tFnMore, Seq(lInt("1"), lInt("2"))),
            _if = Seq(Store(lLocal("c"), Seq(), tInt, lInt("1"))),
            _else = Seq(Store(lLocal("c"), Seq(), tInt, lInt("2")))
          ),
          Ret(tInt, lLocal("c"))
        )))
      )).assertRunEquals(Some(2))
  }

  test("while loop") {
    Module(
      functions = Seq(
        fPlus, fMore,
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Var("a", tInt),
          Store(lLocal("a"), Seq(), tInt, lInt("0")),
          While(Call(lGlobal(">_for_Int"), tFnMore, Seq(lInt("255"), lLocal("a"))), Seq(
            Store(lLocal("a"), Seq(), tInt, Call(lGlobal("+_for_Int"), tFnPlus, Seq(lLocal("a"), lInt("1"))))
          )),
          Ret(tInt, lLocal("a"))
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
          Fn("main", FnPointer(args = Seq(), ret = tBool), Block(Seq(
            Ret(tBool, BoolAnd(lInt(i.toString), lInt(j.toString)))
          )))
        )).assertRunEquals(Some(toInt(toBoolean(i) && toBoolean(j))))
    }
  }

  test("boolean && lazy evaluation test") {
    for (i <- 0 to 1; j <- 0 to 1) {
      //println(i, j)
      Module(
        functions = Seq(
          Fn("main", FnPointer(args = Seq(), ret = tBool), Block(Seq(
            Ret(tBool, BoolOr(lInt(i.toString), lInt(j.toString)))
          )))
        )).assertRunEquals(Some(toInt(toBoolean(i) || toBoolean(j))))
    }
  }
}
