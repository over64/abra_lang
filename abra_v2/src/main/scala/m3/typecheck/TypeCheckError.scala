package m3.typecheck

import m3.parse.Ast0.TypeHint
import m3.parse.AstInfo

sealed trait TypeCheckError extends Exception

object TCE {
  case class FieldNameNotUnique(location: AstInfo) extends TypeCheckError
  case class RetTypeHintRequired(location: AstInfo) extends TypeCheckError
  case class NoSuchModulePath(location: AstInfo) extends TypeCheckError
  case class NoSuchType(location: AstInfo, mod: Seq[String], name: String) extends TypeCheckError {
    override def toString: String = s"$location No such type with name ${mod.mkString(".")}.$name"
  }
  case class NoSuchVar(location: AstInfo) extends TypeCheckError
  case class NoSuchSymbol(location: AstInfo, name: String) extends TypeCheckError {
    override def toString: String = s"$location No local var, argument or function with name $name"
  }
  case class NoSuchCallable(location: AstInfo, name: String) extends TypeCheckError {
    override def toString: String = s"$location No such function with name $name"
  }
  case class ParamsCountMismatch(location: AstInfo) extends TypeCheckError

  case class ArgsCountMismatch(location: Seq[AstInfo], expected: Int, has: Int) extends TypeCheckError {
    override def toString: String = s"Arg count mismatch: expected $expected has $has\n${location.map(l => s"at $l").mkString("\n")}"
  }

  case class TypeMismatch(location: Seq[AstInfo], expected: TypeHint, has: TypeHint) extends TypeCheckError {
    override def toString: String = s"Type mismatch: expected $expected has $has\n${location.map(l => s"at $l").mkString("\n")}"
  }
  case class RetTypeMismatch(location: AstInfo, expected: TypeHint, has: TypeHint) extends TypeCheckError {

  }
}
