package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * The ArrayQueue class uses an array-based list to implement a queue. It
 * contains methods to add an object to a queue, remove, get the size, set the
 * capacity, and check if the queue is empty.
 * 
 * @author Helen O'Connell
 * @param <E> Element type for the Queue
 *
 */
public class ArrayQueue<E> implements Queue<E> {

	/** the ArrayList associated with the ArrayQueue */
	private ArrayList<E> arrayList;

	/** the capacity associated with the arrayQueue */
	private int capacity;

	/**
	 * the constructor for the ArrayQueue. Receives and sets a capacity, and
	 * instantiates the associated arrayList
	 * 
	 * @param capacity to be set as the capacity of the queue
	 */
	public ArrayQueue(int capacity) {
		arrayList = new ArrayList<E>();
		setCapacity(capacity);
	}

	/**
	 * Adds an element to the queue by adding it to the back of the arrayList
	 * 
	 * @param element to be added to the queue
	 * @throws IllegalArgumentException if the capacity of the queue has been
	 *                                  reached
	 */
	@Override
	public void enqueue(E element) {
		if (size() + 1 > capacity) {
			throw new IllegalArgumentException("Capacity has been reached");
		}
		else {
			arrayList.add(element);
		}
	}

	/**
	 * Removes an element from the queue by removing it from the front of the
	 * arrayList
	 * 
	 * @return the object that was removed
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("Queue is empty.");
		}
		return arrayList.remove(0);
	}

	/**
	 * returns whether the given queue is empty or not
	 * @return whether the given queue is or isn't empty
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * returns the number of elements in the queue
	 */
	@Override
	public int size() {
		return arrayList.size();
	}

	/**
	 * sets the capacity of the queue and performs error checking on the capacity
	 * parameter
	 * 
	 * @param capacity to be set for the queue
	 * @throws IllegalArgumentException if the capacity parameter is negative or
	 *                                  less than the size of the queue
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException("Illegal capacity value");
		}
		else {
			this.capacity = capacity;
		}
	}

}
