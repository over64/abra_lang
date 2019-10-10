package integration2

import codegen2.CodeGenUtil
import m3.parse.FsResolver
import org.scalatest.FunSuite

class _01Tree extends FunSuite {
  test("a binary tree demo") {
    val resolver = new FsResolver(
      "/home/over/build/abra_lang/v3/abra/lib/",
      "/home/over/build/abra_lang/v3/abra/bench_game/")
    CodeGenUtil.runModules(resolver.resolve, 0, entry = ".tree", prelude = Some("prelude"))
  }
}
