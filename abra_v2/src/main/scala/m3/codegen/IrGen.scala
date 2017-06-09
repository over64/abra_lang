package m3.codegen

import java.io.{OutputStream, PrintStream, PrintWriter}

import m3.codegen.Ast1._

import scala.collection.mutable


/**
  * Created by over on 12.05.17.
  */
object IrGen {
  case class Ctx(map: Map[String, Closure], out: PrintStream, level: Int) {
    def line(code: String) = out.println("\t" * level + code)
    def deeper = Ctx(map, out, level + 1)
  }

  implicit class ExtId(id: Id) {
    def toFnName: String =
      (id.v +: id.props).mkString(".")
  }

  def evalStat(ctx: Ctx, stat: Stat): Unit = stat match {
    case c: Closure =>
      ctx.line(s"${c.id.toFnName} = \\ ->")
      c.code.foreach(stat => evalStat(ctx.deeper, stat))
    case _ => ctx.line("unknown stat")
  }
  implicit class RichModule(map: Map[String, Closure]) {
    def evalDebug(out: PrintStream): Unit = {
      val main = map("main")
      val ctx = Ctx(map, out, level = 0)
      evalStat(ctx, main)
    }
  }
}
