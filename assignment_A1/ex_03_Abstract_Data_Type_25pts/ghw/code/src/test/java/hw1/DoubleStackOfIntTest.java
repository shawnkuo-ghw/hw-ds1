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

        assertTrue(ds.empty_head());
        assertTrue(ds.empty_tail());

        ds.push_head(1);
        ds.push_tail(11);
        assertEquals(1, ds.top_head());;
        assertEquals(11, ds.top_tail());;

        ds.push_head(2);
        ds.push_tail(22);
        assertEquals(2, ds.top_head());;
        assertEquals(22, ds.top_tail());;
        
        ds.push_head(3);
        ds.push_tail(33);
        assertEquals(3, ds.top_head());;
        assertEquals(33, ds.top_tail());;

        ds.push_head(4);
        ds.push_tail(44);
        ds.push_head(5);
        ds.push_tail(55);
        assertTrue(ds.isFull());
        assertThrows(RuntimeException.class, () -> ds.push_head(6));
        assertThrows(RuntimeException.class, () -> ds.push_head(66));
    }

    @Test
    public void popTest() {

        ds.push_head(1);
        ds.push_head(2);
        ds.push_head(3);
        ds.push_tail(4);
        ds.push_tail(5);
        ds.push_tail(6);
        
        assertEquals(3, ds.pop_head());
        assertEquals(2, ds.pop_head());
        assertEquals(1, ds.pop_head());
        assertEquals(6, ds.pop_tail());
        assertEquals(5, ds.pop_tail());
        assertEquals(4, ds.pop_tail());
        assertTrue(ds.empty_head());
        assertTrue(ds.empty_tail());

        assertThrows(RuntimeException.class, () -> ds.pop_head());
        assertThrows(RuntimeException.class, () -> ds.pop_tail());
    }


}
