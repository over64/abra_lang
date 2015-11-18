import scala.util.parsing.combinator.{RegexParsers, JavaTokenParsers}

/**
  * Created by over on 18.11.15.
  */
object ASTGen extends RegexParsers {

  object Calculator extends RegexParsers {
    def number: Parser[Double] =
      """\d+(\.\d*)?""".r ^^ {
        _.toDouble
      }

    def factor: Parser[Double] = number | "(" ~> expr <~ ")"

    def term: Parser[Double] = factor ~ rep("*" ~ factor | "/" ~ factor) ^^ {
      case number ~ list => (number /: list) {
        case (x, "*" ~ y) => x * y
        case (x, "/" ~ y) => x / y
      }
    }

    def expr: Parser[Double] = term ~ rep("+" ~ log(term)("Plus term") | "-" ~ log(term)("Minus term")) ^^ {
      case number ~ list => list.foldLeft(number) {
        // same as before, using alternate name for /:
        case (x, "+" ~ y) => x + y
        case (x, "-" ~ y) => x - y
      }
    }

    def apply(input: String): Double = parseAll(expr, input) match {
      case Success(result, _) => result
      case failure: NoSuccess => scala.sys.error(failure.msg)
    }
  }

  sealed trait LangAST

  sealed trait Val extends LangAST {
    val `type`: String
    val name: String
  }

  case class IVal(value: Int, name: String) extends Val {
    val `type` = "Int"
  }

  case class InfixCall(fn: String, x: LangAST, y: LangAST) extends LangAST

  var nAnonVals = 0

  def intNumber: Parser[Val] =
    """\d+""".r ^^ {
      case str => nAnonVals += 1; IVal(str.toInt, s"anon$nAnonVals")
    }

  def factor: Parser[LangAST] = intNumber | "(" ~> expr <~ ")"

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

  def getAst(input: String): LangAST = {
    parseAll(expr, input) match {
      case Success(result, _) => result
      case Failure(msg, _) => throw new RuntimeException(msg)
    }
  }
}
