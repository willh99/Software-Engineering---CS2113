public class PrintStarsExercise {

  public static void main (String[] argv)
  {
    printStars(5);
    printStars(3);
    printStars(1);
  }

  static void printStars (int n)
  {
    for(int i=0; i<n; i++){
      System.out.print("*");
    }
    System.out.println();
  }
}
