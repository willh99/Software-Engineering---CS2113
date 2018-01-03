#include <stdio.h>

int main () 
{
  int num_rows = 0, i=0, j=0;

  printf ("Enter the number of rows in your Pascal Triangle:\n");
  scanf("%d", &num_rows);
  int triangle[num_rows][num_rows];
  int lines = num_rows-1;

  /* Set the values for each number in the triangle. '
   * The first and last number in each row are set to 1.
   * Otherwise, add the two numbers 'above' the current number.  */
  for (i=0; i<num_rows; i++) 
  {
    for (j=0; j<=i; j++)
    {
      if(j==0 || i==j)
        triangle[i][j] = 1;
      else
        triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
    }
  }    

  /* Print each element in the triangle. Number of spaces
   * is decided by the 'lines' variable */
  printf ("Pascal's Triangle:\n");
  for (i=0; i<num_rows; i++) {
    for (j=0; j<lines; j++)
      printf (" ");
    for (j=0; j<=i; j++)
      printf ("%d ", triangle[i][j]);

    printf ("\n");
    lines--;
  }
}

