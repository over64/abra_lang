package parse

import java.io.CharArrayReader
import java.nio.file.{Files, Paths}

import grammar.EvaLexer
import grammar.EvaLexer.{Emitter, Lex, UnterminatedStringException}
import org.scalatest.FunSuite

object LexUtils {
  def lexRaw(chars: Array[Char], onLex: Long => Unit): Unit = {
    val lexer = new EvaLexer(new CharArrayReader(chars))
    lexer.yybegin(EvaLexer.IDENT)

    var idx = 0
    var token = lexer.yylex()

    while (token != Emitter.EOF) {
      onLex(token)

      idx += 1
      token = lexer.yylex()
    }
  }

  def lexString(input: String, onLex: (Lex, () => String) => Unit): Unit = {
    val chars = input.toCharArray
    lexRaw(chars, { token =>
      onLex(Emitter.decodeLex(token), () =>
        String.valueOf(chars, Emitter.decodeStart(token), Emitter.decodeLen(token)))
    })
  }

  def lexFile(fname: String, onLex: (Lex, () => String) => Unit): Unit =
    lexString(Files.readString(Paths.get(fname)), onLex)

  def assertForInput(input: String, expectedSeq: Seq[(Lex, String)]): Unit = {
    var idx = 0
    lexString(input, onLex = { (lex, lazyText) =>
      val (expectedLex, expectedText) = expectedSeq(idx)
      assert(lex == expectedLex, s"expected at $idx")
      assert(lazyText() == expectedText, s"expected at $idx")

      idx += 1
    })

    assert(idx == expectedSeq.length, "token count mismatch")
  }
}
class _000NewLexer extends FunSuite {

  import LexUtils._

  test("encode test") {
    Lex.values().foreach { lex =>
      assert(Emitter.decodeLex(Emitter.encode(lex, Long.MaxValue, Long.MaxValue)) == lex)
    }
  }

  test("lex keyword") {
    assertForInput("if else do with import type type type",
      Seq((Lex.IDENT, ""), (Lex.IF, "if"), (Lex.ELSE, "else"), (Lex.DO, "do"),
        (Lex.WITH, "with"), (Lex.IMPORT, "import"),
        (Lex.TYPE, "type"), (Lex.TYPE, "type"), (Lex.TYPE, "type")))
  }

  test("lex literal") {
    assertForInput("none true false  1 42 0xFFFAbcd 0.1 0.42 1.00 0.13E1 13.0e23 13e23",
      Seq((Lex.IDENT, ""), (Lex.LIT_NONE, "none"), (Lex.LIT_BOOL, "true"), (Lex.LIT_BOOL, "false"), (Lex.LIT_INT, "1"),
        (Lex.LIT_INT, "42"), (Lex.LIT_INT, "0xFFFAbcd"),
        (Lex.LIT_FLOAT, "0.1"), (Lex.LIT_FLOAT, "0.42"), (Lex.LIT_FLOAT, "1.00"), (Lex.LIT_FLOAT, "0.13E1"), (Lex.LIT_FLOAT, "13.0e23"),
        (Lex.LIT_FLOAT, "13e23")))
  }

  test("lex id") {
    assertForInput("one oneTwoFree кашак кашакКашак one1 neo13neo",
      Seq((Lex.IDENT, ""), (Lex.ID, "one"), (Lex.ID, "oneTwoFree"),
        (Lex.ID, "кашак"), (Lex.ID, "кашакКашак"),
        (Lex.ID, "one1"), (Lex.ID, "neo13neo")))
  }

  test("lex ident opaque") {
    assertForInput(
      """\\
        |\\\\
        |\\
        |
        |
        |a""".stripMargin.replace("\\", " "),
      Seq((Lex.IDENT, "  "), (Lex.IDENT, "    "), (Lex.IDENT, "  "),
        (Lex.IDENT, ""), (Lex.IDENT, ""), (Lex.IDENT, ""), (Lex.ID, "a")))
  }

  test("lex ident") {
    assertForInput(
      """def xxx =
        |  a +
        |    b
        |""".stripMargin,
      Seq((Lex.IDENT, ""), (Lex.DEF, "def"), (Lex.ID, "xxx"), (Lex.EQ, "="),
        (Lex.IDENT, "  "), (Lex.ID, "a"), (Lex.BIN_PLUS, "+"),
        (Lex.IDENT, "    "), (Lex.ID, "b")))
  }

  test("lex string") {
    assertForInput("'hello, world', 'привет, кашак'",
      Seq((Lex.IDENT, ""), (Lex.LIT_STRING, "hello, world"), (Lex.COMMA, ","),
        (Lex.LIT_STRING, "привет, кашак")))
  }

  test("lex comment") {
    assertForInput(
      """-- hello
        |  -- world
        |42""".stripMargin,
      Seq((Lex.IDENT, ""), (Lex.COMMENT, " hello"), (Lex.IDENT, "  "), (Lex.COMMENT, " world"),
        (Lex.IDENT, ""), (Lex.LIT_INT, "42")))
  }

  test("lex string eof") {
    assertThrows[UnterminatedStringException] {
      assertForInput("'hello, world...", Seq((Lex.IDENT, "")))
    }
  }

  test("lex native single line") {
    assertForInput("type Int = native i32",
      Seq((Lex.IDENT, ""), (Lex.TYPE, "type"), (Lex.TYPE_ID, "Int"), (Lex.EQ, "="), (Lex.NATIVE, " i32")))
  }

  test("lex native") {
    assertForInput(
      """def yyy =
        |  def xxx = native
        |    ;native assembly
        |      ;deeper
        |  a""".stripMargin,
      Seq((Lex.IDENT, ""), (Lex.DEF, "def"), (Lex.ID, "yyy"), (Lex.EQ, "="),
        (Lex.IDENT, "  "), (Lex.DEF, "def"), (Lex.ID, "xxx"), (Lex.EQ, "="), (Lex.NATIVE,
          """
            |    ;native assembly
            |      ;deeper""".stripMargin),
        (Lex.IDENT, "  "), (Lex.ID, "a")))
  }

  // FIXME: test: lex native with blank lines
  // FIXME: test: lex comment with EOF

  test("lex cube demo") {
    val projDir = System.getProperty("user.dir")
    val (demoDir, libDir) = (projDir + "/v3/eva/demo/cube/", projDir + "/v3/eva/lib/")
    val demoFiles = Seq("cube", "objfile").map(f => demoDir + f + ".eva")
    val libFiles = Seq("gl", "kazmath", "vec", "sys", "sdl", "soil",
      "array", "arrayUnsafe", "io", "sreader", "range", "seq", "sbuffer", "sbufferUnsafe")
      .map(f => libDir + f + ".eva")

    (0 to 1).foreach { _ =>
      val m1 = System.currentTimeMillis()
      var n = 0

      (demoFiles ++ libFiles).foreach { f =>
        println(s"\nLEX FOR FILE $f")
        lexFile(f, { (lex, lazyText) =>
          print(s"$lex(${lazyText()})")
          n += 1
        })
      }

      val m2 = System.currentTimeMillis()
      println(s"\n__Lex__ pass elapsed: ${m2 - m1}ms; number of tokens: $n")
    }
  }
}
