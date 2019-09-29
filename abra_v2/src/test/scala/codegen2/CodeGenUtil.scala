package codegen2

import java.io._
import java.util.Scanner

import m3.codegen.{IrGenPass, OutConf}
import m3.parse.Ast0.Module
import m3.parse.{Level, ParsePass, Resolver}
import m3.typecheck.{TypeHintPass, TypeCheckPass}

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

    TypeHintPass.pass(root)
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

  def run(code: String, exitCode: Int, stdout: Option[String] = None): Unit =
    runModules({
      case "main" => code
    }, exitCode, stdout)

  def runModules(resolver: String => String, exitCode: Int, stdout: Option[String] = None): Unit = {
    def invoke(args: Seq[String]): (Int, String, String) = {
      def streamToString(stream: InputStream) = {
        val buff = new StringBuffer()
        val sc = new Scanner(stream)
        while (sc.hasNextLine) {
          buff.append(sc.nextLine())
          buff.append('\n')
        }
        buff.toString
      }

      println(s"invoke: ${args.mkString(" ")}")

      val proc = Runtime.getRuntime.exec(args.toArray)
      proc.waitFor()
      val (out, err) = (streamToString(proc.getInputStream), streamToString(proc.getErrorStream))

      System.out.println(out)
      System.out.println(err)

      (proc.exitValue(), out, err)
    }

    val (root, _) = astForModules(resolver)
    val outConf = new FileOutProvider
    new IrGenPass().pass(root, outConf)

    val objects = outConf.files.map { file =>
      val src = file.getAbsolutePath
      val dest = src.stripSuffix(".ll") + ".o"

      if (invoke(Seq("sh", "-c", s"llc-7 -filetype=obj $src -o $dest"))._1 != 0)
        throw new RuntimeException("Compilation error: llc")

      dest
    }

    if (invoke(Seq("sh", "-c",
      s"clang-7 ${objects.mkString(" ")} ${System.getProperty("user.dir")}/abra_v2/abra/lib/runtime2.ll -o /tmp/main"))._1 != 0)
      throw new RuntimeException("Compilation error: llc")

    val (code, out, err) = invoke(Seq("sh", "-c", "valgrind -q --leak-check=full /tmp/main"))

    if (err != "")
      throw new RuntimeException("valgrind memcheck error")

    stdout.foreach { expectedOut =>
      if (expectedOut != out)
        throw new RuntimeException("run error: stdout mismatch")
    }

    if (code != exitCode)
      throw new RuntimeException(s"run error: expected exit($exitCode) has exit($code)")
  }
}
