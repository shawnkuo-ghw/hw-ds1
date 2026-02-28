package ds1;

import org.junit.jupiter.api.*;
import ds1.analytics.IndexAddressMap;
import static org.junit.jupiter.api.Assertions.*;

public class IndexAddressMapTest {
    IndexAddressMap map;
    
    @BeforeEach
    void init() { map = new IndexAddressMap(); }

    @Test
    void test() {
        map.insert(0, "Addr000");
        map.insert(1, "Addr001");
        map.insert(2, "Addr010");
        // get address by index
        assertEquals("Addr000", map.getAddress(0));
        assertEquals("Addr001", map.getAddress(1));
        assertEquals("Addr010", map.getAddress(2));
        // get index by address
        assertEquals(0, map.getIndex("Addr000"));
        assertEquals(1, map.getIndex("Addr001"));
        assertEquals(2, map.getIndex("Addr010"));
    }
}