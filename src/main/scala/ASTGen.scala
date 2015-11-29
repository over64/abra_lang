import java.io.Reader

import scala.util.parsing.combinator.{RegexParsers, JavaTokenParsers}

/**
  * Created by over on 18.11.15.
  */
object AST {

  sealed trait Lang

  trait Assignable extends Lang

  case class Ident(name: String) extends Assignable

  sealed trait Const extends Assignable {
    val name: String
  }

  case class IConst(name: String, value: Int) extends Const

  case class Val(name: String, init: Assignable) extends Assignable

  case class Var(name: String, init: Assignable) extends Assignable

  case class InfixCall(fn: String, x: Assignable, y: Assignable) extends Assignable

  case class Assignment(i: Ident, assignment: Assignable) extends Lang

}

object ASTGen extends RegexParsers {

  import AST._

  var nConsts = 0

  def ident: Parser[Ident] = "(?!val)(?!var)[A-Za-z_]\\w*".r ^^ {
    case str => Ident(str)
  }

  def intNumber: Parser[Const] =
    """\d+""".r ^^ {
      case str => nConsts += 1; IConst(s"const$nConsts", str.toInt)
    }

  def factor: Parser[Assignable] = intNumber | ident | "(" ~> expr <~ ")"

  def term: Parser[Assignable] = factor ~ rep("*" ~ factor | "/" ~ factor) ^^ {
    case number ~ list => (number /: list) {
      case (x, "*" ~ y) => InfixCall("*", x, y)
      case (x, "/" ~ y) => InfixCall("/", x, y)
    }
  }

  def expr: Parser[Assignable] = term ~ rep("+" ~ log(term)("Plus term") | "-" ~ log(term)("Minus term")) ^^ {
    case number ~ list => list.foldLeft(number) {
      // same as before, using alternate name for /:
      case (x, "+" ~ y) => InfixCall("+", x, y)
      case (x, "-" ~ y) => InfixCall("-", x, y)
    }
  }

  def valDef: Parser[Assignable] = ("val".r ~> ident <~ "=") ~ expr ^^ {
    case ident ~ expr => Val(ident.name, expr)
  }

  def varDef: Parser[Assignable] = ("var".r ~> ident <~ "=") ~ expr ^^ {
    case ident ~ expr => Var(ident.name, expr)
  }

  def assingment: Parser[Lang] = (ident <~ "=") ~ expr ^^ {
    case ident ~ expr => Assignment(ident, expr)
  }

  def lang = rep((valDef | varDef | assingment | expr) <~ opt("\n"))

  def genAst(input: Reader): List[Lang] = {
    parseAll(lang, input) match {
      case Success(result, _) => result
      case Failure(msg, _) => println(msg); throw new RuntimeException(msg)
    }
  }
}
