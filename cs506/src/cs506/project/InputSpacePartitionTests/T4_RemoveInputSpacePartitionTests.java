package cs506.project.InputSpacePartitionTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T4_RemoveInputSpacePartitionTests {

	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		
		assertThrows(NullPointerException.class, () -> {
			list.Remove(null);
		});
	}
	
	@Test
	void Test2() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		int expected = 1;
		int actual = 0;
		
		list.Append(10).Append(20);
		node = list.Head();
		
		list.Remove(node);
		actual = list.Size();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void Test3() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		int expected = 1;
		int actual = 0;
		
		list.Append(10).Append(20);
		node = list.Tail();
		
		list.Remove(node);
		actual = list.Size();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void Test4() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		int expected = 2;
		int actual = 0;
		
		list.Append(10).Append(20).Append(30);
		node = list.Find(20);
		
		list.Remove(node);
		actual = list.Size();
		
		assertEquals(expected, actual);
	}
	
}
