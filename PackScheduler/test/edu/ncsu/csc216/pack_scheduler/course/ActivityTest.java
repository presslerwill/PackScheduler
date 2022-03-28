/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Activity Class method checkConflict().
 * 
 * @author Gabriel Perez
 *
 */
class ActivityTest {

	/**
	 * Tests method checkConflict of the Activity class.
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "TH", 1330,
				1445);

		// checks test works both ways for the above activities
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	/**
	 * Tests method checkConflict of the Activity class.
	 */
	@Test
	public void testCheckConflictTimes() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "W", 1450,
				1540);

		// checks test works both ways for the above activities
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}

	/**
	 * Test method that checks assertions when conflict exists.
	 */
	@Test
	public void testCheckConflictWithConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "M", 1330, 1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
	}

	/**
	 * Test method that checks conflictException is thrown when the times are
	 * overlapping not just same minute
	 */
	@Test
	public void testCheckConflictOverlapWithDiffTimes1() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "M", 1100, 1400);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
	}

	/**
	 * Test method that checks that no conflictExceptoin is thrown when the
	 * activities have arranged meeting days
	 */
	@Test
	public void testCheckConflictArrangedDays() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "A");
		Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "A");

		// checks test works both ways for the above activities
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a1.checkConflict(a3));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
		assertDoesNotThrow(() -> a2.checkConflict(a3));
		assertDoesNotThrow(() -> a3.checkConflict(a1));
		assertDoesNotThrow(() -> a3.checkConflict(a2));
	}

	/**
	 * Test method that checks conflictException is thrown when the times are
	 * overlapping not just same minute
	 */
	@Test
	public void testCheckConflictOverlapWithDiffTimes2() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "W", 1400,
				1430);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
	}

	/**
	 * Test method that checks conflictException is thrown when the times are
	 * overlapping not just same minute
	 */
	@Test
	public void testCheckConflictOverlapWithDiffTimes3() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "W", 1445,
				1530);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
	}
}
