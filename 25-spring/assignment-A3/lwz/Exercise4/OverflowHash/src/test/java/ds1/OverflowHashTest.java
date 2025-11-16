package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OverflowHashTest {
    
    @Test
    public void testValidInsertionAndSearch() {
        OverflowHash table = new OverflowHash(5, 3);
        assertTrue(table.insert(2)); //goes to primary[2]
        assertTrue(table.insert(7)); //goes to overflow[0]
        assertTrue(table.search(2));
        assertTrue(table.search(7));
        assertFalse(table.search(3)); //nver insert
    }

    @Test
    public void testFullOverflowArea() {
        OverflowHash table = new OverflowHash(5, 3);
        assertTrue(table.insert(2));  //goes to primary[2]
        assertTrue(table.insert(7));  //goes to overflow[0]
        assertTrue(table.insert(12)); //goes to overflow[1]
        assertTrue(table.insert(17)); //goes to overflow[2]
        assertFalse(table.insert(22)); //overflowArea full
        assertTrue(table.search(2));
        assertTrue(table.search(7));
        assertTrue(table.search(12));
        assertTrue(table.search(17));
        assertFalse(table.search(22));
    }

     @Test
    public void testDeletion() {
        OverflowHash table = new OverflowHash(5, 3);
        assertTrue(table.insert(2));  //goes to primary[2]
        assertTrue(table.insert(5));  //goes to primary[0]
        assertTrue(table.insert(7));  //goes to overflow[0]
        assertTrue(table.insert(12)); //goes to overflow[1]
        assertTrue(table.insert(17)); //goes to overflow[2]

        assertTrue(table.delete(2));
        assertFalse(table.search(2));

        assertTrue(table.delete(12));
        assertFalse(table.search(12));

        assertFalse(table.delete(3)); //never insert
    }
}