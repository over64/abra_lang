package integration

import codegen.LowUtil
import grammar.M2Parser
import m3.parse.Ast0.Module
import m3.typecheck.{Namespace, TypeChecker}
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.tree.ParseTree
import parse.ParseUtil

trait IntegrationUtil extends LowUtil {
  val parser = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.module() }
  }
  val testBase = "/tmp/"

  def assertCodeEquals(code: String, exit: Option[Int] = None, stdout: Option[String] = None, stderr: Option[String] = None) = {
    val (ast, _) = parser.parse[Module](new ANTLRInputStream(code))
    val namespace = new Namespace("test", ast.lowCode, ast.defs, ast.types, Map.empty)
    TypeChecker.infer(namespace)
    namespace.lowMod.assertRunEquals(exit, stdout, stderr)
  }
}
