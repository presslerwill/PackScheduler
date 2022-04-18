package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Class for the RegistrationManager object. Has fields for the current
 * instance, course catalog, student directory, registrar, current user, hash
 * algorithm, and the properties file. Its functions include creating a
 * registration manager, encoding the User's password, getting the instance of
 * the manager, getting the course catalog and student directory. Logging in and
 * out of the manager, getting the current user, and clearing the data are also
 * implemented.
 * 
 * @author Will Pressler
 * @author Marshall Pearson
 * @author Arch Flanagan
 *
 */
public class RegistrationManager {

	/** The only instance of the RegistrationManager */
	private static RegistrationManager instance;
	/** The course catalog */
	private CourseCatalog courseCatalog;
	/** The student directory */
	private StudentDirectory studentDirectory;
	/** The registrar user */
	private User registrar;
	/** The current User of the registration manager */
	private User currentUser;
	/** the faculty directory */
	private FacultyDirectory facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** The file with registrar properties */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Constructor for the RegistrationManager
	 */
	private RegistrationManager() {
		createRegistrar();
		this.studentDirectory = new StudentDirectory();
		this.courseCatalog = new CourseCatalog();
		this.facultyDirectory = new FacultyDirectory();
	}

	/**
	 * Creates a Registrar based off of the properties file.
	 * 
	 * @throws new IllegalArgumentException if file does not exist
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Returns an encoded password.
	 * 
	 * @param pw the password to be encoded
	 * @return String the encoded password
	 * @throws IllegalArgumentException if the password cannot be hashed
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Gets access to the only instance of the RegistrationManager
	 * 
	 * @return RegistrationManager the only instance
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Gets the course catalog
	 * 
	 * @return CourseCatalog the catalog of courses
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Gets the student directory
	 * 
	 * @return StudentDirectory the directory of students
	 */
	public StudentDirectory getStudentDirectory() {
		return this.studentDirectory;
	}

	/**
	 * Gets the faculty directory of students
	 * 
	 * @return Faculty Directory of students
	 */
	public FacultyDirectory getFacultyDirectory() {
		return this.facultyDirectory;
	}

	/**
	 * Logs into the registration manager
	 * 
	 * @param id       the id of the user
	 * @param password the password of the user
	 * @return boolean true if the login was successful
	 * @throws IllegalArgumentException "User doesn't exist". is thrown when the Id
	 *                                  isn't found in StudentDirectory or in local
	 *                                  properties
	 */
	public boolean login(String id, String password) {
		String localHashPW = hashPW(password);
		Student s = studentDirectory.getStudentById(id);
		Faculty f = facultyDirectory.getFacultyById(id);
		if (currentUser != null) {
			return false;
		}
		if (registrar.getId().equals(id)) {
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			} else {
				return false;
			}

		}

		// student login

		if (s == null && f == null) {
			throw new IllegalArgumentException("User doesn't exist.");
		} 
		if (s != null && s.getPassword().equals(localHashPW)) {
			currentUser = s;
			return true;
		}
		if (f != null && f.getPassword().equals(localHashPW)) {
			currentUser = f;
			return true;
		}

		// faculty login
		
		return false;
	}

	/**
	 * Logs out of the manager
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Gets the current User of the manager
	 * 
	 * @return User the current User
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clears the data in the course catalog and student directory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
//			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
//				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}
	
	/**
	 * Adds a Faculty to the course
	 * @param course to be added
	 * @param f faculty member added to the course
	 * @return true if the addition was successful
	 */
	public boolean addFacultyToCourse(Course course, Faculty f) {
		if (currentUser != null && currentUser == registrar) {
			f.getSchedule().addCourseToSchedule(course);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a Faculty from the course
	 * @param course to be removed
	 * @param f faculty to be removed
	 * @return true if the removal was successful
	 */
	public boolean removeFacultyFromCourse(Course course, Faculty f) {
		if (currentUser != null && currentUser == registrar) {
			f.getSchedule().removeCourseFromSchedule(course);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Resets the schedule of the given faculty
	 * @param f faculty whose schedule will be reset
	 */
	public void resetFacultySchedule(Faculty f) {
		if (currentUser != null && currentUser == registrar) {
			f.getSchedule().resetSchedule();
		}
	}

	/**
	 * The inner class of the Registrar within the User, creates the Registrar as a
	 * User
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user.
		 * 
		 * @param firstName the User's first name
		 * @param lastName  the User's last name
		 * @param id        the User's id
		 * @param email     the User's email
		 * @param hashPW    the User's hashed password
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}