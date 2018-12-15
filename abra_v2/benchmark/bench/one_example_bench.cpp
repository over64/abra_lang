#include "benchmark/benchmark_api.h"
#include <set>
#include <vector>
#include <stdlib.h>
#include <obstack.h>
#include "some.h"

#define obstack_chunk_alloc malloc
#define obstack_chunk_free free

// static void BM_VectorInsert(benchmark::State &state) {

//   while (state.KeepRunning()) {
//     std::vector<int> insertion_test;
//     for (int i = 0, i_end = state.range_x(); i < i_end; i++) {
//       insertion_test.push_back(i);
//     }
//   }
// }

// // Register the function as a benchmark
// BENCHMARK(BM_VectorInsert)->RangeMultiplier(2)->Range(1, 8 << 5);


static void BM_Malloc(benchmark::State &state) {
  void* x;
  while (state.KeepRunning()) {
    benchmark::DoNotOptimize(x = malloc(state.range_x()));
    free(x);
  }
}

// Register the function as a benchmark
//BENCHMARK(BM_Malloc)->RangeMultiplier(2)->Range(1, 8 << 11);


static void BM_Obstack_alloc(benchmark::State &state) {
  struct obstack myobstack;
  benchmark::DoNotOptimize(obstack_init(&myobstack));
  void* x;

  while (state.KeepRunning()) {
    benchmark::DoNotOptimize(x = obstack_alloc(&myobstack, state.range_x()));
    obstack_free(&myobstack, x);
  }
}

// Register the function as a benchmark
//BENCHMARK(BM_Obstack_alloc)->RangeMultiplier(2)->Range(1, 8 << 11);

int __attribute__ ((noinline))_add(int x, int y) {
    return x % y;
}

int add(int x, int y) {
    _add(x, y);
}

static void BM_ex_jmp_hack(benchmark::State &state) {
  while (state.KeepRunning()) {
    bench_routine();
  }
}

// Register the function as a benchmark
BENCHMARK(BM_ex_jmp_hack);


static void BM_test(benchmark::State& state) {
  while(state.KeepRunning()) {
      int x = 0;
      for (int i=0; i < 64; ++i) {
        benchmark::DoNotOptimize(x += i);
      }
  }
}

BENCHMARK(BM_test);




//~~~~~~~~~~~~~~~~

// // Define another benchmark
// static void BM_SetInsert(benchmark::State &state) {

//   while (state.KeepRunning()) {
//     std::set<int> insertion_test;
//     for (int i = 0, i_end = state.range_x(); i < i_end; i++) {
//       insertion_test.insert(i);
//     }
//   }
// }
// BENCHMARK(BM_SetInsert)->Range(8, 8 << 10);

BENCHMARK_MAIN();
