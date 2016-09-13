package m2

import java.io.{File, FileInputStream, FileOutputStream, PrintStream}
import java.nio.file.{Files, Path, Paths}

import grammar2.{M2Lexer, M2Parser}
import lang_m2._
import org.antlr.v4.runtime.{ANTLRFileStream, CommonTokenStream}
import org.scalatest.FunSuite

/**
  * Created by over on 11.09.16.
  */
class IntegrationTests extends FunSuite with LowUtil {
  override val testBase: String = "tl/integration"

  def assertRunEquals(fname: String)(exit: Int, stdout: Option[String] = None, stderr: Option[String] = None) = {
    val reader = new ANTLRFileStream(fname)
    val lexer = new M2Lexer(reader)
    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)

    val tree = parser.module()
    val visitor = new Visitor(fname)
    val ast0 = visitor.visit(tree)
    val typeCheckResult = new TypeChecker().transform(ast0.asInstanceOf[Ast0.Module], visitor.sourceMap)

    typeCheckResult match {
      case TypeCheckSuccess(ast1) =>
        val fnameNoExt = fname.split("\\.").dropRight(1).mkString(".")
        val llMixinFile = new File(fnameNoExt + ".ll")
        val additionalLL =
          if (llMixinFile.exists()) {
            val bytes = Files.readAllBytes(Paths.get(llMixinFile.getPath))
            Some(new String(bytes))
          } else None

        ast1.assertRunEquals(exit, stdout, stderr, additionalLL)
      case TypeCheckFail(at, error) => throw new Exception(s"at ${at.fname}:${at.line}:${at.col} -> \n\t$error")
    }
  }

  test("hello, world") {
    assertRunEquals("tl/integration/hw.abra")(exit = 0, stdout = Some("Привет, мир!"))
  }

  test("floats") {
    assertRunEquals("tl/integration/floats.abra")(exit = 11)
  }

  test("fn pointer") {
    assertRunEquals("tl/integration/fnpointer.abra")(exit = 10)
  }
  test("fake arrays") {
    assertRunEquals("tl/integration/arrays.abra")(exit = 186)
  }
}
