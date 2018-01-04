public class DoubleExample {

  public static void main (String[] argv)
  {
    int n=15;
    double[] A = new double [n];

    for(int i=0; i<n; i++) {
      if(i==0)
        A[i]=0;
      else{
        A[i] = 1.0/(i*i);
        A[i] = A[i]+A[i-1];
      }
      System.out.println (A[i]);
    }
  }
}
