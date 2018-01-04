// File: ex7.java (Module 11)
//
// Author: Rahul Simha
// Created: November 4, 1998.
//
// Template for Ex.11.7: including up and down arrows.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;

class NewFrame extends JFrame {

    // Data.

    JSlider               // One scrollbar for each color.
	redSlider,
	blueSlider,
	greenSlider;

    int                   // Intensity value for each color.
	redValue = 0,
	blueValue = 0,
	greenValue = 0;

    JPanel drawingArea;   // To display the mixed color.


    // Constructor.

    public NewFrame (int width, int height)
    {
	this.setTitle ("RGB combination");
	this.setResizable (true);
	this.setSize (width, height);

	Container cPane = this.getContentPane();
	// cPane.setLayout (new BorderLayout());

	// Three sliders and the JPanel will
	// be added to a panel.
	JPanel p = new JPanel ();
	p.setLayout (new GridLayout (4,1));

	// The canvas on which to display the result.
	drawingArea = new JPanel ();
	drawingArea.setBackground (Color.white);

	// A KeyListener for the panel
	drawingArea.addKeyListener ( getKeyListener() );

	p.add (drawingArea);

	// A local Listener for the sliders
	class ColorChangeListener implements ChangeListener {
	    public void stateChanged (ChangeEvent e)
	    {
		adjust ();
	    }
	}

	// Red one.
	redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
	redSlider.addChangeListener (new ColorChangeListener());
	redSlider.addKeyListener (getKeyListener());
	p.add (redSlider);

	// Green one.
	greenSlider = new JSlider (JSlider.HORIZONTAL, 0, 255, 0);
	greenSlider.addChangeListener (new ColorChangeListener());
	greenSlider.addKeyListener (getKeyListener());
	p.add (greenSlider);

	// Blue one.
	blueSlider = new JSlider (JSlider.HORIZONTAL, 0, 255, 0);
	blueSlider.addChangeListener (new ColorChangeListener());
	blueSlider.addKeyListener (getKeyListener());
	p.add (blueSlider);

	currentSlider = redSlider;

	cPane.add (p, BorderLayout.CENTER);

	// A quit button for the application.
	Button quitb = new Button ("Quit");
	quitb.setBackground (Color.red);
	quitb.setFont (new Font ("Serif", Font.PLAIN | Font.BOLD, 15));
	quitb.addActionListener (
	    new ActionListener () {
		public void actionPerformed (ActionEvent a)
		{
		    System.exit (0);
		}  
	    }
	);
	cPane.add (quitb, BorderLayout.SOUTH);

	// Finally, show the frame.
	this.setVisible (true);
    }


    // No-parameter constructor.
    public NewFrame () 
    {
	this (500, 300);
    }


    // Create the composite color.

    public void adjust ()
    {
	// Get the new values.
	redValue = redSlider.getValue();
	greenValue = greenSlider.getValue ();
	blueValue = blueSlider.getValue ();

	// Set the color in each scrollbar.
	redSlider.setBackground (new Color (redValue, 0, 0));
	greenSlider.setBackground (new Color (0, greenValue, 0));
	blueSlider.setBackground (new Color (0, 0, blueValue));

	// Create the composite color.
	Color newColor = new Color (redValue, greenValue, blueValue); 
	drawingArea.setBackground (newColor);
	drawingArea.repaint ();
    }


    JSlider currentSlider;

    // Create a KeyListener to be used everywhere.

    KeyListener getKeyListener ()
    {
	return new KeyAdapter () {
	    public void keyPressed (KeyEvent k)
	    {
		int currentValue = currentSlider.getValue();
		if (k.getKeyCode() == KeyEvent.VK_Q)
		    // "q" for quit.
		    System.exit (0);
		else if (k.getKeyCode() == KeyEvent.VK_LEFT) {	  
		    // Left arrow.
		    currentSlider.setValue (Math.max (0, currentValue-10));
		    adjust ();
		}
		else if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
		    // Right arrow.
		    currentSlider.setValue (Math.min (255, currentValue+10));
		    adjust ();
		}
		else if (k.getKeyCode() == KeyEvent.VK_UP) {
		    // ADD CODE HERE
		    currentSlider.setValue (Math.min (255, currentValue+10));
		    adjust ();
		}
		else if (k.getKeyCode() == KeyEvent.VK_DOWN) {
		    // ADD CODE HERE
		    currentSlider.setValue (Math.max (0, currentValue-10));
		    adjust ();
		}
	    }
	};
    }

}


public class ex7 {
    public static void main (String[] argv)
    {
	NewFrame nf = new NewFrame (500, 300);
    }
}
