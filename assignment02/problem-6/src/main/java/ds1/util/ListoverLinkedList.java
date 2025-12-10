package ds1.util;
import java.lang.reflect.*;
import java.util.NoSuchElementException;

public class ListoverLinkedList<T> implements Sequence<T>
{    
    class Node<V> {
        V data;
        Node<V> next, prev;
        Node(V newData) {
            next = null;
            prev = null;
            data = newData;
        }
    }
    
    /* ============================= Fields ================================= */

    private final Class<T> type;
    private int length;
    Node<T> head, tail;

    /* =========================== Constructor ============================== */

    public ListoverLinkedList(Class<T> newType) {
        type = newType;
        head = null;
        tail = null;
        length = 0;
    }

    // copy constructor
    public ListoverLinkedList(ListoverLinkedList<T> o) {
        if ( o == null ) throw new IllegalArgumentException(
            "ListoverLinkedList(o): param other is null"
        );
        ListoverLinkedList<T> other = (ListoverLinkedList<T>) o;
        type = other.type;
        head = null;
        tail = null;
        if ( o.length > 0 ) 
            for ( int i = 0; i < o.length; i++ ) insertRear(other.at(i));
    }

    /* ============================ Getters ================================= */

    // O(1)
    public int length() { return length; }

    // O(N)
    public T at(int i) {
        if ( head == null && tail == null )
            throw new NoSuchElementException(
                "ListoverLinkedList.at(): list is empty."
            );
        if ( !(0 <= i && i < length) )
            throw new IndexOutOfBoundsException(
                "ListoverLinkedList.at(): index out of bound."
            );
        Node<T> curr = head;
        for (int j = 0; j < i; j++) curr = curr.next;
        return curr.data;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> curr = head;
        while (curr != null) {
            sb.append(curr.data.toString());
            curr = curr.next;
        }
        return sb.toString();
    }

    // O(N)
    @Override
    public int indexOf(T elem) {
        Node<T> curr = head;
        int index = 0;
        while (curr != null) {
            if (curr.data.equals(elem)) {
                return index;
            }
            curr = curr.next;
            index++;
        }
        return -1; // not found
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] arr = (T[]) Array.newInstance(type, length);
        Node<T> curr = head;
        int index = 0;
        while ( curr != null ) {
            arr[index] = curr.data;
            curr = curr.next;
            index++;
        }
        return arr;
    }

    /* =========================== Modifiers ===============================  */

    // O(N)
    @Override
    public void updateAt(int i, T elem) {
        if ( head == null && tail == null )
            throw new NoSuchElementException(
                "ListoverLinkedList.updateAt(): list is empty."
            );
        if ( !(0 <= i && i < length) )
            throw new IndexOutOfBoundsException(
                "ListoverLinkedList.updateAt(): index out of bound."
            );
        Node<T> curr = head;
        for (int j = 0; j < i; j++) curr = curr.next;
        curr.data = elem;
    }

    // O(N)
    @Override
    public void insertAt(int i, T elem) {
        if ( !(0 <= i && i <= length) )
            throw new IndexOutOfBoundsException(
                "ListoverLinkedList.insertAt(): index out of bound." 
            );
        if ( head == null && tail == null ) {
            Node<T> newNode = new Node<T>(elem);
            head = newNode;
            tail = newNode;
            length++;
        } else if (i == 0) {
            insertFront(elem);
        } else if (i == length) {
            insertRear(elem);
        } else {
            Node<T> newNode = new Node<T>(elem);
            Node<T> prev = findPrev(i);
            newNode.next = prev.next;
            newNode.prev = prev;
            prev.next.prev = newNode;
            prev.next = newNode;
            length++;
        }
        if ( !repOk() )
            throw new IllegalStateException(
                "RI of ListoverLinkedList is not satisfied."
            );
    }

    // O(1)
    @Override
    public void insertFront(T elem) {
        Node<T> newNode = new Node<T>(elem);
        if ( head == null && tail == null ) {
            head = newNode;
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        length++;
        if ( !repOk() )
            throw new IllegalStateException(
                "RI of ListoverLinkedList is not satisfied."
            );
    }

    // O(1)
    @Override
    public void insertRear(T elem) {
        Node<T> newNode = new Node<T>(elem);
        if ( head == null && tail == null ) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
        if ( !repOk() )
            throw new IllegalStateException(
                "RI of ListoverLinkedList is not satisfied."
            );
    }

    // insert elem in sorted order
    // O(N)
    @Override
    public void insertSorted(T elem) {
        Node<T> newNode = new Node<T>(elem);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> prev = findPrev(elem);
            if (prev == null) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else {
                newNode.prev = prev;
                newNode.next = prev.next;
                prev.next.prev = newNode;
                prev.next = newNode;
            }
        }
        length++;
        if ( !repOk() )
            throw new IllegalStateException(
                "RI of ListoverLinkedList is not satisfied."
            );
    }

    // O(N)
    @Override
    public void removeAt(int i) {
        if ( head == null && tail == null )
            throw new NoSuchElementException(
            "ListoverLinkedList.removeAt(): list is empty."
            );
        if ( !(0<= i && i < length) )
            throw new IndexOutOfBoundsException(
            "ListoverLinkedList.removeAt(): index out out bound."
            );
        if (i == 0) {
            removeFront();
        } else if (i == length-1) { 
            removeRear();
        } else {
            Node<T> prev = findPrev(i);
            prev.next = prev.next.next;
            prev.next.prev = prev;
            length--;
        }
        if ( !repOk() )
            throw new IllegalStateException(
                "RI of ListoverLinkedList is not satisfied."
            );
    }

    // O(1)
    @Override
    public void removeFront() {
        if (head == null && null == null) {
            throw new NoSuchElementException(
                "ListoverLinkedList.removeFront(): list if empty."
            );
        } else if ( head == tail ) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        length--;
        if ( !repOk() )
            throw new IllegalStateException(
                "RI of ListoverLinkedList is not satisfied."
            );
    }

    @Override
    public void removeRear() {
        if ( head == null && tail == null ) {
            throw new NoSuchElementException(
                "ListoverLinkedList.removeFront(): list if empty."
            );
        } else if ( head == tail ) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        length--;
        if ( !repOk() )
            throw new IllegalStateException(
                "RI of ListoverLinkedList is not satisfied."
            );
    }

    /* ========================= Private Utilities ========================== */

    // O(N)
    private Node<T> findPrev(int i) {
        Node<T> curr = head;
        for (int j = 0; j < i-1; j++) {
            curr = curr.next;
        }
        return curr;
    }

    // O(N)
    private Node<T> findPrev(T elem) {
        Node<T> curr = head;
        Node<T> prev = null;
        while (curr != null && (Integer) curr.data < (Integer) elem) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }

    /* ====================== Representation Invariant ====================== */

    private boolean repOk() {
        Node<T> tempHead = head;
        Node<T> tempTail = tail;
        int headCount = 0;
        int tailCount = 0;
        while ( tempHead != null && tempTail != null ) {
            headCount++;
            tailCount++;
            tempHead = tempHead.next;
            tempTail = tempTail.prev;
        }
        return headCount == length && tailCount == length;
    }
}