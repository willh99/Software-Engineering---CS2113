
public class Palindrome2 {

    public static void main (String[] argv)
    {
        String str = "redder";
        char[] letters = str.toCharArray();
        System.out.println ( str + " " + checkPalindrome(letters,0,letters.length-1) );

        str = "river";
        letters = str.toCharArray();
        System.out.println ( str + " " + checkPalindrome(letters,0,letters.length-1) );

        str = "neveroddoreven";
        letters = str.toCharArray();
        System.out.println ( str + " " + checkPalindrome(letters,0,letters.length-1) );
    }
    

    static String checkPalindrome (char[] A, int first, int last)
    {
      String palindrome = "";
      if(A.length == 0){return palindrome;}
      else if(A.length == 1){return palindrome += A[0];}

      else if(A[first] != A[last])
	return palindrome;

      else if(first <= last)
        palindrome += checkPalindrome(A, first+1, last-1);
      return palindrome;
    }

}
