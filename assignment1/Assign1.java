class Landscape {

  private Cell[][] cells;

  Landscape(int[][] landscape, int size){
    //initialize cells:
    cells = new Cell[size][size];

    //Allocate new cell for each element in array
    for(int i=0; i<size; i++){
      for(int j=0; j<size; j++){
        cells[i][j] = new Cell(landscape[i][j]);
      }
    }

  }

  void simulateForestFire (int numTreesToIgnite, int[] igniteX, int[] igniteY)
  {
    while(numTreesToIgnite != 0){
      
        //Print Statements used for debugging/visualization
        //System.out.print(toString());
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
    }
    //Need to update twice in case type-2 tree just started burning
    updateForest();
    //System.out.print(toString());
    //System.out.println();
    updateForest();
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

class Cell {
    private Tree tree;
    private int type;
    
    //Constructor for a Cell
    Cell (int treeType){
        if(treeType == 1){
            tree = new Tree();
            type=treeType;
        }else if(treeType == 2){
            tree = new HardyTree();
            type=treeType;
        }
    }

    //Update status of a tree in a cell
    void update() {
        if(tree != null)
            tree.update();
    }

    //Start Burning a tree in a cell
    void ignite() {
        if(tree != null)
            tree.ignite();
    }
    //Check if a Cell has an untouched tree
    boolean hasStandingTree(){
        return (tree != null && tree.isStanding());
    }

    //Check if a cell has a burning tree
    boolean hasBurningTree(){
        return (tree != null && tree.isBurning());
    }

    //Check if a cell contains a burnt tree
    boolean hasBurntTree(){
        return (tree != null && tree.isBurnt());
    }

    //Return the type of tree held in the cell
    boolean TreeType(int typeTree){
        return (tree!=null && typeTree==type);
    }

    //Return String of a Cell
    //Return char 0 if empty or tree string if full
    public String toString() {
        if(tree == null)
            return "0";
        return tree.toString();
    }

}

class Tree {
    int status;
    
    //Update a status of a type-1 tree from ingited to burning
    void update() {
        if(status == 1)
            status = 2;
    }

    //Start burning a tree
    void ignite () {
        if(status == 0)
            status = 1;
    }

    //Check if a tree is not Burnt/Burning
    boolean isStanding(){
        return status==0;
    }

    //Check if a tree is currently Burning
    boolean isBurning(){
        return status==1;
    }

    //Check if a tree has burnt down
    boolean isBurnt(){
        return status==2;
    }

    //Return String of tree depending on status
    public String toString(){
        if(status == 1)
            return "*1";
        else if(status == 2)
            return "x";
        return "1";
    }

}

class HardyTree extends Tree{
    private boolean secondBurning;
    
    //Special update for type-2 tree
    //Require and additional tic to burn down
    void update() {
        if(status == 1){
            if(secondBurning)
                status=2;
            else
                secondBurning = true;
        }
    }

    //Return string of type-2 tree depending on status
    public String toString(){
        if(status == 1)
            return "*2";
        else if(status == 2)
            return "x";
        return "2";
    }
}



class Assign1 {

  // A debug variable - set it to true when debugging so
  // you can print out stuff to screen during execution.
  public static boolean debug = true;

  // This method takes in a landscape (and its size M), and
  // a parameter called "fillDensity" and creates a landscape of
  // trees, by randomly generating trees in various cells.
  // IMPORTANT: DO NOT MODIFY THIS CODE.

  public static void createRandomForest (int[][] landscapeData, 
                                         int M, 
                                         double fillDensity,
                                         double type1Frac)
  {
    // Check fillDensity validity:
    if ( (fillDensity <= 0) || (fillDensity > 1) ) {
      System.out.println ("Assign1.createRandomForest(): fillDensity out of range: "
                          + fillDensity);
    }

    // Initially, clear the landscape:
    for (int i=0; i<M; i++) {
      for (int j=0; j<M; j++)
        landscapeData[i][j] = 0;
    }

    // Now fill in trees randomly:
    for (int i=0; i<M; i++) {
      for (int j=0; j<M; j++)
        if (UniformRandom.uniform () < fillDensity) {
          if (UniformRandom.uniform() < type1Frac)
            landscapeData[i][j] = 1;  // Type-1 tree.
          else
            landscapeData[i][j] = 2;  // Type-2 tree.
        }
    }

  }
  

  // Process a given landscape and identify the number of clusters found.
  // Return this value. DO NOT CHANGE ANY CODE IN THIS METHOD. YOU CAN
  // ADD CODE TO IT IF YOU WISH.

  public static Landscape processLandscapeData (int[][] landscapeData, 
                                      int M, 
                                      int numTreesToIgnite,
                                      int[] igniteX, 
                                      int[] igniteY)
  {
    Landscape L = new Landscape (landscapeData, M);

    if (debug) {
      System.out.println ("BEFORE: ");
      System.out.println (L);
    }

    L.simulateForestFire (numTreesToIgnite, igniteX, igniteY);

    if (debug) {
      System.out.println ("AFTER: ");
      System.out.println (L);
      System.out.println ("Number of type-1 trees burned: " + L.getNumTreesBurned (1));
      System.out.println ("Number of type-2 trees burned: " + L.getNumTreesBurned (2));
      System.out.println ("Number of type-1 clusters: " + L.getNumClusters (1));
    }

    return L;
  }
  

  // Use your main method for testing and estimation.

  public static void main (String[] argv) 
  {

    //////////////////////////////////////////////////////////
    // INSERT YOUR TEST CODE HERE:
    // Example of creating test code:
    int M = 8;
    int[][] landscapeData = new int [M][];
    for (int i=0; i<M; i++) {
      landscapeData[i] = new int[M];
      for (int j=0; j<M; j++) 
        landscapeData[i][j] = 0;
    }
    // Create a random landscape with fillDensity=0.7, type1Frac=0.4:
    createRandomForest (landscapeData, M, 0.7, .4);
    // Burn pattern:
    int numTreesToIgnite = 8;
    int[] igniteX = new int [8];
    int[] igniteY = new int [8];
    igniteX[0]=0;   igniteY[0]=0;
    igniteX[1]=0;   igniteY[1]=1;
    igniteX[2]=0;   igniteY[2]=2;
    igniteX[3]=0;   igniteY[3]=3;
    igniteX[4]=0;   igniteY[4]=4;
    igniteX[5]=0;   igniteY[5]=5;
    igniteX[6]=0;   igniteY[6]=6;
    igniteX[7]=0;   igniteY[7]=7;
    
    // Simulate:
    Landscape L = processLandscapeData (landscapeData, M, numTreesToIgnite, igniteX, igniteY);

    int numType1TreesBurned = L.getNumTreesBurned (1);
    int numType2TreesBurned = L.getNumTreesBurned (2);
    int numType1Clusters = L.getNumClusters (1);

    // INSERT CODE HERE to compute averages ...
    double Type1BurnAvg, Type2BurnAvg, AvgNumClusters;
    double[] filldensity = {0.1, 0.3, 0.5, 0.7, 0.9};
 
    //Run 1000 tests of forest creation to find out Avgerage number of type-1
    //and type-2 trees burnt as well as type-1 clusters. 
    for(int r=0; r<filldensity.length; r++){
        Type1BurnAvg=Type2BurnAvg=AvgNumClusters=0;
        for(int i=0; i<1; i++){
            createRandomForest(landscapeData, M, filldensity[r], .4);
            L= processLandscapeData(landscapeData, M, numTreesToIgnite, igniteX, igniteY);
            Type1BurnAvg += L.getNumTreesBurned(1);
            Type2BurnAvg += L.getNumTreesBurned(2);
            AvgNumClusters += L.getNumClusters(1);
        }

        Type1BurnAvg = Type1BurnAvg/1000;
        Type2BurnAvg = Type2BurnAvg/1000;
        AvgNumClusters = AvgNumClusters/1000;
    
        System.out.println("For Fill Density of: "+filldensity[r]);
        System.out.println("Average Number of Type-1 Trees Burned: "+Type1BurnAvg);
        System.out.println("Average Number of Type-2 Trees Burned: "+Type2BurnAvg);
        System.out.println("Average Number of Type-1 Clusters: "+AvgNumClusters);
        System.out.println();
    }
    
    // Feel free to add as many methods as you need.
    //////////////////////////////////////////////////////////
  }
  
}
