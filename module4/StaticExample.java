public class StaticExample {

    static double x;                      // Static data
    static int y;    

    static void printx ()                 // Static method
    {
        System.out.println ("x = " + x);
    }

    public static void main (String[] argv)
    {
        x = 5.34;
        y = 55;
        System.out.println(y);
        printx ();
    }

}
