import java.util.*;

abstract class ComparableObject {
    public abstract String toString ();
    public abstract int compare (ComparableObject c);
}

class ListItem {

    ComparableObject data = null; 
    ListItem next = null;
    
    // Constructor.
    public ListItem (ComparableObject obj)
    {
	data = obj;  next = null;
    }
    
    // Accessor.
    public ComparableObject getData () 
    {
	return data;
    }
}


// Linked list class - also a dynamic class.

class LinkedList implements Enumeration {
    
    ListItem front = null;
    ListItem rear = null;
    int numItems = 0;      // Current number of items.
    
    // Instance method to add a data item.
    public void addData (ComparableObject obj)
    {
	if (front == null) {
	    front = new ListItem (obj);
	    rear = front;
	}
	else {
	    // Find the right place.
	    ListItem tempPtr=front, prevPtr=front;
	    boolean found = false;
	    while ( (!found) && (tempPtr != null) ) {
		if (tempPtr.data.compare(obj) > 0) {
		    found = true;
		    break;
		}
		prevPtr = tempPtr;
		tempPtr = tempPtr.next;
	    }
	    // Now insert.
	    if (!found) { // Insert at rear.
		rear.next = new ListItem (obj);
		rear = rear.next;
	    }
	    else if (tempPtr == front) { // Insert in front.
		ListItem Lptr = new ListItem (obj);
		Lptr.next = front;
		front = Lptr;
	    }
	    else { // Insert in the middle.
		ListItem Lptr = new ListItem (obj);
		prevPtr.next = Lptr;
		Lptr.next = tempPtr;
	    }
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

    ListItem enumPtr;
    
    public boolean hasMoreElements ()
    {
	if (enumPtr == null)
	    return false;
	else 
	    return true;
    }
    
    public Object nextElement() 
    {
	Object obj = enumPtr.data;
	enumPtr = enumPtr.next;
	return obj;
    }

    public Enumeration getEnumeration ()
    {
	enumPtr = front;
	return this;      // Using the "this" reserved word.
    }
    
    // A copy function.
    public LinkedList copy () 
    {
	LinkedList L = new LinkedList ();
	L.front = front;
	L.rear = rear;
	L.numItems = numItems;
	return L;
    }

} // End of class "LinkedList"


// An object to use in the list:

class Person extends ComparableObject {
    
    String name;
    String ssn;
    
    // Constructor.
    public Person (String nameInit, String ssnInit)
    {
	name = nameInit;  ssn = ssnInit;
    }
    
    // Override toString()
    public String toString ()
    {
	return "Person: name=" + name + ", ssn=" + ssn;
    }
    
    // Must implement compare
    public int compare (ComparableObject obj)
    {
	Person p = (Person) obj;
	return name.compareTo (p.name);
    }
    
} // End of class "Person"


// Test class.

public class SortedList6 {

    public static void main (String[] argv)
    {
	// Create a new list object.
	LinkedList L = new LinkedList ();
	
	// Insert data.
	L.addData (new Person ("Rogue", "1111-12-1212"));
	L.addData (new Person ("Storm", "222-23-2323"));
	L.addData (new Person ("Black Widow", "333-34-3434"));
	L.addData (new Person ("Jean Grey", "888-89-8989"));
	
	LinkedList L2 = L.copy();
	
	L2.addData (new Person ("Xena", "888-87-8787"));
	
	// What gets printed out?
	L.printList();
    }
    
} // End of class "SortedList6"
