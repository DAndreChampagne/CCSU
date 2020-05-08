package cs506.project.GraphCoverageTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T4_RemoveGraphCoverageTests {

	
//	(1,2,3)
	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
				
		assertThrows(NullPointerException.class, () -> {
			list.Prepend(node);
		});
	}	
	
//	(1,2,4,5,7,9,11,13)
	// Infeasible, in order for n not to equal the head or tail, it has to not be the first or last node.
	// Which means it will have a previous and a next.
//	@Test
//	void Test2() {
//		LinkedList<Integer> list = new LinkedList<>(Integer.class);
//		Node<Integer> node = null;
//		
//		Object[] nodes = new Object[] {
//				new Node<Integer>(10),
//				new Node<Integer>(20),
//				new Node<Integer>(30),
//		};
//
//
//	}	
	
	
//	(1,2,4,5,7,9,10,11,13)
	// also infeasible
//	@Test
//	void Test3() {
//		LinkedList<Integer> list = new LinkedList<>(Integer.class);
//		Node<Integer> n1 = null, n2 = null;
//				
//		n1 = new Node<>(10);
//		n2 = new Node<>(20);
//		
//		list.Append(n1).Append(n2);
//		
//		list.Remove(n2);
//		
//		assertEquals(1, list.Size());
//
//	}
	
//	(1,2,4,5,7,9,11,12,13)
	// also infeasible
//	@Test
//	void Test4() {
//		LinkedList<Integer> list = new LinkedList<>(Integer.class);
//		Node<Integer> node = null;
//				
//
//	}
	
//	(1,2,4,5,6,7,8,9,11,13)
	// remove head node from a list of size 1
	@Test
	void Test5() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = new Node<>(10);
				
		list.Append(node);
		
		list.Remove(node);
		
		assertEquals(0, list.Size());
	}
	
//	(1,2,4,5,6,7,9,10,11,13)
	// head node, size > 1
	// infeasible. head cannot have a previous
//	@Test
//	void Test6() {
//		LinkedList<Integer> list = new LinkedList<>(Integer.class);
//		Node<Integer> node = null;
//				
//
//	}
	
//	(1,2,4,5,7,8,9,11,12,13)
	// tail node, size > 1
	// infeasible. tail node in a list size>1 has to have a previous
//	@Test
//	void Test7() {
//		LinkedList<Integer> list = new LinkedList<>(Integer.class);
//		Node<Integer> node = null;
//				
//
//	}
	
//	(1,2,4,5,7,9,10,11,12,13)
	// remove node from middle of list, where n previous is not the head, and n next is not the tail
	@Test
	void Test8() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = new Node<>(3);

		list.Append(1).Append(2).Append(node).Append(4).Append(5);
	
		list.Remove(node);
		
		assertEquals(4, list.Get(2));
	}
	
//	(1,2,4,5,6,7,8,9,11,12,13)
	// infeasible. this would be a single node in the list containing a next pointer.
//	@Test
//	void Test9() {
//		LinkedList<Integer> list = new LinkedList<>(Integer.class);
//		Node<Integer> node = null;
//				
//
//	}
	
//	(1,2,4,5,6,7,9,10,11,12,13)
	// removing head node, size>1
	// infeasible. head node cannot have a previous pointer
//	@Test
//	void Test10() {
//		LinkedList<Integer> list = new LinkedList<>(Integer.class);
//		Node<Integer> node = null;
//				
//
//	}
	
	
}
