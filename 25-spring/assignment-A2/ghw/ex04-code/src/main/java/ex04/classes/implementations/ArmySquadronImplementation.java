package ex04.classes.implementations;

import java.util.NoSuchElementException;
import ex04.classes.interfaces.ArmySquadron;
import ex04.classes.interfaces.AttackStrategy;
import ex04.classes.interfaces.Warrior;
import ex04.collections.implementations.maxHeap;
import ex04.collections.interfaces.Heap;

public class ArmySquadronImplementation implements ArmySquadron {

    private static final int MAX_WARRIORS_NUM = 100;

    Heap<Warrior> squadron;

    public ArmySquadronImplementation() {
        squadron = new maxHeap<Warrior>();
    }

    @Override
    public void add(Warrior w) {
        if ( squadron.size() == MAX_WARRIORS_NUM ) {
            throw new IllegalStateException("ArmySquadronImplementaton.add(): the number of warriors exceed the maximun number allowd.");
        }
        squadron.push(w);
    }

    @Override
    public Warrior next() {
        if ( squadron.empty() ) {
            throw new NoSuchElementException("ArmySquadronImplementaton.next(): squadron is empty.");
        }
        return squadron.pop();
    }
    
    @Override
    public Warrior peek() {
        if ( squadron.empty() ) {
            throw new NoSuchElementException("ArmySquadronImplementaton.pop(): squadron is empty.");
        }
        return squadron.top();
    }

    @Override
    public int repel(AttackStrategy[] attackStrategies) {
        if ( attackStrategies.length == 0 ) {
            throw new IllegalArgumentException("ArmySquadronImplementation.repel(AttackStratyge[]): attackStrategies is empty.");
        }
        int i = 0;
        int warriorsNumber = squadron.size();
        while ( !squadron.empty() && i < attackStrategies.length ) {
            // show info
            String roundStr = "Round: " + String.format("%2s", i + 1);
            System.out.println("\n+---------------------- " + roundStr + " ----------------------------+\n");
            System.out.println(printAttackStrategies(attackStrategies));
            System.out.println(this.toString());
            System.out.println("next attack:  " + attackStrategies[i].toString());
            Warrior w = this.next(); // get the next warrior
            System.out.println("next warrior: " + w.toString());
            // apply attack
            if ( w.repel(attackStrategies[i]) ) { 
                System.out.println("attack is repeled!");
                System.out.println("warrior after attack: " + w.toString());
                this.add(w);
            } else {
                System.out.println("warrior is defeated!");    
            }
            i++;
        }
        return warriorsNumber - squadron.size();
    } 

    private String printAttackStrategies(AttackStrategy[] attackStrategies) {
        String strRep = "";
        int count = 0;
        // basic components
        String edgeLine            = "+-------------------------------------------------------------+\n";
        String tableTitle          = "|                     Attack Strategies                       |\n";
        String sepLine             = "+-----+-------------------------------------------------------+\n";
        String titleLine           = "| No. |                    Attack Strategy                    |\n";
        // construct table
        strRep                    += edgeLine;
        strRep                    += tableTitle;
        strRep                    += edgeLine;
        strRep                    += titleLine;
        while ( count < attackStrategies.length ) {
            strRep                += sepLine;
            String noStr           = " " + String.format("%2s", count + 1) + "  ";
            String attacksStr      = " " + String.format("%-53s", attackStrategies[count].toString()) + " ";
            strRep                += "|" + noStr + "|" + attacksStr + "|\n";
            count++;
        }
        strRep                    += edgeLine;
        return strRep;
    }

    @Override
    public String toString() {
        String strRep = "";
        Heap<Warrior> auxHeap = new maxHeap<Warrior>((maxHeap<Warrior>) squadron);
        int count = 1;
        // basic components
        String edgeLine            = "+-------------------------------------------------------------+\n";
        String tableTitle          = "|                       Army Squadron                         |\n";
        String sepLine             = "+-----+-------------------------------------+-----------------+\n";
        String titleLine           = "| No. |             Shields                 | Remaining Power |\n";
        // constuct table
        strRep                    += edgeLine;
        strRep                    += tableTitle;
        strRep                    += edgeLine;
        strRep                    += titleLine;
        while ( !auxHeap.empty() ) {
            strRep                += sepLine;
            String noStr           = " " + String.format("%2s", count) + "  ";
            String shieldsStr      = " " + String.format("%-35s", auxHeap.top().toString()) + " ";
            String remainingPowStr = " " + String.format("%-15s", auxHeap.pop().remainingPower()) + " ";
            strRep                += "|" + noStr + "|" + shieldsStr + "|" + remainingPowStr + "|\n";
            count++;
        }
        strRep                    += edgeLine;
        return strRep;
    }
}