package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

class LinkedListTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#ArrayList()}.
	 */
	@Test
	void testConstructor() {
		LinkedList<String> stringList = new LinkedList<String>();
		assertEquals(0, stringList.size());

		LinkedList<Course> courseList = new LinkedList<Course>();
		assertEquals(0, courseList.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#size()}.
	 */
	@Test
	void testSize() {
		LinkedList<String> array = new LinkedList<String>();
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
	 * Test method for {@link java.util.AbstractList#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		LinkedList<String> array = new LinkedList<String>();
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
	}

	/**
	 * Test method for {@link java.util.AbstractList#set(int, java.lang.Object)}.
	 */
	@Test
	void testSet() {
		LinkedList<String> array = new LinkedList<String>();
		array.add("String1");
		array.add("String2");
		array.add("String3");

		array.set(1, "New String");
		
		array.set(0, "New String2");
		
		array.set(2, "New String3");
		
		assertThrows(IndexOutOfBoundsException.class, () -> array.set(-1, "string")); 
		assertThrows(IndexOutOfBoundsException.class, () -> array.set(3, "string"));
		
		assertThrows(NullPointerException.class, () -> array.set(0, null)); 



	}

	/**
	 * Test method for {@link java.util.AbstractList#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		LinkedList<String> array = new LinkedList<String>();
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

}
