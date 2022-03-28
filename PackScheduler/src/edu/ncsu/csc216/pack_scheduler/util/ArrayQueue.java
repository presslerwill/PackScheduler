package edu.ncsu.csc216.pack_scheduler.util;

/**
 * 
 * @author Helen O'Connell
 * @param <E>
 *
 */
public class ArrayQueue<E> implements Queue{

	private ArrayList<E> arrayList;
	
	/** the capacity associated with the arrayQueue*/
	private int capacity;
	/**
	 * 
	 * @param capacity to be set as the capacity of the queue
	 */
	public ArrayQueue(int capacity){
		arrayList = new ArrayList<E>();
		this.capacity = capacity;
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
