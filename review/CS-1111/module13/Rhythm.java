public class Rhythm {

  public static void main (String[] argv)
  {
    String[] rhythm = {"Rhythem", "helps", "your", "two", "hips", "move"};
    String[] hello = {"Hello", "There"};
    String digit = "";

    for(int i=0; i<rhythm.length; i++){
      digit += rhythm[i].charAt(0);
    }
    System.out.println(digit);
    digit = "";

    for(int i=0; i<hello.length; i++) {
      digit += hello[i].charAt(0);
    }
    System.out.println(digit);
  }

}
