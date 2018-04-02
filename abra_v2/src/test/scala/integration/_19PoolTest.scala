package integration

import java.nio.file.{Files, Paths}

import org.scalatest.FunSuite

class _19PoolTest extends FunSuite with IntegrationUtil {
  test("simple pool") {
    val code = new String(Files.readAllBytes(Paths.get("abra_v2/abra/bench_game/pool.abra")))
    assertCodeEquals(code, exit = Some(0))
  }
}