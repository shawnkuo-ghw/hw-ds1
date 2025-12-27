package ds1;
import ds1.util.StateMPT;
import ds1.util.Sequence;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StateMPTTest {
    
    StateMPT smt;

    @BeforeEach
    void init() { smt = new StateMPT(); }

    @Test
    void testInsertandSearch() {
        smt.insert("1", 10);
        smt.insert("12", 120);
        smt.insert("13", 130);
        smt.insert("137", 1370);
        smt.insert("15", 150);
        smt.insert("1569", 15690);
        smt.insert("58", 580);
        assertEquals(10, smt.search("1"));
        assertEquals(120, smt.search("12"));
        assertEquals(130, smt.search("13"));
        assertEquals(1370, smt.search("137"));
        assertEquals(150, smt.search("15"));
        assertEquals(15690, smt.search("1569"));
        assertEquals(580, smt.search("58"));
        assertEquals(-1, smt.search("000"));
        assertThrows(IllegalArgumentException.class, () -> smt.insert(null, 500));
        assertThrows(IllegalArgumentException.class, () -> smt.insert("", 500));
        assertThrows(IllegalArgumentException.class, () -> smt.insert("123", -10));
        assertThrows(IllegalArgumentException.class, () -> smt.insert("1569", 156900));
    }
    
    @Test
    void testUpdateandSearch() {
        smt.insert("12", 120);
        smt.insert("13", 130);
        smt.insert("137", 1370);
        smt.insert("15", 150);
        smt.update("12", 1200);
        smt.update("13", 1300);
        smt.update("137", 13700);
        smt.update("15", 1500);
        assertEquals(1200, smt.search("12"));
        assertEquals(1300, smt.search("13"));
        assertEquals(13700, smt.search("137"));
        assertEquals(1500, smt.search("15"));
    }
}
