package lang_m2

object Ast0 {
  case class AstInfo(line: Int, col: Int)
  sealed trait ParseNode {
    val info: AstInfo
  }
  sealed trait BlockExpression extends ParseNode
  sealed trait Expression extends BlockExpression
  sealed trait FnBody
  sealed trait Level1Declaration extends ParseNode

  sealed trait Literal extends Expression {
    val value: String
  }
  case class lInt(info: AstInfo, value: String) extends Literal
  case class lFloat(info: AstInfo, value: String) extends Literal
  case class lBoolean(info: AstInfo, value: String) extends Literal
  case class lString(info: AstInfo, value: String) extends Literal
  case class lId(info: AstInfo, value: String) extends Literal

  sealed trait Type extends Level1Declaration {
    val name: String
  }

  case class ScalarType(info: AstInfo, name: String, llType: String) extends Type
  case class TypeField(info: AstInfo, isSelf: Boolean, name: String, typeHint: TypeHint) extends ParseNode
  case class FactorType(info: AstInfo, name: String, fields: Seq[TypeField]) extends Type

  sealed trait TypeHint extends ParseNode {
    val name: String
  }
  case class ScalarTypeHint(info: AstInfo, name: String) extends TypeHint
  case class FnTypeHintField(info: AstInfo, name: String, typeHint: TypeHint) extends ParseNode
  case class FnTypeHint(info: AstInfo, seq: Seq[FnTypeHintField], ret: TypeHint) extends TypeHint {
    override val name: String = s"fn${seq.map(_.typeHint.name).mkString("_")}__${ret.name}"
  }

  case class Access(seq: Seq[Literal]) extends Expression {
    override val info: AstInfo = seq.head.info
  }
  case class Tuple(info: AstInfo, seq: Seq[Expression]) extends Expression
  case class Call(info: AstInfo, fnName: String, args: Tuple) extends Expression
  case class Block(info: AstInfo, args: Seq[FnArg], seq: Seq[BlockExpression]) extends Expression with FnBody

  case class Cond(info: AstInfo, ifCond: Expression, _then: Block, _else: Option[Block]) extends Expression
  case class While(info: AstInfo, cond: Expression, _then: Block) extends BlockExpression

  case class Store(info: AstInfo, to: Access, what: Expression) extends BlockExpression
  case class Val(info: AstInfo, mutable: Boolean, name: String, typeHint: Option[TypeHint], init: Expression) extends BlockExpression

  case class FnArg(info: AstInfo, name: String, typeHint: Option[TypeHint]) extends ParseNode
  case class LlInline(info: AstInfo, value: String) extends FnBody
  case class Fn(info: AstInfo, name: String, typeHint: Option[FnTypeHint], body: FnBody, retTypeHint: Option[TypeHint]) extends Level1Declaration

  case class Module(info: AstInfo, seq: Seq[Level1Declaration]) extends ParseNode
}
