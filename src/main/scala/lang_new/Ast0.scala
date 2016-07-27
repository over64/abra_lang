package lang_new

sealed trait AST0
sealed trait FnBody extends AST0 {
  val id: Int
}
sealed trait CallArg

sealed trait Lang extends AST0 {
  val id: Int
}
sealed trait InitExpression

case class Uint(id: Int, i: Int) extends FnBody with CallArg with InitExpression {
  override def toString = i.toString
}
case class VarIdent(id: Int, name: String) extends FnBody with CallArg with InitExpression {
  override def toString = name

  //
  //  override def equals(arg: Any) = arg match {
  //    case VarIdent(_, name) => this.name == name
  //    case _ => false
  //  }
  //
  //  override def hashCode() = name.hashCode
}
case class FnIdent(name: String) extends AST0 {
  override def toString = name
}
case class Access(id: Int, seq: Seq[AST0]) extends FnBody with CallArg with InitExpression {
  override def toString = seq.mkString(".")
}
case class FnCall(id: Int, fn: FnIdent, args: Seq[CallArg]) extends FnBody with CallArg with InitExpression {
  override def toString =
    s"$fn(${args.mkString(",")})"
}

case class TypeIdent(name: String) {
  override def toString = name
}
sealed trait TypeDef extends Lang {
  val name: TypeIdent
}
case class LlvmTypeDef(val id: Int, name: TypeIdent, body: String) extends TypeDef {
  override def toString = s"type $name = { $body }"
}

case class Param(name: VarIdent, ttype: TypeIdent) extends AST0 {
  override def toString = s"$name : $ttype"
}
case class AbraTypeDef(val id: Int, name: TypeIdent, fields: Seq[Param]) extends TypeDef {
  override def toString = s"type $name = (${fields.mkString(", ")})"
}

case class ValDef(id: Int, name: VarIdent, ttype: TypeIdent, init: InitExpression) extends FnBody {
  override def toString = s"val $name: $ttype = $init"
}
case class VarDef(id: Int, name: VarIdent, ttype: TypeIdent, init: InitExpression) extends FnBody {
  override def toString = s"var $name: $ttype = $init"
}

sealed trait Fn extends Lang {
  val name: FnIdent
  val ret: Option[TypeIdent]
  val params: Seq[Param]
}
case class Params(seq: Seq[Param]) extends AST0
case class LlvmFn(val id: Int, name: FnIdent, params: Seq[Param], body: String, ret: Option[TypeIdent]) extends Fn {
  override def toString = s"def $name = { ${params.mkString("", ", ", " ->")}\n$body\n\t}${if (ret.isDefined) ":" + ret.get else ""}"
}

case class AbraFnBody(body: Seq[FnBody]) extends AST0
case class AbraFn(val id: Int, name: FnIdent, params: Seq[Param], body: Seq[FnBody], ret: Option[TypeIdent]) extends Fn {
  override def toString = s"def $name = { ${params.mkString("", ", ", " ->")}\n${body.mkString("\t\t", "\n\t\t", "")}\n\t}${if (ret.isDefined) ":" + ret.get else ""}"
}

case class Module(seq: Seq[Lang]) extends AST0 {
  override def toString =
    s"mod = [\n${seq.mkString("\t", "\n\t", "")}\n]"
}