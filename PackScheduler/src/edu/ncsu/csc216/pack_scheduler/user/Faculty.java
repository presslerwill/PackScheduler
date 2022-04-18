/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Creates a class for the Faculty object and has fields of firstName, lastName,
 * id, email, password, and maxCourses. Alongside constants MIN_COURSES and
 * MAX_COURSES. This is the basis of creating Faculty objects as are used in
 * FacultyDirectory. Also this class contains the Faculty Constructor, the
 * getters and setters for the fields, overrides for the hashcode() equals() and
 * toString methods.
 * 
 * @author Will Pressler
 *
 */
public class Faculty extends User {

	/** The number of courses a Faculty can teach in a given semester */
	private int maxCourses;
	/** Minimum number of courses a faculty member can teach in a semester */
	public static final int MIN_COURSES = 1;
	/** Maximum number of courses a faculty member can teach in a semester */
	public static final int MAX_COURSES = 3;
	/** schedule associated with the Faculty object */
	private FacultySchedule schedule;

	/**
	 * Constructs a Faculty object
	 * 
	 * @param firstName  of the Faculty
	 * @param lastName   of the Faculty
	 * @param id         of the Faculty
	 * @param email      address of the Faculty
	 * @param password   of the Faculty
	 * @param maxCourses the Faculty can teach in a semester
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		schedule = new FacultySchedule(id);
		setMaxCourses(maxCourses);
	}

	/**
	 * Sets the maximum number of Courses a Faculty can teach in a semester
	 * 
	 * @param courses the number of Courses that can be taught
	 */
	public void setMaxCourses(int courses) {
		if (courses < 1 || courses > 3) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = courses;
	}

	/**
	 * Gets the max number of Courses the Faculty can teach
	 * 
	 * @return int the max number of Courses the Faculty can teach
	 */
	public int getMaxCourses() {
		return this.maxCourses;
	}

	/**
	 * Changes the hashCode method to suit the needs of the program using the fields
	 * 
	 * @return int the Object's unique hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Changes the equals method to compare the objects used in the program and
	 * properly determine their equivalence
	 * 
	 * @return boolean value of if objects are equivalent
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Faculty)) {
			return false;
		}
		Faculty faculty = (Faculty) obj;

		if (this == faculty)
			return true;
		if (!super.equals(faculty))
			return false;
		if (getClass() != faculty.getClass())
			return false;
		return !(maxCourses != faculty.maxCourses);
	}

	/**
	 * returns the schedule associated with the current instance of faculty
	 * 
	 * @return the schedule of the faculty member
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}

	/**
	 * returns whether the faculty's schedule has more classes than their maximum
	 * number of classes allowed
	 * 
	 * @return whether the schedule of the faculty member is greater than their max
	 *         number allowed
	 */
	public boolean isOverloaded() {
		return schedule.getNumScheduledCourses() > maxCourses;
	}

	/**
	 * Creates a String representation of the Faculty object
	 * 
	 * @return String representation of the Faculty object's fields
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ getMaxCourses();
	}

}
