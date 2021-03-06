package typecheck

import java.io.{File, FileWriter}

import grammar.{M2LexerForIDE, M2Parser}
import m3.Ast0._
import m3._01parse.{Pass, Resolver, Visitor}
import m3._02typecheck.TCMeta._
import m3._02typecheck.Pass
import org.antlr.v4.runtime.{ANTLRInputStream, BailErrorStrategy, CommonTokenStream}
import org.scalatest.FunSuite

object TypeCheckUtil extends FunSuite {
  def assertTh(expected: String, has: TypeHint): Unit = {
    assert(has === expected.toTh)
  }

  def assertTh(expected: String, has: ParseNode): Unit = {
    val th: TypeHint = has.getTypeHint
    assert(th === expected.toTh)
  }

  def assertThRaw(expected: TypeHint, has: TypeHint): Unit = {
    assert(has === expected)
  }

  def assertThRaw(expected: TypeHint, has: ParseNode): Unit = {
    val th: TypeHint = has.getTypeHint
    assert(th === expected)
  }


  def astForCode(code: String): Module =
    astForModules({
      case "main" => code
    })

  def astForModules(resolver: String => String): Module = {
    val root = new m3._01parse.Pass(new Resolver {
      override def resolve(path: String): String = {
        val code = resolver(path)
        val fw = new FileWriter(new File("/tmp/" + path + ".eva"))
        fw.write(code)
        fw.close()
        code
      }
    }, None).pass("main")

    new m3._02typecheck.Pass().pass(root)
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
        .head.getDeclTh.get
    }
  }

  implicit class RichString(self: String) {
    def toTh = {
      val lexer = new M2LexerForIDE(new ANTLRInputStream(self))
      val tokens = new CommonTokenStream(lexer)

      val parser = new M2Parser(tokens)
      parser.setErrorHandler(new BailErrorStrategy)
      val visitor = new Visitor(self, "main.eva", "main", None)

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
