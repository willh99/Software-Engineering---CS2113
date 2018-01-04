class ObjX {

    int i;

/*    public String toString ()
    {
        String s = "i=" + i;
        return s;
    }*/

} 


public class DynamicExample11 {

    public static void main (String[] argv)
    {
        // Make an ObjX instance and assign something to its variable i.
        ObjX x = new ObjX ();
        x.i = 5;

        // Note how "x" is directly given to println()
        System.out.println (x);

        // Another use:
        String outputStr = "Object x: " + x;
        System.out.println (outputStr);
    }

}
