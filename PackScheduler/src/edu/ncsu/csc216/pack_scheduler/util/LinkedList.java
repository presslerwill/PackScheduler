package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * The LinkedList class implements a list of connected LinkedNodes. It maintains
 * its front and back nodes as well as its size. It is used by the faculty
 * directory class to hold a list of faculty, which makes up the directory
 * object
 * 
 * @author Helen O'Connell
 * @author Jason Wong
 * @author Will Pressler
 *
 * @param <E> generic object that acts as a placeholder for the element type
 *            held by the LinkedList
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** first ListNode of the list */
	private ListNode front;
	/** last ListNode of the list */
	private ListNode back;
	/** number of elements in the list */
	private int size;

	/**
	 * Constructor for a LinkedList. creates two ListNodes that have null data to
	 * act as dummy nodes
	 */
	public LinkedList() {
		this.front = new ListNode(null);
		this.back = new ListNode(null);
		this.size = 0;
		front.next = back;
		back.prev = front;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}

	@Override
	public void add(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		super.add(index, element);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E set(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException("Duplicate element.");
		}
		return super.set(index, element);
	}

	private class ListNode {
		/** the data contained in the node */
		private E data;
		/** the node following this node */
		private ListNode next;
		/** the node before this node */
		private ListNode prev;

		/**
		 * Constructor for a ListNode without a next and prev value specified. delegates
		 * to the other constructor for ListNode
		 * 
		 * @param data of the ListNode being created
		 */
		public ListNode(E data) {
			this(data, null, null);
		}

		/**
		 * Constructor for a ListNode with a prev and next value specified. directly
		 * sets the data, prev, and next fields
		 * 
		 * @param data of the ListNode being created
		 * @param prev of the ListNode being created
		 * @param next of the ListNode being created
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	private class LinkedListIterator implements ListIterator<E> {

		/** represents the returned ListNode on a call to the previous method */
		private ListNode previous;
		/** represents the returned ListNode on a call to the next method */
		private ListNode next;
		/** the index returned by a call to the previous index method */
		private int previousIndex;
		/** the index returned by a call to the next index method */
		private int nextIndex;
		/**
		 * the most recently returned ListNode by a call from the previous or next
		 * method
		 */
		private ListNode lastRetrieved;

		/**
		 * Iterates through the list using a current value to position the iterator
		 * between the element before the one at the index given and the one at the
		 * index given. then sets the appropriate fields
		 * 
		 * @param index of where to position the iterator
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException();
			}
			ListNode current = front;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			previous = current;
			next = current.next;
			previousIndex = index - 1;
			nextIndex = index + 1;
			lastRetrieved = null;
		}

		/**
		 * returns whether or not there is a next node that is not a dummy node
		 * 
		 * @return whether or not there is a next node that is not a dummy node
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * returns the data of the node that comes next. it also sets the corresponding
		 * next, and lastRetrieved fields. The next and previous indexes are incremented
		 * as well
		 * 
		 * @return the data of the next element
		 * @throws NoSuchElementException if there is no non-dummy node available next
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			next = next.next;
			previous = next.prev;
			lastRetrieved = next.prev;
			nextIndex++;
			previousIndex++;
			return lastRetrieved.data;
		}

		/**
		 * returns whether or not there is a previous node that is not a dummy node
		 * 
		 * @return whether or not there is a previous node that is not a dummy node
		 */
		@Override
		public boolean hasPrevious() {
			return previous.data != null;
		}

		/**
		 * returns the data of the node that comes before. it also sets the
		 * corresponding previous, and lastRetrieved fields. The next and previous
		 * indexes are decremented as well
		 * 
		 * @return the data of the previous element
		 * @throws NoSuchElementException if there is no non-dummy node available
		 *                                previous
		 */
		@Override
		public E previous() {
			if (previous.data == null) {
				throw new NoSuchElementException();
			}
			previous = previous.prev;
			next = previous.next;
			lastRetrieved = previous.next;
			nextIndex--;
			previousIndex--;
			return lastRetrieved.data;
		}

		/**
		 * returns the index of the next node
		 * 
		 * @return the index of the next node
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * returns the index of the previous node
		 * 
		 * @return the index of the previous node
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * removes the last element set to lastRetrieved, which is done from either a
		 * call to next or previous. also decrements the size
		 * 
		 * @throws IllegalArgumentException if lastRetrieved is null
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException("No last retrieved.");
			}
			ListNode placeholder = lastRetrieved;
			lastRetrieved.prev.next = lastRetrieved.next;
			placeholder.next.prev = placeholder.prev;
			lastRetrieved = null;
			size--;
		}

		/**
		 * sets the data of the node last retrieved from a call to next or previous to
		 * the given element.
		 * 
		 * @param e the element to be set
		 * @throws IllegalArgumentException if there is no recent call to next or
		 *                                  previous, so the lastRetrieved field is
		 *                                  empty
		 * @throws NullPointerException     if the element given is null
		 */
		@Override
		public void set(E e) {
			if (lastRetrieved == null) {
				throw new IllegalStateException("No last retrieved.");
			}
			if (e == null) {
				throw new NullPointerException("Null element cannot be set.");
			}
			lastRetrieved.data = e;
		}

		/**
		 * adds the given element to the list where there list Iterator is currently
		 * positioned. Therefore, the new element would be added between the nodes a
		 * call to previous and a call to next would return.
		 * 
		 * @param e the element to be set
		 * @throws NullPointerException if the element to be added is empty
		 */
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			ListNode toAdd = new ListNode(e, previous, next);
			previous.next = toAdd;
			next.prev = toAdd;
			previous = toAdd;
			nextIndex++;
			size++;
			lastRetrieved = null;
		}
	}
}
