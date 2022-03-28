/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface for a stack
 * 
 * @author Will Pressler
 * @author Jason Wong
 * @author Helen O'Connell
 * @param <E> generic element
 */
public interface Stack<E> {

	/**
	 * Adds an element to the top of the stack
	 * 
	 * @param element to be pushed
	 * @throws IllegalArgumentException if there is no room in the stack
	 */
	void push(E element);

	/**
	 * Removes the element from the top of the stack, and returns the removed
	 * element
	 * 
	 * @return E the element that was removed
	 */
	E pop();

	/**
	 * Checks if the stack is empty
	 * 
	 * @return boolean true if the stack is empty
	 */
	boolean isEmpty();

	/**
	 * Returns the number of elements in the stack
	 * 
	 * @return int the number of elements in the stack
	 */
	int size();

	/**
	 * Sets the stack's capacity
	 * 
	 * @param capacity desired capacity of the stack
	 * @throws IllegalArgumentException if the parameter is negative or less than the
	 *                                  number of elements in the stack
	 */
	void setCapacity(int capacity);
}
