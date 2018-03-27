import java.util.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 * Algorithms Part I: Week 4
 * Problem: Create an immutable data type Solver 
 * Created: Tuesday, March 13th, 2018
 * Last Edit: Tuesday, March 13th, 2018
 * @author markestudillo
 *****************************************************************************/
public class Solver {
	private CompBoards recentMove; 
	/**
	 * Final solution to the initial board (Using the A* algorithm)
	 * @param initial board to solve
	 */
	public Solver(Board initial) {
		if(initial == null) {
			throw new java.lang.IllegalArgumentException();
		}
		MinPQ<CompBoards> answer = new MinPQ<CompBoards>();
		MinPQ<CompBoards> answerTwin = new MinPQ<CompBoards>();
		
		answer.insert(new CompBoards(initial));
		answerTwin.insert(new CompBoards(initial.twin()));
		
		while(true) {
			recentMove = check(answer); 
			if(recentMove != null || check(answerTwin) != null) {
				return;
			}
		}
	}
	
	private CompBoards check(MinPQ<CompBoards> moves) {
		if(moves.isEmpty()) {
			return null;
		}
		
		CompBoards best = moves.delMin();
		
		if(best.board.isGoal()) {
			return best; 
		}
		
		for(Board board : best.board.neighbors()) {
			if(best.prev == null || !board.equals(best.prev.board)) {
				moves.insert(new CompBoards(board, best));
			}
		}
		return null;
	}
	
	private class CompBoards implements Comparable<CompBoards> {
		private CompBoards prev; 
		final private Board board; 
		private int numMoves = 0; 
		
		public CompBoards(Board board) {
			this.board = board;
		}
		
		public CompBoards(Board board, CompBoards previous) {
			this.prev = previous;
			this.board = board; 
			this.numMoves = previous.numMoves + 1; 
		}

		public int compareTo(CompBoards com) {
			return (this.board.manhattan() - com.board.manhattan()) + (this.numMoves + com.numMoves);
		
		}
		
	}
	
	/**
	 * Is the initial board solvable? 
	 * @return boolean is it solvable? 
	 */
	public boolean isSolvable() {
		return recentMove != null;
	}
	
	/**
	 * Minimum number of moves to solve initial board; -1 if unsolvable
	 * @return int number of moves to solve the board 
	 */
	public int moves() {
		return this.isSolvable() ? recentMove.numMoves : -1; 
	}
	
	/**
	 * Sequence of board in a shortest solution; null if unsolvable 
	 * @return Iterable 
	 */
	public Iterable<Board> solution() {
		if(!this.isSolvable()) {
			return null;
		}
		Stack<Board> moves = new Stack<Board>();
		while(recentMove != null) {
			moves.push(recentMove.board);
			recentMove = recentMove.prev;
		}
		return moves; 
	}
	
	public static void main(String[] args) {
		//Modify as necessary 
		// create initial board from file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            blocks[i][j] = in.readInt();
	        }
	    }
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver solver = new Solver(initial);

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}

}
