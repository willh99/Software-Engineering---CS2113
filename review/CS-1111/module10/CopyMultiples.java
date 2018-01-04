public class CopyMultiples {

  public static void main (String[] argv)
  {
    int[] A = {2, 9, 22, 12, 4, 3, 8, 30};
    int[] B = new int [A.length];

    for(int i=0; i<A.length; i++){
      if((A[i]%3 == 0) || (A[i]%4 == 0))
        B[i] = A[i];
      else
        B[i] = 1;
    }

    for(int i=0; i<B.length; i++){
      if(B[i] != 1)
        System.out.print(B[i] + " ");
    }
    System.out.println();
  }

}
