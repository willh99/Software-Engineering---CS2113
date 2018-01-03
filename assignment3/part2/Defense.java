/**
 * This is the interface that a defense strategy
 * must implement
 *
 */
public interface Defense {

    /**
     * <code>getInitialPosition</code> method provides the initial 
     * position of a FireFighter
     *
     * @param i an <code>int</code> value determining the Fire 
     *          fighter whose information is requested
     * @return a <code>FighterMove</code> object which will hold information
     *         about the Fire fighter's initial position.
     *         This position MUST be x=6, y=7 for Fire Fighter 1
     *         This position MUST be x=7, y=7 for Fire Fighter 2
     *         Refer to FighterMove Class for more information on how to 
     *         return
     */
    public FighterMove getInitialPosition (int i);

    /**
     * <code>move</code> method provides the move decision
     * for a Fire fighter
     *
     * @param i an <code>int</code> value determining the Fire 
     *          fighter whose information is requested
     * @return a <code>FighterMove</code> object which will hold information
     *         about the Fire fighter's move decision
     *         Refer to FighterMove Class for more information on how to 
     *         return
     */
    public FighterMove move (int i);

}
    
