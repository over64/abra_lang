package m3.codegen

import java.io.{ByteArrayOutputStream, OutputStream, PrintStream}

import m3.parse.Ast0._

/**
  * Created by over on 27.09.17.
  */
object CodeGen {
  case class Ctx(module: Module,
                 typeStream: LineStream,
                 codeStream: LineStream)

  case class LineStream(level: Int, chan: ByteArrayOutputStream = new ByteArrayOutputStream()) {
    def line(line: String) = chan.write(("\t" * level + line + "\n").getBytes())
    def deeper = LineStream(level + 1)
    def flushTo(stream: LineStream) = chan.writeTo(stream.chan)
    def flushTo(stream: OutputStream) = chan.writeTo(stream)
  }

  def genFunction(ctx: Ctx, fn: Def) = {
    val fnStream = ctx.codeStream.deeper
    val varStream = fnStream.deeper
    val irStream = fnStream.deeper

    CodeWriter.writeFunction(fnStream, varStream, irStream, fn.name)

    fnStream.flushTo(ctx.codeStream)
  }
  def compile(module: Module, out: OutputStream): Unit = {
    val ctx = Ctx(
      module,
      typeStream = LineStream(level = 0),
      codeStream = LineStream(level = 0))

    module.defs.foreach { fn => genFunction(ctx, fn) }

    ctx.typeStream.flushTo(out)
    ctx.codeStream.flushTo(out)
  }
}
