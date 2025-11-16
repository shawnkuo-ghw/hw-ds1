package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTests {
    @Test
    public void testEnqueue() {
        PriorityQueue queue = new PriorityQueue(10);
        queue.enqueue(5);
        queue.enqueue(10);
        queue.enqueue(7);
        assertEquals(10, queue.next());
    }

    @Test
    public void testDequeue() {
        PriorityQueue queue = new PriorityQueue(10);
        queue.enqueue(5);
        queue.enqueue(10);
        queue.enqueue(7);
        assertEquals(10, queue.dequeue());
        assertEquals(7, queue.next());
    }

    @Test
    public void testPeek() {
        PriorityQueue queue = new PriorityQueue(10);
        queue.enqueue(5);
        queue.enqueue(10);
        queue.enqueue(7);
        assertEquals(10, queue.next());
        assertEquals(10, queue.next()); // peek again to ensure the value is not removed
    }
}