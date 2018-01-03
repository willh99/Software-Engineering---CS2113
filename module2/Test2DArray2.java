import java.util.*;
import java.util.Scanner;

public class Test2DArray2 {

  public static void main (String[] argv)
  {
    // Size is the numser of rows. sizelp is used to loop
    // the number of spaces need to form an equalatoral triangle
    int size = 0, sizelp = 0;
    int[][] triangle;
    Scanner sc = new Scanner(System.in);
    
    System.out.println ("Enter the number of rows in your Pascal Triangle:");
    size = sc.nextInt();
    sizelp = size-1;

    // There are 'size' rows
    triangle = new int[size][size];

    // Fill in the array. If it is the first or last number in a row set to 0.
    // Otherwise, add the two number 'above it'
    for (int i=0; i<size; i++) { 
      for (int j=0; j<=i; j++) {
        if (j==0 || i==j)
          triangle[i][j] = 1;
        else
          triangle[i][j] =  triangle[i-1][j-1] + triangle[i-1][j];
      }
    }

    // Now print.
    System.out.println ("Pascal's Triangle:");
    for (int i=0; i<size; i++) {
      for (int j=0; j<sizelp; j++)	// Print spaces to form triangle
        System.out.print (" ");
      for (int j=0; j<=i; j++)		// Print actual numbers
        System.out.print (triangle[i][j] + " ");

      System.out.println();
      sizelp--;
    }
  }
}
