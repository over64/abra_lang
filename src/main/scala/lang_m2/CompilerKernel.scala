package lang_m2

import java.io.{File, FileInputStream, FileOutputStream}
import java.nio.file.{Files, Path, Paths}

import lang_m2.Ast0._
import grammar2.{M2Lexer, M2Parser}
import org.antlr.v4.runtime.{ANTLRFileStream, CommonTokenStream}
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import Utils._

/**
  * Created by over on 20.09.16.
  */

class CompileEx(val node: ParseNode, val error: CompileError) extends Exception {
  override def getMessage: String = error.toString
}
case class CompileResult(namespace: Namespace, binLocation: Path)

class CompilerKernel {

  def message(level: Int, msg: String) = println(s"${"\t" * level}$msg")

  def compile(level: Int, include: Seq[Path], currentPkg: Seq[String], sourcePath: Path, isMain: Boolean): CompileResult = {
    val time1 = System.currentTimeMillis()

    message(level, s"compile $sourcePath")
    val modName = sourcePath.iterator().toSeq.last.toString.split("\\.")(0)
    val fullModName = (currentPkg :+ modName).mkString("", ".", ".")
    val currentDir = sourcePath.getParent

    val reader = new ANTLRFileStream(sourcePath.toAbsolutePath.toString)
    val lexer = new M2Lexer(reader)
    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)

    val tree = parser.module()
    val visitor = new Visitor(sourcePath.getFileName.toString, fullModName)
    val module = visitor.visit(tree).asInstanceOf[Module]

    val importedModules: Seq[CompileResult] = module.imports.map { _import =>
      val modPath = Paths.get(_import.seq.map(_.value).mkString("/") + ".abra")
      val modPkg = _import.seq.dropRight(1).map(_.value)
      val relativePath = currentDir.resolve(modPath)

      message(level, s"- import ${_import.seq.map(_.value).mkString(".")}")

      // try to make relative import
      if (Files.exists(relativePath)) {
        compile(level + 1, include, currentPkg ++ modPkg, relativePath, isMain = false)
      } else {
        // try to find in include dirs
        val includePaths = include.map { path =>
          (path, path.resolve(modPath))
        }

        val foundModule = includePaths.find {
          case (include, fullModPath) => Files.exists(fullModPath)
        }

        foundModule.map {
          case (include, foundModule) => compile(level + 1, include = Seq(include), currentPkg = modPkg, foundModule, isMain = false)
        }.getOrElse(throw new CompileEx(_import, CE.ImportNotResolved(modPath.toString)))
      }
    }

    val thisNamespace = Namespacer.mkNamespace(module)
    val mixedNamespace = Namespacer.mixNamespaces(thisNamespace, importedModules.map(_.namespace))
    val namespace = new TypeChecker().transform(mixedNamespace, visitor.sourceMap)

    val structs = ListBuffer[Ast1.Struct]()
    val lowFunctions = ListBuffer[Ast1.Fn]()
    val lowHeaders = ListBuffer[Ast1.HeaderFn]()

    namespace.types.foreach {
      case (th, FactorType(name, fields)) =>
        structs += namespace.toLow(ScalarTypeHint(name, th._package)).asInstanceOf[Ast1.Struct]
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
    val lowModule = Ast1.Module(structs, lowFunctions ++ anonFunctions, lowHeaders)

    val fnameNoExt = sourcePath.toAbsolutePath.toString.split("\\.").dropRight(1).mkString(".")
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

    new IrGen(llOut).gen(lowModule)
    llOut.close()

    val llcArgs = Seq("llc-3.8", llFname)
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
      if (isMain) Seq("gcc", "-g", "-lSDL2", "-lGL", fnameNoExt + ".out.s") ++ importedModules.map(_.binLocation.toString) ++ Seq("-o", fnameNoExt)
      else Seq("gcc", "-g", "-c", fnameNoExt + ".out.s", "-o", s"$fnameNoExt.o")
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

    CompileResult(Namespacer.dumpHeader(namespace), Paths.get(if (isMain) fnameNoExt else s"$fnameNoExt.o"))
  }
}
