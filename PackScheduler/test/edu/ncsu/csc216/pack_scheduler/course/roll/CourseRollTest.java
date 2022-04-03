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

import edu.ncsu.csc216.pack_scheduler.course.Course;
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
	    Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	    CourseRoll courseRoll = c.getCourseRoll();

		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(10, courseRoll.getOpenSeats());
	}
	
	/**
	 * Tests the enroll method for CourseRoll objects for valid and invalid students.
	 */
	@Test
	public void testEnroll() {
	    Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	    CourseRoll courseRoll = c.getCourseRoll();
		assertThrows(IllegalArgumentException.class, () -> courseRoll.enroll(null));
		Student studentAdd = new Student("Randy", "Woodson", "gopack", "gopack@ncsu.edu", "gopack", 18);
		assertDoesNotThrow(() -> courseRoll.enroll(studentAdd));
		assertThrows(IllegalArgumentException.class, () -> courseRoll.enroll(studentAdd));
		
		StudentDirectory directory = new StudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
	    Course c2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	    CourseRoll courseRoll2 = c2.getCourseRoll();
		String[] studentData = null;
		for (int i = 0; i < 10; i++) {
			studentData = directory.getStudentDirectory()[i];
			courseRoll2.enroll(new Student(studentData[0], studentData[1], studentData[2], studentData[0] + "@ncsu.edu", "pw"));
		}
		assertEquals(0, courseRoll2.getOpenSeats()); 
		
		courseRoll2.enroll(studentAdd);
		assertEquals(0, courseRoll2.getOpenSeats()); 
		assertEquals(1, courseRoll2.getNumberOnWaitlist());
	
		// Add another 10 students to overflow the waitlist
		for (int i = 0; i < 9; i++) {
			studentData = directory.getStudentDirectory()[i];
			courseRoll2.enroll(new Student(studentData[0] + "2", studentData[1], studentData[2], studentData[0] + "@ncsu.edu", "pw"));
		}
		Student studentAdd2 = new Student("Randy2", "Woodson", "gopack", "gopack@ncsu.edu", "gopack", 18);
		assertThrows(IllegalArgumentException.class, () -> courseRoll2.enroll(studentAdd2));
	}
	
	/**
	 * Tests the drop method for CourseRolls in valid and invalid cases.
	 */
	@Test
	public void testDrop() {
	    Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	    CourseRoll courseRoll = c.getCourseRoll();
		Student studentAdd = new Student("Randy", "Woodson", "gopack", "gopack@ncsu.edu", "gopack", 18);
		courseRoll.enroll(studentAdd);
		assertDoesNotThrow(() -> courseRoll.drop(studentAdd));
		
		StudentDirectory directory = new StudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
		String[] studentData = null;
		for (int i = 0; i < 10; i++) {
			studentData = directory.getStudentDirectory()[i];
			courseRoll.enroll(new Student(studentData[0], studentData[1], studentData[2], studentData[0] + "@ncsu.edu", "pw"));
		}
		assertEquals(0, courseRoll.getOpenSeats()); 
		
		Student studentAdd2 = new Student("Randy2", "Woodson", "gopack", "gopack@ncsu.edu", "gopack", 18);
		
		courseRoll.enroll(studentAdd);
		courseRoll.enroll(studentAdd2);
		assertEquals(0, courseRoll.getOpenSeats()); 
		assertEquals(2, courseRoll.getNumberOnWaitlist());
		
		assertDoesNotThrow(() -> courseRoll.drop(studentAdd));
		assertEquals(0, courseRoll.getOpenSeats()); 
		assertEquals(1, courseRoll.getNumberOnWaitlist());
		
		
		assertDoesNotThrow(() -> courseRoll.drop(new Student("Emerald", "Frost", "efrost", "Emerald" + "@ncsu.edu", "pw")));
		assertEquals(0, courseRoll.getOpenSeats()); 
		assertEquals(0, courseRoll.getNumberOnWaitlist());
		
		assertDoesNotThrow(() -> courseRoll.drop(new Student("Zahir", "King", "zking", "Zahir" + "@ncsu.edu", "pw")));
		assertEquals(1, courseRoll.getOpenSeats()); 
		assertEquals(0, courseRoll.getNumberOnWaitlist());
	}
	
	/**
	 * Tests the setEnrollmentCap method for CourseRolls for valid and invalid cases.
	 */
	@Test
	public void testSetEnrollmentCap() {
	    Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	    CourseRoll courseRoll = c.getCourseRoll();
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
	    Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	    CourseRoll courseRoll = c.getCourseRoll();
		Student studentAdd = new Student("Randy", "Woodson", "gopack", "gopack@ncsu.edu", "gopack", 18);
		assertTrue(courseRoll.canEnroll(studentAdd));
		courseRoll.enroll(studentAdd);
		assertFalse(courseRoll.canEnroll(studentAdd));
		courseRoll.drop(studentAdd);
		
		StudentDirectory directory = new StudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
	    Course c2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	    CourseRoll courseRoll2 = c2.getCourseRoll();
		String[] studentData = null;
		for (int i = 0; i < 10; i++) {
			studentData = directory.getStudentDirectory()[i];
			courseRoll2.enroll(new Student(studentData[0], studentData[1], studentData[2], studentData[0] + "@ncsu.edu", "pw"));
		}
        assertTrue(courseRoll2.canEnroll(studentAdd));
        assertFalse(courseRoll2.canEnroll(new Student("Zahir", "King", "zking", "Zahir" + "@ncsu.edu", "pw")));

		for (int i = 0; i < 10; i++) {
            studentData = directory.getStudentDirectory()[i];
            courseRoll2.enroll(new Student(studentData[0] + "2", studentData[1], studentData[2], studentData[0] + "@ncsu.edu", "pw"));
        }
        assertFalse(courseRoll2.canEnroll(studentAdd));
        assertFalse(courseRoll2.canEnroll(new Student("Zahir2", "King", "zking", "Zahir" + "@ncsu.edu", "pw")));
        
		courseRoll2.drop(new Student("Emerald", "Frost", "efrost", "Emerald" + "@ncsu.edu", "pw"));
        assertTrue(courseRoll2.canEnroll(studentAdd));
        assertFalse(courseRoll2.canEnroll(new Student("Zahir2", "King", "zking", "Zahir" + "@ncsu.edu", "pw")));
	}
}
