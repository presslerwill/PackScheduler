package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Creates a class for the student object and has fields of firstName, lastName,
 * id, email, password, and maxCredits. Alongside a constant of MAX_CREDITS.
 * This is the basis of creating student objects that are used in all other
 * classes in some way or another it creates the Student type which is used for
 * SortedLists created in other classes. Also this class contains the Student
 * Constructor, the getters and setters for the fields, overrides for the
 * hashcode() equals() and toString methods.
 * 
 * @author Gabriel Perez
 * @author Will Pressler
 * @author Aaron Jong
 *
 */
public class Student extends User implements Comparable<Student> {

	/** Constant for the maximum credits */
	public static final int MAX_CREDITS = 18;

	/** Field for the maximum credits a student can take */
	private int maxCredits;

	/**
	 * Schedule object that handles adding and managing course enrollment and other
	 * scheduled tasks
	 */
	private Schedule schedule;

	/**
	 * Creates a Student object with all the fields as parameters.
	 * 
	 * @param firstName  first name of the student
	 * @param lastName   last name of the student
	 * @param id         id of the student
	 * @param email      email of the student
	 * @param password   password of the student
	 * @param maxCredits the maximum credits of a student
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
		schedule = new Schedule(); 
	}

	/**
	 * Creates a Student object with the fields and max credits set as 18.
	 * 
	 * @param firstName first name of the student
	 * @param lastName  last name of the student
	 * @param id        id of the student
	 * @param email     email of the student
	 * @param password  password of the student
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS); 
	}

	/**
	 * Gets the maximum credits for the Student object.
	 * 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the max credits for the Student object.
	 * 
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if maxCredits are less than 3 or greater
	 *                                  than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}

		this.maxCredits = maxCredits;
	}

	/**
	 * creates a new way that toString operates that suits the needs of the program
	 * 
	 * @return string of concatenated fields
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ getMaxCredits();
	}

	/**
	 * Compares this object with the specified object for order. Returns a negative
	 * integer, zero, or a positive integer as this object is less than, equal to,
	 * or greater than the specified object.
	 */
	@Override
	public int compareTo(Student s) {
		// checks if s is a null object
		if (s == null) {
			throw new NullPointerException("Object is null");
		}

		// creates integer variables that hold the values returned from the String
		// compareTo() method for use in sorting later in the method.
		int lastInt = getLastName().compareTo(s.getLastName());
		int firstInt = getFirstName().compareTo(s.getFirstName());
		int unityInt = getId().compareTo(s.getId());

		// checks if lastInt is positive or negative and returns the proper result
		if (lastInt > 0) {
			return 1;
		} else if (lastInt < 0) {
			return -1;
		} else {
			// checks if firstInt is positive or negative and returns the proper result
			if (firstInt > 0) {
				return 1;
			} else if (firstInt < 0) {
				return -1;
			} else {
				// checks if unityInt is positive or negative and returns the proper result
				if (unityInt > 0) {
					return 1;
				} else if (unityInt < 0) {
					return -1;
				} else {
					// returns 0 since all variables are neither greater than or less than 0
					return 0;
				}
			}
		}
	}

	/**
	 * Changes the hashCode method to suit the needs of the program using the fields
	 * 
	 * @return int Objects unique hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Changes the equals method to compare the objects used in the program and
	 * properly determine their equivalency
	 * 
	 * @return boolean value of if objects are equivalent
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		Student student = (Student) obj; 
		
		if (this == student)
			return true;
		if (!super.equals(student))
			return false;
		if (getClass() != student.getClass())
			return false;
		return !(maxCredits != student.maxCredits);
	}

	/**
	 * Gets the schedule object attached to this student. This object describes all
	 * aspects of the Student's enrollment in courses and other tasks on the
	 * schedule
	 * 
	 * @return The current schedule object attached to this student
	 */
	public Schedule getSchedule() {
		return this.schedule;
	}
	
	/**
	 * Checks if the student can add a course to their schedule based on their max credits
	 * @return boolean true if the course can be added, false otherwise
	 * @param course the course trying to be added to the student's schedule
	 */
	public boolean canAdd(Course course) {
		boolean canAdd = false;
		if (this.schedule.canAdd(course) && course.getCredits() + this.schedule.getScheduleCredits() <= this.maxCredits) {
			return true;
		}
		else if (course.getCredits() + this.schedule.getScheduleCredits() > this.maxCredits){
			return canAdd;
		}
		return canAdd;
	}
}
