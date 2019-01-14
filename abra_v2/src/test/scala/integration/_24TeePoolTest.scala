package integration

import java.nio.file.{Files, Paths}

import org.scalatest.FunSuite

class _24TeePoolTest extends FunSuite with IntegrationUtil {
  test("simple tree with pool") {
    val code = new String(Files.readAllBytes(Paths.get("abra_v2/abra/bench_game/tree.abra")))
    assertCodeEquals(code, exit = Some(0), isRelease = true)
  }
}