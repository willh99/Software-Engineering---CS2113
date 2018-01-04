public class MethodExample {

  public static void main (String[] argv)
  {
    int i=5;
    incrementAndPrint (i);
    System.out.println(i);
    int j=6;
    incrementAndPrint (j);
  }

  static void incrementAndPrint (int k)
  {
    k++;
    System.out.println(k);
  }
}
