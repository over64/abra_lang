package integration

import java.nio.file.{Files, Paths}

import org.scalatest.FunSuite

class _24CubeTest extends FunSuite with IntegrationUtil {
  test("cube over sdl & openGL") {
    val code = new String(Files.readAllBytes(Paths.get("abra_v2/abra/demo/cube.abra")))
    assertCodeEquals(code, exit = Some(0), isRelease = true)
  }
}