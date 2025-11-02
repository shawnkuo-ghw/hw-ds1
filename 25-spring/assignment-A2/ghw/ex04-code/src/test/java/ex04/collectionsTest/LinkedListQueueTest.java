package ex04.collectionsTest;

import ex04.collections.interfaces.Queue;
import ex04.collections.implementations.LinkedListQueue;

import org.junit.jupiter.api.*;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


public class LinkedListQueueTest {
    
    Queue<Integer> llq;

    @BeforeEach
    public void init() {
        llq = new LinkedListQueue<Integer>();
    }

    @Test
    public void enqueueTest() {
        llq.enqueue(1);
        llq.enqueue(2);
        llq.enqueue(3);
        assertEquals(3, llq.size());
        assertEquals("[1, 2, 3]", llq.toString());
    }

    @Test
    public void dequeueTest() {
        assertThrows(NoSuchElementException.class, () -> llq.dequeue());
        llq.enqueue(1);
        llq.enqueue(2);
        llq.enqueue(3);
        assertEquals(1, llq.dequeue());
        assertEquals(2, llq.size());
        assertEquals("[2, 3]", llq.toString()); 
    }

    @Test
    public void visitTest() {
        assertTrue(llq.empty());
        llq.enqueue(1);
        llq.enqueue(2);
        llq.enqueue(3);
        assertEquals("[1, 2, 3]", llq.toString());
        assertEquals(3, llq.size());
        assertEquals(1, llq.top());
        llq.setTop(4);
        assertEquals("[4, 2, 3]", llq.toString());
        assertEquals(3, llq.size());
        assertEquals(4, llq.top());
    }
}