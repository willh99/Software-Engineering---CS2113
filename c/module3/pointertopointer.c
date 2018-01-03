#include <stdio.h>

int main ()
{
  int i=0;
  int *p = NULL;
  int **p2 = NULL;

  // Fill in the assignments here to make the program work:
  p = &i;
  p2 = &p;
  i = 5;

  printf ("i = %d\n", **p2);
}
