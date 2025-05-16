package ex04.classes.interfaces;

/**
 * The interface of Attack Strategy
 */
public interface AttackStrategy {
    
    /**
     * Add a new attack with the given power to the strategy.
     * <ul>
     * <li> Time Complexity: {@code O(1)}
     * <li> The new attack is applied after all existing attacks in the strategy. </li>
     * </ul>
     * @param newAttack new attack
     * @throws IllegalArgumentException if {@code newAttack} is not positive
     */
    void add(int newAttack);

    /**
     * Pop next attack in all remaining attacks
     * @return next attack
     * @throws NoSuchArgumentException if Attack Strategy is empty
     */
    int pop();

    /**
     * Retern whether attack strategy is empty
     * @return {@code true} if attack strategy is empty, {@code false} otherwise.
     */
    boolean empty();
}
