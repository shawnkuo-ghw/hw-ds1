package hw1;

public class AttackStrategyImplementation implements AttackStrategy {

    // Class attributes
    private Queue attacksQueue;

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

    public boolean isEmpty() {
        return attacksQueue.isEmpty();
    }

    public void setTopAttack(int newAttack) {
        if ( isEmpty() ) {
            throw new RuntimeException("Attack strategy is empty.");
        }
        attacksQueue.setFront(newAttack);
    }

    /**
     * Consult the top attack without poping it.
     * @return top attack
     */
    public int getTopAttack() {
        if ( isEmpty() ) {
            throw new RuntimeException("Attack strategy is empty.");
        }
        return attacksQueue.front();
    }
    

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