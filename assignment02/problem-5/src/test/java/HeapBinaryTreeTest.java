import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HeapBinaryTreeTest {

    @Test
    public void toArrayComplexPositiveTest() {
        int[] array = {55, 40, 50, 20, 35, 10, 5, 18, 12, 30, 33};
        HeapBinaryTree tree = HeapBinaryTree.fromArray(array);
        int[] result = tree.toArray();
        assertArrayEquals(array, result);
        assertTrue(tree.isMaxHeap());
        assertEquals(array.length, tree.countNodes());
        HeapNode root = tree.getRoot();
        assertNotNull(root);
        assertEquals(55, root.getData());
        assertEquals(40, root.getLeft().getData());
        assertEquals(50, root.getRight().getData());
        assertEquals(20, root.getLeft().getLeft().getData());
        assertEquals(35, root.getLeft().getRight().getData());
        assertEquals(10, root.getRight().getLeft().getData());
        assertEquals(5, root.getRight().getRight().getData());
        assertEquals(18, root.getLeft().getLeft().getLeft().getData());
        assertEquals(12, root.getLeft().getLeft().getRight().getData());
        assertEquals(30, root.getLeft().getRight().getLeft().getData());
        assertEquals(33, root.getLeft().getRight().getRight().getData());
    }

    @Test
    public void toArrayEmptyTest() {
        HeapBinaryTree tree = new HeapBinaryTree();
        assertEquals(0, tree.toArray().length);
        assertTrue(tree.isEmpty());
    }

    @Test
    public void toArrayIncompleteTreeNegativeTest() {
        HeapNode root = new HeapNode(10);
        root.right = new HeapNode(5);
        HeapBinaryTree tree = new HeapBinaryTree(root);
        assertThrows(IllegalStateException.class, () -> tree.toArray());
    }

    @Test
    public void fromArrayPositiveTest() {
        int[] array = {80, 60, 50, 30, 20, 10};
        HeapBinaryTree tree = HeapBinaryTree.fromArray(array);
        HeapNode root = tree.getRoot();
        assertNotNull(root);
        assertEquals(80, root.getData());
        assertEquals(60, root.getLeft().getData());
        assertEquals(50, root.getRight().getData());
        assertEquals(30, root.getLeft().getLeft().getData());
        assertEquals(20, root.getLeft().getRight().getData());
        assertEquals(10, root.getRight().getLeft().getData());
        assertTrue(tree.isMaxHeap());
        assertEquals(array.length, tree.countNodes());
    }

    @Test
    public void fromArrayEmptyNegativeTest() {
        int[] array = {};
        HeapBinaryTree tree = HeapBinaryTree.fromArray(array);
        assertNull(tree.getRoot());
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.countNodes());
        assertEquals(0, tree.toArray().length);
    }

    @Test
    public void fromArrayNullNegativeTest() {
        HeapBinaryTree tree = HeapBinaryTree.fromArray(null);
        assertNull(tree.getRoot());
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.countNodes());
        assertEquals(0, tree.toArray().length);
        assertTrue(tree.isMaxHeap());
    }

    @Test
    public void isMaxHeapPositiveTest() {
        int[] array = {90, 70, 60, 40, 50, 30, 20};
        HeapBinaryTree tree = HeapBinaryTree.fromArray(array);
        HeapNode root = tree.getRoot();
        assertNotNull(root);
        assertEquals(90, root.getData());
        assertEquals(70, root.getLeft().getData());
        assertEquals(60, root.getRight().getData());
        assertTrue(tree.isMaxHeap());
        assertArrayEquals(array, tree.toArray());
        assertEquals(array.length, tree.countNodes());
    }

    @Test
    public void isMaxHeapNegativeTest() {
        int[] array = {10, 30, 40};
        HeapBinaryTree tree = HeapBinaryTree.fromArray(array);
        assertFalse(tree.isMaxHeap());
        assertArrayEquals(array, tree.toArray());
    }
}
