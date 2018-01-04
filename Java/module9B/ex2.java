// File: ex2.java (Module 9b)
//
// Template for Ex. 9.2 (Draw a chessboard).

import java.awt.*;
import javax.swing.*;

// Create a JPanel subclass to override paintComponent()

class NewPanel extends JPanel {

    public NewPanel ()
    {
	this.setBackground (Color.cyan);
    }
    
    // PUT YOUR CODE HERE
    public void paintComponent(Graphics g){
   	super.paintComponent(g);

	int size = ( this.getHeight()/8 < this.getWidth()/8) ? (this.getHeight()/8):(this.getWidth()/8);
	g.drawRect(0,0,size*8, size*8);
	for(int i=0; i<(size*8); i=i+size){
	    for(int j=0; j<(size*8); j=j+size){
		if((i/size)%2 == (j/size)%2)
		    g.drawRect(j,i,size,size);
		else
		    g.fillRect(j,i,size,size);

	    }
	}
   }
}


class NewFrame extends JFrame {
    
    // Constructors.
    public NewFrame (int width, int height)
    {
	// Set the title and other parameters.
	this.setTitle ("Chessboard");
	this.setResizable (true);
	this.setSize (width, height);
	
	// Create panel and add it.
	this.getContentPane().add (new NewPanel());
	
	// Show the frame.
	this.setVisible (true);
    }
    
    // No-parameter constructor - use a default size.
    public NewFrame ()
    {
	this (500, 300);
    }
    
}

public class ex2 {

    public static void main (String[] argv)
    {
	NewFrame f = new NewFrame (500, 300);
    }
    
}
