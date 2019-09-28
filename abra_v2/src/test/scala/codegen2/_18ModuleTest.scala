package codegen2

import org.scalatest.FunSuite

class _18ModuleTest extends FunSuite {
  test("CallImport") {
    CodeGenUtil.runModules({
      case "modA" => """
        def add = x: Int, y: Int do llvm
          %1 = add nsw i32 %x, %y
          ret i32 %1 .Int
        """
      case "main" => """
        import modA .
        def main =
           modA.add(1, 41) .
        """
    }, 42)
  }

  test("native function intermod inline") {
    CodeGenUtil.runModules({
      case "modA" => """
        def add = x: Int, y: Int do llvm
          ;meta intermodule_inline
          %1 = add nsw i32 %x, %y
          ret i32 %1 .Int
        """
      case "main" => """
        import modA .
        def main =
           modA.add(1, 41) .
        """
    }, 42)
  }

  test("CallImport generic") {
    CodeGenUtil.runModules({
      case "modA" => """
        def + = self: Int, y: Int do llvm
          %1 = add nsw i32 %self, %y
          ret i32 %1 .Int

        def add = x: num, y: num do 1 + 1 .
        """
      case "main" => """
        import modA .

        def main =
           modA.add(1, 41) .
        """
    }, 2)
  }

  test("SelfCallImport") {
    CodeGenUtil.runModules({
      case "modA" => """
        def + = self: Int, y: Int do llvm
          %1 = add nsw i32 %self, %y
          ret i32 %1 .Int

        def add = self: Int, y: Int do self + y .
        """
      case "main" => """
        import modA .
        def main =
           1.add(41) .
        """
    }, 42)
  }

  test("SelfCallImport generic") {
    CodeGenUtil.runModules({
      case "modA" => """
        def add = self: num, y: num do 42 .
        """
      case "main" => """
        import modA .

        def main =
           1.add(41) .
        """
    }, 42)
  }


  test("SelfCallImport polymorphic") {
    CodeGenUtil.runModules({
      case "modA" => """
        def add = self: num, y: num do self + y .
        """
      case "main" => """
        import modA .

        def + = self: Int, y: Int do llvm
          %1 = add nsw i32 %self, %y
          ret i32 %1 .Int

        def main =
           1.add(41) .
        """
    }, 42)
  }

  test("intermodule type decl") {
    CodeGenUtil.runModules({
      case "libB" => """
        type B = (x: Int, y: Int)
        """
      case "libA" => """
        import libB .
        type A = (b: libB.B, x: Int)
        """
      case "main" => """
        import libA with A .
        type M = (a: A, x: Int)
        def local = m: M do none .
        def main = 42 .
        """
    }, 42)
  }
}