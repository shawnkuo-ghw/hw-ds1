package ex04.classedTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import ex04.classes.interfaces.*;
import ex04.classes.implementations.*;;

public class ArmySquadronTest {
    
    ArmySquadron squadron;

    @BeforeEach
    public void init() {
        squadron = new ArmySquadronImplementation();
    }

    @Test
    public void Test1() {

        System.out.println("+--------------------------- TEST ----------------------------+");

        // attack strategies
        AttackStrategy a1 = new AttackStrategyImplementation();
        a1.add(30);
        a1.add(50);
        a1.add(70);
        AttackStrategy a2 = new AttackStrategyImplementation();
        a2.add(40);
        a2.add(60);
        a2.add(80);
        AttackStrategy a3 = new AttackStrategyImplementation();
        a3.add(50);
        a3.add(100);
        a3.add(120);
        AttackStrategy[] strategies = {a1, a2, a3};

        // warriors
        Warrior w1 = new WarriorImplementation();
        w1.addShield(90);
        w1.addShield(80);
        w1.addShield(30);
        Warrior w2 = new WarriorImplementation();
        w2.addShield(75);
        w2.addShield(100);
        Warrior w3 = new WarriorImplementation();
        w3.addShield(15);
        w3.addShield(50);
        // army squadron
        squadron.add(w1);
        squadron.add(w2);
        squadron.add(w3);

        // repel attack strategies
        int defeatedWarriors =  squadron.repel(strategies);
        System.out.println("\n+--------------------------- RESULT --------------------------+\n");
        System.out.println(squadron.toString());
        System.out.println("Number of defeated warriors: " + defeatedWarriors + "\n");
        System.out.println("+--------------------------- TEST END ------------------------+\n");
        assertEquals(2, defeatedWarriors);
    }

    @Test
    public void Test2() {

        System.out.println("+--------------------------- TEST ----------------------------+");

        // attack strategies
        AttackStrategy a1 = new AttackStrategyImplementation();
        a1.add(30);
        a1.add(50);
        a1.add(70);
        AttackStrategy a2 = new AttackStrategyImplementation();
        a2.add(40);
        a2.add(50);
        a2.add(80);
        AttackStrategy a3 = new AttackStrategyImplementation();
        a3.add(50);
        a3.add(90);
        a3.add(120);
        AttackStrategy[] strategies = {a1, a2, a3};

        // warriors
        Warrior w1 = new WarriorImplementation();
        w1.addShield(60);
        w1.addShield(30);
        Warrior w2 = new WarriorImplementation();
        w2.addShield(75);
        w2.addShield(50);
        Warrior w3 = new WarriorImplementation();
        w3.addShield(15);
        w3.addShield(50);
        // army squadron
        squadron.add(w1);
        squadron.add(w2);
        squadron.add(w3);

        // repel attack strategies
        int defeatedWarriors =  squadron.repel(strategies);
        System.out.println("\n+--------------------------- RESULT --------------------------+\n");
        System.out.println(squadron.toString());
        System.out.println("Number of defeated warriors: " + defeatedWarriors + "\n");
        System.out.println("+--------------------------- TEST END ------------------------+\n");
        assertEquals(3, defeatedWarriors);
    }

}