package m3.codegen

import m3.codegen.CodeGen.LineStream

/**
  * Created by over on 27.09.17.
  */
object CodeWriter {
  def writeFunction(fnStream: LineStream, varStream: LineStream, irStream: LineStream, fnName: String): Unit = {
    fnStream.line(s"define @${"\"" + fnName + "\""} {")
    varStream.flushTo(fnStream)
    irStream.flushTo(fnStream)
    fnStream.line("}")
  }
}
