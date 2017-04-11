package m2

import lang_m2.HexUtil
import org.scalatest.FunSuite

class UnicodeTest extends FunSuite {

  test("unicode conversion test") {
    val hw = "а́Hello, World! Привет, мир!\\' \\b \\t \\f \\r \\n"
    val encoded = HexUtil.singleByteNullTerminated(hw.getBytes("utf-8"))
    println(encoded.str)
    println(encoded.bytesLen)
  }
}
