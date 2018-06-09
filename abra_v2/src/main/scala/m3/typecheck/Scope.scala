package m3.typecheck


import m3.codegen.Ast2
import m3.parse.Ast0._
import Util._

import scala.collection.mutable

/**
  * Created by over on 21.10.17.
  */
sealed trait Location
case object Local extends Location
case object Param extends Location

case class VarInfo(th: TypeHint, lowTh: Ast2.TypeRef, location: Location)
trait Scope {
  val vars = mutable.HashMap[String, VarInfo]()
  val parent: Option[Scope]
  val children = mutable.ListBuffer[Scope]()

  def mkChild[T <: Scope](onParent: Scope => T): T = {
    val child = onParent(this)
    children += child
    child
  }

  def findVarOpt(vName: String): Option[VarInfo] =
    vars.get(vName) match {
      case s@Some(vi) => s
      case None =>
        parent match {
          case Some(p) => p.findVarOpt(vName)
          case None => None
        }
    }
  def findVar(vName: String): VarInfo =
    findVarOpt(vName).getOrElse(throw new RuntimeException(s"var $vName not found"))

  //  def up(root: Boolean): Seq[(String, VarInfo)]
  def self: Seq[(String, VarInfo)] = vars.toSeq
  def down(root: Boolean): Seq[(String, VarInfo)]

  def setRet(th: TypeHint)
}

class FnScope(val parent: Option[Scope]) extends Scope {
  val retTypes = mutable.ListBuffer[TypeHint]()
  val closures = mutable.HashMap[String, VarInfo]()


  def addParam(ctx: TContext, namespace: Namespace, name: String, th: TypeHint) = {
    vars += ((name, VarInfo(th, th.toLow(ctx, namespace), Param)))
  }

  override def findVarOpt(vName: String): Option[VarInfo] =
    vars.get(vName) match {
      case s@Some(vi) => s
      case None =>
        parent match {
          case Some(p) =>
            p.findVarOpt(vName) match {
              case s@Some(vi) =>
                closures.put(vName, vi); s
              case None => None
            }
          case None => None
        }
    }

  //  override def up(root: Boolean): Seq[(String, VarInfo)] =
  //    parent.map(p => p.up(false)).getOrElse(Seq.empty) ++ (if (root) Seq.empty else self)

  override def down(root: Boolean): Seq[(String, VarInfo)] =
    if (root) children.map(_.down(false)).flatten
    else Seq()

  override def setRet(th: TypeHint) =
    retTypes += th
}

class BlockScope(val parent: Option[Scope]) extends Scope {
  def addLocal(ctx: TContext, namespace: Namespace, name: String, th: TypeHint): VarInfo = {
    val vi = VarInfo(th, th.toLow(ctx, namespace), Local)
    vars.put(name, vi)
    vi
  }

  //  override def up(root: Boolean): Seq[(String, VarInfo)] =
  //    parent.map(p => p.up(false)).getOrElse(Seq.empty) ++ self

  override def down(root: Boolean): Seq[(String, VarInfo)] =
    self ++ children.map(_.down(false)).flatten

  override def setRet(th: TypeHint) =
    parent.foreach(p => p.setRet(th))
}

// def main =
//   x = 1
//   if true do
//     y = 2
//     bar(1, \z, y -> x + 1)
//
// Fn('main'): params = [], locals = [x]
//   Block('if'): params = [], locals = [y]
//     Fn('bar'): params = [z, y], locals = [x]
// enumeration pass:
//
// Fn('main'): params = [], locals = [x]
//   Block('if'): params = [], locals = [_y]
//     Fn('bar'): params = [__z, __y], locals = []
//
// down('main'): locals = [x, _y]
// up('bar'): closure-params = [], closure-locals = [_y, x]
// bar:
//   up: closure-params = [], closure-locals = [_y, x]
//   self: params = [__z, __y]
//   down: locals = []