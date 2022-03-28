/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests all behaviors of the CourseRoll class.
 * @author press
 *
 */
public class CourseRollTest {
	
	/**
	 * tests the constructor for CourseRoll objects.
	 */
	@Test
	public void testConstructor() {
		CourseRoll courseRoll = new CourseRoll(20);
		assertEquals(20, courseRoll.getEnrollmentCap());
		assertEquals(20, courseRoll.getOpenSeats());
	}
	
	/**
	 * Tests the enroll method for CourseRoll objects for valid and invalid students.
	 */
	@Test
	public void testEnroll() {
		CourseRoll courseRoll = new CourseRoll(20);
		assertThrows(IllegalArgumentException.class, () -> courseRoll.enroll(null));
		Student studentAdd = new Student("Randy", "Woodson", "gopack", "gopack@ncsu.edu", "gopack", 18);
		assertDoesNotThrow(() -> courseRoll.enroll(studentAdd));
		assertThrows(IllegalArgumentException.class, () -> courseRoll.enroll(studentAdd));
		
		StudentDirectory directory = new StudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
		CourseRoll courseRoll2 = new CourseRoll(10);
		String[] studentData = null;
		for (int i = 0; i < 10; i++) {
			studentData = directory.getStudentDirectory()[i];
			courseRoll2.enroll(new Student(studentData[0], studentData[1], studentData[2], studentData[0] + "@ncsu.edu", "pw"));
		}
		assertEquals(0, courseRoll2.getOpenSeats()); 
		
		assertThrows(IllegalArgumentException.class, () -> courseRoll2.enroll(studentAdd));
	}
	
	/**
	 * Tests the drop method for CourseRolls in valid and invalid cases.
	 */
	@Test
	public void testDrop() {
		CourseRoll courseRoll = new CourseRoll(20);
		Student studentAdd = new Student("Randy", "Woodson", "gopack", "gopack@ncsu.edu", "gopack", 18);
		courseRoll.enroll(studentAdd);
		assertDoesNotThrow(() -> courseRoll.drop(studentAdd));
	}
	
	/**
	 * Tests the setEnrollmentCap method for CourseRolls for valid and invalid cases.
	 */
	@Test
	public void testSetEnrollmentCap() {
		CourseRoll courseRoll = new CourseRoll(20);
		assertDoesNotThrow(() -> courseRoll.setEnrollmentCap(30));
		assertEquals(30, courseRoll.getEnrollmentCap());
		assertThrows(IllegalArgumentException.class, () -> courseRoll.setEnrollmentCap(251));
		assertThrows(IllegalArgumentException.class, () -> courseRoll.setEnrollmentCap(9));
	}
	
	/**
	 * Tests the canEnroll method for CourseRolls for valid and invalid cases.
	 */
	@Test
	public void testCanEnroll() {
		CourseRoll courseRoll = new CourseRoll(10);
		Student studentAdd = new Student("Randy", "Woodson", "gopack", "gopack@ncsu.edu", "gopack", 18);
		assertTrue(courseRoll.canEnroll(studentAdd));
		courseRoll.enroll(studentAdd);
		assertFalse(courseRoll.canEnroll(studentAdd));
		courseRoll.drop(studentAdd);
		
		StudentDirectory directory = new StudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
		CourseRoll courseRoll2 = new CourseRoll(10);
		String[] studentData = null;
		for (int i = 0; i < 10; i++) {
			studentData = directory.getStudentDirectory()[i];
			courseRoll2.enroll(new Student(studentData[0], studentData[1], studentData[2], studentData[0] + "@ncsu.edu", "pw"));
		}
		assertFalse(courseRoll2.canEnroll(studentAdd));
	}
}
