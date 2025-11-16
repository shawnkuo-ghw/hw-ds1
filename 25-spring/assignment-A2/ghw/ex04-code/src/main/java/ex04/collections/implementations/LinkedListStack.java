package ex04.collections.implementations;

import java.util.EmptyStackException;
import ex04.collections.interfaces.List;
import ex04.collections.interfaces.Stack;

/**
 * The linked list implementation of interface {@code stack}
 * @see ex04.collections.interfaces.Stack
 */
public class LinkedListStack<T> implements Stack<T> {

    private List<T> ll;

    public LinkedListStack() { 
        ll = new LinkedList<T>();
    }

    @Override
    public void push(T newElem) {
        ll.prepend(newElem); 
    }

    @Override
    public T pop() {
        if ( this.empty() ) {
            throw new EmptyStackException();
        }
        T popedElem = ll.first();
        ll.removeFirst();
        return popedElem;
    }

    @Override
    public T top() {
        if ( this.empty() ) {
            throw new EmptyStackException();
        }
        return ll.first();
    }

    @Override
    public void setTop(T newTop) {
        if ( this.empty() ) {
            throw new EmptyStackException();
        }
        ll.setFirst(newTop);
    }    

    @Override
    public int size() { return ll.size(); }

    @Override
    public boolean empty() { return ll.empty(); }    
}