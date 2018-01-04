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

  public void print(){
    System.out.println("Printing from class 'Complex'");
  }
}

class extender1 extends Complex {
  
  public extender1 (double x, double y){
    a=x; b=y;
  }

  public extender1 (double x){
    a=x; b=0;
  }

  public extender1 () {
    a=b=0;
  }

  public void print(){ 
    System.out.println("Printing from class 'extender1'");
  }
}

class extender2 extends Complex {
  
  public extender2 (double x, double y){
    a=x; b=y;
  }

  public extender2 (double x){
    a=x; b=0;
  }

  public extender2 () {
    a=b=0;
  }

  public void print(){
    System.out.println("Printing from class 'extender2'");
  }
}

public class TestComplex2 {

  public static void main (String[] argv)
  {
    Complex c1, c2, c3;
    c1 = new Complex(4.35, 9.002);
    c2 = new extender1 (8.5);
    c3 = new extender2 ();

    c1.print();
    c2.print();
    c3.print();


  }

}
