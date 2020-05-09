package cs506.project.InputSpacePartitionTests;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;


class T1_GetInputSpacePartitionTests {

	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		
		assertThrows(IllegalArgumentException.class, () -> {
			list.Get(0);
		});
	}
	
	@Test
	void Test2() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Integer expected = 10;
		
		list.Append(10);
		
		Integer actual = list.Get(0);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void Test3() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		
		assertThrows(IllegalArgumentException.class, () -> {
			list.Get(0);
		});
	}

}
