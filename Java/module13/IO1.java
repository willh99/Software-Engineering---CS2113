// File: IO1.java (Module 13)
//
// Author: Rahul Simha
// Created: Nov 18, 1998.
// Modified: Nov 13, 2000.
//
// Sequential Producer-consumer.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class Producer extends JFrame {

    JLabel L;     // A place to write stuff.
    Buffer buf;   // Reference to the buffer.

    public Producer (Buffer buf)
    {
	// Store the reference to the buffer.
	this.buf = buf;

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
	    buf.writeByte (k);

	    // Sleep for a while.
	    try {
		Thread.sleep ((int)UniformRandom.uniform(100,1000));
	    }
	    catch (InterruptedException e) {
		System.out.println (e);
	    }
	}
	buf.writeByte ((byte)-1);
	L.setText (L.getText() + " Done!");
    }

}


class Consumer extends JFrame {

    JLabel L;     // Data similar to Producer.
    Buffer buf;

    public Consumer (Buffer buf)
    {
	this.buf = buf;

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
	    byte k = buf.readByte ();

	    // Check if end-of-data.
	    if (k < 0) break;

	    System.out.println ("Consumer: just read " + k);

	    // Write it on the frame.
	    L.setText (L.getText() + " " + k);

	    // Sleep for a while.
	    try {
		Thread.sleep ((int)UniformRandom.uniform(500,1000));
	    }
	    catch (InterruptedException e) {
		System.out.println (e);
	    }
	}
	L.setText (L.getText() + " Done!");
    }

}


// A buffer written as a monitor.

class Buffer {

    byte[] buf;                      // The buffer.
    int writeCursor = 0;            // Current write position.
    int readCursor = 0;             // Current read position.

    public Buffer ()
    {
	// Allocate space.
	buf = new byte[1000];
    }

    public void writeByte (byte x)
    {
	// Write to next available space.
	buf [writeCursor++] = x;
    }

    public byte readByte ()
    {
	// Read next byte.
	byte k = buf [readCursor++];
	return k;
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


public class IO1 {

    public static void main (String[] argv)
    {
	// Create an independent quit button.
	QuitButton q = new QuitButton ();

	// A buffer instance.
	Buffer buf = new Buffer ();

	// Create a producer instance and run it.
	Producer p = new Producer (buf);
	p.run();

	// Create a consumer instance and run it.
	Consumer c = new Consumer (buf);
	c.run();
    }

}

