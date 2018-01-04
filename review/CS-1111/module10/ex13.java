public class ex13 {

  public static void main (String[] argv)
  {
    int[] A = {-5, 2, 3, -9, 12, 4, -30};

    for(int i=0; i<A.length; i++){
        if(A[i]==4){
          System.out.println("4 exists in the array");
          break;
        }
    }
  }

}
