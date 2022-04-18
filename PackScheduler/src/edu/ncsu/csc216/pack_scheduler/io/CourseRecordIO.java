/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Aaron Jong
 * @author Will Pressler
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		SortedList<Course> courses = new SortedList<Course>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try { // Attempt to do the following
					// Read the line, process it in readCourse, and get the object
					// If trying to construct a Course in readCourse() results in an exception, flow
					// of control will transfer to the catch block, below
				Course course = readCourse(fileReader.nextLine());

				// Create a flag to see if the newly created Course is a duplicate of something
				// already in the list
				boolean duplicate = false;
				// Look at all the courses in our list
				for (int i = 0; i < courses.size(); i++) {
					// Get the course at index i
					Course current = courses.get(i);
					// Check if the name and section are the same
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						// It's a duplicate!
						duplicate = true;
						break; // We can break out of the loop, no need to continue searching
					}
				}
				// If the course is NOT a duplicate
				if (!duplicate) {
					courses.add(course); // Add to the SortedList!
				} // Otherwise ignore
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the SortedList with all the courses we read!
		return courses;
	}

	/**
	 * Reads a course from file
	 * 
	 * @param line the line to read
	 * @return the course that was read
	 */
	private static Course readCourse(String line) {

		try {

			Scanner s = new Scanner(line);
			s.useDelimiter(",");
			String name = s.next();
			String title = s.next();
			String section = s.next();
			int credits = s.nextInt();
			String instructorId = s.next();
			int enrollmentCap = s.nextInt();
			String meetingDays = s.next();
			Course ret;

			if (!"A".equals(meetingDays)) {
				int startTime = s.nextInt();
				int endTime = s.nextInt();

				if (s.hasNext()) {
					s.close();
					throw new IllegalArgumentException();
				}

				ret = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime, endTime);
				if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId) != null) {
					RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId).getSchedule()
							.addCourseToSchedule(ret);
				}
				s.close();
				return ret;
			}

			if (s.hasNext()) {
				s.close();
				throw new IllegalArgumentException();
			}

			s.close();
			ret = new Course(name, title, section, credits, null, enrollmentCap, meetingDays);

			if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId) != null) {
				RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId).getSchedule()
						.addCourseToSchedule(ret);
			}
			return ret;
		} catch (Exception e) {

			throw new IllegalArgumentException();
		}

	}

	/**
	 * Writes the given list of Courses to
	 * 
	 * @param fileName file to write schedule of Courses to
	 * @param catalog  list of Courses to write
	 * @throws IOException if cannot write to file
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> catalog) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < catalog.size(); i++) {
			fileWriter.println(catalog.get(i).toString());
		}

		fileWriter.close();

	}

}
