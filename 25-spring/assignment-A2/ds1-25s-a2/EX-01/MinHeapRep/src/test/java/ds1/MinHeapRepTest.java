package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinHeapRepTest {
    
    @Test
    public void testEmptyHeap() {
        Integer[] A = new Integer[1]; // only A[0], won't be transmitted into Min-Heap Representation
        assertFalse(MinHeapRep.minHeapRepOK(A, 1, 0)); //start < end
    }

    @Test
    public void testValidMinHeap() {
        Integer[] A = {null, 1, 3, 2, 5, 4}; // `Integer` to use compareTo
        assertTrue(MinHeapRep.minHeapRepOK(A, 1, 5));
        Integer[] B = {null, 2, 4, 6, 8, 10, 20};
        assertTrue(MinHeapRep.minHeapRepOK(B, 2, 6));
        Integer[] C = {null, 19};
        assertTrue(MinHeapRep.minHeapRepOK(C, 1, 1));
    }

    @Test
    public void testInvalidMinHeap() {
        Integer[] A = {null, 5, 1, 2, 3, 4};
        assertFalse(MinHeapRep.minHeapRepOK(A, 1, 5));
        Integer[] B = {null, 1, 3, 4, 5, 2, 6};
        assertFalse(MinHeapRep.minHeapRepOK(B, 2, 6));
    }
}
