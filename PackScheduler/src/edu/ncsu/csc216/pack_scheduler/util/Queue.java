package edu.ncsu.csc216.pack_scheduler.util;

/**
 * creates the queue interface, which will be implemented by ArrayQueue and
 * LinkedQueue
 * 
 * @author Helen O'Connell
 * @param <E> the generic type for a Queue
 *
 */
public interface Queue<E> {

	/**
	 * adds the element to the back of the queue. checks that there is room in the
	 * queue
	 * 
	 * @param element to be added to the queue
	 * @throws IllegalArgumentException if the capacity has been reached as in there
	 *                                  is no room
	 */
	void enqueue(E element);

	/**
	 * removes and returns the element at the front of the queue
	 * 
	 * @return the dequeued element
	 * @throws NoSuchElementException if the queue is empty
	 */
	E dequeue();

	/**
	 * returns whether the queue is(true) or isn't empty(false)
	 * 
	 * @return whether the queue is or isn't empty
	 */
	boolean isEmpty();

	/**
	 * Returns the number of elements currently in the queue
	 * 
	 * @return current size of the queue
	 */
	int size();

	/**
	 * sets the capacity of the given queue after checking the capacity parameter to
	 * be set is not less than size or negative
	 * 
	 * @param capacity of the queue to be set to
	 * @throws IllegalArgumentException if the capacity parameter is negative or if
	 *                                  it is less than the size of the queue
	 */
	void setCapacity(int capacity);
}
