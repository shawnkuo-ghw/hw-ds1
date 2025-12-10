package ds1.util;
import java.util.NoSuchElementException;

/**
 * Interface of sequence
 * @param T the type of data
 */
public interface Sequence<T>
{
    /**
     * Return the i-th element in the sequence
     * <p> Time Complexity: O(N) </p>
     * @param i index
     * @return the i-th element in the sequence
     * @throws NoSuchElementException if the sequence is empty
     * @throws IndexOutOfBoundException if {@code i} is out of bound
     */    
    T at(int i);
    
    /**
     * Return the length of sequence
     * <p> Time Complexity: O(1) </p>
     * @return the lenth of sequence
     */
    int length();
    
    /**
     * insert a new element {@code elem} into the sequence at i-th position
     * <p> Time Complexity: O(N) </p>
     * @param i the position to insert {@code elem}
     * @param elem the element to insert
     * 
     */
    void insertAt(int i, T elem);
        
    /**
     * Insert a new element at the front of sequence
     * <p> Time Complexity: O(1) </p>
     * @param elem the new element to insert
     */
    void insertFront(T elem);

    /**
     * Insert a new element at the rear of sequence
     * <p> Time Complexity: O(1) </p>
     * @param elem the new element to insert
     */
    void insertRear(T elem);

    /**
     * Remove the element at posision {@code i}
     * <p> Time Complexity: O(N) </p>
     * @param i the position of element to remove
     * @throws NoSuchElementException if the sequence is empty
     * @throws IndexOutOfBoundException if {@code i} is out of bound
     */
    void removeAt(int i);

    /**
     * Remove the first element of the sequence
     * <p> Time Complexity: O(1) </p>
     * @throws NoSuchElementException if the sequence is empty
     */
    void removeFront();

    /**
     * Remove the last element of the sequence
     * <p> Time Complexity: O(1) </p>
     * @throws NoSuchElementException if the sequence is empty
     */
    void removeRear();

    /**
     * Insert a new element {@code elem} in sorted order
     * <p> Time Complexity: O(N) </p>
     * @param elem new element to insert
     */
    void insertSorted(T elem);
    
    /**
     * The index of element {@code elem}
     * <p> Time Complexity: O(N) </p>
     * @param elem the element to search index
     * @return index of {@code elem} if it is in the sequence; {@code -1} otherwise
     */
    int indexOf(T elem);
    
    /**
     * Update the element at position {@code i}
     * <p> Time Complexity: O(N) </p>
     * @param i the index of element
     * @param elem new element to update at position {@code i}
     * @throws NoSuchElementException if the list is empty
     * @throws IndexOutOfBoundsException if {@code i} is out of bound
     */
    void updateAt(int i, T elem);
    
    /**
     * Return the list of all elements in the list
     * <p> Time Complexity: O(N) </p>
     * @return the list of elements of type {@code T}
     */
    T[] toArray();
}