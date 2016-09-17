package lang_m2

import java.io._
import java.util.Scanner

import grammar2.{M2Lexer, M2Parser}
import org.antlr.v4.runtime.{ANTLRFileStream, CommonTokenStream}

/**
  * Created by over on 14.08.16.
  */
object Compiler {
  case class Config(include: Seq[File] = Seq(new File(".")), file: File = null)

  def compile(include: Seq[File], file: File, isMain: Boolean) = {
    if (!file.exists()) {
      println(s"file with name ${file.getName} does not exists")
      System.exit(1)
    }

    val reader = new ANTLRFileStream(file.getAbsolutePath)
    val lexer = new M2Lexer(reader)
    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)

    val tree = parser.module()
    val visitor = new Visitor(file.getName)
    val ast0 = visitor.visit(tree)
    val typeCheckResult = new TypeChecker().transform(ast0.asInstanceOf[Ast0.Module], visitor.sourceMap)

    //    visitor.sourceMap.nodes.foreach {
    //      case(info, node) => println(s"$info ->\n\t$node")
    //    }

    typeCheckResult match {
      case TypeCheckSuccess(ast1) =>
        val fnameNoExt = file.getName.split("\\.").dropRight(1).mkString(".")

        val llFname = fnameNoExt + ".out.ll"
        val llFile = new File(llFname)
        llFile.createNewFile()
        val llOut = new FileOutputStream(llFile)

        val llMixinFile = new File(fnameNoExt + ".ll")
        if (llMixinFile.exists()) {
          val llMixin = new FileInputStream(llMixinFile).getChannel
          llMixin.transferTo(0, llMixin.size(), llOut.getChannel)
          llMixin.close()
        }

        new IrGen(llOut).gen(ast1)
        llOut.close()

        val llc = Runtime.getRuntime.exec(Array("llc-3.8", llFname))
        llc.waitFor()
        if (llc.waitFor() != 0) {
          println("llc exited with " + llc.exitValue())
          System.exit(1)
        }

        val gcc = Runtime.getRuntime.exec(Array("gcc", fnameNoExt + ".out.s", "-o", fnameNoExt))
        if (gcc.waitFor() != 0) {
          println("gcc exited with " + gcc.exitValue())
          System.exit(1)
        }
      case TypeCheckFail(at, error) =>
        println(s"at ${at.fname}:${at.line}:${at.col} -> \n\t$error")
    }
  }

  def main(args: Array[String]): Unit = {
    val argsParser = new scopt.OptionParser[Config]("kadabra") {
      head("kadabra", "0.0.1")

      opt[Seq[File]]('I', "include dir").valueName("<dir1>, <dir2>...").action {
        case (files, config) => config.copy(include = files)
      }

      arg[File]("<file>").action {
        case (file, config) => config.copy(file = file)
      }.text("file to compile")
    }

    argsParser.parse(args.toSeq, Config()) match {
      case Some(config) =>
        compile(config.include, config.file, isMain = true)
      case None => System.exit(1)
    }
  }
}
