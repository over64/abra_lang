package integration

import codegen.CodeGenUtil
import m3.parse.FsResolver
import org.scalatest.FunSuite


// TODO - не создавать функцию-конструктор для каждой строковой константы
class _00Cube extends FunSuite {
  test("a cube demo") {
    val resolver = new FsResolver(
      "/home/over/build/eva_lang/v3/eva/lib/",
      "/home/over/build/eva_lang/v3/eva/demo/")
    CodeGenUtil.runModules(resolver.resolve, 0, entry = ".cube", prelude = Some("prelude"),
      linkerFlags = Seq("-lGL", "-lSOIL", "-lSDL2", "-lkazmath"))
  }
}
