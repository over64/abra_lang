package lang_m2

import lang_m2.Ast0.{ParseNode, TypeHint}
import lang_m2.TypeCheckerUtil.Type

import scala.collection.mutable

/**
  * Created by over on 25.09.16.
  */
case class SymbolInfo(lowName: String, isMutable: Boolean, location: SymbolLocation, stype: Type)
case class SymbolKey(name: String, classifier: Option[TypeHint])

sealed trait SymbolLocation
sealed trait ClosurableLocation
case object LocalSymbol extends SymbolLocation with ClosurableLocation
case object ParamSymbol extends SymbolLocation with ClosurableLocation
case object ClosureSymbol extends SymbolLocation with ClosurableLocation
case object GlobalSymbol extends SymbolLocation

sealed trait Scope {
  val parent: Option[Scope]
  val vars: mutable.HashMap[String, SymbolInfo]

  def addVar(node: ParseNode, name: String, vtype: Type, isMutable: Boolean, location: SymbolLocation, varNumber: Int): String = {
    if (vars.contains(name)) throw new CompileEx(node, CE.AlreadyDefined(name))
    val lowName = location match {
      case LocalSymbol => s"${name}_$varNumber"
      case _ => name
    }
    vars += (name -> SymbolInfo(lowName, isMutable, location, vtype))
    lowName
  }

  def findVar(name: String): Option[SymbolInfo]
}

case class BlockScope(parent: Option[Scope], vars: mutable.HashMap[String, SymbolInfo] = mutable.HashMap()) extends Scope {
  def findVar(name: String): Option[SymbolInfo] = {
    var found: Option[SymbolInfo] = vars.get(name)
    if (found == None)
      parent.map(parent => found = parent.findVar(name))

    found
  }
}

case class FnScope(parent: Option[Scope], vars: mutable.HashMap[String, SymbolInfo] = mutable.HashMap()) extends Scope {
  val closuredVars = mutable.ListBuffer[SymbolInfo]()
  def findVar(name: String): Option[SymbolInfo] = {
    var found: Option[SymbolInfo] = vars.get(name)
    if (found == None)
      parent.map { parent =>
        found = parent.findVar(name).map { si =>
          if (!closuredVars.contains(si))
            closuredVars += si
          SymbolInfo(si.lowName, si.isMutable, ClosureSymbol, si.stype)
        }
      }

    found
  }

  def closured(closureTypeName: String): Seq[SymbolInfo] = closuredVars
}
