#include <stdio.h>
#include <math.h>
#include <string.h>

#define boolean int
#define FALSE 0
#define TRUE 1

extern void initializeList ();
extern void clearList ();
extern void insertAtEnd (int);
extern int findPositionAndMove (int, int);
extern long discrete_uniform (long, long);
extern int reverseZipf (int);


void addElements (int n)
{
  int i;

  for (i=1; i<=n; i++)
    insertAtEnd (i);
}

void test (char *testName, int numTrials, int listSize, int moveDistance, boolean isUniform)
{
  double totalDist, avgDist;
  int n, data, position;

  /* First, make the list. */
  initializeList ();
  addElements (listSize);

  /* Now, repeat over a number of trials and estimate average. */
  totalDist = 0;
  for (n=0; n<numTrials; n++) {

    /* Pick a random element in the list to access: */
    data = 0;
    if (isUniform == TRUE) {
      data = discrete_uniform (1, listSize);
    }
    else {
      data = reverseZipf (listSize);
    }

    /* Get its position in the list: */
    position = findPositionAndMove (data, moveDistance);

    /* Accumulate in sum: */
    totalDist += position;
  }

  clearList ();

  avgDist = totalDist / numTrials;

  printf ("%s\n", testName);
  printf ("  Listsize=%d numTrials=%d moveDistance=%d  avgSearchDistance=%lf\n", listSize, numTrials,
          moveDistance, avgDist);

}


int main (int argc, char **argv) 
{
  // Simple tests.
  test ("Test 1: 1 element", 1, 1, 0, TRUE);
  test ("Test 2: 5 elements", 1, 5, 0, TRUE);
  test ("Test 3: 5 elements", 1, 5, 5, TRUE);

  // Statistics.
  test ("Test 4: 10 elements", 10000, 10, 0, TRUE);
  test ("Test 5: 10 elements, moveDist=1", 10000, 10, 1, TRUE);
  test ("Test 6: 10 elements, moveDist=10", 10000, 10, 10, TRUE);
  test ("Test 7: 10 elements, zipf", 10000, 10, 0, FALSE);
  test ("Test 8: 10 elements, zipf, moveDist=1", 10000, 10, 1, FALSE);
  test ("Test 9: 10 elements, zipf, moveDist=10", 10000, 10, 10, FALSE);
}
