//#include
void bench_routine() {
    volatile int x =  43;
    for(int i = 0; i < 100500; i++)
	x += 43;
}