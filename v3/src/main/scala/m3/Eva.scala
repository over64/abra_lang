package m3

import java.nio.file.{Files, Path, Paths}

import m3._01parse.FsResolver
import scopt.OParser

object Eva {
  case class Config(libDir: String = "/home/devteam/build/eva_lang/v3/eva/lib/",
                    linkerFlags: String = "",
                    mcpu: String ="",
                    mainFile: Path = null)

  def main(args: Array[String]): Unit = {

    val options = {
      val builder = OParser.builder[Config]

      import builder._
      OParser.sequence(
        head("eva lang compiler", "v0.3.0"),
        programName("eva"),
        opt[String]("lib-dir")
          .action((x, c) => c.copy(libDir = x))
          .text("path to eva libraries directory"),
        opt[String]("linker-flags")
          .action((flags, c) => c.copy(linkerFlags = flags))
          .text("linker flags"),
        opt[String]("mcpu")
          .action((march, c) => c.copy(mcpu = march))
          .text("target cpu micro architecture"),
        arg[String]("file")
          .required()
          .action { (f, c) =>
            val path = Paths.get(f)
            if (!Files.isRegularFile(path))
              throw new RuntimeException("Expected path to file")
            c.copy(mainFile = path)
          },
      )
    }

    val conf = OParser.parse(options, args, Config()) match {
      case Some(config) => config
      case _ => OParser.usage(options); return
    }

    val projDir = conf.mainFile.toAbsolutePath.getParent.toString + "/"
    val targetDir = projDir + "/target"
    Files.createDirectories(Paths.get(targetDir))
    val entryModule = "." + conf.mainFile.getFileName.toString.stripSuffix(".eva");

    val resolver = new FsResolver(conf.libDir, projDir)

    Compiler.compile(
      resolver.resolve,
      targetDir,
      conf.libDir + "/runtime2.ll",
      Some("prelude"),
      entryModule,
      conf.linkerFlags,
      conf.mcpu)
  }
}
