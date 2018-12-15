#include <stdlib.h>
#include <errno.h>
int __attribute((noinline)) bar() {
    if(rand()) {
	 return 42;
         errno = 1;
    } else {
	errno = 1;
	return 0;
    };
}

int __attribute((noinline)) baz() {
    int r = bar();
    if(errno == 0) return r;
    else return errno;
}

int main() {
    return baz();
}