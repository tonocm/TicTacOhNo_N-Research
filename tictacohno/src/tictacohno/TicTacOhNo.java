package tictacohno;
import java.util.List;
import java.io.*;

public class TicTacOhNo {
  public static void main(String[] args)throws IOException{
    
        
    /*
     * Environmental Variables:
     * Only Variables that are needed to be changed throughout the program to modify its behavior.
     * boardReps - Number of boards generated to calculate probability
     * doExhaustive - Set to true if exhaustive performance needed [only board from 1 to 5].
     * maxN - Maximum size of NxN boards, starting at N=5 or N=1 depending on doExhaustive
     */
    
    final int boardReps = 1000000;
    final boolean doExhaustive = false;
    int maxN = 10;
    final boolean print = false;
    
    BufferedWriter out;
    
    if(print)
      out = new BufferedWriter(new FileWriter(new File("TicTacOhNoData.txt"), false));
    else
      out = new BufferedWriter(new FileWriter(new File("NoPrintData.txt"), false));
    
    int count, n=1;
    
    if(doExhaustive){
      n = 1; //Smallest board size
      maxN = 5; //Memory constrain
    }
    
    for(int i=n; n<=maxN; n++){
      List<Board> boardList;
      if(print)
        System.out.println(n); //For log purposes "progress bar"
      
      if(doExhaustive){
        boardList = Board.generateBoards(n);
        count = boardList.size();
      }
      else{
        count = boardReps;
        boardList = Board.generateRandomBoards(n, count);
      }
      
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
      int nChainTie = 0;
      
      for(Board b : boardList) {
        temp0 = b.getLongestChain(0);
        temp1 = b.getLongestChain(1);
        sumLongestChainZero += temp0;
        sumLongestChainOne += temp1;
        
        if(temp1 > temp0){
          wins++;
          sumChainWin += temp1;
          sumChainLoss += temp0;
        }
        else if(temp1 == temp0){
          ties++;
          sumChainTie += temp1;
        }
        else{
          losses++;
          sumChainLoss += temp1;
          sumChainWin += temp0;
        }
        
        if(temp0 == n)
          nChain0++;
        
        if(temp1 == n)
          nChain1++;
        
        if(temp0 == n && temp1 == n)
          nChainTie++;
        
      }

      /* Temp variables only used for file writing purposes, hence the names */
      
      double a = ((double)ties/count); //RIT-UR Tie Prob
      double b = ((double)wins/count); //RIT Win Prob
      double c = ((double)losses/count); //UR Win Prob
      double d = ((double)sumChainTie/ties); //Expected Tie Chain Length
      double e = ((double)sumLongestChainOne/count); //expected longest chain RIT
      double f = ((double)sumLongestChainZero/count); //expected longest chain UR
      double g = a*d+b*e+c*f; //Average max chain length
      
      if(!print)
        out.write(n + "\t" + (double)nChainTie/count + "\n");
      
      /* Writing results to a file, every element is separated by a tab, and every set of results is separated by a new line */
      if(print){
        out.write(n + "\t");
        out.write(a + "\t");
        out.write(b + "\t");
        out.write(c + "\t");
        out.write(d + "\t");
        out.write(e + "\t");
        out.write(f + "\t");
        out.write(g + "\n");
      }
      
    }
      out.close(); //closing the file
  }
  
}