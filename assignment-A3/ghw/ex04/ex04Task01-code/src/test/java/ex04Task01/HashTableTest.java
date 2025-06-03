package ex04Task01;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import ex04Task01.implementations.HashFunctions;
import ex04Task01.implementations.HashTable;
import ex04Task01.implementations.HashFunctions.*;
import ex04Task01.interfaces.Dictionary;

public class HashTableTest {
    
    private final int SIZE = 6;
    private Dictionary<Integer, String> ht;

    @BeforeEach
    public void init() {
        ht = new HashTable<Integer, String>(SIZE, Integer.class,String.class, HashFunctions.hash1);
    }

    @Test
    public void insertTest() {
        int maxLineWidth = 40;
        System.out.println(ht.toString(maxLineWidth));
        ht.insert(1, "hello");
        System.out.println(ht.toString(maxLineWidth));
        ht.insert(2, "world");
        System.out.println(ht.toString(maxLineWidth));
        ht.insert(3, "gtiit");
        System.out.println(ht.toString(maxLineWidth));
        ht.insert(4, "data");
        System.out.println(ht.toString(maxLineWidth));
        ht.insert(5, "structures");
        System.out.println(ht.toString(maxLineWidth));
        ht.insert(6, "difficult");
        System.out.println(ht.toString(maxLineWidth));
        assertThrows(IllegalStateException.class, () -> ht.insert(7, "another"));
    }

    @Test
    public void getTest() {
        int maxLineWidth = 40;
        ht.insert(1, "hello");
        ht.insert(2, "world");
        ht.insert(3, "gtiit");
        ht.insert(4, "data");
        ht.insert(5, "structures");
        ht.insert(6, "difficult");
        System.out.println(ht.toString(maxLineWidth));
        assertEquals("hello", ht.get(1));
        assertEquals("world", ht.get(2));
        assertEquals("gtiit", ht.get(3));
        assertEquals("data", ht.get(4));
        assertEquals("structures", ht.get(5));
        assertEquals("difficult", ht.get(6));
        assertEquals(null, ht.get(10));
    }

    @Test
    public void searchTest() {
        int maxLineWidth = 40;
        ht.insert(1, "hello");
        ht.insert(2, "world");
        ht.insert(3, "gtiit");
        ht.insert(4, "data");
        ht.insert(5, "structures");
        ht.insert(6, "difficult");
        System.out.println(ht.toString(maxLineWidth));
        /*
         * +----------------------------------------+
         * |               Hash Table               |
         * +----------------------------------------+
         * |     ( key: 5, value: structures )      |
         * +----------------------------------------+
         * |        ( key: 2, value: world )        |
         * +----------------------------------------+
         * |        ( key: 4, value: data )         |
         * +----------------------------------------+
         * |        ( key: 1, value: hello )        |
         * +----------------------------------------+
         * |      ( key: 6, value: difficult )      |
         * +----------------------------------------+
         * |        ( key: 3, value: gtiit )        |
         * +----------------------------------------+
         */
        assertEquals(3, ht.search(1));
        assertEquals(1, ht.search(2));
        assertEquals(5, ht.search(3));
        assertEquals(2, ht.search(4));
        assertEquals(0, ht.search(5));
        assertEquals(4, ht.search(6));
        assertEquals(-1, ht.search(7));
        assertEquals(-1, ht.search(8));
        assertEquals(-1, ht.search(9));
        assertEquals(-1, ht.search(10));
    }
}
