#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <time.h>
#include <string.h>

typedef struct {
    char value[100];
    int code;
} res;

res __attribute__((noinline)) fakeExit2(void) {
    res r = {.code = 1};
    return r;
}
// res __attribute__((noinline)) fakeExit(void);

res __attribute__((noinline)) getValue() {
    if(__builtin_expect(rand() % 2 == 0, 1)) {
        res l1;
	strcpy(l1.value, "aaaa");
	l1.code = 0;
	return l1;
    } else {
	return fakeExit2();
    }
}

int main() {
    srand(time(0));
    res r = getValue();
    if(__builtin_expect(r.code, 0) == 0) printf("value is %s\n", r.value);
    else printf("error is %d\n", r.code);
}