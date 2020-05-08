package cs506.project.GraphCoverageTests;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T8_ToArrayGraphCoverageTests {

	
//	(1,2,3,5)
	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Integer[] expected = new Integer[] { };
		Integer[] actual = null;
				
		actual = list.ToArray();

		Assert.assertArrayEquals(expected, actual);
	}
	
//	(1,2,3,5)
	// again, want to show what it looks like with actual data
	@Test
	void Test1_WithData() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Integer[] expected = new Integer[] { 1,2,3 };
		Integer[] actual = null;
				
		list.Append(1).Append(2).Append(3);
		
		actual = list.ToArray();

		Assert.assertArrayEquals(expected, actual);
	}	
	
}
