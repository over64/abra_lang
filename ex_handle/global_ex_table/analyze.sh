clang-7 -c -S -g -O3 -emit-llvm program.c
cat program.ll | python3 parse.py > program.pass.ll
cat program.pass.ll
cat tbl.ll program.pass.ll > program.pass2.ll
clang-7 program.pass2.ll -c -S -o program.s