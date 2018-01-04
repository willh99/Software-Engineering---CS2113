
public class TestSleep {

    public static void main (String[] argv)
    {
	int n = 5;

	// Without any pausing/sleep:
	for (int i=0; i<n; i++) {
	    System.out.println (i);
	}

	// With some sleep/pausing
	for (int i=0; i<n; i++) {
	    try {
		Thread.sleep (500);
	    }
	    catch (Exception e) {
	    }
	    System.out.println (i);
	}

	// Slower still
	for (int i=0; i<n; i++) {
	    try {
		Thread.sleep (2000);
	    }
	    catch (Exception e) {
	    }
	    System.out.println (i);
	}

    }

}
