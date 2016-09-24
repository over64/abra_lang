package lang_m2

import lang_m2.Ast0._

import scala.collection.mutable
import TypeCheckerUtil._

/**
  * Created by over on 01.09.16.
  */
/*
  for Ast1:
  Ast1Fn = LocalFn | ExternFn

  Правила оверлоадинга:
   - add var - проверить что в локальном scope нет переменной с таким именем
   - add anon function - no problems
   - add type - в typeMap нет типа с таким именем
   - add self function - функции с таким именем нет для данного типа
   - add function - нет функции с таким именем в functions и нет self функции с таким именем
  Правила поиска:
   - find self function - ищем тип self-аргумента -> ищем по имени
   - find function - рекурсивно ищем в локальных scope символы с типом FnTypeHint иначе ищем в globalScope.functions
   - find var - рекурсивно ищем в локальных scope
 */

sealed trait FnInfo
sealed trait CallableFn {
  val _package: String
  val th: FnTypeHint
}
case class RawFn(_package: String, fn: Fn) extends FnInfo
case class HeaderFn(_package: String, th: FnTypeHint) extends FnInfo with CallableFn
case class InferedFn(_package: String, th:FnTypeHint, lowFn: Ast1.Fn) extends FnInfo with CallableFn

case class FnContainer(var fnInfo: FnInfo)
case class TypeInfo(_type: Type, _package: String, selfFunctions: mutable.HashMap[String, FnContainer])
class GlobalScope(val types: Map[String, TypeInfo],
                  val functions: mutable.HashMap[String, FnContainer],
                  val imports: Map[String, HeaderFn],
                  val anonFunctions: mutable.HashMap[String, InferedFn] = mutable.HashMap())


case class SymbolInfo(isMutable: Boolean, location: SymbolLocation, th: TypeHint)
case class SymbolKey(name: String, classifier: Option[TypeHint])

sealed trait SymbolLocation
case object LocalSymbol extends SymbolLocation
case object ParamSymbol extends SymbolLocation
case object GlobalSymbol extends SymbolLocation

class LocalScope(parent: Option[LocalScope], val vars: mutable.HashMap[String, SymbolInfo] = mutable.HashMap()) {
  def mkChild = new LocalScope(parent = Some(this))

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

class Scope(global: GlobalScope, local: LocalScope = new LocalScope(None)) {
  def mkChild() = new Scope(global, local.mkChild)
  def toLow(th: TypeHint) = TypeCheckerUtil.toLow(global.types, th)

  def types = global.types
  def functions = global.functions
  def anonFunctions = global.anonFunctions

  def addVar(node: ParseNode, name: String, th: TypeHint, isMutable: Boolean, location: SymbolLocation) =
    local.addVar(node, name, th, isMutable, location)

  def findVar(name: String) = local.findVar(name)

  def resolveType(th: TypeHint): Type =
    global.types.getOrElse(th.name, throw new CompileEx(th, CE.TypeNotFound(th.name)))._type

  def findSelfFn(name: String, selfType: TypeHint, inferCallback: RawFn => CallableFn): Option[CallableFn] =
    global.types.get(selfType.name).flatMap { ti =>
      ti.selfFunctions.get(name).map(_.fnInfo)
    }.map {
      case fn: RawFn => inferCallback(fn)
      case fn: HeaderFn => fn
      case fn: InferedFn => fn
    }

  def findFn(name: String, inferCallback: RawFn => CallableFn): Option[CallableFn] =
    global.functions.get(name).map { fnContainer =>
      fnContainer.fnInfo match {
        case fn: RawFn => inferCallback(fn)
        case fn: HeaderFn => fn
        case fn: InferedFn => fn
      }
    }

  def inferSelfFn(name: String, selfType: TypeHint, inferedFn: InferedFn) =
    global.types(selfType.name).selfFunctions(name).fnInfo = inferedFn

  def inferFn(name: String, inferedFn: InferedFn) =
    global.functions(name).fnInfo = inferedFn

  def inferAnonFn(name: String, inferedFn: InferedFn) =
    global.anonFunctions += (name -> inferedFn)
}