package tictacohno;

import java.util.List;
import java.io.*;
public class TicTacOhNo {

 public static void main(String[] args)throws IOException{
 
  int maxN = 50;
  int count = 10000; 
  BufferedWriter out = new BufferedWriter(new FileWriter(new File("TicTacOhNoData.txt"), false));
  for(int n=5; n<=maxN; n++) {
  
   //List<Board> boardList = Board.generateBoards(n);
    List<Board> boardList = Board.generateRandomBoards(n, count);
    //int count = boardList.size();
   
   int sumLongestChainZero = 0;
   int sumLongestChainOne = 0;
   int sumChainWin = 0;
   int sumChainTie = 0;
   int sumChainLoss = 0;
   int temp0, temp1;
   int wins = 0;
   int ties = 0;
   int losses = 0;
   
   int nChain0 = 0;
   int nChain1 = 0;
   int nChainBoth = 0;
   
   for(Board b : boardList) {
    temp0 = b.getLongestChain(0);
    temp1 = b.getLongestChain(1);
    sumLongestChainZero += temp0;
    sumLongestChainOne += temp1;
    if(temp1 > temp0) {
     wins++;
     sumChainWin += temp1;
     sumChainLoss += temp0;
    } else if(temp1 == temp0) {
     ties++;
     sumChainTie += temp1;
    } else {
     losses++;
     sumChainLoss += temp1;
     sumChainWin += temp0;
    }
    
    if(temp0 == n)
     nChain0++;
    if(temp1 == n)
     nChain1++;
    if(temp0 == n && temp1 == n)
     nChainBoth++;
    //b.printBoard();
   }
   /*System.out.print(n + "\t");
   System.out.print(count + "\t");
   System.out.print(nChain0 + "\t");
   System.out.print(nChain1 + "\t");
   System.out.print(nChainBoth + "\n");*/
   
   
   //System.out.println("expected longest chain RIT = "+((double)sumLongestChainOne/count));
   //System.out.println("expected longest chain UR = "+((double)sumLongestChainZero/count));
   //System.out.println("loss percentage = "+((double)losses/count));
   //System.out.println("expected loss chain length = "+((double)sumChainLoss/(count-ties)));
   
   
   double a = ((double)ties/count); //RIT-UR Tie Prob
   double b = ((double)wins/count); //RIT Win Prob
   double c = ((double)losses/count); //UR Win Prob
   double d = ((double)sumChainTie/ties); //Expected Tie Chain Length
   double e = ((double)sumChainWin/(count-ties)); //Expected RIT Win Chain Length
   double f = ((double)sumChainLoss/(count-ties)); //Expected UR Win Chain Length
   double g = a*d+b*e+c*f; //Average chain length
   
   
   out.write(n + "\t");
   out.write(a + "\t");
   out.write(b + "\t");
   out.write(c + "\t");
   out.write(d + "\t");
   out.write(e + "\t");
   out.write(f + "\t");
   out.write(g + "\n");
   
  }
  out.close();
 }

}