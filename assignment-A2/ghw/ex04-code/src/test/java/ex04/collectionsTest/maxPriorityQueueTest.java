package ex04.collectionsTest;

import org.junit.jupiter.api.*;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import ex04.collections.interfaces.Queue;
import ex04.collections.implementations.maxPriorityQueue;

public class maxPriorityQueueTest {

    Queue<Integer> maxPQ;

    @BeforeEach
    public void init() {
        maxPQ = new maxPriorityQueue<Integer>();
    }

    @Test
    public void enqueueTest()
    {
        maxPQ.enqueue(2);
        maxPQ.enqueue(3);
        assertEquals(3, maxPQ.top());
        maxPQ.enqueue(7);
        maxPQ.enqueue(5);
        assertEquals(7, maxPQ.top());
        maxPQ.enqueue(8);
        maxPQ.enqueue(10);
        assertEquals(10, maxPQ.top());
    }

    @Test
    public void dequeueTest()
    {
        maxPQ.enqueue(1);
        assertEquals(1, maxPQ.dequeue());
        maxPQ.enqueue(2);
        maxPQ.enqueue(7);
        maxPQ.enqueue(5);
        maxPQ.enqueue(8);
        maxPQ.enqueue(6);
        assertEquals(8, maxPQ.dequeue());
        assertEquals(7, maxPQ.dequeue());
        assertEquals(6, maxPQ.dequeue());
        assertEquals(5, maxPQ.dequeue());
        assertEquals(2, maxPQ.dequeue());
        assertThrows(NoSuchElementException.class, () -> maxPQ.dequeue());
    }

    @Test
    public void setTopTest()
    {
        assertThrows(NoSuchElementException.class, () -> maxPQ.top());
        assertThrows(NoSuchElementException.class, () -> maxPQ.setTop(1));
        maxPQ.enqueue(2);
        maxPQ.enqueue(7);
        maxPQ.enqueue(5);
        maxPQ.enqueue(8);
        maxPQ.enqueue(6);
        maxPQ.setTop(1);
        assertEquals(7, maxPQ.top());
        maxPQ.setTop(3);
        assertEquals(6, maxPQ.top());
        maxPQ.setTop(4);
        assertEquals(5, maxPQ.top());
    }
}