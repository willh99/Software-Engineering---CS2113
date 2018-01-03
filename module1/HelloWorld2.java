// File: HelloWorld2.java
// Author: Rahul Simha

public class HelloWorld2 {
  // public makes these methods accessible outside this file
  // The name of the class is HelloWorld2

  public static void main (String[] argv) {

    // Public function which is also static.
    // void means it returns nothing.
    // main function is required in order to start execution.
    // 'String[] argv' are command line parameters place by the
    // run-time system

    System.out.println ("Hello World!");
    // System.out is an object that contains a useful function
    // called println, which takes in a string and prints it out
    // on a new line
    
    System.out.println ("Here's the list of arguments you gave me:");

    for (int i=0; i<argv.length; i++)
      System.out.println ("Argument#" + i + ": " + argv[i]);
      // Notice how strings can be concatenated with the + operated.
      // Is 'i' a string or a variable
      
    System.out.println ("Bye!");
  }
}
