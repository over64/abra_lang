package lang_m2

import lang_m2.Ast0.TypeHint

/**
  * Created by over on 25.08.16.
  */
sealed trait CompileError
object CE {
  case class VarNotFound(varName: String) extends CompileError {
    override def toString = s"variable with name $varName not found"
  }
  case class ExprTypeMismatch(expected: TypeHint, has: TypeHint) extends CompileError {
    override def toString = s"expected expression of type ${expected.name} has ${has.name}"
  }
  case class NoFnToCall(fnName: String) extends CompileError {
    override def toString = s"cannot find candidato for call $fnName"
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
  case class NeedExplicitTypeDefinition() extends CompileError {
    override def toString = "function with llvm body definition must have explicit return type declaration"
  }
  case class TypeNotFound(typeName: String) extends CompileError {
    override def toString = s"type with name $typeName not found"
  }
}
