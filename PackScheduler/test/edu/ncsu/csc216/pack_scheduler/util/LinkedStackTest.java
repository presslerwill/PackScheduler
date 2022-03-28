package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests methods from the LinkedStack class
 * 
 * @author Jason Wong
 *
 */
class LinkedStackTest {
    /** LinkedStack object for use in tests */
    LinkedStack<String> stack;
    /** Default capacity for the LinkedStack objects in tests */
    private static final int CAPACITY = 2;
   
    /**
     * Constructs the LinkedStack object with the default capacity before each test
     */
    @BeforeEach
    void setup() {
        stack = new LinkedStack<String>(CAPACITY);
    }

    /**
     * Test method for the LinkedStack constructor.
     */
    @Test
    void testLinkedStack() {
        stack.push("1");
        stack.push("2");
        assertThrows(IllegalArgumentException.class, () -> stack.push("3"));
    }

    /**
     * Test method for push() and pop().
     */
    @Test
    void testPushAndPop() {
        stack.push("1");
        stack.push("2");
        
        assertEquals(2, stack.size());
        
        assertEquals("2", stack.pop());
        assertEquals("1", stack.pop());
        assertTrue(stack.isEmpty());
    }

    /**
     * Test method for isEmpty().
     */
    @Test
    void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push("1");
        assertFalse(stack.isEmpty());
        stack.push("2");
        assertFalse(stack.isEmpty());
    }

    /**
     * Test method for size().
     */
    @Test
    void testSize() {
        assertEquals(0, stack.size());
        stack.push("1");
        assertEquals(1, stack.size());
        stack.push("2");
        assertEquals(2, stack.size());
    }

    /**
     * Test method for setCapcity().
     */
    @Test
    void testSetCapacity() {
        assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(-1));
        stack.push("1");
        stack.push("2");
        assertThrows(IllegalArgumentException.class, () -> stack.push("3"));
        stack.setCapacity(4);
        stack.push("3");
        stack.push("4");
        assertThrows(IllegalArgumentException.class, () -> stack.push("5"));
        
        assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(2));
    }

}
