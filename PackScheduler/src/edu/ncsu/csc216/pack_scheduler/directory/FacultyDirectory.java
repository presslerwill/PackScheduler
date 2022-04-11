/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Maintains a directory of all Faculty working at NC State. it has the
 * facultyDirectory field and HASH_ALGORITHM fields. It's methods create new
 * faculty directories, load faculty into directory from a file, add faculty to
 * directory, hashes passwords, removes faculty, gets facultyDirectory, and
 * exports student directory to a file using the FacultyRecordIO class methods.
 * All Faculty have a unique id.
 * 
 * @author Will Pressler
 *
 */
public class FacultyDirectory {

	/** Linked List of Faculty in the Directory */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Constructs a new directory and initializes the facultyDirectory field
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * Sets the directory to a new empty linkedlist
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}

	/**
	 * Loads a list of faculty records from a file
	 * 
	 * @param file name of file to be loaded
	 * @throws IllegalArgumentException if unable to read file
	 */
	public void loadFacultyFromFile(String file) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(file);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + file);
		}
	}

	/**
	 * Adds a faculty member to the list. Returns true if the faculty is added and
	 * false if the faculty is unable to be added because their id matches another
	 * faculty's id.
	 * 
	 * This method also hashes the faculty's password for internal storage.
	 * 
	 * @param firstName      of the Faculty
	 * @param lastName       of the Faculty
	 * @param id             of the Faculty
	 * @param email          of the Faculty
	 * @param password       of the Faculty
	 * @param maxCourses     of the Faculty
	 * @param repeatPassword Faculty password must be entered twice
	 * @return boolean true if the add was successful, false otherwise
	 * @throws IllegalArgumentException if password is invalid or passwords do not match
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}

		hashPW = hashString(password);
		repeatHashPW = hashString(repeatPassword);

		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}

		// If an IllegalArgumentException is thrown, it's passed up from Student
		// to the GUI
		Faculty faculty = null;
		if (maxCourses >= Faculty.MIN_COURSES && maxCourses <= Faculty.MAX_COURSES) {
			faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		}

		for (int i = 0; i < facultyDirectory.size(); i++) {
			User f = facultyDirectory.get(i);
			if (f.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
	}

	/**
	 * Hashes a String according to the SHA-256 algorithm, and outputs the digest in
	 * base64 encoding. This allows the encoded digest to be safely copied, as it
	 * only uses [a-zA-Z0-9+/=].
	 * 
	 * @param toHash the String to hash
	 * @return the encoded digest of the hash algorithm in base64
	 * @throws IllegalArgumentException "Cannot hash password" If password cannot be
	 *                                  hashed
	 */
	private static String hashString(String toHash) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(toHash.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Removes a Faculty from the directory with the given id
	 * 
	 * @param facultyId id of the Faculty to be removed
	 * @return boolean true if the removal was successful
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User f = facultyDirectory.get(i);
			if (f.getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a 2D array representation of the Faculty Directory with the Faculty's
	 * first name, last name, and id.
	 * 
	 * @return String[][] representation of the Directory
	 */
	public String[][] getFacultyDirectory() {

		String[][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}

	/**
	 * Saves all faculty in the directory to a file.
	 * @param fileName name of file to save facultyDirectory to.
	 * @throws IllegalArgumentException "Unable to write to file [filename]" If file
	 *                                  cannot written to for some reason. Ensure
	 *                                  file name is correct and that no other
	 *                                  program is accessing the file
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Gets a Faculty from the Directory as specified by their Id
	 * @param facultyId the id of the desired Faculty
	 * @return Faculty with the given id
	 */
	public Faculty getFacultyById(String facultyId) {
		Faculty f = null;
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if (facultyDirectory.get(i).getId().equals(facultyId)) {
				f = facultyDirectory.get(i);
			}
		}

		return f;
	}
}
