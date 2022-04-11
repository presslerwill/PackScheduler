/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Recursive Implementation of a LinkedList
 * @author press
 * @param <E> generic type for the list
 */
public class LinkedListRecursive<E>  {

	/** Size of the LinkedList*/
	private int size;
	/** The first ListNode in the list */
	private ListNode front;
	
	
	/**
	 * Constructor for the LinkedListRecursvie
	 */
	public LinkedListRecursive() {
		this.size = 0;
		this.front = null;
	}
	
	/**
	 * Checks if the LinkedList is empty
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the size of the LinkedList
	 * @return int the size of the list
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Adds an Element to the list
	 * @param e element to be added to the list
	 * @return boolean true if the add was successful, false otherwise
	 */
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException("Null element");
		}
		if (contains(e)) {
			throw new IllegalArgumentException("Element already exists");
		}
		if (size == 0) {
			front = new ListNode(e, front);
			size++;
			return true;
		}
		front.add(size, e);
		return true;
	}
	
	/**
	 * Adds an element to the list at the given index
	 * @param idx index where the element should be added
	 * @param e element to be added 
	 */
	public void add(int idx, E e) {
		if (contains(e)) {
			throw new IllegalArgumentException("Element already exists");
		}
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		if (e == null) {
			throw new NullPointerException("Null element");
		}
		if (size == 0) {
			front = new ListNode(e, front);
			size++;
			return;
		}
		
		front.add(idx, e);
		
	}
	
	/**
	 * Gets the element in the list at the given index
	 * @param idx desired index to get the element
	 * @return E element at the index
	 */
	public E get(int idx) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		
	}
	
	/**
	 * Removes an element from the given index
	 * @param idx where the element should be removed from
	 * @return boolean true if the removal was successful
	 */
	public boolean remove(int idx) {
		
	}
	
	/**
	 * Removes an element from the list at the given index
	 * @param idx index from where the element should be removed
	 * @return E the element removed from the list
	 */
	public E remove(int idx) {
		
	}
	
	/**
	 * Sets the given index of the list to the given Element
	 * @param idx where the Element should be set
	 * @param e element to be set in the list
	 * @return E the element that was set in the list
	 */
	public E set(int idx, E e) {
		
	}
	
	/**
	 * Checks if the list contains the given Element
	 * @param e Element to look for in the list
	 * @return boolean true if the list contains the element, false otherwise
	 */
	public boolean contains(E e) {
		if (front == null) {
			return false;
		}
		else {
			return front.contains(e);
		}
	}
	
	/**
	 * Class for a single ListNode in the LinkedListRecursive class
	 * @author press
	 *
	 */
	private class ListNode {
		
		/** Data stored in a ListNode */
		public E data;
		/** Reference to the next ListNode in the list */
		public ListNode next;
		
		/**
		 * Constructor for a single ListNode
		 * @param data stored in the ListNode
		 * @param next reference in the LinkedList
		 */
		ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Adds an element to the list at the specified index
		 * @param idx where the element should be added
		 * @param e Element to be added to the list
		 */
		public void add(int idx, E e) {
			if (idx == 1) {
				this.next = new ListNode(e, next);
				size++;
			}
			else {
				this.next.add(idx - 1, e);
			}
		}
		
		/**
		 * Gets an element from the list at the desired index
		 * @param idx where the element should be gotten
		 * @return E the element from the lsit
		 */
		public E get(int idx) {
			
		}
		
		/**
		 * Removes an element from the list at the given index
		 * @param idx where the element should be removed
		 * @return E the element removed from the list
		 */
		public E remove(int idx) {
			
		}
		
		/**
		 * Removes an element from the list
		 * @param e the element to be removed
		 * @return boolean true if the removal was successful, false otherwise
		 */
		public boolean remove(E e) {
			
		}
		
		/**
		 * Sets an index in the list to a given Element
		 * @param idx where the element should be set
		 * @param e element to be set in the list
		 * @return E the element set in the list
		 */
		public E set(int idx, E e) {
			
		}
		
		/**
		 * Checks if the list contains the given element
		 * @param e element to be found in the list
		 * @return boolean true if the list contains the element
		 */
		public boolean contains(E e) {
			if (this.data.equals(e)) {
				return true;
			}
			else if (this.next == null) {
				return false;
			}
			else {
				return this.next.contains(e);
			}
		}
		
	}
}
