package m3

import m3.Ast0.Module
import m3._01parse.CircularException

import scala.collection.mutable

case class Level(modules: mutable.HashMap[String, Module], var next: Option[Level]) {
  def append(level: Level): Unit =
    next match {
      case Some(n) => n.append(level)
      case None => next = Some(level)
    }

  def findMod(path: String): Option[Module] =
    modules.get(path) match {
      case s: Some[Module] => s
      case None =>
        next.flatMap(level => level.findMod(path))
    }

  def stealMod(path: String): Option[Module] =
    modules.get(path) match {
      case s: Some[Module] =>
        val removed = modules.remove(path)

        if (modules.keys.isEmpty) {
          modules.put(path, removed.get)
          throw new CircularException()
        }

        s
      case None =>
        next.flatMap { level =>
          level.stealMod(path)
        }
    }

  def eachModule(callback: (Level, Module) => Unit): Unit = {
    next.foreach(level => level.eachModule(callback))
    modules.values.foreach(module => callback(this, module))
  }
}
