package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Objects;

/**
 * Activity abstract superclass is declared here which contains multiple methods
 * that assist in setting up the Course and Event objects. It does this by
 * checking that title, meeting days, start and end times are formatted
 * correctly. This class also overrides the equals(), hashcode() methods to fit
 * the requirements of the class. it has getters and setters for ShortDisplay,
 * LongDisplay, Title, MeetingDays, StartTime, EndTime, MeetingDaysAndTime, and
 * MeetingString. This class helps in generating the course and event objects
 * that will be used throughout the entirety of the project and the objects will
 * be the basis of having detailed course/event information for the project.
 * 
 * @author Gabriel Perez
 *
 */
public abstract class Activity implements Conflict {

	/** The upper limit for hours */
	private static final int UPPER_HOUR = 24;
	/** The upper limit for minutes */
	private static final int UPPER_MINUTE = 60;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Activity Constructor that helps maintain the one path of construction.
	 * 
	 * @param title       the title for the activity
	 * @param meetingDays the meeting days for the activity
	 * @param startTime   the start time for the activity
	 * @param endTime     the end time for the activity
	 * @throws IllegalArgumentException if title is invalid, and if either
	 *                                  meetingDays, startTime, or endTime are
	 *                                  invalid
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Method checks if the current instance of activity conflicts with the passed
	 * activity object of the method. Two Activity objects are in conflict if there
	 * is at least one day with an overlapping time. A time is overlapping if the
	 * minute is the same.
	 * 
	 * @throws ConflictException if a conflict is found between the current instance
	 *                           of activity and the passed in activity
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		String currentMeetingDays = this.getMeetingDays();
		String checkMeetingDays = possibleConflictingActivity.getMeetingDays();
		boolean conflictExists = false;

		if ("A".equals(checkMeetingDays) || "A".equals(currentMeetingDays)) {
			return;
		}
		for (int i = 0; i < this.getMeetingDays().length(); i++) {
			// iterate through one string and check if the other string contains that
			// character
			if (checkMeetingDays.contains(Character.toString(currentMeetingDays.charAt(i)))) {
				conflictExists = checkTimes(possibleConflictingActivity);
			}
		}

		if (conflictExists) {
			throw new ConflictException();
		}

	}

	/**
	 * Method used by checkConflict to check if times conflict returns a boolean
	 * that determines if a conflict exists or not. Received Help from Matthew Zahn,
	 * for understanding algorithm of checking activity times.
	 * 
	 * @param possibleConflictingActivity the activity object we are checking
	 *                                    against the current instance variable for
	 *                                    conflicts
	 * @return Boolean determines whether times conflict or not
	 */
	private Boolean checkTimes(Activity possibleConflictingActivity) {
		// Integer variables that hold the activity's start and end times
		int checkSTime = possibleConflictingActivity.getStartTime();
		int checkETime = possibleConflictingActivity.getEndTime();
		int currentSTime = this.getStartTime();
		int currentETime = this.getEndTime();

		// check if the possibleConflictingActivity's start time is between the current
		// instance activity's start and end times
		if (currentSTime <= checkSTime && checkSTime <= currentETime) {
			return true;

		}
		// check if the current instance activity's start time is between the
		// possibleConflictingActivity's start and end times
		else if (checkSTime <= currentSTime && currentSTime <= checkETime) {
			return true;
		}

		return false;
	}

	/**
	 * Abstract method that is used to populate the rows of the course catalog and
	 * student schedule.
	 * 
	 * @return string array for short display
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Abstract method that is used to display the final schedule.
	 * 
	 * @return string array for long display
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Abstract method that is used to check if activities are duplicates.
	 * 
	 * @param activity the course/event that needs to be checked
	 * @return boolean that determines if activity is a duplicate of another
	 *         activity that already exists
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * The hash code method is used to return the listed objects hash codes
	 * 
	 * @return objects hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(endTime, meetingDays, startTime, title);
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		return endTime == other.endTime && Objects.equals(meetingDays, other.meetingDays)
				&& startTime == other.startTime && Objects.equals(title, other.title);
	}

	/**
	 * Returns the Course's title.
	 * 
	 * @return title the title of Course
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title. Checks to make sure course title is not null or an
	 * empty string, it if it is then an IllegalArgumentException is thrown.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is invalid
	 */
	public void setTitle(String title) {
		// checks to see if title is null or empty
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}

		this.title = title;
	}

	/**
	 * Returns the Course's meeting days.
	 * 
	 * @return meetingDays the meeting days for the course
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start time.
	 * 
	 * @return startTime the start time for the course
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time.
	 * 
	 * @return endTime the end time for the course
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Course's meeting days, start time, and end time. If meetingDays
	 * consist of any characters other than 'M', 'T', 'W', 'T', 'F', or 'A'. If
	 * meetingDays has a duplicate character, has other characters than 'A' if 'A'
	 * is already part of meetingDays. Has a startTime or endTime outside of valid
	 * military time (between 0000 and 2359.). If the endTime is less than the
	 * startTime or if a startTime or endTime is given when meeting days is 'A'. An
	 * IllegalArgumentException is thrown.
	 * 
	 * @param meetingDays the meeting days for the course
	 * @param startTime   the start time for the course
	 * @param endTime     the end time for the course
	 * @throws IllegalArgumentException if either meetingDays, startTime, or endTime
	 *                                  are invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// checks meetingDays is not null nor an empty string
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// divides startTime and endTime into hours and minutes
		int startHour = startTime / 100;
		int endHour = endTime / 100;
		int startMin = startTime % 100;
		int endMin = endTime % 100;

		// checks that startHour is between 0 and 23 inclusive
		if (startHour > UPPER_HOUR - 1 || startHour < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// checks that startMin is between 0 and 59 inclusive
		if (startMin > UPPER_MINUTE - 1 || startMin < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// checks that endHour is between 0 and 23 inclusive
		if (endHour > UPPER_HOUR - 1 || endHour < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// checks that endMin is between 0 and 59 inclusive
		if (endMin > UPPER_MINUTE - 1 || endMin < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// checks to make sure endTime does not come before startTime
		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns the course's meeting time in format of "MW 1:30PM-2:45PM" for example
	 * for a course that would meet Monday, Wednesday from 1:30PM to 2:45PM.
	 * 
	 * @return meetingString the meeting string for the course that contains the
	 *         required tokens
	 */
	public String getMeetingString() {
		String meetingString = "";

		// constants and variables to be used in this method are declared here
		// conversion is number needed to convert military time to standard time PM
		// hours
		int conversion = 12;

		// creates String variables that will contain the start and end times for course
		// for return string
		String sM = "";
		String eM = "";

		// creates String variables that will either be AM or PM
		String startAmPm = "AM";
		String endAmPm = "AM";

		// calls the setMeetingDaysAndTime() method to verify correct information is
		// given
		setMeetingDaysAndTime(getMeetingDays(), getStartTime(), getEndTime());

		// sets the variables equal to the military time minutes and hour
		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;

		// checks to see if startHour is greater than 12 and if so it subtracts 12 from
		// startHour to convert to standard time PM hours. Also checks if endHour is
		// equal to 12 if so it sets endAmPm to "PM".
		if (startHour > conversion) {
			startHour = startHour - conversion;
			startAmPm = "PM";
		} else if (startHour == conversion) {
			startAmPm = "PM";
		}

		// checks to see if endHour is greater than 12 and if so it subtracts 12 from
		// endHour to convert to standard time PM hours. Also checks if endHour is equal
		// to 12 if so it sets endAmPm to "PM".
		if (endHour > conversion) {
			endHour = endHour - conversion;
			endAmPm = "PM";
		} else if (endHour == conversion) {
			endAmPm = "PM";
		}

		// checks if startMin or endMin is less than zero if it is then it concatenates
		// a 0 in front of the integers and creates a string
		if (startMin < 10) {
			sM = "0" + Integer.toString(startMin);
		} else {
			sM = Integer.toString(startMin);
		}

		if (endMin < 10) {
			eM = "0" + Integer.toString(endMin);
		} else {
			eM = Integer.toString(endMin);
		}

		// Constructs the meetingString variable that will be returned.
		if ("A".equals(meetingDays)) {
			meetingString = "Arranged";
		} else {
			meetingString = meetingDays + " " + startHour + ":" + sM + startAmPm + "-" + endHour + ":" + eM + endAmPm;
		}

		return meetingString;
	}

}