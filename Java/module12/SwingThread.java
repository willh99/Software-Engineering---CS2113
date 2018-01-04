import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


// A simple panel extension that draws a circle of a given size.

class DrawPanel extends JPanel {

    int circleSize = 20;
    String msg = "";

    public void paintComponent (Graphics g)
    {
        super.paintComponent (g);
        Dimension D = this.getSize ();
        g.setColor (Color.white);
        g.fillRect (0,0, D.width, D.height);
        g.setColor (Color.red);
        g.fillOval (300,300, circleSize, circleSize);
        g.setColor (Color.black);
        g.drawString (msg, 400, 100);
    }

}


public class SwingThread extends JFrame {

    DrawPanel drawPanel = new DrawPanel ();


    public SwingThread ()
    {
        this.setSize (600,600);
        
        // Button for Task 1:
        JPanel topPanel = new JPanel ();
        JButton first = new JButton ("Task 1");
        first.addActionListener (
            new ActionListener ()
            {
                public void actionPerformed (ActionEvent a)
                {
                    task1 ();
                }
            }
        );
        topPanel.add (first);

        // Button for Task 2:
        JButton second = new JButton ("Task 2");
        second.addActionListener (
            new ActionListener ()
            {
                public void actionPerformed (ActionEvent a)
                {
                    task2 ();
                }
            }
        );
        topPanel.add (second);

        // Quit button:
        JButton quit = new JButton ("Quit");
        quit.addActionListener (
            new ActionListener ()
            {
                public void actionPerformed (ActionEvent a)
                {
                    System.exit (0);
                }
            }
        );
        topPanel.add (quit);

        // The two buttons on top, the drawing area in the middle.
        Container cPane = this.getContentPane ();
        cPane.add (topPanel, BorderLayout.NORTH);
        cPane.add (drawPanel, BorderLayout.CENTER);

        this.setVisible (true);
    }
    

    void task1 ()
    {
        // Increase the circle size and re-draw.
        drawPanel.circleSize += 10;
        drawPanel.repaint ();
    }


    void task2 ()
    {
        heavyComputation ();
    }
    

    void heavyComputation ()
    {
        // We'll just fake heavy computation by sleeping.
        try {
            for (int i=1; i<=10; i++) {
                drawPanel.msg = "Task 2: " + i;
                Thread.sleep (1000);
                drawPanel.repaint ();
            }
            drawPanel.msg = "";
            drawPanel.repaint ();
        }
        catch (InterruptedException e){
        }
    }


    public static void main (String[] argv)
    {
        new SwingThread ();
    }

}
