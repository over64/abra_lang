#include <stdio.h>
<<<<<<< HEAD
typedef struct {
    void* callee_addr;
    void* handler_addr;
} eh_table;

void bar() {
entry:
    return;
}

void baz() {
}

const eh_table tbl = { &bar - &baz, NULL };

int main() {
    return 0;
=======
#include <stdlib.h>
#include <errno.h>
#include <time.h>

static __thread int err = 0;
int getValue() {
//    err = 0;
    if(__builtin_expect(rand() % 2 == 0, 1)) {
//	err = 0;
//	__asm__ __volatile__("":::"memory");
	return 42;
    }

    err = 1;
    int undef; 
//    __asm__ __volatile__("":::"memory");
    return undef;
}

int main() {
    srand(time(0));
    int value = getValue();
//    int localErr = err;
    if(!__builtin_expect(err, 0)) printf("value is %d\n", value);
    else       printf("error is %d\n", err);
>>>>>>> research
}