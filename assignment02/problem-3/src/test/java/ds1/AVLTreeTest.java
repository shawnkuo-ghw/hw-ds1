package ds1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    @Test
    public void searchNegativeTest0() {
        AVLTree tree = new AVLTree();
        assertNull(tree.search(99));
        assertTrue(tree.repOK());
    }

    @Test
    public void searchPositiveTest0() {
        AVLTree tree = new AVLTree();
        tree.insert(8);
        tree.insert(4);
        tree.insert(12);
        AVLNode twelve = tree.search(12);
        AVLNode four = tree.search(4);
        assertNotNull(twelve);
        assertEquals(12, twelve.value);
        assertNotNull(four);
        assertEquals(4, four.value);
        assertTrue(tree.repOK());
    }

    @Test
    public void insertPositiveTest0() {
        AVLTree tree = new AVLTree();
        AVLNode rootAfterInsert = tree.insert(42);
        assertNotNull(rootAfterInsert);
        assertSame(tree.getRoot(), rootAfterInsert);
        assertEquals(42, rootAfterInsert.value);
        assertEquals(1, tree.size());
        assertEquals(1, tree.height());
        assertNotNull(tree.search(42));
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
        AVLNode three = tree.search(3);
        assertNotNull(three);
        assertEquals(3, three.value);
        assertNull(tree.search(9));
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

        AVLNode rootAfterLeafRemoval = tree.delete(1); // remove leaf
        assertSame(tree.getRoot(), rootAfterLeafRemoval);
        assertNull(tree.search(1));
        assertEquals(2, tree.size());
        assertTrue(tree.repOK());

        AVLNode rootAfterRootRemoval = tree.delete(2); // remove old root
        assertSame(tree.getRoot(), rootAfterRootRemoval);
        assertNull(tree.search(2));
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
        AVLNode newRoot = tree.delete(10);
        assertSame(tree.getRoot(), newRoot);
        assertEquals(12, newRoot.value);
        assertNull(tree.search(10));
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
        // break height
        AVLNode root = tree.getRoot();
        root.height = 99;
        assertFalse(tree.repOK());
    }

    @Test //Bst Order test
    public void repOkNegativeTest2() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        AVLNode root = tree.getRoot();
        //left child greater than parent
        root.left.value = 20;

        assertFalse(tree.repOK());
    }
}
