package ex04.collections.implementations;

import ex04.collections.interfaces.List;
import java.util.NoSuchElementException;

/**
 * Linked list implementation of interface {@code List}
 * @see ex04.collections.interfaces.List
 */
public class LinkedList<T> implements List<T> {
    
    private Node<T> head;
    private int size;
    
    public LinkedList() {
        head = null;
        size = 0;
    }

    // copy constructor
    public LinkedList(LinkedList<T> other) {
        size = other.size;
        Node<T> itrOther = other.head;
        Node<T> itrThis  = null;
        if (itrOther != null) {
            this.head = new Node<T>(itrOther.getValue());
            itrThis  = this.head;
            itrOther = itrOther.getNext();
        }
        while (itrOther != null) {
            Node<T> newNode = new Node<T>(itrOther.getValue());
            itrThis.setNext(newNode);
            itrThis  = itrThis.getNext();
            itrOther = itrOther.getNext();
        }
    }

    @Override
    public void insertAt(int idx, T newElem) {
        if ( idx < 0 || idx > this.size ) {
            throw new IndexOutOfBoundsException("insertAt: index out of bound.");
        }
        if ( size == 0 ) {
            head = new Node<T>(newElem);
        } else if ( idx == 0 ) {
            Node<T> temp = head;
            head = new Node<T>(newElem);
            head.setNext(temp);
        } else {
            Node<T> itr = head;
            int count = 0;
            while ( count < idx - 1 ) {
                itr = itr.getNext();
                count ++;
            }
            Node<T> newNode = new Node<T>(newElem);
            newNode.setNext(itr.getNext());
            itr.setNext(newNode);
        }
        size ++;
    }

    @Override
    public void append(T newElem) { this.insertAt(this.size, newElem); }

    @Override
    public void prepend(T newElem) { this.insertAt(0, newElem); }

    @Override
    public void removeAt(int idx) {
        if ( empty() ) {
            throw new NoSuchElementException("LinkedList.removeAt(): the list is empty.");
        } if ( !( 0 <= idx & idx < size() ) ) {
            throw new IndexOutOfBoundsException("LinkedList.removeAt(): index out of bound.");
        }
        if ( idx == 0 ) {
            head = head.getNext();
        } else {
            Node<T> itr = head;
            int count = 0;
            while ( count < idx - 1 ) {
                itr = itr.getNext();
                count ++;
            }
            itr.setNext(itr.getNext().getNext());
        }
        size --;
    }

    @Override
    public void removeFirst() { 
        if ( this.empty() ) {
            throw new NoSuchElementException("LinkedList.removeFirst(): the list is empty");
        }    
        removeAt(0);
    }

    @Override
    public void removeLast() {
        if ( this.empty() ) {
            throw new NoSuchElementException("LinkedList.removeFirst(): the list is empty");
        }
        removeAt(size - 1);
    }
    
    @Override
    public void swap(int lhs, int rhs) {
        if ( size() < 2 ) {
            throw new NoSuchElementException("LinkedList.swap(): there are less than two elements in the list.");
        } else if ( !( 0 <= lhs & lhs < rhs & rhs < size() ) ) {
            throw new IllegalArgumentException("LinkedList.swap(): 0 <= lhs & lhs < rhs & rhs < size() does not hold.");
        }
        T lhsValue = this.get(lhs);
        T rhsValue = this.get(rhs);
        this.removeAt(rhs);
        this.insertAt(rhs, lhsValue);
        this.removeAt(lhs);
        this.insertAt(lhs, rhsValue);
    }

    @Override
    public T get(int idx) {
        if ( this.empty() ) {
            throw new NoSuchElementException("LinkedList.get(): the list is empty.");
        } else if ( !(0 <= idx & idx < size()) ) {
            throw new IndexOutOfBoundsException("LinkedList.get(): index out of bound.");
        }
        int count = 0;
        Node<T> itr = head;
        while ( count < idx ) { 
            itr = itr.getNext();
            count ++;
        }
        return itr.getValue();
    }

    @Override
    public void setFirst(T newElem) {
        if ( this.empty() ) {
            throw new NoSuchElementException("LinkedList.setFirst(): the list is empty");
        }
        this.head.setValue(newElem);
    }

    @Override
    public T first() {
        if ( this.empty() ) {
            throw new NoSuchElementException("LinkedList.first(): the list is empty.");
        }
        return head.getValue();
    }

    @Override
    public T last() {
        if ( empty() ) {
            throw new  NoSuchElementException("LinkedList.last(): the list is empty.");
        }
        Node<T> itr = head;
        while ( itr.hasNext() ) { itr = itr.getNext(); }
        return itr.getValue();
    }

    @Override
    public int size() { return this.size; }
    
    @Override
    public boolean empty() { return this.size == 0; }

    @Override
    public String toString() {
        String strRep = "[";
        Node<T> itr = head;
        while ( itr != null ) {
            if ( itr.hasNext() ) {
                strRep += itr.getValue().toString() + ", ";
            } else {
                strRep += itr.getValue().toString();
            }
            itr = itr.getNext();
        }
        strRep += "]";
        return strRep;
    }
}