package ex04.collectionsTest;

import ex04.collections.implementations.LinkedList;
import ex04.collections.interfaces.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class LinkedListTest
{
    private List<Integer> l;

    @BeforeEach
    public void init() { l = new LinkedList<Integer>(); }

    @Test
    public void addTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> l.insertAt(-1, 1));
        l.append(1);
        l.append(2);
        l.append(3);
        // l = [1, 2, 3]
        assertEquals("[1, 2, 3]", l.toString());
        l.insertAt(0, 4);
        assertEquals("[4, 1, 2, 3]", l.toString());
        l.append(5);
        assertEquals("[4, 1, 2, 3, 5]", l.toString());
    }

    @Test
    public void removeTest() {
        l.append(1);
        l.append(2);
        l.append(3);
        l.append(4);
        l.append(5);
        // l = [1, 2, 3, 4, 5]
        l.removeFirst();
        assertEquals("[2, 3, 4, 5]", l.toString());
        l.removeLast();
        assertEquals("[2, 3, 4]", l.toString());
        l.removeAt(1);
        assertEquals("[2, 4]", l.toString());
        l.removeAt(1);
        assertEquals("[2]", l.toString());
        l.removeAt(0);
        assertEquals("[]", l.toString());
        assertThrows(NoSuchElementException.class, () -> l.removeAt(0));
    }
    
    @Test
    public void swapElemTest() {
        l.append(1);
        assertThrows(NoSuchElementException.class, () -> l.swap(0, 1));
        l.append(2);
        l.append(3);
        l.append(4);
        // l = [1, 2, 3, 4]
        l.swap(1, 3);
        assertEquals("[1, 4, 3, 2]", l.toString());
        l.swap(0, 1);
        assertEquals("[4, 1, 3, 2]", l.toString());
        l.swap(0, 2);
        assertEquals("[3, 1, 4, 2]", l.toString());
        l.swap(0, 3);
        assertEquals("[2, 1, 4, 3]", l.toString());
    }

    @Test
    public void getTest() {
        assertThrows(NoSuchElementException.class, () -> l.get(0));
        l.append(1);
        l.append(2);
        l.append(3);
        l.append(4);
        // l = [1, 2, 3, 4]
        assertEquals(1, l.get(0));
        assertEquals(2, l.get(1));
        assertEquals(3, l.get(2));
        assertEquals(4, l.get(3));
        assertEquals(1, l.first());
        assertEquals(4, l.last());
        assertThrows(IndexOutOfBoundsException.class, () -> l.get(10));
    }
}