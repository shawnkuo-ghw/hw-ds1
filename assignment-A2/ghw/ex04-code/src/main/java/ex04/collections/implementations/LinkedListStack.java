package ex04.collections.implementations;

import java.util.NoSuchElementException;

import ex04.collections.interfaces.Stack;

/**
 * The linked list implementation of interface {@code stack}
 */
public class LinkedListStack<T> implements Stack<T> {

    private LinkedList<T> ll;

    public LinkedListStack() { ll = new LinkedList<T>(); }

    @Override
    public void push(T newElem) { ll.prepend(newElem); }

    @Override
    public T pop()
    {
        if ( this.empty() ) {
            throw new NoSuchElementException("LinkedListStack.pop(): stack is empty");
        }
        T popedElem = ll.first();
        ll.removeFirst();
        return popedElem;
    }

    @Override
    public int size() { return ll.size(); }

    @Override
    public boolean empty() { return ll.empty(); }
    
}