// File: Survey0.java (Module 11)
//
// Author: Rahul Simha
// Created: November 10, 2000.
//
// Step 1: the top and bottom panels, and the cardlayout.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class NewFrame extends JFrame {

    JPanel centerpanel;    // For the questions.
    CardLayout card;       // For the centerpanel.

    // Constructor.
    public NewFrame (int width, int height)
    {
	this.setTitle ("Snoot Club Membership Test");
	this.setResizable (true);
	this.setSize (width, height);
	
	Container cPane = this.getContentPane();
	// cPane.setLayout (new BorderLayout());
	
	// First, a welcome message, as a Label.
	JLabel L = new JLabel ("<html><b>Are you elitist enough for our exclusive club?"
			       + " <br>Fill out the form and find out</b></html>");
	L.setForeground (Color.blue);
	cPane.add (L, BorderLayout.NORTH);
	
	// Now the center panel with the questions.
	card = new CardLayout ();
	centerpanel = new JPanel ();
	centerpanel.setLayout (card);
	centerpanel.setOpaque (false);
	
	// Each question will be created in a separate method.
	// The cardlayout requires a label as second parameter.
	centerpanel.add (firstQuestion (), "1");
	centerpanel.add (secondQuestion(), "2");
	centerpanel.add (thirdQuestion(), "3");
	centerpanel.add (fourthQuestion(), "4");
	centerpanel.add (fifthQuestion(), "5");
	cPane.add (centerpanel, BorderLayout.CENTER);
	
	// Next, a panel of four buttons at the bottom.
    // The four buttons: quit, submit, next-question, previous-question.
	JPanel bottomPanel = getBottomPanel ();
	cPane.add (bottomPanel, BorderLayout.SOUTH);

	// Finally, show the frame.
	this.setVisible (true);
    }
    
    // No-parameter constructor.
    public NewFrame () 
    {
	this (500, 300);
    }


    JPanel getBottomPanel ()
    {
	// Create a panel into which we will place buttons.
	JPanel bottomPanel = new JPanel ();

	// A "previous-question" button.
	JButton backward = new JButton ("Previous question");
	backward.setFont (new Font ("Serif", Font.PLAIN | Font.BOLD, 15));
	backward.addActionListener (
            new ActionListener () {
		public void actionPerformed (ActionEvent a)
		{
		    // Go back in the card layout.
		    card.previous (centerpanel);
		}  
	    }
        );
	bottomPanel.add (backward);

	// A forward button.
	JButton forward = new JButton ("Next question");
	forward.setFont (new Font ("Serif", Font.PLAIN | Font.BOLD, 15));
	forward.addActionListener (
            new ActionListener () {
		public void actionPerformed (ActionEvent a)
		{
		    // Go forward in the card layout.
		    card.next (centerpanel);
		}  
	    }
        );
	bottomPanel.add (forward);

	// A submit button.
	JButton submit = new JButton ("Submit");
	submit.setFont (new Font ("Serif", Font.PLAIN | Font.BOLD, 15));
	submit.addActionListener (
            new ActionListener () {
		public void actionPerformed (ActionEvent a)
		{
		    // Perform submit task.
		    computeResult();
		}  
	    }
        );
	bottomPanel.add (submit);

	JButton quitb = new JButton ("Quit");
	quitb.setFont (new Font ("Serif", Font.PLAIN | Font.BOLD, 15));
	quitb.addActionListener (
            new ActionListener () {
		public void actionPerformed (ActionEvent a)
		{
		    System.exit (0);
		}  
	    }
        );
	bottomPanel.add (quitb);

	return bottomPanel;
    }


    // This method will handle the panel for the first question.

    JPanel firstQuestion ()
    {
	// We will package everything into a panel and return the panel.
	JPanel subpanel = new JPanel ();
	
	// For now, we'll just create a label.
	JLabel L1 = new JLabel ("Question 1:");
	subpanel.add (L1);
	
	return subpanel;
    }


    // Second question.

    JPanel secondQuestion ()
    {
	JPanel subpanel = new JPanel ();

	JLabel L1 = new JLabel ("Question 2:");
	subpanel.add (L1);

	return subpanel;
    }


    // Third question.

    JPanel thirdQuestion ()
    {
	JPanel subpanel = new JPanel ();
	
	JLabel L1 = new JLabel ("Question 3:");
	subpanel.add (L1);
	
	return subpanel;
    }


    // Fourth question.

    JPanel fourthQuestion ()
    {
	JPanel subpanel = new JPanel ();
	
	JLabel L1 = new JLabel ("Question 4:");
	subpanel.add (L1);
	
	return subpanel;
    }

    // Fifth question
    JPanel fifthQuestion ()
    {
	JPanel subpanel = new JPanel ();
	
	JLabel L1 = new JLabel ("Question 5:");
	subpanel.add (L1);
	
	return subpanel;
    }


    // This method is called after submit is pressed.

    void computeResult ()
    {
	// To be filled in ...
    }

}


public class Survey0 {
    public static void main (String[] argv)
    {
	NewFrame nf = new NewFrame (600, 300);
    }
}
