package ex04.collectionsTest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import ex04.collections.interfaces.PriorityQueue;
import ex04.collections.implementations.MinPriorityQueue;

public class MinPriorityQueueTest
{
    private PriorityQueue<Integer> minPQ;

    @BeforeEach
    public void init() {
        minPQ = new MinPriorityQueue<Integer>();
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
}