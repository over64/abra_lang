package m2

import java.io.PrintStream

import lang_m2.Ast1.{Store, _}
import lang_m2.IrGen
import org.scalatest.FunSuite

class LowLangTest extends FunSuite {
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


  test("simple test") {
    new IrGen(new PrintStream(System.out)).gen(Module(
      structs = Seq(tVec3),
      functions = Seq(
        fPlus,
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Var("a", tInt),
          Store(lLocal("a"), Seq(), tInt, lInt("10")),
          Var("b", tInt),
          Store(lLocal("b"), Seq(), tInt, lInt("11")),
          Var("c", tInt),
          Store(lLocal("c"), Seq(), tInt, Call(lGlobal("+_for_Int"), tFnPlus,
            Seq(lLocal("a"), Call(lGlobal("+_for_Int"), tFnPlus,
              Seq(lLocal("b"), lInt("10")))))),
          Ret(tInt, lLocal("c"))
        )))
      )
    ))
  }

  test("structs test") {
    val tVec3Init = FnPointer(
      args = Seq(Field("x", tInt), Field("y", tInt), Field("z", tInt)),
      ret = tVec3)

    val tVec3Dot = FnPointer(
      args = Seq(Field("self", tVec3)),
      ret = tInt)

    new IrGen(new PrintStream(System.out)).gen(Module(
      structs = Seq(
        tVec3
      ),
      functions = Seq(
        fPlus,
        Fn("Vec3", tVec3Init, Block(Seq(
          Store(lParam("ret"), Seq("x"), tVec3, lParam("x")),
          Store(lParam("ret"), Seq("y"), tVec3, lParam("y")),
          Store(lParam("ret"), Seq("z"), tVec3, lParam("z")),
          RetVoid()
        ))),
        Fn("dot_for_Vec3", tVec3Dot, Block(Seq(
          Var("i", tInt),
          Store(lLocal("i"), Seq(), tInt, Call(lGlobal("+_for_Int"), tFnPlus, Seq(
            Access(lParam("self"), tVec3, "x"), Call(lGlobal("+_for_Int"), tFnPlus, Seq(
              Access(lParam("self"), tVec3, "y"), Access(lParam("self"), tVec3, "z")))))),
          Ret(tInt, lLocal("i"))
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Var("v1", tVec3),
          Store(lLocal("v1"), Seq(), tVec3, Call(lGlobal("Vec3"), tVec3Init, Seq(lInt("1"), lInt("2"), lInt("3")))),
          Var("i", tInt),
          Store(lLocal("i"), Seq(), tInt, Call(lGlobal("dot_for_Vec3"), tVec3Dot, Seq(lLocal("v1")))),
          Ret(tInt, lLocal("i"))
        )))
      )))
  }

  test("cond test") {
    new IrGen(new PrintStream(System.out)).gen(Module(
      structs = Seq(),
      functions = Seq(
        fMore,
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Var("a", tInt),
          Store(lLocal("a"), Seq(), tInt, lInt("11")),
          Var("b", tInt),
          Store(lLocal("b"), Seq(), tInt, lInt("10")),
          Var("c", tInt),
          Cond(Call(lGlobal(">_for_Int"), tFnMore, Seq(lLocal("a"), lLocal("b"))),
            _if = Seq(Store(lLocal("c"), Seq(), tInt, lInt("1"))),
            _else = Seq(Store(lLocal("c"), Seq(), tInt, lInt("2")))
          ),
          Ret(tInt, lLocal("c"))
        )))
      )
    ))
  }

  test("loop test") {
    new IrGen(new PrintStream(System.out)).gen(Module(
      structs = Seq(),
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
      )
    ))
  }

  test("fn pointer test") {
    val tFnPtrIntInt = FnPointer(args = Seq(), ret = tInt)
    val tFnPtrFoo = FnPointer(args = Seq(Field("fn", tFnPtrIntInt)), ret = tInt)

    new IrGen(new PrintStream(System.out)).gen(Module(
      structs = Seq(),
      functions = Seq(
        fPlus,
        Fn("main_def_anon1", tFnPtrIntInt, Block(Seq(
          Ret(tInt, lInt("1"))
        ))),
        Fn("foo", tFnPtrFoo, Block(Seq(
          Ret(tInt, Call(lParam("fn"), tFnPtrIntInt, Seq()))
        ))),
        Fn("main", FnPointer(args = Seq(), ret = tInt), Block(Seq(
          Var("a", tFnPtrIntInt),
          Store(lLocal("a"), Seq(), tFnPtrIntInt, lGlobal("main_def_anon1")),

          Ret(tInt, Call(lGlobal("+_for_Int"), tFnPlus, Seq(
            Call(lLocal("a"), tFnPtrIntInt, Seq()),
            Call(lGlobal("foo"), tFnPtrFoo, Seq(lLocal("a")))
          )))
        )))
      )
    ))
  }
}
