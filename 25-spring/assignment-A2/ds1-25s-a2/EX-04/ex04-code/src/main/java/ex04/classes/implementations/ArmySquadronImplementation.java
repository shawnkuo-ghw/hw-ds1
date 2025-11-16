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
        int i = 0;
        while ( !squadron.empty() && i < attackStrategies.length ) {
            Warrior w = this.next();
            if ( w.repel(attackStrategies[i]) ) { this.add(w); }
            i++;
        }
        return squadron.size();
    }    

    @Override
    public String toString() {
        Heap<Warrior> auxHeap = new maxHeap<Warrior>((maxHeap<Warrior>) squadron);
        int count = 1;
        String sepLine   = "+-----+-------------------------------------+-----------------+\n";
        String titleLine = "| No. |             Shields                 | Remaining Power |\n";
        String strRep    = "";
        strRep          += sepLine;
        strRep          += titleLine;
        strRep          += sepLine;
        while ( !auxHeap.empty() ) {
            String noStr           = " " + String.format("%-3s", count) + " ";
            String shieldsStr      = " " + String.format("%-35s", auxHeap.top().toString()) + " ";
            String remainingPowStr = " " + String.format("%-15s", auxHeap.pop().remainingPower()) + " ";
            strRep      += "|" + noStr + "|" + shieldsStr + "|" + remainingPowStr + "|\n";
            strRep      += sepLine;
            count++;
        }
        return strRep;
    }
}