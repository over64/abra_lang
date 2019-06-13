package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _04RetTest extends FunSuite {
  test("ret: scalar value") {
    compile("""def main = 42 .""")
  }

  test("ret: scalar ref") {
    compile("""def main = 'hello' .""")
  }

  test("ret: struct value") {
    compile("""def main = (1, 1) .""")
  }

  test("ret: struct ref") {
    compile("""def main = (1, 'hello') .""")
  }


  test("ret: union value") {
    compile("""def main = 42 .Int | None""")
  }

  test("ret: union ref") {
    compile("""def main = 'hello' .Int | String""")
  }

  test("ret: nullable union ref") {
    compile("""def main = 'hello' .String | None""")
  }

  test("ret: nullable union null") {
    compile("""def main = none .String | None""")
  }
}
