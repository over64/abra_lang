import java.io.Reader

import scala.util.parsing.combinator.{RegexParsers, JavaTokenParsers}

/**
  * Created by over on 18.11.15.
  */
object AST {

  sealed trait Lang

  trait Assignable extends Lang

  case class Ident(name: String) extends Assignable {
    override def toString() = name
  }

  sealed trait Const extends Assignable {
    val name: String
  }

  case class IConst(name: String, value: Int) extends Const {
    override def toString() = value.toString
  }

  case class BoolConst(name: String, value: Boolean) extends Const {
    override def toString() = value.toString
  }

  case class Val(name: String, init: Assignable) extends Assignable {
    override def toString() = s"val $name = $init"
  }

  case class Var(name: String, init: Assignable) extends Assignable {
    override def toString() = s"var $name = $init"
  }

  case class InfixCall(fn: String, x: Assignable, y: Assignable) extends Assignable {
    override def toString() = s"$fn($x, $y)"
  }

  case class Assignment(i: Ident, assignment: Assignable) extends Lang {
    override def toString() = s"$i = $assignment"
  }

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

  def booleanConst: Parser[Const] = ("true".r | "false") ^^ {
    case str => nConsts += 1; BoolConst(s"const$nConsts", str.toBoolean)
  }

  def const: Parser[Const] = intNumber | booleanConst

  def factor: Parser[Assignable] = const | ident | "(" ~> expr <~ ")"

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

  object AST2 {

    sealed trait Lang
    sealed trait Assignable extends Lang

    case class Ident(name: String) extends Assignable {
      override def toString() = name
    }

    sealed trait Const extends Assignable
    case class IConst(value: Int) extends Assignable {
      override def toString() = value.toString
    }
    case class BoolConst(value: Boolean) extends Assignable {
      override def toString() = value.toString
    }

    case class UnaryCall(op: String, x: Assignable) extends Assignable {
      override def toString() = s"$op$x"
    }

    case class BinaryCall(op: String, x: Assignable, y: Assignable) extends Assignable {
      override def toString() = s"$op($x, $y)"
    }

    case class Val(name: String, typeDef: Option[String], init: Assignable) extends Assignable {
      override def toString() = s"val ${typeDef.map(t => s":$t").getOrElse("")} $name = $init"
    }
    case class Var(name: String, typeDef: Option[String], init: Assignable) extends Assignable {
      override def toString() = s"var ${typeDef.map(t => s":$t").getOrElse("")} $name = $init"
    }

    case class Assignment(i: Ident, assignment: Assignable) extends Lang {
      override def toString() = s"$i = $assingment"
    }
  }


  def ident2: Parser[AST2.Ident] = "(?!val)(?!var)(?!true)(?!false)[A-Za-z_]\\w*".r ^^ {
    case str => AST2.Ident(str)
  }

  def intNumber2: Parser[AST2.IConst] =
    """\d+""".r ^^ {
      case str => nConsts += 1; AST2.IConst(str.toInt)
    }

  def booleanConst2: Parser[AST2.BoolConst] = ("true".r | "false") ^^ {
    case str => nConsts += 1; AST2.BoolConst(str.toBoolean)
  }

  def operable: Parser[AST2.Assignable] = ident2 | intNumber2 | booleanConst2 | "(" ~> expr2 <~ ")"

  def unaryCall: Parser[AST2.UnaryCall] = ("!".r | "+" | "-") ~ operable ^^ {
    case op ~ x => AST2.UnaryCall(op, x)
  }

  def firstPriority: Parser[AST2.Assignable] = operable | unaryCall

  def secondPriority: Parser[AST2.Assignable] = firstPriority ~ rep("*" ~ firstPriority | "/" ~ firstPriority) ^^ {
    case first ~ list => list.foldLeft(first) {
      case (x, op ~ y) => AST2.BinaryCall(op, x, y)
    }
  }

  def expr2: Parser[AST2.Assignable] = secondPriority ~ rep("+" ~ secondPriority | "-" ~ secondPriority) ^^ {
    case first ~ list => list.foldLeft(first) {
      case (x, op ~ y) => AST2.BinaryCall(op, x, y)
    }
  }

  def valDef2: Parser[AST2.Assignable] = ("val".r ~> opt("Int" | "Bool")) ~ (ident2 <~ "=") ~ expr2 ^^ {
    case typeDef ~ ident ~ expr2 => AST2.Val(ident.name, typeDef, expr2)
  }

  def varDef2: Parser[AST2.Assignable] = ("var".r ~> opt("Int" | "Bool")) ~ (ident2 <~ "=") ~ expr2 ^^ {
    case typeDef ~ ident ~ expr2 => AST2.Var(ident.name, typeDef, expr2)
  }

  def assingment2: Parser[AST2.Lang] = (ident2 <~ "=") ~ expr2 ^^ {
    case ident ~ expr => AST2.Assignment(ident, expr)
  }

  def lang2 = rep((valDef2 | varDef2 | assingment2 | expr2) <~ opt("\n"))

  def genAst2(input: Reader): List[AST2.Lang] = {
    parseAll(lang2, input) match {
      case Success(result, _) => result
      case Failure(msg, _) => println(msg); throw new RuntimeException(msg)
    }
  }
}
