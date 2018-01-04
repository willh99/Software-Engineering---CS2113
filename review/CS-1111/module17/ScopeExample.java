public class ScopeExample {

  static double x=0.5;
  static int y=10;

  public static void main (String[] argv)
  {
    System.out.println(x);
    squareIt();
    System.out.println(x);

    System.out.println(y);
    increment();
    System.out.println(y);
    decrement();
    System.out.println(y);
  }

  static void squareIt ()
  {
    x = x*x;
  }

  static void increment()
  {
    y++;
  }

  static void decrement()
  {
    y--;
  }

}
