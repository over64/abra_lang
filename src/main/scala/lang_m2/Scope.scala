package lang_m2

import lang_m2.Ast0.{ParseNode, TypeHint}

import scala.collection.mutable

/**
  * Created by over on 25.09.16.
  */
case class SymbolInfo(isMutable: Boolean, location: SymbolLocation, th: TypeHint)
case class SymbolKey(name: String, classifier: Option[TypeHint])

sealed trait SymbolLocation
case object LocalSymbol extends SymbolLocation
case object ParamSymbol extends SymbolLocation
case object GlobalSymbol extends SymbolLocation

class Scope(parent: Option[Scope], val vars: mutable.HashMap[String, SymbolInfo] = mutable.HashMap()) {
  def mkChild = new Scope(parent = Some(this))

  def addVar(node: ParseNode, name: String, th: TypeHint, isMutable: Boolean, location: SymbolLocation): Unit = {
    if (vars.contains(name)) throw new CompileEx(node, CE.AlreadyDefined(name))
    vars += (name -> SymbolInfo(isMutable, location, th))
  }

  def findVar(name: String): Option[SymbolInfo] = {
    var found: Option[SymbolInfo] = vars.get(name)
    if (found == None)
      parent.map(parent => found = parent.findVar(name))

    found
  }
}
