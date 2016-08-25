package lang_m2

object Ast0 {
  sealed trait ParseNode
  sealed trait BlockExpression extends ParseNode
  sealed trait Expression extends BlockExpression
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

  sealed trait Type extends Level1Declaration {
    val name: String
  }

  case class ScalarType(name: String, llType: String) extends Type
  case class TypeField(isSelf: Boolean, name: String, typeHint: TypeHint) extends ParseNode
  case class FactorType(name: String, fields: Seq[TypeField]) extends Type

  sealed trait TypeHint extends ParseNode {
    val name: String
  }

  case class ScalarTypeHint(name: String) extends TypeHint
  case class FnTypeHintField(name: String, typeHint: TypeHint) extends ParseNode
  case class FnTypeHint(seq: Seq[FnTypeHintField], ret: TypeHint) extends TypeHint {
    override val name: String = s"fn${seq.map(_.typeHint.name).mkString("_")}__${ret.name}"
  }

  case class Prop(from: Expression, prop: lId) extends Expression
  case class Tuple(seq: Seq[Expression]) extends Expression
  case class Call(fnName: String, args: Tuple) extends Expression
  case class Block(args: Seq[FnArg], seq: Seq[BlockExpression]) extends Expression with FnBody
  case class Cond(ifCond: Expression, _then: Block, _else: Option[Block]) extends Expression
  case class While(cond: Expression, _then: Block) extends Expression with BlockExpression
  case class Store(to: Seq[lId], what: Expression) extends BlockExpression
  case class Val(mutable: Boolean, name: String, typeHint: Option[TypeHint], init: Expression) extends BlockExpression
  case class FnArg(name: String, typeHint: Option[TypeHint]) extends ParseNode
  case class LlInline(value: String) extends FnBody with ParseNode
  case class Fn(name: String, typeHint: Option[FnTypeHint], body: FnBody, retTypeHint: Option[TypeHint]) extends Level1Declaration
  case class Module(seq: Seq[Level1Declaration]) extends ParseNode
}
