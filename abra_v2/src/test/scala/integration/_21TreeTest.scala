package integration

import java.nio.file.{Files, Paths}

import org.scalatest.FunSuite

class _21TreeTest extends FunSuite with IntegrationUtil {
  test("simple tree no pool") {
    val code = new String(Files.readAllBytes(Paths.get("abra_v2/abra/bench_game/tree_no_pool.abra")))
    assertCodeEquals(code, exit = Some(0))
  }
}