package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the custom implementation of the ArrayList class, which doesn't allow null elements or duplicate elements
 * 
 * @author Marshall Pearson
 *
 */
class ArrayListTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#ArrayList()}.
	 */
	@Test
	void testConstructor() {
		ArrayList<String> stringList = new ArrayList<String>();
		assertEquals(0, stringList.size());

		ArrayList<Course> courseList = new ArrayList<Course>();
		assertEquals(0, courseList.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#size()}.
	 */
	@Test
	void testSize() {
		ArrayList<String> array = new ArrayList<String>();
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
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#get(int)}.
	 */
	@Test
	void testGetInt() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("string1");
		assertEquals("string1", array.get(0));
		array.add("string2");
		array.add("string3");
		assertEquals("string2", array.get(1));
		assertEquals("string3", array.get(2));
		assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> array.get(3));
	}

	/**
	 * Test method for {@link java.util.AbstractList#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		ArrayList<String> array = new ArrayList<String>();
		assertThrows(NullPointerException.class, () -> array.add(0, null));

		array.add(0, "String");
		assertEquals("String", array.get(0));

		assertThrows(IllegalArgumentException.class, () -> array.add(1, "String"));

		array.add(1, "String2");
		assertEquals("String2", array.get(1));

		array.add(1, "String3");
		assertAll(() -> assertEquals("String", array.get(0)), () -> assertEquals("String3", array.get(1)),
				() -> assertEquals("String2", array.get(2)));

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
		assertEquals("11", array.get(10));
		assertEquals("10", array.get(9));
		assertEquals("9", array.get(8));

	}

	/**
	 * Test method for {@link java.util.AbstractList#set(int, java.lang.Object)}.
	 */
	@Test
	void testSet() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("String1");
		array.add("String2");
		array.add("String3");

		array.set(1, "New String");
		assertAll(() -> assertEquals("String1", array.get(0)), () -> assertEquals("New String", array.get(1)),
				() -> assertEquals("String3", array.get(2))
		);
		
		array.set(0, "New String2");
		assertAll(() -> assertEquals("New String2", array.get(0)), () -> assertEquals("New String", array.get(1)),
				() -> assertEquals("String3", array.get(2))
		);
		
		array.set(2, "New String3");
		assertAll(() -> assertEquals("New String2", array.get(0)), () -> assertEquals("New String", array.get(1)),
				() -> assertEquals("New String3", array.get(2))
		);
		
		assertThrows(IndexOutOfBoundsException.class, () -> array.set(-1, "string")); 
		assertThrows(IndexOutOfBoundsException.class, () -> array.set(3, "string"));
		
		assertThrows(NullPointerException.class, () -> array.set(0, null)); 



	}

	/**
	 * Test method for {@link java.util.AbstractList#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		ArrayList<String> array = new ArrayList<String>();
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
