package ds1.util;

/**
 * The interface of AVL Tree
 * <li>@param K the type of key that can be comparable to another key</li>
 * <li>@param V the type of value</li>
 */
public interface AVLTree<K extends Comparable<K>, V>
{
    /**
     * Insert the key-value pair into avl tree
     * <p>Time complexity: O(logN)</p>
     * @param newKey the key
     * @param newValue the value
     * @throws IllegalArgumentException if {@code key} is already in the AVL tree
     */
    void insertTree(K newKey, V newValue);
    
    /**
     * Search the value corresponding to the key {@code key}
     * <p>Time complexity: O(logN)</p>
     * @param key the key to search
     * @return the value corresponding to {@key}; {@code null} if search fails
     */
    V searchTree(K key);
    
    /**
     * Update the value correspoding to {@code key}
     * <p>Time complexity: O(logN)</p>
     * @param key the key of value to update
     * @param newValue new value of {@code key}
     * @throws NoSuchElementException if {@code key} does not exist in AVL tree
     */
    void updateTree(K key, V newValue);

    /**
     * Return the array of data values stored in AVL tree inorderly
     * @return the array of values of type {@cocde V} inorderly
     */
    V[] toArray();
}