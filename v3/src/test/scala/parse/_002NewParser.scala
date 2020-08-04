package parse

import org.scalatest.FunSuite

class _002NewParser extends FunSuite {
  case class Def(name: String)

  sealed trait Literal
  case class LitInt() extends Literal
  case class LitString() extends Literal


  case class Ctx()
  trait WithCtx {
    val ctx: Ctx
  }

  trait LiteralCodeGen extends WithCtx {
    @inline def rDef[T](next: => Option[T]): Option[T] = {
      ctx
      None
    }
    @inline def rId[T](next: String => Option[T]): Option[T] = ???
    @inline def rEq[T](next: => Option[T]): Option[T] = ???
  }

  final class Parser(val ctx: Ctx) extends LiteralCodeGen {
    def rLitInt(): Option[LitInt] = ???
    def rLitString(): Option[LitString] = ???

    def rLit[T](next: Literal => Option[T]): Option[T] =
      mOr[Literal, T](rLitInt, rLitString)(next)


    def mOr[T, U](rules: (() => Option[T])*)(next: T => Option[U]): Option[U] = ???
    def mOpt[T, U](rule: => Option[T], next: Option[T] => Option[U]): Option[U] = ???

    // @formatter:off
    def _def = rDef{ rId{ id => rEq{ rLit{ lit => Some(Def(id)) }}}}
    // @formatter:on

  }



  test("xxx") {
    //val d = new Parser(Ctx())._def
  }
}
