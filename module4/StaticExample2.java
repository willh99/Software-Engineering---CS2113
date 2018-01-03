public class StaticExample2 {

    static double x;                      // Static data

    static void printx ()                 // Static method
    {
        System.out.println ("x = " + x);
    }

    double y;                             // Non-static data

    void printy ()                        // Non-static method
    {
        System.out.println ("y = " + y);
    }

    public static void main (String[] argv)
    {
        x = 5.34;
        printx ();

        y = 9.67;   
        printy ();  
    }

}
