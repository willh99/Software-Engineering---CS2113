public class CharCount {

  public static void main (String[] argv)
  {
    char[] sentence = {'t','h','e',' ','q','u','i','c','k',' ','b','r',
		       'o','w','n',' ','f','o','x',' ','j','u','m','p',
		       'e','d',' ','o','v','e','r',' ','t','h','e',' ',
		       'l','a','z','y',' ','d','o','g'};
    int[] counts = new int[123];
    for(int i=0; i<sentence.length; i++){
      counts[sentence[i]]++;
    }
    for(int i=97; i<123; i++){
      char c = (char) i;
      System.out.print (c);
      System.out.print (" occurence: ");
      System.out.println (counts[i]);
    }
  }

}
