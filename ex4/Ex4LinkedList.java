class ListNode {
    public String data;		//String of data
    ListNode next;		//ptr to next node in list
    ListNode prev;		//ptr to prev node in list

    //One field constructor for ListNode
    public ListNode(String s)
    {
	data=s; next=prev=null;
    }

    //Returns string of data in list node
    public String getData()
    {
	return data;
    }
}

public class Ex4LinkedList {

    // Pointers to front and back of the list
    ListNode front=null;
    ListNode back=null;

    public void insertAtEnd(String s)
    {
        if(front == null){
	    // Add data to an empty list
	    front = new ListNode(s);
	    back = front;
	}
	else{
	    // Add data to the back of the list
	    back.next = new ListNode(s);
	    back.next.prev = back;
	    back = back.next;
	}
    }

    public void clearList()
    {
	// Remove reference pointers to list and java
	// compiler's garbage collecter should handle the rest
	front=back=null;
    }

    public int findPositionAndMove(String s, int moveDistance)
    {
	// Pointers to nodes to scan through the list
	// Depth counter starts where first node is node 1
	ListNode tempNode1;
	ListNode tempNode2;
	int depth=1;

	// Scan list for a node with matching string
	// Increment depth as you go deeper into the list
	tempNode1=front;
	while(tempNode1 != null){
	    if(tempNode1.getData().equals(s))
		break;
	    depth++;
	    tempNode1 = tempNode1.next;
	}

	// Check if data exists in list and if you have to move at all
	if(tempNode1 == null)
	    return -1;
	if(moveDistance <= 1 || depth==1)
	    return depth;

	// Use tempNode2 to traverse the list while tempNode1 stays at the
	// Original position. Break if you get to the front of the list before
	// hitting the given moveDistance
	tempNode2 = tempNode1;
	for(int i=0; i<moveDistance; i++){
	    if(tempNode2.prev==null)
		break;
	    tempNode2 = tempNode2.prev;
	}

	if(tempNode1 == back){
	    // If data is found in the back of the list
	    tempNode1.prev.next=null;
	    back = tempNode1.prev;

	    if(tempNode2 == front){
		// If data is being moved to the front of the list
		tempNode2.prev = tempNode1;
		tempNode1.next = tempNode2;
		tempNode1.prev = null;
		front = tempNode1;
		return depth;
	    }
	    // If data is not being moved to the front of the list	
	    tempNode2.prev.next = tempNode1;
	    tempNode1.prev = tempNode2.prev;
	    tempNode1.next = tempNode2;
	    tempNode2.prev = tempNode1;
	    return depth;
	}
	// Points node on either side of moving node at one another
	tempNode1.next.prev = tempNode1.prev;
	tempNode1.prev.next = tempNode1.next;

	if(tempNode2 == front){
	    // If data is being moved to the front of the list but
	    // is currently not at the back of the list
	    tempNode2.prev = tempNode1;
	    tempNode1.next = tempNode2;
	    tempNode1.prev = null;
	    front = tempNode1;
	    return depth;
	}


	// Normal insertion where neither node is that the front or backk
	// Inserts node1  inbetween node2 and node2->prev
	tempNode1.prev = tempNode2.prev;
	tempNode1.next = tempNode2;
	tempNode1.next.prev = tempNode1.prev.next = tempNode1;
	return depth;
    }
}
