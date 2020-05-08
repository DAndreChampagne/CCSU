package cs506.project.GraphCoverageTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T6_FindGraphCoverageTests {

	
//	(1,2,3,8)
	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
				
		assertNull(list.Find(10));
	}	
	
//	(1,2,3,4,5,6)
	@Test
	void Test2() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = new Node<>(10);
			
		list.Append(node);
		
		assertEquals(node, list.Find(10));
	}	
	
}
