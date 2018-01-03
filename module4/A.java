class A {
  static int w = 1;
  private static int x = 2;
  public static int y = 3;
  protected static int z = 4;

  protected static void add1 ()
  {
    w++;
  }

  public static void add1_2 ()
  {
    w++;
  }

  public static void main (String[] argv)
  {
    System.out.println (" w = " + w);
    System.out.println (" x = " + x); 
    System.out.println (" y = " + y);
    System.out.println (" z = " + z);
  }

}

class B extends A {
  
  public static void call_add1()
  {
    add1();
  }

  protected static void call_add1_2 ()
  {
    add1_2();
  }

  public static void main (String[] argv)
  {
    call_add1();
    call_add1_2();
  }
}
