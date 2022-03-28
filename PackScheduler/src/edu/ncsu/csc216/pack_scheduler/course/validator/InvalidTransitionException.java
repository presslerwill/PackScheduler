/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Creates an Exception for when an invalid transition is attempted in the FSM. 
 * @author Will Pressler
 * @author Arch Flanagan
 * @author Marshall Pearson
 *
 */
public class InvalidTransitionException extends Exception {
	
	/**
	 * Default field for serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates an InvalidTransitionException with a default error message
	 */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	}
	
	/**
	 * Creates an InvalidTransitionException with a custom error message
	 * @param exception the exception message to be displayed
	 */
	public InvalidTransitionException(String exception) {
		super(exception);
	}
}
