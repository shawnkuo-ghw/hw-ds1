package ds1.util;

public class ListoverLinkedList<T> implements Sequence<T>
{
    
    /* ========================== Node Class ===============================  */

    class Node<T2> {
        T2 data;
        Node<T2> next;
    }

    /* ============================= Fields ================================= */

    Node<T> head, tail;
    private int length;
    private final Class<T> type;

    /* =========================== Constructor ============================== */

    public ListoverLinkedList(Class<T> newType) {
        head = null;
        tail = null;
        length = 0;
        type = newType;
    }

    /* =========================== Queriers ================================= */

    // O(N)
    public T at(int i) {
        Node<T> curr = head;
        for (int j = 0; j < i; j++) {
            curr = curr.next;
        }
        return curr.data;
    }
    
    // O(1)
    public int length() { return length; }

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
    public T[] toArray() {
        T[] arr = (T[]) java.lang.reflect.Array.newInstance(type, length);
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
        Node<T> curr = head;
        for (int j = 0; j < i; j++) {
            curr = curr.next;
        }
        curr.data = elem;
    }

    // O(N)
    public void insertAt(int i, T elem) {
        if (i == 0) {
            insertFront(elem);
        } else if (i == length-1) {
            insertRear(elem);
        }  else {
            Node<T> newNode = new Node<T>();
            newNode.data = elem;
            Node<T> prev = findPrev(i);
            newNode.next = prev.next;
            prev.next = newNode;
            length++;
        }
    }

    // O(N)
    public void removeAt(int i) {
        if (i == 0) {
            removeFront();
        } else {
            Node<T> prev = findPrev(i);
            prev.next = prev.next.next;
            length--;
        }
    }

    // O(1)
    public void insertFront(T elem) {
        Node<T> newNode = new Node<T>();
        newNode.data = elem;
        newNode.next = head;
        head = newNode;
        length++;
    }

    // O(1)
    public void removeFront() {
        if (head != null) {
            head = head.next;
            length--;
        }
    }

    // O(1)
    public void insertRear(T elem) {
        Node<T> newNode = new Node<T>();
        newNode.data = elem;
        newNode.next = null;
        if ( head == null && tail == null ) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    // insert elem in sorted order
    // O(N)
    public void insertSorted(T elem) {
        Node<T> newNode = new Node<T>();
        newNode.data = elem;
        if (head == null) {
            head = newNode;
        } else {
            Node<T> prev = findPrev(elem);
            if (prev == null) {
                newNode.next = head;
                head = newNode;
            } else {
                newNode.next = prev.next;
                prev.next = newNode;
            }
        }
        length++;
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
}