#include <stdio.h>

#define call(ret, fn, ...) fn(ret, __VA_ARGS__) 

void *rebase(void *ptr);
void boot(void* fn);
void* st_alloc(int size);

int* test_alloc(int a, int b) {
    volatile int first;
    int aa, ab, ac = 10;
    volatile int dyn;

    int* res = st_alloc(a * b);

    printf("stack frame pointer: %p\n", &first);
    printf("allocated pointer at: %p\n", res);
    return res;
}

void code() {
    printf("boot successful\n");
    int *ptr = test_alloc(10, 10);
}

int main() {
    boot(&code);
}