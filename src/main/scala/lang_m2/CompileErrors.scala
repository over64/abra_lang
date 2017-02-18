package lang_m2

import lang_m2.Ast0.ScalarTypeHint
import lang_m2.TypeCheckerUtil.Type

/**
  * Created by over on 25.08.16.
  */
sealed trait CompileError
object CE {
  case class AssemblyError(msg: String) extends CompileError {
    override def toString = msg
  }
  case class LinkError(msg: String) extends CompileError {
    override def toString = msg
  }
  //FIXME: include dirs
  case class ImportNotResolved(modPath: String, includes: Seq[String]) extends CompileError {
    override def toString = s"could not resolve import: $modPath with includes \n ${includes.mkString("\n")}"
  }
  case class FnlTypeNotEntensible() extends CompileError {
    override def toString = s"function pointer type cannot have self-functions"
  }
  case class AlreadyDefined(symbolName: String) extends CompileError {
    override def toString = s"symbol with name $symbolName already defined"
  }
  case class VarNotFound(varName: String) extends CompileError {
    override def toString = s"variable with name $varName not found"
  }
  case class ExprTypeMismatch(expected: String, has: String) extends CompileError {
    override def toString = s"expected expression of type $expected has $has"
  }
  case class NoFnToCall(fnName: String) extends CompileError {
    override def toString = s"cannot find candidate for call $fnName"
  }
  case class NotCallable(fnName: String) extends CompileError {
    override def toString = s"$fnName is not callable"
  }
  case class ReassignToVal() extends CompileError {
    override def toString = s"reassignment to val"
  }
  case class PropNotFound(propName: String, typeName: String) extends CompileError {
    override def toString = s"cannot find field $propName in type $typeName"
  }
  case class BranchTypesNotEqual() extends CompileError {
    override def toString = "expected equal types in if-else branches"
  }
  case class ExpectedTypeHint() extends CompileError {
    override def toString = "expected type hint"
  }
  case class NeedExplicitTypeDefinition() extends CompileError {
    override def toString = "function with llvm body definition must have explicit return type declaration"
  }
  case class TypeNotFound(sth: ScalarTypeHint) extends CompileError {
    override def toString = s"type with name ${sth.name} not found (${sth._package}${sth.name})"
  }
  case class ExpectedStructType(sth: ScalarTypeHint) extends CompileError {
    override def toString = s"expected struct type, got ${sth._package}${sth.name}"
  }
}
