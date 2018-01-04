// File: ex7.java (Module 9b)
//
// Template for ex 9.7.

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; // Needed for ActionListener.

class NewPanel extends JPanel {

  int fontSize = 10;
	Font f;
    public NewPanel ()
    {
	this.setBackground (Color.cyan);
    
    }
    
    public void paintComponent (Graphics g) 
    {
	super.paintComponent (g);

	g.drawString("Hello World!", 100, 100);
	// Insert some of your code here (drawing helloworld in the
	// right font size)
    }
    
  
}



class NewFrame extends JFrame implements ActionListener {

    // Store the panel.
    NewPanel panel;
    int font=10;
    
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
	
	// The panel for drawing - created for you.
	panel = new NewPanel ();
	cPane.add (panel, BorderLayout.CENTER);
	
	// Quit button - created for you.
	JButton b = new JButton ("Quit");
	b.setBackground (Color.red);
	b.setFont (new Font ("Serif", Font.PLAIN | Font.BOLD, 15));
	b.addActionListener (this);
	cPane.add (b, BorderLayout.SOUTH);
	
	// Insert some of your code here (zoom button)
	JButton z = new JButton("Zoom");
	z.setFont(new Font ("Serif", Font.PLAIN | Font.BOLD, 15));
	z.addActionListener(this);
	
	cPane.add(z, BorderLayout.NORTH);
	
	// Show the frame.
	this.setVisible (true);
    }

    // This method is required to implement the 
    // ActionListener interface.
    public void actionPerformed (ActionEvent a)
    {
	String event_desc = a.getActionCommand();
	if (event_desc.equalsIgnoreCase("Quit"))
	    System.exit (0);
	else if(event_desc.equalsIgnoreCase("Zoom")){
	    font = font*2;
	    panel.setFont(new Font ("Serif", Font.PLAIN, font)); 
	}
	repaint();
    }
} // End of class "NewFrame"


public class ex7 {

  public static void main (String[] argv)
  {
    NewFrame nf = new NewFrame (300, 200);
  }

}
