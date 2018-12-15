#include <stdio.h>
#include <stdlib.h>

void* eh_raised_address(void);
int eh_has_err(void);
int eh_raise(void);

//int __attribute((noinline)) bar(void) {
int bar(void) {
    printf("hello");
    if(rand() == 32) return 11;
    else eh_raise();
}

int main() {
    int x = bar();
    printf("hello");
    if(rand() == 32) return 11;
    else goto bar_fail1;

bar_success1:
    printf("first bar ret: %d\n", x);
    bar();
bar_success2:
    0;

    void *magic = (void*) 0x123;
    if(!__builtin_expect(*((int*) magic), 0))
        return 0;

    void* addr = eh_raised_address();

    if(addr == &&bar_success1) {
bar_fail1:
	x = 42;
	goto bar_success1;
    } else if (addr == &&bar_success2) {
bar_fail2:
        printf("eh for bar call 2");
	return eh_raise();
    }
}