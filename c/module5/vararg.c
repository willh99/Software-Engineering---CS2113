#include <stdio.h>
#include <string.h>
#include <stdarg.h>    // Required include for variable number of arguments.
#include <stdlib.h>

// Function prototype for a function with variable number of arguments:
// This function will take in an integer and any number of strings.
char * concatStrings (int numStrings, ...);


int main ()
{
  char *str;

  // Example with 2 strings.
  str = concatStrings (2, "Hello", " World!");
  printf ("%s\n", str);

  // Example with 6 strings.
  str = concatStrings (6, "So,", " how're", " we", " doing", " today,", " eh?");
  printf ("%s\n", str);
}


// The function with definition and body.

char * concatStrings (int numStrings, ...)
{
  va_list argList;                // The var-arg package requires this variable
                                  // to be defined.

  // Variables for our use:
  char *resultString = malloc (256);        // Resulting string after concatenation.
  char *nextString;               // A variable to hold the next argument.
  int numStringsExtracted = 0;    

  // This call initializes the var-arg package:
  va_start (argList, numStrings);

  // Extract arguments one by one:
  while (numStringsExtracted < numStrings) {

    // Note: first argument is the required argList variable, and
    // the second argument is simply the type of the next argument:
    nextString = va_arg (argList, char*);
    strcat(resultString, nextString);
    numStringsExtracted ++;

  }

  // Required call to signal end of var-args:
  va_end (argList);

  // Return result.
  return resultString;
}
