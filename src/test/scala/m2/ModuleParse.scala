package m2

import java.io.File

import grammar2.M2Parser
import lang_m2.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

class ModuleParse extends FunSuite {
  val moduleParser = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.module() }
  }

  test("a module parse test") {
    import moduleParser._
    withInput(new File("/tmp/abra/m2.abra"), Module(AstInfo(0, 0), Seq()))
  }
}
