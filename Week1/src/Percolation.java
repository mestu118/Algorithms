import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//import java.lang.*;

public class Percolation {
	private int size;
	private int [][] grid; 
	private WeightedQuickUnionUF conSites;
	private int openSites;
	
	public Percolation(int n) {
		this.size = n; 
		this.grid = new int[n][n];
		conSites = new WeightedQuickUnionUF(n);
		for(int i = 0; i < n; i ++) {
			for(int j = 0; j < n; j ++) {
				grid[i][j] = 0;// create n-by-n grid, with all sites blocked
			}
		}
	}
	
    public void open(int row, int col) {	
    		if (row <= 0 || row > size) {
    			throw new java.lang.IllegalArgumentException(); 
    		}
    		grid[row][col] = 1;
    		int location = xyTo1D(row,col);
    		// open site (row, col) if it is not open already
    }
    
    public boolean isOpen(int row, int col) {
    		if (row <= 0 || row > size) {
    			throw new java.lang.IllegalArgumentException(); 
    		}
    		
    		if(grid[row][col] == 1) {
    			return true;
    		}
    		// is site (row, col) open?
    		return false;
    }
    
    public boolean isFull(int row, int col) {
    		if (row <= 0 || row > size) {
    			throw new java.lang.IllegalArgumentException(); 
    		}// is site (row, col) full?
    		return false;
    }
    
    public int numberOfOpenSites() {
    		return 0;
    	// number of open sites
    }
    
    public boolean percolates() {
    		return false;
    	// does the system percolate?
    }
    
    private int xyTo1D(int row, int col) {
    		if(row == 0) {
    			return col;
    		}
    		else {
    			return row*size + col;
    		}
    }
    
	public static void main(String[] args) {
		
	}

}
