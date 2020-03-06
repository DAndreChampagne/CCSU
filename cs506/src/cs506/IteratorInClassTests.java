package cs506;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class IteratorInClassTests {

	List<String> list = null;
	Iterator<?> iterator = null;
	
	
	@BeforeEach
	void setUp() throws Exception {
		 list = new ArrayList<>();
		 list.add("This");
		 list.add("is");
		 list.add("a");
		 list.add("test");
		 
		 iterator = list.iterator();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void HasNext_T() {
		assertTrue(iterator.hasNext());
	}

	@Test
	void HasNext_F() {
		
		while (iterator.hasNext()) {
			iterator.next();
		}
		
		assertFalse(iterator.hasNext());
	}
	
	@Test
	void Next_TT() {
		String expected = "This";
		String actual = "";
		
		actual = (String)iterator.next();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void Next_FF() {
		
		while (iterator.hasNext()) {
			iterator.next();
		}
		
		assertThrows(NoSuchElementException.class, () -> {
			iterator.next();
		});
	}
	
	@Test
	void Next_TF() {
		String expected = null;
		String actual = "";
		
		list = new ArrayList<>();
		list.add(null);
		
		iterator = list.iterator();
		actual = (String)iterator.next();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void Remove_TTTT() {
		iterator.next();
		iterator.remove();
		
		assertFalse(list.contains("This"));
	}
	
	@Test
	void Remove_FFTT() {
		
		while (iterator.hasNext()) {
			iterator.next();
		}
		
		assertThrows(UnsupportedOperationException.class, () -> {
			iterator.remove(); 
		});
		
	}
	
	@Test
	void Remove_TFTT() {
		
	}
	
	@Test
	void Remove_TTFT() {
		
	}
	
	@Test
	void Remove_TTTF() {
		assertThrows(IllegalStateException.class, () -> {
			iterator.remove();
		});
	}
}
