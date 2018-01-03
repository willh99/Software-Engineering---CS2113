class ListItem {

    Object data = null;    // The universal Object.
    ListItem next = null;

    // Constructor.
    public ListItem (Object obj)
    {
	data = obj;  next = null;
    }
    
    // Accessor.
    public Object getData () 
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
    public void addData (Object obj)
    {
	if (front == null) {
	    front = new ListItem (obj);
	    rear = front;
	}
	else {
	    rear.next = new ListItem (obj);
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
	    System.out.println ("Item# " + i + ": " 
				+ listPtr.getData());
	    i++;
	    listPtr = listPtr.next;
	}
    }


    ListItem enumPtr = null;

    // Start an enumeration.
    public void startEnumeration ()
    {
	enumPtr = front;
    }
    
    public boolean hasMoreElements()
    {
	if (enumPtr == null)
	    return false;
	else
	    return true;
    }
    
    public Object getNextElement()
    {
	Object obj = enumPtr.data;
	enumPtr = enumPtr.next;
	return obj;
    }

} // End of class "LinkedList"



// Test class.

public class ListProblem {

    public static void main (String[] argv)
    {
	// Create a new list object.
	LinkedList L = new LinkedList ();
	
	
	// Add stuff the list was originally designed for:
	L.addData (new Integer(5));
	L.addData (new Integer(6));
	
	// Does this belong?
	L.addData (new String("Blah-blah"));
	
	// An application that fails:
	L.startEnumeration ();
	int sum = 0;
	while ( L.hasMoreElements() ) {
	    Integer I = (Integer) L.getNextElement ();
	    sum = sum + I;
	}
	System.out.println ("sum=" + sum);
	
    }

} // End of class "ListProblem"
