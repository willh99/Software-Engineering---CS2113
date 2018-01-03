// File: ex5.java (Module 9b)
//
// Template for Ex 9.5 - drawing the image to fit the frame.

import java.awt.*;
import javax.swing.*;
import java.net.*;

// Extend JPanel to override paintComponent ()
class NewPanel extends JPanel {

    public NewPanel () 
    {
	this.setBackground (Color.cyan);
    }
    
    public void paintComponent (Graphics g)
    {
	super.paintComponent (g);

	Toolkit tk = Toolkit.getDefaultToolkit();
	try{
	    URL u = new URL(" http://www.seas.gwu.edu/~simha/mystery_person.jpg");
	    Image img = tk.getImage(u);
	    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	catch(MalformedURLException e){
	    System.out.println(e);
	}
    } 
    
} // End of NewPanel


class NewFrame extends JFrame {

    // Constructor.
    public NewFrame (int width, int height)
    {
	// Set the title and other frame parameters.
	this.setTitle ("Exercise 9.5");
	this.setResizable (true);
	this.setSize (width, height);
	
	// Create and add the panel.
	NewPanel panel = new NewPanel ();
	this.getContentPane().add (panel);
	
	// Show the frame.
	this.setVisible (true);
    }
    
} // End of class "NewFrame"

public class ex5 {
    
    public static void main (String[] argv)
    {
	NewFrame nf = new NewFrame (300, 250);
    }
    
}
