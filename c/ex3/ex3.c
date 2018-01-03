#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>

/* Linked list node structure. Each node holds a data integer and
 * and pointers to the nodes on either side of it */
typedef struct ListNode{
  int data;
  struct ListNode *next;
  struct ListNode *prev;
}ListNode;

/* Pointers to front and back of the list */
struct ListNode *front=NULL, *back=NULL;

/* Initially list is empty */
void initializeList()
{
  if(front != NULL){
    /* Check if there is already an existing list */
    printf("List already exists\n");
    return;
  }

  /* Allocate memory for a node. Make sure it doesn't point to anything.
   * Set back and front equal to this new node */
  ListNode *NewNode = (ListNode*) malloc (sizeof(ListNode));
  NewNode->next = NewNode->prev = NULL;
  front = back = NewNode;
}

/* Need to free memory */
void clearList()
{
  if(front == NULL){
    /* Check if list has been initialized */
    printf("List does not exist\n");
    return;
  }

  /* loop until you reach the last node in the list. While looping,
   * free the node you just came from. */
  while(front->next != NULL)
  {
    front = front->next;
    free(front->prev);
  }

  /* Free the last node and set back and front to NULL so a new list
   * can be made. */
  free(front);
  front=back=NULL;
}

/* Insert an item at the end of a list */
void insertAtEnd(int data)
{
  if(front == NULL){
    /* Check if the list has been initialized */
    printf("List does not exist\n");
    return;
  }

  /* Allocate memory for a new node and assign it data */
  struct ListNode *NewNode = (ListNode*) malloc (sizeof(ListNode));
  NewNode->data = data;
  NewNode->prev = NewNode->next = NULL;

  if(back == front && (!front->data)){
    /* Special case when insterting the first node in the list */
    front = back = NewNode;
    return;
  }

  /* Have the back node point to the new node and the new node point to back,
   * then set the new node as the back node */
  back->next = NewNode;
  NewNode->prev = back;
  back = NewNode;
}

/* Find "data" in the list, if it exists, and move it up by a
 * distance of "moveDistance" closer to the front of the list. */
int findPositionAndMove(int data, int moveDistance)
{
  /* Set up temporary nodes to search through list */
  ListNode *tempNode1;
  ListNode *tempNode2;

  /* depth starts counting at 1*/
  int depth=1, i=0;
  tempNode1 = front;

  /* Search list for a node with the value of data. Count up the depth
   * as you traverse through the list */
  while(tempNode1 != NULL){
    if(tempNode1->data == data)
      break;

    depth++;
    tempNode1 = tempNode1->next;
  }

  /* Check if data was found and if you have to move at all */
  if(tempNode1 == NULL)
    return -1;
  if(moveDistance <= 1 || depth == 1)
    return depth;
  else if(moveDistance >= depth || tempNode1->prev == front){
    /* Move to the front of the list */
    tempNode1->next->prev = tempNode1->prev;
    tempNode1->prev->next = tempNode1->next;
    tempNode1->prev = NULL;
    tempNode1->next = front;
    front->prev = tempNode1;
    front = tempNode1;
    return depth;  
  }
  else if(moveDistance == 1){
    /* Swap with node just in front of tempNode1 */
    tempNode2 = tempNode1->prev;
    if(tempNode1 == back){
      /* Special case where data is found at back of the list */
      tempNode2->prev->next = tempNode1;
      tempNode1->prev = tempNode2->prev;
      tempNode1->next = tempNode2;
      tempNode2->prev = tempNode1;
      tempNode2->next = NULL;
      back = tempNode2;
      return depth;
    }
    tempNode1->next->prev = tempNode2;
    tempNode2->prev->next = tempNode1;
    tempNode1->prev = tempNode2->prev;
    tempNode2->next = tempNode1->next;
    tempNode1->next = tempNode2;
    tempNode2->prev = tempNode1;
    return depth; 
  }

  /* Use tempNode2 to traverse the list while tempNode1 stays at the 
   * original position. Break if you get to the front of the list before
   * hitting the given moveDistance. */
  tempNode2 = tempNode1;
  for(i=0; i<moveDistance; i++)
  {
    if(tempNode2->prev == NULL)
      break;

    tempNode2 = tempNode2->prev;
  }

  if(tempNode1==back){
    /* If data is found in back of the list */
    tempNode1->prev->next = NULL;
    back = tempNode1->prev;

    if(tempNode2==front){
      /* If data is being moved to front of list */
      tempNode2->prev = tempNode1;
      tempNode1->next = tempNode2;
      tempNode1->prev = NULL;
      front = tempNode1;
      return depth;
    }

    tempNode2->prev->next = tempNode1;
    tempNode1->prev = tempNode2->prev;
    tempNode1->next = tempNode2;
    tempNode2->prev = tempNode1;
    return depth;
  }

  /* Point nodes on either side of moving mode at one another */
  tempNode1->next->prev = tempNode1->prev;
  tempNode1->prev->next = tempNode1->next;

  /* Insert node inbetween nodes */
  tempNode1->prev = tempNode2->prev;
  tempNode1->next = tempNode2;
  tempNode1->next->prev = tempNode1->prev->next = tempNode1;
  return depth;
}
