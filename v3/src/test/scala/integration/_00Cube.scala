package integration

import codegen.CodeGenUtil
import m3.parse.FsResolver
import org.scalatest.FunSuite


// TODO - не создавать функцию-конструктор для каждой константы
class _00Cube extends FunSuite {
  test("a cube demo") {
    val resolver = new FsResolver(
      "/home/over/build/abra_lang/v3/abra/lib/",
      "/home/over/build/abra_lang/v3/abra/demo/")
    CodeGenUtil.runModules(resolver.resolve, 0, entry = ".cube", prelude = Some("prelude"),
      linkerFlags = Seq("-lGL", "-lSOIL", "-lSDL2", "-lkazmath"))
  }
}
