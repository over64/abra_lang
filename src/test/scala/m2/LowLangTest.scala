package m2

import java.io.PrintStream

import lang_m2.IrGen
import org.scalatest.FunSuite
import lang_m2.Ast1.Store
import lang_m2.Ast1._

class LowLangTest extends FunSuite {
  val tVoid = Scalar("void")
  val tBool = Scalar("i1")
  val tInt = Scalar("i32")
  val tVec3 = Struct("Vec3", Seq(Field("x", tInt), Field("y", tInt), Field("z", tInt)))

  val pPlus = GlobalFnPtr("+_for_Int", Signature(
    args = Seq(Field("self", tInt), Field("other", tInt)),
    ret = tInt))

  val fPlus = Fn(pPlus, IrInline(
    """|    %1 = add nsw i32 %other, %self
      |    ret i32 %1 """.stripMargin))

  val pMore = GlobalFnPtr(">_for_Int", Signature(
    args = Seq(Field("self", tInt), Field("other", tInt)),
    ret = tBool))

  val fMore = Fn(pMore, IrInline(
    """|    %1 = icmp sgt i32 %self, %other
      |    ret i1 %1 """.stripMargin))

  test("a low lang dev test") {
    new IrGen(new PrintStream(System.out)).gen(Module(
      structs = Seq(tVec3),
      functions = Seq(
        fPlus,
        Fn(GlobalFnPtr("main", Signature(args = Seq(), ret = tInt)), Block(Seq(
          Var("a", tInt),
          Store(lId("a"), Seq(), tInt, lInt("10")),
          Var("b", tInt),
          Store(lId("b"), Seq(), tInt, lInt("11")),
          Var("c", tInt),
          Store(lId("c"), Seq(), tInt, Call(pPlus,
            Seq(lId("a"), Call(pPlus,
              Seq(lId("b"), lInt("10")))))),
          Ret(tInt, lId("c"))
        )))
      )
    ))
  }

  test("a ABI test") {
    val pVec3Init = GlobalFnPtr("Vec3", Signature(
      args = Seq(Field("x", tInt), Field("y", tInt), Field("z", tInt)),
      ret = tVec3))

    val pVec3Plus = GlobalFnPtr("+_for_Vec3", Signature(
      args = Seq(Field("self", tVec3), Field("other", tVec3)),
      ret = tVec3))

    val pVec3Dot = GlobalFnPtr("dot_for_Vec3", Signature(
      args = Seq(Field("self", tVec3)),
      ret = tInt))

    new IrGen(new PrintStream(System.out)).gen(Module(
      structs = Seq(
        tVec3
      ),
      functions = Seq(
        fPlus,
        Fn(pVec3Init, Block(Seq(
          Store(lParam("ret"), Seq("x"), tVec3, lParam("x")),
          Store(lParam("ret"), Seq("y"), tVec3, lParam("y")),
          Store(lParam("ret"), Seq("z"), tVec3, lParam("z")),
          RetVoid()
        ))),
        Fn(pVec3Plus, Block(Seq(
          Store(lParam("ret"), Seq("x"), tVec3, Call(pPlus, Seq(
            Access(lParam("self"), tVec3, "x"), Access(lParam("other"), tVec3, "x")))),
          Store(lParam("ret"), Seq("y"), tVec3, Call(pPlus, Seq(
            Access(lParam("self"), tVec3, "y"), Access(lParam("other"), tVec3, "y")))),
          Store(lParam("ret"), Seq("z"), tVec3, Call(pPlus, Seq(
            Access(lParam("self"), tVec3, "z"), Access(lParam("other"), tVec3, "z")))),
          RetVoid()
        ))),
        Fn(pVec3Dot, Block(Seq(
          Var("i", tInt),
          Store(lId("i"), Seq(), tInt, Call(pPlus, Seq(
            Access(lParam("self"), tVec3, "x"), Call(pPlus, Seq(
              Access(lParam("self"), tVec3, "y"), Access(lParam("self"), tVec3, "z")))))),
          Ret(tInt, lId("i"))
        ))),
        Fn(GlobalFnPtr("main", Signature(args = Seq(), ret = tInt)), Block(Seq(
          Var("v1", tVec3),
          Store(lId("v1"), Seq(), tVec3, Call(pVec3Init, Seq(lInt("1"), lInt("2"), lInt("3")))),
          Var("v2", tVec3),
          Store(lId("v2"), Seq(), tVec3, Call(pVec3Init, Seq(lInt("3"), lInt("2"), lInt("1")))),
          Var("v3", tVec3),
          Store(lId("v3"), Seq(), tVec3, Call(pVec3Plus, Seq(lId("v1"), lId("v2")))),
          Var("i", tInt),
          Store(lId("i"), Seq(), tInt, Call(pVec3Dot, Seq(lId("v3")))),
          Ret(tInt, lId("i"))
        )))
      )
    ))
  }

  test("cond test") {

    new IrGen(new PrintStream(System.out)).gen(Module(
      structs = Seq(),
      functions = Seq(
        fMore,
        Fn(GlobalFnPtr("main", Signature(args = Seq(), ret = tInt)), Block(Seq(
          Var("a", tInt),
          Store(lId("a"), Seq(), tInt, lInt("11")),
          Var("b", tInt),
          Store(lId("b"), Seq(), tInt, lInt("10")),
          Var("c", tInt),
          Cond(Call(pMore, Seq(lId("a"), lId("b"))),
            _if = Seq(Store(lId("c"), Seq(), tInt, lInt("1"))),
            _else = Seq(Store(lId("c"), Seq(), tInt, lInt("2")))
          ),
          Ret(tInt, lId("c"))
        )))
      )
    ))
  }

  test("loop test") {
    new IrGen(new PrintStream(System.out)).gen(Module(
      structs = Seq(),
      functions = Seq(
        fPlus, fMore,
        Fn(GlobalFnPtr("main", Signature(args = Seq(), ret = tInt)), Block(Seq(
          Var("a", tInt),
          Store(lId("a"), Seq(), tInt, lInt("0")),
          While(Call(pMore, Seq(lInt("255"), lId("a"))), Seq(
            Store(lId("a"), Seq(), tInt, Call(pPlus, Seq(lId("a"), lInt("1"))))
          )),
          Ret(tInt, lId("a"))
        )))
      )
    ))
  }

  test("fn pointer test") {
    new IrGen(new PrintStream(System.out)).gen(Module(
      structs = Seq(),
      functions = Seq(
        fPlus,
        Fn(GlobalFnPtr("main_def_anon1", Signature(args = Seq(Field("self", tInt)), ret = tInt)), Block(Seq(

        ))),
        Fn(GlobalFnPtr("main", Signature(args = Seq(), ret = tInt)), Block(Seq(
          Var("a", tInt),
          Store(lId("a"), Seq(), tInt, lInt("0")),
          While(Call(pMore, Seq(lInt("255"), lId("a"))), Seq(
            Store(lId("a"), Seq(), tInt, Call(pPlus, Seq(lId("a"), lInt("1"))))
          )),
          Ret(tInt, lId("a"))
        )))
      )
    ))
  }
}
