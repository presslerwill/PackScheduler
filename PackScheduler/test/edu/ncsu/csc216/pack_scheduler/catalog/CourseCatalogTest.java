/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;



/**
 * Tests the CourseCatalog class.
 * @author Will Pressler
 *
 */
public class CourseCatalogTest {

	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	/** Valid Course enrollment cap */
	private static final int ENROLLMENT_CAP = 20;
	
	
	/**
	 * Test PackScheduler.addCourseToCatalog().
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();
		// adds a course to the catalog
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(1, cc.getCourseCatalog().length);
		
		//Attempt to add a course that is already in the catalog
		assertFalse(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(1, cc.getCourseCatalog().length);
		
		
		//Attempt to add a course that is not already in the catalog
		assertTrue(cc.addCourseToCatalog("CSC420", "Computing in Minecraft", "602", 2, "jdsalli", 100, "MW", 1200, 1330));
		assertEquals(2, cc.getCourseCatalog().length);
		
	}
	
	/**
	 * Test PackScheduler.addCourseToCatalog().
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.newCourseCatalog();
		assertFalse(cc.removeCourseFromCatalog(NAME, SECTION));
		
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertTrue(cc.removeCourseFromCatalog(NAME, SECTION));
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertFalse(cc.removeCourseFromCatalog(NAME, "420"));
		assertFalse(cc.removeCourseFromCatalog("ACC 210", SECTION));

	}
	
	/**
	 * Test PackScheduler.getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		// adds a course to the catalog
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(1, cc.getCourseCatalog().length);
		
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertTrue(c.equals(cc.getCourseFromCatalog(NAME, SECTION)));
		
		assertNull(cc.getCourseFromCatalog("CSC 420", SECTION));
	}
	
	/**
	 * Test PackScheduler.loadCoursesFromFile().
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
		
		cc.saveCourseCatalog("test-files/actual_course_records.txt");
		
	}
	
}
