package hw2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AttackStrategyTest {
    // To Do
    AttackStrategy attackStrategy;
    AttackStrategyImplementation strategy;

    @BeforeEach
    public void initAttack() {
        attackStrategy = new AttackStrategyImplementation();
        strategy = (AttackStrategyImplementation) attackStrategy;
    }

    @Test
    public void Test() {

        assertTrue(strategy.isEmpty());
        assertThrows(RuntimeException.class, () -> strategy.getTopAttack());
        assertThrows(RuntimeException.class, () -> strategy.setTopAttack(1));
        assertThrows(RuntimeException.class, () -> strategy.popAttack());

        // attackStrategy = [1, 2, 3]
        attackStrategy.add(1);
        assertThrows(RuntimeException.class, () -> attackStrategy.add(-2));
        attackStrategy.add(2);
        assertThrows(RuntimeException.class, () -> attackStrategy.add(-3));
        attackStrategy.add(3);
        
        assertFalse(strategy.isEmpty());
        assertEquals(1, strategy.getTopAttack());
        assertEquals(1, strategy.popAttack());     // q = [2, 3]
        strategy.setTopAttack(20);                // q = [20, 3]
        assertEquals(20, strategy.getTopAttack()); 
        assertEquals(20, strategy.popAttack());    // q = [3]
        assertEquals(3, strategy.getTopAttack());
        assertEquals(3, strategy.popAttack());     // q = []
        assertThrows(RuntimeException.class, () -> strategy.getTopAttack());
        assertThrows(RuntimeException.class, () -> strategy.setTopAttack(1));
        assertThrows(RuntimeException.class, () -> strategy.popAttack());
        assertTrue(strategy.isEmpty());
    }
}
