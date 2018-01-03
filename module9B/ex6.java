// File: ex6.java (Module 9b)
//
// Template for Ex. 9.6 - getting image sizes.

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
    	URL u1 = new URL("http://www2.seas.gwu.edu/~simhaweb/java/person1.jpg");
	URL u2 = new URL("http://www2.seas.gwu.edu/~simhaweb/java/person2.jpg");
	URL u3 = new URL("http://www2.seas.gwu.edu/~simhaweb/java/person3.jpg");
	URL u4 = new URL("http://www2.seas.gwu.edu/~simhaweb/java/person4.jpg");
    	
	Image p1 = tk.getImage(u1); 
     	Image p2 = tk.getImage(u2);
	Image p3 = tk.getImage(u3);
	Image p4 = tk.getImage(u4);
	
	MediaTracker mt = new MediaTracker(this);
	int halfH = (int) this.getHeight()/2;
	int halfW = (int) this.getWidth()/2;

	mt.addImage(p1, 1);
	mt.addImage(p2, 2);
	mt.addImage(p3, 3);
	mt.addImage(p4, 4);
	try{
	    mt.waitForID(1);
	    mt.waitForID(2);
	    mt.waitForID(3);
	    mt.waitForID(4);
	}
	catch(InterruptedException e){
	    System.out.println(e);
	}
	
	g.drawImage(p1, 0, 0, halfW, halfH, this);
	g.drawImage(p2, halfW, 0, halfW, halfH, this);
	g.drawImage(p3, 0, halfH, halfW, halfH, this);
	g.drawImage(p4, halfW, halfH, halfW,halfH, this);
    }
    catch (MalformedURLException e) {
	System.out.println (e);
    }
  }

     
  
} // End of NewPanel

class NewFrame extends JFrame {

  // Constructor.
  public NewFrame (int width, int height)
  {
    // Set the title and other frame parameters.
    this.setTitle ("Exercise 9.6");
    this.setResizable (true);
    this.setSize (width, height);

    // Create and add the panel.
    NewPanel panel = new NewPanel ();
    this.getContentPane().add (panel);

    // Show the frame.
    this.setVisible (true);
  }


} // End of class "NewFrame"

public class ex6 {

  public static void main (String[] argv)
  {
    NewFrame nf = new NewFrame (300, 200);
  }

}
