package cs506.project.InputSpacePartitionTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T5_RemoveAtInputSpacePartitionTests {

	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		
		assertThrows(IllegalArgumentException.class, () -> {
			list.RemoveAt(-1);
		});
	}

	@Test
	void Test2() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		
		assertThrows(IllegalArgumentException.class, () -> {
			list.RemoveAt(10);
		});
	}
	
	@Test
	void Test3() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		
		list.Append(10);
		
		assertThrows(IllegalArgumentException.class, () -> {
			list.RemoveAt(-1);
		});
	}
	
	@Test
	void Test4() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		
		list.Append(10);
		
		assertThrows(IllegalArgumentException.class, () -> {
			list.RemoveAt(10);
		});
	}
	
	@Test
	void Test5() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		int expected = 0;
		int actual = -1;
		
		list.Append(10);
		
		list.RemoveAt(0);
		actual = list.Size();
		
		assertEquals(expected, actual);
	}
	
	
}
