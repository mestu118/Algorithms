import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
	
	private Digraph _digraph;
	private BreadthFirstDirectedPaths _v; 
	private BreadthFirstDirectedPaths _w;
	private int _ancestorInt; 
	
	public SAP(Digraph G) {
		if(G == null) {
			throw new java.lang.IllegalArgumentException(); 
		}
		_digraph = G; 
	}
	
   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w) {
	   int minPath = Integer.MAX_VALUE;

	   _v = new BreadthFirstDirectedPaths(_digraph, v);
	   _w = new BreadthFirstDirectedPaths(_digraph, w);
	   _ancestorInt = -1; 
	   for(int i = 0; i < _digraph.V(); i++) {
		   if(_v.hasPathTo(i) && _w.hasPathTo(i)) {
			   minPath = Math.min(minPath, _v.distTo(i) + _w.distTo(i));
			   _ancestorInt = i; 
		   }
	   }
	   if(_ancestorInt == -1) {
		   return _ancestorInt; 
	   }
	   return minPath;
   }

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w) {
	   return _ancestorInt; 
   }

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w) {
	   if (v == null || w == null) {
		   throw new java.lang.IllegalArgumentException();
	   }
	   int minPath = Integer.MAX_VALUE;
	   _v = new BreadthFirstDirectedPaths(_digraph, v); 
	   _w = new BreadthFirstDirectedPaths(_digraph, w);
	   for(int i = 0; i < _digraph.V(); i++) {
		   if(_v.hasPathTo(i) && _w.hasPathTo(i)) {
			   minPath = Math.min(minPath, _v.distTo(i) + _w.distTo(i));
			   _ancestorInt = i; 
		   }
	   }
	   if(_ancestorInt == -1) {
		   return _ancestorInt; 
	   }
	   return minPath;
   }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
	   if (v == null || w == null) {
		   throw new java.lang.IllegalArgumentException();
	   }
	   return _ancestorInt;  
   }

   // do unit testing of this class
   public static void main(String[] args) {
	   	In in = new In(args[0]);
	    Digraph G = new Digraph(in);
	    SAP sap = new SAP(G);
	    while (!StdIn.isEmpty()) {
	        int v = StdIn.readInt();
	        int w = StdIn.readInt();
	        int length   = sap.length(v, w);
	        int ancestor = sap.ancestor(v, w);
	        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
	    }
	   
   }
}
