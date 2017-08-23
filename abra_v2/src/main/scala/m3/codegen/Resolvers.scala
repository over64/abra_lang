package m3.codegen

import m3.codegen.Ast1new._

import scala.collection.mutable

/**
  * Created by over on 28.07.17.
  */
// Динамический полиморфизм против статического?!
// Нужен ли нам микс как в скриптоте?
// Резолвер - поставщик .air кода + его кэш

case class ModuleSetResolver(modules: Seq[Module]) {
  case class ClosureRef(specs: Seq[TypeRef], pkg: String, self: Option[TypeRef], name: String)

  val typeCache = new mutable.HashMap[TypeRef, Type]()
  val closureCache = new mutable.HashMap[ClosureRef, Closure]()

  def typeRef(ref: TypeRef): Option[(Boolean, Type)] = {
    null
  }
  def closure(specs: Seq[TypeRef], pkg: String, self: Option[TypeRef], name: String): Option[(Boolean, Closure)] = {
    val key = ClosureRef(specs, pkg, self, name)
    closureCache.get(key) match {
      case Some(c) => Some((true, c))
      case None =>
        modules
          .find { m => m.pkg == pkg }
          .flatMap { m => m.closureMap.get((name, self)) }
          .map { c =>
            val specialized = c // TODO: do spec
            closureCache.put(key, specialized)
            (false, c)
          }
    }
  }
}
