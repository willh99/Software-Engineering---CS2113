#include <stdio.h>

void swap (int *first, int *second)
{
  int temp = *first;
  printf ("first:%p  second:%p\n", first, second);

  *first = *second;
  *second = temp;
  printf ("frist:%p  second:%p\n", first, second);
}

int main ()
{
  int i=5, j=6;
  swap (&i, &j);
  printf ("i=%d j=%d\n", i, j);
}
