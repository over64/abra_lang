package typecheck

import m3.parse.Ast0.{ScalarDecl, ScalarTh}
import m3.typecheck.{BlockScope, FnScope, Namespace, TContext}
import org.scalatest.FunSuite

/**
  * Created by over on 23.10.17.
  */
class ScopeTest extends FunSuite {
  val tInt = ScalarTh(Seq.empty, "Int", None)
  val ctx = new TContext()
  val namespace = new Namespace(pkg = "test", types = Seq(ScalarDecl(pkg = "test", ref = false, Seq.empty, "Int", "i32")))
  test("up/ down") {
    var bottom: FnScope = null
    val root = new FnScope(None) {
      addParam(ctx, namespace, "x", tInt)
      mkChild({ parent =>
        new BlockScope(Some(parent)) {
          addLocal(ctx, namespace, "y", tInt)
          mkChild({ parent =>
            bottom = new FnScope(Some(parent)) {
              addParam(ctx, namespace, "z", tInt)
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
