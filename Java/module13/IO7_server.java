// File: IO7_server.java (Module 13)
//
// Author: Rahul Simha
// Created: Nov 28, 1998
// Modified: Nov 13, 2000.
//
// Consumer as server, using sockets.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

class Consumer extends JFrame implements Runnable {

    JLabel L;     // Data similar to Producer.
    InputStream inStream;

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

	    // Extract byte.
	    byte k = (byte) i;

	    // Check if end-of-data.
	    if ( (i < 0) || (k < 0) ) break;

	    System.out.println ("Consumer: just read " + k);

	    // Write it on the frame.
	    L.setText (L.getText() + " " + k);

	    // Sleep for a while.
	    try {
		Thread.sleep ((int)UniformRandom.uniform(5,10));
	    }
	    catch (InterruptedException e) { System.out.println (e); }
	}

	L.setText (L.getText() + " Done!");
	try {
	    inStream.close ();
	}
	catch (IOException e) { System.out.println (e); }

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


public class IO7_server {

    public static void main (String[] argv)
    {
	// Create an independent quit button.
	QuitButton q = new QuitButton ();

	try {
	    // Create a listening service for connections
	    // at the designated port number.
	    ServerSocket srv = new ServerSocket (40220);

	    // When a connection is made, get the socket.
	    // The method accept() blocks until then.
	    System.out.println ("Consumer as server: waiting for a connection");
	    Socket soc = srv.accept ();

	    // At this stage, the connection will have been made.
	    InetAddress remoteMachine = soc.getInetAddress();
	    System.out.println ("Consumer as server: accepted a connection"
				+ " from " + remoteMachine);

	    // We are going to listen, so get an InputStream
	    // and hand it over to the consumer.
	    InputStream inStream = soc.getInputStream ();

	    // Create a consumer instance and thread.
	    Consumer c = new Consumer (inStream);
	    Thread cthread = new Thread (c);
      
	    // Start the thread.
	    cthread.start();
	}
	catch (IOException e) { System.out.println (e); }
    }

}

