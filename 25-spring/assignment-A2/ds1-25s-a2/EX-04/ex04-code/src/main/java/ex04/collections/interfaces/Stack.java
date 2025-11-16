package ex04.collections.interfaces;

import java.util.EmptyStackException;

/**
 * The interface of stack
 * @param <T> the type of elements in the stack
 * <p>
 * A stack is a collection of elements that supports the following operations:
 * <ul>
 * <li> {@code push} : add a new element to the stack </li>
 * <li> {@code pop} : remove the top element of the stack </li>
 * <li> {@code top} : return the top element of the stack without removing it </li>
 * <li> {@code size} : return the number of elements in the stack </li>
 * <li> {@code empty} : check whether the stack is empty </li>
 */
public interface Stack<T> {
    
    /**
     * Pushes an element onto the top of the stack.
     * @param newElem the element to be pushed onto the stack
     */
    void push(T newElem);

    /**
     * Pops the top element from the stack.
     * @return the popped element
     * @throws EmptyStackException if the stack is empty
     */
    T pop();

    /**
     * Return the top element of the stack without removing it.
     * @return the top element
     * @throws EmptyStackException if the stack is empty
     */
    T top();

    /**
     * Set the value of the top element of the stack
     * @throws EmptyStackException if the stack is empty
     */
    void setTop(T newTop);

    /**
     * Returns the number of elements in the stack.
     * @return the size of the stack
     */
    int size();

    /**
     * Checks if the stack is empty.
     * @return true if the stack is empty, false otherwise
     */
    boolean empty();
}
