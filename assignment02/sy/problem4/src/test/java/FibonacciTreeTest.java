import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FibonacciTreeTest {

    @Test
    public void FibonacciTreePositiveTest() {
        FibonacciTree tree = new FibonacciTree(6);
        assertNotNull(tree.getRoot());
        assertEquals(6, tree.height());
        assertEquals(20, tree.countNodes());
    }

    @Test
    public void FibonacciTreeNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> new FibonacciTree(-1));
    }

    @Test
    public void getRootPositiveTest() {
        FibonacciTree tree = new FibonacciTree(3);
        FibonacciNode root = tree.getRoot();
        assertNotNull(root);
        assertNotNull(root.left);
        assertNotNull(root.right);
    }

    @Test
    public void getRootNegativeTest() {
        FibonacciTree tree = new FibonacciTree();
        assertNull(tree.getRoot());
    }

    @Test
    public void heightPositiveTest() {
        FibonacciTree tree = new FibonacciTree(7);
        assertEquals(7, tree.height());
    }

    @Test
    public void heightNegativeTest() {
        FibonacciTree tree = new FibonacciTree(0);
        assertEquals(0, tree.height());
    }

    @Test
    public void balanceFactorPositiveTest() {
        FibonacciTree tree = new FibonacciTree(5);
        assertEquals(-1, tree.balanceFactor(tree.getRoot()));
        FibonacciNode leftChild = tree.getRoot().left;
        assertEquals(-1, tree.balanceFactor(leftChild));
    }

    @Test
    public void balanceFactorNegativeTest() {
        FibonacciTree tree = new FibonacciTree(5);
        assertEquals(0, tree.balanceFactor(null));
    }

    @Test
    public void countNodesPositiveTest() {
        FibonacciTree tree = new FibonacciTree(8);
        assertEquals(54, tree.countNodes());
    }

    @Test
    public void countNodesNegativeTest() {
        FibonacciTree tree = new FibonacciTree();
        assertEquals(0, tree.countNodes());
    }

    @Test
    public void getBalanceFactorsArrayPositiveTest() {
        FibonacciTree tree = new FibonacciTree(3);
        int[] factors = tree.getBalanceFactorsArray();
        assertNotNull(factors);
        assertEquals(4, factors.length);
        assertEquals(tree.balanceFactor(tree.getRoot()), factors[0]);
        int[] expected = new int[] { -1, -1, 0, 0 };
        assertArrayEquals(expected, factors);
    }

    @Test
    public void getBalanceFactorsArrayNegativeTest() {
        FibonacciTree tree = new FibonacciTree(0);
        assertEquals(0, tree.getBalanceFactorsArray().length);
    }

    @Test
    public void isEmptyPositiveTest() {
        FibonacciTree tree = new FibonacciTree();
        assertTrue(tree.isEmpty());
    }

    @Test
    public void isEmptyNegativeTest() {
        FibonacciTree tree = new FibonacciTree(3);
        assertFalse(tree.isEmpty());
    }

    @Test
    public void FibonacciTreeSize0PositiveTest() {
        FibonacciTree tree = new FibonacciTree(0);
        assertNull(tree.getRoot());
        assertEquals(0, tree.height());
        assertEquals(0, tree.balanceFactor(tree.getRoot()));
        assertEquals(0, tree.countNodes());
        assertEquals(0, tree.getBalanceFactorsArray().length);
        assertTrue(tree.isEmpty());
    }

    @Test
    public void FibonacciTreeSize4PositiveTest() {
        FibonacciTree tree = new FibonacciTree(4);
        FibonacciNode root = tree.getRoot();
        assertNotNull(root);
        assertEquals(4, tree.height());
        assertEquals(-1, tree.balanceFactor(root));
        assertEquals(7, tree.countNodes());
        assertEquals(7, tree.getBalanceFactorsArray().length);
        assertFalse(tree.isEmpty());
    }

    @Test
    public void FibonacciTreeSize7PositiveTest() {
        FibonacciTree tree = new FibonacciTree(7);
        FibonacciNode root = tree.getRoot();
        assertNotNull(root);
        assertEquals(7, tree.height());
        assertEquals(-1, tree.balanceFactor(root));
        assertEquals(33, tree.countNodes());
        assertEquals(33, tree.getBalanceFactorsArray().length);
        assertFalse(tree.isEmpty());
    }

    @Test
    public void FibonacciTreeSize8PositiveTest() {
        FibonacciTree tree = new FibonacciTree(8);
        FibonacciNode root = tree.getRoot();
        assertNotNull(root);
        assertEquals(8, tree.height());
        assertEquals(-1, tree.balanceFactor(root));
        assertEquals(54, tree.countNodes());
        assertEquals(54, tree.getBalanceFactorsArray().length);
        assertFalse(tree.isEmpty());
    }

    @Test
    public void FibonacciTreeSize12PositiveTest() {
        FibonacciTree tree = new FibonacciTree(12);
        FibonacciNode root = tree.getRoot();
        assertNotNull(root);
        assertEquals(12, tree.height());
        assertEquals(-1, tree.balanceFactor(root));
        assertEquals(376, tree.countNodes());
        assertEquals(376, tree.getBalanceFactorsArray().length);
        assertFalse(tree.isEmpty());
    }
}

