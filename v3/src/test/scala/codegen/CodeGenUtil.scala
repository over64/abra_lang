package codegen

import java.io._
import java.util.Scanner

import m3.Ast0.Module
import m3.Level
import m3._03codegen.{OutConf, Pass}
import m3._01parse.{Pass, Resolver}
import m3._02typecheck.Pass

import scala.collection.mutable

object CodeGenUtil {

  def astForCode(code: String): (Level, Module) =
    astForModules({
      case "main" => code
    })

  def astForModules(resolver: String => String, entry: String = "main", prelude: Option[String]  = None): (Level, Module) = {
    val root = new m3._01parse.Pass(new Resolver {
      override def resolve(path: String): String = {
        val code = resolver(path)
        val fw = new FileWriter(new File("/tmp/" + path + ".eva"))
        fw.write(code)
        fw.close()
        code
      }
    }, prelude).pass(entry)

    new m3._02typecheck.Pass().pass(root)

    (root, root.findMod(entry).get)
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
    }, exitCode, stdout = stdout)

  def runModules(resolver: String => String, exitCode: Int,
                 entry: String = "main", prelude: Option[String] = None,
                 stdout: Option[String] = None,
                 linkerFlags: Seq[String] = Seq.empty): Unit = {
    val m1 = System.currentTimeMillis()

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

    val (root, _) = astForModules(resolver, entry, prelude)
    val outConf = new FileOutProvider
    new m3._03codegen.Pass().pass(root, outConf)

    val objects = outConf.files.map { file =>
      val src = file.getAbsolutePath
      val dest = src.stripSuffix(".ll") + ".o"

      if (invoke(Seq("sh", "-c", s"opt-9 -O3 -debugify $src | llc-9 -O3 -filetype=obj -o $dest"))._1 != 0)
        throw new RuntimeException("Compilation error: llc")

      dest
    }

    val m2 = System.currentTimeMillis()
    println(s"__LLVM pass elapsed: ${m2 - m1}ms")

    if (invoke(Seq("sh", "-c",
      s"clang-9 -O3 -g ${objects.mkString(" ")} ${System.getProperty("user.dir")}/v3/eva/lib/runtime2.ll -o /tmp/main  ${linkerFlags.mkString(" ")}"))._1 != 0)
      throw new RuntimeException("Compilation error: llc")

    val _m3 = System.currentTimeMillis()
    println(s"__Linker pass elapsed: ${_m3 - m2}ms")


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
