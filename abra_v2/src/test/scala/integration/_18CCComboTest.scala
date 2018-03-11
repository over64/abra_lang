package integration

import java.nio.file.{Files, Paths}

import org.scalatest.FunSuite

class _18CCComboTest extends FunSuite with IntegrationUtil {
  test("simple binary tree") {
    val code = new String(Files.readAllBytes(Paths.get("abra_v2/abra/bench_game/tree.abra")))
    assertCodeEquals(code, exit = Some(13))
  }
}