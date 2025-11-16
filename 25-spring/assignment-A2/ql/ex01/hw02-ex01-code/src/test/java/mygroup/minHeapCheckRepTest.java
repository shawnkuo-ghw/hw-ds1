package mygroup;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class minHeapCheckRepTest {
        @Test
        public void testWholeMinheap() {
            Integer[] A1 = {3, 6, 4, 13, 16, 18}; // valid min-heap.
            assertTrue(minHeapCheckRep.minHeapRepOK(A1, 0, 5));

            Integer[] A2 = {3, 13, 4, 6, 16, 18}; // invalid min-heap.
            assertFalse(minHeapCheckRep.minHeapRepOK(A2, 0, 5));
        }

        @Test
        public void testSubarray(){
            Integer[] A3 = {3, 5, 4, 7, 12, 6, 5, 8}; // A3[1:5] is valid.
            assertTrue(minHeapCheckRep.minHeapRepOK(A3, 1, 5));

            Integer[] A4 = {3, 7, 4, 5, 12, 6, 5, 8}; // A3[1:5] is valid.
            assertFalse(minHeapCheckRep.minHeapRepOK(A4, 1, 5));
        }

        @Test
        public void testSingleValue(){
            Integer[] A5 = {10};
            assertTrue(minHeapCheckRep.minHeapRepOK(A5, 0, 0));
        }

        @Test
        public void testInvalidIndex(){
            Integer[] A6 = {3, 6, 4, 13, 16, 18};
            assertFalse(minHeapCheckRep.minHeapRepOK(A6, -1, 6));
            assertFalse(minHeapCheckRep.minHeapRepOK(A6, 0, 6));
            assertFalse(minHeapCheckRep.minHeapRepOK(A6, -1, 5));
        }

        @Test
        public void testNull(){
            Integer[] A7 = null;
            assertFalse(minHeapCheckRep.minHeapRepOK(A7, 1, 5));
        }
}
