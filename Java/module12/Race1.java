// File: Race1.java (Module 12)
//
// Author: Rahul Simha
// Created: Nov 17, 1998.
// Modified: Nov 10, 2000.
//
// A simple simulation without threads.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NewFrame extends JFrame {

    // For drawing the race results.
    JPanel drawingArea;


    public NewFrame ()
    {
	// Frame properties.
	this.setTitle ("Dog Race");
	this.setResizable (true);
	this.setSize (600,200);

	Container cPane = this.getContentPane();
	// cPane.setLayout (new BorderLayout());

	// Quit button.
	JPanel p = new JPanel ();
	JButton quitb = new JButton ("QUIT");
	quitb.addActionListener (
            new ActionListener () {
	        public void actionPerformed (ActionEvent a)
		{
		    System.exit (0);
		}
	    }
        );
	p.add (quitb);

	// Pressing "start" calls race()
	JButton startb = new JButton ("START");
	startb.addActionListener (
            new ActionListener () {
	        public void actionPerformed (ActionEvent a)
		{
		    race ();
		}
	    }
	);
	p.add (startb);

	// Now add the panel to the frame.
	cPane.add (p, BorderLayout.SOUTH);

	// A JPanel to draw the results.
	drawingArea = new JPanel();
	drawingArea.setBackground (Color.white);
	cPane.add (drawingArea, BorderLayout.CENTER);

	this.setVisible (true);
    }


    void race ()
    {
	Dimension D = drawingArea.getSize ();

	// Finish-line is at the right end of the canvas.
	int finishLine = D.width;

	// Create two dog instances with different ID's.
	Dog d1 = new Dog (1, drawingArea);
	Dog d2 = new Dog (2, drawingArea);
	Dog d3 = new Dog (3, drawingArea);

	// Each dog sleeps a random amount of time.
	int d1Nextwakeup = (int) UniformRandom.uniform (300,600);
	int d2Nextwakeup = (int) UniformRandom.uniform (300,600);
	int d3Nextwakeup = (int) UniformRandom.uniform (300,600);

	// Keep track of the current time.
	int currentTime = 0;

	// Stop when one dog crosses the finish line.

	while ( (d1.position < finishLine) && 
		(d2.position < finishLine) &&
		(d3.position < finishLine) ) 
	{

	    // See which one is done first.
	    if ( (d1Nextwakeup < d2Nextwakeup) &&
		 (d1Nextwakeup < d3Nextwakeup) ) 
	    {
		try {
		    // Static method "sleep" in class Thread.
		    Thread.sleep (d1Nextwakeup - currentTime);
		}
		catch (InterruptedException e) {
		    System.out.println (e);
		}
		currentTime = d1Nextwakeup;
		d1.move(); 	// Move a random distance.
		d1Nextwakeup += (int) UniformRandom.uniform (300,600);
	    }
	    else if( (d2Nextwakeup < d1Nextwakeup) &&
		     (d2Nextwakeup < d3Nextwakeup) )
	    {
		try {
		    Thread.sleep (d2Nextwakeup - currentTime);
		}
		catch (InterruptedException e) {
		    System.out.println (e);
		}
		currentTime = d2Nextwakeup;
		d2.move();      // Move a random distance.
		d2Nextwakeup += (int) UniformRandom.uniform (300,600);
	    }
	    else {
		try {
		    Thread.sleep (d3Nextwakeup - currentTime);
		}
		catch (InterruptedException e) {
		    System.out.println (e);
		}
		currentTime = d3Nextwakeup;
		d3.move();      // Move a random distance.
		d3Nextwakeup += (int) UniformRandom.uniform (300,600);

	    }
	}
    }

}


class Dog {

    public int position = 20;  // Starting position.
    int ID;                    // An ID.
    JPanel drawingArea;        // The panel on which to draw.

    public Dog (int ID, JPanel drawingArea)
    {
	this.ID = ID;
	this.drawingArea = drawingArea;

	// Draw ID on panel.
	Graphics g = drawingArea.getGraphics ();
	g.drawString (""+ID, 5, 20*ID+8);
    }

    public void move ()
    {
	// Move a random amount.
	int newPosition = position + (int) UniformRandom.uniform (50,100);

	// Draw new position.
	Graphics g = drawingArea.getGraphics ();
	int size = newPosition - position;
	g.fillRect (position, 20*ID, size, 10);
	position = newPosition;
    }
}

public class Race1 {
    public static void main (String[] argv)
    {
	NewFrame nf = new NewFrame ();
    }
}
