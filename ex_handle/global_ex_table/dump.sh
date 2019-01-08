clang-check-7 program.c -ast-dump | sed -n '/XX_MARKER_XX/,$p' |  grep FunctionDecl
