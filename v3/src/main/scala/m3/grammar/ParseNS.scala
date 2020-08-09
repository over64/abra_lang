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
      val savepoint = new Savepoint(); ctx.save(savepoint)
      var lastMsg: String = "__unreachable_no_message__"

      rule match {
        case Right(value) => buff += value; ctx.save(savepoint)
        case Left(msg) => ctx.restore(savepoint); matched = false; lastMsg = msg
      }

      while (matched) {
        delim match {
          case Right(_) =>
            rule match {
              case Right(value) => buff += value; ctx.save(savepoint)
              case Left(msg) => ctx.restore(savepoint); matched = false; lastMsg = msg
            }
          case Left(msg) => ctx.restore(savepoint); matched = false; lastMsg = msg
        }
      }

      if (notZero && buff.isEmpty) Left(lastMsg) else Right(buff.toSeq)
    }

    def or[T](rules: (() => PResult[T])*): PResult[T] = {
      var i = 0
      val savepoint = new Savepoint(); ctx.save(savepoint)
      val anyOf = mutable.ListBuffer[String]()
      while (i < rules.length) {
        rules(i)() match {
          case right@Right(_) => return right
          case Left(msg) => ctx.restore(savepoint); anyOf += msg
        }
        i += 1
      }

      Left("one of: " + anyOf.mkString(" or "))
    }

    def opt[T](rule: => PResult[T]): PResult[Option[T]] = {
      val savepoint = new Savepoint(); ctx.save(savepoint)
      rule match {
        case Right(value) => Right(Some(value))
        case Left(_) => ctx.restore(savepoint); Right(None)
      }
    }
  }

  class Savepoint(var pos: Int = 0, var iPtr: Int = 0)
  final class Ctx(source: Array[Char], stream: Array[Long], var pos: Int,
                  iStack: Array[Int] = Array.fill(128)(0), var iPtr: Int = 0) {
    def identCur = iStack(iPtr)
    def identPop() = iPtr -= 1
    def identPush(ident: Int) = {
      iPtr += 1
      iStack(iPtr) = ident
    }

    def save(point: Savepoint): Unit = {
      point.pos = pos; point.iPtr = iPtr
    }
    def restore(point: Savepoint): Unit = {
      pos = point.pos; iPtr = point.iPtr
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