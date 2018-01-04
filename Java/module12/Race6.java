// File: Race6.java (Module 12)
//
// Author: Rahul Simha
// Created: Nov 17, 1998
// Modified: Nov 10, 2000.
//
// Using a shared static variable.

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

	// Create a Thread instance for each dog.
	// Note: the class Dog must implement the
	// Runnable interface.
	Thread dThread1 = new Thread (d1);
	Thread dThread2 = new Thread (d2);

	Dog.startRace();

	// Start running the threads.
	// ("start" is a method in Thread).
	dThread1.start();
	dThread2.start();
    }


}


class Dog implements Runnable {

    public int position = 20;  // Starting position.
    int ID;                    // An ID.
    JPanel drawingArea;        // The panel on which to draw.

    static boolean raceOver;

    public Dog (int ID, JPanel drawingArea)
    {
	this.ID = ID;
	this.drawingArea = drawingArea;

	// Draw ID on canvas.
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

    public void run ()
    {
	// Compute the finish line distance.
	int finishLine = drawingArea.getSize().width;

	// While not complete...
	while (position < finishLine) {
	    try {
		Thread.sleep ((int)UniformRandom.uniform (300,600));
	    }
	    catch (InterruptedException e) {
		System.out.println (e);
	    }

	    // Check whether race is over.
	    if (raceFinished (false, ID)) break;

	    // Move if race is still on.
	    move ();
	}
	if (position >= finishLine)
	    raceFinished (true, ID);
    }

    public static synchronized void startRace ()
    {
	raceOver = false;
    }

    // The same method is called to check whether the race
    // is complete or to indicate that it is complete.

    public synchronized boolean raceFinished (boolean set, int ID)
    {
	boolean returnVal = raceOver; 

	System.out.println ("finished: startofcode: ID=" + ID + ", set=" + set);
	if (!set) 
	    returnVal = raceOver;
	else {
	    // Race overseer sleeps for a while.
	    try {
		Thread.sleep ((int)UniformRandom.uniform (1000,2000));
	    }
	    catch (InterruptedException e) {
		System.out.println (e);
	    }
	    raceOver = true;
	    returnVal = true;
	}

	System.out.println ("finished: endofcode: ID=" + ID + ", set=" + set);
	return returnVal;
    }

}


public class Race6 {
    public static void main (String[] argv)
    {
	NewFrame nf = new NewFrame ();
    }
}
