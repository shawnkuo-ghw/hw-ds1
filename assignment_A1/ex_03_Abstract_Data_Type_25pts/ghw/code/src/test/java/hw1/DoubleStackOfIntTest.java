package hw1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.*;

public class DoubleStackOfIntTest {

    DoubleStackOfInt ds;
    @BeforeEach
    public void initStack() {
        ds = new DoubleStackOfIntOnArray();
    }    

    @Test
    public void pushTest() {

        assertTrue(ds.isEmptyHead());
        assertTrue(ds.isEmptyTail());

        ds.pushHead(1);
        ds.pushTail(11);
        assertEquals(1, ds.topHead());;
        assertEquals(11, ds.topTail());;

        ds.pushHead(2);
        ds.pushTail(22);
        assertEquals(2, ds.topHead());;
        assertEquals(22, ds.topTail());;
        
        ds.pushHead(3);
        ds.pushTail(33);
        assertEquals(3, ds.topHead());;
        assertEquals(33, ds.topTail());;

        ds.pushHead(4);
        ds.pushTail(44);
        ds.pushHead(5);
        ds.pushTail(55);
        assertTrue(ds.isFull());
        assertThrows(RuntimeException.class, () -> ds.pushHead(6));
        assertThrows(RuntimeException.class, () -> ds.pushHead(66));
    }

    @Test
    public void popTest() {

        ds.pushHead(1);
        ds.pushHead(2);
        ds.pushHead(3);
        ds.pushTail(4);
        ds.pushTail(5);
        ds.pushTail(6);
        
        assertEquals(3, ds.popHead());
        assertEquals(2, ds.popHead());
        assertEquals(1, ds.popHead());
        assertEquals(6, ds.popTail());
        assertEquals(5, ds.popTail());
        assertEquals(4, ds.popTail());
        assertTrue(ds.isEmptyHead());
        assertTrue(ds.isEmptyTail());

        assertThrows(RuntimeException.class, () -> ds.popHead());
        assertThrows(RuntimeException.class, () -> ds.popTail());
    }


}
