import java.util.ArrayList;

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
	private ArrayList<LineSegment> lines;
	
	public FastCollinearPoints(Point[] points) {
		lines = new ArrayList<LineSegment>();
		if (points == null) {
			throw new java.lang.IllegalArgumentException();
		}
		
		for (int i = 0; i < points.length; i = i + 4) {
			Point p = points[i]; 
			Point q = points[i + 1];
			Point r = points[i + 2]; 
			Point s = points[i + 3];
			if (p == null || q == null || r == null || s == null) {
				throw new java.lang.IllegalArgumentException();
			}
			if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
				lines.add(new LineSegment(p, s));
			}
		}
	}
	
	public int numberOfSegments() {
		return lines.size();
	}
	
	public LineSegment[] segments() {
		LineSegment[] answer = new LineSegment[lines.size()];
		return lines.toArray(answer);
	}
	
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
