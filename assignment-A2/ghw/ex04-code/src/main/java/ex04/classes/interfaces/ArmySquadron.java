package ex04.classes.interfaces;

import ex04.classes.interfaces.Warrior;

/**
 * The interface of Army Squadron
 */
public interface ArmySquadron {
    
    /**
     * Add new warrior to the army squadron
     * @param w new warrior
     * @throws IllegalStateException if the number of warriors exceed the maximun number (100)
     */
    void addWarrior(Warrior w);

    /**
     * 
     * @return
     * @throws
     */
    Warrior popWarrior();

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
     * Iteratively selects the healthiest warrior (highest remaining shield power)
     * Applies the next unexecuted AttackStrategy from the sequence
     * <ul>
     * <li> Each AttackStrategy in the sequence is applied to at most one warrior </li>
     * <li> If a warrior is defeated mid-strategy, all remaining attacks in that strategy 
     *      are discarded. However, subsequent strategies in the sequence continue to be 
     *      applied until either the sequence completes or no warriors remain in the squadron. </li>
     * </ul>
     * @return the count of defeated warriors
     */
    int repel(AttackStrategy[] attackStrategies);
}