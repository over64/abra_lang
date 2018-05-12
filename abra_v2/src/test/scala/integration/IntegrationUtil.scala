package integration

import java.nio.file.{Files, Paths}

import codegen.LowUtil
import grammar.M2Parser
import m3.parse.Ast0.Module
import m3.typecheck.{DefCont, Namespace, TContext, TypeChecker}
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.tree.ParseTree
import parse.ParseUtil
import m3.typecheck.Util._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait IntegrationUtil extends LowUtil {
  val parser = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.module() }
  }
  val testBase = "/tmp/"

  def compile(cache: mutable.HashMap[String, (Namespace, String, Seq[String])],
              level: Int, ctx: TContext, pkg: String, code: String, isRelease: Boolean): (Namespace, String, Seq[String]) = {
    println("\t" * level + s"parse $pkg")
    val (ast, _) = parser.parse[Module](new ANTLRInputStream(code))
    val imports =
      ast.imports.seq.map { ie =>
        cache.get(ie.path) match {
          case None => (ie.modName, compile(
            cache, level + 1, new TContext(level + 1), ie.path, new String(
              Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/abra_v2/abra/lib" + ie.path + ".abra"))), isRelease))
          case Some(compiled) =>
            println("\t" * (level + 1) + s"import cached ${ie.path}")
            (ie.modName, compiled)
        }
      }.toMap

    val typeImports =
      ast.imports.seq.flatMap { ie =>
        ie.withTypes.map(tname => (tname, ie.modName))
      }.toMap

    val selfDefs = ast.defs.filter { fn => fn.isSelf }
      .map(fn => DefCont(fn, ListBuffer.empty))
      .groupBy(cont => cont.fn.name)
    val defs = ast.defs.filter { fn => !fn.isSelf }
      .map(fn => DefCont(fn, ListBuffer.empty))
      .groupBy(cont => cont.fn.name)
      .map {
        case (name, conts) =>
          if (conts.length == 1) (name, conts(0))
          else throw new RuntimeException(s"double definition for function $name")
      }

    val nsImports = imports.map { case (k, v) => (k, v._1) }
    val objImports = imports.flatMap { case (k, v) => v._2 +: v._3 }.toSeq.distinct

    val namespace = new Namespace(pkg, imports = nsImports, typeImports = typeImports,
      lowCode = ast.lowCode, selfDefs = selfDefs, defs = defs, types = ast.types)

    println("\t" * level + s"compile $pkg")
    TypeChecker.infer(ctx, namespace)
    val obj = ctx.lowMod.makeObj(pkg, isRelease)
    val res = (namespace, obj, objImports)
    cache.put(pkg, res)
    res
  }

  def assertCodeEquals(code: String, exit: Option[Int] = None, stdout: Option[String] = None, stderr: Option[String] = None, isRelease: Boolean = false) = {
    val ctx = new TContext()
    val (ns, obj, deps) = compile(new mutable.HashMap(), 0, ctx, "test", code, isRelease)
    runProgram(obj, deps, exit, stdout, stderr, isRelease)
  }
}
