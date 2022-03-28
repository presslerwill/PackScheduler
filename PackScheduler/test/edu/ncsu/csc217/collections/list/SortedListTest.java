package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the SortedList libraries.
 * 
 * @author Will Pressler
 * @author Aaron Jong
 * @author Gabriel Perez
 *
 */
public class SortedListTest {

	/**
	 * Tests SortedList constructor
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		assertTrue(list.isEmpty());
		for (int i = 65; i < 76; i++) {
			list.add(Character.toString(i));
			assertEquals(Character.toString(i), list.get(i - 65));
		}

		assertEquals(11, list.size());
		// TODO Test that the list grows by adding at least 11 elements
		// Remember the list's initial capacity is 10

	}

	/**
	 * Tests adding elements to the SortedList
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));

		list.add("zucchini");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("zucchini", list.get(2));

		list.add("cantaloupe");
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("cantaloupe", list.get(2));
		assertEquals("zucchini", list.get(3));

		assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("cantaloupe", list.get(2));
		assertEquals("zucchini", list.get(3));

		assertThrows(IllegalArgumentException.class, () -> list.add("banana"));
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("cantaloupe", list.get(2));
		assertEquals("zucchini", list.get(3));

		// TODO Test adding to the front, middle and back of the list

		// TODO Test adding a null element

		// TODO Test adding a duplicate element
	}

	/**
	 * Tests get() method for retrieving elements in the SortedList
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		// Since get() is used throughout the tests to check the
		// contents of the list, we don't need to test main flow functionality
		// here. Instead this test method should focus on the error
		// and boundary cases.

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));

		list.add("banana");
		list.add("apple");
		list.add("cantaloupe");
		list.add("zucchini");

		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("cantaloupe", list.get(2));
		assertEquals("zucchini", list.get(3));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));

		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
	}

	/**
	 * Tests removing elements from different positions in the SortedList
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));

		list.add("banana");
		list.add("apple");
		list.add("cantaloupe");
		list.add("zucchini");

		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("cantaloupe", list.get(2));
		assertEquals("zucchini", list.get(3));

		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));

		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));

		// remove element from middle
		list.remove(1);

		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("cantaloupe", list.get(1));
		assertEquals("zucchini", list.get(2));

		// remove element from end
		list.remove(2);

		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("cantaloupe", list.get(1));

		// remove first element
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("cantaloupe", list.get(0));

		// remove last element
		list.remove(0);
		assertEquals(0, list.size());
	}

	/**
	 * Tests the indexOf() for mulitple elements in the SortedList
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		assertEquals(-1, list.indexOf("apple"));

		list.add("apple");
		list.add("banana");
		list.add("cantaloupe");
		list.add("zucchini");

		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("cantaloupe", list.get(2));
		assertEquals("zucchini", list.get(3));

		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("cantaloupe"));

		assertEquals(-1, list.indexOf("kiwi"));
		assertEquals(-1, list.indexOf("dragonfruit"));

		assertThrows(NullPointerException.class, () -> list.indexOf(null));

	}

	/**
	 * Tests clearing the list to make sure it is empty
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		list.add("apple");
		list.add("banana");
		list.add("cantaloupe");
		list.add("zucchini");

		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("cantaloupe", list.get(2));
		assertEquals("zucchini", list.get(3));

		list.clear();

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

	}

	/**
	 * Tests an empty list, then checking it is not empty after adding an element
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add("apple");
		list.add("banana");
		list.add("cantaloupe");
		list.add("zucchini");

		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("cantaloupe", list.get(2));
		assertEquals("zucchini", list.get(3));

		assertEquals(4, list.size());
		assertFalse(list.isEmpty());

	}

	/**
	 * Tests to see if the SortedList contains certain elements
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		assertFalse(list.contains("apple"));

		list.add("apple");
		list.add("banana");
		list.add("cantaloupe");
		list.add("zucchini");

		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("cantaloupe", list.get(2));
		assertEquals("zucchini", list.get(3));

		assertTrue(list.contains("apple"));
		assertTrue(list.contains("zucchini"));
		assertFalse(list.contains("watermelon"));
		assertFalse(list.contains("orange"));

	}

	/**
	 * Tests to check that different Lists are equal and non-equal
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list2.add("apple");
		list2.add("banana");
		list3.add("cantaloupe");
		list3.add("zucchini");

		assertEquals(2, list1.size());
		assertEquals("apple", list1.get(0));
		assertEquals("banana", list1.get(1));

		assertEquals(2, list2.size());
		assertEquals("apple", list2.get(0));
		assertEquals("banana", list2.get(1));

		assertEquals(2, list3.size());
		assertEquals("cantaloupe", list3.get(0));
		assertEquals("zucchini", list3.get(1));

		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertFalse(list1.equals(list3));

	}

	/**
	 * Tests the hash code for same and different lists
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list2.add("apple");
		list2.add("banana");
		list3.add("cantaloupe");
		list3.add("zucchini");

		assertEquals(2, list1.size());
		assertEquals("apple", list1.get(0));
		assertEquals("banana", list1.get(1));

		assertEquals(2, list2.size());
		assertEquals("apple", list2.get(0));
		assertEquals("banana", list2.get(1));

		assertEquals(2, list3.size());
		assertEquals("cantaloupe", list3.get(0));
		assertEquals("zucchini", list3.get(1));

		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}

}
