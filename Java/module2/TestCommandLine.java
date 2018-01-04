public class TestCommandLine {

  public static void main (String[] argv) 
  {
    float average=0;

    System.out.println ("Here is a list of arguments you gave:");

    for (int i=0; i<argv.length; i++){
      System.out.println ("arg#"+ i +": "+ argv[i] +", length: "+ argv[i].length());
      average += argv[i].length();
    }
    average = average/argv.length;
    System.out.printf ("Average Length: %.2f\n", average);
  }
}
