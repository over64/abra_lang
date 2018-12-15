#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <time.h>
#include <string.h>

typedef struct {
    char value[100];
    int code;
} res;

extern __thread int err;

int fakeExit(void);

int __attribute__((noinline)) getValueNI() {
    if(__builtin_expect(rand() % 2 == 0, 1)) {
	err = 0;
	return 42;
    } else {
	int r = fakeExit();
	err = 1;
	return r;
    }
}
void bar() {
    int value = getValueNI();
    if(__builtin_expect(err, 1)) printf("value is %d\n", value);
    else printf("an error occured\n");
}

int getValue() {
    if(__builtin_expect(rand() % 2 == 0, 1)) {
	err = 0;
	return 42;
    } else {
	int r = fakeExit();
	err = 1;
	return r;
    }
}

int main() {
    srand(time(0));
    int value = getValue();
    if(__builtin_expect(err, 1)) printf("value is %d\n", value);
    else printf("an error occured\n");
    return 0;
}