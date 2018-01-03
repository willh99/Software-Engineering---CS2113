import java.util.*;

public class StrangeDataStructureImpl implements StrangeDataStructure{

    private ArrayList<Integer> array;
    
    // Zero argument constructor for StrangeDataStructureImp1
    // Allocates memory for a new integer ArrayList data structure
    StrangeDataStructureImpl() {
        array = new ArrayList<Integer>(1);
    }
    
    // Add a new Integer to the ArrayList
    public void addElement(int k) {
        array.add(k);
    }

    // Loop through entire ArrayList and get 
    // the sum of all elements
    public int getSumOfElements() {
        int sum=0;
        for(int i=0; i<array.size(); i++){
            sum += array.get(i);
        }
        return sum;
    }

    // Create a new ArrayList and fill it with odd elements
    // from the maian ArrayList. Then pass the odd ArrayList
    // object to an enumeration. 
    public Enumeration oddElements() {
        Enumeration<Integer> e;
        ArrayList<Integer> a = new ArrayList<Integer>(1);

        for(int i=1; i<array.size(); i=i+2){
            a.add(array.get(i));
        }
        e = Collections.enumeration(a);
        return e;
    }

    // Create a new ArrayList and fill it with even elements
    // from the maian ArrayList. Then pass the even ArrayList
    // object to an enumeration. 
    public Enumeration evenElements() {
        Enumeration<Integer> e;
        ArrayList<Integer> a = new ArrayList<Integer>(1);

        for(int i=0; i<array.size(); i=i+2){
            a.add(array.get(i));
        }
        e = Collections.enumeration(a);
        return e;
    }
    
}
