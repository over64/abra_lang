package parse2

import java.io.{File, RandomAccessFile}

import grammar.M2Lexer
import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream, Token}
import org.scalatest.FunSuite

/**
  * Created by over on 22.08.17.
  */
class ParseTest extends FunSuite {
  test("parser research") {
    val lexer = new M2Lexer(new ANTLRInputStream("import abra.io"))
    val stream = new CommonTokenStream(lexer)
    var token = stream.LT(1)
    stream.consume()

    while (token.getType != Token.EOF) {
      println(s"${token.getType} -> ${token.getText}")
      token = stream.LT(1)
      stream.consume()
    }
  }
}
