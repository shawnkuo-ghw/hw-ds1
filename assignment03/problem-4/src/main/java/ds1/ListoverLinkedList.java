package ds1;

class Node<T2> {
    T2 data;
    Node<T2> next;
}

public class ListoverLinkedList<T> implements Sequence<T>{
    // implement Sequence using LinkedList
    Node<T> head;

    public ListoverLinkedList() {
        head = null;
    }

    public LinkedListIterator<T> getIterator() {
        return new LinkedListIterator<T>(head);
    }

    public T at(int i) {
        int startPosition = 0;
        Node<T> curr = head;
        for (int j = startPosition; j < i; j++) {
            curr = curr.next;
        }
        return curr.data;
    }

    public int length() {
        int count = 0;
        Node<T> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public void insertAt(int i, T elem) {
        if (i == 0) {
            insertFront(elem);
        } else {
            Node<T> newNode = new Node<T>();
            newNode.data = elem;
            Node<T> prev = findPrev(i);
            newNode.next = prev.next;
            prev.next = newNode;
        }
    }

    private Node<T> findPrev(int i) {
        Node<T> curr = head;
        for (int j = 0; j < i-1; j++) {
            curr = curr.next;
        }
        return curr;
    }

    public void removeAt(int i) {
        if (i == 0) {
            removeFront();
        } else {
            Node<T> prev = findPrev(i);
            prev.next = prev.next.next;
        }
    }

    public void insertFront(T elem) {
        Node<T> newNode = new Node<T>();
        newNode.data = elem;
        newNode.next = head;
        head = newNode;
    }

    public void removeFront() {
        if (head != null) {
            head = head.next;
        }
    }

    public void insertRear(T elem) {
        Node<T> newNode = new Node<T>();
        newNode.data = elem;
        newNode.next = null;
        if (head == null) {
            head = newNode;
        } else {
            Node<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
    }

    // append another list to the end of this list
    public void append(ListoverLinkedList<T> other) {
        if (head == null) {
            head = other.head;
        } else {
            Node<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = other.head;
        }
    }

    // insert elem in sorted order
    public void insertSorted(T elem) {
        Node<T> insert = new Node<T>();
        insert.data = elem;
        if (head == null) {
            head = insert;
        } else {
            Node<T> curr = head;
            // prev is the previous one, at the beginning it is null
            Node<T> prev = null;
            while (curr != null && (Integer) elem >= (Integer) curr.data){
                prev = curr;
                curr = curr.next;
            }
            // Case 1: prev is null => insert at the front
            if (prev == null) {
                insert.next = head;
                head = insert;
            }
            // Case 2 : prev is not null => insert after prev
            else {
                insert.next = prev.next;
                prev.next = insert;
            }
        }
    }
}


