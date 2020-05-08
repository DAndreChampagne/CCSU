package cs506.project.GraphCoverageTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;

class T1_GetGraphCoverageTests {

	
	// 1,2,3
	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		
		assertThrows(IllegalArgumentException.class, () -> {
			list.Get(10);
		});
	}
	
	
	// 1,2,4,5,7
	@Test
	void Test2() {
		int expected = 10;
		int actual = -1;
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		
		list.Append(10);
		
		actual = list.Get(0);
		
		assertEquals(expected, actual);
	}

}
