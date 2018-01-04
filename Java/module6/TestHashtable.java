import java.util.*;

public class TestHashtable {

    public static void main (String[] argv)
    {
	Hashtable h = new Hashtable ();
	
	// Insert a string and a key.
	h.put ("Ali", "Anorexic Ali");
	h.put ("Bill", "Bulimic Bill");
	h.put ("Chen", "Cadaverous Chen");
	h.put ("Dave", "Dyspeptic Dave");
	
	// To retrieve, pass a key:
	String d = (String) h.get ("Dave");
	System.out.println (d);  // Prints "Dyspeptic Dave"
    }
    
}
