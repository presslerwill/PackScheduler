/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This class is the custom exception class and contains 2 constructors one with
 * a string parameter called message and the other with no parameters. The
 * parameterized constructor makes a call to the super class and passes the
 * default message "Schedule conflict." The parameterless constructor simply
 * uses the this() keyword to set the current instance variable equal to the
 * default string. It also extends the Exception class.
 * 
 * @author Gabriel Perez
 *
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * This method takes a String parameter, makes a call to the super class and
	 * sets the parameter equal to the default message listed in the project
	 * requirements.
	 * 
	 * @param message the message string passed into the constructor that is used in
	 *                this method to set the default message for the exception.
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * A parameterless constructor that makes a call to the parameterized
	 * constructor passing the default message string.
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
