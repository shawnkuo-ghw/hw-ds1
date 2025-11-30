package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AVLTests {
    @Test
    public void testInsert() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        assertEquals(2, tree.getRootValue());
    }

    @Test
    public void testDelete() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.delete(2);
        assertEquals(3, (int) tree.getRootValue());
    }
    @Test
    public void testTreeLongHeight() {
        AVLTree avl = new AVLTree();
        avl.insert(1);
        avl.insert(2);
        avl.insert(3);
        avl.insert(4);
        avl.insert(5);
        avl.insert(6);
        avl.insert(7);
        avl.insert(8);
        avl.print();
        ListoverLinkedList<Integer> inorder = avl.inorder();
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
        assertArrayEquals(expected,inorder.toArray());
    }


    @Test
    public void testSearch() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        assertTrue(tree.search(2));
        assertFalse(tree.search(4));
    }

    @Test
    public void testBalance() {
        AVLTree tree = new AVLTree();
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.print();
        assertEquals(2, (int) tree.getRootValue());
        assertEquals(1, (int) tree.getLeft().getValue());
        assertEquals(3, (int) tree.getRight().getValue());
    }

    @Test
    public void testInorder() {
        AVLTree avl = new AVLTree();
        avl.insert(5);
        avl.insert(3);
        avl.insert(7);
        avl.insert(1);
        avl.insert(4);
        avl.insert(6);
        avl.insert(8);
        ListoverLinkedList<Integer> inorder = avl.inorder();
        int[] expected = {1, 3, 4, 5, 6, 7, 8};
        assertArrayEquals(expected,inorder.toArray());
    }
    @Test
    public void testInorderWithRemove() {
        AVLTree avl = new AVLTree();
        avl.insert(1);
        assertEquals(1, avl.height());
        avl.insert(2);
        assertEquals(2, avl.height());
        avl.insert(3);
        avl.insert(4);
        avl.insert(5);
        avl.insert(6);
        assertEquals(3, avl.height());
        System.out.println("Before delete");
        avl.print();
        avl.delete(2);
        System.out.println("After delete 3");
        avl.print();
        avl.insert(7);
        System.out.println("After insert 7");
        ListoverLinkedList<Integer> inorder = avl.inorder();
        int[] expected = {1, 3, 4,  5, 6, 7};
        assertArrayEquals(expected,inorder.toArray());
    }
}