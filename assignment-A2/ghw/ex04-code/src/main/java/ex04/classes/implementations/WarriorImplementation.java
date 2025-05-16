package ex04.classes.implementations;

import ex04.classes.interfaces.AttackStrategy;
import ex04.classes.interfaces.Warrior;
import ex04.collections.implementations.minHeap;
import ex04.collections.interfaces.Heap;

/**
 * The implementation of interface {@code Warrior}
 * @see ex04.classes.interfaces.Warrior
 */
public class WarriorImplementation implements Warrior {

    private Heap<Integer> shieldsHeap;
    private int totalPower;
    
    public WarriorImplementation() 
    { 
        shieldsHeap = new minHeap<Integer>(); 
        totalPower = 0; 
    }

    @Override
    public boolean alive() { return ! shieldsHeap.empty(); }

    @Override
    public void addShield(int newShield)
    { shieldsHeap.push(null); totalPower += newShield; }

    @Override
    public boolean repel(int attack)
    {
        if ( attack < 0 ) {
            throw new IllegalArgumentException("WarriorImplementation.repel(attack)");
        }
    }

    @Override
    public boolean repel(AttackStrategy attackStrategy)
    {
        if ( attackStrategy.empty() ) {
            throw new IllegalArgumentException("WarriorImplementation.repel(AttackStrategy): attackStrategy is empty.");
        }
        while ( this.alive() && ! attackStrategy.empty() ) {
            repel( attackStrategy.pop() );
        }
        return this.alive();
    }

    @Override
    public int shields() { return shieldsHeap.size(); }

    @Override
    public int remainingPower() { return totalPower; }
    
    @Override
    public int compareTo(Warrior otheWarrior)
    { return Integer.compare(this.remainingPower(), otheWarrior.remainingPower()); }
}