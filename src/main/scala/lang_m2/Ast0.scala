package lang_m2

object Ast0 {
  sealed trait ParseNode
  sealed trait BlockExpression extends ParseNode
  sealed trait MatchOver extends ParseNode
  sealed trait Expression extends BlockExpression with MatchOver
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

  case class ScalarDecl(name: String, llType: String) extends TypeDecl
  case class FieldDecl(isSelf: Boolean, name: String, th: TypeHint) extends ParseNode
  case class FactorDecl(name: String, fields: Seq[FieldDecl]) extends TypeDecl
  case class UnionDecl(name: String, variants: Seq[ScalarTypeHint]) extends TypeDecl

  sealed trait TypeHint extends ParseNode {
    val name: String
  }

  case class ScalarTypeHint(name: String, _package: String) extends TypeHint
  case class FnTypeHintField(name: String, typeHint: TypeHint) extends ParseNode {
    override def hashCode(): Int = typeHint.hashCode()

    override def equals(o: scala.Any): Boolean =
      if (!o.isInstanceOf[FnTypeHintField]) false
      else o.asInstanceOf[FnTypeHintField].typeHint == typeHint
  }
  case class FnTypeHint(seq: Seq[FnTypeHintField], ret: TypeHint) extends TypeHint {
    override val name: String = s"fn${seq.map(_.typeHint.name).mkString("_")}__${ret.name}"
  }

  case class Prop(from: Expression, prop: lId) extends Expression
  case class Tuple(seq: Seq[Expression]) extends Expression

  case class GetCall(self: Expression, args: Seq[Expression]) extends Expression
  case class ApplyCall(self: Expression) extends Expression
  case class SelfCall(fnName: String, self: Expression, args: Seq[Expression]) extends Expression
  case class Call(fnName: String, args: Seq[Expression]) extends Expression
  case class ModCall(_package: String, fnName: String, args: Seq[Expression]) extends Expression

  case class Block(args: Seq[FnArg], seq: Seq[BlockExpression]) extends Expression with FnBody

  case class BoolAnd(left: Expression, right: Expression) extends Expression
  case class BoolOr(left: Expression, right: Expression) extends Expression
  case class Cond(ifCond: Expression, _then: Block, _else: Option[Block], allowPartialRet: Boolean = false) extends Expression

  case object Dash extends MatchOver
  case class BindVar(varName: lId) extends MatchOver
  case class Destruct(varName: Option[lId], scalarTypeHint: ScalarTypeHint, args: Seq[MatchOver]) extends MatchOver
  case class MatchType(varName: lId, scalarTypeHint: ScalarTypeHint) extends MatchOver
  case class Case(over: MatchOver, cond: Option[Expression], expr: Expression) extends ParseNode
  case class Match(on: Expression, cases: Seq[Case]) extends Expression

  case class While(cond: Expression, _then: Block) extends Expression with BlockExpression
  case class Store(to: Seq[lId], what: Expression) extends BlockExpression
  case class Val(mutable: Boolean, name: String, typeHint: Option[TypeHint], init: Expression) extends BlockExpression
  case class FnArg(name: String, typeHint: Option[TypeHint]) extends ParseNode
  case class LlInline(value: String) extends FnBody with ParseNode
  case class Fn(name: String, typeHint: Option[FnTypeHint], body: FnBody, retTypeHint: Option[TypeHint]) extends Level1Declaration
  case class Import(seq: Seq[lId]) extends Level1Declaration
  case class Module(_package: String, imports: Seq[Import], typeDecls: Seq[TypeDecl], functions: Seq[Fn]) extends ParseNode
}
