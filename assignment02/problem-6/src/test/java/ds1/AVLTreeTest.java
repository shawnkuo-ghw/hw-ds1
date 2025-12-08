package ds1;
import ds1.util.AVLTree;
import ds1.util.AVLNode;
import ds1.util.AVLTreeImple;
import org.junit.jupiter.api.*;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest
{
    AVLTree<Integer, Integer> t;

    @BeforeEach
    void init()
    {
        t = new AVLTreeImple<Integer, Integer>(AVLNode.class, Integer.class);
    }
    
    @Test
    void testInsertAndSearch01()
    {
        t.insertTree(1, 1);
        t.insertTree(2, 2);
        t.insertTree(3, 3);
        t.insertTree(4, 4);
        t.insertTree(5, 5);
        t.insertTree(6, 6);
        t.insertTree(7, 7);
        assertEquals(1, t.searchTree(1));
        assertEquals(2, t.searchTree(2));
        assertEquals(3, t.searchTree(3));
        assertEquals(4, t.searchTree(4));
        assertEquals(5, t.searchTree(5));
        assertEquals(6, t.searchTree(6));
        assertEquals(7, t.searchTree(7));
        assertNull(t.searchTree(8));
    }

    @Test
    void testInsertAndSearch02()
    {
        t.insertTree(7, 7);
        t.insertTree(6, 6);
        t.insertTree(5, 5);
        t.insertTree(4, 4);
        t.insertTree(3, 3);
        t.insertTree(2, 2);
        t.insertTree(1, 1);
        assertEquals(1, t.searchTree(1));
        assertEquals(2, t.searchTree(2));
        assertEquals(3, t.searchTree(3));
        assertEquals(4, t.searchTree(4));
        assertEquals(5, t.searchTree(5));
        assertEquals(6, t.searchTree(6));
        assertEquals(7, t.searchTree(7));
        assertNull(t.searchTree(8));
    }

    @Test
    void testInsertAndSearch03()
    {
        t.insertTree(6, 6);
        t.insertTree(1, 1);
        t.insertTree(5, 5);
        t.insertTree(2, 2);
        t.insertTree(7, 7);
        t.insertTree(4, 4);
        t.insertTree(3, 3);
        assertEquals(1, t.searchTree(1));
        assertEquals(2, t.searchTree(2));
        assertEquals(3, t.searchTree(3));
        assertEquals(4, t.searchTree(4));
        assertEquals(5, t.searchTree(5));
        assertEquals(6, t.searchTree(6));
        assertEquals(7, t.searchTree(7));
        assertNull(t.searchTree(8));
    }

    @Test
    void testUpdate()
    {
        t.insertTree(1, 1);
        t.insertTree(2, 2);
        t.insertTree(3, 3);
        t.insertTree(4, 4);
        t.insertTree(5, 5);
        t.insertTree(6, 6);
        t.insertTree(7, 7);
        t.updateTree(1, 100);
        t.updateTree(2, 200);
        t.updateTree(3, 300);
        t.updateTree(4, 400);
        t.updateTree(5, 500);
        t.updateTree(6, 600);
        t.updateTree(7, 700);
        assertEquals(100, t.searchTree(1));
        assertEquals(200, t.searchTree(2));
        assertEquals(300, t.searchTree(3));
        assertEquals(400, t.searchTree(4));
        assertEquals(500, t.searchTree(5));
        assertEquals(600, t.searchTree(6));
        assertEquals(700, t.searchTree(7));
        assertThrows(NoSuchElementException.class, () -> t.updateTree(8, 800));
    }

    @Test
    void testInsertRepetitiveKey()
    {
        t.insertTree(1, 1);
        t.insertTree(2, 2);
        t.insertTree(3, 3);
        assertThrows(IllegalArgumentException.class, () -> t.insertTree(1, 1) );
        assertThrows(IllegalArgumentException.class, () -> t.insertTree(2, 2) );
        assertThrows(IllegalArgumentException.class, () -> t.insertTree(3, 3) );
    }

    @Test
    void testToArray()
    {
        t.insertTree(1, 1);
        t.insertTree(2, 2);
        t.insertTree(3, 3);
        t.insertTree(4, 4);
        Integer[] array = t.toArray();
        assertEquals(4, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
        assertEquals(4, array[3]);
    }
}