/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Tests the StudentRecordIO.java class's methods readStudentRecords() and
 * writeStudentRecords(). This class generates valid student data strings to be
 * used in tests and helps ensure line coverage for our class. It contains the
 * helper method setUp() which helps set up the files used in the test methods
 * later on. Also has the method checkFiles() to compare the data held within
 * files and ensure they are correct.
 * 
 * @author Gabriel Perez
 *
 */
class StudentRecordIOTest {
	/** String for proper student data format in txt files */
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** String for proper student data format in txt files */
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** String for proper student data format in txt files */
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** String for proper student data format in txt files */
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** String for proper student data format in txt files */
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** String for proper student data format in txt files */
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** String for proper student data format in txt files */
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** String for proper student data format in txt files */
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** String for proper student data format in txt files */
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** String for proper student data format in txt files */
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

	/** Constructs a String array of all the valid student data strings */
	private String[] validStudents = { validStudent3, validStudent6, validStudent4, validStudent5, validStudent2,
			validStudent8, validStudent0, validStudent9, validStudent1, validStudent7 };

	/** hashPW field that holds the hashed PassWord value */
	private String hashPW;
	/** The hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Resets student_records.txt for use in other tests.
	 */
	@BeforeEach
	public void setUp() {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = Base64.getEncoder().encodeToString(digest.digest());

			for (int i = 0; i < validStudents.length; i++) {
				validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Test method to ensure that readStudentRecords() method in the
	 * StudentRecordIO.java class properly meets the requirements for the project.
	 */
	@Test
	public void testReadStudentRecords() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
			assertEquals(10, students.size());

			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], students.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + "test-files/student_records.txt");
		}
	}

	/**
	 * Tests readStudentRecords() for invalid input.
	 */
	@Test
	public void testReadInvalidStudentRecords() {
		// creates SortedList of Student type named student
		SortedList<Student> students = new SortedList<Student>();
		try {
			//this should throw an IllegalArgumentException
			students = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		} catch (IllegalArgumentException IAE) {
			assertEquals(0, students.size());
			//checkFiles("", "test-files/invalid_student_records.txt");
		}
	}

	/**
	 * Tests writeStudentRecords()
	 */
	@Test
	public void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();
		//Only add one student because that's all the output in expected_student_records.txt
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
			checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		} catch (IOException e) {
			fail("Cannot write to course records file");
		}
	}

	/**
	 * tests writeStudentRecordsNoPermissions() method for IOException is thrown
	 * when expected.
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));

		Exception exception = assertThrows(IOException.class,
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
		assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", exception.getMessage());
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
				Scanner actScanner = new Scanner(new FileInputStream(actFile));) {

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act);
				// The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
