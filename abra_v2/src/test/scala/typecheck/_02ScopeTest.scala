package typecheck

import m3.parse.Ast0.{ScalarDecl, ScalarTh}
import m3.typecheck.{BlockScope, FnScope, TContext}
import org.scalatest.FunSuite

/**
  * Created by over on 23.10.17.
  */
class _02ScopeTest extends FunSuite {
  val tInt = ScalarTh(Seq.empty, "Int", Seq.empty)
  val ctx = TContext.forTest(types = Seq(ScalarDecl(ref = false, Seq.empty, "Int", "i32")), defs = Map())
  test("up/ down") {
    var bottom: FnScope = null
    val root = new FnScope(None) {
      addParam(ctx, "x", tInt)
      mkChild({ parent =>
        new BlockScope(Some(parent)) {
          addLocal(ctx, "y", tInt)
          mkChild({ parent =>
            bottom = new FnScope(Some(parent)) {
              addParam(ctx, "z", tInt)
            }
            bottom
          })
        }
      })
    }

    println(s"root.closure = \n${root.closures}")
    println(s"root.self = \n${root.self.mkString("\n")}")
    println(s"root.down = \n${root.down(true).mkString("\n")}")
    println()
    println(s"bottom.up = \n${bottom.closures}")
    println(s"bottom.self = \n${bottom.self.mkString("\n")}")
    println(s"bottom.down = \n${bottom.down(true).mkString("\n")}")
  }
}
