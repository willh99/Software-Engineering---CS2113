public class AnotherForLoopExample {

  public static void main (String[] argv)
  {
    for(int i=1; i<=5; i++) {
      for(int j=i; j>0; j--) {
        System.out.print ("*");
      }
      System.out.print (" ");
      for(int j=1; j<i; j++) {
        System.out.print ("-");
      }
      System.out.println();
    }
  }

}
