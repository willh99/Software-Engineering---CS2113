import java.util.*;

// Interfaces can extend other interfaces.

interface TwoWayEnumeration extends Enumeration {
    public abstract void setForward ();
    public abstract void setBackward ();
}

interface MinMax extends TwoWayEnumeration {
    public abstract Object minElement();
    public abstract Object maxElement();
}

abstract class ComparableObject {
    public abstract String toString ();
    public abstract int compare (ComparableObject c);
}

class ListItem {

    ComparableObject data = null; 
    ListItem next = null;
    ListItem prev = null;
    
    // Constructor.
    public ListItem (ComparableObject obj, ListItem p, ListItem n)
    {
	data = obj;  next = n;  prev = p;
    }
    
}


// Linked list class - also a dynamic class.

class LinkedList implements TwoWayEnumeration, MinMax {

    ListItem front = null;
    ListItem rear = null;
    int numItems = 0;      // Current number of items.
    
    // Instance method to add a data item.
    public void addData (ComparableObject obj)
    {
	if (front == null) {
	    front = new ListItem (obj, null, null);
	    rear = front;
	}
	else {
	    // Find the right place.
	    ListItem tempPtr = front;
	    boolean found = false;
	    while ( (!found) && (tempPtr != null) ) {
		if (tempPtr.data.compare(obj) > 0) {
		    found = true;
		    break;
		}
		tempPtr = tempPtr.next;
	    }
	    // Now insert.
	    if (!found) { // Insert at rear.
		rear.next = new ListItem (obj, rear, null);
		rear = rear.next;
	    }
	    else if (tempPtr == front) { // Insert in front.
		front.prev = new ListItem (obj, null, front);
		front = front.prev;
	    }
	    else { // Insert in the middle.
		ListItem Lptr = new ListItem (obj, tempPtr.prev, tempPtr);
		tempPtr.prev.next = Lptr;
		tempPtr.prev = Lptr;
	    }
	}
	numItems++;
    }
    
    ListItem enumPtr;
    boolean forward = true;
    Object min, max;

    public void setForward ()
    {
	forward = true;
	enumPtr = front;
    }
    
    public void setBackward ()
    {
	forward = false;
	enumPtr = rear;
    }

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
	if (forward) 
	    enumPtr = enumPtr.next;
	else
	    enumPtr = enumPtr.prev;
	return obj;
    }

    public Enumeration getEnumeration ()
    {
	setForward();
	return this;  
    }

    public TwoWayEnumeration getEnumeration (boolean f)
    {
	forward = f;
	if (forward)
	    setForward();
	else
	    setBackward();
	return this;
    }

    public MinMax getMinMax ()
    {
	return this;
    }

    public Object minElement ()
    {
	Object p;
	p = rear.data;
	return p;
    }

    public Object maxElement ()
    {
	Object p;
	p = front.data;
	return p;
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

public class SortedList12 {

    public static void main (String[] argv)
    {
	// Create a new list object.
	LinkedList L = new LinkedList ();
	
	// Add data.
	L.addData (new Person ("Rogue", "1111-12-1212"));
	L.addData (new Person ("Storm", "222-23-2323"));
	L.addData (new Person ("Black Widow", "333-34-3434"));
	L.addData (new Person ("Jean Grey", "888-89-8989"));
	
	// Print via regular Enumeration
	Enumeration e = L.getEnumeration();
	while (e.hasMoreElements())  {
	    Person p = (Person) e.nextElement();
	    System.out.println (p);
	}

	// Get a 2-way enumeration.
	TwoWayEnumeration e2 = L.getEnumeration (false);
	while (e.hasMoreElements())  {
	    Person p = (Person) e.nextElement();
	    System.out.println (p);
	}

	// Get MinMax instance.
	MinMax m = L.getMinMax();
	Person p = (Person) m.minElement();
	Person p2 = (Person) m.maxElement();
	System.out.println ("Min: " + p);
	System.out.println ("Max: " + p2);
	
  }

} // End of class "SortedList11"

