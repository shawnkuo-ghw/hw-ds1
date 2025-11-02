package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MinHeapTreeRepTest {
    @Test
    public void testNullTree() {
        assertTrue(MinHeapTreeRep.minHeapRepOK(null));
    }

    @Test
    public void testValidTree() {
        MinHeapTreeRep.Node<Integer> root = new MinHeapTreeRep.Node<Integer>(1);
        root.left = new MinHeapTreeRep.Node<Integer>(2);
        root.right = new MinHeapTreeRep.Node<Integer>(3);
        root.left.parent = root;
        root.right.parent = root;
        root.left.left = new MinHeapTreeRep.Node<Integer>(4);
        root.left.right = new MinHeapTreeRep.Node<Integer>(5);
        root.left.left.parent = root.left;
        root.left.right.parent = root.left;
        root.right.left = new MinHeapTreeRep.Node<Integer>(6);
        root.right.left.parent = root.right;

        assertTrue(MinHeapTreeRep.minHeapRepOK(root));
    }

    @Test
    public void testViolationTree() {
        MinHeapTreeRep.Node<Integer> root = new MinHeapTreeRep.Node<Integer>(2);
        root.left = new MinHeapTreeRep.Node<Integer>(1);
        root.right = new MinHeapTreeRep.Node<Integer>(3);
        root.left.parent = root;
        root.right.parent = root;

        assertFalse(MinHeapTreeRep.minHeapRepOK(root));
    }

    @Test
    public void testUndefinedTree() {
        MinHeapTreeRep.Node<Integer> root = new MinHeapTreeRep.Node<Integer>(1);
        root.left = new MinHeapTreeRep.Node<Integer>(2);
        root.right = new MinHeapTreeRep.Node<Integer>(3);

        assertFalse(MinHeapTreeRep.minHeapRepOK(root));
    }
}
