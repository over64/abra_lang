package m3.typecheck

import m3.parse.Ast0.{Module, ScalarDecl, StructDecl, UnionDecl}
import m3.parse.Level


class TypeCheckPass {

  def checkTypeDecl(level: Level, module: Module) = {
    module.types.foreach {
      case sc: ScalarDecl =>
      case st: StructDecl =>
      case un: UnionDecl =>

    }
  }

  def passModule(level: Level, module: Module) = {
    checkTypeDecl(level, module)
  }

  def pass(root: Level) =
    root.eachModule(passModule)
}