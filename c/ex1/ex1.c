#include <stdio.h>
#include <math.h>
#include <sys/times.h>

/* Solves the first bday problem */

double sharedBirthdayProbability (int numPeople, int numTrials)
{
  /* INSERT YOUR CODE HERE */
  double probability = 0;
  int j = 0;
  
  for(j=0; j < numTrials; j++)
  {
    /* Inside this loop is each trail. Loops for # of trials */
    int year[365] = {0};
    int i=0, count=0;

    for(i=0; i < numPeople; i++)
    {
      /* Add a random birthday for each person in numPeople */
      int rand = discrete_uniform (0, 364);
      year[rand]++;
    }

    for(i=0; i < 365; i++)
    {
      /* Scan through list and count # of days with multiple bdays*/
      if(year[i] > 1)
        count++;
    }
    
    /* Add probability of trail to total probability */
    if(count > 0) 
      probability++;
  }

  /* Divided cumulative probability by numTrials for emperical probability*/
  probability = probability/numTrials;
  return probability;
}


/* Solution to second bday problem */

double consecutiveBirthdaysProbability (int numPeople, int numConsecutive, int numTrials)
{
  /* INSERT YOUR CODE HERE */
  double probability = 0;
  int j = 0;
  
  for(j=0; j < numTrials; j++)
  {
    /* Inside this loop is each trail. Loops for # of trials */
    int year[365] = {0};
    int i=0, count=0, condays=0;

    for(i=0; i < numPeople; i++)
    {
      /* Add a random birthday for each person in numPeople */
      int rand = discrete_uniform (0, 364);
      year[rand]++;
    }

    for(i=0; i < 365; i++)
    {
      /* Scan through list looking for 'numConsecutive' consecutive *
       * birthdays. Add one to count if found. */
      if(year[i] > 0)
        condays++;
      else
        condays = 0;

      if(condays >= numConsecutive)
        count++;
    }
    
    /* Add probability of trail to total probability */ 
    if(count > 0)
      probability++;
  }

  /* Divided cumulative probability by numTrials for emperical probability*/
  probability = probability/numTrials;
  return probability;
}
