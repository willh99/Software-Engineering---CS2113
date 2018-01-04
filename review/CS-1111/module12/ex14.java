public class ex14 {

  public static void main (String[] argv)
  {
    int a=5, b=6;
    swap (a,b);
    System.out.println (a);
    System.out.println (b);

    int[] A = {7,8};
    swapArray (A);
    System.out.println (A[0] + " " + A[1]);
  }

  static void swap (int x, int y)
  {
    int temp = x;
    x = y;
    y = temp;
  }

  static void swapArray (int[] B)
  {
    int temp = B[0];
    B[0] = B[1];
    B[1] = temp;
  }
}
