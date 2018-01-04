import java.util.*;

public class TestStrangeDataStructure {

    public static void main (String[] argv)
    {
	StrangeDataStructureImpl s = new StrangeDataStructureImpl ();
        test (s);
    }	


    // Note the parameter: an interface.

    static void test (StrangeDataStructure strange)
    {
        // Add stuff to the data structure.
	for (int i=-10; i<=20; i++) {
	    strange.addElement (i);
        }

        // Print sum of elements added so far.
	System.out.println ("Sum: " + strange.getSumOfElements());

        // Enumerate all the odd integers added so far.
	Enumeration e = strange.oddElements();
	System.out.print ("Odd elements: ");
	while (e.hasMoreElements()) {
	    System.out.print (" " + e.nextElement());
	}
        System.out.println ();

        // Enumerate all the evenintegers added so far.
	System.out.print ("Even elements: ");
	e = strange.evenElements ();
	while (e.hasMoreElements()) {
	    System.out.print (" " + e.nextElement());
	}
        System.out.println ();
    }

}
