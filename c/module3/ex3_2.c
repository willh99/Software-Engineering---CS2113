#include <stdio.h>

int main (void) {

    int i = 5;
    char *charPtr;  // Pointer declaration
    int j;

    // Make the char pointer point to the start of the integer:
    charPtr = (char*) (&i);

    // Extract the byte, store it in the integer j and print j.
    // Also print the address stored within charPtr
    j = (int) *charPtr;
    printf ("First byte: %d\t\t charPtr=%p\n", j, charPtr);

    // get the next byte and print:
    j = (int) *(charPtr+1);
    printf ("Second byte: %d\n", j);

    // get the third byte and print:
    j = (int) *(charPtr+2);
    printf ("Third byte: %d\n", j);

    // get the fourth byte and print:
    j = (int) *(charPtr+3);
    printf ("Fourth byte: %d\n", j);
}
