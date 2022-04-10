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

import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
/**
 * Tests the FacultyRecordIO class's methods. This class generates valid faculty data strings to be
 * used in tests and helps ensure line coverage for our class. It contains the
 * helper method setUp() which helps set up the files used in the test methods
 * later on. This class also has the method checkFiles() to compare the data held within
 * files and ensure they are correct.
 * 
 * @author Jason Wong
 *
 */
public class FacultyRecordIOTest {
	/** String for proper faculty data format in txt files */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	/** String for proper faculty data format in txt files */
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	/** String for proper faculty data format in txt files */
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	/** String for proper faculty data format in txt files */
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	/** String for proper faculty data format in txt files */
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	/** String for proper faculty data format in txt files */
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	/** String for proper faculty data format in txt files */
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	/** String for proper faculty data format in txt files */
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";

	/** Constructs a String array of all the valid faculty data strings */
	private String[] validFaculty = { validFaculty3, validFaculty6, validFaculty4, validFaculty5, validFaculty2,
			validFaculty0, validFaculty1, validFaculty7 };

	/** hashPW field that holds the hashed PassWord value */
	private String hashPW;
	/** The hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Resets faculty_records.txt for use in other tests.
	 */
	@BeforeEach
	public void setUp() {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = Base64.getEncoder().encodeToString(digest.digest());

			for (int i = 0; i < validFaculty.length; i++) {
				validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Test method to ensure that readFacultyRecords() method in the
	 * FacultyRecordIO class properly meets the requirements for the project.
	 */
	@Test
	public void testReadFacultyRecords() {
		try {
			LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			assertEquals(10, faculty.size());

			for (int i = 0; i < validFaculty.length; i++) {
				assertEquals(validFaculty[i], faculty.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + "test-files/faculty_records.txt");
		}
	}

	/**
	 * Tests readFacultyRecords() for invalid input.
	 */
	@Test
	public void testReadInvalidFacultyRecords() {
		// creates LinkedList of Faculty type named faculty
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		try {
			//this should throw an IllegalArgumentException
			faculty = FacultyRecordIO.readFacultyRecords("test-files/invalid_faculty_records.txt");
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		} catch (IllegalArgumentException IAE) {
			assertEquals(0, faculty.size());
		}
	}

	/**
	 * Tests writeFacultyRecords()
	 */
	@Test
	public void testWriteFacultyRecords() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		//Only add one faculty member because that's all the output in expected_faculty_records.txt
		faculty.add(new Faculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15)); // TODO
		
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", faculty);
			checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
		} catch (IOException e) {
			fail("Cannot write to faculty records file");
		}
	}

	/**
	 * Tests writeFacultyRecords() method for cases when IOException should be thrown
	 */
	@Test
	public void testWriteFacultyRecordsNoPermissions() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		faculty.add(new Faculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15)); // TODO

		Exception exception = assertThrows(IOException.class,
				() -> FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_faculty_records.txt", faculty));
		assertEquals("/home/sesmith5/actual_faculty_records.txt (Permission denied)", exception.getMessage());
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