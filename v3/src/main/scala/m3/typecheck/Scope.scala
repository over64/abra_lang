package m3.typecheck

import m3.parse.Ast0.{Def, Module, TypeHint}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

sealed trait VarType
case class VarDefLocal(fn: Def) extends VarType
case class VarDefImport(mod: Module) extends VarType
case object VarParam extends VarType
case object VarLocal extends VarType
case class VarClosureParam(nested: Boolean) extends VarType
case class VarClosureLocal(nested: Boolean) extends VarType

sealed trait Scope {
  def findVar(varName: String): Option[(TypeHint, VarType)]
  def addRetType(th: TypeHint): Unit
}

case class DefScope(params: Map[String, TypeHint],
                    retTypes: mutable.ListBuffer[TypeHint] = ListBuffer.empty) extends Scope {

  def findVar(varName: String): Option[(TypeHint, VarType)] =
    params.get(varName).map(th => (th, VarParam))

  def addRetType(th: TypeHint): Unit =
    retTypes.append(th)

}

case class LambdaScope(parent: Scope,
                       params: Map[String, TypeHint],
                       closure: mutable.HashMap[String, (TypeHint, VarType)] = mutable.HashMap(),
                       retTypes: mutable.ListBuffer[TypeHint] = ListBuffer.empty) extends Scope {

  def findVar(varName: String): Option[(TypeHint, VarType)] =
    params.get(varName) match {
      case Some(th) =>
        Some((th, VarParam))
      case None =>
        parent.findVar(varName).map {
          case (th, VarParam) => (th, VarClosureParam(false))
          case (th, VarLocal) => (th, VarClosureLocal(false))
          case (th, VarClosureLocal(_)) => (th, VarClosureLocal(true))
          case (th, VarClosureParam(_)) => (th, VarClosureParam(true))
        }.map { res =>
          closure.put(varName, res)
          res
        }
    }

  def addRetType(th: TypeHint): Unit =
    retTypes.append(th)
}

class BlockScope(val parent: Scope,
                 vars: mutable.HashMap[String, TypeHint] = mutable.HashMap.empty) extends Scope {

  def findVar(varName: String): Option[(TypeHint, VarType)] =
    vars.get(varName) match {
      case Some(th) => Some((th, VarLocal))
      case None => parent.findVar(varName)
    }

  def addLocal(varName: String, th: TypeHint): Unit =
    vars.put(varName, th)

  def addRetType(th: TypeHint): Unit =
    parent.addRetType(th)
}


class WhileScope(parent: Scope) extends BlockScope(parent, mutable.HashMap.empty)
