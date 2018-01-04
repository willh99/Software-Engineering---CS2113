/**
 * This is the interface that an offense strategy
 * must implement
 *
 */
public interface Offense {

    /**
     * <code>getInitialPosition</code> method provides the initial 
     * position of the Pyromaniac
     *
     * @return a <code>PyroMove</code> object which will hold information
     *         about the Pyromaniac's initial position.
     *         This position MUST be x=0, y=0
     *         Refer to PyroMove Class for more information on how to 
     *         return
     */
    public PyroMove getInitialPosition ();

    /**
     * <code>move</code> method provides the move decision  
     * of the Pyromaniac
     *
     * @return a <code>PyroMove</code> object which will hold information
     *         about the Pyromaniac's move.
     *         Refer to PyroMove Class for more information
     */
    public PyroMove move ();

}
