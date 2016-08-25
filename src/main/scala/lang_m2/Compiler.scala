package lang_m2

import java.io.{File, FileOutputStream, PrintStream}

import grammar2.{M2Lexer, M2Parser}
import org.antlr.v4.runtime.{ANTLRFileStream, CommonTokenStream}

/**
  * Created by over on 14.08.16.
  */
object Compiler {
  def printUsage() = println(
    """
      |Usage:
      |java -jar kadabra.jar fname
    """.stripMargin)

  def main(args: Array[String]): Unit = {
    val fname =
      if (args.length > 0) args(0)
      else {
        printUsage();
        return
      }

    val file = new File(fname)
    if (!file.exists()) {
      println(s"file with name $fname does not exists")
      return
    }

    val reader = new ANTLRFileStream(file.getAbsolutePath)
    val lexer = new M2Lexer(reader)
    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)

    val tree = parser.module()
    val visitor = new Visitor(fname)
    val ast0 = visitor.visit(tree)
    val typeCheckResult = new TypeChecker().transform(ast0.asInstanceOf[Ast0.Module], visitor.sourceMap)

//    visitor.sourceMap.nodes.foreach {
//      case(info, node) => println(s"$info ->\n\t$node")
//    }

    typeCheckResult match {
      case TypeCheckSuccess(ast1) =>
        val fnameNoExt = fname.split("\\.").dropRight(1).mkString(".")

        val llFname = fnameNoExt + ".ll"
        val llFile = new File(llFname)
        llFile.createNewFile()
        val llOut = new FileOutputStream(llFile)

        new IrGen(new PrintStream(llOut)).gen(ast1)

        llOut.close()

        val llc = Runtime.getRuntime.exec(Array("llc-3.8", llFname))
        llc.waitFor()
        if (llc.exitValue() != 0) {
          println("llc exited with " + llc.exitValue())
          return
        }

        Runtime.getRuntime.exec(Array("gcc", fnameNoExt + ".s", "-o", fnameNoExt))
      case TypeCheckFail(at, error) =>
        println(s"at ${at.fname}:${at.line}:${at.col} -> \n\t$error")
    }
  }
}
