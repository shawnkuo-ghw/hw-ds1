package hw1;

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
    public void repelAnUnsuccessfulAttackTest_One() {
        
        // w = [10, 20]
        w.addShield(10);
        w.addShield(20);

        // a = 5
        int anAttack = 5;

        assertEquals(30, w.remainingPower());
        assertEquals(2, w.shields());
        assertTrue(w.repel(anAttack));
        assertEquals(25, w.remainingPower());
        assertEquals(2, w.shields());
        assertTrue(w.alive());    
    }


    @Test
    public void repelAnUnsuccessfulAttackTest_Two() {
        
        // w = [10, 20]
        w.addShield(10);
        w.addShield(20);

        // a = 10
        a.add(10);

        assertEquals(30, w.remainingPower());
        assertEquals(2, w.shields());
        assertTrue(w.repel(a));
        assertEquals(20, w.remainingPower());
        assertEquals(1, w.shields());
        assertTrue(w.alive());    
    }

    @Test
    public void repelAnUnsuccessfulAttackTest_Three() {
        
        // w = [10, 20]
        w.addShield(10);
        w.addShield(20);

        // a = 15
        a.add(15);

        assertEquals(30, w.remainingPower());
        assertEquals(2, w.shields());
        assertTrue(w.repel(a));
        assertEquals(15, w.remainingPower());
        assertEquals(1, w.shields());
        assertTrue(w.alive());    
    }

    @Test
    public void repelAnSuccessfulAttackTest() {
        

    }


}