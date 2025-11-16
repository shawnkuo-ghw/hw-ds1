package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MaxHeapTest {
    @Test
    public void testInsert() {
        MaxHeapArray maxHeap = new MaxHeapArray(10);
        maxHeap.insert(5);
        maxHeap.insert(10);
        maxHeap.insert(7);
        assertEquals(10, maxHeap.getMax());
    }

    @Test
    public void testExtractMax() {
        MaxHeapArray maxHeap = new MaxHeapArray(10);
        maxHeap.insert(5);
        maxHeap.insert(10);
        maxHeap.insert(7);
        assertEquals(10, maxHeap.extractMax());
        assertEquals(7, maxHeap.getMax());
    }

    @Test
    public void testGetMax() {
        MaxHeapArray maxHeap = new MaxHeapArray(10);
        maxHeap.insert(5);
        maxHeap.insert(10);
        maxHeap.insert(7);
        assertEquals(10, maxHeap.getMax());
    }

    @Test
    public void testBuildMaxHeap() {
        int[] initialArray = {5, 3, 8, 1, 6, 9, 2};
        MaxHeapArray maxHeap = MaxHeapArray.buildMaxHeap(initialArray);
        maxHeap.printHeapAsTree();
        assertEquals(9, maxHeap.getMax());
    }

    @Test
    public void testEj1() {
        int[] initialArray = {23, 17, 14, 6, 13, 10, 1, 5, 7, 12};
        assertFalse(MaxHeapArray.testMaxHeapProperty(initialArray));
        MaxHeapArray maxHeap = MaxHeapArray.buildMaxHeap(initialArray);
        maxHeap.printHeapAsTree();
        assertTrue(MaxHeapArray.testMaxHeapProperty(maxHeap.getHeap()));
        maxHeap.printAsArray();
    }

    @Test
    public void testChangeValue() {
        MaxHeapArray maxHeap = new MaxHeapArray(10);
        // Add some initial values
        for (int i = 0; i < 10; i++) {
            maxHeap.insert(i);
        }
        maxHeap.changeValue(5, 20);
        assertEquals(20, maxHeap.extractMax());
        
        // Test exception case
        assertThrows(IllegalArgumentException.class, () -> {
            maxHeap.changeValue(-1, 20);
        });
    }
    
}