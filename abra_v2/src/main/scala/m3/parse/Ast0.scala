package m3.parse

object Ast0 {
  sealed trait ParseNode
  sealed trait Expression extends ParseNode
  sealed trait FnBody
  sealed trait Level1Declaration extends ParseNode
  sealed trait Literal extends Expression {
    val value: String
  }

  case class lNone() extends Literal {
    override val value: String = "none"
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

  case class ScalarDecl(pkg: String, ref: Boolean, params: Seq[GenericTh], name: String, llType: String) extends TypeDecl
  case class FieldDecl(isSelf: Boolean, name: String, th: TypeHint) extends ParseNode
  case class StructDecl(pkg: String, params: Seq[GenericTh], name: String, fields: Seq[FieldDecl]) extends TypeDecl
  case class UnionDecl(pkg: String, params: Seq[GenericTh], name: String, variants: Seq[TypeHint]) extends TypeDecl

  sealed trait TypeHint extends ParseNode
  case class ScalarTh(params: Seq[TypeHint], name: String, mod: Seq[String]) extends TypeHint
  case class FieldTh(name: String, typeHint: TypeHint) extends ParseNode
  case class StructTh(seq: Seq[FieldTh]) extends TypeHint
  case class UnionTh(seq: Seq[TypeHint]) extends TypeHint
  case class GenericTh(typeName: String) extends TypeHint
  case object AnyTh extends TypeHint

  sealed trait ClosureType {
    val th: TypeHint
  }
  case class CLocal(th: TypeHint) extends ClosureType
  case class CParam(th: TypeHint) extends ClosureType
  case class FnTh(closure: Seq[ClosureType], args: Seq[TypeHint], ret: TypeHint) extends TypeHint

  case class Prop(from: Expression, props: Seq[lId]) extends Expression
  case class Tuple(seq: Seq[Expression]) extends Expression
  case class SelfCall(fnName: String, self: Expression, args: Seq[Expression]) extends Expression
  case class Call(expr: Expression, args: Seq[Expression]) extends Expression

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
  case class WhenElse(seq: Seq[Expression]) extends ParseNode
  case class When(expr: Expression, is: Seq[Is], _else: Option[WhenElse]) extends Expression
  case class While(cond: Expression, _do: Seq[Expression]) extends Expression
  case class Store(th: TypeHint, to: Seq[lId], what: Expression) extends Expression
  case class Ret(what: Option[Expression]) extends Expression
  case class Break() extends Expression
  case class Continue() extends Expression
  case class Arg(name: String, typeHint: TypeHint) extends ParseNode
  case class Def(name: String, lambda: Lambda, retTh: TypeHint) extends Level1Declaration
  case class ImportEntry(modName: String, path: String, withTypes: Seq[String]) extends ParseNode
  case class Import(seq: Seq[ImportEntry]) extends ParseNode
  case class Module(pkg: String, imports: Import, lowCode: Seq[llVm], types: Seq[TypeDecl], defs: Seq[Def]) extends ParseNode
}
