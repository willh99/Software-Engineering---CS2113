// File: ex4.java (Module 9)
//
// Template for Ex. 9.4: 

import java.awt.*;
import javax.swing.*;

// Extend JPanel to override paintComponent ()
class NewPanel extends JPanel {
	
	String word;
	int fontsize;

    public NewPanel ()
    {
    	this.word = "Hello World!";
	this.fontsize = 10;
	this.setBackground (Color.cyan);
    
    }
    
    public void paintComponent (Graphics g)
    {
	super.paintComponent (g);
	
	
	Font f = new Font("Serif", Font.PLAIN | Font.BOLD, fontsize);
	g.setFont(f);
	
	FontMetrics fm= g.getFontMetrics();
	int stringW = fm.stringWidth(word);
	int fontHeight = fm.getHeight();
	
	Dimension D = this.getSize();

	int max_width = D.width;
	int max_height = D.height;

	while(stringW<=max_width && fontHeight<=max_height){
		
		f = new Font("Serif", Font.PLAIN | Font.BOLD, fontsize);
		g.setFont(f);
	
		fm= g.getFontMetrics();
		stringW = fm.stringWidth(word);
		fontHeight = fm.getHeight();
		fontsize = fontsize + 1;
	}
	g.drawString(word, 0, 0+fontHeight);	
    }
    
}


class NewFrame extends JFrame {
    
    // Constructor.
    public NewFrame (int width, int height)
    {
	// Set the title and other frame parameters.
	this.setTitle ("Exercise 9.4");
	this.setResizable (true);
	this.setSize (width, height);
	
	// Create and add the panel.
	NewPanel panel = new NewPanel ();
	this.getContentPane().add (panel);
	
	// Show the frame.
	this.setVisible (true);
    }
    
} // End of class "NewFrame"


public class ex4 {
    
    public static void main (String[] argv)
    {
	NewFrame nf = new NewFrame (600, 200);
    }
    
}

