package ds1;
import ds1.util.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class GenericPQTest
{
    GenericPQ<Integer> pq;
    int capacity;

    @Test
    void testEnqueueDequeueNext()
    {
        capacity = 8;
        pq = new GenericMaxPQ<Integer>(Integer.class, capacity);
        pq.enqueue(2);
        pq.enqueue(7);
        pq.enqueue(6);
        pq.enqueue(3);
        pq.enqueue(5);
        pq.enqueue(8);
        pq.enqueue(1);
        pq.enqueue(4);
        assertEquals(8, pq.next());
        assertEquals(8, pq.dequeue());
        assertEquals(7, pq.next());
        assertEquals(7, pq.dequeue());
        assertEquals(6, pq.next());
        assertEquals(6, pq.dequeue());
        assertEquals(5, pq.next());
        assertEquals(5, pq.dequeue());
        assertEquals(4, pq.next());
        assertEquals(4, pq.dequeue());
        assertEquals(3, pq.next());
        assertEquals(3, pq.dequeue());
        assertEquals(2, pq.next());
        assertEquals(2, pq.dequeue());
        assertEquals(1, pq.next());
        assertEquals(1, pq.dequeue());
    }

    @Test
    void testGetters()
    {
        capacity = 5;
        pq = new GenericMaxPQ<Integer>(Integer.class, capacity);
        // pq is empty
        assertEquals(0, pq.size());
        assertTrue(pq.isEmpty());
        assertFalse(pq.isFull());
        pq.enqueue(3); // pq = [3]
        assertEquals(1, pq.size());
        assertFalse(pq.isEmpty());
        assertFalse(pq.isFull());
        pq.enqueue(1); // pq = [3, 1]
        pq.enqueue(4); // pq = [4, 1, 3]
        assertEquals(3, pq.size());
        assertFalse(pq.isEmpty());
        assertFalse(pq.isFull());
        pq.enqueue(2); // pq = [4, 2, 3, 1]
        pq.enqueue(5); // pq = [5, 4, 3, 1, 2]
        // pq is full
        assertEquals(5, pq.size());
        assertFalse(pq.isEmpty());
        assertTrue(pq.isFull());
    }

    @Test
    void testDequeueEmpty()
    {
        capacity = 5;
        pq = new GenericMaxPQ<Integer>(Integer.class, capacity);
        assertThrows(NoSuchElementException.class, () -> pq.next());
        assertThrows(NoSuchElementException.class, () -> pq.dequeue());
    }

    @Test
    void testEnqueueFull()
    {
        capacity = 5;
        pq = new GenericMaxPQ<Integer>(Integer.class, capacity);
        pq.enqueue(3);
        pq.enqueue(1);
        pq.enqueue(4);
        pq.enqueue(2);
        pq.enqueue(5);
        assertThrows(IllegalStateException.class, () -> pq.enqueue(6));
    }

    @Test
    void testToArray()
    {
        capacity = 5;
        pq = new GenericMaxPQ<Integer>(Integer.class, capacity);
        pq.enqueue(3);
        pq.enqueue(1);
        pq.enqueue(4);
        pq.enqueue(2);
        pq.enqueue(5);
        Integer[] pqArr = pq.toArray();
        assertEquals(5, pqArr[0]);
        assertEquals(4, pqArr[1]);
        assertEquals(3, pqArr[2]);
        assertEquals(2, pqArr[3]);
        assertEquals(1, pqArr[4]);
    }
}