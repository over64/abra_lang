#include <stdio.h>
#include <string.h>

typedef struct {
   double x;
   int y;
   char* a1;
   int a2;
   char a3;
} S0 ;

typedef struct {
    double x;
    int y;
} SAny;

typedef struct {
    int a1Offset;
    int a2Offset;
    int a3Offset;
} Vtable0 ;

char* cool = "abra is cool";

void check(Vtable0* vt, SAny* any) {
    if(any->x != 1.0) printf("verify error at any->x");
    if(any->y != 2) printf("verify error at any->y");

    char* raw = (char*) any;

    char** a1Ptr = (char**) (raw + vt->a1Offset);
    char* a1 = *a1Ptr;
    if(strcmp(a1, cool) != 0) {
        printf("%d\n", strcmp(a1, cool));
        printf("verify error at any->a1");
    }

    int a2 = *(raw + vt->a2Offset);
    if(a2 != 3) printf("verify error at any->a2");

    int a3 = *(raw + vt->a3Offset);
    if(a3 != 'k') printf("verify error at any->a3");
}

int main() {
    S0 s0;
    s0.x = 1.0;
    s0.y = 2;
    s0.a1 = cool;
    s0.a2 = 3;
    s0.a3 = 'k';

    Vtable0 vt;
    vt.a1Offset = 8 + 4 + 4; // x + y + pad
    vt.a2Offset = 8 + 4 + 4 + 8; // x + y + pad + a1
    vt.a3Offset = 8 + 4 + 4 + 8 + 4; // x + y + pad + a1 + a2

    check(&vt, (SAny*) &s0);
    return 0;
}
