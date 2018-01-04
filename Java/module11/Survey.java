// File: Survey.java (Module 11)
//
// Author: Rahul Simha
// Created: November, 2000
//
// Demonstrates standard widgets via a survey form.
// The survey has four questions - different widgets
// are used for each question.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class NewFrame extends JFrame {

    JPanel centerpanel;    // For the questions.
    CardLayout card;       // For the centerpanel.

    JTextField tf;         // Used in question 1.

    boolean                // Store selections for Q2.
	q2Option1,
	q2Option2,
	q2Option3,
	q2Option4;

    JList q4List;          // For question 4.

    double                 // Score on each question.
	q1Score = 0,
	q2Score = 0,
	q3Score = 0,
	q4Score = 0;
    
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


    // The first question uses labels for the question and
    // gets input via a textfield. A panel containing all 
    // these things is returned. The question asks for
    // a vacation destination: the more exotic the location,
    // the higher the score.
    
    JPanel firstQuestion ()
    {
	// We will package everything into a panel and return the panel.
	JPanel subpanel = new JPanel ();
	
	// We will place things in a single column, so 
	// a GridLayout with one column is appropriate.
	subpanel.setLayout (new GridLayout (8,1));
	
	JLabel L1 = new JLabel ("Question 1:");
	L1.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L1);
	
	JLabel L2 = new JLabel ("  Select a vacation destination");
	L2.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L2);
	
	JLabel L3 = new JLabel ("    1. Baltimore");
	L3.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L3);
	
	JLabel L4 = new JLabel ("    2. Disneyland");
	L4.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L4);
	
	JLabel L5 = new JLabel ("    3. French Riviera");
	L5.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L5);
	
	JLabel L6 = new JLabel ("    4. Musha Cay");
	L6.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L6);
	
	JLabel L7 = new JLabel ("Enter 1,2,3 or 4 below:");
	L7.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L7);
	
	// Here's the textfield to get user-input.
	tf = new JTextField ();
	tf.addActionListener (
            new ActionListener () {
		// This interface has only one method.
		public void actionPerformed (ActionEvent a)
		{
		    String q1String = a.getActionCommand();
		    if (q1String.equals ("2"))
			q1Score = 2;
		    else if (q1String.equals ("3"))
			q1Score = 3;
		    else if (q1String.equals ("4"))
			q1Score = 4;
		    else
			q1Score = 1;
		}  
	    }
        );
        subpanel.add (tf);

	return subpanel;
    }


    // For the second question, a collection of checkboxes
    // will be used. More than one selection can be made.
    // A listener is required for each checkbox. The state
    // of each checkbox is recorded.
    
    JPanel secondQuestion ()
    {
	JPanel subpanel = new JPanel ();
	subpanel.setLayout (new GridLayout (7,1));
	
	JLabel L1 = new JLabel ("Question 2:");
	L1.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L1);
	
	JLabel L2 = new JLabel ("  Select ONE OR MORE things that ");
	L2.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L2);
	
	JLabel L3 = new JLabel ("  you put into your lunch sandwich");
	L3.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L3);
	
	// Initialize the selections to false.
	q2Option1 = q2Option2 = q2Option3 = q2Option4 = false;
	
	// First checkbox.
	JCheckBox c1 = new JCheckBox ("Walmart Bologna");
	c1.addItemListener (
            new ItemListener () {
		public void itemStateChanged (ItemEvent i)
		{
		    JCheckBox c = (JCheckBox) i.getSource();
		    q2Option1 = c.isSelected();
		}
	    }
        );
	subpanel.add (c1);

	// Second checkbox.
	JCheckBox c2 = new JCheckBox ("Amy's Black Bean Burger");
	c2.addItemListener (
            new ItemListener () {
		// This is where we will react to a change in checkbox.
		public void itemStateChanged (ItemEvent i)
		{
		    JCheckBox c = (JCheckBox) i.getSource();
		    q2Option2 = c.isSelected();
		}
	    }
        );
	subpanel.add (c2);

	// Third checkbox.
	JCheckBox c3 = new JCheckBox ("Lemon-enhanced smoked Siberian caviar");
	c3.addItemListener (
            new ItemListener () {
		public void itemStateChanged (ItemEvent i)
		{
		    JCheckBox c = (JCheckBox) i.getSource();
		    q2Option3 = c.isSelected();
		}
	    }
        );
	subpanel.add (c3);

	// Fourth checkbox.
	JCheckBox c4 = new JCheckBox ("Artisanal oyster leaf et framboise");
	c4.addItemListener (
            new ItemListener () {
		public void itemStateChanged (ItemEvent i)
		{
		    JCheckBox c = (JCheckBox) i.getSource();
		    q2Option4 = c.isSelected();
		}
	    }
        );
	subpanel.add (c4);

	return subpanel;
    }


    // The third question allows only one among four choices
    // to be selected. We will use radio buttons.
    
    JPanel thirdQuestion ()
    {
	JPanel subpanel = new JPanel ();
	subpanel.setLayout (new GridLayout (6,1));
	
	JLabel L1 = new JLabel ("Question 3:");
	L1.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L1);
	
	JLabel L2 = new JLabel ("  And which mustard do you use?");
	L2.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L2);
	
	// First, create the ButtonGroup instance.
	// We will add radio buttons to this group.
	ButtonGroup bGroup = new ButtonGroup();

	// First checkbox.
	JRadioButton r1 = new JRadioButton ("Safeway brand");
	r1.addItemListener (
            new ItemListener () {
		public void itemStateChanged (ItemEvent i)
		{
		    JRadioButton r = (JRadioButton) i.getSource();
		    if (r.isSelected()) q3Score = 1;
		}
	    }
        );
	bGroup.add (r1);
	subpanel.add (r1);
	
	// Second checkbox.
	JRadioButton r2 = new JRadioButton ("Fleishman's");
	r2.addItemListener (
            new ItemListener () {
		public void itemStateChanged (ItemEvent i)
		{
		    JRadioButton r = (JRadioButton) i.getSource();
		    if (r.isSelected()) q3Score = 2;
		}
	    }
        ); 
	bGroup.add (r2);
	subpanel.add (r2);

	// Third checkbox.
	JRadioButton r3 = new JRadioButton ("Grey Poupon");
	r3.addItemListener (
            new ItemListener () {
		public void itemStateChanged (ItemEvent i)
		{
		    JRadioButton r = (JRadioButton) i.getSource();
		    if (r.isSelected()) q3Score = 3;
		}
	    }
        );
	bGroup.add (r3);
	subpanel.add (r3);

	// Fourth checkbox.
	JRadioButton r4 = new JRadioButton ("Moutarde de Meaux");
	r4.addItemListener (
            new ItemListener () {
		public void itemStateChanged (ItemEvent i)
		{
		    JRadioButton r = (JRadioButton) i.getSource();
		    if (r.isSelected()) q3Score = 4;
		}
	    }
        );
	bGroup.add (r4);
	subpanel.add (r4);


	return subpanel;
    }


    // For the fourth question we will use a drop-down Choice.

    JPanel fourthQuestion ()
    {
	JPanel subpanel = new JPanel ();
	subpanel.setLayout (new GridLayout (3,1));

	JLabel L1 = new JLabel ("Question 4:");
	L1.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L1);
	
	JLabel L2 = new JLabel ("  Your movie preference, among these:");
	L2.setFont (new Font ("SansSerif", Font.ITALIC, 15));
	subpanel.add (L2);

	// Create a JList with options.
	String[] movies = { "Fast & Furious 19", "Star Wars", "Citizen Kane", 
                        "Le film d'art avec sous-titres"};
	q4List = new JList (movies);
	q4Score = 1;
        q4List.addListSelectionListener (
            new ListSelectionListener () {
		public void valueChanged (ListSelectionEvent e)
		{
		    q4Score = 1 + q4List.getSelectedIndex();
		}
	    }
        );
	subpanel.add (q4List);

	return subpanel;
    }

    void computeResult ()
    {
	// Clear the center panel.
	centerpanel.removeAll();
	
	// Create a new panel to display in the center.
	JPanel subpanel = new JPanel (new GridLayout (5,1));
	
	// Score on question 1.
	JLabel L1 = new JLabel ("Score on question 1: " + q1Score);
	L1.setFont (new Font ("Serif", Font.ITALIC, 15));
	subpanel.add (L1);
	
	// Score on question 2.
	if (q2Option1) q2Score += 1;
	if (q2Option2) q2Score += 2;
	if (q2Option3) q2Score += 3;
	if (q2Option4) q2Score += 4;
	q2Score = 0.6 * q2Score;
	JLabel L2 = new JLabel ("Score on question 2: " + q2Score);
	L2.setFont (new Font ("Serif", Font.ITALIC, 15));
	subpanel.add (L2);
	
	// Score on question 3.
	JLabel L3 = new JLabel ("Score on question 3: " + q3Score);
	L3.setFont (new Font ("Serif", Font.ITALIC, 15));
	subpanel.add (L3);

	// Score on question 4.
	JLabel L4 = new JLabel ("Score on question 4: " + q4Score);
	L4.setFont (new Font ("Serif", Font.ITALIC, 15));
	subpanel.add (L4);
	
	// Weighted score.
	double avg = (q1Score + q2Score + q3Score + q4Score) / (double) 4;
	JLabel L5;
	if (avg <= 3.5)
	    L5 = new JLabel ("Your average score: " + avg + " - REJECTED!");
	else 
	    L5 = new JLabel ("Your average score: " + avg + " - WELCOME!");
	L5.setFont (new Font ("Serif", Font.BOLD, 20));
	//L5.setAlignment (JLabel.CENTER);
	subpanel.add (L5);

	// Now add the new subpanel.
	centerpanel.add (subpanel, "5");
	
	// Need to mark the centerpanel as "altered"
	centerpanel.invalidate();

	// Everything "invalid" (e.g., the centerpanel above)
	// is now re-computed.
	this.validate();
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

}


public class Survey {
    public static void main (String[] argv)
    {
	NewFrame nf = new NewFrame (600, 300);
    }
}
