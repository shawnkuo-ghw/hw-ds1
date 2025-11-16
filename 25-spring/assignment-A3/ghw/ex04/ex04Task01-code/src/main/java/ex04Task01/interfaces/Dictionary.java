package ex04Task01.interfaces;

public interface Dictionary<K, V> {

    /**
     * Get the value stored with the key k
     * @param key
     * @return the value associated with {@code key} if any; {@code null} otherwise.
     * @throws IllegalArgumentException if key is not of type {@code K}
     */
    V get(K key);
    
    /**
     * Insert the pair ({@code key}, {@code value}) into the dictionary
     * @param key the key
     * @param value the value
     * @return the position of hash table where ({@code key}, {@code value}) is inserted 
     * @throws IllegalStateException if the dictionary is full
     * @throws IllegalArgumentException if {@code key} or {@code value} is not of type {@code K} or {@code V}
     */
    int insert(K key, V value);

    /**
     * Search the index of {@code key} in dictionary where it is stored
     * @param key the key to search
     * @return the index of {@code key} if it is stored in the dictionart; {@code -1} otherwise
     * @throws IllegalArgumentException if {@code key} is not of type {@code K}
     */
    int search(K key);

    /**
     * Return whether {@code key} is stored in the dictionary
     * @param key the key to check
     * @return {@code true} if {@code key} is stored in the dictionary; {@code false} otherwise
     */
    boolean defined(K key);

    /**
     * Return the length of the largest primary cluster in the dictionary
     * @return the largest primary cluster
     */
    int largestPrimaryCluster();

    String toString(int maxLineWidth);
}