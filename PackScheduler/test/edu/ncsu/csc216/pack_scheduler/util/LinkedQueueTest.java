package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class LinkedQueueTest {
	
	@Test
	void testLinkedQueue() {
		LinkedQueue<Integer> aq1 = new LinkedQueue<Integer>(10);
		aq1.enqueue(0);
		assertFalse(aq1.isEmpty());
	}
	
	@Test
	void testEnqueue() {
		LinkedQueue<Integer> aq1 = new LinkedQueue<Integer>(3);
		aq1.enqueue(0);
		aq1.enqueue(1);
		aq1.enqueue(2);
		assertThrows(IllegalArgumentException.class, () -> aq1.enqueue(3));
	}

	@Test
	void testDequeue() {
		LinkedQueue<Integer> aq1 = new LinkedQueue<Integer>(10);
		aq1.enqueue(0);
		aq1.enqueue(1);
		aq1.enqueue(2);
		assertEquals(0, aq1.dequeue());
		aq1.enqueue(3);
		assertEquals(1, aq1.dequeue());
		aq1.enqueue(4);
		assertEquals(2, aq1.dequeue());
		assertEquals(3, aq1.dequeue());
		assertEquals(4, aq1.dequeue());
		assertThrows(NoSuchElementException.class, () -> aq1.dequeue());
	}

	@Test
	void testIsEmpty() {
		LinkedQueue<Integer> aq1 = new LinkedQueue<Integer>(10);
		assertTrue(aq1.isEmpty());
		aq1.enqueue(0);
		assertFalse(aq1.isEmpty());
	}

	@Test
	void testSize() {
		LinkedQueue<Integer> aq1 = new LinkedQueue<Integer>(10);
		assertEquals(0, aq1.size());
		aq1.enqueue(0);
		aq1.enqueue(1);
		aq1.enqueue(2);
		assertEquals(3, aq1.size());
	}

	@Test
	void testSetCapacity() {
		LinkedQueue<Integer> aq1 = new LinkedQueue<Integer>(10);
		aq1.enqueue(0);
		aq1.enqueue(1);
		aq1.enqueue(2);
		aq1.setCapacity(3);
		assertThrows(IllegalArgumentException.class, () -> aq1.setCapacity(-1));
		assertThrows(IllegalArgumentException.class, () -> aq1.setCapacity(2));
	}

}
