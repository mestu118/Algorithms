import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DequeTest {
	@Test
	void test1() {
		Deque<String> deque = new Deque<String>();
		deque.addFirst("ALO");
		deque.addLast("BOOTY");
		deque.removeLast();
		assertEquals("ALO", deque.removeLast());
	}

}
