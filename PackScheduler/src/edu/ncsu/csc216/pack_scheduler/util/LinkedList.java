package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Helen O'Connell
 * @author Jason Wong
 * @author Will Pressler
 *
 * @param <E>
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
			previousIndex = index;
			nextIndex = index + 1;
			lastRetrieved = null;
		}

		@Override
		public boolean hasNext() {
			return (next.data != null);
		}

		@Override
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException();
			}
			next = next.next;
			lastRetrieved = next.prev;
			nextIndex = nextIndex + 1;
			previousIndex = previousIndex + 1;
			return lastRetrieved.data;
		}

		@Override
		public boolean hasPrevious() {
			return (previous.data != null);
		}

		@Override
		public E previous() {
			if (previous.data == null) {
				throw new NoSuchElementException();
			}
			previous = previous.prev;
			lastRetrieved = previous.next;
			nextIndex = nextIndex - 1;
			previousIndex = previousIndex - 1;
			return lastRetrieved.data;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			return previousIndex;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			ListNode toAdd = new ListNode(e, previous, next);
			previous.next = toAdd;
			next.prev = toAdd;
			previous = toAdd;
			previousIndex++;
			nextIndex++;
			size++;
			lastRetrieved = null;
		}
	}
}
