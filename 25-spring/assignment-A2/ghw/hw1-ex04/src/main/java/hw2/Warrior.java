package hw2;
 
public interface Warrior
{
    /**
     * Returns whether the warrior is alive, i.e., whether they still have shields.
     * <ul>
     * <li> Time Complexity: {@code O(1)} </li>
     * </ul>
     * @return true if the warrior is alive, false otherwise.
     */
    boolean alive();

    /**
     * Adds an outer shield with the given power to the warrior.
     * <ul>
     * <li> Time Complexity: {@code O(log S)} </li>
     * <li> This method modifies the warrior, meaning that after the attack, only remaining shields are kept. </li>
     * <li> The new shield is added to the end of the queue. </li>
     * <li> The shields are stored in a queue, so the first shield added is the first one to be removed. </li>
     * </ul>
     * @param newShield the power of the new shield
     * @throws IllegalArgumentException if the new shield is not positive
     */
    void addShield(int newShield);

    /**
     * Applies an attack of the given power to the warrior.
     * <ul>
     * <li> Time Complexity: {@code O(S)} </li>
     * <li> Shields are used by selecting the smallest one capable of repelling the attack. 
     *      If no single shield can repel the attack on its own, they are used successively in 
     *      order of decreasing strength until the attack is neutralized or no shields remain. </li>
     * <li> Returns true if the attack is repelled, false if the warrior is defeated. </li>
     * <li> This method modifies the warrior, meaning that after the attack, only remaining shields are kept. </li>
     * </ul>
     * @param anAttack an attack to apply
     * @return Returns true if the attack is repelled, false if the warrior is defeated.
     */
    public boolean repel(int anAttack);

    /**
     * Applies all attacks in the given strategy, one by one.
     * <ul>
     * <li> Time Complexity: {@code O()} </li>
     * <li> The process stops if an attack cannot be repelled. </li>
     * <li> Returns true if the entire strategy is repelled, false otherwise. </li>
     * <li> This method modifies the warrior, removing destroyed shields. </li>
     * </ul>
     * @param anAttackStrategy a sequence of attacks to apply
     * @return true if the entire strategy is repelled, false otherwise. 
     */
    boolean repel(AttackStrategy anAttackStrategy);

    /**
     * Returns the number of shields the warrior has left.
     * <ul>
     * <li> Time Complexity: {@code O(1)} </li>
     * </ul>
     * @return the number of shields the warrior has
     */
    int shields();

    /**
     * Returns the sum of the power of all the shields the warrior has.
     * <ul>
     * <li> Time Complexity: {@code O(1)} </li>
     * </ul>
     * @return the sum of the power of all the shields the warrior has
     */
    int remainingPower();

    /**
     * Performs a calculation.
     *
     * <p>This method does the following:
     * <ul>
     *   <li>Validates inputs</li>
     *   <li>Performs computation</li>
     *   <li>Returns result</li>
     * </ul>
     *
     * @param x the first operand
     * @param y the second operand
     * @return the sum of {@code x} and {@code y}
     * @throws IllegalArgumentException if {@code x < 0 || y < 0}
     * @see Math#addExact(int, int)
     * @since 1.0
     */
    public int add(int x, int y);
}