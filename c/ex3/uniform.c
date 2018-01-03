#include <stdio.h>
#include <math.h>
#include <stdlib.h>


static long r_seed = 12345678L;

/* Uniform [0,1] using the standard Lehmer generator. 
   See http://www.seas.gwu.edu/~simhaweb/java/lectures/appendix/random.html
   for more details. */

double uniform ()
{
  static long m = 2147483647;
  static long a = 48271;
  static long q = 44488;
  static long r = 3399;
  long t, lo, hi;

  hi = r_seed / q;
  lo = r_seed - q * hi;
  t = a * lo - r * hi;
  if (t > 0)
    r_seed = t;
  else
    r_seed = t + m;
  return ( (double) r_seed / (double) m );
}



/* Uniform random number generator - returns a number between a and b */ 

double uniform_range (double a, double b)
{
  if (b > a)
    return ( a + (b-a) * uniform() );
  else { 
    printf ("ERROR: in uniform.uniform_range(): a=%lf b=%lf\n",a,b); return 0;
  }
}



/* Uniform random number generator - returns an integral number between a and b */ 

long discrete_uniform (long a, long b)
{
  if (b > a) {
    double x = uniform ();
    long c = ( a + (long) floor((b-a+1)*x) );
    return c;
  }
  else if (a == b) return a;
  else { printf("ERROR in uniform.discrete_uniform(): a=%2ld b=%2ld\n",a,b);return 0;}
}



/* Generate a random int from the given discrete pmf */

int generateFromPmf (double *pmf, int length)
{
  int lastIndex, i;
  double u, sum;

  if ( ( pmf == NULL) || (length <= 1) ){
    printf ("ERROR: in uniform.generateFromPmf(): pmf null or zero-length");
    return 0;
  }
    
  lastIndex = length - 1;
  u = uniform();
  i = 0;
  sum = pmf[0];
  while ( (i < lastIndex) && (u > sum) ) {
    i++;
    sum += pmf[i];
  }
  return i;
}



/* Generate from zipf distribution of given size. */

int zipf (int size) 
{
  double *pmf, sum;
  int index, i;

  pmf = (double*) malloc (sizeof(double)*(size+1));
  sum = 0;
  pmf[0] = 0;
  for (i=1; i<=size; i++) {
    pmf[i] = 1.0 / (double) i;
    sum += pmf[i];
  }
  for (i=1; i<=size; i++) {
    pmf[i] = pmf[i] / sum;
  }
  index = generateFromPmf (pmf, size+1);
  if (index == 0)
    index = 1;
  return index;
}



/* Reverse zipf, has an increasing pmf */

int reverseZipf (int size) 
{
  double *pmf, sum;
  int index, i;

  pmf = (double*) malloc (sizeof(double)*(size+1));
  pmf[0] = 0;
  sum = 0;
  for (i=1; i<=size; i++) {
    pmf[i] = 1.0 / (double) (size-i+1);
    sum += pmf[i];
  }
  for (i=1; i<=size; i++) {
    pmf[i] = pmf[i] / sum;
  }
  index = generateFromPmf (pmf, size+1);
  if (index == 0)
    index = 1;
  return index;
}

