package ds1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    @Test
    public void searchNegativeTest0() {
        AVLTree tree = new AVLTree();
        assertFalse(tree.search(99));
        assertTrue(tree.repOK());
    }

    @Test
    public void searchPositiveTest0() {
        AVLTree tree = new AVLTree();
        tree.insert(8);
        tree.insert(4);
        tree.insert(12);
        assertTrue(tree.search(12));
        assertTrue(tree.search(4));
        assertTrue(tree.repOK());
    }

    @Test
    public void insertPositiveTest0() {
        AVLTree tree = new AVLTree();
        tree.insert(42);
        assertEquals(1, tree.size());
        assertEquals(1, tree.height());
        assertTrue(tree.search(42));
        assertArrayEquals(new int[]{42}, tree.toArray());
        assertTrue(tree.repOK());
    }

    @Test
    public void insertNegativeTest0() {
        AVLTree tree = new AVLTree();
        tree.insert(5);
        assertThrows(IllegalArgumentException.class, () -> tree.insert(5));
        assertTrue(tree.repOK());
    }

    @Test
    public void insertPositiveTest1() {
        AVLTree tree = new AVLTree();
        tree.insert(4);
        tree.insert(2);
        tree.insert(6);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);

        assertEquals(7, tree.size());
        assertEquals(3, tree.height());
        assertTrue(tree.search(3));
        assertFalse(tree.search(9));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7}, tree.toArray());
        assertTrue(tree.repOK());
    }

    @Test
    public void insertNegativeTest1() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        assertThrows(IllegalArgumentException.class, () -> tree.insert(10));
        assertTrue(tree.repOK());
    }

    @Test
    public void deletePositiveTest0() {
        AVLTree tree = new AVLTree();
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);

        tree.delete(1); // remove leaf
        assertFalse(tree.search(1));
        assertEquals(2, tree.size());
        assertTrue(tree.repOK());

        tree.delete(2); // remove old root
        assertFalse(tree.search(2));
        assertEquals(1, tree.size());
        assertArrayEquals(new int[]{3}, tree.toArray());
        assertTrue(tree.repOK());
    }

    @Test
    public void deletePositiveTest1() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(2);
        tree.insert(7);
        tree.insert(12);
        tree.insert(18);
        tree.delete(10);
        assertFalse(tree.search(10));
        assertArrayEquals(new int[]{2, 5, 7, 12, 15, 18}, tree.toArray());
        assertEquals(6, tree.size());
        assertTrue(tree.repOK());
    }

    @Test
    public void deleteNegativeTest0() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(2);
        assertThrows(IllegalArgumentException.class, () -> tree.delete(3));
        assertTrue(tree.repOK());
    }

    @Test
    public void deleteNegativeTest1() {
        AVLTree tree = new AVLTree();
        assertThrows(IllegalArgumentException.class, () -> tree.delete(1));
        assertTrue(tree.repOK());
    }

    @Test
    public void rotationPositiveTest0() {
        AVLTree tree = new AVLTree();
        tree.insert(3);
        tree.insert(2);
        tree.insert(1); // right rotation

        AVLNode root = tree.getRoot();
        assertNotNull(root);
        assertEquals(2, root.value);
        assertEquals(2, tree.height());
        assertArrayEquals(new int[]{1, 2, 3}, tree.toArray());
        assertTrue(tree.repOK());
    }

    @Test
    public void rotationPositiveTest1() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3); // left rotation

        AVLNode root = tree.getRoot();
        assertNotNull(root);
        assertEquals(2, root.value);
        assertEquals(2, tree.height());
        assertArrayEquals(new int[]{1, 2, 3}, tree.toArray());
        assertTrue(tree.repOK());
    }

    @Test
    public void repOkPositiveTest0() {
        AVLTree tree = new AVLTree();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertEquals(0, tree.height());
        assertArrayEquals(new int[0], tree.toArray());
        assertTrue(tree.repOK());
    }

    @Test
    public void repOkNegativeTest0() {
        AVLTree tree = new AVLTree();
        tree.insert(3);
        tree.insert(1);
        tree.insert(4);

        AVLNode root = tree.getRoot();
        root.left = root; // create a cycle
        assertFalse(tree.repOK());
    }

    @Test
    public void repOkNegativeTest1() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        // break height invariant
        AVLNode root = tree.getRoot();
        root.height = 99;
        assertFalse(tree.repOK());
    }
}
