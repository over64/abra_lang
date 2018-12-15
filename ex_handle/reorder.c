void bar() {
    int x = 1;
    __asm__ __volatile__("":::"memory");
    return;
}
void main() {

}