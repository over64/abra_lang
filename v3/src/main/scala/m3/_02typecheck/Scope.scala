package m3._02typecheck

import m3.Ast0.{Def, Module, TypeHint}

import scala.collection.mutable.{ArrayBuffer, HashMap}

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
                    retTypes: ArrayBuffer[TypeHint] = ArrayBuffer.empty) extends Scope {

  def findVar(varName: String): Option[(TypeHint, VarType)] =
    params.get(varName).map(th => (th, VarParam))

  def addRetType(th: TypeHint): Unit =
    retTypes.append(th)

}

case class LambdaScope(parent: Scope,
                       params: Map[String, TypeHint],
                       closure: HashMap[String, (TypeHint, VarType)] = HashMap(),
                       retTypes: ArrayBuffer[TypeHint] = ArrayBuffer.empty) extends Scope {

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
                 vars: HashMap[String, TypeHint] = HashMap.empty) extends Scope {

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

class WhileScope(parent: Scope) extends BlockScope(parent, HashMap.empty)
