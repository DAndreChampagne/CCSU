package cs506.project.GraphCoverageTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T7_ContainsGraphCoverageTests {

	
//	(1,2,3,7)
	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = new Node<>(10);
		boolean expected = false;
		boolean actual = true;
		
		actual = list.Contains(node);

		assertEquals(expected, actual);
	}	
	
//	(1,2,3,4,5)
	@Test
	void Test2() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = new Node<>(10);
		boolean expected = true;
		boolean actual = false;

		list.Append(node);
		
		actual = list.Contains(node);
		
		assertEquals(expected, actual);
	}	
	
	
}
