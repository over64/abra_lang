package integration

import java.nio.file.{Files, Paths}

import org.scalatest.FunSuite

class _23TreeTest extends FunSuite with IntegrationUtil {
  test("simple tree") {
    val code = new String(Files.readAllBytes(Paths.get("abra_v2/abra/bench_game/treeNoPool.abra")))
    assertCodeEquals(code, exit = Some(0), isRelease = true)
  }
}