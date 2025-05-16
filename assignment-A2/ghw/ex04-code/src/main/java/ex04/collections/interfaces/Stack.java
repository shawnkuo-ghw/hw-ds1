package ex04.collections.interfaces;

/**
 * The interface of stack
 */
public interface Stack<T> {
    
    void push(T newElem);

    T pop();

    int size();

    boolean empty();
}
