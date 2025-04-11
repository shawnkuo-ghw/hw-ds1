package hw1;

public class WarriorImplementation  implements Warrior {
    
    // Class attributes
    private Queue shieldsQueue;

    // Methods
    public WarriorImplementation() {
        shieldsQueue = new ModifiedLinkedListQueue();
    }

    /**
     * Returns whether the warrior is alive, i.e., whether they still have shields.
     * Time Complexity: O(1)
     */
    @Override
    public boolean alive() {
        return !shieldsQueue.isEmpty();
    }

    /**
     * Adds an outer shield with the given power to the warrior.
     * Time Complexity: O(1)
     */
    @Override
    public void addShield(int newShield) {
        if ( newShield <= 0 ) {
            throw new RuntimeException("addShield(): New shield is not positive.");
        }
        shieldsQueue.enqueue(newShield);
    }

    /**
     * Applies all attacks in the given strategy, one by one.
     * Time Complexity: O(S + A)
     * – The process stops if an attack cannot be repelled.
     * – Returns true if the entire strategy is repelled, false otherwise.
     * – This method modifies the warrior, removing destroyed shields.
     * @param anAttackStrategy a sequence of attacks to apply
     * @return true if the entire strategy is repelled, false otherwise. 
     */
    @Override
    public boolean repel(AttackStrategy anAttackStrategy) {
        AttackStrategyImplementation strategy = (AttackStrategyImplementation) anAttackStrategy;
        if ( strategy.isEmpty() ) {
            throw new RuntimeException("repel(): Attack Strategy is empty.");
        }
        while ( alive() && !strategy.isEmpty() ) {
            repel( strategy.popAttack() );
        }
        return alive();
    }

    /**
     * Returns the number of shields the warrior has left.
     * Time Complexity: O(1)
     */
    @Override
    public int shields() {
        return shieldsQueue.elemNum();
    }

    /**
     * Returns the sum of the power of all the shields the warrior has.
     * Time Complexity: O(1)
     */
    @Override
    public int remainingPower() {
        return shieldsQueue.elemSum();
    }

    /**
     * Applies an attack of the given power to the warrior.
     * Time Complexity: O(S)
     * – Shields are removed until the attack is repelled.
     * - Returns true if the attack is repelled, false if the warrior is defeated.
     * – This method modifies the warrior, meaning that after the attack, 
     *   only remaining shields are kept.
     * @param anAttack an attack to apply
     * @return Returns true if the attack is repelled, false if the warrior is defeated.
     */
    private boolean repel(int anAttack) {
        if ( anAttack <= 0 ) {
            throw new RuntimeException("repel(): Attack is not positive");
        }

        while ( !shieldsQueue.isEmpty() && anAttack > 0 ) {

            int topShield = shieldsQueue.front();

            if ( anAttack > topShield ) {
                anAttack -= topShield;
                shieldsQueue.dequeue();
            } else if ( topShield > anAttack ) {
                shieldsQueue.setFront(topShield - anAttack);
                anAttack = 0;
            } else {
                shieldsQueue.dequeue();
                anAttack = 0;
            }    
        }
        
        return alive();
    }

    public String toString() {
        String result = "w: ";
        result += shieldsQueue.toString();
        return result;
    }

    private String allInfo(AttackStrategy anAttackStrategy) {
        String allInfo = "";
        allInfo += anAttackStrategy.toString() + "\n";
        allInfo += toString() + "\n";
        allInfo += "shields: " + shields() + "\n";
        allInfo += "remainning power: " + remainingPower() + "\n\n";
        return allInfo;
    }
}
