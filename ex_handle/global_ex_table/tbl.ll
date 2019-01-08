%struct.eh_table = type { i16, i16 }
@tbl.bar = constant %struct.eh_table {
  i16 trunc(
    i64 sub(
      i64 ptrtoint(i8* blockaddress(@bar, %eh_branch1) to i64),
      i64 ptrtoint(i8* bitcast(i32 (i32)* @bar to i8*) to i64)) to i16),
  i16 0}
@tbl.baz = constant %struct.eh_table {
  i16 trunc(
    i64 sub(
      i64 ptrtoint(i8* blockaddress(@baz, %eh_branch3) to i64),
      i64 ptrtoint(i8* bitcast(i32 (i32, i32)* @baz to i8*) to i64)) to i16),
  i16 0}
@tbl._main = constant %struct.eh_table {
  i16 trunc(
    i64 sub(
      i64 ptrtoint(i8* blockaddress(@_main, %eh_branch5) to i64),
      i64 ptrtoint(i8* bitcast(i32 ()* @_main to i8*) to i64)) to i16),
  i16 0}
@tbl.main = constant %struct.eh_table {
  i16 trunc(
    i64 sub(
      i64 ptrtoint(i8* blockaddress(@main, %eh_branch7) to i64),
      i64 ptrtoint(i8* bitcast(i32 ()* @main to i8*) to i64)) to i16),
  i16 0}
