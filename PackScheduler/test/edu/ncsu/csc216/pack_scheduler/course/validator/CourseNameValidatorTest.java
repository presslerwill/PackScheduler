/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the functionality of the course name validator, specifically ensuring
 * that the isValid method returns true when the name is valid, and throws an
 * exception otherwise
 * 
 * @author marshallp
 *
 */
class CourseNameValidatorTest {
	/** Instance of class used in testing */
	private CourseNameValidator validator;

	/**
	 * Sets up the instance of class used for testing
	 */
	@BeforeEach
	void setUp() {
		validator = new CourseNameValidator();
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM#isValid(java.lang.String)}.
	 */
	@Test
	void testIsValid() {
		try {
			assertTrue(validator.isValid("CSC216"));
			assertTrue(validator.isValid("E101"));
			assertTrue(validator.isValid("PY205"));
			assertTrue(validator.isValid("HESF110"));			
			
		} catch (InvalidTransitionException e) {
			fail("Exception shouldn't have been thrown");
		}
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("CSC 216"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("C 216"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("CS 216"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("CSC2 16"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("CSC21 6"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("CSC216 "));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("E*110"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("1"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid(" CSC216"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("E1E"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("E11C"));
				
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("E1100"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("E110CA"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("HESFF110"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("PY2008"));
		
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("E110C1"));

		
		









	}

}
