public class Sum {

  public static void main (String[] argv)
  {
    int n=3;
    int S  = sum(n);
    System.out.println ("Sum 1 + ... + "+ n +" = "+ S);

    n = 10;
    S = sum(n);
    System.out.println ("Sum 1 + ... + "+ n +" = "+ S);
  }

  public static int sum (int num)
  {
    if (num > 0)
      return (num + sum(num-1));
    else
      return 0;
  }

}
