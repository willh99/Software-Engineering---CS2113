class ListItem {

    int data = 0;
    ListItem next = null;
    
    // Constructor.
    public ListItem (int d)
    {
	data = d;  next = null;
    }
    
    // Accessor.
    public int getData () 
    {
	return data;
    }
}


// Linked list class - also a dynamic class.

class LinkedList {

    ListItem front = null;
    ListItem rear = null;
    int numItems = 0;      // Current number of items.
    
    // Could this class use a constructor?
    
    // Instance method to add a data item.
    public void addData (int d)
    {
	if (front == null) {
	    front = new ListItem (d);
	    rear = front;
	}
	else {
	    rear.next = new ListItem (d);
	    rear = rear.next;
	}
	numItems++;
    }

    public void printList ()
    {
	ListItem listPtr = front;
	System.out.println ("List: (" + numItems + " items)");
	int i = 1;
	while (listPtr != null) {
	    System.out.println ("Item# " + i + ": " + listPtr.getData());
	    i++;
	    listPtr = listPtr.next;
	}
    }

    public boolean memberOf (int d)
    {
        ListItem listPtr = front;
        while (listPtr != null){
            if(listPtr.getData() == d)
                return true;

            listPtr = listPtr.next;
        }
        return false;
    }

} // End of class "LinkedList"


// Test class.

class TestListMember {

    public static void main (String[] argv)
    {
	// Create a new list object.
	LinkedList L = new LinkedList ();
	
	// Let's 10 random numbers between 1 and 100
	java.util.Random rand = new java.util.Random ();
	for (int i=1; i <= 10; i++) {
	    // int k = (int) UniformRandom.uniform (1L,100L);
	    int k = rand.nextInt (100);
	    L.addData (k);
	}
	
	// Print the contents.
	L.printList();
	
	// We will test each number in [1,100]
	for (int i=1; i<=100; i++) 
	    if (L.memberOf (i)) {
		System.out.println ("Value " + i + " is in the list");
	    }
	
    }

}
