package typecheck

import java.io.{File, FileOutputStream, InputStream, PrintStream}
import java.nio.file.{Files, Path, Paths}
import java.util.Scanner

import grammar.{M2Lexer, M2Parser}
import m3.codegen.IrGen2
import m3.parse.Ast0.Module
import m3.parse.Visitor
import m3.typecheck.{Namespace, TypeChecker}
import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream}
import org.scalatest.FunSuite

/**
  * Created by over on 23.10.17.
  */
class EvalFullTest extends FunSuite {

  def run(args: String*)(onRun: (Int, String, String) => Unit): Unit = {
    def outToString(stream: InputStream): String = {
      val buff = new StringBuffer()
      val out = new Scanner(stream)
      while (out.hasNextLine)
        buff.append(out.nextLine())
      buff.toString
    }

    val program = Runtime.getRuntime.exec(args.toArray)
    val exitCode = program.waitFor()

    val stdout = outToString(program.getInputStream)
    val stderr = outToString(program.getErrorStream)

    onRun(exitCode, stdout, stderr)
  }

  test("main") {
    val lexer = new M2Lexer(new ANTLRInputStream(
      """def main = 42"""))

    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)
    val tree = parser.module()

    val ast = new Visitor("test.abra", "test").visit(tree).asInstanceOf[Module]
    val namespace = new Namespace("test", ast.defs, ast.types, Map.empty)
    TypeChecker.infer(namespace)
    println(ast)
    println(namespace.inferedDefs)
    println(namespace.dumpCode)
    val (lowTypes, lowDefs) = namespace.dumpCode

    val fname = "/tmp/test.ll"
    val ps = new PrintStream(new FileOutputStream(fname))
    IrGen2.gen(ps, Seq(), lowTypes, lowDefs)
    ps.close()
    Files.copy(Paths.get(fname), System.out)

    run("llc-3.8", fname)({ case (exit, stdout, stderr) =>
      println(stdout)
      println(stderr)
    })
  }
}
