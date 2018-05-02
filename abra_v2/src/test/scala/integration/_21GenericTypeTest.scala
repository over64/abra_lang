package integration

import java.nio.file.{Files, Paths}

import org.scalatest.FunSuite

class _21GenericTypeTest extends FunSuite with IntegrationUtil {
  test("simplest array impl") {
    val code = new String(Files.readAllBytes(Paths.get("abra_v2/abra/lib/array.abra")))
    assertCodeEquals(code, exit = Some(13))
  }
}