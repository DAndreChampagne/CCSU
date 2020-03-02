package cs501.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;


class LinkedList_Tests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void GetGenericType() {
		Integer[] expected = { 10, 20 };
		Integer[] actual = null;
		
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.Append(10);
		l.Append(20);
	
		actual = l.ToArray();
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void EnsureHeadNodeIsntChanged() {
		LinkedList<Integer> l = new LinkedList<>();
		
		l.Append(10);
		l.Append(20);
		
		for (Node<Integer> p = l.Head(); p != null; p = p.GetNext()) {
			
		}
		
		assertNotEquals(l.Head(), null);
	}
	
//	@Test
//	void LinkedListToArray() {
//		Integer[] expected = { 10, 20 };
//		Integer[] actual = null;
//		LinkedList<Integer> l = new LinkedList<>();
//		
//		l.Append(10);
//		l.Append(20);
//		
//		actual = l.ToArray();
//		
//		assertEquals(expected, actual);
//	}

}
