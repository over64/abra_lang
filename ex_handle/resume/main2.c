int foo(int flag) {
    if(flag) return 42; else return 0;
}

int bar(int flag) {
    return foo(flag);
}