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
    def specCall(call: Call, routine: (Closure) => Seq[Closure]) = {
      val fnName = call.i.toFnName
      if (Seq("Mem", "Mem.store", "Mem.load").contains(fnName)) {

      } else routine(map(fnName))
    }

    def findFnRet(typeParams: Seq[Type], id: Id): Type =
      id.toFnName match {
        case "Mem.store" => Scalar("void")
        case "Mem.load" => typeParams(0)
        case "Int.+" => Scalar("i32")
        case "Int.toString" => Scalar("String")
        case name =>
          val c = map(name)
          c.ret match {
            // case s: Spec => s.specialize(typeParams)
            case other => other
          }
      }
  }

  implicit class ExtId(id: Id) {
    def toFnName: String =
      (id.v +: id.props).mkString(".")
  }

  def argsToString(args: Seq[Field]) =
    args.map(a => s"${a.name}: ${a._type.name}").mkString(", ")

  def evalStat(ctx: Ctx, stat: Stat): Unit = stat match {
    case c: Closure =>
      ctx.line(s"${c.id.toFnName} = \\${argsToString(c.args)} -> ${c.ret.name}")
      c.code.foreach(stat => evalStat(ctx.deeper, stat))
    case call: Call =>
      val specsStr = if (call.specs.isEmpty) "" else s"[${call.specs.map(_.name).mkString(", ")}]"

      ctx.findFnRet(call.specs, call.i) match {
        case Scalar("void") =>
          ctx.line(s"${call.i.toFnName}$specsStr(${call.args.map(_.irVal).mkString(", ")})")
        case t: Type =>
          ctx.line(s"${call.args.head.irVal}: ${t.name} = ${call.i.toFnName}$specsStr(${call.args.drop(1).map(_.irVal).mkString(", ")})")
      }
    case _ => ctx.line("unknown stat")
  }
  implicit class RichModule(map: Map[String, Closure]) {
    def evalDebug(out: PrintStream): Unit = {
      val ctx = Ctx(map, out, level = 0)
      ctx.specCall(Call('main), { c =>
        evalStat(ctx, c)
        Seq()
      })
    }
  }
}
