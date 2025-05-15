package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayIntQueueTest {

    private ArrayIntQueue queue;

    @Before
    public void setUp() {
        queue = new ArrayIntQueue();
    }

    @Test
    public void testEnqueueDequeueBasic() {
        assertTrue(queue.isEmpty());
        assertTrue(queue.enqueue(10));
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());

        assertEquals(Integer.valueOf(10), queue.dequeue());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testPeek() {
        assertNull(queue.peek());

        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(Integer.valueOf(1), queue.peek());
        assertEquals(2, queue.size());
    }

    @Test
    public void testClear() {
        queue.enqueue(5);
        queue.enqueue(6);
        queue.clear();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peek());
        assertNull(queue.dequeue());
    }

    @Test
    public void testMultipleEnqueueDequeue() {
        for (int i = 0; i < 20; i++) {
            assertTrue(queue.enqueue(i));
        }

        assertEquals(20, queue.size());
        for (int i = 0; i < 20; i++) {
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testWrapAround() {
        // fill to capacity and dequeue a few
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }

        // enqueue more to cause wrap-around
        for (int i = 10; i < 15; i++) {
            queue.enqueue(i);
        }

        // validate the rest
        for (int i = 5; i < 15; i++) {
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testResize() {
        int n = 100;
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }

        assertEquals(n, queue.size());

        for (int i = 0; i < n; i++) {
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }
}