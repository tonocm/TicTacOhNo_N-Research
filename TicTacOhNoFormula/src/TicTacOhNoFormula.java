import java.math.BigDecimal;
import java.math.BigInteger;

public class TicTacOhNoFormula{
  public static void main(String[] args){
    
    /* Environmental Variables
     * 
     * maxN - determines maximum NxN board
     * decimals - determines the number of decimal spaces to be printed in the output
     */
    final int maxN = 13;
    final int decimals = 5;
    
    for(int n=1; n<=maxN; n++){
      // Probability of an N-chain tie
      BigInteger b = numberOfBoards(n, n, n);
      BigInteger total = binomialCoeff(n*n, (int)Math.floor(n*n/2.0f));
      //BigDecimal p = divide(b, total, 100);
      
      System.out.println(n + "\t" +  b + " | " + total + " | " + divide(b,total,decimals));
    }
  }
  
  /*
   * Divide a/b
   * Scale = # of decimal values
   */
  
  public static BigDecimal divide(BigInteger a, BigInteger b, int scale){
    BigDecimal aa = new BigDecimal(a);
    BigDecimal bb = new BigDecimal(b);
    return aa.divide(bb, scale, BigDecimal.ROUND_HALF_UP);
  }
  
  /*
   * SOURCE: http://www.geeksforgeeks.org/dynamic-programming-set-9-binomial-coefficient/
   * Ported from C to Java
   */
  
  public static BigInteger binomialCoeff(int n, int k){
    BigInteger[] C = new BigInteger[k+1];
    BigInteger result;
    C[0] = new BigInteger("1");
    for(int i = 1; i <= k; i++){
      C[i] = new BigInteger("0");
    }
    for(int i = 1; i <= n; i++){
      for(int j = Math.min(i, k); j > 0; j--){
        C[j] = C[j].add(C[j-1]);
      }
    }
    result = C[k];
    return result;
  }
  
  /*
   * n - board size
   * k0 - RIT max-chain
   * k1 - UR max-chain
   * 
   * ONLY WORKS FOR k0 = n, k1 = n
   */
  public static BigInteger numberOfBoards(int n, int k0, int k1){
    BigInteger count = new BigInteger("0");
    BigInteger countRows = new BigInteger("0");
    for(int r = 1; r <= Math.floor(n/2.0f); r++){
      for(int s = 1; s <= Math.floor(n/2.0f); s++){
        BigInteger sign;
        if((r+s)%2 == 0){
          sign = new BigInteger("1");
        }
        else{
          sign = new BigInteger("-1");
        }
        BigInteger rows0 = binomialCoeff(n,r);
        BigInteger rows1 = binomialCoeff(n-r, s);
        BigInteger leftover = binomialCoeff(n*n - (r+s)*n, (int)Math.ceil(n*n/2.0f) - r*n);
        countRows = countRows.add(sign.multiply(rows0).multiply(rows1).multiply(leftover));
      }
    }
    BigInteger countDiagonals = new BigInteger("0");
    if(n%2 == 0){
      BigInteger diagonals = new BigInteger("2");
      BigInteger leftover = binomialCoeff(n*n - 2*n, (int)Math.ceil(n*n/2.0f) - n);
      countDiagonals = diagonals.multiply(leftover);
    }
    count = new BigInteger("2").multiply(countRows).add(countDiagonals);
    return count;
  }
  
  
  /* NON IMPLEMENTED METHODS */
  
  /*
   * n - board size
   * k - RIT max-chain
   * 
   * TODO: WRITE THIS FOR k = n
   */
  
  public static BigInteger numberOfBoardsRIT(int n, int k){
    BigInteger count = new BigInteger("0");
    return count;
  }
  
  /*
   * n - board size
   * k - UR max-chain
   * 
   * TODO: WRITE THIS FOR k = n
   */
  public static BigInteger numberOfBoardsUR(int n, int k) {
    BigInteger count = new BigInteger("0");
    return count;
  } 
}