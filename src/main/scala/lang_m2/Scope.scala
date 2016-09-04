package lang_m2

import lang_m2.Ast0._

import scala.collection.mutable
import TypeCheckerUtil._

/**
  * Created by over on 01.09.16.
  */
case class SymbolInfo(isMutable: Boolean, location: SymbolLocation, th: TypeHint)
case class SymbolKey(name: String, classifier: Option[TypeHint])

sealed trait SymbolLocation
case object LocalSymbol extends SymbolLocation
case object ParamSymbol extends SymbolLocation
case object GlobalSymbol extends SymbolLocation

class Scope(parent: Option[Scope],
            types: Map[String, Type] = Map(),
            rawFunctions: Map[String, Seq[Fn]] = Map(),
            symbols: mutable.Map[SymbolKey, SymbolInfo] = mutable.HashMap()) {

  def global: Scope = parent.getOrElse(this)
  def mkChild(): Scope = new Scope(Some(this))

  def findType(typeName: String): Option[Type] = {
    val found = types.get(typeName)

    if (found != None) found
    else parent.flatMap(_.findType(typeName))
  }

  def resolveType(th: TypeHint): Type =
    findType(th.name).getOrElse(throw new CompileEx(th, CE.TypeNotFound(th.name)))


  def toLow(th: TypeHint): Ast1.Type =
    th match {
      case ScalarTypeHint(typeName) =>
        findType(typeName).getOrElse(throw new CompileEx(th, CE.TypeNotFound(typeName))) match {
          case ScalarType(_, llType) => Ast1.Scalar(llType)
          case FactorType(name, fields) => Ast1.Struct(name, fields.map { field =>
            Ast1.Field(field.name, toLow(field.typeHint))
          })
        }
      case FnTypeHint(seq, ret) =>
        Ast1.FnPointer(seq.map(arg => Ast1.Field(arg.name, toLow(arg.typeHint))), toLow(ret))
    }

  def findFn(name: String, firstArgTh: Option[TypeHint]): Option[FnTypeHint] = {
    val found = symbols.get(SymbolKey(name, firstArgTh))

    if (found != None) found.map(_.th.asInstanceOf[FnTypeHint])
    else parent.flatMap(_.findFn(name, firstArgTh))
  }

  def findRawFn(name: String, firstArgTh: Option[TypeHint]): Option[Fn] = {
    val found = rawFunctions.get(name).getOrElse(Seq()).find { fn =>
      inferFnArgs(fn).headOption.map(_.typeHint) == firstArgTh
    }
    if (found != None) found
    else parent.flatMap(_.findRawFn(name, firstArgTh))
  }

  def findOverloadedFunctions(name: String): Set[Option[TypeHint]] = {
    val infered = symbols.toSeq.filter {
      case (key, value) => key.name == name && value.th.isInstanceOf[FnTypeHint]
    }.map {
      case (key, value) => value.th.asInstanceOf[FnTypeHint].seq.headOption.map(_.typeHint)
    } ++ parent.map(_.findOverloadedFunctions(name)).getOrElse(Seq())

    val raw = rawFunctions.getOrElse(name, Seq()).map { fn =>
      inferFnArgs(fn).headOption.map(_.typeHint)
    }

    (infered ++ raw).toSet
  }

  def addSymbol(node: ParseNode, name: String, classifier: Option[TypeHint], th: TypeHint, location: SymbolLocation, isMutable: Boolean = false): Unit = {
    val key = SymbolKey(name, classifier)
    symbols.get(key).map { symbol =>
      throw new CompileEx(node, CE.AlreadyDefined(name))
    }

    symbols += ((key, SymbolInfo(isMutable, location, th)))
  }

  def addGlobalFn(node: ParseNode, name: String, classifier: Option[TypeHint], th: TypeHint): Unit =
    if (parent != None) parent.get.addGlobalFn(node, name, classifier, th)
    else addSymbol(node, name, classifier, th, GlobalSymbol, isMutable = false)

  def findVar(name: String): Option[SymbolInfo] = {
    val found = symbols.find {
      case (key, value) => key.name == name
    }.map(_._2)

    if (found != None) found
    else parent.flatMap(_.findVar(name))
  }
}