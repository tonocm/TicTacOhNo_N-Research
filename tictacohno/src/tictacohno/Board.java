package tictacohno;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public static int RIT = 0;
	public static int UR = 1;
	
	private int n;
	private int[][] board;
	
	public Board(int n, int value) {
		this.n = n;
		board = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				board[i][j] = value;
			}
		}
	}
	
	public int getN() {
		return n;
	}
	
	public int getCell(int x, int y) {
		return board[x][y];
	}
	public void setCell(int x, int y, int value) {
		board[x][y] = value;
	}
	
	public Board clone() {
		Board b = new Board(n, UR);
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				b.setCell(i, j, getCell(i, j));
			}
		}
		return b;
	}
	
	public void printBoard() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public int getLongestChain(int value) {
		int max = 0;
		max = Math.max(max, getLongestChainHorizontal(value));
		max = Math.max(max, getLongestChainVertical(value));
		max = Math.max(max, getLongestChainDiagonalUp(value));
		max = Math.max(max, getLongestChainDiagonalDown(value));
		return max;
	}
	public int getLongestChainHorizontal(int value) {
		int max = 0;
		int chain = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(board[i][j] == value)
					chain++;
				else if(board[i][j] != value) {
					if(chain > max)
						max = chain;
					chain = 0;
				}	
			}
			if(chain > max)
				max = chain;
			chain = 0;
		}
		return max;
	}
	public int getLongestChainVertical(int value) {
		int max = 0;
		int chain = 0;
		for(int j=0; j<n; j++) {
			for(int i=0; i<n; i++) {
				if(board[i][j] == value)
					chain++;
				else if(board[i][j] != value) {
					if(chain > max)
						max = chain;
					chain = 0;
				}	
			}
			if(chain > max)
				max = chain;
			chain = 0;
		}
		return max;
	}
	public int getLongestChainDiagonalUp(int value) {
		int max = 0;
		int chain = 0;
		for(int j=0; j<2*n-1; j++) {
			int a;
			int b;
			if(j < n) {
				a = 0;
				b = j+1;
			} else {
				a = j-n+1;
				b = n;
			}
			for(int i=a; i<b; i++) {
				if(board[i][j-i] == value)
					chain++;
				else if(board[i][j-i] != value) {
					if(chain > max)
						max = chain;
					chain = 0;
				}	
			}
			if(chain > max)
				max = chain;
			chain = 0;
		}
		return max;
	}
	public int getLongestChainDiagonalDown(int value) {
		int max = 0;
		int chain = 0;
		for(int j=1-n; j<n; j++) {
			int a;
			int b;
			if(j < 0) {
				a = -j;
				b = n;
			} else {
				a = 0;
				b = n-j;
			}
			for(int i=a; i<b; i++) {
				if(board[i][j+i] == value)
					chain++;
				else if(board[i][j+i] != value) {
					if(chain > max)
						max = chain;
					chain = 0;
				}	
			}
			if(chain > max)
				max = chain;
			chain = 0;
		}
		return max;
	}	
	
	/* STATIC METHODS */
	
	static public Board randomGame(int n) {
		Board b = new Board(n, UR);
		int count = (int)Math.ceil((float)n*n/2);
		for(int i=0; i<count; i++) {
			int x;
			int y;
			do {
				x = (int)(n*Math.random());
				y = (int)(n*Math.random());
			} while(b.getCell(x, y) == RIT);
			b.setCell(x, y, RIT);
		}
		return b;
	}
	
	static public List<Board> generateRandomBoards(int n, int count) {
		List<Board> boardList = new ArrayList<Board>();
		for (int i=0; i<count; i++) {
			boardList.add(randomGame(n));
		}
		return boardList;
	}
	
	static public List<Board> generateBoards(int n) {
		List<Board> boardList = new ArrayList<Board>();
		Board b = new Board(n, UR);
		generateBoardsRecursive(boardList, b, 0, 0, (int)Math.ceil((float)n*n/2));
		return boardList;
	}
	static public void generateBoardsRecursive(List<Board> boardList, Board b, int x, int y, int count) {
		if(count == 0) {
			boardList.add(b.clone());
			return;
		}
		
		int n = b.getN();
		
		if(y >= n)
			return;
		
		int xNew = x;
		int yNew = y;
		xNew++;
		if(xNew >= n) {
			xNew = 0;
			yNew++;
		}
		
		b.setCell(x, y, RIT);
		generateBoardsRecursive(boardList, b, xNew, yNew, count-1);
		
		b.setCell(x, y, UR);
		generateBoardsRecursive(boardList, b, xNew, yNew, count);
	}
}
