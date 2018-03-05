import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;
/**
 * Implementation of a Randomized Queue
 * Because performance requirements are to have queue operations in constant
 * amortized time, it utilizes a resizable array. 
 * Week 2 - Princeton Algorithms
 * @author Mark J. Estudillo 
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] a; 		// array of items
	private int n; 			// number of elements in randomized queue
	
	/**
	 * Constructor for the class
	 * Constructs an empty randomized queue
	 */
	public RandomizedQueue() {
		a = (Item[]) new Object[2];
		n = 0; 
	}
	
	/**
	 * Checks if the randomized queue is empty
	 * @return if queue is empty
	 */
	public boolean isEmpty() {
		return n == 0; 
	}
	
	/**
	 * Returns the number of items on the randomized queue
	 * @return size of the queue
	 */
	public int size() {
		return n; 
	}
	
	/**
	 * Resizes the array 
	 */
	private void resize(int capacity) {
		assert capacity >= n; 
		
		Item[] temp = (Item[]) new Object[capacity]; 
		for (int i = 0; i < n; i++) {
			temp[i] = a[i];
		}
		
		a = temp; 
	}
	
	/**
	 * Adds an item to the randomized queue
	 * @param item to be added
	 */
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException(); 
		}
		
		if (n == a.length) {
			resize(2 * a.length); 			// doubles the size of the array 
		}
		
		a[n++] = item; 
	}
	
	/**
	 * Removes and returns a random item
	 * @return random items from the randomized queue
	 */
	public Item dequeue() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException(); 
		}
		int randomIndex = StdRandom.uniform(n); 
		Item temp = a[n-1]; 
		Item item = a[randomIndex]; 
		a[randomIndex] = temp; 
		a[n - 1] = null; 
		--n; 
		
		if (n > 0 && n == a.length/4) {
			resize(a.length/2); 
		}
		return item; 
	}
	
	/**
	 * Returns a random item but does not remove it
	 * from the randomized queue
	 * @return random item from the randomized queue
	 */
	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException(); 
		}
		int randomIndex = StdRandom.uniform(n); 
		Item item = a [randomIndex]; 
		return item; 
	}
	
	/**
	 * Returns an independent iterator over items in random order
	 * @return iterator 
	 */
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator(); 
	
	}
	
	/**
	 * Inner class for the iterator of Randomized Queue
	 */
	private class RandomizedQueueIterator implements Iterator<Item> {
		private int i; 
		
		/*
		 * Constructor for inner class
		 */
		public RandomizedQueueIterator() {
			int randomIndex = StdRandom.uniform(n);
			i = randomIndex;  
		}
		
		/**
		 * Checks if there is a next value for the iterator
		 * @return if there is a next item 
		 */
		public boolean hasNext() {
			return i <= n; 
		}
		
		/**
		 * Does not implement remove
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		/**
		 * Returns the next item in the iterator
		 * @return the next item 
		 */
		public Item next() {
			if (isEmpty()) {
				throw new java.util.NoSuchElementException(); 
			}
			Item item = a[i];
//			i = StdRandom.uniform(n);
			i ++; 
			return item;
		}
	}
	
	
	public static void main(String[] args) {
		/*
		 * Will implement a StdIn as necessary when asked to
		 */
		
	}

}














