import java.io.{File, PrintWriter}
import java.lang.ProcessBuilder.Redirect

/**
  * Created by over on 20.11.15.
  */
class Pipeline(sourceFileName: String) {

  val fnameNoExt = sourceFileName.split("\\.")(0)
  val llvmOut = new PrintWriter(new File(s"$fnameNoExt.ll"))
  val asmOut = new File(s"$fnameNoExt.s")

  val llc = new ProcessBuilder("llc")
  val gcc = new ProcessBuilder("gcc", "-x", "assembler", "-o", s"$fnameNoExt.elf", "-")

  llc.redirectOutput(asmOut)
  gcc.redirectInput(asmOut)

  llc.redirectError(Redirect.INHERIT)
  gcc.redirectError(Redirect.INHERIT)

  val pllc = llc.start()

  val llcIn = new PrintWriter(pllc.getOutputStream)


  def println(bitcode: String): Unit = {
    llvmOut.println(bitcode)
    llcIn.println(bitcode)
  }

  def finish() = {
    llvmOut.close()
    llcIn.close()
    pllc.waitFor()

    val pgcc = gcc.start()
    pgcc.waitFor()
  }
}
