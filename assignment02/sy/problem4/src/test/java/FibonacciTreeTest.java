import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FibonacciTreeTest {

    @Test
    public void testFibonacciTree1() {
        FibonacciTree tree = new FibonacciTree(0);
        assertTrue(tree.isEmpty());
        assertNull(tree.getRoot());
        assertEquals(0, tree.countNodes());
        assertEquals(0, tree.height());
    }

    @Test
    public void testFibonacciTree2() {
        FibonacciTree tree = new FibonacciTree(1);
        assertFalse(tree.isEmpty());
        assertNotNull(tree.getRoot());
        assertNull(tree.getRoot().getLeft());
        assertNull(tree.getRoot().getRight());
        assertEquals(1, tree.countNodes());
        assertEquals(1, tree.height());
        assertEquals(0, tree.balanceFactor(tree.getRoot()));
    }

    @Test
    public void testFibonacciTree3() {
        FibonacciTree tree = new FibonacciTree(2);
        assertNotNull(tree.getRoot());
        assertNotNull(tree.getRoot().getLeft());
        assertNull(tree.getRoot().getRight());
        assertEquals(2, tree.countNodes());
        assertEquals(2, tree.height());
        assertEquals(-1, tree.balanceFactor(tree.getRoot()));
    }

    @Test
    public void testFibonacciTree4() {
        FibonacciTree tree = new FibonacciTree(3);
        assertNotNull(tree.getRoot());
        assertNotNull(tree.getRoot().getLeft());
        assertNotNull(tree.getRoot().getRight());
        assertEquals(4, tree.countNodes());
        assertEquals(3, tree.height());
        assertEquals(-1, tree.balanceFactor(tree.getRoot()));
        assertEquals(-1, tree.balanceFactor(tree.getRoot().getLeft()));
        assertEquals(0, tree.balanceFactor(tree.getRoot().getRight()));
    }

    @Test
    public void testBalanceFactorsArray() {
        FibonacciTree tree = new FibonacciTree(2);
        int[] factors = tree.getBalanceFactorsArray();
        assertEquals(2, factors.length);
        assertEquals(-1, factors[0]);
        assertEquals(0, factors[1]);
    }

    @Test
    public void testEmptyTreeBalanceFactorsArray() {
        FibonacciTree tree = new FibonacciTree(0);
        int[] factors = tree.getBalanceFactorsArray();
        assertEquals(0, factors.length);
    }

    @Test
    public void testNegativeIndex() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FibonacciTree(-1);
        });
    }

    @Test
    public void testPrintBalanceFactors() {
        FibonacciTree tree = new FibonacciTree(3);
        assertDoesNotThrow(() -> tree.printBalanceFactors());
    }
}

