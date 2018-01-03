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




