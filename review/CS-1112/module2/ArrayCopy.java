import java.util.*;

public class ArrayCopy {

    public static void main (String[] argv)
    {
        int[][] A = {
            {1},
            {2, 1},
            {3, 2, 1},
            {4, 3, 2, 1},
            {5, 4, 3, 2, 1}
        };
        print (A);

        int[][] B = copy (A);
        print (B);
    }

    static void print (int[][] X)
    {
        for (int i=0; i<X.length; i++) {
            for (int j=0; j < X[i].length; j++) {
                System.out.print (" " + X[i][j]);
            }
            System.out.println ();
        }
    }
    
    // INSERT YOUR CODE HERE.
    static int[][] copy(int[][] a)
    {
      int[][] b = new int[a.length][];      

      for(int i=0; i<a.length; i++){

	b[i] = new int [a[i].length];
	for(int j=0; j<a[i].length; j++){
	  b[i][j] = a[i][j];
	}
      }
      return b;
    }
    
}
