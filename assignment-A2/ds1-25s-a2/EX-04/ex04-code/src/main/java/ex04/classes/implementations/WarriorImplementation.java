package ex04.classes.implementations;

import ex04.classes.interfaces.AttackStrategy;
import ex04.classes.interfaces.Warrior;
import ex04.collections.interfaces.Heap;
import ex04.collections.interfaces.Stack;
import ex04.collections.implementations.maxHeap;
import ex04.collections.implementations.minHeap;
import ex04.collections.implementations.LinkedListStack;

/**
 * The implementation of interface {@code Warrior}
 * @see ex04.classes.interfaces.Warrior
 */
public class WarriorImplementation implements Warrior {

    private Heap<Integer> shieldsHeap;
    private int totalPower;
    
    public WarriorImplementation() { 
        shieldsHeap = new minHeap<Integer>(); 
        totalPower = 0; 
    }

    @Override
    public boolean alive() { 
        return ! shieldsHeap.empty(); 
    }

    @Override
    public void addShield(int newShield) { 
        shieldsHeap.push(newShield); 
        totalPower += newShield;
    }

    @Override
    public boolean repel(int attack) {
        if ( !(attack > 0) ) {
            throw new IllegalArgumentException("WarriorImplementation.repel(attack): attack is not positive.");
        }
        Stack<Integer> auxStack = new LinkedListStack<Integer>();
        // push all shields smaller than attack to a auxiliary stack
        while ( !shieldsHeap.empty() && attack > shieldsHeap.top() ) { 
            auxStack.push( shieldsHeap.pop() ); 
        }
        // attack is greater than every shield the warrior has
        if ( shieldsHeap.empty() ) {
            while ( !auxStack.empty() && attack > 0 ) {
                if ( attack >= auxStack.top() ) {
                    totalPower -= auxStack.top();
                    attack -= auxStack.pop();
                } else if ( attack < auxStack.top() ) {
                    totalPower -= attack;
                    auxStack.setTop( auxStack.top() - attack );
                    attack = 0;
                }
            }
        // there is a shield greater than attack
        } else {
            totalPower -= attack;
            shieldsHeap.setTop( shieldsHeap.top() - attack );
            if ( shieldsHeap.top() == 0 ) {
                shieldsHeap.pop();
            }
        }
        // pop remaining shileds (if any) into shieldsHeap
        while ( !auxStack.empty() ) {
            shieldsHeap.push( auxStack.pop() );
        }
        return this.alive();
    }

    @Override
    public boolean repel(AttackStrategy attackStrategy) {
        if ( attackStrategy.empty() ) {
            throw new IllegalArgumentException("WarriorImplementation.repel(AttackStrategy): attackStrategy is empty.");
        }
        boolean isAlive = true;
        while ( isAlive && !attackStrategy.empty() ) {
            isAlive = repel( attackStrategy.pop() );
        }
        return isAlive;
    }

    @Override
    public int shields() { 
        return shieldsHeap.size();
    }

    @Override
    public int remainingPower() { 
        return totalPower;
    }
    
    @Override
    public int compareTo(Warrior otheWarrior) { 
        return Integer.compare(this.remainingPower(), otheWarrior.remainingPower());
    }

    public String toString() {
        Heap<Integer> auxHeap = new minHeap<Integer>((minHeap<Integer>) shieldsHeap);
        String strRep = "[";
        while ( !auxHeap.empty() ) {
            if (auxHeap.size() == 1) {
                strRep += auxHeap.pop().toString();
            } else {
                strRep += auxHeap.pop().toString() + ", ";
            }
        }
        strRep += "]";
        return strRep;
    }
}