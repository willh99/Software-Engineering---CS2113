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
