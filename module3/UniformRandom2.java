public class UniformRandom2 {

  // "Global" variable - the see, set to some
  // aritrary non-zero value.
  static long r_seed = 12345678L;

  // Basic Lehmer generator - constants
  static final long m = 2147483647L;
  static final long a = 48271L;
  static final long q = 44488L;
  static final long r = 3399L;

  // Basic Lehmer generator - uniform[0,1]
  public static double uniform()
  {
    long hi = r_seed/q;
    long lo = r_seed - q * hi;
    long t = a * lo - r * hi;
    if(t > 0)
      r_seed = t;
    else
      r_seed = t+m;
    return( (double) r_seed/(double) m);
  }

  //Set the seed to any given value
  public static void setSeed (long value)
  {
    r_seed = value;
  }

  public static double cArea(double radius)
  {
    return (radius*radius);
  }

  public static void main (String[] argv)
  {
    // Let's test the generator again. The mean should be 0.5
    setSeed (13579);
    double radius = 0, area=0;
    for(int i=1; i<=10000; i++){
      radius = uniform();
      area += cArea(radius);
    }

    System.out.println("Average Area of 10000 smaples: "+ area/10000);
  }
}
