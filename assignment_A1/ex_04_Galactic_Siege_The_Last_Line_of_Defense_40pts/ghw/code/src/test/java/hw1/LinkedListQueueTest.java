package hw1;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListQueueTest {

    Queue queue;

    @BeforeEach
    public void initQueue() {
        queue = new ModifiedLinkedListQueue();
    }

    @Test
    public void testEnque() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertTrue(queue.front()==1);
    }

    @Test
    public void testDeque() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertThrows(RuntimeException.class, () -> queue.dequeue());   
    }

    @Test
    public void testSetFront() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.setFront(10);
        assertEquals(10, queue.dequeue());
        queue.setFront(20);
        assertEquals(20, queue.dequeue());
        queue.setFront(30);
        assertEquals(30, queue.dequeue());
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