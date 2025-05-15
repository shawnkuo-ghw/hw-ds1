package ex04.collections.interfaces;

import java.util.NoSuchElementException;

/**
 * The interface of heap
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
