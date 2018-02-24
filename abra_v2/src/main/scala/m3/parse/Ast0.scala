package m3.parse

object Ast0 {
  sealed trait ParseNode
  sealed trait Expression extends ParseNode
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
  case class llVm(code: String) extends ParseNode with FnBody

  sealed trait TypeDecl extends Level1Declaration {
    val name: String
  }

  case class GenericType(name: String) extends ParseNode
  case class ScalarDecl(ref: Boolean, params: Seq[GenericType], name: String, llType: String) extends TypeDecl
  case class FieldDecl(isSelf: Boolean, name: String, th: TypeHint) extends ParseNode
  case class StructDecl(params: Seq[GenericType], name: String, fields: Seq[FieldDecl]) extends TypeDecl
  case class UnionDecl(params: Seq[GenericType], name: String, variants: Seq[ScalarTh]) extends TypeDecl

  sealed trait TypeHint extends ParseNode
  case class ScalarTh(params: Seq[TypeHint], name: String, pkg: Option[String]) extends TypeHint
  case class FieldTh(name: String, typeHint: TypeHint) extends ParseNode
  case class StructTh(seq: Seq[FieldTh]) extends TypeHint
  case class UnionTh(seq: Seq[TypeHint]) extends TypeHint

  sealed trait ClosureType {
    val th: TypeHint
  }
  case class CLocal(th: TypeHint) extends ClosureType
  case class CParam(th: TypeHint) extends ClosureType
  case class FnTh(closure: Seq[ClosureType], args: Seq[TypeHint], ret: TypeHint) extends TypeHint

  case class Prop(from: Expression, props: Seq[lId]) extends Expression
  case class Tuple(seq: Seq[Expression]) extends Expression
  case class SelfCall(params: Seq[TypeHint], fnName: String, self: Expression, args: Seq[Expression]) extends Expression
  case class Call(params: Seq[TypeHint], expr: Expression, args: Seq[Expression]) extends Expression

  case class AbraCode(seq: Seq[Expression]) extends FnBody
  case class Lambda(args: Seq[Arg], body: FnBody) extends Expression

  sealed trait AndOr {
    val left: Expression
    val right: Expression
  }
  case class And(left: Expression, right: Expression) extends Expression with AndOr
  case class Or(left: Expression, right: Expression) extends Expression with AndOr
  case class If(cond: Expression, _do: Seq[Expression], _else: Seq[Expression]) extends Expression
  case class Is(vName: lId, typeRef: TypeHint, _do: Seq[Expression]) extends ParseNode
  case class When(expr: Expression, is: Seq[Is], _else: Seq[Expression]) extends Expression
  case class While(cond: Expression, _do: Seq[Expression]) extends Expression
  case class Store(th: Option[TypeHint], to: Seq[lId], what: Expression) extends Expression
  case class Ret(what: Option[Expression]) extends Expression
  case class Arg(name: String, typeHint: Option[TypeHint]) extends ParseNode
  case class Def(params: Seq[GenericType], name: String, lambda: Lambda, retTh: Option[TypeHint]) extends Level1Declaration
  case class Import(seq: Seq[lId]) extends Level1Declaration
  case class Module(pkg: String, lowCode: Seq[llVm], types: Seq[TypeDecl], defs: Seq[Def]) extends ParseNode
}
