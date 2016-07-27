import grammar._
import lang_new._
import org.antlr.v4.runtime.{ANTLRFileStream, CommonTokenStream}
import org.scalatest.FunSuite

class M1 extends FunSuite {


  test("antlr gramma test") {
    val reader = new ANTLRFileStream("/tmp/abra/test8.abra")
    val lexer = new HelloLexer(reader)

    val tokens = new CommonTokenStream(lexer)
    val parser = new HelloParser(tokens)

    val tree = parser.lang()
    val ast = new LangVisitor().visit(tree)
    println(ast)

    val ast1 = new TypeChecker(ast.asInstanceOf[Module]).checkAndTransform()
    print(ast1)
  }
}
