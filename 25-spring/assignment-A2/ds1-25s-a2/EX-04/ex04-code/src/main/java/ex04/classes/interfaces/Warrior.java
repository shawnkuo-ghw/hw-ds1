package ex04.classes.interfaces;

/**
 * The interface of Warrior
 * In the following we use:
 * <ul>
 * <li> {@code W} = Number of warriors in the squadron </li>
 * <li> {@code n} = Length of the input AttackStrategy[] array </li>
 * <li> {@code l} = Length of the longest attack strategy </li>
 * <li> {@code S} = Maximum number of shields per warrior </li>
 * </ul>
 */
public interface Warrior extends Comparable<Warrior> {

    /**
     * Return whether the warrior is alive, i.e., whether they still have shields.
     * <li> Time Complexity: {@code O(1)}
     * @return true if the warrior is alive, false otherwise
     */
    boolean alive();
    
    /**
     * Add an shield with the given power to the warrior.
     * <li> Time Complexity: {@code O(log S)}
     * @param newShield new shield added to the warrior
     * @throws IllegalArgumentException if {@code newShield} is not positive
     */
    void addShield(int newShield);

    /**
     * Applies an attack of the given power to the warrior.
     * <ul>
     * <li> Time Complexity: {@code O(S * log S)}
     * <li> Shields are used by selecting the smallest one capable of repelling the
     *      attack. If no single shield can repel the attack on its own, they are
     *      used successively in order of decreasing strength until the attack is
     *      neutralized or no shields remain. </li>
     * <li> After the attack, only remaining shields are kept.
     * </ul>
     * @param attack the power of applied attack
     * @return {@code true} if the attack is repelled, {@code false} if the warrior is defeated.
     * @throws IllegalArgumentException if attackPower is not positive
     */
    boolean repel(int attack);

    /**
     * Applies all attacks in the given strategy, one by one.
     * <ul>
     * <li> Time Complexity: {@code O( n * (log W + l * S * log S) )}, where 
     * <li> The process stops if an attack cannot be repelled. </li>
     * <li> Destroyed shields are removed.
     * </ul>
     * @param attackStrategy the attack strategy to apply
     * @return true if the entire strategy is repelled, false otherwise
     * @throws IllegalArgumentException if {@code attackStrategy} is null
     */
    boolean repel(AttackStrategy attackStrategy);

    /**
     * Return the number of shields the warrior has left.
     * <li> Time Complexity: {@code O(1)}
     * @return the number of shields the warrior has left
     */
    int shields();

    /**
     * Return the sum of the power of all the shields the warrior has.
     * <li> Time Complexity: {@code O(1)}
     * @return the sum of the power of all the shields the warrior has
     */
    int remainingPower();
}