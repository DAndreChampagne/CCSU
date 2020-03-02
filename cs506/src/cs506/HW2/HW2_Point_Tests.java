package cs506.HW2;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs506.HW2.Point;

public class HW2_Point_Tests {
		
	@BeforeEach
	public void BeforeEach() {
		
	}
	
	@Test
	public void AddingObjectsTwice() {
		Set<cs506.HW2.Point> set = new HashSet<cs506.HW2.Point>();
		
		Point p1 = new Point(1,2);
		Point p2 = new Point(5,5);
		
		set.add(p1);
		set.add(p1); // should be fine, because it's the same object
		set.add(p2);
		
		assertEquals(2, set.size(), "Unexpected set size");
	}
	
	@Test
	public void AddingSameValueTwice() {
		Set<cs506.HW2.Point> set = new HashSet<cs506.HW2.Point>();
		
		Point p1 = new Point(1,2);
		Point p2 = new Point(5,5);
		Point p3 = new Point(5,5);
		
		set.add(p1);
		set.add(p2);
		set.add(p3); // should not be added, since (5,5) and (5,5) are the same point
		
		assertEquals(2, set.size(), "Unexpected set size");
	}
	
	@Test
	public void HashCodesEqual() {
		Point p1 = new Point(1,1);
		Point p2 = new Point(1,1);
		
		
		assertAll(
			() -> assertTrue(p1.equals(p2)),
			() -> assertEquals(p1.hashCode(), p2.hashCode())
		);
	}
	
}













