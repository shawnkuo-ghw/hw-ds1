package ds1.util;

import java.lang.reflect.Array;


public class ListoverLinkedList<T> implements Sequence<T> {
    class Node<T2> {
        T2 data;
        Node<T2> next;
    }
    
    // implement Sequence using LinkedList
    Node<T> head;
    public ListoverLinkedList() {
        head = null;
    }

    // Print the list into an array
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        // assumes the list is of integer
        T[] arr = (T[]) Array.newInstance(head.data.getClass(), length());
        Node<T> curr = head;
        int i = 0;
        while (curr != null) {
            arr[i] = curr.data;
            curr = curr.next;
            i++;
        }
        return arr;
    }
    
    // return the element at index i
    public T at(int i) {
        Node<T> curr = head;
        for (int j = 0; j < i; j++) {
            curr = curr.next;
        }
        return curr.data;
    }

    // return the length of the list
    public int length() {
        int count = 0;
        Node<T> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    // insert elem at index i
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

    // find the node before index i
    private Node<T> findPrev(int i) {
        Node<T> curr = head;
        for (int j = 0; j < i-1; j++) {
            curr = curr.next;
        }
        return curr;
    }

    // remove the element at index i
    public void removeAt(int i) {
        if (i == 0) {
            removeFront();
        } else {
            Node<T> prev = findPrev(i);
            prev.next = prev.next.next;
        }
    }

    // insert elem at the front of the list
    public void insertFront(T elem) {
        Node<T> newNode = new Node<T>();
        newNode.data = elem;
        newNode.next = head;
        head = newNode;
    }

    // remove the element at the front of the list    
    public void removeFront() {
        if (head != null) {
            head = head.next;
        }
    }

    // insert elem at the end of the list
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
            // You dont need to create a node again 
            // you have newNode, I renamed it to insert
            // Node<T> insert = new Node<T>();
            // insert.data = elem;
            Node<T> curr = head;
            // prev is the previos one, at the beguining it is null 
            // not curr
            Node<T> prev = null;
            while (curr != null && (Integer) elem >= (Integer) curr.data){
                prev = curr;
                curr = curr.next;
            }
            // now I know that elem < curr.data
            // or curr == null 
            // and prev is the previous one
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
    public static void main(String[] args) {
        ListoverLinkedList<Integer> list = new ListoverLinkedList<Integer>();
        list.insertFront(1);
        list.insertFront(2);
        list.insertFront(3);
        list.removeAt(1);
        System.out.println(list.at(0));
        System.out.println(list.at(1));
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
        return -1;
    }

    @Override
    public void updateAt(int i, T elem) {
        Node<T> curr = head;
        int index = 0;
        while (curr != null) {
            if (index == i) {
                curr.data = elem;
                return;
            }
            curr = curr.next;
            index++;
        }
        throw new IndexOutOfBoundsException("Index " + i + " out of bounds");
    }

    // to string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> curr = head;
        sb.append("[");
        while (curr != null) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != null) { 
                sb.append(", ");
           }

        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public SequenceIterator<T> getIterator() {
        return new LinkedListIterator<>(this);
    }

    @Override
    public boolean in(T elem) {
        Node<T> curr = head;
        while (curr != null) {
            if (curr.data.equals(elem)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
}