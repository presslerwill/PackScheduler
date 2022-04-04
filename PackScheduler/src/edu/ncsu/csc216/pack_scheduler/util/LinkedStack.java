package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * This class implements a linked list-based stack. This stack has
 * methods to push, pop, get the size, get if the stack is currently
 * empty, and set capacity. In a stack, only the top (most recently
 * added) element can be accessed.
 * 
 * @author Jason Wong
 * @param <E> generic type used in this LinkedStack
 *
 */
public class LinkedStack<E> implements Stack<E> {
    /** Underlying linked list that this LinkedStack delegates to */
    private LinkedAbstractList<E> stack;
   
    /**
     * Constructs a new LinkedStack with the given capacity
     * 
     * @param capacity capacity to set to
     */
    public LinkedStack(int capacity) {
        stack = new LinkedAbstractList<E>(capacity);
    }
   
    /**
     * Implements Stack's push() to add the given element to the top of the stack.
     * 
     * @param element element to add to top of stack
     * @throws IllegalArgumentException if stack is full
     */
    @Override
    public void push(E element) {
        stack.add(0, element);
    }

    /**
     * Implements Stack's pop() method to remove and return the element at the top of the stack
     * 
     * @return the element that was removed
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(0);
    }

    /**
     * Implements Stack's isEmpty() method to check if the stack is empty
     * @return true if stack contains no elements
     */
    @Override
    public boolean isEmpty() {
        return stack.size() == 0;
    }

    /**
     * Implements Stack's size() method to get the current size of this LinkedStack
     * @return size of stack
     */
    @Override
    public int size() {
        return stack.size();
    }

    /**
     * Implements Stack's setCapacity() method to set the capacity of this LinkedStack
     * 
     * @param capacity capacity to set
	 * @throws IllegalArgumentException if the parameter is negative or less than the
	 *                                  number of elements in the stack
     */
    @Override
    public void setCapacity(int capacity) {
        stack.setCapacity(capacity);
    }
}
