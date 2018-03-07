import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 * Algorithms Part I: Week 3
 * Problem: Given a set of n distinct points in the plane, find every (maximal)
 * line segment that connects a subset of 4 or more of the points. 
 * Created: Tuesday, March 6th, 2018
 * Last Edit: Tuesday, March 6th, 2018
 * @author markestudillo
 *
 *****************************************************************************/
public class FastCollinearPoints {
	private ArrayList<LineSegment> lines; 		// Where segments will be stored
	
	/**
	 * Constructor for the Brute Collinear Points class 
	 * @param points The points that will be checked for connected segments
	 */
	public FastCollinearPoints(Point[] points) {
		if(points == null) {
			throw new java.lang.IllegalArgumentException();
		}
		
		for(int i = 0; i < points.length; i++) {
			if(points[i] == null) {
				throw new java.lang.IllegalArgumentException();
			}
		}
		
		if(checkDuplicates(points)) {
			throw new java.lang.IllegalArgumentException();
		}
		
		Point[] adjPoints = Arrays.copyOf(points, points.length);
		lines = new ArrayList<LineSegment>();
		for(int i = 0; i < adjPoints.length - 3; i ++) {
			Arrays.sort(adjPoints, adjPoints[i].slopeOrder());
			int counter = 0; 
			for(int j = i + 1; j < i + 2; j ++) {
				if(adjPoints[i].slopeTo(adjPoints[j]) != adjPoints[i].slopeTo(adjPoints[j + 1])) {
					counter = 1; 
				}
			}
			if(counter != 1) {
				lines.add(new LineSegment(adjPoints[i], adjPoints[i + 3]));
			}
			else {
				counter = 0; 
			}
		}
		
	}
	
	
	/**
	 * This method checks for duplicate values in the Point array
	 * @param points the array to iterate over and check for duplicates
	 * @return true if there is a duplicate 
	 */
	private boolean checkDuplicates(Point[] points) {
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].compareTo(points[j]) == 0) {
					return true; 
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns the number of connected segments in the inputs 
	 * @return int the number of segments connected in the input values 
	 */
	public int numberOfSegments() {
		return lines.size();
	}
	
	/**
	 * Returns the segments that are connected, p->q->r->s
	 * The output is in the for p->s or s->p
	 * @return LineSegment[] with all the connected points 
	 */
	public LineSegment[] segments() {
		LineSegment[] answer = new LineSegment[lines.size()];
		return lines.toArray(answer);
	}
	
	/**
	 * Main Function 
	 * @param args
	 */
	public static void main(String[] args) {

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}

}

