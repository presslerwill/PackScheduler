/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Stack list with an Array based implementation
 * @author Will Pressler
 * @param <E>
 *
 */
public class ArrayStack<E> implements Stack<E> {
	
	/** ArrayList to which ArrayStack delegates functionality */
	private ArrayList<E> stack;
	/** The capacity of the stack */
	private int capacity;

	/**
	 * Constructor for the ArrayStack
	 * @param capacity the capacity of the ArrayStack
	 */
	public ArrayStack(int capacity) {
		stack = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Pushes an element on to the top of the stack
	 * @param element the element to be added to the stack
	 */
	@Override
	public void push(E element) {
		if (capacity == stack.size()) {
			throw new IllegalArgumentException("No more room");
		}
		stack.add(element);
		
	}

	/**
	 * Pops the top element off of the stack
	 * @return E the element that was removed from the stack
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.remove(size() - 1);
	}

	/**
	 * Checks if the stack is empty
	 * @return boolean true if the stack has 0 elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		boolean ret = false;
		if(stack.size() == 0) {
			ret = true;
		}
		return ret;
	
	}

	/**
	 * Gets the size of the Stack
	 * @return int the number of elements in the stack
	 */
	@Override
	public int size() {
		return stack.size();
	}

	/**
	 * Sets the capacity of the the Stack
	 * @param capacity the desired capacity of the Stack
	 * @throws IllegalArgumentException if capacity is less than zero or the current size
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < stack.size()) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		this.capacity = capacity;
		
	}

}
