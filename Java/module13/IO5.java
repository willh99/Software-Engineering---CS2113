// File: IO5.java
//
// Author: Rahul Simha
// Created: Nov 18, 1998
// Modified: Nov 13, 2000.
//
// Sequential Producer-consumer using pipes.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


class Producer extends JFrame implements Runnable {

    JLabel L;     
    OutputStream outStream;  // For the output.

    public Producer (OutputStream outStream)
    {
	// Store the reference to the buffer.
	this.outStream = outStream;

	// Create the frame.
	this.setSize (700,100);
	this.setLocation (0,100);
	this.setTitle ("Producer");

	Container cPane = this.getContentPane();
	// cPane.setLayout (new BorderLayout());

	// This is where we will write to.
	L = new JLabel ("");
	cPane.add (L, BorderLayout.CENTER);

	this.setVisible (true);
    }


    // Must implement the run() method.

    public void run ()
    {
	// Write 25 random bytes to the buffer.

	for (int i=1; i<=25; i++) {
	    // Create a random byte
	    byte k = (byte) UniformRandom.uniform (1, 100);

	    // Write to label first.
	    L.setText (L.getText() + " " + k);

	    // Write it to the screen.
	    System.out.println ("Producer: writing " + k);

	    // Write integer to buffer.
	    try {
		outStream.write (k);
	    }
	    catch (IOException e) { System.out.println (e); }

	    // Sleep for a while.
	    try {
		Thread.sleep ((int)UniformRandom.uniform(100,1000));
	    }
	    catch (InterruptedException e) { System.out.println (e); }
	}

	try {
	    outStream.write ((byte)-1);
	}
	catch (IOException e) { System.out.println (e); }

	L.setText (L.getText() + " Done!");
    }

}


class Consumer extends JFrame implements Runnable {

    JLabel L;     
    InputStream inStream;  // To get data from.

    public Consumer (InputStream inStream)
    {
	this.inStream = inStream;

	this.setSize (700,100);
	this.setLocation (0, 200);
	this.setTitle ("Consumer");

	Container cPane = this.getContentPane();
	// cPane.setLayout (new BorderLayout());

	L = new JLabel ("");
	cPane.add (L, BorderLayout.CENTER);

	this.setVisible (true);
    }


    public void run ()
    {
	// Read byte values until EOF.

	while (true) {
	    // Get the next byte
	    int i = -1;
	    try {
		i = inStream.read ();
	    }
	    catch (IOException e) { System.out.println (e); }

	    byte k = (byte) i;

	    // Check if end-of-data.
	    if (k < 0) break;

	    System.out.println ("Consumer: just read " + k);

	    // Write it on the frame.
	    L.setText (L.getText() + " " + k);

	    // Sleep for a while.
	    try {
		Thread.sleep ((int)UniformRandom.uniform(500,1000));
	    }
	    catch (InterruptedException e) { System.out.println (e); }
	}

	L.setText (L.getText() + " Done!");
    }

}


// This is an independent quit button to quit the application.

class QuitButton extends JFrame {

    public QuitButton ()
    {
	this.setSize (80,50);
	this.setLocation (0, 0);
	this.setTitle ("Quit button");

	Container cPane = this.getContentPane();
	// cPane.setLayout (new BorderLayout());

	JButton quitb = new JButton ("QUIT");
	quitb.setBackground (Color.red);
	quitb.addActionListener (
	    new ActionListener () {
		public void actionPerformed (ActionEvent a)
		{
		    System.exit (0);
		}
	    }
	);
	cPane.add (quitb, BorderLayout.CENTER);
	this.setVisible (true);
    }

}


public class IO5 {

    public static void main (String[] argv)
    {
	// Create an independent quit button.
	QuitButton q = new QuitButton ();

	try {
	    // Set up an output pipe.
	    PipedOutputStream pipeOut = new PipedOutputStream ();

	    // Set up an input pipe.
	    PipedInputStream pipeIn = new PipedInputStream (pipeOut);

	    // Create a producer instance and run it.
	    Producer p = new Producer (pipeOut);
	    Thread pThread = new Thread(p);
      
	    // Create a consumer instance and run it.
	    Consumer c = new Consumer (pipeIn);
	    Thread cThread = new Thread(c);

	    pThread.start();
	    cThread.start();
	}
	catch (IOException e) { System.out.println (e); }
    }

}

