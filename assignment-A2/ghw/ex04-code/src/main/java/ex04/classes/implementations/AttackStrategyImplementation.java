package ex04.classes.implementations;

import ex04.classes.interfaces.AttackStrategy;
import ex04.collections.interfaces.Queue;
import ex04.collections.implementations.LinkedListQueue;;

public class AttackStrategyImplementation implements AttackStrategy {

    private Queue<Integer> attacks;

    public AttackStrategyImplementation() { attacks = new LinkedListQueue<Integer>(); }

    @Override
    public void add(int newAttack)
    {
        if ( !(newAttack > 0) ) {
            throw new IllegalArgumentException("AttackStrategyImplementation.add(): new attack is not positive");
        }
        attacks.enqueue(newAttack);
    }

    @Override
    public int pop()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

    @Override
    public boolean empty() { return attacks.empty(); }

}
