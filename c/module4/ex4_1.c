#include <stdio.h>
#include "uniform.c"
#define pi 3.141

double computeArea (double radius)
{
  /* Take a double value as the radius and returns area of circle */
  double area;
  area = radius*radius*pi;
  return area;
}

int main ()
{
  /* Obtain a random value for the radius between 0 and 1 */
  double radius=0, area=0, average=0;
  int i=0;

  for(i=0; i < 10000; i++){
    radius = uniform();

    /* Calculate area for given radius */
    area = computeArea(radius);
    average = average+area;
  }
  average = average/10000;
  printf ("Average area of a circle is %lf\n",  average);
  return 0;
}
