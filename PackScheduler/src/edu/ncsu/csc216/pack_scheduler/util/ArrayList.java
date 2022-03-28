package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This class represents an AbstractList in the form of an array complete with
 * the functionality that comes with regular arrays, except it doesn't allow the
 * storage of null or duplicate objects. The functionality of this class is
 * defined by mixing the function of an AbstractList with that of an array which
 * allows this class to add, set, remove, and get objects from anywhere in the
 * array, while dynamically sizing the array to grow when needed.
 * 
 * @author Marshall Pearson
 * @author Arch Flanagan
 *
 * @param <E> The type of object that is stored in the instance of ArrayList
 */
public class ArrayList<E> extends AbstractList<E> {

	/** size for the list to be initialized at */
	private static final int INIT_SIZE = 10;

	/** list to store objects */
	private E[] list;

	/** size of the array */
	private int size;

	/**
	 * Creates an ArrayList object. Sets the capacity to the default size, and
	 * initializes the size at zero
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		size = 0;
		list = (E[]) new Object[INIT_SIZE];
	}

	/**
	 * Adds an element to the array at the specified index, and shifts all elements
	 * after the index to the left (if applicable). The element cannot be null or a
	 * duplicate of an element already in the array, and the index must exist within
	 * the list.
	 * 
	 * @param idx    int index for the element to be added at.
	 * @param addObj object to be added to the array.
	 * @throws IndexOutOfBoundsException if the index does not exist int he list.
	 * @throws NullPointerException      if the object to be added is null.
	 * @throws IllegalArgumentException  if the object already exists in the list.
	 */
	@SuppressWarnings("unchecked")
	public void add(int idx, E addObj) {
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}

		if (addObj == null) {
			throw new NullPointerException("Object to be added cannot be null.");
		}

		for (E e : list) {
			if (addObj.equals(e)) {
				throw new IllegalArgumentException("Duplicates aren't allowed in list.");
			}
		}

		if (size() == list.length) {
			extendList();
		}

		E[] newList = (E[]) new Object[list.length];

		for (int i = 0; i < list.length; i++) {
			if (i < idx) {
				newList[i] = list[i];
			} else if (i == idx) {
				newList[i] = addObj;
			} else {
				newList[i] = list[i - 1];
			}
		}

		if (size() == 0) {
			newList[0] = addObj;
		}

		list = newList;

		size++;
	}

	@SuppressWarnings("unchecked")
	private void extendList() {
		E[] newList = (E[]) new Object[list.length * 2];
		for (int i = 0; i < list.length; i++) {
			newList[i] = list[i];
		}
		list = newList;

	}

	/**
	 * Removes an element from the list at a specified index, an returns it. If the
	 * index does not exist in the array, throws an error. Moves all elements to the
	 * right of the element to the left.
	 * 
	 * @param idx int index of object to be removed
	 * @return E object removed from the list
	 * @throws IndexOutOfBoundsException if the index does not exist in the list.
	 */
	@SuppressWarnings("unchecked")
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}

		E removeElement = (E) list[idx];
		E[] newList = (E[]) new Object[list.length];

		for (int i = 0; i < size(); i++) {
			if (i < idx) {
				newList[i] = list[i];
			} else if (i > idx) {
				newList[i - 1] = list[i];
			}
		}

		newList[size() - 1] = null;
		size--;
		list = newList;

		return removeElement;
	}

	/**
	 * returns the element at the specified index.
	 * 
	 * @param idx int index of the element to be returned
	 * @return E element at the specified index
	 * @throws IndexOutOfBoundsException if the index does not exist in the list.
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}

		return list[idx];
	}

	/**
	 * Returns the size of the ArrayList.
	 * 
	 * @return int size of the ArrayList.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the element at the given index to the specified object.
	 * 
	 * @param idx    The index that is being replaced
	 * @param addObj The object that is being set to the index
	 * @return The object that was replaced from the list
	 * @throws IndexOutOfBoundsException if the index does not exist int he list.
	 * @throws NullPointerException      if the object to be added is null.
	 * @throws IllegalArgumentException  if the object already exists in the list.
	 */
	public E set(int idx, E addObj) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}

		if (addObj == null) {
			throw new NullPointerException("Object to be added cannot be null.");
		}

		for (E e : list) {
			if (addObj.equals(e)) {
				throw new IllegalArgumentException("Duplicates aren't allowed in list.");
			}
		}

		E returnObject = list[idx];

		list[idx] = addObj;

		return returnObject;
	}

	/**
	 * Adds an object to the end of a list
	 * 
	 * @param e object to be added to the array.
	 * @throws IndexOutOfBoundsException if the index does not exist int he list.
	 * @throws NullPointerException      if the object to be added is null.
	 * @throws IllegalArgumentException  if the object already exists in the list.
	 * @return true if successful
	 */
	public boolean add(E e) {
		add(size, e);
		return true;
	}

}
