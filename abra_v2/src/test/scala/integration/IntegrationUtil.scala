package integration

import java.nio.file.{Files, Paths}

import codegen.LowUtil
import grammar.M2Parser
import m3.codegen.IrUtil.Mod
import m3.parse.Ast0.Module
import m3.typecheck.Util._
import m3.typecheck._
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.tree.ParseTree
import parse.ParseUtil

import scala.collection.mutable

trait IntegrationUtil extends LowUtil {
  val parser = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.module() }
  }
  val testBase = "/tmp/"

  def compile(cache: mutable.HashMap[String, (ModHeader, String, Seq[String])],
              level: Int, projDir: String, pkg: String, code: String, isRelease: Boolean): (ModHeader, String, Seq[String]) = {
    println("\t" * level + s"parse $pkg")
    val ast = parser.parse[Module](new ANTLRInputStream(code))
    val imports =
      ast.imports.seq.map { ie =>
        cache.get(ie.path) match {
          case None =>
            val code = new String(Files.readAllBytes(Paths.get(
              if (ie.path.startsWith("/"))
                System.getProperty("user.dir") + "/abra_v2/abra/lib" + ie.path + ".abra"
              else
                projDir + ie.path + ".abra"
            )))

            (ie.modName, compile(cache, level + 1, projDir, ie.path, code, isRelease))
          case Some(compiled) =>
            println("\t" * (level + 1) + s"import cached ${ie.path}")
            (ie.modName, compiled)
        }
      }

    val typeImports =
      ast.imports.seq.flatMap { ie =>
        ie.withTypes.map(tname => (tname, ie.modName))
      }.toMap

    val types = ast.types.map { t => (t.name, t) }.toMap

    val defs = ast.defs.filter { fn => !fn.isSelf }
      .groupBy(fn => fn.name)
      .map { case (name, fnList) =>
        if (fnList.length == 1) (name, fnList.head)
        else throw new RuntimeException(s"double definition for function $name")
      }

    val selfDefs = ast.defs.filter { fn => fn.isSelf }
      .groupBy(fn => fn.name)

    val nsImports = imports.map { case (k, v) => (k, v._1) }
    val ctx = TContext(0, mutable.Stack[String](), Mod(), level, pkg, nsImports, typeImports,
      ast.lowCode, types, defs, selfDefs, mutable.HashMap())

    println("\t" * level + s"compile $pkg")
    TypeChecker.infer(ctx)

    val obj = ctx.lowMod.makeObj(pkg, isRelease)
    val objImports = imports.flatMap { case (k, v) => v._2 +: v._3 }.toSeq.distinct
    val res = (ctx.toHeader, obj, objImports)
    cache.put(pkg, res)
    res
  }

  def assertCodeEquals(code: String, exit: Option[Int] = None, stdout: Option[String] = None, stderr: Option[String] = None,
                       isRelease: Boolean = false,
                       projDir: String = System.getProperty("user.dir")) = {
    val (_, obj, deps) = compile(new mutable.HashMap(), 0, projDir, "test", code, isRelease)
    runProgram(obj, deps, exit, stdout, stderr, isRelease)
  }
}
