import java.util.Iterator;
import java.util.Stack;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
	
	private SET<Point2D> points; 
	
	public PointSET() {
		points = new SET<Point2D>(); 
		
	}
	
	public boolean isEmpty() {
		return points.isEmpty(); 
	}
	
	public void insert(Point2D p) {
		this.points.add(p);
		
	}
	
	public int size() {
		return points.size();
	}
	
	public boolean contains(Point2D p) {
		return this.points.contains(p); 
	}
	
	public void draw() {
		Iterator<Point2D> iter = this.points.iterator();
		while(iter.hasNext()) {
			Point2D val = iter.next(); 
	        val.draw();
		}
	}
	
	public Iterable<Point2D> range(RectHV rect){
		Stack<Point2D> valsStack = new Stack<Point2D>(); 
		Iterator<Point2D> iter = this.points.iterator();
		while(iter.hasNext()) {
			Point2D val = iter.next(); 
			if(rect.contains(val)) {
				valsStack.push(val);
			}
		}
		return valsStack;
	}
	
	public Point2D nearest(Point2D p) {
		Point2D retVal = null; 
		double distance = Double.MAX_VALUE;
		Iterator<Point2D> iter = this.points.iterator(); 
		while(iter.hasNext()) {
			Point2D curr = iter.next(); 
			double currDist = p.distanceTo(curr); 
			if(currDist < distance) {
				distance = currDist; 
				retVal = curr; 
			}
		}
		return retVal; 
	}
	
	public static void main(String[] args) {
		// Will modify when needed 
	}

}
