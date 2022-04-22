/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the LinkedListRecursiveRecursive Class
 * @author press
 *
 */
public class LinkedListRecursiveTest {

	/**
	 * Test method for LinkedListRecursive's constructor
	 */
	@Test
	void testConstructor() {
		LinkedListRecursive<String> stringList = new LinkedListRecursive<String>();
		assertEquals(0, stringList.size());

		LinkedListRecursive<Course> courseList = new LinkedListRecursive<Course>();
		assertEquals(0, courseList.size());
	}
	
	/**
	 * Tests the contains() method in LinkedListRecursive
	 */
	@Test
	void testContains() {
		LinkedListRecursive<String> stringList = new LinkedListRecursive<String>();
		assertEquals(0, stringList.size());
		assertFalse(stringList.contains("Hello"));
		stringList.add("String1");
		assertTrue(stringList.contains("String1"));
		
	}

	/**
	 * Test method for LinkedListRecursive.size()
	 */
	@Test
	void testSize() {
		LinkedListRecursive<String> array = new LinkedListRecursive<String>();
		array.add("string1");
		assertEquals(1, array.size());

		array.add("string2");
		assertEquals(2, array.size());

		array.remove(1);
		assertEquals(1, array.size());

		array.remove(0);
		assertEquals(0, array.size());
	}
	
	/**
	 * Tests the LinkedListRecursive.add()
	 */
	@Test
	void testAddE() {
		LinkedListRecursive<String> array = new LinkedListRecursive<String>();
		assertThrows(NullPointerException.class, () -> array.add(null));
		array.add("String1");
		assertEquals(1, array.size());
		array.add("String2");
		assertEquals(2, array.size());
	}

	/**
	 * Test method for LinkedListRecursive.add()
	 */
	@Test
	void testAddIntE() {
		LinkedListRecursive<String> array = new LinkedListRecursive<String>();
		assertThrows(NullPointerException.class, () -> array.add(0, null));

		array.add(0, "String");
		assertEquals("String", array.get(0));
		assertThrows(IllegalArgumentException.class, () -> array.add(1, "String"));

		array.add(1, "String2");

		array.add(1, "String3");

		assertThrows(IndexOutOfBoundsException.class, () -> array.add(-1, "str"));
		assertThrows(IndexOutOfBoundsException.class, () -> array.add(4, "str"));

		array.add("4");
		array.add("5");
		array.add("6");
		array.add("7");
		array.add("8");
		array.add("9");
		array.add("10");
		array.add("11");
		assertEquals(11, array.size());
		assertEquals("String3", array.get(1));
		assertEquals("String2", array.get(2));
		assertEquals("4", array.get(3));
		assertEquals("5", array.get(4));
		assertEquals("6", array.get(5));
		assertEquals("7", array.get(6));
		assertEquals("8", array.get(7));
		assertEquals("9", array.get(8));
		assertEquals("10", array.get(9));
		assertEquals("11", array.get(10));
		
		// More tests
		LinkedListRecursive<String> array2 = new LinkedListRecursive<String>();
		// Out of bounds tests
		assertThrows(IndexOutOfBoundsException.class, () -> array2.add(-1, "2"));
		assertThrows(IndexOutOfBoundsException.class, () -> array2.add(1, "2"));
		
		// Valid tests
		// Add to empty list
		array2.add(0, "2");
		// Add to end of list
		array2.add(1, "3");
		// Add to beginning of list
		array2.add(0, "0");
		// Add to middle of list
		array2.add(1, "1");
		
		// Check if everything added correctly
		assertEquals(4, array2.size());
		assertEquals("0", array2.get(0));
		assertEquals("1", array2.get(1));
		assertEquals("2", array2.get(2));
		assertEquals("3", array2.get(3));

	}

	/**
	 * Test method for LinkedListRecursive.set()
	 */
	@Test
	void testSet() {
		LinkedListRecursive<String> array = new LinkedListRecursive<String>();
		array.add("String1");
		array.add("String2");
		array.add("String3");

		array.set(1, "New String");

		array.set(0, "New String2");

		array.set(2, "New String3");

		assertThrows(IndexOutOfBoundsException.class, () -> array.set(-1, "string"));
		assertThrows(IndexOutOfBoundsException.class, () -> array.set(3, "string"));

		assertThrows(NullPointerException.class, () -> array.set(0, null));
		assertThrows(IllegalArgumentException.class, () -> array.set(0, "New String"));
	}

	/**
	 * Test method for LinkedListRecursive.remove()
	 */
	@Test
	void testRemoveInt() {
		LinkedListRecursive<String> array = new LinkedListRecursive<String>();
		array.add("String1");
		array.add("String2");
		array.add("String3");

		String ret = array.remove(2);
		assertEquals("String3", ret);
		assertEquals(2, array.size());

		ret = array.remove(0);
		assertEquals("String1", ret);
		assertEquals(1, array.size());

		array.add("str1");
		array.add("str2");

		ret = array.remove(1);
		assertEquals("str1", ret);
		assertEquals(2, array.size());

		assertThrows(IndexOutOfBoundsException.class, () -> array.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> array.remove(3));
	}

	/**
	 * Test method for LinkedListRecursive.remove(E e)
	 */
	@Test
	void testRemoveE() {
		LinkedListRecursive<String> array = new LinkedListRecursive<String>();
		assertFalse(array.remove(null));
		assertFalse(array.remove("asdf"));
		
		array.add("String1");
		array.add("String2");
		array.add("String3");
		assertEquals(3, array.size());
	
		assertTrue(array.remove("String2"));
		assertFalse(array.remove("qwerty"));
		assertTrue(array.remove("String3"));
		assertTrue(array.remove("String1"));
	}
	

}
