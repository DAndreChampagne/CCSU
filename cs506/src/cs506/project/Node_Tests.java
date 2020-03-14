package cs506.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs501.project.Node;

class Node_Tests {

	private Node<String> _node = null;

	@Test
	void C1_SetData() {
		String expected = "Dog";
		String actual = null;
		
		_node = new Node<>();
		_node.SetData("Dog");
		
		actual = _node.GetData();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void C2_SetPreviousNull() {
		Node<String> actual = null;
		
		_node = new Node<>();
		_node.SetPrevious(null);
		
		_node.GetPrevious();
		
		assertNull(actual);
	}
	
	@Test
	void C2_SetPreviousNonNull() {
		Node<String> actual = null;
		
		_node = new Node<>("Dog");
		_node.SetPrevious(new Node<String>("Cat"));
		
		actual = _node.GetPrevious();
		
		assertNotNull(actual);
	}
	
	@Test
	void C3_SetNextNull() {
		Node<String> actual = null;
		
		_node = new Node<>();
		_node.SetNext(null);
		
		_node.GetNext();
		
		assertNull(actual);
	}
	
	@Test
	void C3_SetNextNonNull() {
		Node<String> actual = null;
		
		_node = new Node<>("Dog");
		_node.SetNext(new Node<String>("Cat"));
		
		actual = _node.GetNext();
		
		assertNotNull(actual);
	}
	
}
