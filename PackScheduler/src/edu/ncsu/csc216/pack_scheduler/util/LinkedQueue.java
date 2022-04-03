package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * The LinkedQueue class uses an LinkedList to implement a queue. It contains
 * methods to add an object to a queue, remove, get the size, set the capacity,
 * and check if the queue is empty.
 * 
 * @author Helen O'Connell
 * @param <E>
 *
 */
public class LinkedQueue<E> implements Queue<E> {

	/**
	 * the LinkedList associated with this implementation of queue
	 */
	private LinkedAbstractList<E> linkedList;

	/**
	 * Creates the LinkedList object associated with the queue. Given a capacity,
	 * uses the LinkedAbstractList constructor to create the linkedList object
	 * 
	 * @param capacity of the LinkedList when created
	 * @throws IllegalArgumentException if capacity has been reached
	 */
	public LinkedQueue(int capacity) {
		linkedList = new LinkedAbstractList<E>(capacity);
	}

	/**
	 * Adds an element to the queue by adding to the back of the LinkedList
	 * 
	 * @param element to be added to the list
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public void enqueue(E element) {
		linkedList.add(size(), element);
	}

	/**
	 * Removes from the queue by removing from the front of the LinkedList
	 * 
	 * @return the removed object
	 */
	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("Queue is empty.");
		}
		return linkedList.remove(0);
	}
	/**
	 * returns whether the given queue is or isn't empty
	 * 
	 * @return whether the queue is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * gets the size of the queue
	 * 
	 * @return the number of elements in the queue
	 */
	@Override
	public int size() {
		return linkedList.size();
	}

	/**
	 * sets the capacity of the queue after performing error checking
	 * 
	 * @param capacity to be set for the queue
	 * @throws IllegalArgumentException if the capacity parameter is less than size
	 *                                  or zero
	 */
	@Override
	public void setCapacity(int capacity) {
		linkedList.setCapacity(capacity);
	}
}
