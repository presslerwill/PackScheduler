package edu.ncsu.csc216.pack_scheduler.util;
/**
 * 
 * @author Helen O'Connell
 * @param <E>
 *
 */
public class LinkedQueue<E> implements Queue{

	/**
	 * the LinkedList associated with this implementation of queue
	 */
	private LinkedAbstractList<E> linkedList;
	
	/**
	 * creates the LinkedList object associated with the queue
	 * @param capacity of the LinkedList when created
	 */
	public LinkedQueue(int capacity){
		linkedList = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * 
	 */
	@Override
	public void enqueue(Object element) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	@Override
	public Object dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public void setCapacity(int capacity) {
		// TODO Auto-generated method stub
		
	}
}
