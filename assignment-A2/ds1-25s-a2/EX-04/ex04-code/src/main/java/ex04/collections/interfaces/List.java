package ex04.collections.interfaces;

import java.util.NoSuchElementException;

/**
 * The interface of List
 * <p>
 * A list is a collection of elements that supports the following operations:
 * <ul>
 * <li> {@code insertAt} : insert a new element at a given position </li>
 * <li> {@code append} : add a new element to the end of the list </li>
 * <li> {@code prepend} : add a new element to the beginning of the list </li>
 * <li> {@code removeAt} : remove an element at a given position </li>
 * <li> {@code removeFirst} : remove the first element of the list </li>
 * <li> {@code removeLast} : remove the last element of the list </li>
 * <li> {@code swapElem} : swap two elements </li>
 * <li> {@code get} : get an element at a given position </li>
 * <li> {@code setFirst} : set the first element of the list </li>
 * <li> {@code first} : get the first element of the list </li>
 * <li> {@code last} : get the last element of the list </li>
 * <li> {@code size} : get the size of the list </li>
 * <li> {@code empty} : check whether the list is empty </li>
 * <li> {@code toString} : return a string representation of the list </li>
 * </ul>
 */
public interface List<T> {

    /**
     * Insert a new element to the list at position {@code idx}
     * <ul>
     * <li> If idx = 0, {@code newElem} will be inserted at the beginning of list </li>
     * <li> If idx = {@code l.size}, {@code newElem} will be inserted at the end of list </li>
     * </ul> 
     * @param idx the posisiont to insert the new element
     * @param newElem the element to insert
     * @throws IndexOutOfBoundException if {@code at} is out of range
     */
    void insertAt(int idx, T newElem);

    /**
     * Add a new element to the end of the list
     * @param newElem the new element to add
     */
    void append(T newElem);

    /**
     * Add a new element to the beginning of the list
     * @param newElem the new element to add
     */
    void prepend(T newElem);
    
    /**
     * Remove the element at posisiont {@code idx}
     * @param idx the index of element to remove
     * @throws IndexOutOfBoundException if {@code idx} is out of range
     * @throws NoSuchElementException if the list is empty
     */
    void removeAt(int idx);

    /**
     * Remove the first element of the list
     * @throws NoSuchElementException if the list is empty
     */
    void removeFirst();

    /**
     * Remove the last element of the list
     * @throws NoSuchElementException if the list is empty
     */
    void removeLast();

    /**
     * Swap to elements
     * @param lhs the index of the first element to swap
     * @param rhs the index of the second element to swap
     * @throws NoSuchElementException if there are less than two elements in the list
     * @throws IllegalArgumentException if 0 <= {@code lhs} < {@code rhs} < size does not hold
     */
    void swap(int lhs, int rhs);

    /**
     * Get the element at position {@code idx}
     * @param idx the index of the element to get
     * @return the element at position {@code idx}
     * @throws NoSuchElementException if the list is empty
     * @throws IndexOutOfBoundException if {@code idx} is out of range
     */
    T get(int idx);

    /**
     * Get the first element of the list
     * @return the first element of the list
     * @throws NoSuchElementException if the list is empty
     */
    T first();

    /**
     * Set the first element of the list
     * @param newElem the new element to set
     * @throws NoSuchElementException if the list is empty
     */
    void setFirst(T newElem);

    /**
     * Get the last element of the list
     * @return the last element of the list
     * @throws NoSuchElementException if the list is empty
     */
    T last();
    
    /**
     * Get the size of the list
     * @return the size of the list
     */
    int size();

    /**
     * Check whether the list is empty
     * @return true if list is empty, false otherwise
     */
    boolean empty();

    /**
     * Return a string representation of the list
     * @return a string representation of the list
     */
    String toString();
}
