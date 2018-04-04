import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;


public class KdTree {

	
	private static class Node {
		private Point2D p;
		private RectHV rect; 
		private Node lb; 
		private Node rt; 
		
		// Create the Node 
		// If rect == null, create a rectangle with max of 1. 
		public Node(Point2D p, RectHV rect) {
			RectHV r = rect;
			if (r == null) {
				r = new RectHV(0,0,1,1);
			}
			this.rect = r; 
			this.p = p;
		}
	}
	// Defining nodes and the size of the tree
	private Node _points;
	private int _size; 
	
	//initialize the tree, points are null and size is 0.
	public KdTree() {
		_points = null; 
		_size = 0; 
	}
	
	
	//Size of the tree
	public int size() {
		return _size; 
	}
	
	//If _points is null, no nodes in tree
	public boolean isEmpty() {
		return _points == null; 
	}
	
	
	//Helper classes with inserting 
	//Larger or equal put the node to the right 
	private Node insertHorizontal(Node x, Point2D p, RectHV rect) {
        if (x == null) {
            _size++;
            return new Node(p, rect);
        }
        if (x.p.equals(p))  {
        		return x;
        }

        RectHV r;
        //Compares with respect to their y-coordinate 
        int compare = Point2D.Y_ORDER.compare(x.p, p);
        if (compare > 0) {
            if (x.lb == null) {
            		r = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y());
            }
            else{
            		r = x.lb.rect;
            }
            x.lb = insertVertical(x.lb, p, r);
        } else {
            if (x.rt == null) {
            		r = new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax());
            }
            else{
            		r = x.rt.rect;
            }
            x.rt = insertVertical(x.rt, p, r);
        }

        return x;
    }

    // larger or equal keys go right
    private Node insertVertical(Node x, Point2D p, RectHV rect) {
        if (x == null) {
            _size++;
            return new Node(p, rect);
        }
        if (x.p.equals(p))  {
        		return x;
        }

        RectHV r;
        int compare = Point2D.X_ORDER.compare(x.p, p);
        if (compare > 0) {
            if (x.lb == null) {
                r = new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax());
            }
            else {
                r = x.lb.rect;
            }
            x.lb = insertHorizontal(x.lb, p, r);
        } else {
            if (x.rt == null) {
            		r = new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
            }
            else {
            		r = x.rt.rect;
            }
                
            x.rt = insertHorizontal(x.rt, p, r);
        }

        return x;
    }
	public void insert(Point2D p) {
		if(isEmpty()) {
			_points = insertVertical(_points, p, null);
		}
		
		else {
			_points = insertVertical(_points, p, _points.rect);
		}
		
	}
	
	private boolean contains(Node node, Point2D p, boolean vert) {
		if(node == null) {
			return false; 
		}
		if(node.p.equals(p)) {
			return true;
		}
		
		int compare; 
		
		if(vert) {
			compare = Point2D.X_ORDER.compare(node.p, p);
		}
		else {
			compare = Point2D.Y_ORDER.compare(node.p, p);
		}
		
		if(compare > 0) {
			return contains(node.lb, p, !vert);
		}
		else {
			return contains(node.rt, p, !vert);
		}
	}
	
	
	public boolean contains(Point2D p) {
		return contains(_points, p , true); 
	}
	
	private void draw(Node x, boolean vert) {
        if (x.lb != null)     draw(x.lb, !vert);
        if (x.rt != null)    draw(x.rt, !vert);

        // draw the point first
        StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            StdDraw.point(x.p.x(), x.p.y());

            // draw the line
            double xmin, ymin, xmax, ymax;
            if (vert) {
                StdDraw.setPenColor(StdDraw.RED);
                xmin = x.p.x();
                xmax = x.p.x();
                ymin = x.rect.ymin();
                ymax = x.rect.ymax();
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                ymin = x.p.y();
                ymax = x.p.y();
                xmin = x.rect.xmin();
                xmax = x.rect.xmax();
            }
            StdDraw.setPenRadius();
            StdDraw.line(xmin, ymin, xmax, ymax);
    }
	public void draw() {
		StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);
        if (isEmpty()) return;
        draw(_points, true);
	}
	
	private void range(Node node, RectHV rect, Queue<Point2D> q) {
		if (node == null) {
			return; 
		}
		if (rect.contains(node.p)) {
			q.enqueue(node.p);
		}
		
		if (node.lb != null && rect.intersects(node.lb.rect)) {
			range(node.lb, rect, q);
		}
		
		if (node.rt != null && rect.intersects(node.rt.rect)) {
			range(node.rt, rect, q);
		}
	}
	
	public Iterable<Point2D> range(RectHV rect){
		Queue<Point2D> q = new Queue<Point2D>();
		range(_points,rect,q);
		return q;
	}
	
	private Point2D nearest(Node node, Point2D p, Point2D mp, boolean vert) {
		Point2D min = mp; 
		if(node == null) {
			return min; 
		}
		
		if(p.distanceSquaredTo(node.p) < p.distanceSquaredTo(min)) {
			min = node.p;
		}
		
		if(vert) {
			if(node.p.x() < p.x()) {
				min = nearest(node.rt, p, min, !vert);
				if(node.lb != null && (min.distanceSquaredTo(p) > 
				node.lb.rect.distanceSquaredTo(p))) {
					min = nearest(node.lb, p, min, !vert); 
				}
			} else {
				min = nearest(node.lb, p, min, !vert);
				if(node.rt != null && (min.distanceSquaredTo(p) > 
				node.rt.rect.distanceSquaredTo(p))) {
					min = nearest(node.rt, p, min, !vert);
				}
			}
		}
		else {
			if(node.p.y() < p.y()) {
				min = nearest(node.rt, p, min, !vert); 
				if(node.lb != null && (min.distanceSquaredTo(p) > 
				node.lb.rect.distanceSquaredTo(p))) {
					min = nearest(node.lb, p, min, !vert);
				}
				else {
					min = nearest(node.lb, p, min, !vert);
					if(node.rt != null && (min.distanceSquaredTo(p)
							> node.rt.rect.distanceSquaredTo(p))) {
						min = nearest(node.rt, p, min, !vert);
					}
				}
			}
		}
		return min;
	}
	public Point2D nearest(Point2D p) {
		if(isEmpty()) {
			return null;
		}
		return nearest(_points, p, _points.p, true);
	}
	
	public static void main(String[] args) {
		// Will modify when needed 
	}

}