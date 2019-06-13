package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _08LambdaDeclTest extends FunSuite {
  test("no closure") {
    compile(
      """
        def main =
          z = lambda x: Int, y: Int ->
            42 ..
      """)
  }

  test("closure local: value scalar") {
    compile(
      """
        def main =
          x1 = 0

          z = lambda
            x1 = 42
            x1 ..
      """)
  }


  test("closure local: ref scalar") {
    compile(
      """
        def main =
          x1 = 'hello'

          z = lambda
            x1 = 'world'
            x1 ..
      """)
  }

  test("closure local: value struct") {
    compile(
      """
        def main =
          x1 = (1, 1)

          z = lambda
            x1 = (2, 2)
            x1 ..
      """)
  }

  test("closure local: ref struct") {
    compile(
      """
        def main =
          x1 = (1, 'hello')

          z = lambda
            x1 = (2, 'world')
            x1 ..
      """)
  }

  test("closure local: value union") {
    compile(
      """
        def main =
          x1: Int | None = 42

          z = lambda
            x1 = none
            x1 ..
      """)
  }

  test("closure local: ref union") {
    compile(
      """
        def main =
          x1: Int | String | None = 42

          z = lambda
            x1 = none
            x1 ..
      """)
  }

  test("closure local: nullable union") {
    compile(
      """
        def main =
          x1: String | None = 'hello'

          z = lambda
            x1 = none
            x1 ..
      """)
  }

  test("closure param: value scalar") {
    compile(
      """
        def some = x: Int do
           lambda
             y = x
             x ..
      """)
  }

  test("closure param: ref scalar") {
    compile(
      """
        def some = x: String do
           lambda
             y = x
             x ..
      """)
  }

  test("closure param: value struct") {
    compile(
      """
        def some = x: (f1: Int, f2: Int) do
           lambda
             y = x
             yy = x.f1
             x.f1 = 42
             x ..
      """)
  }

  test("closure param: ref struct") {
    compile(
      """
        def some = x: (f1: Int, f2: String) do
           lambda
             y = x
             yy = x.f2
             x.f1 = 42
             x.f2 = 'hello'
             x ..
      """)
  }

  test("closure param: value union") {
    compile(
      """
        def some = x: Int | None do
           lambda
             y = x
             x ..
      """)
  }

  test("closure param: ref union") {
    compile(
      """
        def some = x: Int | String do
           lambda
             y = x
             x ..
      """)
  }

  test("closure param: nullable union") {
    compile(
      """
        def some = x: String | None do
           lambda
             y = x
             x ..
      """)
  }
}
