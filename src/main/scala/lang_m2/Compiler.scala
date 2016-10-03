package lang_m2

import java.io._
import java.nio.file.{Path, Paths}
import java.util.Scanner

import scala.collection.JavaConversions._
import grammar2.{M2Lexer, M2Parser}
import org.antlr.v4.runtime.{ANTLRFileStream, CommonTokenStream}

/**
  * Created by over on 14.08.16.
  */
object Compiler {
  case class Config(include: Seq[Path] = Seq(), file: Path = null)

  def main(args: Array[String]): Unit = {
    val argsParser = new scopt.OptionParser[Config]("kadabra") {
      head("kadabra", "0.0.1")

      opt[Seq[String]]('I', "include dir").valueName("<dir1>, <dir2>...").action {
        case (files, config) => config.copy(include = files.map { file => Paths.get(file) })
      }

      arg[String]("<file>").action {
        case (file, config) =>
          config.copy(file = Paths.get(file).normalize().toRealPath().toAbsolutePath)
      }.text("file to compile")
    }

    val currentDir = Paths.get("").toAbsolutePath

    argsParser.parse(args.toSeq, Config()) match {
      case Some(config) =>
        //        currentDir.iterator().foreach { dir =>
        //          println(dir)
        //        }
        //        println("->")
        //        config.file.iterator().foreach { dir =>
        //          println(dir)
        //        }
        val basePackage =
          currentDir.iterator().map(_.toString).zipAll(config.file.iterator().map(_.toString), "", "").dropWhile {
            case (p1, p2) => p1 == p2
          }.map { case (p1, p2) => p2 }.toSeq.dropRight(1)

        //println(s"base package = $basePackage")

        new CompilerKernel().compile(level = 0, config.include, basePackage, config.file, isMain = true)

      case None => System.exit(1)
    }
  }
}
