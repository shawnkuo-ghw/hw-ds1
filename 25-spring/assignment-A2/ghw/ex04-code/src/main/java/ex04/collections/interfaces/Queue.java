package ex04.collections.interfaces;

import java.util.NoSuchElementException;

/**
 * The interface of queue
 * @param <T> the type of elements in the queue
 * <p>
 * A queue is a collection of elements that supports the following operations:
 * <ul>
 * <li> {@code enqueue} : add a new element to the queue </li>
 * <li> {@code dequeue} : remove and return the “next in line” element of the queue </li>
 * <li> {@code top} : return the “next in line” element in the queue without removing it </li>
 * <li> {@code setTop} : set the value of “next in line” element in the queue </li>
 * <li> {@code size} : return the number of elements in the queue </li>
 * <li> {@code empty} : check whether the queue is empty </li>
 * <li> {@code toString} : return a string representation of the queue </li>
 * </ul>
 */
public interface Queue<T> {

    /**
     * Add a new element to queue
     * @param newElem the new element to add
     */
    void enqueue(T newElem);

    /**
     * Remove the “next in line” element of the queue and return it.
     * @return the “next in line” element of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    T dequeue();

    /**
     * Return the “next in line” element in the queue without removing it.
     * @return the “next in line” element of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    T top();

    /**
     * Set the value of “next in line” element in the queue.
     * @throws NoSuchElementException if the queue is empty
     */
    void setTop(T newElem);

    /**
     * Returns the number of elements in the queue
     * @return the number of elements in the queue
     */
    int size();

    /**
     * Check whether the queue is empty
     * @return true if the queue is empty, false otherwise
     */
    boolean empty();

    /**
     * Returns a string representation of the queue
     * @return a string representation of the queue
     */
    String toString();
}
