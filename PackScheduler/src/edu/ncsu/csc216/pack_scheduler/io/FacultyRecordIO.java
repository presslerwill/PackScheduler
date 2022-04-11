package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Handles input of Faculty records from a text file and outputs faculty records as
 * text files. readFacultyRecords takes a fileName and tokenizes the information
 * within using the processFaculty method by passing a single line of text from
 * the file into it for each time called. writeFacultyRecords is used to write
 * the given faculty list out to a new file.
 * 
 * @author Jason Wong
 *
 */
public class FacultyRecordIO {

	/**
	 * Reads faculty records from a file and creates and returns the list of Faculty.
	 * 
	 * @param fileName input file's name
	 * @return a list of Faculty
	 * @throws FileNotFoundException if file does not exist
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		try {
			// fileReader scanner is used to read fileName
			Scanner fileReader = new Scanner(new FileInputStream(fileName));

			// LinkedList of faculty is created and given Faculty type
			LinkedList<Faculty> facultyList = new LinkedList<Faculty>();

			// if fileReader has next line the loop repeats

			while (fileReader.hasNextLine()) {
				// faculty object is created and a call to processFaculty() is made to assign
				// its value
				Faculty faculty = processFaculty(fileReader.nextLine());

				// duplicate is a boolean variable that determines if a duplicate is found
				boolean duplicate = false;

				// loop that iterates through each element in the facultyList LinkedList
				for (int i = 0; i < facultyList.size(); i++) {

					// current variable is given current element being analyzed from faculty LinkedList
					User current = facultyList.get(i);

					// if faculty is equivalent to current the variable duplicate is set to true and
					// the loop is exited
					if (faculty != null && faculty.equals(current)) {
						duplicate = true;
						break;
					}

				}

				// if duplicate is false, faculty object is added to the facultyList
				if (!duplicate && faculty != null) {
					facultyList.add(faculty);
				}
			}

			// closes fileReader and returns the facultyList
			fileReader.close();
			return facultyList;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Writes the LinkedList of faculty to a file containing their information.
	 * 
	 * @param fileName         the file of the directory will be exported to
	 * @param facultyList the list of faculty objects to write
	 * @throws IOException if cannot write to file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyList) throws IOException {
		try {
			// PrintStream fileWriter is created and given fileName as the name for the new
			// file created
			PrintStream fileWriter = new PrintStream(new File(fileName));

			// loop iterates for each element of facultyList
			for (int i = 0; i < facultyList.size(); i++) {
				// fileWriter is used to write the elements of facultyList
				fileWriter.println(facultyList.get(i).toString());
			}

			// fileWriter is closed
			fileWriter.close();

		} catch (IOException e) {
			throw new IOException(fileName + " (Permission denied)");
		}
	}

	/**
	 * Reads in a single line of text as a Faculty object.
	 * 
	 * @param line of text from input file
	 * @return Faculty object with correct parameters from file
	 * @throws IllegalArgumentException if the line is not able to be processed due to too many tokens
	 */
	private static Faculty processFaculty(String line) {
		try {
			// fileReader scanner is used to read line
			Scanner fileReader = new Scanner(line);
			// changes delimiter of scanner to ","
			fileReader.useDelimiter(",");

			// creates a list of string variables to hold the tokens from fileReader
			// scanning line
			String firstName = fileReader.next();
			String lastName = fileReader.next();
			String id = fileReader.next();
			String email = fileReader.next();
			String password = fileReader.next();

			int numCourses = fileReader.nextInt();

			// if fileReader has another line after reading tokens throws IAE as line has
			// too many tokens
			if (fileReader.hasNext()) {
				fileReader.close();
				throw new IllegalArgumentException("Invalid faculty string.");
			}

			// if fileReader has correct number of tokens it makes a call to constructor
			// Faculty() in the Faculty class and constructs Faculty object
			fileReader.close();
			return new Faculty(firstName, lastName, id, email, password, numCourses);
		} catch (Exception e) {
			return null;
		}
	}

}