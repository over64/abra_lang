#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <time.h>
#include <string.h>

typedef struct {
    char value[100];
    int code;
} res;

int fakeExit(void);

//int __attribute__((noinline)) getValue() {
int getValue() {
    if(__builtin_expect(rand() % 2 == 0, 1)) {
//	errno = 0;
	return 42;
    } else {
	return fakeExit();
    }
}

int main() {
    srand(time(0));
    int value = getValue();
    if(__builtin_expect(errno, 0) == 1) printf("value is %d\n", value);
    else printf("an error occured\n");
}