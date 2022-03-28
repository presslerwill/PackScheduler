/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Creates a Catalog of Course objects. Can load in courses to the Catalog from
 * a file, add Courses to the Catalog, remove Courses from the Catalog. Also
 * gets a specific Course from the Catalog or the entire Catalog. Also saves the
 * Catalog to a text file.
 * 
 * @author Will Pressler
 *
 */
public class CourseCatalog {

	/** Sorted list of courses that makes up the course catalog */
	private SortedList<Course> catalog;


	/**
	 * Constructor for an empty course catalog.
	 */
	public CourseCatalog() {
		this.catalog = new SortedList<Course>();
	}

	/**
	 * Creates an empty course catalog when called.
	 */
	public void newCourseCatalog() {
		this.catalog = new SortedList<Course>();
	}

	/**
	 * Loads in courses from a file to populate the course catalog.
	 * 
	 * @param filename the filename with courses
	 * @throws IllegalArgumentException if the file cannot be found
	 */
	public void loadCoursesFromFile(String filename) {
		try {
			this.catalog = CourseRecordIO.readCourseRecords(filename);
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Adds a course with the parameters to the Catalog. Returns a boolean whether
	 * or not it was added successfully.
	 * 
	 * @param name         Course name
	 * @param title        Course title
	 * @param section      Course section
	 * @param credits      Course credits
	 * @param instructorId the Course's instructor's id
	 * @param enrollmentCap The maximum amount of students that can enroll into the Course
	 * @param meetingDays  Course meeting days
	 * @param startTime    Course starting time
	 * @param endTime      Course ending time
	 * @return boolean true if course is added, false if course is already in
	 *         catalog
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {

		Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);

		for (int i = 0; i < catalog.size(); i++) {
			if (c.getName().equals(catalog.get(i).getName()) && c.getSection().equals(catalog.get(i).getSection())) {
				return false;
			}
		}
		catalog.add(c);
		return true;
	}

	/**
	 * Removes a Course from the catalog. Returns true if the removal was
	 * successful, and false if the Course was not in the catalog to begin with.
	 * 
	 * @param name    the name of the course to be removed
	 * @param section the section of the course to be removed
	 * @return boolean true if the course removal was successful
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets a course from the catalog with the given name and section.
	 * 
	 * @param name    Course's name
	 * @param section Course's section
	 * @return Course specified by name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection())) {
				return catalog.get(i);
			}
		}

		return null;
	}

	/**
	 * Returns a 2D string array of the catalog, has a row for each course and
	 * columns for the name, section, and title. Returns an empty array if there are
	 * no courses in the catalog.
	 * 
	 * @return courseCatalog array with course name, section, and title
	 */
	public String[][] getCourseCatalog() {
		// creates 2D array of courses
		String[][] courseCatalog = new String[catalog.size()][4];

		// returns empty string if there are no courses
		if (catalog.size() == 0) {
			return courseCatalog;
		}

		// iterates through array and adds the courses to it
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			courseCatalog[i] = c.getShortDisplayArray();

		}
		return courseCatalog;
	}

	/**
	 * Saves the course catalog as a text file.
	 * 
	 * @param fileName the name of the file where the Course Catalog will be saved
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public void saveCourseCatalog(String fileName) {

		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}


}
