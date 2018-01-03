#include <stdio.h>

int main ()
{
  int i =5;
  printf ("The square of %d is %d\n", i, squareIt(i));
}

int squareIt (int inputInt)
{
  return (inputInt * inputInt);
}
