package typecheck

import m3.parse.Ast0.ScalarTh
import m3.typecheck.{BlockScope, FnScope}
import org.scalatest.FunSuite

/**
  * Created by over on 23.10.17.
  */
class ScopeTest extends FunSuite {
  val tInt = ScalarTh(Seq.empty, "Int", None)
  test("up/ down") {
    var bottom: FnScope = null
    val root = new FnScope(None) {
      addParam("x", tInt)
      mkChild({ parent =>
        new BlockScope(Some(parent)) {
          addLocal(mut = false, "y", tInt)
          mkChild({ parent =>
            bottom = new FnScope(Some(parent)) {
              addParam("z", tInt)
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
