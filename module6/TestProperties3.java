import java.util.*;
import java.io.*;

public class TestProperties3 {

    public static void main (String[] argv)
    {
	Properties p = new Properties();
	
	// Read properties from the file "geodata".
	try {
	    FileInputStream f = new FileInputStream ("geodata");
	    p.load (f);
	}
	catch (IOException e)
	    {
		System.out.println ("Cannot open file");
		System.exit(0);
	    }

	System.out.print ("Property Names:\n");
	p.list(System.out);

	// The entire list of property NAMES can be enumerated.
	System.out.print("\nProperty Values:\n");
	Enumeration e = p.propertyNames();
	while (e.hasMoreElements()) {
	    System.out.println(e.nextElement());
	}
	
    } // "main"
    
}
