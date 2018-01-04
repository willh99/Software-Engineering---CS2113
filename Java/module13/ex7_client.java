// File: ex7_client.java (Module 13)
//
// Author: Rahul Simha
// Created: Nov 28, 1998
//
// Template for Ex 13.7: a client that writes a byte.

import java.io.*;
import java.net.*;

public class ex7_client {

    public static void main (String[] argv)
    {
        if ( (argv == null) || (argv.length == 0) ) {
            System.out.println ("Usage: enter 1 or 2 as parameter");
            System.exit(0);
        }
    
        try {

            // Extract integer from command-line argument.
            int i = Integer.parseInt (argv[0]);
	    byte b = (byte) i;

            // INSERT YOUR CODE HERE:
            //   Make a socket, extract the output stream and write the
	    //   byte b to it.
	    Socket soc = new Socket ("unix.seas.gwu.edu", 40012);
	    InetAddress remoteMachine = soc.getInetAddress();
	    System.out.println ("Producer as client: attempting connection"
				+ " to " + remoteMachine);
      
	    // Note: server must be fired up first!
      
	    // Now create the output stream and hand off to producer.
	    OutputStream outStream = soc.getOutputStream ();
	    outStream.write(b);
	    outStream.flush();
	    outStream.close();

        }
        catch (IOException e) { System.out.println (e); }
    }

}

