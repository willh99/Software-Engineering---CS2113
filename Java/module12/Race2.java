// File: Race2.java (Module 12)
//
// Author: Rahul Simha
// Created: Nov 17, 1998
// Modified: Nov 10, 2000.
//
// A simple simulation with threads.

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

	// Create a Thread instance for each dog.
	// Note: the class Dog must implement the
	// Runnable interface.
	Thread dThread1 = new Thread (d1);
	Thread dThread2 = new Thread (d2);
	Thread dThread3 = new Thread (d3);

	// Start running the threads.
	// ("start" is a method in Thread).
	dThread1.start();
	dThread2.start();
	dThread3.start();
    }

}


class Dog implements Runnable {

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

    // Must implement this method to implement
    // the Runnable interface.
    public void run ()
    {
	// Compute the finish line distance.
	int finishLine = drawingArea.getSize().width;

	// While not complete...
	while (position < finishLine) {
	    // Sleep
	    try {
		Thread.sleep ((int)UniformRandom.uniform (300,600));
	    }
	    catch (InterruptedException e) {
		System.out.println (e);
	    }
      
	    // Move.
	    move ();
	}
    }

}


public class Race2 {
    public static void main (String[] argv)
    {
	NewFrame nf = new NewFrame ();
    }
}
