// File: ex1.java (Module 9b)
//
// Template for Ex 9.1 (Obtain the screen resolution etc).

import java.awt.*;
import javax.swing.*;

public class ex1 {

    public static void main (String[] argv)
    {
	// Get a Toolkit instance.
	Toolkit tk = Toolkit.getDefaultToolkit ();
	
	// Get resolution in pixels per inch.
	int size = tk.getScreenResolution ();
	
	// Screen dimensions can also be obtained in a Dimension instance.
	Dimension screenSize = tk.getScreenSize ();
	// 3 ins = 225 pixels
	// 2 ins = 150 pixels
	// Print out results.
	System.out.println ("Resolution: " + size);
	System.out.println ("Height=" + screenSize.height);
	System.out.println("Width=" + screenSize.width);
	
	// Add your stuff here:
	JFrame f = new JFrame();

	f.setSize(size*3, size*2);
	f.setLocation(screenSize.width/2, screenSize.height/2);
	f.setVisible(true);
    }

}
