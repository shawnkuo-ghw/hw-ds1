package ds1;
import ds1.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class GenericPQTest
{
    GenericPQ<Integer> pq;

    @BeforeEach
    void init() { pq = new GenericMaxPQ<Integer>(Integer.class); }

    @Test
    void testEnqueueDequeueNext()
    {        
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
        // pq is empty
        assertEquals(0, pq.size());
        assertTrue(pq.isEmpty());

        pq.enqueue(3); // pq = [3]
        
        assertEquals(1, pq.size());
        assertFalse(pq.isEmpty());

        pq.enqueue(1); // pq = [3, 1]
        pq.enqueue(4); // pq = [4, 1, 3]
        
        assertEquals(3, pq.size());
        assertFalse(pq.isEmpty());
        
        pq.enqueue(2); // pq = [4, 2, 3, 1]
        pq.enqueue(5); // pq = [5, 4, 3, 1, 2]
        
        // pq is full
        assertEquals(5, pq.size());
        assertFalse(pq.isEmpty());
    }

    @Test
    void testDequeueEmpty()
    {
        assertThrows(NoSuchElementException.class, () -> pq.next());
        assertThrows(NoSuchElementException.class, () -> pq.dequeue());
    }

    @Test
    void testToArray()
    {
        pq.enqueue(3);
        pq.enqueue(1);
        pq.enqueue(4);
        pq.enqueue(2);
        pq.enqueue(5);
        Integer[] arr = pq.toArray();
        assertEquals(5, arr[0]);
        assertEquals(4, arr[1]);
        assertEquals(3, arr[2]);
        assertEquals(2, arr[3]);
        assertEquals(1, arr[4]);
    }

    @Test
    void testCopyConstructor()
    {
        pq.enqueue(3);
        pq.enqueue(4);
        pq.enqueue(2);
        // construct a new priority queue `pq`, which is the same as pq
        GenericPQ<Integer> pq2 = new GenericMaxPQ<Integer>((GenericMaxPQ<Integer>) pq);
        Integer[] pq2Arr = pq2.toArray();
        assertEquals(3, pq2Arr.length);
        assertEquals(4, pq2Arr[0]);
        assertEquals(3, pq2Arr[1]);
        assertEquals(2, pq2Arr[2]);
        // modify pq2
        pq2.enqueue(5);
        pq2.enqueue(1);
        Integer[] pq2Arr2 = pq2.toArray();
        assertEquals(5, pq2Arr2.length);
        assertEquals(5, pq2Arr2[0]);
        assertEquals(4, pq2Arr2[1]);
        assertEquals(3, pq2Arr2[2]);
        assertEquals(2, pq2Arr2[3]);
        assertEquals(1, pq2Arr2[4]);
        // pq should not be changed
        Integer[] pqArr = pq.toArray();
        assertEquals(3, pq2Arr.length);
        assertEquals(4, pq2Arr[0]);
        assertEquals(3, pq2Arr[1]);
        assertEquals(2, pq2Arr[2]);
    }
}