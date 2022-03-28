/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * This class was created to handle the representation of a schedule with
 * courses, allowing for adding, removing, and viewing Courses in the schedule.
 * The schedule can also be easily reset, and has a title that can be set and
 * retrieved.
 * 
 * @author Will Pressler
 * @author Marshall Pearson
 */
public class Schedule {

	/** All the courses in the Schedule */
	private ArrayList<Course> schedule;
	/** The schedule's title */
	private String title;

	/**
	 * Constructor for the Schedule
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		setTitle("My Schedule");

	}

	/**
	 * Adds a course to the Schedule
	 * 
	 * @param c Course to be added
	 * @return boolean true if the addition was successful
	 * @throws IllegalArgumentException When the course cannot be added, usually
	 *                                  either because the student is already
	 *                                  enrolled, or there is a time conflict in the
	 *                                  schedule
	 */
	public boolean addCourseToSchedule(Course c) {
		for (int i = 0; i < schedule.size(); i++) {
			if (c.getName().equals(schedule.get(i).getName())) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
			try {
				schedule.get(i).checkConflict(c);
			} catch (Exception e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}

		}

		schedule.add(c);
		return true;
	}

	/**
	 * Removes a course from the Schedule
	 * 
	 * @param c Course to be removed
	 * @return boolean true if the removal was successful
	 */
	public boolean removeCourseFromSchedule(Course c) {
		for (int i = 0; i < schedule.size(); i++) {
			Course current = schedule.get(i);
			if (c == current) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the entire Schedule
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}

	/**
	 * Gets all the scheduled courses as an array
	 * 
	 * @return String[][] an array representation of all the Courses in the Schedule
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduledCourses = new String[schedule.size()][4];

		if (schedule.size() == 0) {
			return scheduledCourses;
		}
		for (int i = 0; i < schedule.size(); i++) {
			Course c = schedule.get(i);
			scheduledCourses[i] = c.getShortDisplayArray();

		}
		return scheduledCourses;
	}

	/**
	 * Sets the title of the Schedule
	 * 
	 * @param s the new title
	 * @throws IllegalArgumentException If title is null
	 */
	public void setTitle(String s) {
		if (s == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = s;
	}

	/**
	 * Gets the Schedule's title
	 * 
	 * @return string the title of the Schedule
	 */
	public String getTitle() {
		return this.title;
	}

	// cumulative sum of all credits in schedule
	/**
	 * Gets the cumulative sum of all credits in the schedule
	 * @return int the number of credits in the schedule
	 */
	public int getScheduleCredits() {
		int sum = 0;
		for (Course course : this.schedule) {
			sum += course.getCredits();
		}
		return sum;

	}

	// returns true if the course can be added to the sched. if any problems, return
	// false
	/**
	 * Returns true if the course can be added to the schedule, if not returns false
	 * @param course the course that is trying to be added to the schedule
	 * @return boolean true if the course can be added 
	 */
	public boolean canAdd(Course course) {
		try {
			if (course == null) {
				throw new IllegalArgumentException();
			}
			for (Course check : this.schedule) {
				check.checkConflict(course);
				if (check.getName().equals(course.getName())) {
					throw new IllegalArgumentException(); 
				}
			}
		} catch (Exception e) {
			return false; 
		}

		return true;
	}
}
