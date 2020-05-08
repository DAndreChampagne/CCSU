package cs506.project.GraphCoverageTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T5_RemoveAtGraphCoverageTests {

	
//	(1,2,3)
	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
				
		assertThrows(IllegalArgumentException.class, () -> {
			list.RemoveAt(0);
		});
	}	
	
//	(1,2,4,5,7)
	@Test
	void Test2() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = new Node<>(10);
			
		list.Append(node);
		
		list.RemoveAt(list.Size()-1);
		
		assertEquals(0, list.Size());
	}	
	
	
}
