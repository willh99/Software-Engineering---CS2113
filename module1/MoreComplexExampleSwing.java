// A simple application that uses the mouse to draw

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class MoreComplexExampleSwing extends JPanel implements ActionListener {

  // Store current location of mouse:
  int currentX=0, currentY=0;

  public MoreComplexExampleSwing()
  {
    this.addMouseListener(
      // Unusual syntax for anonymous classes:
      new MouseAdapter () {
        public void mousePressed (MouseEvent m)
        {
          System.out.println ("mousePressed event: " + m.paramString());
          int x = m.getX();
          int y = m.getY();
          Graphics g = MoreComplexExampleSwing.this.getGraphics();
          g.drawLine (currentX, currentY, x, y);
          currentX = x;
          currentY = y;
         }
      }
    );

    this.addMouseMotionListener (
      new MouseMotionAdapter () {
        public void mouseDragged (MouseEvent m)
        {
          System.out.println ("mouseDragged event: " + m.paramString());
          int x = m.getX();
          int y = m.getY();
          Graphics g = MoreComplexExampleSwing.this.getGraphics();
          g.drawLine (currentX, currentY, x, y);
          currentX = x;
          currentY = y;
        }
      }
    );
  }

  // Button-pressed events:
  public void actionPerformed (ActionEvent a)
  {
    String eventDesc= a.getActionCommand();
    Color c = Color.gray;
    if (eventDesc.equalsIgnoreCase ("quit")) {
      System.exit(0);
    }
    else if (eventDesc.equalsIgnoreCase ("red")) {
      c = Color.red;
    }
    else if (eventDesc.equalsIgnoreCase ("blue")) {
      c = Color.blue;
    }
    this.setBackground (c);
    this.repaint();
  }

  void buildGUI ()
  {
    // Example of how a GUI is constructed
    JFrame frame = new JFrame ();
    frame.setSize (500,500);
    Container cPane = frame.getContentPane();
    cPane.add (this, BorderLayout.CENTER);

    JPanel bottom = new JPanel();

    Button qb = new Button ("Quit");
    qb.addActionListener (this);
    bottom.add (qb);

    Button rb = new Button ("Red");
    rb.addActionListener (this);
    bottom.add (rb);

    Button bb = new Button ("Blue");
    bb.addActionListener (this);
    bottom.add (bb);

    cPane.add (bottom, BorderLayout.SOUTH);

    frame.setVisible (true);
  }

  // To start execution, "main" is needed
  public static void main (String[] argv)
  {
    new MoreComplexExampleSwing().buildGUI();
  }
}
