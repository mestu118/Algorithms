import java.util.Arrays;
import java.util.Stack;

/******************************************************************************
 * Algorithms Part I: Week 4
 * Problem: Create an immutable data type Board 
 * Created: Tuesday, March 13th, 2018
 * Last Edit: Tuesday, March 27th, 2018
 * @author markestudillo
 *
 *****************************************************************************/

public class Board {
	private int[][] tiles; 
	private int n; 
	
	/**
	 *  Constructs a board from an n-by-n array blocks
	 *  where blocks[i][j] = block in row i, column j
	 * @param blocks n-by-n array of blocks
	 */
	public Board(int[][] blocks) {
		this.tiles = blocks;
		this.n = blocks.length; 
	}
	
	/**
	 * Board dimension n
	 * @return n Dimension of board
	 */
	public int dimension() {
		return n; 
	}
	
	/**
	 * Number of blocks out of place
	 * @return int number of blocks out of place 
	 */
	public int hamming() {
		int outOfPlace = 0; 
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				int location = n*row + col + 1; 
				int currTile = this.tiles[row][col]; 
				if(currTile != 0 && currTile != location) {
					++outOfPlace; 
				}
			}
		}
		return outOfPlace; 
	}
	
	/**
	 * Sum of Manhattan distance between blocks and goal
	 * @return Manhattan distance 
	 */
	public int manhattan() {
		int distance = 0;
		for(int row = 0; row < this.n; row++) {
			for(int col = 0; col < this.n; col++) {
				distance += calculateDistance(row, col);
			}
		}
		return distance; 
	}
	
	public int calculateDistance(int row, int col) {
		if(this.tiles[row][col] == 0) {
			return 0; 
		}
		int value = this.tiles[row][col];
		return Math.abs(row - (value - 1)/n) + Math.abs(col - (value - 1)%n);
	}
	/**
	 * Is this board the goal board? 
	 * @return boolean to check if Goal board
	 */
	public boolean isGoal() {
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				int location = n*row + col + 1; 
				if(this.tiles[row][col] != location) {
					return false; 
				}
			}
		}
		return true; 
	}
	
	/**
	 * A board that is obtained by exchanging any pair of blocks
	 * @return Board
	 */
	public Board twin() {
		for(int row = 0; row < this.n; row++) {
			for(int col = 0; col < this.n - 1; col++) {
				int currVal = this.tiles[row][col];
				int nextVal = this.tiles[row][col + 1];
				if(currVal != 0 && nextVal != 0) {
					int[][] tempArr = Arrays.copyOf(this.tiles, this.n);
					tempArr[row][col] = nextVal;
					tempArr[row][col + 1] = currVal;
					return new Board(tempArr);
				}
			}
		}
		return null;
	}
	
	/**
	 * Does the board equal y
	 * @param y compare to 
	 * @return boolean is it equal to y
	 */
	public boolean equals(Object y) {
		if(y == this) {
			return true; 
		}
		if(y == null || !(y instanceof Board) || ((Board) y).n != this.n) {
			return false; 
		}
		for(int row = 0; row < this.n; row++) {
			for(int col = 0; col < this.n; col++) {
				if(((Board) y).tiles[row][col] != this.tiles[row][col]) {
					return false;
				}
			}
		}
		return true; 
	}
	
	/**
	 * All neighboring board
	 * @return Iterable for neighboring board 
	 */
	public Iterable<Board> neighbors() {
		Stack<Board> neighbors = new Stack<Board>();
		int spaceRow = 0;
		int spaceCol = 0; 
		
		for(int row = 0; row < this.n; row++) {
			for(int col = 0; col < this.n; col++) {
				if(this.tiles[row][col] == 0) {
					spaceRow = row;
					spaceCol = col; 
				}
			}
		}
		if(spaceRow > 0) {
			neighbors.push(new Board(swapping(spaceRow, spaceCol, spaceRow - 1, spaceCol)));
		}
		if(spaceCol > 0) {
			neighbors.push(new Board(swapping(spaceRow, spaceCol, spaceRow, spaceCol - 1)));
		}
		if(spaceRow < this.n - 1) {
			neighbors.push(new Board(swapping(spaceRow, spaceCol, spaceRow + 1, spaceCol)));
		}
		if(spaceCol < this.n - 1) {
			neighbors.push(new Board(swapping(spaceRow, spaceCol, spaceRow, spaceCol + 1)));
		}
		return neighbors; 
	}
	
	/**
	 * Swaps values between old location and new location
	 * @param oldCol The column of the first value to be switched
	 * @param oldRow The row of the first value to be switched
	 * @param newCol The column of the second value to be switched
	 * @param newRow The column of the second value to be switched
	 * @return int[][] 2-D array with the values switched 
	 */
	public int[][] swapping(int oldCol, int oldRow, int newCol, int newRow){
		int[][] answer = Arrays.copyOf(this.tiles, this.n);
		int temp = answer[oldRow][oldCol]; 
		answer[oldRow][oldCol] = answer[newRow][newCol];
		answer[newRow][newCol] = temp; 
		return answer; 
	}
	
	/**
	 * String that represents the board 
	 * @return String 
	 */
	public String toString() {
		StringBuilder s = new StringBuilder(); 
		s.append(n + "\n");
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				s.append(String.format("%2d", tiles[i][j])); 
			}
			s.append("\n");
		}
		return s.toString(); 
	}
	
	public static void main(String[] main) {
		// Will modify as necessary 
	}

}
