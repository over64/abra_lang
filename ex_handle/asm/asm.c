#include <stdio.h>

int x = 4;
int
main(void)
{
  asm("jmp label");
  puts("You should not see this.");
  asm("label:");

  return 0;
}