package hw2;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListQueueTest {

    Queue queue;

    @BeforeEach
    public void initQueue() {
        queue = new LinkedListQueue();
    }

    @Test
    public void testEnque() {
        assertThrows(RuntimeException.class, () -> queue.front());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertTrue(queue.front()==1);
    }

    @Test
    public void testDeque() {
        queue.enqueue(1);                        // q = [1]
        queue.enqueue(2);                        // q = [1, 2]
        queue.enqueue(3);                        // q = [1, 2, 3]
        assertEquals(1, queue.dequeue());    // q = [2, 3]
        assertEquals(2, queue.dequeue());    // q = [3]
        assertEquals(3, queue.dequeue());    // q = []
        assertThrows(RuntimeException.class, () -> queue.dequeue());
    }

    @Test
    public void testSetFront() {
        assertThrows(RuntimeException.class, () -> queue.setFront(10));
        queue.enqueue(1);                        // q = [1]
        queue.enqueue(2);                        // q = [1, 2]
        queue.enqueue(3);                        // q = [1, 2, 3]
        queue.setFront(10);                   // q = [10, 2, 3]
        assertEquals(10, queue.dequeue());   // q = [2, 3]
        queue.setFront(20);                   // q = [20, 3]
        assertEquals(20, queue.dequeue());   // q = [3]
        queue.setFront(30);                   // q = [30]
        assertEquals(30, queue.dequeue());   // q = []
    }

    @Test
    public void testEmptyQueue() {
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testElemNum() {
        queue.enqueue(1);
        assertEquals(1, queue.elemNum());
        queue.enqueue(2);
        assertEquals(2, queue.elemNum());
        queue.enqueue(3);
        assertEquals(3, queue.elemNum());
        queue.dequeue();
        assertEquals(2, queue.elemNum());
        queue.dequeue();
        assertEquals(1, queue.elemNum());
    }

    @Test
    public void testElemSum() {
        queue.enqueue(1);
        assertEquals(1, queue.elemSum());
        queue.enqueue(2);
        assertEquals(3, queue.elemSum());
        queue.enqueue(3);
        assertEquals(6, queue.elemSum());
        queue.dequeue();
        assertEquals(5, queue.elemSum());
        queue.dequeue();
        assertEquals(3, queue.elemSum());
        queue.dequeue();
        assertEquals(0, queue.elemSum());
    }

    @Test
    public void toStringTest() {
        assertEquals("[  ]", queue.toString());
        queue.enqueue(1);
        assertEquals("[ 1 ]", queue.toString());
        queue.enqueue(2);
        assertEquals("[ 1, 2 ]", queue.toString());
        queue.enqueue(3);
        assertEquals("[ 1, 2, 3 ]", queue.toString());
        queue.dequeue();
        assertEquals("[ 2, 3 ]", queue.toString());
        queue.dequeue();
        assertEquals("[ 3 ]", queue.toString());
        queue.dequeue();
        assertEquals("[  ]", queue.toString());
    }
}