package tictacohno;

import java.util.List;

public class TicTacOhNo {

	public static void main(String[] args) {
	
		int maxN = 50;
		
		for(int n=1; n<=maxN; n++) {
		
			//List<Board> boardList = Board.generateBoards(n);
			//int count = boardList.size();
			
			int count = 100000;
			
			/*
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
			*/
			
			
			
			for(int i=0; i<count; i++) {
				Board b = Board.generateRandomBoards(n, 1).get(0);
				
			}
			
			/*
			System.out.println("n = "+n);
			System.out.println("Number of boards = "+count);
			System.out.println("RIT n-chains = "+nChain0);
			System.out.println("UR n-chains = "+nChain1);
			System.out.println("Both n-chains = "+nChainBoth);
			*/
			
			/*System.out.println("expected longest chain RIT = "+((double)sumLongestChainOne/count));
			System.out.println("expected longest chain UR = "+((double)sumLongestChainZero/count));
			System.out.println("win percentage = "+((double)wins/count)*100);
			System.out.println("tie percentage = "+((double)ties/count)*100);
			System.out.println("loss percentage = "+((double)losses/count)*100);
			System.out.println("expected win chain length = "+((double)sumChainWin/(count-ties)));
			System.out.println("expected tie chain length = "+((double)sumChainTie/ties));
			System.out.println("expected loss chain length = "+((double)sumChainLoss/(count-ties)));
			System.out.println();*/
		}
	}

}
