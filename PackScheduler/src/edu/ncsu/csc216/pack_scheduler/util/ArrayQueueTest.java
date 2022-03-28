package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class ArrayQueueTest<E> {
	@Test
	void testArrayQueue() {
		ArrayQueue<E> aq1 = new ArrayQueue(10);
		aq1.enqueue(0);
	}

	@Test
	void testEnqueue() {
		ArrayQueue<E> aq1 = new ArrayQueue<E>(3);
		aq1.enqueue(0);
		aq1.enqueue(1);
		aq1.enqueue(2);
		assertThrows(IllegalArgumentException.class, () -> aq1.enqueue(3));
	}

	@Test
	void testDequeue() {
		ArrayQueue<E> aq1 = new ArrayQueue<E>(10);
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
		ArrayQueue aq1 = new ArrayQueue(10);
		assertTrue(aq1.isEmpty());
		aq1.enqueue(0);
		assertFalse(aq1.isEmpty());
	}

	@Test
	void testSize() {
		ArrayQueue aq1 = new ArrayQueue(10);
		assertEquals(0, aq1.size());
		aq1.enqueue(0);
		aq1.enqueue(1);
		aq1.enqueue(2);
		assertEquals(3, aq1.size());
	}

	@Test
	void testSetCapacity() {
		ArrayQueue aq1 = new ArrayQueue(10);
		aq1.enqueue(0);
		aq1.enqueue(1);
		aq1.enqueue(2);
		aq1.setCapacity(3);
		assertThrows(IllegalArgumentException.class, () -> aq1.setCapacity(-1));
		assertThrows(IllegalArgumentException.class, () -> aq1.setCapacity(2));
	}

}
