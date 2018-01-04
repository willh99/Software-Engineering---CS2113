import java.util.*;


// An object to use in the stack:

class Person {

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
    
} // End of class "Person"


public class TestStack {

    public static void main (String[] argv)
    {
	// Create a new stack object.
	Stack S = new Stack ();
	
	// Create Person instances and add it to the Stack.
	S.push (new Person ("Rogue", "1111-12-1212"));
	S.push (new Person ("Storm", "222-23-2323"));
	S.push (new Person ("Black Widow", "333-34-3434"));
	S.push (new Person ("Jean Grey", "888-89-8989"));
	
	// Pop the top of the stack. Note the cast needed.
	Person p = (Person) S.pop();
	System.out.println (p);
	
	// Look at the top without popping:
	p = (Person) S.peek();
	System.out.println (p);
	
    }

} // End of class "TestStack"
