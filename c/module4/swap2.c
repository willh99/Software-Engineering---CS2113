#include <stdio.h>

void swap2 (int first, int second)
{
  int temp = first;
  first = second;
  second = temp;
}

int main ()
{
  int i = 5, j = 6;
  swap2 (i, j);
  printf ("i=%d j=%d\n", i, j);
}
