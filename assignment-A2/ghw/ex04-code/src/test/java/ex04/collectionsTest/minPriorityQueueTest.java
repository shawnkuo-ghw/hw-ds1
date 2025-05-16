package ex04.collectionsTest;

import org.junit.jupiter.api.*;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import ex04.collections.interfaces.Queue;
import ex04.collections.implementations.minPriorityQueue;

public class minPriorityQueueTest {
    
    Queue<Integer> minPQ;

    @BeforeEach
    public void init() {
        minPQ = new minPriorityQueue<Integer>();
    }

    @Test
    public void enqueueTest()
    {
        minPQ.enqueue(8);
        minPQ.enqueue(10);
        assertEquals(8, minPQ.top());
        minPQ.enqueue(7);
        minPQ.enqueue(5);
        assertEquals(5, minPQ.top());
        minPQ.enqueue(2);
        minPQ.enqueue(3);
        assertEquals(2, minPQ.top());
    }

    @Test
    public void dequeueTest()
    {
        minPQ.enqueue(1);
        assertEquals(1, minPQ.dequeue());
        minPQ.enqueue(6);
        minPQ.enqueue(8);
        minPQ.enqueue(5);
        minPQ.enqueue(7);
        minPQ.enqueue(2);
        assertEquals(2, minPQ.dequeue());
        assertEquals(5, minPQ.dequeue());
        assertEquals(6, minPQ.dequeue());
        assertEquals(7, minPQ.dequeue());
        assertEquals(8, minPQ.dequeue());
        assertThrows(NoSuchElementException.class, () -> minPQ.dequeue());
    }

    @Test
    public void setTopTest()
    {
        assertThrows(NoSuchElementException.class, () -> minPQ.top());
        assertThrows(NoSuchElementException.class, () -> minPQ.setTop(1));
        minPQ.enqueue(6);
        minPQ.enqueue(8);
        minPQ.enqueue(5);
        minPQ.enqueue(7);
        minPQ.enqueue(2);
        minPQ.setTop(10);
        assertEquals(5, minPQ.top());
        minPQ.setTop(12);
        assertEquals(6, minPQ.top());
        minPQ.setTop(14);
        assertEquals(7, minPQ.top());
    }
}