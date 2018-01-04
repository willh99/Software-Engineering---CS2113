import java.util.*;

/**
 * The <code>StrangeDataStructure</code> interface specifies the
 * behavior (interface) of a data structure. This data structure
 * accepts integers (via the <code>addElement()</code> method
 * and stores them internally.
 * Any object that implements interface <code>StrangeDataStructure</code> 
 * must implement the methods below. 
 * 
 */

public interface StrangeDataStructure {

    /**
     * <code>addElement</code> should take the argument
     * and store that internally. You can use whatever data
     * structure you like for the internal storage of these integers.
     *
     * @param k an <code>int</code> value
     */

    public void addElement (int k);



    /**
     * Method <code>getSumOfElements</code> should return the sum
     * of the integers stored.
     *
     * @return an <code>int</code> value
     */

    public int getSumOfElements ();



    /**
     * Method <code>oddElements</code> should return an
     * <code>Enumeration</code> of ONLY the odd integers stored.
     *
     * @return an <code>Enumeration</code> value
     */
    public Enumeration oddElements ();


    /**
     * Method <code>evenElements</code> should return an
     * <code>Enumeration</code> of ONLY the odd integers stored.
     *
     * @return an <code>Enumeration</code> value
     */
    public Enumeration evenElements ();

}
