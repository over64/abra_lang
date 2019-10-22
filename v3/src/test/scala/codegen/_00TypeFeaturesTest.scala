package codegen

import codegen.CodeGenUtil._
import grammar.M2Parser
import m3._03codegen.IrUtils.ThIrExtension
import m3._03codegen.ModContext
import m3.Ast0.TypeHint
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.{FunSuite, Ignore}
import parse.ParseUtil

@Ignore
class _00TypeFeaturesTest extends FunSuite {
  val thParser = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.typeHint() }
  }

  implicit class RichString(self: String) {
    def th = {
      val parsed  = thParser.parseStr[TypeHint](self)
      // FIXME
      //TCMeta.setSthModule(parsed, Builtin.builtInMod)
      parsed
    }
  }


  test("is ref type") {
    val (root, main) = astForCode(
      """
        type Ptr = llvm i8* .
        type StrLike = ref llvm i8* .

        type Some = (x: Int, y: Int)
        type Gen[a, b] = (x: a, y: b)

        type R = (x: Int, next: R)

        type A = (x: Int, next: B)
        type B = (x: Int, next: C)
        type C = (x: Int, next: A)

        type U = Node | None
        type Node = (v: Int, next: Node | None)
        type Node2 = (v: Int, next: U)

        type Bar = (u: U)

        # union with type params disabled now
        # type Option[t] = t | None
        # type Node3 = (v: Int, next: Option[Node])
      """)

    def assertRefType(thStr: String) =
      assert(thStr.th.isRefType(ModContext(System.out, root, Seq(main))) === true, s"Expected $thStr IS ref type")

    def assertNotRefType(thStr: String) =
      assert(thStr.th.isRefType(ModContext(System.out, root, Seq(main))) === false, s"Expected $thStr NOT ref type")

    assertNotRefType("Ptr")
    assertRefType("StrLike")
    assertNotRefType("Some")
    assertNotRefType("Gen[Int, Int]")
    assertRefType("Gen[Int, String]")
    assertRefType("Gen[Int, Int | String]")
    assertRefType("R")
    assertRefType("A")
    assertRefType("B")
    assertRefType("C")
    assertRefType("Node")
    assertRefType("U")
    assertRefType("Node | Node2 | None")
    assertRefType("Node | None")
    assertRefType("Node2")
    assertRefType("Bar")
    assertRefType("Array[String]")
    assertRefType("Array[Int]")
    assertRefType("Array5[String]")
    assertNotRefType("Array5[Int]")
  }
}
