#include <stdio.h>

int main () {

  //Constant with "F" appended:
  float PI =3.141;

  //Constant in exponent format:
  double doublePI = 314.159265E-2;

  //Long double constant:
  long double ldoublePI = 3.14156265358979L;

  //Output in exponent format:
  printf ("float PI = %7.5d\n", PI);

  //Output in decimal format with field width and number of significant digits:
  printf ("double PI = %16.10f\n", doublePI);

  //Long double's need to be printed as double's
  printf ("long double PI = %15.12lf\n", (double) ldoublePI);
}
