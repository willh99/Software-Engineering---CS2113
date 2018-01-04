// File: TestList.java (Module 10)
//
// Author: Rahul Simha
// Created: Nov 2, 1998
//
// Starting point for Ex. 10.1: an anonymous class
// implementation of an Enumeration.
// The current file has the list implement the enumeration.

import java.util.*;

abstract class ComparableObject {
  public abstract String toString ();
  public abstract int compare (ComparableObject c);
}

class ListItem {

  ComparableObject data = null; 
  ListItem next = null;

  // Constructor.
  public ListItem (ComparableObject obj)
  {
    data = obj;  next = null;
  }
  
  // Accessor.
  public ComparableObject getData () 
  {
    return data;
  }
}


// LinkedList now implements Enumeration itself.

class LinkedList {

  ListItem front = null;
  ListItem rear = null;
  int numItems = 0;      // Current number of items.

  // Instance method to add a data item.
  public void addData (ComparableObject obj)
  {
    if (front == null) {
      front = new ListItem (obj);
      rear = front;
    }
    else {
      // Find the right place.
      ListItem tempPtr=front, prevPtr=front;
      boolean found = false;
      while ( (!found) && (tempPtr != null) ) {
        if (tempPtr.data.compare(obj) > 0) {
          found = true;
          break;
	}
        prevPtr = tempPtr;
        tempPtr = tempPtr.next;
      }
      // Now insert.
      if (!found) { // Insert at rear.
	rear.next = new ListItem (obj);
	rear = rear.next;
      }
      else if (tempPtr == front) { // Insert in front.
	ListItem Lptr = new ListItem (obj);
        Lptr.next = front;
        front = Lptr;
      }
      else { // Insert in the middle.
	ListItem Lptr = new ListItem (obj);
	prevPtr.next = Lptr;
	Lptr.next = tempPtr;
      }
    }
    numItems++;
  }

  public void printList ()
  {
    ListItem listPtr = front;
    System.out.println ("List: (" + numItems + " items)");
    int i = 1;
    while (listPtr != null) {
      System.out.println ("Item# " + i + ": " 
                          + listPtr.getData());
                          // Must implement toString()
      i++;
      listPtr = listPtr.next;
    }
  }

  ListItem enumPtr;

  // This is needed to return an Enumeration
  // instance to the user.
  public Enumeration getEnumeration ()
  {
    class Enum implements Enumeration{

      Enum(){
	enumPtr=front;
      }

      // Must implement this method.
      public Object nextElement() 
      {
        Object obj = enumPtr.data;
        enumPtr = enumPtr.next;
        return obj;
      }

      // Must implement this method.
      public boolean hasMoreElements ()
      {
        if (enumPtr == null)
          return false;
        else 
          return true;
      }


    }
    
    Enum e = new Enum();
    return e;      // Using the "this" reserved word.
  }

} // End of class "LinkedList"


// An object to use in the list:

class Person extends ComparableObject {

  String name;
  String ssn;

  // Constructor.
  public Person (String nameIn, String ssnIn)
  {
    name = nameIn;  ssn = ssnIn;
  }

  // Override toString()
  public String toString ()
  {
    return "Person: name=" + name + ", ssn=" + ssn;
  }

  // Must implement compare
  public int compare (ComparableObject obj)
  {
    Person p = (Person) obj;
    return name.compareTo (p.name);
  }

} // End of class "Person"


// Test class.

public class TestList {

  public static void main (String[] argv)
  {
    // Create a new list object.
    LinkedList L = new LinkedList ();

    // Create a Person instance and add it to list.
    L.addData (new Person ("Terminator", "444-43-4343"));
    L.addData (new Person ("James Bond", "666-65-6565"));
    L.addData (new Person ("Rambo", "555-54-5454"));
    L.addData (new Person ("Bruce Lee", "777-76-7676"));

    // Print contents via an Enumeration.
    Enumeration e = L.getEnumeration();
    while (e.hasMoreElements())
    {
      Person p = (Person) e.nextElement();
      System.out.println (p);
    }

  }

} // End of class "TestList"
