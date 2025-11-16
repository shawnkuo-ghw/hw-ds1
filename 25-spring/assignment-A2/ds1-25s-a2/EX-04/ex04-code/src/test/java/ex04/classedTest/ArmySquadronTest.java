package ex04.classedTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import ex04.classes.interfaces.*;
import ex04.classes.implementations.*;;

public class ArmySquadronTest {
    
    ArmySquadron s;

    @BeforeEach
    public void init() {
        s = new ArmySquadronImplementation();
    }

    @Test
    public void Test() {

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

        s.add(w1);
        s.add(w2);
        s.add(w3);

        System.out.println(s.toString());
    }
}