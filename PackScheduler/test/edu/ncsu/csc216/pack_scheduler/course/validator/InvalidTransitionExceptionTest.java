/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the InvalidTransitionException, ensuring it properly throws with
 * default and custom messages.
 * 
 * @author marshallp
 *
 */
class InvalidTransitionExceptionTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException#InvalidTransitionException()}.
	 */
	@Test
	void testInvalidTransitionException() {
		Exception e = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", e.getMessage());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException#InvalidTransitionException(java.lang.String)}.
	 */
	@Test
	void testInvalidTransitionExceptionString() {
		Exception e = new InvalidTransitionException("My message");
		assertEquals("My message", e.getMessage());
	}

}
