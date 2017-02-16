package lang_m2

import java.nio.file.{Files, Path, Paths}

import scala.collection.JavaConversions._

/**
  * Created by over on 14.08.16.
  */
object Compiler {
  val defaultInclude = Seq(".")

  def absolutePath(paths: Seq[String]): Seq[Path] =
    paths.map { file => Paths.get(file).normalize().toRealPath().toAbsolutePath }

  case class Config(targetDir: Path = Paths.get("").toAbsolutePath.resolve("target"),
                    include: Seq[Path] = absolutePath(defaultInclude),
                    libs: Seq[String] = Seq(),
                    file: Path = null)

  def main(args: Array[String]): Unit = {
    val argsParser = new scopt.OptionParser[Config]("kadabra") {
      head("kadabra", "0.0.1")

      opt[Seq[String]]('I', "include").valueName("<dir1>,<dir2>...").action {
        case (files, config) =>
          config.copy(include = absolutePath(files ++ defaultInclude))
      }.text("include directories")

      opt[Seq[String]]('l', "libs").valueName("<lib1>,<lib2>...").action {
        case (libs, config) => config.copy(libs = libs.map(lib => "-l" + lib))
      }.text("link with libs")

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
        println(s"libs = ${config.libs}")

        Files.createDirectories(config.targetDir)

        new CompilerKernel().compile(level = 0, config, basePackage, config.file, isMain = true)

      case None => System.exit(1)
    }
  }
}
