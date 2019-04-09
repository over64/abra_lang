package m3.codegen

import m3.parse.Ast0._
import m3.typecheck.TCMeta._

import scala.collection.mutable

object DumpTypeHints {

  implicit class ExpressionPassExtension(self: Expression) {
    def eachExpression(callback: Expression => Unit): Unit = self match {
      case literal: Literal =>
        callback(literal)
      case p@Prop(from, props) =>
        callback(p)
        from.eachExpression(callback)
      case Tuple(seq) =>
        seq.foreach(e => e.eachExpression(callback))
      case cons@Cons(sth, args) =>
        callback(cons)
        args.foreach(e => e.eachExpression(callback))
      case sc@SelfCall(fnName, selfExpr, args) =>
        callback(sc)
        selfExpr.eachExpression(callback)
      case c@Call(expr, args) =>
        callback(c)
        expr.eachExpression(callback)
        args.foreach(e => e.eachExpression(callback))
      case l@Lambda(args, body) =>
        callback(l)
        body match {
          case AbraCode(seq) => seq.foreach(e => e.eachExpression(callback))
          case _ =>
        }
      case a@And(left, right) =>
        callback(a)
        left.eachExpression(callback)
        right.eachExpression(callback)
      case o@Or(left, right) =>
        callback(o)
        left.eachExpression(callback)
        right.eachExpression(callback)
      case i@If(cond, _do, _else) =>
        callback(i)
        _do.foreach(e => e.eachExpression(callback))
        _else.foreach(e => e.eachExpression(callback))
      case u@Unless(expr, is) =>
        callback(u)
        expr.eachExpression(callback)
        is.foreach { i => i._do.foreach(e => e.eachExpression(callback)) }
      case w@While(cond, _do) =>
        callback(w)
        cond.eachExpression(callback)
        _do.foreach(e => e.eachExpression(callback))
      case s@Store(th, to, what) =>
        callback(s)
        what.eachExpression(callback)
      case r@Ret(what) =>
        callback(r)
        what.foreach(e => e.eachExpression(callback))
      case b@Break() => callback(b)
      case c@Continue() => callback(c)
    }

    def eachTypeHint(callback: TypeHint => Unit): Unit =
      self.eachExpression { expr =>
        expr.getTypeHintOpt[TypeHint].foreach(th => callback(th))
        expr match {
          case st: Store => callback(st.th)
          case u: Unless => u.is.foreach(i => callback(i.typeRef))
          case _ =>
        }
      }
  }

  implicit class DefPassExtension(self: Def) {
    def eachExpression(callback: Expression => Unit): Unit =
      self.lambda.body match {
        case AbraCode(seq) => seq.foreach(e => e.eachExpression(callback))
        case _ =>
      }

    def eachTypeHint(callback: TypeHint => Unit): Unit = {
      callback(self.getTypeHint)
      self.lambda.body match {
        case AbraCode(seq) => seq.foreach(e => e.eachTypeHint(callback))
        case _ =>
      }
    }
  }


  implicit class ModulePassExtension(self: Module) {
    def eachExpression(callback: Expression => Unit): Unit = {
      self.defs.values.foreach(d => d.eachExpression(callback))
      self.selfDefs.values.foreach { defs => defs.foreach(d => d.eachExpression(callback)) }
    }

    def eachTypeHint(callback: TypeHint => Unit): Unit = {
      self.defs.values.foreach(d => d.eachTypeHint(callback))
      self.selfDefs.values.foreach { defs => defs.foreach(d => d.eachTypeHint(callback)) }
    }
  }

  def dump(module: Module): Set[TypeHint] = {
    val buff = mutable.ListBuffer[TypeHint]()
    module.eachTypeHint(th => buff += th)
    buff.toSet
  }
}