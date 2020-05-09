package cs506.project.InputSpacePartitionTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T6_FindInputSpacePartitionTests {

	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		
		node = list.Find(null);
		
		assertNull(node);
	}
	
	@Test
	void Test2() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		
		node = list.Find(10);
		
		assertNull(node);
	}
	
	@Test
	void Test3() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		
		list.Append(5);
		
		node = list.Find(10);
		
		assertNull(node);
	}
	
	@Test
	void Test4() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		
		list.Append(10);
		
		node = list.Find(10);
		
		assertNotNull(node);
	}
	
}
