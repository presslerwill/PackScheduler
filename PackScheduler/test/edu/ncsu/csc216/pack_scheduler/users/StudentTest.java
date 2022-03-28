package edu.ncsu.csc216.pack_scheduler.users;

import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Tests Student.java using requirements and tests both valid and invalid test
 * cases. Contains the first name, last name, id, email, hashPW, and max credits
 * fields that are used throughout class as valid parameters for the
 * Student.java class. Multiple methods are created to test the various
 * requirements and scenarios that are expected for Student.java to pass and
 * handle. This test class relates directly to Student.java and tests its
 * capabilities at handling exceptions.
 * 
 * @author Gabriel Perez
 * @author Will Pressler
 * @author Aaron Jong
 *
 */
class StudentTest {
	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name. */
	private String lastName = "last";
	/** Test Student's id. */
	private String id = "flast";
	/** Test Student's email. */
	private String email = "first_last@ncsu.edu";
	/** Test Student's hashed password. */
	private String hashPW;
	/** Test Student's max credits. */
	private int maxCredits = 18;
	/** Hashing algorithm. */
	private static final String HASH_ALGORITHM = "SHA-256";
	

	// This is a block of code that is executed when the StudentTest object is
	// created by JUnit. Since we only need to generate the hashed version
	// of the plaintext password once, we want to create it as the StudentTest
	// object is
	// constructed. By automating the hash of the plaintext password, we are
	// not tied to a specific hash implementation. We can change the algorithm
	// easily.
	{
		try {
			String plaintextPW = "password";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(plaintextPW.getBytes());
			this.hashPW = new String(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			fail("An unexpected NoSuchAlgorithmException was thrown.");
		}
	}

	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		User s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",18", s1.toString());
	}

	/**
	 * test hashCode() function.
	 */
	@Test
	public void testHashCode() {
		User s = new Student(firstName, lastName, id, email, hashPW);
		User a = new Student(firstName, lastName, id, email, hashPW);
		User b = new Student("alice", "wonderland", "awonder", "awonder@ncsu.edu", "aliceiscool");

		assertEquals(s.hashCode(), a.hashCode());

		assertNotEquals(s.hashCode(), b.hashCode());
		assertNotEquals(a.hashCode(), b.hashCode());

	}

	/**
	 * test equals() function.
	 */
	@Test
	public void testEqualsObject() {
		User s = new Student(firstName, lastName, id, email, hashPW);
		User a = new Student(firstName, lastName, id, email, hashPW);
		User b = new Student("alice", "wonderland", "awonder", "awonder@ncsu.edu", "aliceiscool");

		assertTrue(s.equals(a), "same states");
		assertFalse(s.equals(b), "different states");

	}

	/**
	 * Tests constructing a student with maxCredits.
	 */
	@Test
	public void testStudentWithCredits() {
		// test valid construction
		Student s = assertDoesNotThrow(() -> new Student(firstName, lastName, id, email, hashPW, maxCredits),
				"Should not throw exception");

		assertAll("Student", () -> assertEquals(firstName, s.getFirstName(), "Incorrect first name"),
				() -> assertEquals(lastName, s.getLastName(), "Incorrect last name"),
				() -> assertEquals(id, s.getId(), "Incorrect student id"),
				() -> assertEquals(email, s.getEmail(), "Incorrect student email"),
				() -> assertEquals(hashPW, s.getPassword(), "Incorrect student password"),
				() -> assertEquals(maxCredits, s.getMaxCredits(), "Incorrect max credits"));

	}

	/**
	 * Tests constructing a Student with max credits and invalid first name.
	 */
	@Test
	public void testStudentWithCreditsInvalidFirstName() {
		// test invalid construction
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, lastName, id, email, hashPW, maxCredits));
		assertEquals("Invalid first name", e1.getMessage());
	}

	/**
	 * Tests constructing a Student with max credits and invalid last name.
	 */
	@Test
	public void testStudentWithCreditsInvalidLastName() {
		// test invalid construction
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, null, id, email, hashPW, maxCredits));
		assertEquals("Invalid last name", e1.getMessage());
	}

	/**
	 * Tests constructing a Student with max credits and invalid ID.
	 */
	@Test
	public void testStudentWithCreditsInvalidID() {
		// test invalid construction
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, null, email, hashPW, maxCredits));
		assertEquals("Invalid id", e1.getMessage());
	}

	/**
	 * Tests constructing a Student with max credits and invalid email.
	 */
	@Test
	public void testStudentWithCreditsInvalidEmail() {
		// test invalid construction
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, hashPW, maxCredits));
		assertEquals("Invalid email", e1.getMessage());
	}

	/**
	 * Tests constructing a Student with max credits and invalid password.
	 */
	@Test
	public void testStudentWithCreditsInvalidPassword() {
		// test invalid construction
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, null, maxCredits));
		assertEquals("Invalid password", e1.getMessage());
	}

	/**
	 * Tests constructing a Student with max credits and invalid max credits.
	 */
	@Test
	public void testStudentWithCreditsInvalidMaxCredits() {
		// test invalid construction
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, hashPW, 2));
		assertEquals("Invalid max credits", e1.getMessage());
	}

	/**
	 * Tests constructing a student without maxCredits.
	 */
	@Test
	public void testStudentWithoutCredits() {
		// test valid construction
		User s = assertDoesNotThrow(() -> new Student(firstName, lastName, id, email, hashPW),
				"Should not throw exception");

		assertAll("Student", () -> assertEquals(firstName, s.getFirstName(), "Incorrect first name"),
				() -> assertEquals(lastName, s.getLastName(), "Incorrect last name"),
				() -> assertEquals(id, s.getId(), "Incorrect student id"),
				() -> assertEquals(email, s.getEmail(), "Incorrect student email"),
				() -> assertEquals(hashPW, s.getPassword(), "Incorrect student password"));
	}

	/**
	 * Tests constructing a Student without max credits and invalid first name.
	 */
	@Test
	public void testStudentWithoutCreditsInvalidFirstName() {
		// test invalid construction
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, lastName, id, email, hashPW));
		assertEquals("Invalid first name", e1.getMessage());
	}

	/**
	 * Tests constructing a Student without max credits and invalid last name.
	 */
	@Test
	public void testStudentWithoutCreditsInvalidLastName() {
		// test invalid construction
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, null, id, email, hashPW));
		assertEquals("Invalid last name", e1.getMessage());
	}

	/**
	 * Tests constructing a Student without max credits and invalid ID.
	 */
	@Test
	public void testStudentWithoutCreditsInvalidID() {
		// test invalid construction
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, null, email, hashPW));
		assertEquals("Invalid id", e1.getMessage());
	}

	/**
	 * Tests constructing a Student without max credits and invalid email.
	 */
	@Test
	public void testStudentWithoutCreditsInvalidEmail() {
		// test invalid construction
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, hashPW));
		assertEquals("Invalid email", e1.getMessage());
	}

	/**
	 * Tests constructing a Student without max credits and invalid password.
	 */
	@Test
	public void testStudentWithoutCreditsInvalidPassword() {
		// test invalid construction
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, null));
		assertEquals("Invalid password", e1.getMessage());
	}

	/**
	 * Tests setFirstName().
	 */
	@Test
	public void testSetFirstName() {
		User s = new Student(firstName, lastName, id, email, hashPW);

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setFirstName(null));

		assertEquals("Invalid first name", e1.getMessage());
		assertEquals(firstName, s.getFirstName());
	}

	/**
	 * Tests setLastName().
	 */
	@Test
	public void testSetLastName() {
		User s = new Student(firstName, lastName, id, email, hashPW);

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setLastName(null));

		assertEquals("Invalid last name", e1.getMessage());
		assertEquals(lastName, s.getLastName());
	}

	/**
	 * Tests setEmail().
	 */
	@Test
	public void testSetEmail() {
		User s = new Student(firstName, lastName, id, email, hashPW);

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setEmail(null));

		assertEquals("Invalid email", e1.getMessage());
		assertEquals(email, s.getEmail());
	}

	/**
	 * Tests setPassword().
	 */
	@Test
	public void testSetPassword() {
		User s = new Student(firstName, lastName, id, email, hashPW);

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setPassword(null));

		assertEquals("Invalid password", e1.getMessage());
		assertEquals(hashPW, s.getPassword());
	}

	/**
	 * Tests setMaxCredits().
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student(firstName, lastName, id, email, hashPW, 10);

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setMaxCredits(80));

		assertEquals("Invalid max credits", e1.getMessage());
		assertEquals(10, s.getMaxCredits());
	}
	
	/**
	 * Tests the compareTo method in Student
	 */
	@Test
	public void testCompareTo() {
		Student a = new Student("Barry", "Allen", "ballen", "ballen@ncsu.edu", "pdiddy");
		Student b = new Student("Bruce", "Wayne", "bwayne", "bwayne@ncsu.edu", "supermansucks");
		Student c = new Student("Barry", "Allen", "ballen", "ballen@ncsu.edu", "pdiddy");
		Student d = new Student("Christopher", "Barnaby", "cbarna", "cbarna@ncsu.edu", "2002rocks");
		Student e = new Student("Christopher", "Allen", "callen", "callen@ncsu.edu", "2003rocks");
		Student f = new Student("Barry", "Allen", "ballin", "ballin@ncsu.edu", "2006rocks");
		
		assertEquals(-1, a.compareTo(b));
		assertEquals(1, b.compareTo(a));
		assertEquals(0, a.compareTo(c));
		assertEquals(0, c.compareTo(a));
		assertEquals(-1, a.compareTo(d));
		assertEquals(1, d.compareTo(a));
		assertEquals(-1, a.compareTo(e));
		assertEquals(1, e.compareTo(a));
		assertEquals(-1, a.compareTo(f));
		assertEquals(1, f.compareTo(a));
		
	}
	
	/**
	 * Tests the canAdd method in Student
	 */
	@Test
	public void testCanAdd() {
		Student a = new Student("Barry", "Allen", "ballen", "ballen@ncsu.edu", "pdiddy", 7);
		Course c = new Course("CSC216", "Computing with Java", "001", 3, "jsmith7", 20, "MW");
		Course d = new Course("CSC217", "Computing with Java", "001", 5, "jsmith7", 20, "F");
		Course e = new Course("CSC226", "Computing with Java", "001", 3, "jsmith7", 20, "TH");
		
		assertTrue(a.canAdd(c));
		a.getSchedule().addCourseToSchedule(c);
		assertFalse(a.canAdd(d));
		assertTrue(a.canAdd(e));
		
	}
}
