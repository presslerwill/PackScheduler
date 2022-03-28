package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Class for User objects, with the fields for a first name, last name, id,
 * email, and hashed password. Includes methods to get and set these fields, and
 * compare other User objects to the current instance
 * 
 * @author Marshall Pearson
 * @author Will Pressler
 * @author Arch Flanagan
 *
 */
public abstract class User {

	/** Field for the User's first name */
	private String firstName;
	/** Field for the User's last name */
	private String lastName;
	/** Field for the User's ID */
	private String id;
	/** Field for the User's email */
	private String email;
	/** Field for the User's password */
	private String password;

	/**
	 * Constructs a User class and sets the state of the objects to reflect the
	 * parameters
	 * 
	 * @param firstName User's first name
	 * @param lastName  User's last name
	 * @param id        User's id
	 * @param email     User's email
	 * @param password  User's hashed password
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		super();
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setId(id);
		this.setEmail(email);
		this.setPassword(password);
	}

	/**
	 * Overrides the parent's hashCode method to take into account added fields in
	 * the class
	 * 
	 * @return int The objects hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Overrides the parent's equals method to take into account added fields in the
	 * class
	 * 
	 * @return boolean True if the object is the same as the current instance
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (!email.equals(other.email))
			return false;
		if (!firstName.equals(other.firstName))
			return false;
		if (!id.equals(other.id))
			return false;
		if (!lastName.equals(other.lastName))
			return false;
		
		return password.equals(other.password);
	}

	/**
	 * Gets the User's first name
	 * 
	 * @return String the student's first name
	 */
	public String getFirstName() {

		return firstName;
	}

	/**
	 * Sets the first name of User object
	 * 
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if firstName is null or empty string
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}

		this.firstName = firstName;
	}

	/**
	 * Gets the User's last name
	 * 
	 * @return String the student's last name
	 */
	public String getLastName() {

		return lastName;
	}

	/**
	 * Sets the last name of the User object
	 * 
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if lastName is null or empty
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}

		this.lastName = lastName;
	}

	/**
	 * Gets the User's ID
	 * 
	 * @return String the User's ID
	 */
	public String getId() {

		return id;
	}

	/**
	 * Sets the id for the User object
	 * 
	 * @param id the id to set
	 * @throws IllegalArgumentException if id is null or empty
	 */
	protected void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}

		this.id = id;
	}

	/**
	 * Gets the email of the User.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address for the User.
	 * 
	 * @param email the email to set
	 * @throws IllegalArgumentException if email is null or empty, if it does not
	 *                                  contain "@" or "." or if the index for "."
	 *                                  is equal to zero
	 */
	public void setEmail(String email) {
		int indDot = 0;
		int indAt = 0;

		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}

		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}

		if (!email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}

		indAt = email.indexOf('@');

		for (int i = indAt; i < email.length(); i++) {
			if (email.charAt(i) == '.') {
				indDot = i;
			}
		}

		if (indDot == 0) {
			throw new IllegalArgumentException("Invalid email");
		}

		this.email = email;
	}

	/**
	 * Gets the password for the User.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password for the User object.
	 * 
	 * @param password the password to set
	 * @throws IllegalArgumentException if password is null or empty
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}

		this.password = password;
	}

}