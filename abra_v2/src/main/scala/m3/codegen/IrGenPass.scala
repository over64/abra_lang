package m3.codegen

import java.io.PrintStream

import m3.parse.Ast0._
import m3.parse.Level

import scala.collection.mutable

trait OutConf {
  def withStream[T](module: Module, op: PrintStream => T): T
}

class IrGenPass {
  object LowBuiltin {
  }

  import IrUtils._

  case class PassContext(deep: Int, out: PrintStream, level: Level, module: Module)

  def passDef(ctx: PassContext, fn: Def): Unit = {

  }

  def pass(root: Level, outConf: OutConf): Unit =
    root.eachModule((level, module) => {
      outConf.withStream(module, { out =>
        val ctx = PassContext(0, out, level, module)

        val typeHintOrder = mutable.ListBuffer[TypeHint]()
        DumpTypeHints.dump(module).foreach(th => th.orderTypeHints(level, module, typeHintOrder))

        val uniqueTh = typeHintOrder.distinct
        val typeDecl = mutable.ListBuffer[String]()

        uniqueTh.foreach(th => th.toDecl(level, module, uniqueTh, typeDecl))
        typeDecl.foreach(td => ctx.out.println(td))


        module.defs.values.foreach(fn => passDef(ctx, fn))
        module.selfDefs.values.foreach(defs => defs.foreach(fn => passDef(ctx, fn)))
      })
    })
}
