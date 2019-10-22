package integration

import codegen.CodeGenUtil
import m3._01parse.FsResolver
import org.scalatest.FunSuite

class _01Tree extends FunSuite {
  test("a binary tree demo") {
    val resolver = new FsResolver(
      "/home/over/build/eva_lang/v3/eva/lib/",
      "/home/over/build/eva_lang/v3/eva/bench_game/")
    CodeGenUtil.runModules(resolver.resolve, 0, entry = ".tree", prelude = Some("prelude"))
  }
}
