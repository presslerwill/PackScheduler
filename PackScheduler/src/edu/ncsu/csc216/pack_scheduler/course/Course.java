/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Course class is declared here which contains multiple methods that assist in
 * setting up the Course object. It does this by checking that meeting days,
 * section number, instructorId, start and end times are formatted correctly.
 * This class also overrides the equals(), hashcode(), and toString() methods to
 * fit the requirements of the class. it has getters and setters for Name,
 * Section, Credits, and InstructorId which are required to generate a course
 * object. This class helps in generating the course object that will be used
 * throughout the entirety of the project and the course object will be the
 * basis of having detailed course information for the project. Also Course
 * class is a child class of Activity class and extends its functionality.
 * 
 * @author Gabriel Perez
 *
 */
public class Course extends Activity implements Comparable<Course> {

	/** The minimum length of course name */
	private static final int MIN_NAME_LENGTH = 4;
	/** The maximum length of course name */
	private static final int MAX_NAME_LENGTH = 8;
	/** The length of the section number */
	private static final int SECTION_LENGTH = 3;
	/** The maximum credits for a course */
	private static final int MAX_CREDITS = 5;
	/** The minimum credits for a course */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Object that validates the name of the course is valid */
	private CourseNameValidator validator;
	/** CourseRoll object to hold student registered for the course */
	private CourseRoll roll;

	/**
	 * Returns the Course's name.
	 * 
	 * @return name the name of the Course
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * 3 trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		// Throw exception is name is null
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Throw exception if the name is an empty string
		// Throw exception if the name contains less than 5 character or greater than 8
		// characters
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		try {
			validator.isValid(name);
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;

	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return section the section of the Course
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section. Checks to make sure the section number is exactly
	 * 3 digits it it is not an IllegalArgumentException is thrown.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is invalid
	 */
	public void setSection(String section) {

		// checks if section is not equal to three or null
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		// iterates through section and makes sure no letters are contained in the
		// string
		for (int i = 0; i < section.length(); i++) {
			if (Character.isLetter(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}

		this.section = section;
	}

	/**
	 * Returns the Course's credits.
	 * 
	 * @return credits the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits. Checks to credits to ensure it is a number and
	 * between the values of 1 and 5, inclusive. If it is not then an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits is invalid
	 */
	public void setCredits(int credits) {
		// checks credits to ensure it is not less than minCredits or greater than
		// maxCredits
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}

		this.credits = credits;
	}

	/**
	 * Returns the Course instructor's ID.
	 * 
	 * @return instructorId the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course instructor's ID. Checks instructorId is not null or an empty
	 * string, if it is an IllegalArgumentException is thrown.
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId is invalid
	 */
	public void setInstructorId(String instructorId) {
		// conducts the test to see if instructorId is null or an empty string
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}

		this.instructorId = instructorId;
	}

	/**
	 * Returns the CourseRoll for this course.
	 * 
	 * @return the CourseRoll for this course.
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

	/**
	 * Overrides the setMeetingDaysAndTime() method from the parent Activity.java
	 * class and ensures that this method meets Use Case 1 requirements. Only deals
	 * with meeting days string and lets parent class Activity.java handle the start
	 * and end times.
	 * 
	 * @throws IllegalArgumentException is thrown if meetingDays has invalid input
	 *                                  as stated in Use Case 1
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// if meetingDays is null calls the parent setMeetingDaysAndTime() method
		if (meetingDays == null) {
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}

		// checks if meetingDays is equal "A"
		if ("A".equals(meetingDays)) {
			// checks that startTime and endTime is zero. otherwise throws an
			// IllegalArgumentException
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			// sets parameters to correct information when meetingDays is "A"
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}

		// if meetingDays is not equal to "A" this code executes
		else {
			// creates counter variables for each weekday
			int mCount = 0;
			int tCount = 0;
			int wCount = 0;
			int hCount = 0;
			int fCount = 0;

			// for loop that iterates through each char in meetingDays and increases the
			// respective letter counter if the current testChar matches the weekday.
			// Otherwise an IllegalArgumentException is thrown due to being an invalid
			// letter.
			for (int i = 0; i < meetingDays.length(); i++) {
				// test variable to reduce redundancy that holds current character at i index
				// inside meetingDays string.
				char testChar = meetingDays.charAt(i);

				// checks if the current testChar matches the respective weekday and then adds
				// to its respective counter if it does match.
				if (testChar == 'M') {
					mCount++;
				} else if (testChar == 'T') {
					tCount++;
				} else if (testChar == 'W') {
					wCount++;
				} else if (testChar == 'H') {
					hCount++;
				} else if (testChar == 'F') {
					fCount++;
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}

			// checks if any counter variables was greater than one, if it was then an
			// IllegalArgumentException is thrown
			if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		// custom toString() code copied from guided project task 4
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
				+ roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * The hash code method is used to return the listed objects hash codes.
	 * 
	 * @return objects hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(credits, instructorId, name, section);
		return result;
	}

	/**
	 * Determines if obj is equal using boolean logic and returns a boolean variable
	 * determining if the object is equal or not.
	 * 
	 * @return boolean value
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return credits == other.credits && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(name, other.name) && Objects.equals(section, other.section);
	}

	/**
	 * Constructs a Course object with values for all fields: name, title, section,
	 * credits, instructorId, meetingDays, startTime, and endTime for courses that
	 * are not arranged.
	 * 
	 * @param name          name of Course
	 * @param title         title of Course
	 * @param section       section of Course
	 * @param credits       credit hours for Course
	 * @param instructorId  instructor's unity Id
	 * @param meetingDays   meeting days for Course as series of chars
	 * @param startTime     start time for Course
	 * @param endTime       end time for Course
	 * @param enrollmentCap the maximum number of students who can enroll
	 * @throws IllegalArgumentException if the name parameter is invalid, section is
	 *                                  invalid, credits is invalid, instructorId is
	 *                                  invalid, title is invalid, either
	 *                                  meetingDays, startTime, or endTime are
	 *                                  invalid
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		// placed the code to check for invalid parameters in each of the setter methods
		// and calls the setter methods from the constructor
		super(title, meetingDays, startTime, endTime);
		roll = new CourseRoll(this, enrollmentCap);
		validator = new CourseNameValidator();
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorID,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity Id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param enrollmentCap the maximum number of Students who can enroll
	 * @throws IllegalArgumentException if the name parameter is invalid, section is
	 *                                  invalid, credits is invalid, instructorId is
	 *                                  invalid, title is invalid, either
	 *                                  meetingDays, startTime, or endTime are
	 *                                  invalid
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays) {
		// to reduce redundancy this constructor with 6 fields calls the course
		// constructor with 8 fields so that the fields are checked in only one
		// constructor
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns an array of length 4 containing the Course name, section, title, and
	 * meeting string.
	 * 
	 * @return string array of length 4
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[5];

		shortDisplay[0] = name;
		shortDisplay[1] = section;
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		shortDisplay[4] = Integer.toString(roll.getOpenSeats());

		return shortDisplay;
	}



	/**
	 * Returns an array of length 7 containing the Course name, section, title,
	 * credits, instructorId, meeting string, empty string (for a field that Event
	 * will have that Course does not).
	 * 
	 * @return string array of length 7
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];

		longDisplay[0] = name;
		longDisplay[1] = section;
		longDisplay[2] = getTitle();
		longDisplay[3] = Integer.toString(credits);
		longDisplay[4] = instructorId;
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";

		return longDisplay;
	}

	/**
	 * Checks if activity is an instance of a Course Object and if so it casts
	 * activity to a Course type and compares the name of other to the current name
	 * instance of the variable using the this.name keyword.
	 * 
	 * @return true if other.getName() equals this.name otherwise false
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		// determines if activity is instance of Course object
		if (activity instanceof Course) {
			// casts activity to a Course object and sets it equal to other
			Course other = (Course) activity;
			// determines if the name of other is equal to the current instance name and
			// returns true if condition is satisfied
			if (other.getName().equals(this.name)) {
				return true;
			}
		}

		// returns false if above statements are false
		return false;
	}

	/**
	 * This method compares the current instance to the Course object that is
	 * defined as the parameter. If the current instance should come before the
	 * argument, the method will return a negative number. If the current instance
	 * comes after, the method will return a positive number. If the Courses are
	 * equal, 0 will be returned. The return value is based solely on the Course's
	 * name and section
	 * 
	 * @param c Course that is being compared to the current instance
	 * @throws NullPointerException "Object is null" If the object passed into the
	 *                              method is null.
	 */
	@Override
	public int compareTo(Course c) {
		// checks if s is a null object
		if (c == null) {
			throw new NullPointerException("Object is null");
		}

		// creates integer variables that hold the values returned from the String
		// compareTo() method for use in sorting later in the method.
		int nameInt = name.compareTo(c.getName());
		int sectionInt = section.compareTo(c.getSection());

		// checks if lastInt is positive or negative and returns the proper result
		if (nameInt > 0) {
			return 1;
		} else if (nameInt < 0) {
			return -1;
		} else {
			// checks if firstInt is positive or negative and returns the proper result
			if (sectionInt > 0) {
				return 1;
			} else if (sectionInt < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}

}
