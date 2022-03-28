/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;


/**
 * Tests the Schedule class
 * @author Will Pressler
 *
 */
public class ScheduleTest {

	/**
	 * Tests the Schedule constructor method
	 */
	@Test
	public void testScheduleConstructor() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());
		assertEquals(0, schedule.getScheduledCourses().length);
	}
	
	/**
	 * Tests the addCourseToSchedule method
	 * @throws ConflictException if the Course to be added conflicts with an existing Course in the schedule
	 */
	@Test
	public void testAddCourseToSchedule() throws ConflictException {
		Schedule schedule = new Schedule();
		Course a = new Course("CSC116", "Intro to Programming", "001", 3, "jschmidt", 100, "MW", 1200, 1315);
		Course b = new Course("CSC216", "More Programming", "001", 3, "jyoung", 100, "MW", 830, 920);
		assertTrue(schedule.addCourseToSchedule(a));
		assertTrue(schedule.addCourseToSchedule(b));
		
		Course c = new Course("CSC226", "Discrete Mathematics", "001", 3, "nboyo", 100, "MW", 1230, 1320);
		assertThrows(IllegalArgumentException.class, () -> schedule.addCourseToSchedule(c));
	}
	
	/**
	 * Tests the removeCourseFromSchedule method
	 * @throws ConflictException if the Courses conflict
	 */
	@Test
	public void testRemoveCourseFromSchedule() throws ConflictException {
		Schedule schedule = new Schedule();
		Course a = new Course("CSC116", "Intro to Programming", "001", 3, "jschmidt", 100, "MW", 1200, 1315);
		Course b = new Course("CSC216", "More Programming", "001", 3, "jyoung", 100, "MW", 830, 920);
		assertTrue(schedule.addCourseToSchedule(a));
		assertTrue(schedule.addCourseToSchedule(b));
		assertTrue(schedule.removeCourseFromSchedule(b));
		assertEquals(1, schedule.getScheduledCourses().length);
		assertFalse(schedule.removeCourseFromSchedule(b));
		assertTrue(schedule.removeCourseFromSchedule(a));
		assertEquals(0, schedule.getScheduledCourses().length);
		
	}
	
	/**
	 * Tests the resetSchedule method
	 * @throws ConflictException if the Courses conflict
	 */
	@Test
	public void testResetSchedule() throws ConflictException {
		Schedule schedule = new Schedule();
		Course a = new Course("CSC116", "Intro to Programming", "001", 3, "jschmidt", 100, "MW", 1200, 1315);
		Course b = new Course("CSC216", "More Programming", "001", 3, "jyoung", 100, "MW", 830, 920);
		assertTrue(schedule.addCourseToSchedule(a));
		assertTrue(schedule.addCourseToSchedule(b));
		assertEquals(2, schedule.getScheduledCourses().length);
		schedule.resetSchedule();
		assertEquals(0, schedule.getScheduledCourses().length);
	}
	
	/**
	 * Tests the getScheduledCourses method
	 * @throws ConflictException if the Courses conflict
	 */
	@Test
	public void testGetScheduledCourses() throws ConflictException {
		Schedule schedule = new Schedule();
		Course a = new Course("CSC116", "Intro to Programming", "001", 3, "jschmidt", 100, "MW", 1200, 1315);
		Course b = new Course("CSC216", "More Programming", "001", 3, "jyoung", 100, "MW", 830, 920);
		assertTrue(schedule.addCourseToSchedule(a));
		assertTrue(schedule.addCourseToSchedule(b));
		assertEquals("CSC116", schedule.getScheduledCourses()[0][0]);
		assertEquals("CSC216", schedule.getScheduledCourses()[1][0]);
		assertEquals("001", schedule.getScheduledCourses()[0][1]);
		assertEquals("001", schedule.getScheduledCourses()[1][1]);



	}
	
	/**
	 * Tests the setTitle method
	 */
	@Test
	public void testSetTitle() {
		Schedule schedule = new Schedule();
		schedule.setTitle("This is my schedule");
		assertEquals("This is my schedule", schedule.getTitle());
		assertThrows(IllegalArgumentException.class, () -> schedule.setTitle(null));
	}
	
	/**
	 * Tests the getScheduleCredits() method
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule schedule = new Schedule();
		assertEquals(0, schedule.getScheduleCredits());
		
		Course a = new Course("CSC116", "Intro to Programming", "001", 3, "jschmidt", 100, "MW", 1200, 1315);
		schedule.addCourseToSchedule(a);
		assertEquals(3, schedule.getScheduleCredits());
		
		Course b = new Course("CSC216", "More Programming", "001", 4, "jyoung", 100, "MW", 830, 920);
		schedule.addCourseToSchedule(b);
		assertEquals(7, schedule.getScheduleCredits());

	}
	
	/**
	 * Tests the canAdd() method
	 */
	@Test
	public void testCanAdd() {
		Schedule schedule = new Schedule();
		Course a = new Course("CSC116", "Intro to Programming", "001", 3, "jschmidt", 100, "MW", 1200, 1315);
		assertTrue(schedule.canAdd(a));
		schedule.addCourseToSchedule(a);
		
		Course b = new Course("CSC216", "More Programming", "001", 4, "jyoung", 100, "MW", 830, 920);
		assertTrue(schedule.canAdd(b));
		schedule.addCourseToSchedule(b);
		
		Course c = new Course("CSC316", "Even more programming", "001", 4, "jyoung", 100, "WF", 900, 1000); 
		assertFalse(schedule.canAdd(c));
		
		assertFalse(schedule.canAdd(null));
		assertFalse(schedule.canAdd(b));
		
		c = new Course("CSC216", "More programming", "001", 4, "jyoung", 100, "WF", 1200, 1300); 
		assertFalse(schedule.canAdd(c));


		




	}
	
	
}
