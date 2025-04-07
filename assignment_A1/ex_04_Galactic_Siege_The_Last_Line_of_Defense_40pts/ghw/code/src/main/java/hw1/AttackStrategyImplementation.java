package hw1;

public class AttackStrategyImplementation implements AttackStrategy {

    // Class attributes
    // To Do
    Queue attacks_queue;

    // Methods
    public AttackStrategyImplementation() {
        attacks_queue = new ModifiedLinkedListQueue();
    }

    @Override
    public void add(int newAttack) {
        if ( newAttack <= 0 ) {
            throw new RuntimeException("New Attack is not positive.");
        }
        attacks_queue.enqueue(newAttack);
    }

    @Override
    public boolean isEmpty() {
        return attacks_queue.isEmpty();
    }
}