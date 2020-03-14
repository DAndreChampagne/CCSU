package cs506.HW3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoundedQueue_Tests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

//	A1, B1, C1, D1, E1 (F)
	@Test
	void BaseChoiceTest() {
		BoundedQueue q = new BoundedQueue(10); // C1
		
		String expected = "cat";
		String actual = "";
		
		if (!q.isFull()) // C4
			q.enQueue("cat"); // C2

		if (!q.isEmpty()) // C5
			actual = (String)q.deQueue(); // C3
		
		assertEquals(expected, actual);
	}

//	A1, B1, C1, D1, E1 (T)
	@Test
	void EnqueueWhenQueueIsFull() {
		BoundedQueue q = new BoundedQueue(1);
		
		String expected = "cat";
		String actual = "";
		
		q.enQueue("cat");
		
		assertThrows(IllegalStateException.class, () -> {
			q.enQueue("dog");
		});
	}
	
//	A1, B1, C1, D1 (T), E1
	@Test
	void DequeueWhenQueueIsEmpty() {
		BoundedQueue q = new BoundedQueue(1);
		
		assertThrows(IllegalStateException.class, () -> {
			q.deQueue();
		});
	}
		
//	A1, B1, C2, D1, E1
	@Test
	void EnqueueNullItem() {
		BoundedQueue q = new BoundedQueue(10);
		
		assertThrows(NullPointerException.class, () -> {
			if (!q.isFull())
				q.enQueue(null);
		});
	}
	
//	A1, B2, C1, D1, E1
	@Test
	void EnqueueWithZeroCapacity() {
		BoundedQueue q = new BoundedQueue(0);
		
		assertThrows(IllegalStateException.class, () -> {
			q.enQueue("cat");
		});
	}
	
//	A1 (null), B1, C1, D1, E1
	@Test
	void EnqueueItemToNullList() {
		BoundedQueue q = null;

		assertThrows(NullPointerException.class, () -> {
			q.enQueue("cat");
		});
	}
	


}
