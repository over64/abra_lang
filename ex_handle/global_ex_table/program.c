#include <stdlib.h>

int XX_MARKER_XX = 9000; // marker line for ast-dump

void raise(const char* msg);

void unused$212() {
}

int bar(int x) {
    if(rand() % 2 == 0)
	raise("an error has occured");

    return x * x;
}

int baz(int x, int y) {
    return bar(x) + y;
}

int _main() {
    baz(2, 2);
    return 0;
}
int main() {
    return _main();
}