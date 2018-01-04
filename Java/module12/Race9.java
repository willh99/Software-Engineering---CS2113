// File: Race9.java (Module 12)
//
// Author: Rahul Simha
// Created: Nov 17, 1998
// Modified: Nov 10, 2000.
//
// Waiting and notification.

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

	// Create the dog monitor.
	DogMonitor monitor = new DogMonitor (2);

	// Create two dog instances with different ID's.
	Dog d1 = new Dog (1, drawingArea, monitor, 2);
	Dog d2 = new Dog (2, drawingArea, monitor, 1);

	// Create a Thread instance for each dog.
	// Note: the class Dog must implement the
	// Runnable interface.
	Thread dThread1 = new Thread (d1);
	Thread dThread2 = new Thread (d2);

	// Start running the threads.
	// ("start" is a method in Thread).
	dThread1.start();
	dThread2.start();
    }


}


// A monitor class with synchronized access.

class DogMonitor {

    // The position of each dog.
    int[] position;

    public DogMonitor (int numDogs)
    {
	position = new int [numDogs+1];
	for (int i=1; i<=numDogs; i++)
	    position[i] = 20;
    }

    // Set the new position of Dog# ID.
    public synchronized void setPosition (int ID, int newPosition)
    {
	position[ID] = newPosition;
    }

    // Get the current position of Dog# ID.
    public synchronized int getPosition (int ID)
    {
	return position[ID];
    }

    // A waiting dog needs to call a synchronized
    // wait method inside the monitor.
    public synchronized void synchWait ()
    {
	try {
	    // wait() is inherited from Object.
	    wait ();
	}
	catch (InterruptedException e)
	    {
		System.out.println (e);
	    }
    }

    // Tell the other dog that it can continue.
    public synchronized void synchNotify ()
    {
	// notify() is inherited from Object.
	notify ();
    }
}


class Dog implements Runnable {

    int ID;                    // An ID.
    JPanel drawingArea;        // The panel on which to draw.

    DogMonitor monitor;        // Store a reference to the monitor.
    int OtherID;               // ID of the other dog.

    public Dog (int ID, JPanel drawingArea, DogMonitor monitor, int OtherID)
    {
	this.ID = ID;
	this.drawingArea = drawingArea;
	this.monitor = monitor;
	this.OtherID = OtherID;

	// Draw ID on canvas.
	Graphics g = drawingArea.getGraphics ();
	g.drawString (""+ID, 5, 20*ID+8);
    }

    public void move ()
    {
	// Move a random amount.
	int position = monitor.getPosition (ID);
	int newPosition = position + (int) UniformRandom.uniform (1,50);
	monitor.setPosition (ID, newPosition);

	// Draw new position.
	Graphics g = drawingArea.getGraphics ();
	int size = newPosition - position;
	g.fillRect (position, 20*ID, size, 10);
    }

    public void run ()
    {
	// Compute the finish line distance.
	int finishLine = drawingArea.getSize().width;

	// While not complete...
	while (monitor.getPosition (ID) < finishLine) {
	    try {
		Thread.sleep ((int)UniformRandom.uniform (300,600));
	    }
	    catch (InterruptedException e) {
		System.out.println (e);
	    }
      
	    move ();
      
	    // Check if you need to wait.
	    int other = monitor.getPosition (OtherID);
	    int mine = monitor.getPosition (ID);
	    if (mine - other > 20) {
		System.out.println ("ID=" + ID + " waiting: mine=" + mine
				    + ", other=" + other);
		monitor.synchWait ();
		System.out.println ("ID=" + ID + " continuing");
	    }
      
	    // See if the other guy is waiting for me.
	    if (other - mine <= 20) {
		System.out.println ("ID=" + ID + " notifying: mine=" + mine
				    + ", other=" + other);
		monitor.synchNotify();
	    }
	} // endwhile

    }

}


public class Race9 {
    public static void main (String[] argv)
    {
	NewFrame nf = new NewFrame ();
    }
}
