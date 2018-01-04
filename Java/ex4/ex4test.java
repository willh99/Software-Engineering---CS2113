public class ex4test {

  static void test (String testName, int numTrials, int listSize, int moveDistance, boolean isUniform)
  {
    Ex4LinkedList list = new Ex4LinkedList ();
    for (int i=1; i<=listSize; i++)
      list.insertAtEnd (""+i);

    /* Now, repeat over a number of trials and estimate average. */
    double totalDist = 0;
    for (int n=0; n<numTrials; n++) {

      /* Pick a random element in the list to access: */
      int data = 0;
      if (isUniform == true) {
        data = (int) discrete_uniform (1, listSize);
      }
      else {
        data = (int) reverseZipf (listSize);
      }

      /* Get its position in the list: */
      int position = list.findPositionAndMove (""+data, moveDistance);

      /* Accumulate in sum: */
      totalDist += position;
    }

    list.clearList ();

    double avgDist = totalDist / numTrials;

    System.out.println (testName);
    System.out.println ("  Listsize=" + listSize + " numTrials=" + numTrials
                        + " moveDistance=" + moveDistance + " avgSearchDistance="
                        + avgDist);
  }
  
  public static void main (String[] argv)
  {
    // Simple tests.
    test ("Test 1: 1 element", 1, 1, 0, true);
    test ("Test 2: 5 elements", 1, 5, 0, true);
    test ("Test 3: 5 elements", 1, 5, 5, true);
    
    // Statistics.
    test ("Test 4: 10 elements", 10000, 10, 0, true);
    test ("Test 5: 10 elements, moveDist=1", 10000, 10, 1, true);
    test ("Test 6: 10 elements, moveDist=10", 10000, 10, 10, true);
    test ("Test 7: 10 elements, zipf", 10000, 10, 0, false);
    test ("Test 8: 10 elements, zipf, moveDist=1", 10000, 10, 1, false);
    test ("Test 9: 10 elements, zipf, moveDist=10", 10000, 10, 10, false);
  }




  ////////////////////////////////////////////////////////////////////////
  // For convenience, the same uniform generator.
  static long r_seed = 12345678L;
  static long m = 2147483647;
  static long a = 48271;
  static long q = 44488;
  static long r = 3399;

  static double uniform ()
  {
    long t, lo, hi;

    hi = r_seed / q;
    lo = r_seed - q * hi;
    t = a * lo - r * hi;
    if (t > 0)
      r_seed = t;
    else
      r_seed = t + m;
    return ( (double) r_seed / (double) m );
  }
  
  static long discrete_uniform (long a, long b)
  {
    if (b > a) {
      double x = uniform ();
      long c = ( a + (long) Math.floor((b-a+1)*x) );
      return c;
    }
    else if (a == b) return a;
    else { 
      System.out.println ("ERROR in uniform.discrete_uniform(): a="+a+" b="+b);
      return 0;
    }
  }

  static int generateFromPmf (double[] pmf, int length)
  {
    int lastIndex, i;
    double u, sum;

    if ( ( pmf == null) || (length <= 1) ){
      System.out.println ("ERROR: in uniform.generateFromPmf(): pmf null or zero-length");
      return 0;
    }
    
    lastIndex = length - 1;
    u = uniform();
    i = 0;
    sum = pmf[0];
    while ( (i < lastIndex) && (u > sum) ) {
      i++;
      sum += pmf[i];
    }
    return i;
  }
  

  static int reverseZipf (int size) 
  {
    double[] pmf;
    double sum;
    int index, i;
    
    pmf = new double [size+1];
    pmf[0] = 0;
    sum = 0;
    for (i=1; i<=size; i++) {
      pmf[i] = 1.0 / (double) (size-i+1);
      sum += pmf[i];
    }
    for (i=1; i<=size; i++) {
      pmf[i] = pmf[i] / sum;
    }
    index = generateFromPmf (pmf, size+1);
    if (index == 0)
      index = 1;
    return index;
  }

}

