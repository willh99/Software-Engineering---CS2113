#include <stdio.h>
#include <stdlib.h>

// From the previous example:
typedef enum skyColor {blue, orange, gray} skyColorType;

typedef struct {
  double temperature;
  char *message;
  char *city;
} forecastInfo;


// Structure of each list node:
typedef struct ListNode {
  forecastInfo fInfo;
  struct ListNode *last;
  struct ListNode *next;
} ListNodeType;

// A list node pointer type:
typedef ListNodeType *ListNodePtrType;


// The list's front and rear pointers:
ListNodePtrType front = NULL, rear = NULL;


// Add a new node to the rear of the list.

void add (char *city, double temp, char *message)
{
  // Allocate space for the node:
  ListNodePtrType listPtr = (ListNodePtrType) malloc (sizeof (ListNodeType));

  // Fill data.
  listPtr->fInfo.city = city;
  listPtr->fInfo.temperature = temp;
  listPtr->fInfo.message = message;
  listPtr->next = NULL;
  listPtr->last = NULL;

  if (front == NULL) {
    // If list is empty, front and rear point to same node.
    front = rear = listPtr;
    return;
  }

  // Otherwise, we know there's at least one element, so
  // add the new node past the current rear node.
  listPtr->last = rear;
  rear->next = listPtr;
  rear = rear->next;
}


void printListforward ()
{
  ListNodePtrType listPtr;

  printf ("List :\n");

  // Start at the front and walk through the list.
  listPtr = front;
  while (listPtr != NULL) {
    // Process current node.
    printf ("  Forecast for %s: temperature=%lf  %s\n", listPtr->fInfo.city, 
            listPtr->fInfo.temperature, listPtr->fInfo.message);

    // Move on to next node.
    listPtr = listPtr->next;
  }
}


void printListbackward ()
{
  ListNodePtrType listPtr;

  printf ("List :\n");

  // Start at the front and walk through the list.
  listPtr = rear;
  while (listPtr != NULL) {
    // Process current node.
    printf ("  Forecast for %s: temperature=%lf  %s\n", listPtr->fInfo.city, 
            listPtr->fInfo.temperature, listPtr->fInfo.message);

    // Move on to next node.
    listPtr = listPtr->last;
  }
}



int main ()
{
  add ("DC", 85.6, "Hot");
  printListforward ();
  add ("LA", 72.6, "Warm");
  printListforward ();
  add ("NY", 64.6, "Cool");
  printListforward ();
  printListbackward ();
}
