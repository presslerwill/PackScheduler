/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the ArrayStack class
 * @author Will Pressler
 *
 */
public class ArrayStackTest {

	/** Stack to be used in tests */
	private ArrayStack<String> stack;
	/**
	 * Sets up an array with capacity 5 before the tests
	 */
	@BeforeEach
	public void setUp() {
		stack = new ArrayStack<String>(5);
		
	}
	/**
	 * Tests ArrayStack's push method
	 */
	@Test
	public void testPush() {
		assertTrue(stack.isEmpty());
		stack.push("String1");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		stack.push("String2");
		stack.push("String3");
		assertEquals(3, stack.size());
		stack.push("String4");
		stack.push("String5");
		assertThrows(IllegalArgumentException.class, () -> stack.push("String6"));
		assertEquals(5, stack.size());
		assertEquals("String5", stack.pop());
		assertEquals(4, stack.size());
		stack.push("String6");
		assertEquals(5, stack.size());
	}
	
	/**
	 * Tests ArrayStack's pop method
	 */
	@Test
	public void testPop() {
		assertTrue(stack.isEmpty());
		assertThrows(EmptyStackException.class, () -> stack.pop());
		stack.push("String1");
		stack.push("String2");
		stack.push("String3");
		assertEquals(3, stack.size());
		assertEquals("String3", stack.pop());
		assertEquals(2, stack.size());
		assertEquals("String2", stack.pop());
		assertEquals("String1", stack.pop());
		assertThrows(EmptyStackException.class, () -> stack.pop());
	}
	
	/**
	 * Tests ArrayStack's isEmpty method
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(stack.isEmpty());
		stack.push("String1");
		assertFalse(stack.isEmpty());
	}
	
	/**
	 * Tests ArrayStack's size method
	 */
	@Test
	public void testSize() {
		assertEquals(0, stack.size());
		stack.push("String1");
		assertEquals(1, stack.size());
		stack.push("String2");
		assertEquals(2, stack.size());
		stack.push("String3");
		assertEquals(3, stack.size());
		stack.push("String4");
		assertEquals(4, stack.size());
		stack.push("String5");
		assertEquals(5, stack.size());
		
		assertThrows(IllegalArgumentException.class, () -> stack.push("String6"));
		assertEquals(5, stack.size());
	}
	
	/**
	 * Tests ArrayStack's setCapacity method
	 */
	@Test
	public void testSetCapacity() {
		stack.push("String1");
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(-2));
		stack.push("String2");
		stack.push("String3");
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(2));
		stack.push("String4");
		stack.push("String5");
		assertThrows(IllegalArgumentException.class, () -> stack.push("String6"));
		stack.setCapacity(7);
		stack.push("String6");
		stack.push("String7");
		assertEquals(7, stack.size());
		assertThrows(IllegalArgumentException.class, () -> stack.push("String"));
	}
}
