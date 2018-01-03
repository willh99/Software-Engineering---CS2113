// File: TestSwing25.java (Module 9b)
//
// Author: Rahul Simha
// Created: October 21, 2000
//
// Handling mouse clicks in the JPanel

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; // Needed for ActionListener.

class NewPanel extends JPanel implements MouseListener {

    Color drawColor = Color.green;  // Default color.
    
    public NewPanel ()
    {
	this.setBackground (Color.white);
	// Add panel's mouse-listening to panel.
	this.addMouseListener (this);
    }
    
    // These methods are required to implement 
    // the MouseListener interface.
    int currentX=0, currentY=0;
    public void mouseClicked (MouseEvent m)
    {
	System.out.println ("mouseClicked event: " + m.paramString());
	int x = m.getX();
	int y = m.getY();
	Graphics g = this.getGraphics();
	g.setColor (drawColor);
	g.drawLine (currentX, currentY, x, y);
	currentX = x;
	currentY = y;
    }

    // We need to implement these methods, but
    // don't actually have to do anything inside.
    public void mouseEntered (MouseEvent m) {}
    public void mouseExited (MouseEvent m) {}
    public void mousePressed (MouseEvent m) {}
    public void mouseReleased (MouseEvent m) {}
    
    public void setDrawingColor (Color c)
    {
	this.drawColor = c;
    }
    
}


class NewFrame extends JFrame 
    implements ActionListener {

    NewPanel drawPanel;
    
    // Constructor.
    public NewFrame (int width, int height)
    {
	// On Mac-OS-X, you may add this for the default Java look:
	try {
	    UIManager.setLookAndFeel (UIManager.getCrossPlatformLookAndFeelClassName());
	} catch (Exception e) {
	    e.printStackTrace(); 
	}

	// Set the title and other frame parameters.
	this.setTitle ("Button example");
	this.setResizable (true);
	this.setSize (width, height);

	Container cPane = this.getContentPane();
	// cPane.setLayout (new BorderLayout());    

	// The panel for drawing.
	drawPanel = new NewPanel ();
	cPane.add (drawPanel, BorderLayout.CENTER);

	// Create a quit button.
	JButton b = new JButton ("Quit");
	b.setBackground (Color.red);
	b.setFont (new Font ("Serif", Font.PLAIN | Font.BOLD, 15));
	b.addActionListener (this);
	cPane.add (b, BorderLayout.SOUTH);
	
	// Use a panel for clear and start buttons.
	JPanel topPanel = new JPanel ();
	topPanel.setLayout (new BorderLayout());
	cPane.add (topPanel, BorderLayout.NORTH);
	
	// Now insert the color buttons.
	JButton orangeB = new JButton ("Orange");
	orangeB.setBackground (Color.orange);
	orangeB.addActionListener (this);
	topPanel.add (orangeB);
	
	JButton greenB = new JButton ("Green");
	greenB.setBackground (Color.green);
	greenB.addActionListener (this);
	topPanel.add (greenB);

	// Show the frame.
	this.setVisible (true);
    }
    
    // This method is required to implement the 
    // ActionListener interface.
    public void actionPerformed (ActionEvent a)
    {
	String label = a.getActionCommand();
	if (label.equalsIgnoreCase ("Quit"))
	    System.exit (0);
	else if (label.equalsIgnoreCase ("Orange"))
	    drawPanel.setDrawingColor (Color.orange);
	else if (label.equalsIgnoreCase ("Green"))
	    drawPanel.setDrawingColor (Color.green);
    }
    
} // End of class "NewFrame"


public class TestSwing25 {

    public static void main (String[] argv)
    {
	NewFrame nf = new NewFrame (300, 200);
    }
    
}

