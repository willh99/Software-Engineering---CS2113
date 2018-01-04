public class NestedLoopExample {

  public static void main (String[] argv)
  {
    int n = 5;
    double sum = 0;
    for(int k=0; k<=n; k++) 
    {
      double power = 1;
      for(int i=0; i<k; i++)
      {
        power *= 0.5;
        System.out.println("i = " + i);
      }
      sum += power;
      System.out.println("sum = "+ sum +" k = "+ k +" power = "+ power);
    }
  }

}
