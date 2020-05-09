package cs506.project.InputSpacePartitionTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cs501.project.LinkedList;
import cs501.project.Node;

class T7_ContainsInputSpacePartitionTests {

	@Test
	void Test1() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		boolean expected = false;
		boolean actual;
		
		actual = list.Contains(10);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void Test2() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		boolean expected = false;
		boolean actual;
		
		list.Append(5);
		
		actual = list.Contains(10);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void Test3() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		boolean expected = true;
		boolean actual;
		
		list.Append(10);
		
		actual = list.Contains(10);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void Test4() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		boolean expected = false;
		boolean actual;
		
		actual = list.Contains(node);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void Test5() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		boolean expected = false;
		boolean actual;
		
		node = new Node<>(10);
		
		actual = list.Contains(node);
		
		assertEquals(expected, actual);	
	}
	
	@Test
	void Test6() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		boolean expected = false;
		boolean actual;
		
		list.Append(10);
		
		actual = list.Contains(node);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void Test7() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		boolean expected = false;
		boolean actual;
		
		list.Contains(5);
		
		actual = list.Contains(10);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void Test8() {
		LinkedList<Integer> list = new LinkedList<>(Integer.class);
		Node<Integer> node = null;
		boolean expected = true;
		boolean actual;
		
		list.Append(10);
		
		actual = list.Contains(10);
		
		assertEquals(expected, actual);
	}
}
