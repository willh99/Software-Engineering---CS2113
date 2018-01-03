#include <stdio.h>

int main () {

  int i = 5;
  int *intPtr;
  int *intPtr2;

  // Extract the address of variable i into the pointer
  intPtr = &i;
  intPtr2 = &i;

  // Print this address:
  printf ("Variable i is located at address %p\n", intPtr);

  // Use the address:
  *intPtr = *intPtr+1;


  // See what happens:
  printf ("i = %d\n", i);

  // Use second pointer to modify i
  *intPtr2 = 19;
  printf ("i = %d\n", i);
}
