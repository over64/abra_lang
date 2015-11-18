import java.io.Reader

import scala.util.parsing.combinator.{RegexParsers, JavaTokenParsers}

/**
  * Created by over on 18.11.15.
  */
object ASTGen extends RegexParsers {

  //override val whiteSpace = """\s+""".r

  sealed trait LangAST

  sealed trait Val extends LangAST {
    val name: String
  }

  case class NamedVal(name: String, init: LangAST) extends Val

  sealed trait AnonVal extends Val

  case class AnonIVal(name: String, value: Int) extends AnonVal

  case class Ident(name: String) extends LangAST

  case class InfixCall(fn: String, x: LangAST, y: LangAST) extends LangAST

  var nAnonVals = 0

  def ident: Parser[Ident] = "(?!val)[A-Za-z_]\\w*".r ^^ {
    case str => Ident(str)
  }

  def intNumber: Parser[AnonVal] =
    """\d+""".r ^^ {
      case str => nAnonVals += 1; AnonIVal(s"anon$nAnonVals", str.toInt)
    }

  def factor: Parser[LangAST] = intNumber | ident | "(" ~> expr <~ ")"

  def term: Parser[LangAST] = factor ~ rep("*" ~ factor | "/" ~ factor) ^^ {
    case number ~ list => (number /: list) {
      case (x, "*" ~ y) => InfixCall("*", x, y)
      case (x, "/" ~ y) => InfixCall("/", x, y)
    }
  }

  def expr: Parser[LangAST] = term ~ rep("+" ~ log(term)("Plus term") | "-" ~ log(term)("Minus term")) ^^ {
    case number ~ list => list.foldLeft(number) {
      // same as before, using alternate name for /:
      case (x, "+" ~ y) => InfixCall("+", x, y)
      case (x, "-" ~ y) => InfixCall("-", x, y)
    }
  }

  def valDef: Parser[NamedVal] = ("val".r ~> ident <~ "=") ~ expr ^^ {
    case ident ~ expr => NamedVal(ident.name, expr)
  }

  def valDefs = rep(valDef <~ opt("\n"))

  def expressions = rep(expr <~ opt("\n"))

  def lang = rep((valDef | expr) <~ opt("\n"))

  def genAst(input: Reader): List[LangAST] = {
    parseAll(lang, input) match {
      case Success(result, _) => result
      case Failure(msg, _) => println(msg); throw new RuntimeException(msg)
    }
  }
}
