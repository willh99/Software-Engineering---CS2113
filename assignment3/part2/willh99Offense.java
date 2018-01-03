public class willh99Offense implements Offense {

    private int Xpos=0;
    private int Ypos=0;
    private static boolean rightToLeft = true;

    // Returns an instance of PyroMove with Coordinates x=0
    // y=0
    public PyroMove getInitialPosition()
    {
	PyroMove PyM = new PyroMove(0,0,false);
	return PyM;

    }

    // Returns an instnace Pyro move with coordinates of where to move
    // and whether or not to ignite a tree
    public PyroMove move()
    {
	// 2D array holding pairs of coordinate relative to the current
	// possition of the pyro
	int[][] posChange = { {1, 0, -1, 0}, {0, 1, 0, -1} };
	int choice=-1;

	// Always ignite an unburnt tree
	if(Game.getTreeStatus(Xpos,Ypos) == 1){
	    PyroMove PyM = new PyroMove(Xpos, Ypos, true);
	    return PyM;
	}
	// Use this loop if the rightToLeft is set to true
	if(rightToLeft){
	    for(int i=0; i<4; i++)
	    {
		// check position is in bounds, unoccupied and if there is a tree next door
		if( InBounds(Xpos+posChange[0][i],Ypos+posChange[1][i]) ){
		    if(Game.getOccupancyStatus(Xpos+posChange[0][i],Ypos+posChange[1][i]) == 0){

			// Preference for neighboring cells with unburnt trees
			if(Game.getTreeStatus(Xpos+posChange[0][i], Ypos+posChange[1][i]) == 1)
			    choice = i;
			else if(choice < 0)
			    choice = i;
		    }
	        }
	    }
	    // Change direction if at the opposite corner of the array
	    if(Xpos==7 && Ypos==7)
		rightToLeft=false;
	}
	else{
	    for(int i=3; i>=0; i--)
	    {
		// check position is in bounds, unoccupied and if there is a tree next door
		if( InBounds(Xpos+posChange[0][i],Ypos+posChange[1][i]) ){
		    if(Game.getOccupancyStatus(Xpos+posChange[0][i],Ypos+posChange[1][i]) == 0){
			
			// Preference for neighboring cells with unburnt trees
			if(Game.getTreeStatus(Xpos+posChange[0][i], Ypos+posChange[1][i]) == 1)
			    choice = i;
			else if(choice < 0)
			    choice = i;
		    }
	        }
	    }
	    // Change direct if in the top left corner
	    if(Xpos==0 && Ypos==0)
		rightToLeft=true;
	}
	// Set movement coordinate depending on neighboring cell scan above
	if(choice >= 0){
	    Xpos = Xpos + posChange[0][choice];
	    Ypos = Ypos + posChange[1][choice];
	    PyroMove PyM = new PyroMove(Xpos, Ypos, false);
	    return PyM;
	}
	return null;	
    }

    // Method to check if value is in bounds of the game
    private boolean InBounds(int x, int y)
    {
	return ( (x>=0 && x<8) && (y>=0 && y<8) );
    }

}
