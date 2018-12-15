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

void __attribute__((noinline)) set_err_zero() {
    err = 0;
}
void __attribute__((noinline)) set_err_one() {
    err = 1;
}
int __attribute__((noinline)) get_errno() {
    return errno;
}
int fakeExit(void);
void payload();

int __attribute__((noinline)) getValueNI() {
    if(__builtin_expect(rand() % 2 == 0, 1)) {
	return 42;
    } else {
	return fakeExit();
    }
}

int getValue2() {
    if(__builtin_expect(rand() % 2 == 0, 1)) {
	errno = 0;
	return 42;
    } else {
	return fakeExit();
    }
}


//int __attribute__((noinline)) getValue() {
int getValue() {
    if(__builtin_expect(rand() % 2 == 0, 1)) {
//	err = 0;
	set_err_zero();
	return 42;
    } else {
	set_err_one();
	return fakeExit();
//	err = 1;
//	return r;
    }
}

int main() {
    srand(time(0));
    int value = getValue();
    if(__builtin_expect(err, 1)) printf("value is %d\n", value);
    else printf("an error occured\n");
    return 0;
}