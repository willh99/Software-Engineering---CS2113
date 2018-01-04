// File: Form.java (Module 11)
//
// Author: Rahul Simha
// Created: November 4, 2000
//
// A simple example to illustrate menus.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.util.*;
import java.io.*;

class NewFrame extends JFrame {

    // Data.
    JPanel centerpanel;         // For the questions.
    JScrollPane sc;             // For placing the centerpanel.

    JTextField lastnameTextF;   // To get the lastname.
    String lastname;

    JTextField firstnameTextF;  // Firstname.
    String firstname;

    JTextField networthTextF;   // Net worth
    String networth;

    JTextArea addressTextA;     // Address (TextArea)
    String address;

    JMenuBar mb;                // The menubar.

    JPopupMenu pm;              // The popup menu.


    // Constructor.
    public NewFrame (int width, int height)
    {
	this.setTitle ("Snoot Club Membership Form");
	this.setResizable (true);
	this.setSize (width, height);

	Container cPane = this.getContentPane();
	// cPane.setLayout (new BorderLayout());

	// First, a welcome message:
	JLabel L = new JLabel ("Please fill out the following membership form:");
	L.setFont (new Font ("Serif", Font.BOLD | Font.ITALIC, 15));
	L.setForeground (Color.blue);
	cPane.add (L, BorderLayout.NORTH);

	// Now the center panel.
	centerpanel = new JPanel ();
	centerpanel.setLayout (new GridLayout (4,1));

	// Each widget is created in a separate method.
	centerpanel.add ( firstName() );
	centerpanel.add ( lastName() );
	centerpanel.add ( netWorth() );
	centerpanel.add ( address () );

	// Next, the scrollpane to contain the main panel.
	sc = new JScrollPane (centerpanel);
	cPane.add (sc, BorderLayout.CENTER);

	// Create a menubar and add two menus.
	mb = new JMenuBar ();
	mb.add ( makeFileMenu() );
	mb.add ( makeActionMenu() );

	// Note how a menubar is added to a Frame.
	this.setJMenuBar (mb);

	// A pop-up menu is handled a little different from the other menus.
	makePopupMenu ();

	// Finally, show the frame.
	this.setVisible (true);
    }


    // No-parameter constructor.
    public NewFrame () 
    {
	this (500, 300);
    }


    // Read in the first name.

    JPanel firstName ()
    {
	JPanel subpanel = new JPanel ();

	// First, a label before the textfield.
	JLabel L = new JLabel ("First Name");
	L.setFont (new Font ("SansSerif", Font.ITALIC, 20));
	subpanel.add (L);

	// Need to have each component listen for a Popup click.
	L.addMouseListener (getMouseListener (L));

	// Create and add the textfield.
	firstnameTextF = new JTextField (20);
	firstnameTextF.setForeground (Color.blue);
	subpanel.add (firstnameTextF);

	// Make the subpanel listen for mouseclicks too.
	subpanel.addMouseListener (getMouseListener(subpanel));

	return subpanel;
    }


    // Get last name.

    JPanel lastName ()
    {
	JPanel subpanel = new JPanel ();

	// The "last name" label.
	JLabel L = new JLabel ("Last Name");
	L.setFont (new Font ("SansSerif", Font.ITALIC, 20));
	subpanel.add (L);

	// Listen for a Popup click.
	L.addMouseListener (getMouseListener (L));

	lastnameTextF = new JTextField (20);
	lastnameTextF.setForeground (Color.blue);
	subpanel.add (lastnameTextF);

	subpanel.addMouseListener (getMouseListener(subpanel));

	return subpanel;
    }


    // Get net worth.

    JPanel netWorth ()
    {
	JPanel subpanel = new JPanel ();

	JLabel L = new JLabel ("Net worth (in millions)");
	L.setFont (new Font ("SansSerif", Font.ITALIC, 20));
	subpanel.add (L);

	L.addMouseListener (getMouseListener (L));

	networthTextF = new JTextField (10);
	networthTextF.setForeground (Color.blue);
	subpanel.add (networthTextF);

	subpanel.addMouseListener (getMouseListener(subpanel));

	return subpanel;
    }


    // Get address via a TextArea.

    JPanel address ()
    {
	JPanel subpanel = new JPanel ();

	JLabel L = new JLabel ("Address");
	L.setFont (new Font ("SansSerif", Font.ITALIC, 20));
	subpanel.add (L);

	L.addMouseListener (getMouseListener (L));

	addressTextA = new JTextArea (4, 30);
	addressTextA.setForeground (Color.blue);
	subpanel.add (addressTextA);

	subpanel.addMouseListener (getMouseListener(subpanel));

	return subpanel;
    }


    // Create the File menu and associated listeners.

    JMenu makeFileMenu ()
    {
	// Add a "File" menu with two items.
	JMenu fileMenu = new JMenu ("File");

	// "Load From File" menu item
	JMenuItem loadFromFileMenuItem = new JMenuItem ("Load From File");
	loadFromFileMenuItem.addActionListener (
   	    new ActionListener () {
		public void actionPerformed (ActionEvent a)
		{
		    loadFromFile ();
		}  
	    }
	);
	fileMenu.add (loadFromFileMenuItem);

	// "Quit" menu item
	JMenuItem quitMenuItem = new JMenuItem ("Quit");
	quitMenuItem.addActionListener (
    	    new ActionListener () {
		public void actionPerformed (ActionEvent a)
		{
		    System.exit (0);
		}  
	    }
        );
	fileMenu.add (quitMenuItem);

	return fileMenu;
    }
  


    // Create the Action Menu and its listeners.

    JMenu makeActionMenu ()
    {
	// Add an "Action" menu with two items.
	JMenu actionMenu = new JMenu ("Action");

	// "Submit" menu item
	JMenuItem submitMenuItem = new JMenuItem ("Submit");
	submitMenuItem.addActionListener (
            new ActionListener () {
		public void actionPerformed (ActionEvent a)
		{
		    submit ();
		}  
	    }
        );
	actionMenu.add (submitMenuItem);

	// "Clear" menu item
	JMenuItem clearMenuItem = new JMenuItem ("Clear");
	clearMenuItem.addActionListener (
            new ActionListener () {
		public void actionPerformed (ActionEvent a)
		{
		    // Clear all the fields.
		    firstname = "";  firstnameTextF.setText (firstname);
		    lastname = "";  lastnameTextF.setText (lastname);
		    networth = "";  networthTextF.setText (networth);
		    address = "";  addressTextA.setText (address);
		}  
	    }
        );
	actionMenu.add (clearMenuItem);

	return actionMenu;
    }


    // Create the popup menu and its listeners.

    void makePopupMenu ()
    {
	// Note: pm is a top-level variable, which we will need to access elsewhere.
	pm = new JPopupMenu ();

	// Move to "top"
	JMenuItem topMenuItem = new JMenuItem ("Top");
	topMenuItem.addActionListener (
           new ActionListener () {
	       public void actionPerformed (ActionEvent a)
	       {
		   top ();
	       }  
	   }
        );
	pm.add (topMenuItem);

	// Move to "bottom"
	JMenuItem bottomMenuItem = new JMenuItem ("Bottom");
	bottomMenuItem.addActionListener (
            new ActionListener () {
		public void actionPerformed (ActionEvent a)
		{
		    bottom ();
		}  
	    }
        );
	pm.add (bottomMenuItem);

    }
  

    // Process data when ready.

    void submit ()
    {
	// Retrieve data from the widgets.
	lastname = lastnameTextF.getText();
	firstname = firstnameTextF.getText();
	networth = networthTextF.getText();
	address = addressTextA.getText();

	// Create a file by concatenating firstname and lastname.
	String filename = firstname + lastname;
	if (filename.length() == 0) return;

	// We will use a "Properties" instance to store data.
	Properties p = new Properties ();
	p.put ("firstname", firstname);
	p.put ("lastname", lastname);
	p.put ("networth", networth);
	p.put ("address", address);

	// Use the "save" feature of the Properties instance.
	try {
	    FileOutputStream f = new FileOutputStream (filename);
	    p.store (f, "Member information");
	}
	catch (IOException e) {
	    System.out.println (e);
	    System.exit (0);
	}
    }


    // Load information from an existing Properties file.

    Properties info;

    // Use a file dialog.

    void loadFromFile ()
    {
	// Create the FileDialog instance.
	JFileChooser fc =  new JFileChooser();
	int returnCode = fc.showOpenDialog (null);
    
	File file = fc.getSelectedFile();
    
	info = new Properties ();
	try {
	    FileInputStream f = new FileInputStream (file);
	    info.load (f);
	    firstname = info.getProperty ("firstname");
	    firstnameTextF.setText (firstname);
	    lastname = info.getProperty ("lastname");
	    lastnameTextF.setText (lastname);
	    networth = info.getProperty ("networth");
	    networthTextF.setText (networth);
	    address = info.getProperty ("address");
	    addressTextA.setText (address);
	}
	catch (IOException e) {
	    // Handle a file error gracefully with a dialog.
	    JOptionPane.showMessageDialog (this,
					   "File does not exist",
					   "Error dialog",
					   JOptionPane.INFORMATION_MESSAGE);
	}
    
    }



    // Popup's need to be explicitly shown when a mouseclick occurs.
    // Since each component will probably require such a listener,
    // this is a generic way of creating them.

    MouseListener getMouseListener (JComponent c)
    {
	class PopupMouseListener extends MouseAdapter {
	    JComponent c;
	    public PopupMouseListener (JComponent c)
	    {
		this.c = c;
	    }
	    public void mousePressed (MouseEvent m)
	    {
		// We need this special check.
		if (m.isPopupTrigger ()) 
		    pm.show (c, m.getX(), m.getY());
		// The "show" method requires the component reference,
		// which is why we store it in the variable c.
	    }  
	}

	return new PopupMouseListener (c);
    }


    // Handle the "go to top" event.

    void top ()
    {
	// Get the viewport out of the JScrollPane.
	JViewport vp = sc.getViewport();
	// Set the new view.
	vp.setViewPosition (new Point (0,0));
    }

    // Handle the "go to bottom" event.

    void bottom ()
    {
	// Get the viewport out of the JScrollPane.
	JViewport vp = sc.getViewport();
	// Set the new view.
	Dimension D = vp.getViewSize();
	vp.setViewPosition (new Point (0,D.height));
    }

}


public class Form {
    public static void main (String[] argv)
    {
	NewFrame nf = new NewFrame (500, 200);
    }
}
