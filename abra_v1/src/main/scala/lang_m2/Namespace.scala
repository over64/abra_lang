package lang_m2

import lang_m2.Ast0._

import scala.collection.mutable
import TypeCheckerUtil._

import scala.collection.mutable.HashMap

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
case class FnContainer(var fnInfo: FnInfo)

sealed trait CallableFn {
  val _package: String
  val ftype: FnType
}
case class RawFn(_package: String, fn: Fn) extends FnInfo
case class HeaderFn(_package: String, ftype: FnType, lowFn: Ast1.HeaderFn) extends FnInfo with CallableFn
case class InferedFn(_package: String, ftype: FnType, lowFn: Ast1.Fn) extends FnInfo with CallableFn

class Namespace(val _package: String,
                val types: Map[ScalarTypeHint, Type],
                val extensions: HashMap[ScalarTypeHint, HashMap[String, FnContainer]],
                val functions: HashMap[String, HashMap[String, FnContainer]],
                val anonFunctions: HashMap[String, InferedFn] = mutable.HashMap()) {

  def toLow(etype: Type) = TypeCheckerUtil.toLow(etype)

  //  def resolveType(th: TypeHint): Type = th match {
  //    case th: ScalarTypeHint => types.getOrElse(th, throw new CompileEx(th, CE.TypeNotFound(th)))
  //    case fth: FnTypeHint =>
  //  }

  def findSelfFn(name: String, selfType: Type, inferCallback: RawFn => CallableFn): Option[CallableFn] = {
    val selfTh = selfType match {
      case UnionType(fullModName, name, _) => ScalarTypeHint(name, fullModName)
      case ScalarType(fullModName, name, _) => ScalarTypeHint(name, fullModName)
      case FactorType(fullModName, name, _) => ScalarTypeHint(name, fullModName)
      case fnPtr: FnType => throw new CompileEx(null, CE.FnlTypeNotEntensible())
    }
    extensions.get(selfTh).flatMap { fnMap =>
      fnMap.get(name)
    }.map { fnContainer =>
      fnContainer.fnInfo match {
        case fn: RawFn => inferCallback(fn)
        case fn: HeaderFn => fn
        case fn: InferedFn => fn
      }
    }
  }

  def findFn(name: String, inferCallback: RawFn => CallableFn, pkg: String = _package): Option[CallableFn] =
    functions.get(pkg).flatMap { byPkg =>
      byPkg.get(name)
    }.map { fnContainer =>
      fnContainer.fnInfo match {
        case fn: RawFn => inferCallback(fn)
        case fn: HeaderFn => fn
        case fn: InferedFn => fn
      }
    }

  def inferSelfFn(name: String, selfType: ScalarTypeHint, inferedFn: InferedFn) =
    extensions(selfType)(name).fnInfo = inferedFn

  def inferFn(name: String, inferedFn: InferedFn) =
    functions(_package)(name).fnInfo = inferedFn

  def inferAnonFn(name: String, inferedFn: InferedFn) =
    anonFunctions += (name -> inferedFn)
}