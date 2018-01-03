
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;


class DrawPanel extends JPanel {

    // Target x and velocity (It doesn't move in the Y direction).
    double targetX=0, targetVX=10;

    // Missile (x,y) and velocities in x,y directions.
    double missileX=0, missileY=300, missileVX=20, missileVY=0;

    // Missile acceleration towards earth.
    double missileAY = -3;

    // Timestep.
    double delT = 0.1;

    // Compute move for missile when this has been set by user.
    boolean missileFired = false;

    // We'll use a new thread for each reset.
    Thread currentThread;


    public void paintComponent (Graphics g)
    {
        super.paintComponent (g);

        // Clear.
        Dimension D = this.getSize();
        g.setColor (Color.white);
        g.fillRect (0,0, D.width,D.height);
        
        // Draw target.
        g.setColor (Color.blue);
        int x = (int) targetX;
        g.fillRect (x,D.height-20, 20, 20);

        // Draw missile.
        g.setColor (Color.red);
        x = (int) missileX;
        int y = D.height - (int) missileY;
        g.fillOval (x,y, 10, 10);
    }


    void reset ()
    {
	// Initialize the "physics"
        missileFired = false;
        targetX = 0;
        targetVX = 10;
        missileX = 0;
        missileY = 300;
        missileVY = 0;
        missileVX = 20;

        // Create and start off a thread for the animation.
        if (currentThread != null) {
            currentThread = null;
        }
        currentThread = new Thread () {
            public void run () 
            {
		animate ();
	    }
        };
        currentThread.start();
    }


    void animate ()
    {
        while (true) {

            // Do the "physics" for each step
            targetX += targetVX*delT;
            if (missileFired) {
                missileX += missileVX*delT;
                missileVY += missileAY*delT;
                missileY += missileVY*delT;
            }

	    // Redraw the screen.
            this.repaint ();

	    // Pause between successive re-drawings to create animation effect.
            try {
                Thread.sleep (50);
            }
            catch (InterruptedException e){
                System.out.println (e);
            }

        } //endwhile
    }
    
    
    void fire ()
    {
        missileFired = true;
    }
    
}




public class Missile extends JFrame implements ActionListener {

    // This is the JPanel on which we draw, and the one that
    // will override paintComponent()
    DrawPanel drawPanel;

    public Missile () 
    {
        this.setSize (500,500);
        Container cPane = this.getContentPane();

	// Create the center panel on which to draw and place in the center.
        drawPanel = new DrawPanel ();
        cPane.add (drawPanel, BorderLayout.CENTER);

	// This panel serves as the container for the two buttons:
        JPanel panel = new JPanel ();

        JButton resetB = new JButton ("Reset");
        resetB.addActionListener (this);
        panel.add (resetB);

        panel.add (new JLabel("    "));
        JButton fireB = new JButton ("Fire");
        fireB.addActionListener (this);
        panel.add (fireB);

        cPane.add (panel, BorderLayout.SOUTH);

        this.setVisible (true);
    }

    public void actionPerformed (ActionEvent a)
    {
	if (a.getActionCommand().equals("Reset")) {
	    // We'll put the animation and calculation code in DrawPanel
	    drawPanel.reset ();
	}
	else if (a.getActionCommand().equals("Fire")) {
	    drawPanel.fire ();
	}
	else {
	    System.out.println ("ERROR: no such action");
	}
    }


    public static void main (String[] argv)
    {
        new Missile ();
    }


}
