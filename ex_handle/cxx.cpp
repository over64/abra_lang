#include <stdlib.h>
int __attribute((noinline)) bar() {
    if(rand()) return 42; else throw 1;
}

int __attribute((noinline)) baz() {
    try {
        return bar();
    } catch(int e)  {
	return e;
    }
}

int main() {
    return baz();
}