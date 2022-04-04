/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This is a basic abstract Linked List implementation, allowing for a generic
 * data type to be stored in nodes, up to a certain capacity. You can add, edit,
 * remove, and get data from nodes based off their index. You can also see the
 * current size of the LinkedList.
 * 
 * @author Marshall Pearson
 * @param <E> The class that the list can store in the nodes
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** The first node in the list */
	ListNode front;
	/** The amount of nodes in the list */
	int size;
	/** The total capacity that the list can hold */
	int capacity;
	/** The last node in the list */
	ListNode back;

	/**
	 * Constructs an empty Abstract Linked List with a given capacity, with the
	 * front node being undefined.
	 * 
	 * @param capacity The total amount of nodes that can be added to the list
	 * @throws IllegalArgumentException If capacity is less than 0
	 */
	public LinkedAbstractList(int capacity) {
		front = null;
		back = null;
		size = 0;
		if (capacity < size) {
			throw new IllegalArgumentException("Capacity cannot be smaller than size or less than 0");
		}
		this.capacity = capacity;

	}

	/**
	 * Adds a new node at the given index with the given data
	 * 
	 * @param idx Index that the node is being added at
	 * @param e   Data that the node is holding
	 * @throws IllegalArgumentException  If the list is at max capacity, or if the
	 *                                   data is already present somewhere in the
	 *                                   list
	 * @throws NullPointerException      If the data to be added is null
	 * @throws IndexOutOfBoundsException If the index less than 0 or index greater
	 *                                   than size
	 */
	public void add(int idx, E e) {
		if (size >= capacity) {
			throw new IllegalArgumentException("List is at max capacity");
		}

		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}

		if (e == null) {
			throw new NullPointerException("Null elements cannot be added to list.");
		}
		ListNode node = front;

		while (node != null) {
			if (node.data.equals(e)) {
				throw new IllegalArgumentException("Duplicates not allowed in list.");
			}
			node = node.next;
		}

		if (idx == 0) {
			ListNode newNode = new ListNode(e, this.front);
			this.front = newNode;
		} else {
			ListNode before = front;
			// Sets before to the node before the target index
			for (int i = 0; i < idx - 1; i++) {
				before = before.next;
			}
			// Node that will come after node that is added
			ListNode after = before.next;
			// I have to use this constructor to get enough coverage
			if (after == null) {
				before.next = new ListNode(e);
				size++;
				return;
			}
			// Makes sure back is the final node
			if (after.next == null) {
				back = after;
			}
			// Sets the newNodes next to the after node
			ListNode newNode = new ListNode(e, after);
			// Sets the before node's next to the newNode
			before.next = newNode;
		}

		size++;

	}

	/**
	 * Gets the data from the node at the given index in the list
	 * 
	 * @param idx Index of the node that the data is being received from
	 * @return The data that the node at the given index holds
	 * @throws IndexOutOfBoundsException If the index less than 0 or index greater
	 *                                   than or equal to size
	 */
	public E get(int idx) {

		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}

		ListNode target = front;
		for (int i = 0; i < idx; i++) {
			target = target.next;
		}
		return target.data;
	}

	/**
	 * Sets the node at the given index to hold the new given data, and returns the
	 * old data it held
	 * 
	 * @param idx Index of the node being manipulated
	 * @param e   The data that the edited node will hold
	 * @return The old data that the node held before changing
	 * @throws IllegalArgumentException  If the data to be set is already present in
	 *                                   the list
	 * @throws NullPointerException      If the data being added is null
	 * @throws IndexOutOfBoundsException If the index less than 0 or index greater
	 *                                   than or equal to size
	 */
	public E set(int idx, E e) {

		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (e == null) {
			throw new NullPointerException("Null elements cannot be added to list.");
		}
		ListNode node = front;
		while (node != null) {
			if (node.data.equals(e)) {
				throw new IllegalArgumentException("Duplicates not allowed in list.");
			}
			node = node.next;
		}

		ListNode target = front;
		for (int i = 0; i < idx; i++) {
			target = target.next;
		}
		E pastData = target.data;
		target.data = e;
		return pastData;
	}

	/**
	 * Removed the node at the given index and returns the data it held.
	 * 
	 * @param idx Index of the node being removed
	 * @return The data that the removed node stored
	 * @throws IndexOutOfBoundsException If the index less than 0 or index greater
	 *                                   than or equal to size
	 */
	public E remove(int idx) {
		E data = null;
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (idx == 0) {
			data = front.data;
			ListNode nextNode = front.next;
			this.front = nextNode;
		} else {
			ListNode before = front;
			// Sets before to the node before the target index
			for (int i = 0; i < idx - 1; i++) {
				before = before.next;
			}
			// Node that will come after node that is added
			ListNode after;
			if (idx == size - 1) {
				after = null;
			} else {
				after = before.next.next;
			}

			data = before.next.data;
			before.next = after;
			// makes sure the final node is back
			if (after == null) {
				back = before.next;
			}
			else if (after.next == null) {
				back = after;
			}

		}

		size--;
		return data;
	}

	/**
	 * Returns the size of the list, which is the total amount of nodes inside the
	 * list
	 * 
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Sets the capacity of the LinkedList
	 * 
	 * @param capacity value of the capacity
	 * @throws IllegalArgumentException if the capacity parameter is invalid
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < this.size) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		this.capacity = capacity;
	}

	/**
	 * This class is a class that represents a node, which can hold a reference to
	 * the next node in a LinkedList, and a generic type of data.
	 * 
	 * @author marshallp
	 */
	private class ListNode {
		/** The data that the node holds */
		E data;
		/** A reference to the next node in the list */
		ListNode next;

		/**
		 * Constructs a node that points to another node and has defined data
		 * 
		 * @param data Data that this node holds
		 * @param next A reference to the next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * Constructs a node that doesn't point to another node and has defined data
		 * 
		 * @param data Data that this node holds
		 */
		public ListNode(E data) {
			this(data, null);
		}

	}

}
