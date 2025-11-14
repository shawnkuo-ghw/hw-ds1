package ds1.utils;

public class ListoverLinkedList<T> implements Sequence<T>
{
    class Node<T2> {
        T2 data;
        Node<T2> next;
    }

    // implement Sequence using LinkedList

    /**********
     * Fields *
     **********/
    Node<T> head;
    private int length;

    /***************
     * Constructor *
     ***************/
    public ListoverLinkedList() {
        head = null;
        length = 0;
    }

    public T at(int i) {
        Node<T> curr = head;
        for (int j = 0; j < i; j++) {
            curr = curr.next;
        }
        return curr.data;
    }
    
    public int length() {
        return length;
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
            length++;
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
            length--;
        }
    }

    public void insertFront(T elem) {
        Node<T> newNode = new Node<T>();
        newNode.data = elem;
        newNode.next = head;
        head = newNode;
        length++;
    }

    public void removeFront() {
        if (head != null) {
            head = head.next;
            length--;
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
        length++;
    }

    // insert elem in sorted order
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

    private Node<T> findPrev(T elem) {
        Node<T> curr = head;
        Node<T> prev = null;
        while (curr != null && (Integer) curr.data < (Integer) elem) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }

    // implement toString
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

    @Override
    public void updateAt(int i, T elem) {
        Node<T> curr = head;
        for (int j = 0; j < i; j++) {
            curr = curr.next;
        }
        curr.data = elem;
    }

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

    public static void main(String[] args) {
        ListoverLinkedList<Integer> list = new ListoverLinkedList<Integer>();
        list.insertFront(1);
        list.insertFront(2);
        list.insertFront(3);
        list.removeAt(1);
        System.out.println(list.at(0));
        System.out.println(list.at(1));
    }
}