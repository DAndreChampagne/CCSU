package cs506.project.InputSpacePartitionTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T2_AppendInputSpacePartitionTests {

	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		
		assertThrows(NullPointerException.class, () -> {
			list.Append(node);
		});
	}
	
	@Test
	void Test2() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = new Node<>(10);
		int expected = 1;
		int actual = 0;
		
		list.Append(node);
		
		actual = list.Size();
		
		assertEquals(expected, actual);
	}
	
}
