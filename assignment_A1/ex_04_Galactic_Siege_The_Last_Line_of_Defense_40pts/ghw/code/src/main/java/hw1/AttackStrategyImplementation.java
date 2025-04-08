package hw1;

public class AttackStrategyImplementation implements AttackStrategy {

    // Class attributes
    // To Do
    public Queue attacksQueue;

    // Methods
    public AttackStrategyImplementation() {
        attacksQueue = new ModifiedLinkedListQueue();
    }

    @Override
    public void add(int newAttack) {
        if ( newAttack <= 0 ) {
            throw new RuntimeException("New Attack is not positive.");
        }
        attacksQueue.enqueue(newAttack);
    }

    @Override
    public boolean isEmpty() {
        return attacksQueue.isEmpty();
    }

    @Override
    public void setTopAttack(int newAttack) {
        if ( isEmpty() ) {
            throw new RuntimeException("Attack strategy is empty.");
        }
        attacksQueue.setFront(newAttack);
    }

    @Override
    public int getTopAttack() {
        if ( isEmpty() ) {
            throw new RuntimeException("Attack strategy is empty.");
        }
        return attacksQueue.front();
    }
    

    @Override
    public int popAttack() {
        if ( isEmpty() ) {
            throw new RuntimeException("Attack strategy is empty.");
        }
        return attacksQueue.dequeue();
    }

    public String toString() {
        String result = "a: ";
        result += attacksQueue.toString();
        return result;
    }
}