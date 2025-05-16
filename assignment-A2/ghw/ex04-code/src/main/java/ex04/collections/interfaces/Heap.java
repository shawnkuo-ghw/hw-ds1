package ex04.collections.interfaces;

import java.util.NoSuchElementException;

/**
 * The interface of heap
 * <p>
 * A heap is a collection of elements that supports the following operations:
 * <ul>
 * <li> push: add a new element to the heap </li>
 * <li> pop: remove the top element of the heap and replace it by the last one (if any) </li>
 * <li> setTop: set the value of the top element of the heap </li>
 * <li> top: return the top element of the heap </li>
 * <li> empty: check whether the heap is empty </li>
 * <li> size: return the number of elements in the heap </li>
 * </ul>
 */
public interface Heap<T extends Comparable<T> > {

    /**
     * Add a new element to the heap
     * @param newElem
     */
    void push(T newElem);
    
    /**
     * Remove the top of the heap and replace by the last one (if any)
     * @return the top element of the heap
     * @throws NoSuchElementException if heap is empty
     */ 
    T pop();

    /**
     * Return the top element of the heap
     * @return the top element of the heap
     * @throws NoSuchElementException if heap is empty
     */
    T top();
    
    /**
     * Set the value of the top element of the heap
     * @param newTop the new value of the top element
     * @throws NoSuchElementException if heap is empty
     */
    void setTop(T newTop);

    /**
     * Check whether heap is empty
     * @return true if heap is empty, false otherwise
     */ 
    boolean empty();

    /**
     * Retern the number of elements in heap
     * @return the number of elements in heap
     */
    int size();
}
