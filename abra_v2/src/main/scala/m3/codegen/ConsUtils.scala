package m3.codegen

object ConsUtils {
  def genStringConstuctors(mctx: ModContext): Unit = {
    mctx.strings.zipWithIndex.foreach { case (cst, id) =>
      val encoded = HexUtil.singleByteNullTerminated(cst.getBytes())
      val len = encoded.bytesLen

      mctx.write(s"""@strconst$$$id = private unnamed_addr constant [${encoded.bytesLen} x i8] c"${encoded.str}" """)

      mctx.write(s"define private %String @$$cons.String$id () {")
      mctx.write(s"  %$$alloc = load i8* (i64)*,  i8* (i64)** @evaAlloc")
      mctx.write(s"  %1 = call i8* %$$alloc(i64 $len)")
      mctx.write(s"  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %1, i8* getelementptr inbounds ([$len x i8], [$len x i8]* @strconst$$$id, i32 0, i32 0), i64 $len, i32 1, i1 false)")
      mctx.write(s"  ret %String %1")
      mctx.write(s"}")
    }
  }
}