package hw1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AttackStrategyTest {
    // To Do
    AttackStrategy a;

    @BeforeEach
    public void initAttack() {
        a = new AttackStrategyImplementation();
    }

    @Test
    public void Test() {

        assertTrue(a.isEmpty());
        
        // a = [1, 2, 3]
        a.add(1);
        a.add(2);
        a.add(3);

        assertFalse(a.isEmpty());
        assertEquals(1, a.getTopAttack());
        assertEquals(2, a.getTopAttack());
        assertEquals(3, a.getTopAttack());
        assertTrue(a.isEmpty());
    }
}
