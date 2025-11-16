package ex04.classedTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import ex04.classes.interfaces.Warrior;
import ex04.classes.implementations.WarriorImplementation;;

public class WarriorTest {
    
    private Warrior w;

    @BeforeEach
    public void init() {
        w = new WarriorImplementation();
    }

    @Test
    public void repelAttackTest1() {
        int attack = 50;
        w.addShield(50);
        w.addShield(75);
        w.addShield(100);
        assertEquals("[50, 75, 100]", w.toString());
        assertEquals(225, w.remainingPower());
        assertTrue(w.repel(attack)); // warrior is alive
        assertEquals("[75, 100]", w.toString());
        assertEquals(175, w.remainingPower());
    }

    @Test
    public void repelAttackTest2() {
        int attack = 70;
        w.addShield(50);
        w.addShield(75);
        w.addShield(100);
        assertEquals("[50, 75, 100]", w.toString());
        assertEquals(225, w.remainingPower());
        assertTrue(w.repel(attack)); // warrior is alive
        assertEquals("[5, 50, 100]", w.toString());
        assertEquals(155, w.remainingPower());
    } 

    @Test
    public void repelAttackTest3() {
        int attack = 125;
        w.addShield(50);
        w.addShield(75);
        w.addShield(100);
        assertEquals("[50, 75, 100]", w.toString());
        assertEquals(225, w.remainingPower());
        assertTrue(w.repel(attack)); // warrior is alive
        assertEquals("[50, 50]", w.toString());
        assertEquals(100, w.remainingPower());
    } 

    @Test
    public void repelAttackTest4() {
        int attack = 170;
        w.addShield(50);
        w.addShield(75);
        w.addShield(100);
        assertEquals("[50, 75, 100]", w.toString());
        assertEquals(225, w.remainingPower());
        assertTrue(w.repel(attack)); // warrior is alive
        assertEquals("[5, 50]", w.toString());
        assertEquals(55, w.remainingPower());
    }     
    
    @Test
    public void repelAttackTest5() {
        int attack = 400;
        w.addShield(50);
        w.addShield(75);
        w.addShield(100);
        assertEquals("[50, 75, 100]", w.toString());
        assertEquals(225, w.remainingPower());
        assertFalse(w.repel(attack)); // warrior is defeated
        assertEquals(0, w.remainingPower());
    }
}
