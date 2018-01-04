public class FindSmallestExercise {

  public static void main (String[] argv)
  {
    int i=3, j=4, k=5;
    int a = findSmallest (j, i, k);
    System.out.println (a);
    a = findSmallest (9, 7, 5);
    System.out.println (a);
  }

  static int findSmallest (int a, int b, int c)
  {
    if(a<=b && a<=c)
      return a;
    else if(b<=a && b<=c)
      return b;
    else
      return c;
  }


}
