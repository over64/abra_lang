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

import scala.collection.mutable.ListBuffer

trait IntegrationUtil extends LowUtil {
  val parser = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.module() }
  }
  val testBase = "/tmp/"

  def compile(level: Int, ctx: TContext, pkg: String, code: String): Namespace = {
    println("\t" * level + s"parse $pkg")
    val (ast, _) = parser.parse[Module](new ANTLRInputStream(code))
    val imports =
      ast.imports.seq.map { ie =>
        (ie.modName, compile(level + 1, new TContext(), ie.path, new String(
          Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/abra_v2/abra/lib" + ie.path + ".abra")))))
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

    val namespace = new Namespace(pkg, imports = imports, typeImports = typeImports,
      lowCode = ast.lowCode, selfDefs = selfDefs, defs = defs, types = ast.types)

    println("\t" * level + s"compile $pkg")
    TypeChecker.infer(ctx, namespace)
    namespace
  }

  def assertCodeEquals(code: String, exit: Option[Int] = None, stdout: Option[String] = None, stderr: Option[String] = None) = {
    val ctx = new TContext()
    val namespace = compile(0, ctx, "test", code)
    ctx.lowMod.assertRunEquals(exit, stdout, stderr)
  }
}
