package integration

import codegen.LowUtil
import grammar.M2Parser
import m3.parse.Ast0.Module
import m3.typecheck.{DefCont, Namespace, TypeChecker}
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

  def assertCodeEquals(code: String, exit: Option[Int] = None, stdout: Option[String] = None, stderr: Option[String] = None) = {
    val (ast, _) = parser.parse[Module](new ANTLRInputStream(code))
    val selfDefs = ast.defs.filter { fn => fn.isSelf }
      .map(fn => DefCont(fn, ListBuffer.empty))
      .groupBy(cont => cont.fn.name)
    val defs = ast.defs.filter { fn => !fn.isSelf }
      .map(fn => DefCont(fn, ListBuffer.empty))
      .groupBy(cont => cont.fn.name)
      .map {
        case (name, conts) =>
          if(conts.length == 1) (name, conts(0))
          else throw new RuntimeException(s"double definition for function $name")
      }
    val namespace = new Namespace("test", ast.lowCode, selfDefs, defs, ast.types, Map.empty)
    TypeChecker.infer(namespace)
    namespace.lowMod.assertRunEquals(exit, stdout, stderr)
  }
}
