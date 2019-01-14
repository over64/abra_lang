package m3.parse

import scala.collection.mutable

case class AstInfo(fname: String, line: Int, col: Int)

object Ast0 {
  sealed trait ParseNode {
    val meta: mutable.HashMap[String, Object] = new mutable.HashMap()
  }

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

  // Struct[String]("1", "2", "3") -> Cons(ScalarTh('String', []), [lInt('1'), lInt('2'), lInt('3')])

  case class ScalarDecl(ref: Boolean, params: Seq[GenericTh], name: String, llType: String) extends TypeDecl
  case class FieldDecl(isSelf: Boolean, name: String, th: TypeHint) extends ParseNode
  case class StructDecl(params: Seq[GenericTh], name: String, fields: Seq[FieldDecl]) extends TypeDecl
  case class UnionDecl(params: Seq[GenericTh], name: String, variants: Seq[TypeHint]) extends TypeDecl

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
  case class Cons(sth: ScalarTh, args: Seq[Expression]) extends Expression
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
  case class Is(vName: Option[lId], typeRef: TypeHint, _do: Seq[Expression]) extends ParseNode
  case class Unless(expr: Expression, is: Seq[Is]) extends Expression
  case class While(cond: Expression, _do: Seq[Expression]) extends Expression
  case class Store(var th: TypeHint, to: Seq[lId], what: Expression) extends Expression
  case class Ret(what: Option[Expression]) extends Expression
  case class Break() extends Expression
  case class Continue() extends Expression
  case class Arg(name: String, var typeHint: TypeHint) extends ParseNode
  case class Def(name: String, lambda: Lambda, var retTh: TypeHint) extends Level1Declaration
  case class ImportEntry(modName: String, path: String, withTypes: Seq[String]) extends ParseNode
  case class Import(seq: Seq[ImportEntry]) extends ParseNode
  case class Module(pkg: String, imports: Import, lowCode: Seq[llVm], types: Seq[TypeDecl], defs: Seq[Def]) extends ParseNode
}
