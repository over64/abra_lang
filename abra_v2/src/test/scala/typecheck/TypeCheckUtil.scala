package typecheck

import java.io.{File, FileWriter}

import grammar.{M2LexerForIDE, M2Parser}
import org.scalatest.FunSuite
import m3.parse.Ast0._
import m3.parse.{ParsePass, Resolver, Visitor}
import m3.typecheck.TypeCheckPass
import org.antlr.v4.runtime.{ANTLRInputStream, BailErrorStrategy, CommonTokenStream}

object TypeCheckUtil extends FunSuite {
  def assertTh(expected: String, has: TypeHint): Unit = {
    assert(has === expected.toTh)
  }

  def assertTh(expected: String, has: ParseNode): Unit = {
    val th: TypeHint = has.getTypeHint
    assert(th === expected.toTh)
  }

  def astForCode(code: String): Module = {
    val fw = new FileWriter(new File("/tmp/test.abra"))
    fw.write(code)
    fw.close()

    val root = new ParsePass(new Resolver {
      override def resolve(path: String): String = path match {
        case "main" => code
      }
    }).pass("main")

    new TypeCheckPass().pass(root)
    root.findMod("main").get
  }

  implicit class RichModule(self: Module) {
    def dataDecl(name: String) = self.types(name)

    def selfFunction(name: String, th: TypeHint) =
      self.selfDefs(name).find(d => d.lambda.args(0).typeHint == th).get

    def function(name: String) = self.defs(name)
  }

  implicit class RichDef(self: Def) {
    def varDecl(varName: String): TypeHint = {
      self.lambda.body.asInstanceOf[AbraCode].seq
        .filter(expr => expr.isInstanceOf[Store])
        .map(expr => expr.asInstanceOf[Store])
        .filter(store => store.to == Seq(lId(varName)))
        .head.getDeclTh
    }
  }

  implicit class RichString(self: String) {
    def toTh = {
      val lexer = new M2LexerForIDE(new ANTLRInputStream(self))
      val tokens = new CommonTokenStream(lexer)

      val parser = new M2Parser(tokens)
      parser.setErrorHandler(new BailErrorStrategy)
      val visitor = new Visitor("test.abra", "test")

      visitor.visit(parser.typeHint()).asInstanceOf[TypeHint]
    }
  }

  def astPrint(any: Any): Unit = {
    var showLine = true
    var tabSize = 4
    var level = 0
    var inGeneric = false
    any.toString.foreach {
      case '[' => inGeneric = true; print('[')
      case ']' => inGeneric = false; print(']')
      case '(' =>
        level += 1
        println()
        if (showLine) {
          print(("|" + " " * (tabSize - 1)) * (level - 1))
          print("|" + "-" * (tabSize - 1))
        } else {
          print(" " * tabSize * level)
        }
      case ')' =>
        level -= 1
      case ',' =>
        if (!inGeneric) {
          println()
          if (showLine) {
            print(("|" + " " * (tabSize - 1)) * (level - 1))
            print("|" + "-" * (tabSize - 1))
          } else {
            print(" " * tabSize * level)
          }
        } else print(',')
      case ' ' =>
      case f => print(f)
    }
    println()
  }
}
