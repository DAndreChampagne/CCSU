package cs501.project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

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
	void AppendItems() {
		Integer[] expected = { 10, 20 };
		Integer[] actual = null;
		
		LinkedList<Integer> l = new LinkedList<>(Integer.class);
		
		l.Append(10);
		l.Append(20);
	
		actual = l.ToArray();
		
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void PrependItems() {
		Integer[] expected = { 10, 20 };
		Integer[] actual = null;
		
		LinkedList<Integer> l = new LinkedList<>(Integer.class);
		
		l.Prepend(20);
		l.Prepend(10);
		
		actual = l.ToArray();
		
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void RemoveAt() {
		Integer[] expected = { 10, 20 };
		Integer[] actual = null;
		
		LinkedList<Integer> l = new LinkedList<>(Integer.class);
		
		l.Append(10).Append(20).Append(30);
		
		l.RemoveAt(2);
		
		actual = l.ToArray();
		
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void RemoveHead() {
		Integer[] expected = { 20, 30 };
		Integer[] actual = null;
		LinkedList<Integer> l = new LinkedList<>(Integer.class);
		
		l.Append(10).Append(20).Append(30);
		l.Remove(l.Head());
		
		actual = l.ToArray();
		
		assertArrayEquals(expected, actual);
		System.out.println("RemoveHead, actual = " + actual.toString());
	}
	
	@Test
	void RemoveTail() {
		Integer[] expected = { 10, 20 };
		Integer[] actual = null;
		LinkedList<Integer> l = new LinkedList<>(Integer.class);
		
		l.Append(10).Append(20).Append(30);
		l.Remove(l.Tail());
		actual = l.ToArray();
		
		assertArrayEquals(expected, actual);
		System.out.println("RemoveTail, actual = " + actual.toString());
	}
	
	@Test
	void GetItemAtIndex() {
		Integer expected = 10;
		Integer actual = -1;
		
		LinkedList<Integer> l = new LinkedList<>(Integer.class);
		
		l.Append(20);
		l.Append(10);
		l.Append(15);
		
		actual = l.Get(1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void EnsureHeadNodeIsntChanged() {
		LinkedList<Integer> l = new LinkedList<>(Integer.class);
		
		l.Append(10);
		l.Append(20);
		
		for (Node<Integer> p = l.Head(); p != null; p = p.GetNext());
		
		assertNotEquals(l.Head(), null);
	}
	
	
	@Test
	void IteratorTest() {
		LinkedList<Integer> l = new LinkedList<>(Integer.class);
		
		l.Append(10);
		l.Append(20);
		
		Iterator<?> p = l.Iterator();
		
		while (p.hasNext()) {
			Integer x = (Integer)p.next();
			System.out.println(x);
		}
		
	}
	
	@Test
	void ToStringTest() {
		String expected = "[10, 20]";
		String actual = "";
		LinkedList<Integer> l = new LinkedList<>(Integer.class);
		
		l.Append(10);
		l.Append(20);
		
		actual = l.toString();
		
		assertEquals(expected, actual);
		System.out.println(l.toString());
	}

}
