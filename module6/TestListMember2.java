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
                                // Must implement toString()
	    i++;
	    listPtr = listPtr.next;
	}
    }

    // Membership method.
    public boolean memberOf (Object obj)
    {
	ListItem listPtr = front;
	while (listPtr != null) {
	    if (listPtr.data.equals(obj))
		return true;
	    listPtr = listPtr.next;
	}
	return false;
    }

} // End of class "LinkedList"


// An object to use in the list:

class Person {

    String name;
    String ssn;
    
    // Constructor.
    public Person (String name_in, String ssn_in)
    {
	name = name_in;  ssn = ssn_in;
    }

    // Override toString()
    public String toString ()
    {
	return "Person: name=" + name + ", ssn=" + ssn;
    }


    public boolean equals (Object obj)
    {
        Person p = (Person) obj;
	if( (name.equals(p.name)) && (ssn.equals(p.ssn)))
	    return true;
	return false;
    }

} // End of class "Person"


// Test class.

class TestListMember2 {

    public static void main (String[] argv)
    {
	// Create a new list object.
	LinkedList L = new LinkedList ();
	
	
	L.addData (new Person ("Terminator", "444-43-4343"));
	L.addData (new Person ("Rambo", "555-54-5454"));
	L.addData (new Person ("James Bond", "666-65-6565"));
	L.addData (new Person ("Bruce Lee", "777-76-7676"));
	
	// Print contents.
	L.printList();

	// Test membership.
	Person p = new Person ("Rambo", "555-54-5454");
	if (L.memberOf (p))
	    System.out.println ("Rambo is in the list");
	else
	    System.out.println ("Rambo is not in the list");

	Person p2 = new Person ("PeeWee", "555-54-5454");
	if (L.memberOf (p2))
	    System.out.println ("PeeWee is in the list");
	else
	    System.out.println ("PeeWee is not in the list");
    }

}
