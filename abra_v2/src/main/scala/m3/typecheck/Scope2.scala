package m3.typecheck

import m3.parse.Ast0.TypeHint

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

sealed trait VarType
case object VarParam extends VarType
case object VarLocal extends VarType
// FIXME: remove object after remove Scope1
object Scope2 {
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

  case class BlockScope(parent: Option[Scope],
                        vars: mutable.HashMap[String, TypeHint] = mutable.HashMap.empty) extends Scope {

    def findVar(varName: String): Option[(TypeHint, VarType)] =
      vars.get(varName) match {
        case Some(th) => Some((th, VarLocal))
        case None =>
          parent match {
            case Some(p) => p.findVar(varName)
            case None => None
          }
      }

    def addLocal(varName: String, th: TypeHint): Unit =
      vars.put(varName, th)

    def addRetType(th: TypeHint): Unit =
      parent.foreach(p => p.addRetType(th))
  }
}
