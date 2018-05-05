package m3.typecheck

import m3.codegen.IrUtil

import scala.collection.mutable

class TContext {
  var nextAnonId = new (() => Int) {
    var idSeq = 0

    override def apply(): Int = {
      idSeq += 1
      idSeq
    }
  }
  val inferStack = mutable.Stack[String]()
  val lowMod = IrUtil.Mod()
}
