package codegen2

import java.io._
import java.util.Scanner

import m3.codegen.{IrGenPass, OutConf}
import m3.parse.Ast0.Module
import m3.parse.{Level, ParsePass, Resolver}
import m3.typecheck.TypeCheckPass

import scala.collection.mutable

object CodeGenUtil {

  def astForCode(code: String): (Level, Module) =
    astForModules({
      case "main" => code
    })

  def astForModules(resolver: String => String): (Level, Module) = {
    val root = new ParsePass(new Resolver {
      override def resolve(path: String): String = {
        val code = resolver(path)
        val fw = new FileWriter(new File("/tmp/" + path + ".abra"))
        fw.write(code)
        fw.close()
        code
      }
    }).pass("main")

    new TypeCheckPass().pass(root)

    (root, root.findMod("main").get)
  }

  class FileOutProvider extends OutConf {
    val files = mutable.ListBuffer[File]()

    override def withStream[T](module: Module, op: PrintStream => T): T = {
      val file = new File(s"/tmp/${module.pkg}.ll")
      files += file

      val stream = new PrintStream(file)
      val res = op(stream)
      stream.close()

      res
    }
  }

  def compile(code: String): Unit =
    compileModules({
      case "main" => code
    })

  def compileModules(resolver: String => String): Unit = {
    val (root, _) = astForModules(resolver)
    val outConf = new FileOutProvider
    new IrGenPass().pass(root, outConf)

    outConf.files.foreach { file =>
      val src = file.getAbsolutePath
      val dest = src.stripSuffix(".ll") + ".o"
      val args = Seq("sh", "-c", s"llc-7 -filetype=obj $src -o $dest")

      println(s"invoke: ${args.mkString(" ")}")

      val proc = Runtime.getRuntime.exec(args.toArray)
      proc.waitFor()

      def printStream(stream: InputStream) = {
        val sc = new Scanner(stream)
        while (sc.hasNextLine)
          println(sc.nextLine())
      }

      printStream(proc.getErrorStream)
      printStream(proc.getInputStream)

      if (proc.exitValue() != 0)
        throw new RuntimeException("Compilation error: llc")
    }
  }
}
