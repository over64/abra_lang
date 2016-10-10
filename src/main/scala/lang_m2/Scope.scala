package lang_m2

import lang_m2.Ast0.{ParseNode, TypeHint}

import scala.collection.mutable

/**
  * Created by over on 25.09.16.
  */
case class SymbolInfo(lowName: String, isMutable: Boolean, location: SymbolLocation, th: TypeHint)
case class SymbolKey(name: String, classifier: Option[TypeHint])

sealed trait SymbolLocation
case object LocalSymbol extends SymbolLocation
case object ParamSymbol extends SymbolLocation
case object GlobalSymbol extends SymbolLocation

case class Scope(parent: Option[Scope], level: Int = 0, val vars: mutable.HashMap[String, SymbolInfo] = mutable.HashMap()) {
  def mkChild = new Scope(parent = Some(this), level + 1)

  def addVar(node: ParseNode, name: String, th: TypeHint, isMutable: Boolean, location: SymbolLocation, varNumber: Int): String = {
    if (vars.contains(name)) throw new CompileEx(node, CE.AlreadyDefined(name))
    val lowName = location match {
      case LocalSymbol => s"${name}_$varNumber"
      case _ => name
    }
    vars += (name -> SymbolInfo(lowName, isMutable, location, th))
    lowName
  }

  def findVar(name: String): Option[SymbolInfo] = {
    var found: Option[SymbolInfo] = vars.get(name)
    if (found == None)
      parent.map(parent => found = parent.findVar(name))

    found
  }
}
