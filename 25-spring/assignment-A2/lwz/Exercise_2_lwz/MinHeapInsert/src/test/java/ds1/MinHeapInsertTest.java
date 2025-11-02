package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinHeapInsertTest {
    
    @Test
    public void testNullHeap() {
        MinHeapInsert<Integer> heap = new MinHeapInsert<>();
        MinHeapInsert.Node<Integer> root = null;

        root = heap.insertion(root, 1);

        assertTrue(heap.minHeapRepOK(root));
    }

    @Test
    public void testValidHeap() {
        MinHeapInsert<Integer> heap = new MinHeapInsert<>();
        MinHeapInsert.Node<Integer> root = null;

        root = heap.insertion(root, 3);
        root = heap.insertion(root, 2);
        root = heap.insertion(root, 5);
        root = heap.insertion(root, 7);
        root = heap.insertion(root, 1);

        assertTrue(heap.minHeapRepOK(root));
    }

}
