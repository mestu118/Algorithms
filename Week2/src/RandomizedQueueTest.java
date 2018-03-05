import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.StdRandom;

class RandomizedQueueTest {
	
	@Test
	public void test01() {
		RandomizedQueue<String> test = new RandomizedQueue<String>();
		test.enqueue("Hello");
		test.enqueue("Hi");
		test.enqueue("Love");
		test.enqueue("Mark");
		test.enqueue("A");
		assertEquals("A", test.dequeue());
		
	}
}
