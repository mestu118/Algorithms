/******************************************************************************
 * Algorithms Part I: Week 3
 * Problem: Given a set of n distinct points in the plane, find every (maximal)
 * line segment that connects a subset of 4 or more of the points. 
 * Created: Tuesday, March 6th, 2018
 * Last Edit: Tuesday, March 6th, 2018
 * @author markestudillo
 *
 *****************************************************************************/
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
	
	private final int x; 				// x-coordinate of this point
	private final int y; 				// y-coordinate of this point 
	
	/**
	 * Constructor for the points class and constructs the points x and y
	 * @param x The x-location
	 * @param y The y-location 
	 */
	public Point(int x, int y) {
		this.x = x; 
		this.y = y;
	}
	
	/**
	 * Draws this point
	 */
	public void draw() {
		StdDraw.point(x, y);
	}
	
	/**
	 * Draws the line segment from this point to that point
	 * @param that Another point to create a line segment with 
	 */
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}
	
	/**
	 * Returns a string representation of this point 
	 * @return String String representation of the point 
	 */
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * Compare two points by y-coordinates, breaking ties by x-coordinate 
	 * @param that The other point to compare with 
	 * @return int The integer for the difference in the y-coordinate 
	 */
	public int compareTo(Point that) {
		if (this.x == that.x && this.y == that.y) {
			return 0; 
		}
		
		else if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
			return -1;
		}
		
		return 1; 
	}
	
	/**
	 * The slope between this point and that point 
	 * @param that The point to find the slope with respect to 
	 * @return double The slope 
	 */
	public double slopeTo(Point that) {
		if (that.x == this.x) {
			if (that.y == this.y) {
				return Double.NEGATIVE_INFINITY;
			}
			return Double.POSITIVE_INFINITY;
		}
		
		if (that.x != this.x && that.y == this.y) {
			return +0.0;
		}
		double slope = (1.0*(that.y - this.y)) / (1.0*(that.x - this.x));
		return slope;
	}
	
	/**
	 * Compare two points by slopes they make with this point 
	 * @return Comparator the comparison 
	 */
	public Comparator<Point> slopeOrder() {
		return new BySlope();
	}
	
	private class BySlope implements Comparator<Point> {
		
		public int compare(Point p1, Point p2) {
			double diff = slopeTo(p1) - slopeTo(p2);
			if (diff > 0) {
				return 1; 
			}
			
			else if (diff < 0) {
				return -1; 
			}
			return 0;
		}
	}
	
	public static void main(String[] args) {
		/* Will Modify as necessary */
	}
}
