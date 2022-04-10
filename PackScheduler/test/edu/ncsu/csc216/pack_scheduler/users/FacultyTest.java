package edu.ncsu.csc216.pack_scheduler.users;

import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Tests the Faculty class
 * @author Will Pressler
 *
 */
class FacultyTest {
	
	/** Test Faculty's first name. */
	private String firstName = "first";
	/** Test Faculty's last name. */
	private String lastName = "last";
	/** Test Faculty's id. */
	private String id = "flast";
	/** Test Faculty's email. */
	private String email = "first_last@ncsu.edu";
	/** Test Faculty's hashed password. */
	private String hashPW;
	/** Hashing algorithm. */
	private static final String HASH_ALGORITHM = "SHA-256";
	

	// This is a block of code that is executed when the FacultyTest object is
	// created by JUnit. Since we only need to generate the hashed version
	// of the plaintext password once, we want to create it as the FacultyTest
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
	 * Tests the hashCode method
	 */
	@Test
	void testHashCode() {
		User s = new Faculty(firstName, lastName, id, email, hashPW, 2);
		User a = new Faculty(firstName, lastName, id, email, hashPW, 2);
		User b = new Faculty("alice", "wonderland", "awonder", "awonder@ncsu.edu", "aliceiscool", 2);

		assertEquals(s.hashCode(), a.hashCode());

		assertNotEquals(s.hashCode(), b.hashCode());
		assertNotEquals(a.hashCode(), b.hashCode());
	}

	/**
	 * Tests the equals method.
	 */
	@Test
	void testEqualsObject() {
		User s = new Faculty(firstName, lastName, id, email, hashPW, 3);
		User a = new Faculty(firstName, lastName, id, email, hashPW, 3);
		User b = new Faculty("alice", "wonderland", "awonder", "awonder@ncsu.edu", "aliceiscool", 3);

		assertTrue(s.equals(a), "same states");
		assertFalse(s.equals(b), "different states");
		
	}

	/**
	 * Tests invalid construction of a Faculty object.
	 */
	@Test
	void testFacultyInvalid() {
		assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, lastName, id, email, hashPW, 4));
		assertThrows(IllegalArgumentException.class, () -> new Faculty(null, lastName, id, email, hashPW, 3));
		assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, null, id, email, hashPW, 3));
		assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, lastName, null, email, hashPW, 3));
		assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, lastName, id, null, hashPW, 3));
		assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, lastName, id, email, null, 3));
		assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, lastName, id, "hellO", hashPW, 3));
		assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, lastName, id, "@hello", hashPW, 3));

	}

	/**
	 * Tests invalid maxCourses
	 */
	@Test
	void testSetMaxCourses() {
		assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, lastName, id, email, hashPW, 5));
		assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, lastName, id, email, hashPW, 0));


	}


	/**
	 * Tests the toString method
	 */
	@Test
	void testToString() {
		User f1 = new Faculty(firstName, lastName, id, email, hashPW, 3);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",3", f1.toString());
	}

}
