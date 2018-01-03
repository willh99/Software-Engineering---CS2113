import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Landscape extends JFrame{

  private Cell[][] cells;
  protected int numTreesToIgnite;
  private int[] igniteX;
  private int[] igniteY;

  public Landscape(int[][] landscape, int size, int TtoI, int[] iX, int[] iY){
    //initialize cells:
    cells = new Cell[size][size];
    numTreesToIgnite = TtoI;
    igniteX = iX;
    igniteY = iY;

    //Allocate new cell for each element in array
    for(int i=0; i<size; i++){
      for(int j=0; j<size; j++){
        cells[i][j] = new Cell(landscape[i][j]);
      }
    }
    
    createFrame(600,500);
  }

  protected void createFrame(int width, int height)
  {
    // Set title and frame parameters
    this.setTitle("Assignment 2");
    this.setResizable(true);
    this.setSize(600,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container cPane = this.getContentPane();

    // Add panels to insert components into layout
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout());
    cPane.add(topPanel, BorderLayout.NORTH);
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());
    cPane.add(bottomPanel, BorderLayout.SOUTH);
    ForestPanel forestPanel = new ForestPanel(cells);
    cPane.add(forestPanel, BorderLayout.CENTER);
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    cPane.add(rightPanel, BorderLayout.EAST);
    
    // JLabels used to display forestfire statistics
    JLabel num1standing = new JLabel("Number of Type 1 Trees Standing = "+getNumTreesStanding(1)+" ");
    rightPanel.add(num1standing);
    JLabel num1burning = new JLabel("Number of Type 1 Trees Burning = "+getNumTreesBurning(1)+" ");
    rightPanel.add(num1burning);
    JLabel num1burnt = new JLabel("Number of Type 1 Trees Burned = "+getNumTreesBurned(1)+" ");
    rightPanel.add(num1burnt);
    JLabel num2standing = new JLabel("Number of Type 2 Trees Standing = "+getNumTreesStanding(2)+" ");
    rightPanel.add(num2standing);
    JLabel num2burning = new JLabel("Number of Type 2 Trees Burning = "+getNumTreesBurning(2)+" ");
    rightPanel.add(num2burning);
    JLabel num2burnt = new JLabel("Number of Type 2 Trees Burned = "+getNumTreesBurned(2)+" ");
    rightPanel.add(num2burnt);
    
    // Next Step and Complete Run Buttons are in the top panel
    // Next Step Button increments the simulation by one step
    // at a time by calling the simulateForestFile() method once
    // every time pressed
    JButton nextStepB = new JButton("Next Step");
    nextStepB.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent a){
            simulateForestFire();
            updateLabels(num1standing, num1burning, num1burnt, num2standing, num2burning, num2burnt);
            forestPanel.repaint();
        }
    });
    topPanel.add(nextStepB);
    
    // Complete Run Button runs a forest fire all the way to
    // completion when pressed
    JButton completeRunB = new JButton("Complete Run");
    completeRunB.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent a){
            while(numTreesToIgnite != 0)
                simulateForestFire();
            updateForest();
            updateForest();
            updateLabels(num1standing, num1burning, num1burnt, num2standing, num2burning, num2burnt);
            forestPanel.repaint();
        }
    });
    topPanel.add(completeRunB);

    // Buttons in bottom panel
    JButton startNewB = new JButton("Start New Run");
    startNewB.addActionListener( new ActionListener() {
       public void actionPerformed(ActionEvent a){
           newLandscape();
           updateLabels(num1standing, num1burning, num1burnt, num2standing, num2burning, num2burnt);
           forestPanel.repaint();
       } 
    });
    bottomPanel.add(startNewB);
    
    // Simple quit button exits the program when pressed
    JButton quitB = new JButton("Quit");
    quitB.addActionListener( new ActionListener() {
	public void actionPerformed(ActionEvent a){
	    System.exit(0);
	}
      });
    bottomPanel.add(quitB);

    this.setVisible(true);
  }
 

    // Method to generate a new Landscape. Calls static method
    // createRandomForest from Assign2.java to generate new landscape.
    // Then new cell values are assign accordingly
    public void newLandscape(){
        int[][] landscapeData = new int [cells.length][];
        for (int i=0; i<cells.length; i++) {
            landscapeData[i] = new int[cells.length];
            for (int j=0; j<cells.length; j++) 
                landscapeData[i][j] = 0;
        }
        
        Assign2.createRandomForest(landscapeData, cells.length, .7, .4);
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells.length; j++){
                cells[i][j] = new Cell(landscapeData[i][j]);
            }
        }
        numTreesToIgnite = 3;
        igniteX = new int [3];
	igniteY = new int [3];
	igniteX[0]=0;   igniteY[0]=1;
	igniteX[1]=1;   igniteY[1]=3;
	igniteX[2]=4;   igniteY[2]=2;
    }

  protected void simulateForestFire()
  {  
    //Print Statements used for debugging/visualization
    //toString();
    //System.out.println();

    //Update the burning trees
    updateForest();
    //make the trees to ignite burning
    igniteTrees(numTreesToIgnite, igniteX, igniteY);

    //find the trees to ignite in next step
    int[][] igniteXY = getIgniteXY();
    igniteX = igniteXY[0];
    igniteY = igniteXY[1];
    numTreesToIgnite = igniteX.length;
    //Need to update twice in case type-2 tree just started burning

  }

  private void updateForest() 
  {
    //Update each element of the matrix
    for(int i=0; i <cells.length; i++){
      for(int j=0; j<cells.length; j++){
        cells[i][j].update();
      }
    }
  }

  private void igniteTrees(int numTreesToIgnite, int[] igniteX, int[] igniteY)
  {
    //ignite specific cells
    for(int i=0; i<numTreesToIgnite; i++){
      cells[igniteX[i]][igniteY[i]].ignite();
    }
  }

  private int[][] getIgniteXY ()
  {
    int numTreesToignite = 0;
    int[] igniteX = new int[cells.length *cells.length];
    int[] igniteY = new int[cells.length *cells.length];

    //start by assigning a value outside the bounds of the matrix
    for(int i=0; i<igniteX.length; i++){
        igniteX[i]=igniteY[i]=-1;
    }
    
    //scan the 2D-array, and check the neighbors of every tree
    //If neighbors meet conditions to be ignited, mark them for ignition
    for(int i=0; i<cells.length; i++){
        for(int j=0; j<cells.length; j++){
            if(cells[i][j].hasStandingTree())
            {
                if(i==j && i == 0)
                {
                    //Check top left corner
                    if(cells[i][j].TreeType(1))
                    {
                        if(cells[i+1][j].hasBurningTree() || cells[i][j+1].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                    else{
                        if(cells[i+1][j].hasBurningTree() && cells[i][j+1].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }

                    }
                }
                else if(i==0 && j==(cells.length-1))
                {
                    //Check top right corner
                    if(cells[i][j].TreeType(1))
                    {
                        if(cells[i+1][j].hasBurningTree() || cells[i][j-1].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                    else{
                        if(cells[i+1][j].hasBurningTree() && cells[i][j-1].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                }
                else if(j==0 && i==(cells.length-1))
                {
                    //Check bottom left corner
                    if(cells[i][j].TreeType(1))
                    {
                        if(cells[i-1][j].hasBurningTree() || cells[i][j+1].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                    else{
                        if(cells[i-1][j].hasBurningTree() && cells[i][j+1].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;

                        }
                    }
                }
                else if(i==(cells.length-1) && j==(cells.length-1))
                {
                    //Check bottom right corner
                    if(cells[i][j].TreeType(1))
                    {
                        if(cells[i-1][j].hasBurningTree() || cells[i][j-1].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                    else{
                        if(cells[i-1][j].hasBurningTree() && cells[i][j-1].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;

                        }
                    }
                }
                else if(i==0)
                {
                    //Check top row (not corners)
                    if(cells[i][j].TreeType(1))
                    {
                        if(cells[i+1][j].hasBurningTree() || cells[i][j+1].hasBurningTree() || cells[i][j-1].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                    else{
                        if( (cells[i+1][j].hasBurningTree() && cells[i][j+1].hasBurningTree()) ||
                            (cells[i+1][j].hasBurningTree() && cells[i][j-1].hasBurningTree()) ||
                            (cells[i][j+1].hasBurningTree() && cells[i][j-1].hasBurningTree()))
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                }
                else if(j==0)
                {
                    //Check left-hand column (not corners)
                    if(cells[i][j].TreeType(1))
                    {
                        if(cells[i+1][j].hasBurningTree() || cells[i][j+1].hasBurningTree() || cells[i-1][j].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                    else{
                        if( (cells[i+1][j].hasBurningTree() && cells[i][j+1].hasBurningTree()) ||
                            (cells[i+1][j].hasBurningTree() && cells[i-1][j].hasBurningTree()) ||
                            (cells[i][j+1].hasBurningTree() && cells[i-1][j].hasBurningTree()))
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                }
                else if(i==(cells.length-1))
                {
                    //Check bottom row (not corners)
                    if(cells[i][j].TreeType(1))
                    {
                        if(cells[i-1][j].hasBurningTree() || cells[i][j+1].hasBurningTree() || cells[i][j-1].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                    else{
                        if( (cells[i-1][j].hasBurningTree() && cells[i][j+1].hasBurningTree()) ||
                            (cells[i-1][j].hasBurningTree() && cells[i][j-1].hasBurningTree()) ||
                            (cells[i][j+1].hasBurningTree() && cells[i][j-1].hasBurningTree()))
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                }    
                else if(j==(cells.length-1))
                {
                    //Check right-hand column (not corners)
                    if(cells[i][j].TreeType(1))
                    {
                        if(cells[i+1][j].hasBurningTree() || cells[i][j-1].hasBurningTree() || cells[i-1][j].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                    else{
                        if( (cells[i+1][j].hasBurningTree() && cells[i][j-1].hasBurningTree()) ||
                            (cells[i-1][j].hasBurningTree() && cells[i][j-1].hasBurningTree()) ||
                            (cells[i+1][j].hasBurningTree() && cells[i-1][j].hasBurningTree()))
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                }
                else{
                    //New Ignitions for normal cell (four neighbors)
                    if(cells[i][j].TreeType(1))
                    {
                        if(cells[i+1][j].hasBurningTree() || cells[i-1][j].hasBurningTree() ||cells[i][j+1].hasBurningTree() || cells[i][j-1].hasBurningTree())
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                    else{
			//Check all combinations of 2 adjacent, burning trees
                        if( (cells[i+1][j].hasBurningTree() && cells[i-1][j].hasBurningTree()) ||
                            (cells[i][j+1].hasBurningTree() && cells[i][j-1].hasBurningTree()) ||
                            (cells[i+1][j].hasBurningTree() && cells[i][j-1].hasBurningTree()) ||
                            (cells[i+1][j].hasBurningTree() && cells[i][j+1].hasBurningTree()) ||
                            (cells[i-1][j].hasBurningTree() && cells[i][j-1].hasBurningTree()) ||
                            (cells[i-1][j].hasBurningTree() && cells[i][j+1].hasBurningTree()))
                        {
                            igniteX[j+(cells.length*i)] = i;
                            igniteY[j+(cells.length*i)] = j;
                            numTreesToignite++;
                        }
                    }
                }
            }
        }
    }


    //2D array used to hold coordinates of trees to ignite
    int[][] igniteXY = new int[2][numTreesToignite];
    int j=0;

    for(int i=0; i<igniteX.length; i++)
    {
	//Take coordinates of trees marked for ignition and add to new array
        if(igniteX[i] > -1  && igniteY[i] > -1)
        {
            igniteXY[0][j] = igniteX[i];
            igniteXY[1][j] = igniteY[i];
            j++;
        }
    }
    return igniteXY;
}

    //Scan through the array and find # of trees of a type burnt down
    int getNumTreesBurned(int treeType)
    {
        int numTreesBurnt=0;
        //Scan the 2D array
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells.length; j++){
                if(cells[i][j].hasBurntTree() && cells[i][j].TreeType(treeType))
                    numTreesBurnt++;
            }
        }
        return numTreesBurnt;
    }
    
    //Scan through the array and find the # of currently burning trees
    int getNumTreesBurning(int treeType)
    {
        int numTreesBurnt=0;
        //Scan the 2D array
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells.length; j++){
                if(cells[i][j].hasBurningTree() && cells[i][j].TreeType(treeType))
                    numTreesBurnt++;
            }
        }
        return numTreesBurnt;
    }
    
    //Get number of live trees in the simulation
    int getNumTreesStanding(int treeType)
    {
        int numTreesBurnt=0;
        //Scan the 2D array
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells.length; j++){
                if(cells[i][j].hasStandingTree() && cells[i][j].TreeType(treeType))
                    numTreesBurnt++;
            }
        }
        return numTreesBurnt;
    }
    
    // Used to update JLabels used for providing statistics
    void updateLabels(JLabel n1stand, JLabel n1burning, JLabel n1burnt, JLabel n2stand, JLabel n2burning, JLabel n2burnt) 
    {
        n1stand.setText("Number of Type 1 Trees Standing = "+getNumTreesStanding(1)+" ");
        n1burning.setText("Number of Type 1 Trees Burning = "+getNumTreesBurning(1)+" ");
        n1burnt.setText("Number of Type 1 Trees Burned = "+getNumTreesBurned(1)+" ");
        n2stand.setText("Number of Type 2 Trees Standing = "+getNumTreesStanding(2)+" ");
        n2burning.setText("Number of Type 2 Trees Burning = "+getNumTreesBurning(2)+" ");
        n2burnt.setText("Number of Type 2 Trees Burned = "+getNumTreesBurned(2)+" ");
    }
    
    //Show if a given x-y coordinate is in bounds of the matrix
    //This was made after the mess of if-statements above. Whoops
    private boolean isInBounds(int x, int y){
	return ( (x>=0 && x<cells.length) && (y>=0 && y<cells.length));
    }
    
    // Does a DFS for a 2D boolean matrix.
    // It only considers the 4 neighbors as adjacent cells (no diagonals)
    void DFS(int row, int col, int treeType, boolean visited[][])
    {
        // These arrays are used to get row and column numbers
        // of 4 neighbors of a given cell
        int rowNbr[] = new int[] {-1, 0, 1, 0};
        int colNbr[] = new int[] {0, 1, 0, -1};
 
        // Mark this cell as visited
        visited[row][col] = true;
 
        // Recursive check for all connected neighbours
        for (int k = 0; k < 4; ++k){
            int nbrX = row + rowNbr[k];
            int nbrY = col + colNbr[k];
            if (isInBounds(nbrX, nbrY) && cells[row][col].TreeType(treeType) && !visited[nbrX][nbrY])
                DFS(nbrX, nbrY, treeType, visited);
        }
    }
 
    // The function that returns count of clusters of
    // a given tree type. Uses a boolen matrix to not repeat counts
    int getNumClusters(int treeType)
    {
        // Make a bool array to mark visited cells.
        // Initially all cells are unvisited
        boolean visited[][] =new boolean[cells.length][cells.length];
 
        // Initialize count as 0 and traverse through the all cells
        int count = 0;
        for (int i = 0; i < cells.length; ++i)
            for (int j = 0; j < cells.length; ++j)
                if (cells[i][j].TreeType(treeType) && !visited[i][j]) 
                {
                    // If a cell with  value false is not visited
                    // Add one to count if not visited and of right tree type
                    DFS(i, j, treeType, visited);
                    ++count;
                }
 
        return count;
    }  

  //Print out each element of the 2D array
  public String toString()
  {
    for(int i=0; i<cells.length; i++){
      for(int j=0; j<cells.length; j++){
        System.out.print(cells[i][j]+" ");
      }
      System.out.println();
    }
    return "";
  }

}
