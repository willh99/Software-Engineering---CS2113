import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class ForestPanel extends JPanel{

    Cell[][] cells;
    int size;
   
    // Constructor for ForestPanel
    // Point cell array to the one pased to 
    // the constructor 
    public ForestPanel(Cell[][] c){
	cells=c;
        mouseClick();
    }
    

    // Function to handle a mouse click event in the panel
    // Listens for a mouse click, then determines the coordinates
    // in order to update clicked cell.
    private void mouseClick() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int x=e.getX();
                int y=e.getY();
/*                System.out.println("Tree:"+cells[y/size][x/size]);
                System.out.println("isStanding="+cells[x/size][y/size].hasStandingTree());
                System.out.println("hasBurningTree="+cells[x/size][y/size].hasBurningTree());
                System.out.println("hasBurntTree="+cells[x/size][y/size].hasBurntTree());*/
                if(cells[x/size][y/size].hasStandingTree())
                    cells[y/size][x/size].ignite();
            }
        });
    }


    // Overrides paintomponent of the JPanel superclass.
    // Size is a basic unit of the panel. Each cell is given an nth
    // of the size of the overall panel
    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	if(this.getHeight() > this.getWidth())
	    size = this.getWidth()/cells.length;
	else
	    size = this.getHeight()/cells.length;

	// Loop through the coordinates relative to the position of every
	// cell in the array. Draw a circle in the middle of every rectangle
	// and fill it with the colour cooresponding to its respective tree
	for(int i=0; i<(size*cells.length); i=i+size){
	    for(int j=0; j<(size*cells.length); j=j+size){

		g.drawRect(j,i,size,size);
		if(cells[i/size][j/size].TreeType(1)){
		    if(cells[i/size][j/size].hasStandingTree()){
                        g.setColor(Color.green);
                        g.fillOval(j+(size/4),i+(size/4),size/2,size/2);
		    }
		    else if(cells[i/size][j/size].hasBurningTree()){
                        g.setColor(Color.red);
                        g.fillOval(j+(size/4),i+(size/4),size/2,size/2);
		    }
		    else if(cells[i/size][j/size].hasBurntTree()){
                        g.setColor(Color.gray);
                        g.fillOval(j+(size/4),i+(size/4),size/2,size/2);
		    }

		}
		else if(cells[i/size][j/size].TreeType(2)){
		    if(cells[i/size][j/size].hasStandingTree()){
                        g.setColor(Color.blue);
                        g.fillOval(j+(size/4),i+(size/4),size/2,size/2);
		    }
		    else if(cells[i/size][j/size].hasBurningTree()){
                        g.setColor(Color.orange);
                        g.fillOval(j+(size/4),i+(size/4),size/2,size/2);
		    }
		    else if(cells[i/size][j/size].hasBurntTree()){
                        g.setColor(Color.black);
                        g.fillOval(j+(size/4),i+(size/4),size/2,size/2);
		    }
		}
		else{
                    g.setColor(Color.black);
                    g.fillRect(j,i,size,size);
		}
                g.setColor(Color.black);
	    }
	}
    }

}
