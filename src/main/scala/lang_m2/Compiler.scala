package lang_m2

import java.io._
import java.nio.file.{Path, Paths}
import java.util.Scanner

import scala.collection.JavaConversions._
import grammar2.{M2Lexer, M2Parser}
import org.antlr.v4.runtime.{ANTLRFileStream, CommonTokenStream}
import scala.collection.mutable

/**
  * Created by over on 14.08.16.
  */
object Compiler {
  case class Config(include: Seq[Path] = Seq(), file: Path = null)

  def main(args: Array[String]): Unit = {
    val argsParser = new scopt.OptionParser[Config]("kadabra") {
      head("kadabra", "0.0.1")

      opt[String]('I', "include").valueName("source base dir").action {
        case (files, config) =>
          println("here")
          config.copy(include = files.split(",").map { file =>
            println(s"file - $file")
            Paths.get(file).normalize().toRealPath().toAbsolutePath
          })
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

        println(s"includes = ${config.include}")


        new CompilerKernel().compile(level = 0, config.include, basePackage, config.file, isMain = true)

      case None => System.exit(1)
    }
  }
}
