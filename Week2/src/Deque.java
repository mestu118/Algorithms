import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Code for implementation of a Deque
 * @author Mark J. Estudillo 
 */

public class Deque<Item> implements Iterable<Item> {
	private int n; 			// size of the deque
	private Node first; 		// front of the deque 
	private Node last; 		// back of deque
	
	//	linked list class
	private class Node {
		private Item item; 
		private Node next;
		private Node previous; 
	}
	
	/**
	 * Initialize empty deque 
	 */
	public Deque() {
		first = null; 
		n = 0; 
		last = null; 
	}
	
	/**
	 * Checks if the deque is empty
	 * @return true if the deque is empty  
	 */
	public boolean isEmpty() {
		return n == 0;
	}
	
	/**
	 * Returns the size of the deque 
	 * @return the number of the items in the deque
	 */
	public int size() {
		// return the number of items on the deque
		return n; 
	}
	
	/**
	 * Adds an item to the front of the deque
	 * @param item to add
	 */
	public void addFirst(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException();
		}
		if (n == 0) {
			first = new Node();
			first.item = item; 
			last = first; 
			n++; 
		}
		else {
			Node oldFirst = first; 
			first = new Node();
			oldFirst.previous = first; 
			first.item = item; 
			first.next = oldFirst;
			n++;
		}
	}
	
	/**
	 * Adds an item to the back of the deque
	 * @param item to add
	 */
	public void addLast(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException();
		}
		if (n == 0) {
			first = new Node();
			first.item = item; 
			last = first; 
			n++; 
		}
		else {
			Node oldLast = new Node();
			oldLast = last; 
			last = new Node(); 
			last.item = item; 
			oldLast.next = last; 
			last.previous = oldLast;
			n++; 
		}
	}
	
	/**
	 * Remove the first item of the deque 
	 * @return the item at the front of the deque
	 */
	public Item removeFirst() {
		if (this.isEmpty()) {
			throw new NoSuchElementException(); 
		}
		Item item = first.item; 
		if (first.next != null) {
			first = first.next;
			--n;
		}
		else {
			--n;
			// added this line
			first = null;
		} 
		return item; 
	}
	
	/**
	 * Remove the last item in the deque
	 * @return item at the end of the deque 
	 */
	public Item removeLast() {
		if (this.isEmpty()) {
			throw new NoSuchElementException(); 
		}
		Item item = last.item;
		if (last.previous != null) {
			last = last.previous; 
			--n; 
		}
		else {
			--n;
			// added this line 
			last = null; 
		}
		return item; 
	}
	
	/**
	 * Returns an iterator to this deque
	 * @return an iterator to the deque 
	 */
	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first; 
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException(); 
		}
		
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException(); 
			}
			Item item = current.item; 
			current = current.next;  
			return item; 
		}
	}
	
	public static void main(String[] args) {
		/*
		 * Will implement a StdIn as necessary when asked to
		 */
		
	}

}
