#include <stdio.h>
#include <stdlib.h>

// Define an enum type and give it the name skyColorType:
typedef enum skyColor {blue, orange, gray} skyColorType;

// Define a structure containing a double and a string. Note the
// use of typedef to name the structure.
typedef struct {
  double temperature;
  char *message;
} forecastInfo;

// Define a pointer type to the above structure.
typedef forecastInfo *forecastInfoPtrType;


// The function forecast returns a structure.

forecastInfo forecast (skyColorType c)
{
  // Declare a pointer to the struct.
  forecastInfoPtrType fInfoPtr;

  // Make the pointer point to a block of memory for the struct.
  fInfoPtr = (forecastInfoPtrType) malloc (sizeof (forecastInfo) * 1);

  if (c == blue) {
    fInfoPtr->message = "Sunny and warm!";    // Note the use of the "->" operator
    fInfoPtr->temperature = 85.5;             // because fInfoPtr is a pointer.
  }
  else if (c == orange) {
    fInfoPtr->message = "Enjoy the sunset";
    fInfoPtr->temperature = 77.3;
  }
  else if (c == gray) {
    fInfoPtr->message = "Stay inside";
    fInfoPtr->temperature = 64.7;
  }

  // Lines added to the module
  double * dPtr;
  dPtr = (double*) fInfoPtr;
  printf ("Pointer=%p temp=%lf\n", fInfoPtr, *dPtr); 

  return *fInfoPtr;             // Return the struct itself, not the pointer.
}

int main ()
{
  // Example of struct variable declaration:
  forecastInfo fInfo;

  fInfo = forecast (blue);

  // Note the use of the "." operator to access struct members.
  printf ("Temperature = %lf: %s\n", fInfo.temperature, fInfo.message);
}
