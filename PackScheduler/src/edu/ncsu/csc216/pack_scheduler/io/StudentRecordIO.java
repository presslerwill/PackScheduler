package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Handles input of Student records from files and outputs student records as
 * files. readStudentRecords takes a fileName and tokenizes the information
 * within using the processStudent method by passing a single line of text from
 * the file into it for each time called. writeStudentRecords is used to write
 * studentDirectory out to a new file. StudentDirectory.java uses the methods of
 * this class to create and manipulate student directories.
 * 
 * @author Will Pressler
 * @author Gabriel Perez-Botello
 * @author Aaron Jong
 *
 */
public class StudentRecordIO {

	/**
	 * Reads student records from a file and creates a list of Students.
	 * 
	 * @param fileName input file's name
	 * @return students a list of Students
	 * @throws FileNotFoundException if file does not exist
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		try {
			// fileReader scanner is used to read fileName
			Scanner fileReader = new Scanner(new FileInputStream(fileName));

			// SortedList of students is created and given Student type
			SortedList<Student> students = new SortedList<Student>();

			// if fileReader has next line the loop repeats

			while (fileReader.hasNextLine()) {
				// student object is created and a call to processStudent() is made to assign
				// its value
				Student student = processStudent(fileReader.nextLine());

				// duplicate is a boolean variable that determines if a duplicate is found
				boolean duplicate = false;

				// loop that iterates through each element in students SortedList
				for (int i = 0; i < students.size(); i++) {

					// current variable is given current element being analyzed from students
					// SortedList
					User current = students.get(i);

					// if student is equivalent to current the variable duplicate is set to true and
					// the loop is exited
					if (student != null && student.equals(current)) {
						duplicate = true;
						break;
					}

				}

				// if duplicate is false student object is added to students SortedList
				if (!duplicate && student != null) {
					students.add(student);
				}
			}

			// closes fileReader and returns students SortedList
			fileReader.close();
			return students;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Writes the SortedList of students to a file containing their information.
	 * 
	 * @param fileName         the file of the directory will be exported to
	 * @param studentDirectory the directory of all the Student objects
	 * @throws IOException if cannot write to file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		try {
			// PrintStream fileWriter is created and given fileName as the name for the new
			// file created
			PrintStream fileWriter = new PrintStream(new File(fileName));

			// loop iterates for each element of studentDirectory
			for (int i = 0; i < studentDirectory.size(); i++) {
				// fileWriter is used to print the elements of studentDirectory using the get
				// and toString classes
				fileWriter.println(studentDirectory.get(i).toString());
			}

			// fileWriter is closed
			fileWriter.close();

		} catch (IOException e) {
			throw new IOException(fileName + " (Permission denied)");
		}
	}

	/**
	 * Reads in a single line of text as a Student object.
	 * 
	 * @param line of text from input file
	 * @return Student object with correct parameters from file
	 * @throws IllegalArgumentException if the line is not able to be processed
	 */
	private static Student processStudent(String line) {
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
			int maxCredits = Student.MAX_CREDITS;

			// if (fileReader.hasNextInt()) {
			maxCredits = fileReader.nextInt();
			// }

			// if fileReader has another line after reading tokens throws IAE as line has
			// too many tokens
			if (fileReader.hasNext()) {
				fileReader.close();
				throw new IllegalArgumentException("Invalid student string.");
			}

			// if fileReader has correct number of tokens it makes a call to constructor
			// Student() in the Student.java class and constructs Student object
			fileReader.close();
			return new Student(firstName, lastName, id, email, password, maxCredits);
		} catch (Exception e) {
			return null;
		}
	}

}
