/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * The Conflict class is an interface that is used in Activity class. This class
 * contains the abstract method checkConflict() which accepts an Activity object
 * as a parameter and checks if that Activity creates a conflict. If the
 * Activity object passed into checkConflict creates a conflict a custom checked
 * exception called ConflictException is thrown to ensure that the issue is
 * handled by the user and the program can properly continue running.
 * 
 * @author Gabriel Perez
 *
 */
public interface Conflict {
	/**
	 * Accepts an Activity object as a parameter and determines if the object
	 * creates a conflict with other activities in the project and if a conflict is
	 * created the method throws a ConflictException.
	 * 
	 * @param possibleConflictingActivity an activity object passed into the method
	 *                                    as a parameter.
	 * @throws ConflictException if the Activity object overlaps with an existing
	 *                           activity's time even by a minute a
	 *                           ConflictException is thrown.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
