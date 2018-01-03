#include <stdio.h>
#include <stdlib.h>

int main (int argc, char **argv)
{
  if (argc != 3) {
    printf ("Usage: copy <source-file> <destination-file>\n");
    exit (0);
  }

  printf ("Copying from %s to %s ... \n", argv[1], argv[2]);

  // ... Do actual copying ...
  FILE *inputFile, *outputFile;
  char ch;
  inputFile = fopen(argv[1], "r");
  outputFile = fopen(argv[2], "w");

  while(1) {
    ch = fgetc(inputFile);

    // Take char from input file and send to output file
    if(ch == EOF)
      break;
    else
      putc(ch, outputFile);
  }

  printf ("... done\n");
  fclose(inputFile);
  fclose(outputFile);
}
