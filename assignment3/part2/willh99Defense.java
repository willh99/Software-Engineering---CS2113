public class willh99Defense implements Defense {

    private int Xpos1=6;
    private int Ypos1=7;
    private int Xpos2=7;
    private int Ypos2=7;
    private boolean leftToRight;

    // Return instance of FighterMove with inital coordinates of
    // fire fighter. Allows for the creation of two fire fighters
    public FighterMove getInitialPosition(int i)
    {
	if(i==1){
	    FighterMove FtM = new FighterMove(6,7,false);
	    return FtM;
	}
	else if(i==2){
	    FighterMove FtM = new FighterMove(7,7,false);
	    return FtM;
	}
	return null;
    }

    // Returns an instnace FighterMove with coordinates of where to move
    // and whether or not to douse a tree
    public FighterMove move(int i)
    {
	// 2D array holding pairs of coordinate relative to the current
	// possition of the pyro
	int[][] posChange = { {1, 0, -1, 0}, {0, 1, 0, -1} };
	int choice=-1;

	// Different movement patterns for different fire fighters
	if(i==1){
	    // Douse a tree if it is unburnt, ignited, or burning
	    if( Game.getTreeStatus(Xpos1,Ypos1) == 1 ||
		Game.getTreeStatus(Xpos1,Ypos1) == -1 ||
		Game.getTreeStatus(Xpos1,Ypos1) == -2  ){
		FighterMove FtM = new FighterMove(Xpos1, Ypos1, true);
		return FtM;
	    }
	    if(leftToRight){
		for(int j=0; j<4; j++)
		{
		    if( InBounds(Xpos1+posChange[0][j],Ypos1+posChange[1][j]) ){
			
			// Take the pyro if possible
			if(Game.getOccupancyStatus(Xpos1+posChange[0][j],Ypos1+posChange[1][j]) == 3){
			    choice = j;
			    break;
			}
			if(Game.getOccupancyStatus(Xpos1+posChange[0][j],Ypos1+posChange[1][j]) == 0){

			    // Preference for cells with non-burnt trees
			    if(Game.getTreeStatus(Xpos1+posChange[0][j], Ypos1+posChange[1][j]) == 1 || 
				Game.getTreeStatus(Xpos1+posChange[0][j], Ypos1+posChange[1][j]) == -1 )
				choice = j;
			    else if(choice < 0)
				choice = j;
			}
	    	    }
		}
		// Reverse direction if at the corner of the game
		if(Xpos1==7 && Ypos1==7)
		    leftToRight=false;
	    }
	    else{
		for(int j=3; j>=0; j--)
		{
		    if( InBounds(Xpos1+posChange[0][j],Ypos1+posChange[1][j]) ){

			// Take the pyro if possible
			if(Game.getOccupancyStatus(Xpos1+posChange[0][j],Ypos1+posChange[1][j]) == 3){
			    choice = j;
			    break;
			}
			else if(Game.getOccupancyStatus(Xpos1+posChange[0][j],Ypos1+posChange[1][j]) == 0){
			    
			    // Preference for cells with unburnt trees
			    if(Game.getTreeStatus(Xpos1+posChange[0][j], Ypos1+posChange[1][j]) == 1 ||
				Game.getTreeStatus(Xpos1+posChange[0][j], Ypos1+posChange[1][j]) == -1 )
				choice = j;
			    else if(choice < 0)
				choice = j;
			}
		    }
		}
		// Change direction when at corner
		if(Xpos1==0 && Ypos1==0)
		    leftToRight=true;
	    }
	    // Return coordinate based on chosen cell
	    if(choice >= 0){
		Xpos1 = Xpos1 + posChange[0][choice];
		Ypos1 = Ypos1 + posChange[1][choice];
		FighterMove FtM = new FighterMove(Xpos1, Ypos1, false);
		return FtM;
	    }
	}
	if(i==2){
	    // Douse a tree if possible
	    if(Game.getTreeStatus(Xpos2,Ypos2) == 1||
		Game.getTreeStatus(Xpos2,Ypos2) == -1 ||
		Game.getTreeStatus(Xpos2,Ypos2) == -2 ){
		FighterMove FtM = new FighterMove(Xpos2, Ypos2, true);
		return FtM;
	    }
	    if(leftToRight){
		for(int j=0; j<4; j++)
		{
		    if( InBounds(Xpos2+posChange[0][j],Ypos2+posChange[1][j]) ){
			// Take the pyro if possible
			if(Game.getOccupancyStatus(Xpos2+posChange[0][j],Ypos2+posChange[1][j]) == 3){
			    choice = j;
			    break;
			}

			if(Game.getOccupancyStatus(Xpos2+posChange[0][j],Ypos2+posChange[1][j]) == 0){

			    // Prefer cells with unburnt or ignited trees
			    if(Game.getTreeStatus(Xpos2+posChange[0][j], Ypos2+posChange[1][j]) == 1 ||
				Game.getTreeStatus(Xpos2+posChange[0][j], Ypos2+posChange[1][j]) == -1 )
				choice = j;
			    else if(choice < 0)
				choice = j;
			}
	    	    }
		}
		if(Xpos2==7 && Ypos2==7)
		    leftToRight=false;
	    }
	    else{
		for(int j=3; j>=0; j--)
		{
		    if( InBounds(Xpos2+posChange[0][j],Ypos2+posChange[1][j]) ){
			// Take the pyro if possible
			if(Game.getOccupancyStatus(Xpos2+posChange[0][j],Ypos2+posChange[1][j]) == 3){
			    choice = j;
			    break;
			}
	
			else if(Game.getOccupancyStatus(Xpos2+posChange[0][j],Ypos2+posChange[1][j]) == 0){

			    // Prefer cells with unburnt or ignited trees
			    if(Game.getTreeStatus(Xpos2+posChange[0][j], Ypos2+posChange[1][j]) == 1 ||
				Game.getTreeStatus(Xpos2+posChange[0][j], Ypos2+posChange[1][j]) == -1 )
				choice = j;
			    else if(choice < 0)
				choice = j;
			}
		    }
		}
		// Reverse direction at corner
		if(Xpos2==0 && Ypos2==0)
		    leftToRight=true;
	    }
	    // Return coordinates based on choosen cell
	    if(choice >= 0){
		Xpos2 = Xpos2 + posChange[0][choice];
		Ypos2 = Ypos2 + posChange[1][choice];
		FighterMove FtM = new FighterMove(Xpos2, Ypos2, false);
		return FtM;
	    }
	}
	return null;	

    }

    // Method to check if value is in bounds of the game
    private boolean InBounds(int x, int y) {
	return ( (x>=0 && x<8) && (y>=0 && y<8) );
    }

}
