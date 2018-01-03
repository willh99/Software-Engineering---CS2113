class A {

  static int i;

  public A(){
    i=0;
    System.out.println("Class A. i="+i);
  }
}

class B extends A {

  public B(){
    i=1;
    System.out.println("Class B. i="+i);
  }
}

class C extends B {

  public C(){
    System.out.println("Class C");
  }
}


public class ex5_14 {

  public static void main (String[] args)
  {
    C Obj = new C();
    B Obj2 = new B();
    A obj3 = new A();
  }

}
