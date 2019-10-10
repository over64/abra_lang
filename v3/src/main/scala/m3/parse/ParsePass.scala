package m3.parse

import java.nio.file.{Files, Paths}

import grammar.{M2LexerForIDE, M2Parser}
import m3.parse.Ast0.Module
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.antlr.v4.runtime.{ANTLRInputStream, BailErrorStrategy, CommonTokenStream, InputMismatchException}

import scala.collection.mutable

trait Resolver {
  def resolve(path: String): String
}

class FsResolver(libDir: String, projDir: String) extends Resolver {
  override def resolve(path: String): String =
    new String(Files.readAllBytes(Paths.get(
      if (path.startsWith("."))
        projDir + path.stripPrefix(".") + ".eva"
      else
        libDir + path + ".eva"
    )))
}

class CircularException() extends Exception

case class Level(modules: mutable.HashMap[String, Module], var next: Option[Level]) {
  def append(level: Level): Unit =
    next match {
      case Some(n) => n.append(level)
      case None => next = Some(level)
    }

  def findMod(path: String): Option[Module] =
    modules.get(path) match {
      case s: Some[Module] => s
      case None =>
        next.flatMap(level => level.findMod(path))
    }

  def stealMod(path: String): Option[Module] =
    modules.get(path) match {
      case s: Some[Module] =>
        val removed = modules.remove(path)

        if (modules.keys.isEmpty) {
          modules.put(path, removed.get)
          throw new CircularException()
        }

        s
      case None =>
        next.flatMap { level =>
          level.stealMod(path)
        }
    }

  def eachModule(callback: (Level, Module) => Unit): Unit = {
    next.foreach(level => level.eachModule(callback))
    modules.values.foreach(module => callback(this, module))
  }
}

class CircularModReference(val stack: Seq[String]) extends Exception

class ParsePass(resolver: Resolver, prelude: Option[String]) {
  def parseMod(path: String, code: String): Module = {
    val lexer = new M2LexerForIDE(new ANTLRInputStream(code))
    val tokens = new CommonTokenStream(lexer)

    val parser = new M2Parser(tokens)
    parser.setErrorHandler(new BailErrorStrategy)
    val visitor = new Visitor(code, path, path, prelude)

    try {
      visitor.visit(parser.module()).asInstanceOf[Module]
    } catch {
      case ex: ParseCancellationException =>
        val cause = ex.getCause.asInstanceOf[InputMismatchException]
        throw new RuntimeException("parse error: " + cause.getOffendingToken)
    }
  }

  def throwLoop(stack: Seq[String], root: Level, terminal: String): Unit =
    root.next match {
      case Some(level) =>
        root.modules.keys.foreach { path => throwLoop(stack :+ path, level, terminal) }
      case None =>
        root.modules.keys.foreach { path =>
          if ((stack :+ path) contains terminal) throw new CircularModReference(stack :+ path :+ terminal)
        }
    }

  def recursiveParse(deep: Int, root: Level, paths: Seq[String]): Level = {
    val current = Level(mutable.HashMap.empty, None)

    paths.map { path =>
      try {
        val module = root.stealMod(path)
        module match {
          case Some(module) =>
            println(s"${"\t" * deep}cache $path")
            current.modules.put(path, module)
          case None =>
            println(s"${"\t" * deep}parse $path")
            current.modules.put(path, parseMod(path, resolver.resolve(path)))
        }
      } catch {
        case ex: CircularException =>
          if (path != "prelude") {
            println(s"${"\t" * deep}circular reference $path")
            root.append(current)
            throwLoop(Seq.empty, root.next.get /* root is always empty by design */ , path)
            throw new RuntimeException("internal compiler error") // if throwLoop will not throw
          }

          None
      }
    }

    val nextPaths = current.modules.values.flatMap { module => module.imports.seq.map(_.path) }.toSeq.distinct
    root.append(current)

    if (nextPaths.nonEmpty) recursiveParse(deep + 1, root, nextPaths)
    current
  }

  def pass(entry: String): Level = {
    val m1 = System.currentTimeMillis()
    val level = recursiveParse(0, Level(mutable.HashMap.empty, None), Seq(entry))
    val m2 = System.currentTimeMillis()
    println(s"__Parse__ pass elapsed: ${m2 - m1}ms")
    level
  }
}
