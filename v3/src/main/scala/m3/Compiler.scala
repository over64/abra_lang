package m3

import java.io.{File, InputStream, PrintStream}
import java.util.Scanner

import m3.Ast0.Module
import m3._01parse.Resolver
import m3._03codegen.OutConf

import scala.collection.mutable

object Compiler {
  class FileOutProvider(targetDir: String) extends OutConf {
    val files = mutable.ListBuffer[File]()

    override def withStream[T](module: Module, op: PrintStream => T): T = {
      val file = new File(s"$targetDir/${module.pkg}.ll")
      files += file

      val stream = new PrintStream(file)
      val res = op(stream)
      stream.close()

      res
    }
  }

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

  def compile(resolver: String => String,
              targetDir: String,
              runtimeLL: String,
              prelude: Option[String] = None,
              entry: String,
              linkerFlags: String = "",
              mcpu: String = ""): Unit = {

    val m1 = System.currentTimeMillis()

    val root = new m3._01parse.Pass(new Resolver {
      override def resolve(path: String): String = {
        resolver(path)
      }
    }, prelude).pass(entry)

    new m3._02typecheck.Pass().pass(root)

    val outConf = new FileOutProvider(targetDir)
    new m3._03codegen.Pass().pass(root, outConf)

    val objects = outConf.files.map { file =>
      val llSrc = file.getAbsolutePath
      val objDest = llSrc.stripSuffix(".ll") + ".o"

      if (invoke(Seq("sh", "-c", s"opt-9 -O3 -debugify $llSrc | llc-9 -O3 ${if(mcpu != "") s"-mcpu=$mcpu " else "" }-filetype=obj -o $objDest"))._1 != 0)
        throw new RuntimeException("Compilation error: llc")
      objDest
    }

    val m2 = System.currentTimeMillis()
    println(s"__LLVM pass elapsed: ${m2 - m1}ms")

    val binFile = entry.stripPrefix(".").stripSuffix(".eva")
    if (invoke(Seq("sh", "-c",
      s"clang-9 -O3 -g ${objects.mkString(" ")} $runtimeLL $linkerFlags -o $targetDir/$binFile"))._1 != 0)
      throw new RuntimeException("Compilation error: llc")

    val _m3 = System.currentTimeMillis()
    println(s"__Linker pass elapsed: ${_m3 - m2}ms")
  }
}