package parse

import m3.Level
import m3._01parse._
import org.scalatest.FunSuite

class _07ParsePassTest extends FunSuite {
  def checkLevelSeq(expected: List[Seq[String]], root: Level): Unit = {
    assert(root.modules.keys.toSeq.sorted === expected.head.sorted)
    root.next.foreach { level => checkLevelSeq(expected.tail, level) }
  }

  test("build module LevelTree") {
    val root = new m3._01parse.Pass(new Resolver {
      override def resolve(path: String): String = path match {
        case "a" => """
         import some.c, b """
        case "b" => """import some.c """
        case "some/c" => ""
      }
    }, None).pass("a")

    val expectedLevelSeq = Seq("a") :: Seq("b") :: Seq("some/c") :: Nil
    println(s"expected: $expectedLevelSeq")

    checkLevelSeq(expectedLevelSeq, root)
  }

  test("circular module reference") {
    try {
      new m3._01parse.Pass(new Resolver {
        override def resolve(path: String): String = path match {
          case "a" =>
            """import b, c """
          case "b" => "import c"
          case "c" => "import a"
        }
      }, None).pass("a")

    } catch {
      case ex: CircularModReference =>
        assert(ex.stack === Seq("a", "b", "c", "a"))
    }
  }

  test("parse cube demo") {
    //    (1 to 100).foreach { i =>
    val root = new m3._01parse.Pass(new FsResolver(
      libDir = System.getProperty("user.dir") + "/v3/eva/lib/",
      projDir = System.getProperty("user.dir") + "/v3/eva/demo/cube/"
    ), None).pass(".cube")

    val expectedLevelSeq =
      Seq(".cube") ::
        Seq("sys", "sdl", "soil", ".objfile") ::
        Seq("range", "seq", "sreader", "io", "gl", "vec") ::
        Seq("array", "kazmath", "sbuffer") ::
        Seq("arrayUnsafe", "sbufferUnsafe") :: Nil

    println(s"expected: $expectedLevelSeq")
    checkLevelSeq(expectedLevelSeq, root)
    //    }
  }
}
