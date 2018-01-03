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


