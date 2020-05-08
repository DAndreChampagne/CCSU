package cs506.project.GraphCoverageTests;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T10_toRawStringGraphCoverageTests {

	
//	(1,2,3,4)
	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		String expected = "";
		String actual = null;
				
		actual = list.toRawString();

		Assert.assertEquals(expected, actual);
	}
	
//	(1,2,3,5,6,7,9)
	@Test
	void Test1_WithData() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		String expected = "1 ";
		String actual = null;
		
		list.Append(1);
		
		actual = list.toRawString();

		Assert.assertEquals(expected, actual);
	}	
	
}
