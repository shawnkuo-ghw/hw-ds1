package ex04Task01;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.*;

import ex04Task01.implementations.LinkedList;
import ex04Task01.interfaces.List;

public class LinkedListTest {
    
    private List<Integer> l;
    private static final int SIZE = 3;

    @BeforeEach
    public void init() { l = new LinkedList<Integer>(SIZE); }

    @Test
    public void copyConstructorTest() {
        List<Integer> emptyList = new LinkedList<Integer>();
        assertTrue(emptyList.empty());
        l.append(1);
        l.append(2);
        l.append(3);
        l.append(5);
        // l = [NIL, NIL, NIL, 1, 2, 3, 5]
        List<Integer> newList = new LinkedList<Integer>((LinkedList<Integer>) l);
        assertEquals("[NIL, NIL, NIL, 1, 2, 3, 5]", newList.toString());
    }

    @Test
    public void addTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> l.insertAt(-1, 1));
        l.append(1);
        l.append(2);
        l.append(3);
        // l = [NIL, NIL, NIL, 1, 2, 3]
        assertEquals("[NIL, NIL, NIL, 1, 2, 3]", l.toString());
        l.insertAt(0, 4);
        assertEquals("[4, NIL, NIL, NIL, 1, 2, 3]", l.toString());
        l.insertAt(7, 5);
        assertEquals("[4, NIL, NIL, NIL, 1, 2, 3, 5]", l.toString());        
        l.insertAt(2, 6);
        assertEquals("[4, NIL, 6, NIL, NIL, 1, 2, 3, 5]", l.toString());
    }

    @Test
    public void removeTest() {
        l.append(1);
        l.append(2);
        l.append(3);
        l.append(4);
        l.append(5);
        // l = [NIL, NIL, NIL, 1, 2, 3, 4, 5]
        l.removeFirst();
        assertEquals("[NIL, NIL, 1, 2, 3, 4, 5]", l.toString());
        l.removeLast();
        assertEquals("[NIL, NIL, 1, 2, 3, 4]", l.toString());
        l.removeAt(1);
        assertEquals("[NIL, 1, 2, 3, 4]", l.toString());
        l.removeAt(1);
        assertEquals("[NIL, 2, 3, 4]", l.toString());
        l.removeAt(0);
        assertEquals("[2, 3, 4]", l.toString());
        assertEquals(3, l.size());
    }

    @Test
    public void swapElemTest() {
        l.append(1);
        l.append(2);
        l.append(3);
        l.append(4);
        // l = [NIL, NIL, NIL, 1, 2, 3, 4]
        l.swap(1, 3);
        assertEquals("[NIL, 1, NIL, NIL, 2, 3, 4]", l.toString());
        l.swap(3, 6);
        assertEquals("[NIL, 1, NIL, 4, 2, 3, NIL]", l.toString());
        l.swap(0, 4);
        assertEquals("[2, 1, NIL, 4, NIL, 3, NIL]", l.toString());
        l.swap(1, 2);
        assertEquals("[2, NIL, 1, 4, NIL, 3, NIL]", l.toString());
    }

    @Test
    public void getTest() {
        l.append(1);
        l.append(2);
        l.append(3);
        l.append(4);
        // l = [NIL, NIL, NIL, 1, 2, 3, 4]
        assertEquals(null, l.get(0));
        assertEquals(null, l.get(1));
        assertEquals(null, l.get(2));
        assertEquals(1, l.get(3));
        assertEquals(null, l.first());
        assertEquals(4, l.last());
        assertThrows(IndexOutOfBoundsException.class, () -> l.get(10));
    }

    @Test
    public void setAtTest() {
        l.append(1);
        l.append(2);
        l.append(3);
        l.append(4);
        // l = [NIL, NIL, NIL, 1, 2, 3, 4]
        l.setAt(0, 5);
        // l = [5, NIL, NIL, 1, 2, 3, 4] 
        assertEquals("[5, NIL, NIL, 1, 2, 3, 4]", l.toString());
        l.setAt(6, 6);
        assertEquals("[5, NIL, NIL, 1, 2, 3, 6]", l.toString());
        l.setAt(2, 7);
        assertEquals("[5, NIL, 7, 1, 2, 3, 6]", l.toString());
    }
}