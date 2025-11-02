package mygroup;

import org.junit.Before;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class minHeapRepTest {

    @Test
    public void Test1() {
        Node<Integer> root = new Node<>(2);
        root.left = new Node<>(3);
        root.right = new Node<>(4); //Construct a min-heap with height 1.
        assertTrue(minHeapRep.minHeapRepOK(root));
        root.left.right = new Node<>(5); //Construct an incomplete binary tree.
        assertFalse(minHeapRep.minHeapRepOK(root));
        root.left.left = new Node<>(7); // Make the tree constructed above to be complete and be a min-heap.
        assertTrue(minHeapRep.minHeapRepOK(root));
    }

    @Test
    public void Test2() {
        Node<Integer> root = new Node<>(5);
        root.left = new Node<>(1);
        root.right = new Node<>(6); // Construct an invalid min-heap.
        assertFalse(minHeapRep.minHeapRepOK(root));
    }

    @Test
    public void Test3() {
        Node<Integer> root = null;
        assertTrue(minHeapRep.minHeapRepOK(root));
    }
}
