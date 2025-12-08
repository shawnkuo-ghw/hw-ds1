package ds1;
import ds1.util.Sequence;
import ds1.util.ListoverLinkedList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ListoverLinkedListTest
{
    Sequence<Integer> ll;

    @BeforeEach
    void init() { ll = new ListoverLinkedList<Integer>(Integer.class); }

    @Test
    void testInsertRear()
    {
        ll.insertRear(1);
        ll.insertRear(2);
        ll.insertRear(3);
        ll.insertRear(4);
        assertEquals(1, ll.at(0));
        assertEquals(2, ll.at(1));
        assertEquals(3, ll.at(2));
        assertEquals(4, ll.at(3));
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
}