package m3.grammar

import grammar.EvaLexer.{Emitter, Lex}

import scala.collection.immutable.Seq
import scala.collection.mutable

object ParseNS {
  type PResult[T] = Either[String, T]

  trait WithCtx {
    val ctx: Ctx


    def expected[T](fn: => PResult[T]): PResult[T] =
      fn match {
        case r@Right(_) => r
        case Left(msg) => throw new RuntimeException("expected: " + msg)
      }

    def many[T](notZero: Boolean, delim: => PResult[_],
                rule: => PResult[T]): PResult[Seq[T]] = {
      val buff = mutable.ListBuffer[T]()
      var matched = true
      var (savePos, saveIdent) = (ctx.pos, ctx.lastIdent)
      var lastMsg: String = "__unreachable_no_message__"

      rule match {
        case Right(value) => buff += value; savePos = ctx.pos; saveIdent = ctx.lastIdent
        case Left(msg) => ctx.pos = savePos; ctx.lastIdent = saveIdent; matched = false; lastMsg = msg
      }

      while (matched) {
        delim match {
          case Right(_) =>
            rule match {
              case Right(value) => buff += value; savePos = ctx.pos; saveIdent = ctx.lastIdent
              case Left(msg) => ctx.pos = savePos; ctx.lastIdent = saveIdent; matched = false; lastMsg = msg
            }
          case Left(msg) => ctx.pos = savePos; ctx.lastIdent = saveIdent; matched = false; lastMsg = msg
        }
      }

      if (notZero && buff.isEmpty) Left(lastMsg) else Right(buff.toSeq)
    }

    def or[T](rules: (() => PResult[T])*): PResult[T] = {
      var (i, savePos, saveIdent) = (0, ctx.pos, ctx.lastIdent)
      val anyOf = mutable.ListBuffer[String]()
      while (i < rules.length) {
        rules(i)() match {
          case right@Right(_) => return right
          case Left(msg) => ctx.pos = savePos; ctx.lastIdent = saveIdent; anyOf += msg
        }
        i += 1
      }

      Left("one of: " + anyOf.mkString(" or "))
    }

    def opt[T](rule: => PResult[T]): PResult[Option[T]] = {
      val (savePos, saveIdent) = (ctx.pos, ctx.lastIdent)
      rule match {
        case Right(value) => Right(Some(value))
        case Left(_) => ctx.pos = savePos; ctx.lastIdent = saveIdent; Right(None)
      }
    }
  }

  final class Ctx(source: Array[Char], stream: Array[Long],
                  var pos: Int, var lastIdent: Int) {
    def next(): Long = {
      pos += 1
      stream(pos - 1)
    }

    def tokenText(token: Long): String =
      String.valueOf(source, Emitter.decodeStart(token), Emitter.decodeLen(token))

    def tryFetch[T](lex: Lex, fn: Long => PResult[T]): PResult[T] = {
      pos += 1
      if (pos > stream.length) Left("EOF")
      else {
        val token = stream(pos - 1)
        if (Emitter.decodeLex(token) == lex) {
          println(s"fetch(${pos - 1}): $lex")
          fn(token)
        } else Left(lex.name())
      }
    }
  }
}