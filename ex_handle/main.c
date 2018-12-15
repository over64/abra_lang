#include <stdio.h>
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
}