// File: IO7_client.java (Module 13)
//
// Author: Rahul Simha
// Created: Nov 28, 1998
// Modified: Nov 13, 2000.
//
// Producer as client, using sockets.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

class Producer extends JFrame implements Runnable {

    JLabel L;     // A place to write stuff.
    OutputStream outStream;

    public Producer (OutputStream outStream)
    {
	// Store the reference to the output stream.
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
	    } catch (InterruptedException e) { System.out.println (e); }
	}

	// Write EOF and close output stream.
	try {
	    outStream.write (-1);
	    outStream.close ();
	}
	catch (IOException e) { System.out.println (e); }

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


public class IO7_client {

    public static void main (String[] argv)
    {
	// Create an independent quit button.
	QuitButton q = new QuitButton ();

	try {
	    // Open a socket to the server.
	    // Address: hobbes.seas.gwu.edu, port 5010.
	    // 161.253.77.33 is the IP addr of the Unix UDP server
	    //Socket soc = new Socket ("161.253.77.33", 40013);
	    Socket soc = new Socket ("unix.seas.gwu.edu", 40220);
	    InetAddress remoteMachine = soc.getInetAddress();
	    System.out.println ("Producer as client: attempting connection"
				+ " to " + remoteMachine);
      
	    // Note: server must be fired up first!
      
	    // Now create the output stream and hand off to producer.
	    OutputStream outStream = soc.getOutputStream ();
      
	    // Create a producer instance and thread.
	    Producer p = new Producer (outStream);
	    Thread pthread = new Thread (p);
      
	    // Start the threads.
	    pthread.start();
	}
	catch (IOException e) { System.out.println (e); }
    }

}

