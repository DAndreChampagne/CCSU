package cs506.project.GraphCoverageTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T2_AppendGraphCoverageTests {

	
	// 1,2,3
	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		
		
		assertThrows(NullPointerException.class, () -> {
			list.Append(node);
		});
	}
	
	
	// 1,2,4,6,7
	@Test
	void Test2() {
		int expected = 1;
		int actual = -1;
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		
		list.Append(10);
		
		actual = list.Size();
		
		assertEquals(expected, actual);
	}

	// 1,2,4,5,6,7
	@Test
	void Test3() {
		int expected = 2;
		int actual = -1;
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		
		list.Append(10);
		list.Append(20);
		
		actual = list.Size();
		
		assertEquals(expected, actual);
	}

	
	
}
