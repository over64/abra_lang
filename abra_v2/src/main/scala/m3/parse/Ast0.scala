package m3.parse

object Ast0 {
  sealed trait ParseNode
  sealed trait MatchOver extends ParseNode
  sealed trait Expression extends MatchOver
  sealed trait FnBody
  sealed trait Level1Declaration extends ParseNode
  sealed trait Literal extends Expression {
    val value: String
  }

  case class lInt(value: String) extends Literal
  case class lFloat(value: String) extends Literal
  case class lBoolean(value: String) extends Literal
  case class lString(value: String) extends Literal
  case class lId(value: String) extends Literal

  sealed trait TypeDecl extends Level1Declaration {
    val name: String
  }

  case class TypeParam(name: String) extends ParseNode
  case class ScalarDecl(name: String, llType: String) extends TypeDecl
  case class FieldDecl(isSelf: Boolean, name: String, th: TypeHint) extends ParseNode
  case class StructDecl(params: Seq[TypeParam], name: String, fields: Seq[FieldDecl]) extends TypeDecl
  case class UnionDecl(params: Seq[TypeParam], name: String, variants: Seq[ScalarTh]) extends TypeDecl

  sealed trait TypeHint extends ParseNode {
    val name: String
  }

  case class ScalarTh(params: Seq[TypeParam], name: String, _package: String, pointer: Boolean) extends TypeHint
  case class FieldTh(name: String, typeHint: TypeHint) extends ParseNode {
    override def hashCode(): Int = typeHint.hashCode()

    override def equals(o: scala.Any): Boolean =
      if (!o.isInstanceOf[FieldTh]) false
      else o.asInstanceOf[FieldTh].typeHint == typeHint
  }
  case class FnTh(args: Seq[TypeHint], ret: TypeHint) extends TypeHint {
    override val name: String = s"fn${args.map(_.name).mkString("_")}__${ret.name}"
  }
  case class StructTh(seq: Seq[TypeHint]) extends TypeHint {
    override val name: String = seq.mkString("(", ",", ")")
  }
  case class UnionTh(seq: Seq[TypeHint]) extends TypeHint {
    override val name: String = seq.mkString("|")
  }

  case class LlDef(args: Seq[FnArg], code: String) extends ParseNode with FnBody
  case class LlType(text: String) extends ParseNode
  case class Block(seq: Seq[Expression]) extends ParseNode

  case class Ref(from: Expression) extends Expression
  case class Deref(from: Expression) extends Expression
  case class Prop(from: Expression, prop: lId) extends Expression
  case class Tuple(seq: Seq[Expression]) extends Expression

  case class SelfCall(fnName: String, self: Expression, args: Seq[Expression]) extends Expression
  case class Call(expr: Expression, args: Seq[Expression]) extends Expression

  case class Lambda(args: Seq[FnArg], seq: Seq[Expression]) extends Expression with FnBody

  case class BoolAnd(left: Expression, right: Expression) extends Expression
  case class BoolOr(left: Expression, right: Expression) extends Expression
  case class Cond(ifCond: Expression, _then: Seq[Expression], _else: Seq[Expression]) extends Expression

  case object Dash extends MatchOver
  case class BindVar(varName: lId) extends MatchOver
  case class Destruct(varName: Option[lId], scalarTypeHint: ScalarTh, args: Seq[MatchOver]) extends MatchOver
  case class MatchType(varName: lId, scalarTypeHint: ScalarTh) extends MatchOver
  case class Case(over: MatchOver, cond: Option[Expression], seq: Seq[Expression]) extends ParseNode
  case class Match(on: Expression, cases: Seq[Case]) extends Expression

  case class While(cond: Expression, _then: Seq[Expression]) extends Expression
  case class Store(to: Seq[lId], what: Expression) extends Expression
  case class Val(mutable: Boolean, name: String, typeHint: Option[TypeHint], init: Expression) extends Expression
  case class FnArg(name: String, typeHint: Option[TypeHint]) extends ParseNode
  case class Fn(tparams: Seq[TypeParam], name: String, body: FnBody, retTh: Option[TypeHint]) extends Level1Declaration
  case class Import(seq: Seq[lId]) extends Level1Declaration
  case class Module(_package: String, typeDecls: Seq[TypeDecl], functions: Seq[Fn]) extends ParseNode
}
