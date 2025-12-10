package ds1;
import ds1.util.Sequence;
import ds1.util.ListoverLinkedList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class ListoverLinkedListTest
{
    Sequence<Integer> ll;

    @BeforeEach
    void init() { ll = new ListoverLinkedList<Integer>(Integer.class); }

    @Test
    void testInsertAt()
    {
        ll.insertAt(0, 1); // ll = [1]
        ll.insertAt(0, 2); // ll = [2, 1]
        ll.insertAt(1, 3); // ll = [2, 3, 1]
        ll.insertAt(3, 4); // ll = [2, 3, 1, 4]
        ll.insertAt(2, 5); // ll = [2, 3, 5, 1, 4]
        ll.insertAt(4, 6); // ll = [2, 3, 5, 1, 6, 4]
        assertEquals(2, ll.at(0));
        assertEquals(3, ll.at(1));
        assertEquals(5, ll.at(2));
        assertEquals(1, ll.at(3));
        assertEquals(6, ll.at(4));
        assertEquals(4, ll.at(5));
        assertEquals(6, ll.length());
    }

    @Test
    void testRemoveAt()
    {
        assertThrows(NoSuchElementException.class, () -> ll.removeAt(0));
        ll.insertAt(0, 1); // ll = [1]
        ll.insertAt(0, 2); // ll = [2, 1]
        ll.insertAt(1, 3); // ll = [2, 3, 1]
        ll.insertAt(3, 4); // ll = [2, 3, 1, 4]
        ll.insertAt(2, 5); // ll = [2, 3, 5, 1, 4]
        ll.insertAt(4, 6); // ll = [2, 3, 5, 1, 6, 4]
        ll.removeAt(0); // ll = [3, 5, 1, 6, 4] 
        ll.removeAt(4); // ll = [3, 5, 1, 6]
        ll.removeAt(2); // ll = [3, 5, 6]
        assertEquals(3, ll.length());
        assertEquals(3, ll.at(0));
        assertEquals(5, ll.at(1));
        assertEquals(6, ll.at(2));
        assertThrows(IndexOutOfBoundsException.class, () -> ll.removeAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> ll.removeAt(3));
    }

    @Test
    void testInsertFront()
    {
        ll.insertFront(3);
        ll.insertFront(2);
        ll.insertFront(1);
        assertEquals(3, ll.length());
        assertEquals(1, ll.at(0));
        assertEquals(2, ll.at(1));
        assertEquals(3, ll.at(2));
    }

    @Test
    void testInsertRear()
    {
        ll.insertRear(1);
        ll.insertRear(2);
        ll.insertRear(3);
        assertEquals(3, ll.length());
        assertEquals(1, ll.at(0));
        assertEquals(2, ll.at(1));
        assertEquals(3, ll.at(2));
    }

    @Test
    void testRemoveFront()
    {
        ll.insertRear(1);
        ll.insertRear(2);
        ll.insertRear(3); // l = [1, 2, 3]
        ll.removeFront();      // l = [2, 3]
        assertEquals(2, ll.length());
        assertEquals(2, ll.at(0));
        assertEquals(3, ll.at(1));
        ll.removeFront();      // l = [3]
        assertEquals(1, ll.length());
        assertEquals(3, ll.at(0));
        ll.removeFront();      // l = []
        assertEquals(0, ll.length());
    }

    @Test
    void testRemoveRear()
    {
        ll.insertRear(1);
        ll.insertRear(2);
        ll.insertRear(3); // l = [1, 2, 3]
        ll.removeRear();      // l = [1, 2]
        assertEquals(2, ll.length());
        assertEquals(1, ll.at(0));
        assertEquals(2, ll.at(1));
        ll.removeRear();      // l = [1]
        assertEquals(1, ll.length());
        assertEquals(1, ll.at(0));
        ll.removeRear();      // l = []
        assertEquals(0, ll.length());
    }

    @Test
    void testUpdateAt()
    {
        assertThrows(NoSuchElementException.class, () -> ll.updateAt(0, 1));
        ll.insertAt(0, 1); // ll = [1]
        ll.insertAt(0, 2); // ll = [2, 1]
        ll.insertAt(1, 3); // ll = [2, 3, 1]
        ll.insertAt(3, 4); // ll = [2, 3, 1, 4]
        ll.insertAt(2, 5); // ll = [2, 3, 5, 1, 4]
        ll.insertAt(4, 6); // ll = [2, 3, 5, 1, 6, 4]
        ll.updateAt(0, 200);
        ll.updateAt(2, 500);
        ll.updateAt(4, 600);
        ll.updateAt(5, 400);
        assertEquals(200, ll.at(0));
        assertEquals(3, ll.at(1));
        assertEquals(500, ll.at(2));
        assertEquals(1, ll.at(3));
        assertEquals(600, ll.at(4));
        assertEquals(400, ll.at(5));
        assertThrows(IndexOutOfBoundsException.class, () -> ll.updateAt(-1, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> ll.updateAt(7, 7));
    }

    @Test
    void testToArray()
    {
        ll.insertRear(1);
        ll.insertRear(2);
        ll.insertRear(3);
        ll.insertRear(4);
        Integer[] array = ll.toArray();
        assertEquals(4, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
        assertEquals(4, array[3]);
    }

    @Test
    void testCopyConstructor()
    {
        ll.insertRear(1);
        ll.insertRear(2);
        ll.insertRear(3);
        ll.insertRear(4);
        Sequence<Integer> ll2 = new ListoverLinkedList<Integer>((ListoverLinkedList<Integer>) ll);
        assertEquals(1, ll2.at(0));
        assertEquals(2, ll2.at(1));
        assertEquals(3, ll2.at(2));
        assertEquals(4, ll2.at(3));
    }
}