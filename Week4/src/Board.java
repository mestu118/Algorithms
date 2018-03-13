/******************************************************************************
 * Algorithms Part I: Week 4
 * Problem: Create an immutable data type Board 
 * Created: Tuesday, March 13th, 2018
 * Last Edit: Tuesday, March 13th, 2018
 * @author markestudillo
 *
 *****************************************************************************/

public class Board {
	
	/**
	 *  Constructs a board from an n-by-n array blocks
	 *  where blocks[i][j] = block in row i, column j
	 * @param blocks n-by-n array of blocks
	 */
	public Board(int[][] blocks) {
		
	}
	
	/**
	 * Board dimension n
	 * @return n Dimension of board
	 */
	public int dimension() {
		return 0; 
	}
	
	/**
	 * Number of blocks out of place
	 * @return int number of blocks out of place 
	 */
	public int hammin() {
		return 0; 
	}
	
	/**
	 * Sum of Manhattan distance between blocks and goal
	 * @return Manhattan distance 
	 */
	public int manhattan() {
		return 0; 
	}
	
	/**
	 * Is this board the goal board? 
	 * @return boolean to check if Goal board
	 */
	public boolean isGoal() {
		return false; 
	}
	
	/**
	 * A board that is obtained by exchanging any pair of blocks
	 * @return Board
	 */
	public Board twin() {
		return null;
	}
	
	/**
	 * Does the board equal y
	 * @param y compare to 
	 * @return boolean is it equal to y
	 */
	public boolean equal(Object y) {
		return false; 
	}
	
	/**
	 * All neighboring board
	 * @return Iterable for neighboring board 
	 */
	public Iterable<Board> neighbors() {
		return null; 
	}
	
	/**
	 * String that represents the board 
	 * @return String 
	 */
	public String toString() {
		return ""; 
	}
	
	public static void main(String[] main) {
		// Will modify as necessary 
	}

}
