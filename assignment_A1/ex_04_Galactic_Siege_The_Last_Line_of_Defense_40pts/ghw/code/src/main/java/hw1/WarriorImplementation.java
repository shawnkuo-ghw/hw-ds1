package hw1;

public class WarriorImplementation  implements Warrior {
    
    // Class attributes
    // To Do
    Queue shields_queue;

    // Methods
    public WarriorImplementation() {
        shields_queue = new ModifiedLinkedListQueue();
    }

    /**
     * Returns whether the warrior is alive, i.e., whether they still have shields.
     * Time Complexity: O(1)
     */
    @Override
    public boolean alive() {
        return !shields_queue.isEmpty();
    }

    /**
     * Adds an outer shield with the given power to the warrior.
     * Time Complexity: O(1)
     */
    @Override
    public void addShield(int newShield) {
        if ( newShield <= 0 ) {
            throw new RuntimeException("New shield is not positive.");
        }
        shields_queue.enqueue(newShield);
    }

    @Override
    public boolean repel(AttackStrategy anAttackStrategy) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Returns the number of shields the warrior has left.
     * Time Complexity: O(1)
     */
    @Override
    public int shields() {
        return shields_queue.elemNum();
    }

    /**
     * Returns the sum of the power of all the shields the warrior has.
     * Time Complexity: O(1)
     */
    @Override
    public int remainingPower() {
        return shields_queue.elemSum();
    }

    /**
     * Applies an attack of the given power to the warrior.
     * – Shields are removed until the attack is repelled.
     * – This method modifies the warrior, meaning that after the attack, only remaining shields are kept.
     * Time Complexity: O(S)
     * @param anAttack
     * @return Returns true if the attack is repelled, false if the warrior is defeated.
     */
    private boolean repel(int anAttack) {
        if ( anAttack <= 0 ) {
            throw new RuntimeException("Attack is not positive");
        }
        boolean result = true;

        return result;
    }
}
