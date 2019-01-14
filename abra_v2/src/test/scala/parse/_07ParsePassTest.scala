package parse

import m3.parse._
import org.scalatest.FunSuite

class _07ParsePassTest extends FunSuite {
  def checkLevelSeq(expected: List[Seq[String]], root: Level): Unit = {
    assert(root.modules.keys.toSeq.sorted === expected.head.sorted)
    root.next.foreach { level => checkLevelSeq(expected.tail, level) }
  }

  test("build module LevelTree") {
    val root = new ParsePass(new Resolver {
      override def resolve(path: String): String = path match {
        case "a" => """
         import
           some/c
           b ."""
        case "b" => """import some/c ."""
        case "some/c" => ""
      }
    }).pass("a")

    val expectedLevelSeq = Seq("a") :: Seq("b") :: Seq("some/c") :: Nil
    println(s"expected: $expectedLevelSeq")

    checkLevelSeq(expectedLevelSeq, root)
  }

  test("circular module reference") {
    try {
      new ParsePass(new Resolver {
        override def resolve(path: String): String = path match {
          case "a" =>
            """import
               b
               c ."""
          case "b" => "import c ."
          case "c" => "import a ."
        }
      }).pass("a")

    } catch {
      case ex: CircularModReference =>
        assert(ex.stack === Seq("a", "b", "c", "a"))
    }
  }

  test("parse cube demo") {
    //(1 to 100).foreach { i =>
    val root = new ParsePass(new FsResolver(
      libDir = System.getProperty("user.dir") + "/abra_v2/abra/lib/",
      projDir = System.getProperty("user.dir") + "/abra_v2/abra/demo/"
    )).pass("cube")

    val expectedLevelSeq =
      Seq("cube") ::
        Seq("/sys", "/sdl", "/soil", "objLoader") ::
        Seq("/vec", "/range", "/sreader", "/io", "/gl") ::
        Seq("/array", "/kazmath", "/sbuffer") ::
        Seq("/universe") :: Nil

    println(s"expected: $expectedLevelSeq")
    checkLevelSeq(expectedLevelSeq, root)
    // }
  }
}
