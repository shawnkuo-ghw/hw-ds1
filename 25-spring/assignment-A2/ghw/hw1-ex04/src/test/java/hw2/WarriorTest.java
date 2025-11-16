package hw2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class WarriorTest {
    // To Do
    Warrior w; 
    AttackStrategy a;

    @BeforeEach
    public void initWorrior() {
        w = new WarriorImplementation();
        a = new AttackStrategyImplementation();
    }

    @Test
    public void unsuccessfulAttackTest_One() {

        // shields = [100, 75, 50]
        w.addShield(100);
        w.addShield(75);
        w.addShield(50);
        
        // attacks = [30, 40, 30, 60]
        a.add(30);
        a.add(40);
        a.add(30);
        a.add(60);

        assertEquals(225, w.remainingPower());
        assertEquals(3, w.shields());
        assertTrue(w.repel(a));
        assertEquals(65, w.remainingPower());
        assertEquals(2, w.shields());
        assertTrue(w.alive());
    }

    @Test
    public void unsuccessfulAttackTest_Two() {
    
        // shields = [50, 70, 80]
        w.addShield(50);
        w.addShield(70);
        w.addShield(80);
    
        // attacks = [100, 20, 20, 20]
        a.add(100);
        a.add(20);
        a.add(20);
        a.add(20);
    
        assertEquals(200, w.remainingPower());
        assertEquals(3, w.shields());
        assertTrue(w.repel(a));
        assertEquals(40, w.remainingPower());
        assertEquals(1, w.shields());
        assertTrue(w.alive());
    }

    @Test
    public void unsuccessfulAttackTest_Three() {
    
        // shields = [10, 50, 100]
        w.addShield(10);
        w.addShield(50);
        w.addShield(100);
    
        // attacks = [60, 99]
        a.add(60);
        a.add(99);
    
        assertEquals(160, w.remainingPower());
        assertEquals(3, w.shields());
        assertTrue(w.repel(a));
        assertEquals(1, w.remainingPower());
        assertEquals(1, w.shields());
        assertTrue(w.alive());
    }

    @Test
    public void successfulAttackTest_One() {

        // shields = [10, 50]
        w.addShield(10);
        w.addShield(50);
        
        // attacks = [40, 25]
        a.add(40);
        a.add(25);

        assertEquals(60, w.remainingPower());
        assertEquals(2, w.shields());
        assertFalse(w.repel(a));
        assertEquals(0, w.remainingPower());
        assertEquals(0, w.shields());
        assertFalse(w.alive());
    }
    
    @Test
    public void successfulAttackTest_Two() {

        // shields = [100, 50, 10]
        w.addShield(100);
        w.addShield(50);
        w.addShield(10);

        // attacks = [100, 20, 30, 20]
        a.add(100);
        a.add(20);
        a.add(30);
        a.add(20);

        assertEquals(160, w.remainingPower());
        assertEquals(3, w.shields());
        assertFalse(w.repel(a));
        assertEquals(0, w.remainingPower());
        assertEquals(0, w.shields());
        assertFalse(w.alive());
    }

    @Test
    public void successfulAttackTest_Three() {

        // shields = [50, 100, 49]
        w.addShield(50);
        w.addShield(100);
        w.addShield(49);

        // attacks = [200]
        a.add(200);

        assertEquals(199, w.remainingPower());
        assertEquals(3, w.shields());
        assertFalse(w.repel(a));
        assertEquals(0, w.remainingPower());
        assertEquals(0, w.shields());
        assertFalse(w.alive());
    }
}