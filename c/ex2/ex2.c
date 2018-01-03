#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>


char **wordlist=NULL;
int numWords=0;

void makeWordList (char *fileName)
{
  /* Create a file variable to be read.
   * 'cursor' is the number position of the char in a word
   * 'word_count' is the number word and is used in the char* array*/
  FILE *inputFile;
  char ch;
  int cursor=0, word_count=0;

  /* Allocatate memory for upto 8 words 
   * Also allocate memory for upto 6 char's in a word in char* array 0*/
  wordlist = (char**) malloc (sizeof(char*) * 8);
  wordlist[word_count] = (char*) malloc (sizeof(char) *6);

  inputFile = fopen (fileName, "r");
  ch = fgetc (inputFile);
  while (ch != EOF) {
    /* Process character ch into the word list */
   
    if (ch == ' ' || ch == '\n')
    {
      /* This if statement signifies a new word.
       * First all NULL to then end of a word,
       * Then increment word_count for a new word,
       * Then allocate memory for the new word and
       * reset the cursor. */
      wordlist[word_count][cursor] = '\0';
      word_count++;
      wordlist[word_count] = (char*) malloc (sizeof(char) * 6);
      cursor=0;
    }
    else{
      /* Add a new char to a word. Increment the
       * cursor in preparation for the next char. */
      wordlist[word_count][cursor] = ch;
      cursor++;
    }
    
    /* Get the next char from the file */
    ch = fgetc (inputFile);
  }

  /* Add NULL to the end of the last word. 
   * Then close the file. */
  wordlist[word_count][cursor] = '\0';
  numWords = word_count;
  fclose (inputFile);
}


char **getWordList ()
{
  /* Simply return the value in the double ptr 'wordlist'*/
  return wordlist;
}

int getNumWords ()
{
  /* Return number of words. Obtained in makeWordList() */
  return numWords;
}


void compareFiles (char *fileName1, char* fileName2)
{
  char **wordlist1=NULL;
  char **wordlist2=NULL;
  int numwords1=0, numwords2=0, line=1;
  int wordline1[8];
  int wordline2[8];

  /* Create a file variable to be read.
   * 'cursor' is the number position of the char in a word
   * 'word_count' is the number word and is used in the char* array*/
  FILE *inputFile;
  char ch;
  int cursor=0, word_count=0;

  /* Allocatate memory for upto 8 words 
   * Also allocate memory for upto 6 char's in a word in char* array 0*/
  wordlist1 = (char**) malloc (sizeof(char*) * 8);
  wordlist2 = (char**) malloc (sizeof(char*) * 8);
  wordlist1[word_count] = (char*) malloc (sizeof(char) *6);
  wordlist2[word_count] = (char*) malloc (sizeof(char) *6);

  /* Beginning of the word list for the first file */

  inputFile = fopen (fileName1, "r");
  ch = fgetc (inputFile);
  while (ch != EOF) {
    /* Process character ch into the word list */
   
    if (ch == ' ' || ch == '\n')
    {
      /* This if statement signifies a new word.
       * First all NULL to then end of a word,
       * Then increment word_count for a new word,
       * Then allocate memory for the new word and
       * reset the cursor. */
      wordlist1[word_count][cursor] = '\0';
      wordline1[word_count] = line;
      word_count++;
      wordlist1[word_count] = (char*) malloc (sizeof(char) * 6);
      cursor=0;
    }
    else{
      /* Add a new char to a word. Increment the
       * cursor in preparation for the next char. */
      wordlist1[word_count][cursor] = ch;
      cursor++;
    }
    if(ch == '\n')
      line++;
    
    /* Get the next char from the file */
    ch = fgetc (inputFile);
  }

  /* Add NULL to the end of the last word. 
   * Then close the file. */
  wordlist1[word_count][cursor] = '\0';
  numwords1 = word_count;
  fclose (inputFile);

  /* Beginning of the creatation of the word-list for the second file*/

  cursor=word_count=0;
  line=1;
  inputFile = fopen (fileName2, "r");
  ch = fgetc (inputFile);
  while (ch != EOF) {
    /* Process character ch into the word list */
   
    if (ch == ' ' || ch == '\n')
    {
      /* This if statement signifies a new word.
       * First all NULL to then end of a word,
       * Then increment word_count for a new word,
       * Then allocate memory for the new word and
       * reset the cursor. */
      wordlist2[word_count][cursor] = '\0';
      wordline2[word_count] = line;
      word_count++;
      wordlist2[word_count] = (char*) malloc (sizeof(char) * 6);
      cursor=0;
    }
    else{
      /* Add a new char to a word. Increment the
       * cursor in preparation for the next char. */
      wordlist2[word_count][cursor] = ch;
      cursor++;
    }
    if(ch == '\n')
      line++;
    
    /* Get the next char from the file */
    ch = fgetc (inputFile);
  }

  /* Add NULL to the end of the last word. 
   * Then close the file. */
  wordlist2[word_count][cursor] = '\0';
  numwords2 = word_count;
  fclose (inputFile);

  printf ("\n");

  /* This loop prints the output of this function
   * Detects if two strings are the same and have the same line 
   * via the if-statement below*/
  int i=0;
  for(i=0; i<8; i++)
  {
    printf ("Word# %d: ", i);
    if(!(strncmp(wordlist1[i],wordlist2[i],6)) && (wordline1[i] == wordline2[i]))
    {
      printf ("same in each file");
    }
      printf ("\n");
      printf ("\tOn line %d in file %s: %s\n", wordline1[i], fileName1, wordlist1[i]);
      printf ("\tOn line %d in file %s: %s\n", wordline2[i], fileName2, wordlist2[i]);
    
  }
}
