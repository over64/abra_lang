package lang_m2

import java.io.{File, FileInputStream, FileOutputStream}
import java.nio.file.{Files, Path, Paths}

import lang_m2.Ast0._
import grammar2.{M2Lexer, M2Parser}
import org.antlr.v4.runtime.{ANTLRFileStream, CommonTokenStream}

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import Utils._
import lang_m2.Compiler.Config
import lang_m2.TypeCheckerUtil.{FactorType, UnionType}

/**
  * Created by over on 20.09.16.
  */

class CompileEx(val node: ParseNode, val error: CompileError) extends Exception {
  override def getMessage: String = error.toString
}
case class CompileResult(namespace: Namespace, binLocation: Path)

class CompilerKernel {

  def message(level: Int, msg: String) = println(s"${"  " * level}$msg")

  def compile(level: Int, config: Config, currentPkg: Seq[String], sourcePath: Path, isMain: Boolean): CompileResult = {
    val time1 = System.currentTimeMillis()

    message(level, s"- compile $sourcePath")

    val targetPath = config.targetDir.resolve(Paths.get(currentPkg.mkString("/")))
    Files.createDirectories(targetPath)

    val modName = sourcePath.iterator().toSeq.last.toString.split("\\.")(0)
    val fullModName = (currentPkg :+ modName).mkString("", ".", ".")

    val reader = new ANTLRFileStream(sourcePath.toAbsolutePath.toString)
    val lexer = new M2Lexer(reader)
    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)

    val tree = parser.module()
    val visitor = new Visitor(sourcePath.getFileName.toString, fullModName)
    val module = visitor.visit(tree).asInstanceOf[Module]

    val importedModules: Seq[CompileResult] = module.imports.map { _import =>
      val modPkg = _import.seq.dropRight(1).map(_.value)
      val modPath = Paths.get(_import.seq.map(_.value).mkString("/") + ".abra")

      message(level, s"- import ${_import.seq.map(_.value).mkString(".")}")

      // try to find in include dirs
      val includePaths = config.include.map { path =>
        (path, path.resolve(modPath))
      }

      val foundModule = includePaths.find {
        case (include, fullModPath) => Files.exists(fullModPath)
      }

      foundModule.map {
        case (_, foundModule) =>
          message(level + 1, s"- target path = $targetPath")

          compile(level + 1, config, currentPkg = modPkg, foundModule, isMain = false)
      }.getOrElse(throw new CompileEx(_import, CE.ImportNotResolved(modPath.toString, config.include.map(_.toString))))

    }

    val thisNamespace = Namespacer.mixNamespaces(module, importedModules.map(_.namespace))
    val namespace = new TypeChecker().transform(thisNamespace, visitor.sourceMap)

    val structs = ListBuffer[Ast1.Struct]()
    val unions = ListBuffer[Ast1.Union]()
    val lowFunctions = ListBuffer[Ast1.Fn]()
    val lowHeaders = ListBuffer[Ast1.HeaderFn]()

    namespace.types.values.foreach {
      case ft: FactorType =>
        structs += namespace.toLow(ft).asInstanceOf[Ast1.Struct]
      case u: UnionType => unions += namespace.toLow(u).asInstanceOf[Ast1.Union]
      case _ =>
    }

    namespace.extensions.values.foreach { fnMap =>
      fnMap.values.foreach { fnCont =>
        fnCont.fnInfo match {
          case HeaderFn(_, _, lowFn) => lowHeaders += lowFn
          case InferedFn(_, _, lowFn) => lowFunctions += lowFn
        }
      }
    }

    namespace.functions.values.flatMap { fnMap =>
      fnMap.values.map { fnCont =>
        fnCont.fnInfo match {
          case HeaderFn(_, _, lowFn) => lowHeaders += lowFn
          case InferedFn(_, th, lowFn) => lowFunctions += lowFn
        }
      }
    }

    val anonFunctions = namespace.anonFunctions.values.map(_.lowFn)
    val lowModule = Ast1.Module(structs, unions, anonFunctions.toSeq ++ lowFunctions, lowHeaders)

    val fnameNoExt = sourcePath.toAbsolutePath.toString.split("\\.").dropRight(1).mkString(".")
    val llFilePath = targetPath.resolve(modName + ".out.ll")
    println(s"llFilePath = $llFilePath")
    val llFile = llFilePath.toFile
    llFile.createNewFile()
    val llOut = new FileOutputStream(llFile)

    val llMixinFile = new File(fnameNoExt + ".ll")
    if (llMixinFile.exists()) {
      val llMixin = new FileInputStream(llMixinFile).getChannel
      llMixin.transferTo(0, llMixin.size(), llOut.getChannel)
      llMixin.close()
    }

    new IrGen(llOut).gen(lowModule)
    llOut.close()

    val llcArgs = Seq("llc-3.8", llFilePath.toString)
    message(level, llcArgs.mkString("- ", " ", ""))

    run(llcArgs: _*) { (realExit, realStdout, realStderr) =>
      if (realExit != 0) {
        realStderr.split("\n").foreach { line =>
          message(level, line)
        }
        throw new CompileEx(null, CE.LinkError("llc exited with non zero"))
      }
    }

    val gccArgs =
      if (isMain)
        Seq("gcc", "-g", targetPath.resolve(modName).toString + ".out.s") ++
          config.libs ++ importedModules.map(_.binLocation.toString) ++ Seq("-o", targetPath.resolve(modName).toString)
      else Seq("gcc", "-g", "-c", targetPath.resolve(modName).toString + ".out.s", "-o", s"${targetPath.resolve(modName).toString}.o")

    message(level, gccArgs.mkString("- ", " ", ""))

    run(gccArgs: _*) { (realExit, realStdout, realStderr) =>
      if (realExit != 0) {
        realStderr.split("\n").foreach { line =>
          message(level, line)
        }
        throw new CompileEx(null, CE.LinkError("gcc exited with non zero"))
      }
    }

    val time2 = System.currentTimeMillis()
    message(level, s"- done with ${(time2 - time1).toDouble / 1000}s")

    CompileResult(Namespacer.dumpHeader(namespace), Paths.get(
      if (isMain) targetPath.resolve(modName).toString
      else s"${targetPath.resolve(modName).toString}.o")
    )
  }
}
