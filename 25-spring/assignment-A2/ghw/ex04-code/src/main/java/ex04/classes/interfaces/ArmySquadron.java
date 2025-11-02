package ex04.classes.interfaces;

/**
 * The interface of Army Squadron
 */
public interface ArmySquadron {
    
    /**
     * Add new warrior to the army squadron
     * @param w new warrior
     * @throws IllegalStateException if the number of warriors exceed the maximun number allowd (100)
     */
    void add(Warrior w);
    
    /**
     * Return the healthiest warrior in the squadron.
     * <ul>
     * <li> Time Complexity: {@code O(1)}
     * <li> If multiple warriors have the maximum remaining power, it may return any one of them. </li>
     * </ul>
     * @return the healthiest warrior in the squadron
     * @throws NoSuchElemeentException if squadron is empty
     */
    Warrior next();

    /**
     * Return the healthiest warrior in the squadron without removing it.
     * <ul>
     * <li> Time Complexity: {@code O(1)}
     * </ul>
     * @return the healthiest warrior in the squadron
     * @throws NoSuchElemeentException if squadron is empty
     */
    Warrior peek();

    /**
     * Iteratively selects the healthiest warrior (highest remaining shield power)
     * Applies the next unexecuted AttackStrategy from the sequence
     * <ul>
     * <li> Each AttackStrategy in the sequence is applied to at most one warrior </li>
     * <li> If a warrior is defeated mid-strategy, all remaining attacks in that strategy 
     *      are discarded. However, subsequent strategies in the sequence continue to be 
     *      applied until either the sequence completes or no warriors remain in the squadron. </li>
     * </ul>
     * @param attackStrategies the attack strategies to apply
     * @return the count of defeated warriors
     * @throws Ille
     */
    int repel(AttackStrategy[] attackStrategies);

    /**
     * Return the string representation of all information of army squadron
     * @return string rep of army squadron
     */
    String toString();
}