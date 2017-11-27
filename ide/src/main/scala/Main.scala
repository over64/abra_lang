/**
  * Created by over on 27.08.17.
  */

import java.io.{File, FileInputStream}

import grammar.{M2LexerForIDE, M2Parser}
import org.antlr.v4.runtime._
import org.json4s.native
import org.json4s.NoTypeHints
import spark._
import java.nio.file.Files
import java.nio.file.Paths

import m3.parse.Visitor

import scala.collection.mutable

object Main {
  val keywords = Seq(M2LexerForIDE.IF, M2LexerForIDE.DO, M2LexerForIDE.ELSE, M2LexerForIDE.WHILE,
    M2LexerForIDE.VAL, M2LexerForIDE.VAR, M2LexerForIDE.TYPE, M2LexerForIDE.DEF,
    M2LexerForIDE.IMPORT, M2LexerForIDE.WITH, M2LexerForIDE.MATCH, M2LexerForIDE.OF, M2LexerForIDE.BACK_SLASH, M2LexerForIDE.ARROW_RIGHT,
    M2LexerForIDE.LlBegin, M2LexerForIDE.LL_End)

  val literals = Seq(M2LexerForIDE.IntLiteral, M2LexerForIDE.FloatLiteral, M2LexerForIDE.BooleanLiteral)
  val operators = Seq(M2LexerForIDE.MINUS, M2LexerForIDE.PLUS, M2LexerForIDE.MUL, M2LexerForIDE.DIV,
    M2LexerForIDE.EXCL, M2LexerForIDE.MORE_, M2LexerForIDE.MORE_EQ, M2LexerForIDE.LESS, M2LexerForIDE.LESS_EQ,
    M2LexerForIDE.EQEQ, M2LexerForIDE.NOTEQ, M2LexerForIDE.LOGIC_OR, M2LexerForIDE.LOGIC_AND)

  implicit val formats = native.Serialization.formats(NoTypeHints)

  case class Span(text: String, clazz: String)
  case class Splice(position: Int, deleteCount: Int, add: mutable.Buffer[Span])
  case class FOpen(fd: Int, text: String, splice: Splice)

  def lex(text: String): mutable.Buffer[Span] = {
    val add = mutable.Buffer[Span]()
    var inError = false

    val lexer = new M2LexerForIDE(new ANTLRInputStream(text))
    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens) {
      override def consume(): Token = {
        val token = super.consume()
        System.out.println(token.getText + "->" + token.getType)

        val text =
          token.getType match {
            case M2LexerForIDE.NL => "<br>"
            case M2LexerForIDE.WS => token.getText.replace(" ", "&nbsp").replace("\t", "&nbsp&nbsp&nbsp&nbsp")
            case M2LexerForIDE.StringLiteral => token.getText.replace(" ", "&nbsp").replace("\n", "<br>")
            case M2LexerForIDE.LLVM_NL => token.getText.replace("\n", "<br>")
            case M2LexerForIDE.LLVM_WS => token.getText.replace(" ", "&nbsp")
            case _ => token.getText
          }

        val clazz =
          if(inError) "err"
          else if (keywords.contains(token.getType)) "keyword"
          else if (token.getType == M2LexerForIDE.StringLiteral) "string-literal"
          else if (token.getType == M2LexerForIDE.SELF) "self"
          else if (literals.contains(token.getType)) "literal"
          else if (operators.contains(token.getType)) "operator"
          else if (token.getType == M2LexerForIDE.COMMENT) "comment"
          else if (token.getType == M2LexerForIDE.IrLine) "ir"
          else ""

        add.append(Span(text, clazz))

        token
      }
    }

    parser.setErrorHandler(new DefaultErrorStrategy() {
      override def beginErrorCondition(recognizer: Parser): Unit = {
        inError = true
        super.beginErrorCondition(recognizer)
      }

      override def endErrorCondition(recognizer: Parser): Unit = {
        inError = false
        super.endErrorCondition(recognizer)
      }
    })

    val tree = parser.module()
//    val visitor = new Visitor("test.abra", "test")
//
//    try {
//      visitor.visit(tree)
//    } catch {
//      case t: Throwable =>
//        t.printStackTrace()
//      case e: Exception =>
//        e.printStackTrace()
//    }

    //    var hasNext = true
    //    while (hasNext) {
    //      val token = tokens.LT(1)
    //
    //      if (token.getType == Token.EOF) hasNext = false
    //      else {
    //        val text =
    //          token.getType match {
    //            case M2LexerForIDE.NL => "<br>"
    //            case M2LexerForIDE.WS => token.getText.replace(" ", "&nbsp").replace("\t", "&nbsp&nbsp&nbsp&nbsp")
    //            case M2LexerForIDE.StringLiteral => token.getText.replace(" ", "&nbsp").replace("\n", "<br>")
    //            case M2LexerForIDE.LLVM_NL => token.getText.replace("\n", "<br>")
    //            case M2LexerForIDE.LLVM_WS => token.getText.replace(" ", "&nbsp")
    //            case _ => token.getText
    //          }
    //
    //        val clazz =
    //          if (keywords.contains(token.getType)) "keyword"
    //          else if (token.getType == M2LexerForIDE.StringLiteral) "string-literal"
    //          else if (token.getType == M2LexerForIDE.SELF) "self"
    //          else if (literals.contains(token.getType)) "literal"
    //          else if (operators.contains(token.getType)) "operator"
    //          else if (token.getType == M2LexerForIDE.COMMENT) "comment"
    //          else if (token.getType == M2LexerForIDE.IrLine) "ir"
    //          else ""
    //
    //        add.append(Span(text, clazz))
    //
    //        tokens.consume()
    //      }
    //    }

    add
  }

  var fdSeq = new Function0[Int] {
    var seq = 0
    override def apply(): Int = {
      seq += 1;
      seq
    }
  }
  val fileMap = mutable.HashMap[Int, (String, Seq[Span])]()

  def main(args: Array[String]): Unit = {
    Spark.exception(classOf[Exception], new ExceptionHandler[Exception] {
      override def handle(exception: Exception, request: Request, response: Response): Unit = {
        response.header("Access-Control-Allow-Origin", "*")
        response.status(500)
        exception.printStackTrace(System.out)
      }
    })

    Spark.post("/fopen", new Route {
      override def handle(request: Request, response: Response): AnyRef = {
        response.header("Access-Control-Allow-Origin", "*")

        val fname = "/home/over/build/abra_lang/abra_v2/abra/research/transducers.abra"
        val encoded = Files.readAllBytes(Paths.get(fname))
        val text = new String(encoded, "UTF-8")

        val fd = fdSeq()
        val spans = lex(text)

        fileMap.put(fd, (fname, spans))

        native.Serialization.write(FOpen(fd, text, Splice(0, 0, spans)))
      }
    })

    Spark.post("/fwrite/:fd", new Route {
      override def handle(request: Request, response: Response): AnyRef = {
        response.header("Access-Control-Allow-Origin", "*")
        val fd = request.params(":fd").toInt
        val (fname, oldSpans) = fileMap(fd)

        val text = request.body()
        val spans = lex(text)

        Files.write(Paths.get(fname), text.getBytes)

        native.Serialization.write(Splice(0, 0, spans))
      }
    })
  }
}
