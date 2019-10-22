package m3

import scala.collection.immutable.ArraySeq
import scala.collection.mutable.HashMap

case class AstInfo(source: Array[String], fname: String, line: Int, col: Int, lineEnd: Int, colEnd: Int) {
  override def toString: String = s"$fname.eva:$line:$col"
}

object Ast0 {
  sealed trait ParseNode {
    val meta: HashMap[String, Any] = HashMap()
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
    val params: ArraySeq[GenericTh]
  }

  case class ScalarDecl(ref: Boolean, params: ArraySeq[GenericTh], name: String, llType: String) extends TypeDecl
  case class FieldDecl(isSelf: Boolean, name: String, th: TypeHint) extends ParseNode
  case class StructDecl(params: ArraySeq[GenericTh], name: String, fields: ArraySeq[FieldDecl]) extends TypeDecl
  case class UnionDecl(params: ArraySeq[GenericTh], name: String, variants: ArraySeq[TypeHint]) extends TypeDecl

  sealed trait TypeHint extends ParseNode
  case class ScalarTh(params: ArraySeq[TypeHint], name: String, ie: Option[String], declaredIn: String) extends TypeHint {
    override def toString: String = s"${ie.map(s => s + ".").getOrElse("")}$name${if (params.isEmpty) "" else params.mkString("[", ", ", "]")}"

    override def equals(obj: Any): Boolean =
      obj match {
        case sth: ScalarTh =>
          this.params == sth.params &&
            this.name == sth.name &&
            this.declaredIn == sth.declaredIn
        case _ => false
      }

    override def hashCode(): Int = (params, name, declaredIn).hashCode()
  }
  case class FieldTh(name: String, typeHint: TypeHint) extends ParseNode {
    override def toString: String = s"$name: $typeHint"
  }
  case class StructTh(seq: ArraySeq[FieldTh]) extends TypeHint {
    override def toString: String = seq.mkString("(", ", ", ")")
  }
  case class UnionTh(seq: ArraySeq[TypeHint]) extends TypeHint {
    override def toString: String = seq.map {
      case unionTh: UnionTh => "(" + unionTh + ")"
      case other => other.toString
    }.mkString(" | ")
  }

  case class GenericTh(var typeName: String, var isAnon: Boolean = false) extends TypeHint {
    override def toString: String = typeName
  }
  case object AnyTh extends TypeHint {
    override def toString: String = "_"
  }

  case class FnTh(args: ArraySeq[TypeHint], ret: TypeHint) extends TypeHint {
    override def toString: String =
      args.mkString("(", ", ", ")") + " -> " + ret
  }

  case class Prop(from: Expression, props: ArraySeq[lId]) extends Expression
  case class Tuple(seq: ArraySeq[Expression]) extends Expression
  case class Cons(sth: ScalarTh, args: ArraySeq[Expression]) extends Expression
  case class SelfCall(fnName: String, self: Expression, args: ArraySeq[Expression]) extends Expression
  case class Call(expr: Expression, args: ArraySeq[Expression]) extends Expression

  case class AbraCode(seq: ArraySeq[Expression]) extends FnBody
  case class Lambda(args: ArraySeq[Arg], body: FnBody) extends Expression

  sealed trait AndOr {
    val left: Expression
    val right: Expression
  }
  case class And(left: Expression, right: Expression) extends Expression with AndOr
  case class Or(left: Expression, right: Expression) extends Expression with AndOr
  case class If(cond: Expression, _do: ArraySeq[Expression], _else: ArraySeq[Expression]) extends Expression
  case class Is(vName: Option[lId], typeRef: TypeHint, _do: ArraySeq[Expression]) extends ParseNode
  case class Unless(expr: Expression, is: ArraySeq[Is]) extends Expression
  case class While(cond: Expression, _do: ArraySeq[Expression]) extends Expression
  case class Store(var th: TypeHint, to: ArraySeq[lId], what: Expression) extends Expression
  case class Ret(what: Option[Expression]) extends Expression
  case class Break() extends Expression
  case class Continue() extends Expression
  case class Arg(name: String, var typeHint: TypeHint) extends ParseNode
  case class Def(name: String, lambda: Lambda, var retTh: TypeHint) extends Level1Declaration
  case class ImportEntry(modName: String, path: String, withTypes: ArraySeq[String]) extends ParseNode
  case class Import(seq: ArraySeq[ImportEntry]) extends ParseNode
  case class Module(pkg: String,
                    imports: Import,
                    lowCode: ArraySeq[llVm],
                    types: Map[String, TypeDecl],
                    defs: Map[String, Def],
                    selfDefs: Map[String, ArraySeq[Def]]) extends ParseNode
}
