gcc -c alloc.s -g
gcc -c research.c -g -O3 -fno-omit-frame-pointer
gcc -c research.c -S -O3 -fno-omit-frame-pointer
gcc -o rs alloc.o research.o