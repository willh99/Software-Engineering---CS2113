
public class PointerCopyProblem {

    public static void main (String[] argv)
    {
        // Make a 3 x 2 array.
        int[][] A = {
            {1, 2},
            {3, 4},
            {5, 6}
        };
        
        // To B, we assign space for 3 pointers:
        int[][] B = new int [3][2];

        // Now do a pointer copy.
        for (int i=0; i<A.length; i++) {
	  for (int j=0; j<A[0].length; j++){
            B[i][j] = A[i][j];
	  }
        }

        print (A, B);
        
        // Make a change in B.
        B[0][0] = 9;
        
        print (A, B);
    }

    static void print (int[][] A, int[][] B)
    {
        System.out.println ("\nArray A: ");
        for (int i=0; i<A.length; i++) {
            for (int j=0; j<A[i].length; j++) {
                System.out.print (" " + A[i][j]);
            }
            System.out.println ();
        }
        
        System.out.println ("Array B: ");
        for (int i=0; i<B.length; i++) {
            for (int j=0; j<B[i].length; j++) {
                System.out.print (" " + B[i][j]);
            }
            System.out.println ();
        }
    }
    
}
