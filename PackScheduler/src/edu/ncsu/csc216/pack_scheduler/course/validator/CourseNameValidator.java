/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Class for validating course names for an NCSU course registration system. Has
 * a method for validating names. Has fields for each possible state (Initial, Letter,
 * Number, Suffix), one to contain the current state, and two fields to count numbers
 * and letters. Implementation of the finite state machine found in project-docs.
 * 
 * @author Will Pressler
 * @author Arch Flanagan
 *
 */
public class CourseNameValidator {
	/** Counts the number of letters present */
	private int letterCount;
	/** Counts the digits present */
	private int digitCount;
	/** boolean true if the String is in a valid end state, otherwise false. */
	private boolean isValidEndState;
	/** State class instance of CurrentState */
	private State currentState;
	/** State class instance of SuffixState */
	private State stateSuffix;
	/** State class instance of InitialState */
	private State stateInitial;
	/** State class instance of LetterState */
	private State stateLetter;
	/** State class instance of numberState */
	private State stateNumber;
	
	/**
	 * Creates a new CourseNameValidator object and initializes all fields.
	 */
	public CourseNameValidator() {
		stateSuffix = new SuffixState();
		stateInitial = new InitialState();
		stateLetter = new LetterState();
		stateNumber = new NumberState();
		currentState = stateInitial;
		letterCount = 0;
	}
	
	/**
	 * Checks if a string is a valid name for a course
	 * @param s String to be checked for validity
	 * @return true if the string is valid, false if the string follows a valid
	 * 			format until the end, but ends before a valid end state is reached.
	 * @throws InvalidTransitionException Thrown if the String is in an invalid format
	 * 			for a course name.
	 */
	public boolean isValid(String s) throws InvalidTransitionException {
		char c;
		letterCount = 0;
		digitCount = 0;
		currentState = stateInitial;
		isValidEndState = false;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			
			if (Character.isDigit(c)) {
				currentState.onDigit();
			} else if (Character.isLetter(c)) {
				currentState.onLetter();
			} else {
				currentState.onOther();
			}
		}
		if (!isValidEndState) {
			throw new InvalidTransitionException(); 
		}
		
		return isValidEndState;
	}
	
	/**
	 * Abstract inner class for states. Contains abstract methods onLetter, onDigit, 
	 * and onOther. Contains no fields.
	 */
	public abstract class State {
		
		/**
		 * Abstract method for input values that are letters. Makes appropriate
		 * state transitions, depending on the current number of letters and the
		 * current state of the FSM.
		 * @throws InvalidTransitionException if the input does not represent a
		 * 			valid state transition.
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		
		/**
		 * Abstract method for input values that are digits. Makes appropriate
		 * state transitions, depending on the current number of digits.
		 * @throws InvalidTransitionException if the input does not represent a
		 * 			valid state transition.
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		
		/**
		 * Abstract method for input values that are not numbers or letters.
		 * @throws InvalidTransitionException every time it is called, because there
		 * 			are no valid transitions that are not a number or a letter.
		 */
		public abstract void onOther() throws InvalidTransitionException;
		
	}
	
	/**
	 * Internal class for state transitions when the current state is LetterState.
	 * Contains methods for letter input, digit input, and input that is neither.
	 * Contains no fields.
	 * @author press
	 * @author Arch Flanagan
	 *
	 */
	public class LetterState extends State {
		
		/** Constructor for LetterState objects. Does nothing. */
		private LetterState() {
			
		}
		
		/**
		 * Handles transitions for a letter character.
		 * @throws InvalidTransitionException If a letter input is not a valid state transition.
		 */
		public void onLetter() throws InvalidTransitionException {
			if (letterCount < 4) {
				letterCount++;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}
		
		/**
		 * Handles state transitions for a digit input.
		 * @throws InvalidTransitionException if a digit is not a valid input in
		 * 			the current state.
		 */
		public void onDigit() throws InvalidTransitionException {
			digitCount++;
			currentState = stateNumber;
		}
		
		/**
		 * Handles state transitions for an input that is not a digit or a alphabetical
		 * character. Will always throw an InvalidTransitionException
		 * @throws InvalidTransitionException if a non-alphanumeric character is not
		 * 			valid (always).
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
		
	/**
	 * Internal class for state transitions when the current state is SuffixState.
	 * Contains methods for letter input, digit input, and input that is neither.
	 * Contains no fields. All methods throw InvalidTransitionException, because
	 * no input is valid after the Suffix.
	 * @author press
	 * @author Arch Flanagan
	 *
	 */
	public class SuffixState extends State {

		/**
		 * Constructor for a SuffixState object.
		 */
		private SuffixState() {
			
		}
		
		/**
		 * Handles transitions for a letter character. Always throws an InvalidTransitionException
		 * because no input is valid after the suffix.
		 * @throws InvalidTransitionException Always, because no input is valid after
		 * 			the suffix.
		 */
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}
		
		/**
		 * Handles transitions for a digit character. Always throws an InvalidTransitionException
		 * because no input is valid after the suffix.
		 * @throws InvalidTransitionException Always, because no input is valid after
		 * 			the suffix.
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
		
		/**
		 * Handles transitions for a non-alphanumeric character. Always throws an 
		 * InvalidTransitionException because no input is valid after the suffix.
		 * @throws InvalidTransitionException Always, because no input is valid after
		 * 			the suffix.
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * Internal class for state transitions when the current state is InitialState.
	 * Contains methods for letter input, digit input, and input that is neither.
	 * Contains no fields. This is the starting state for the FSM.
	 * @author press
	 * @author Arch Flanagan
	 *
	 */
	public class InitialState extends State {
		
		/**
		 * Constructor for an InitialState object. Does nothing.
		 */
		private InitialState() {
			
		}
		
		/**
		 * Handles transitions for a letter character. Increments the letterCount
		 * variable and sets the current state to LetterState.
		 */
		public void onLetter() throws InvalidTransitionException {
			letterCount++;
			currentState = stateLetter;
		}
		
		/**
		 * Handles transitions for a digit character.
		 * @throws InvalidTransitionException always, because a course name cannot start
		 * with a digit.
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
		
		/**
		 * Handles state transitions for an input that is not a digit or a alphabetical
		 * character. Will always throw an InvalidTransitionException
		 * @throws InvalidTransitionException if a non-alphanumeric character is not
		 * 			valid (always).
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * Internal class for state transitions when the current state is NumberState.
	 * Contains methods for letter input, digit input, and input that is neither.
	 * Contains no fields.
	 * @author press
	 * @author Arch Flanagan
	 *
	 */
	public class NumberState extends State {
		
		/**
		 * Constructor for a NumberState object. Does nothing.
		 */
		private NumberState() {
			
		}
		
		/**
		 * Handles transitions for a letter character. Sets the current state to SuffixState.
		 * @throws InvalidTransitionException if there are not yet three digits.
		 */
		public void onLetter() throws InvalidTransitionException {
			if (digitCount == 3) {
				currentState = stateSuffix;
				isValidEndState = true;
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}
		
		/**
		 * Handles transitions for a digit character. If there are already 3 digits,
		 * throws an exception.
		 * @throws InvalidTransitionException if there are already 3 digits.
		 */
		public void onDigit() throws InvalidTransitionException {
			switch (digitCount) {
			case 0:
				digitCount++;
				break;
			case 1:
				digitCount++;
				break;
			case 2:
				digitCount++;
				isValidEndState = true;
				break;
			default:
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
		
		/**
		 * Handles state transitions for an input that is not a digit or a alphabetical
		 * character. Will always throw an InvalidTransitionException
		 * @throws InvalidTransitionException if a non-alphanumeric character is not
		 * 			valid (always).
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
}
