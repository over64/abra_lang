package integration

import codegen.CodeGenUtil
import m3.parse.FsResolver
import org.scalatest.FunSuite

class _02TreePool extends FunSuite {
  test("a binary tree with pool demo") {
    val resolver = new FsResolver(
      "/home/over/build/eva_lang/v3/eva/lib/",
      "/home/over/build/eva_lang/v3/eva/bench_game/")
    CodeGenUtil.runModules(resolver.resolve, 0, entry = ".treeWithPool", prelude = Some("prelude"))
  }
}