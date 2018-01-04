// File: ex7_server.java (Module 13)
//
// Author: Rahul Simha
// Created: Nov 28, 1998
//
// Template for Ex. 13.7: a server that reads a byte.

import java.io.*;
import java.net.*;

public class ex7_server {

    public static void main (String[] argv)
    {
        try {

            // INSERT YOUR CODE HERE.
            //   Make a server-socket, wait for a connection.
            //   When a connection is received, extract the inputStream
            //   and read a byte from it. Print that byte.
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
	    System.out.println(inStream.read());

        }
        catch (IOException e) { System.out.println (e); }
    }

}

