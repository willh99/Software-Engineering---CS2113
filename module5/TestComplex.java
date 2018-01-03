import java.lang.Math;

class Complex {

  static double a=0, b=0;

  public Complex (double x, double y)
  {
    a=x; b=y;
  }

  public Complex (double x)
  {
    a=x; b=0;
  }

  public Complex () 
  {
    a=0; b=0;
  }

  public double getRealPart() { return a; }

  public double getMagnitude()
  {
    double mag = Math.sqrt(a*a + b*b);
    return mag;
  }

  public Complex add (Complex cTemp)
  {
    Complex temp = new Complex();
    double c, d;

    c = a+cTemp.a;
    d = b+cTemp.b;
    temp.a=c;
    temp.b=d;
    return temp;
  }

  public Complex multiply (Complex cTemp)
  {
    Complex temp = new Complex();
    double c, d;

    c = (a*cTemp.a) - (b*cTemp.b);
    d = (a*cTemp.b) + (b*cTemp.a);
    temp.a=c;
    temp.b=d; 
    return temp;
  }

  public String toString(){
    return "("+ a +" ,"+ b +");";
  }

}

public class TestComplex {

  public static void main (String[] argv)
  {
    Complex c1 = new Complex();
    Complex c2 = new Complex(4.35, 9.002);
    Complex c3 = new Complex (8.93);

    Complex c4 = c1.add(c2);
    Complex c5 = c2.multiply(c3);
    Complex c6 = new Complex (4.35, 9.002);
    System.out.println ("c6 = "+ c6);

    System.out.println ("|c1| = " + c1.getRealPart() + "\n" +
			"c1's imag part = "+ c1.getMagnitude());
    System.out.println ("|c1| = "+ c1.getMagnitude());

    System.out.println ("|c2| = " + c2.getRealPart() + "\n" +
			"c2's imag part = "+ c2.getMagnitude());
    System.out.println ("|c2| = "+ c2.getMagnitude());

    double magSum = c1.getMagnitude() + c2.getMagnitude();
    System.out.println ("|c1| + |c2| = "+ magSum);
  }

}
