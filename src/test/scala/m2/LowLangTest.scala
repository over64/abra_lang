package m2

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
    new IrGen().gen(Module(
      structs = Seq(tVec3),
      functions = Seq(
        fPlus,
        Fn(GlobalFnPtr("main", Signature(args = Seq(), ret = tInt)), Block(Seq(
          Var("a", tInt),
          Store(Access("a", tInt, Seq()), lInt("10")),
          Var("b", tInt),
          Store(Access("b", tInt, Seq()), lInt("11")),
          Var("c", tInt),
          Store(Access("c", tInt, Seq()), Call(pPlus,
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

    new IrGen().gen(Module(
      structs = Seq(
        tVec3
      ),
      functions = Seq(
        fPlus,
        Fn(pVec3Init, Block(Seq(
          Store(Access("ret", tVec3, Seq("x")), lParam("x")),
          Store(Access("ret", tVec3, Seq("y")), lParam("y")),
          Store(Access("ret", tVec3, Seq("z")), lParam("z")),
          RetVoid()
        ))),
        Fn(pVec3Plus, Block(Seq(
          Store(Access("ret", tVec3, Seq("x")), Call(pPlus, Seq(
            Access("self", tVec3, Seq("x")), Access("other", tVec3, Seq("x"))))),
          Store(Access("ret", tVec3, Seq("y")), Call(pPlus, Seq(
            Access("self", tVec3, Seq("y")), Access("other", tVec3, Seq("y"))))),
          Store(Access("ret", tVec3, Seq("z")), Call(pPlus, Seq(
            Access("self", tVec3, Seq("z")), Access("other", tVec3, Seq("z"))))),
          RetVoid()
        ))),
        Fn(pVec3Dot, Block(Seq(
          Var("i", tInt),
          Store(Access("i", tInt, Seq()), Call(pPlus, Seq(
            Access("self", tVec3, Seq("x")), Call(pPlus, Seq(
              Access("self", tVec3, Seq("y")), Access("self", tVec3, Seq("z"))))))),
          Ret(tInt, lId("i"))
        ))),
        Fn(GlobalFnPtr("main", Signature(args = Seq(), ret = tInt)), Block(Seq(
          Var("v1", tVec3),
          Store(Access("v1", tVec3, Seq()), Call(pVec3Init, Seq(lInt("1"), lInt("2"), lInt("3")))),
          Var("v2", tVec3),
          Store(Access("v2", tVec3, Seq()), Call(pVec3Init, Seq(lInt("3"), lInt("2"), lInt("1")))),
          Var("v3", tVec3),
          Store(Access("v3", tVec3, Seq()), Call(pVec3Plus, Seq(lId("v1"), lId("v2")))),
          Var("i", tInt),
          Store(Access("i", tInt, Seq()), Call(pVec3Dot, Seq(lId("v3")))),
          Ret(tInt, lId("i"))
        )))
      )
    ))
  }

  test("cond test") {

    new IrGen().gen(Module(
      structs = Seq(),
      functions = Seq(
        fMore,
        Fn(GlobalFnPtr("main", Signature(args = Seq(), ret = tInt)), Block(Seq(
          Var("a", tInt),
          Store(Access("a", tInt, Seq()), lInt("11")),
          Var("b", tInt),
          Store(Access("b", tInt, Seq()), lInt("10")),
          Var("c", tInt),
          Cond(Call(pMore, Seq(lId("a"), lId("b"))),
            _if = Seq(Store(Access("c", tInt, Seq()), lInt("1"))),
            _else = Seq(Store(Access("c", tInt, Seq()), lInt("2")))
          ),
          Ret(tInt, lId("c"))
        )))
      )
    ))
  }

  test("loop test") {
    new IrGen().gen(Module(
      structs = Seq(),
      functions = Seq(
        fPlus, fMore,
        Fn(GlobalFnPtr("main", Signature(args = Seq(), ret = tInt)), Block(Seq(
          Var("a", tInt),
          Store(Access("a", tInt, Seq()), lInt("0")),
          While(Call(pMore, Seq(lInt("255"), lId("a"))), Seq(
            Store(Access("a", tInt, Seq()), Call(pPlus, Seq(lId("a"), lInt("1"))))
          )),
          Ret(tInt, lId("a"))
        )))
      )
    ))
  }

  test("fn pointer test") {
    new IrGen().gen(Module(
      structs = Seq(),
      functions = Seq(
        fPlus,
        Fn(GlobalFnPtr("main_def_anon1", Signature(args = Seq(Field("self", tInt)), ret = tInt)), Block(Seq(

        ))),
        Fn(GlobalFnPtr("main", Signature(args = Seq(), ret = tInt)), Block(Seq(
          Var("a", tInt),
          Store(Access("a", tInt, Seq()), lInt("0")),
          While(Call(pMore, Seq(lInt("255"), lId("a"))), Seq(
            Store(Access("a", tInt, Seq()), Call(pPlus, Seq(lId("a"), lInt("1"))))
          )),
          Ret(tInt, lId("a"))
        )))
      )
    ))
  }
}
