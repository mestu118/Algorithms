import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 * Algorithms Part I: Week 4
 * Problem: Create an immutable data type Solver 
 * Created: Tuesday, March 13th, 2018
 * Last Edit: Tuesday, March 13th, 2018
 * @author markestudillo
 *
 *****************************************************************************/
public class Solver {
	
	/**
	 * Final solution to the initial board (Using the A* algorithm)
	 * @param initial board to solve
	 */
	public Solver(Board initial) {
		if(initial == null) {
			throw new java.lang.IllegalArgumentException();
		}
		MinPQ<Board> answer = new MinPQ<Board>();
	}
	
	/**
	 * Is the initial board solvable? 
	 * @return boolean is it solvable? 
	 */
	public boolean isSolvable() {
		return false;
	}
	
	/**
	 * Minimum number of moves to solve initial board; -1 if unsolvable
	 * @return int number of moves to solve the board 
	 */
	public int moves() {
		return 0; 
	}
	
	/**
	 * Sequence of board in a shortest solution; null if unsolvable 
	 * @return Iterable 
	 */
	public Iterable<Board> solution() {
		return null; 
	}
	
	public static void main(String[] args) {
		//Modify as necessary 
		// create initial board from file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
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
