package hw1;

class Node {
    int data;
    Node next;
    Node(int newdata) { 
        data = newdata;
        next = null;
    }
}

public class ModifiedLinkedListQueue implements Queue {
    // Class Attributes
    Node head;
    Node tail;
    int count;
    int sum;

    // Class Methods
    public ModifiedLinkedListQueue() {
        head = null;
        tail = null;
        count = 0;
        sum = 0;
    }

    @Override
    public void enqueue(int elem) {
        Node newnode = new Node(elem);
        if ( head == null && tail == null ) {
            head = newnode;
            tail = newnode;
        } else {
            tail.next = newnode;
            tail = newnode;
        }
        count ++;
        sum += elem;
    }

    @Override
    public int dequeue() {
        if ( isEmpty() ) {
            throw new RuntimeException("Queue.dequeue(): The queue is empty.");
        } 
        int popedElem = head.data;
        if ( head == tail ) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        count --;
        sum -= popedElem;
        return popedElem;
    }

    @Override
    public int  front() {
        if ( isEmpty() ) {
            throw new RuntimeException("Queue.front(): The queue is empty.");
        }
        return head.data;
    }

    @Override
    public void setFront(int newData) {
        if ( isEmpty() ) {
            throw new RuntimeException("Queue.setFront(): The queue is empty.");
        }
        sum += newData - head.data;
        head.data = newData;
    }

    @Override
    public boolean isEmpty() { return head == null && tail == null; }

    @Override
    public boolean isFull() { return false; }

    @Override
    public int elemNum() { return count; }

    @Override
    public int elemSum() { return sum; }

    @Override
    public String toString() {
        String result = "[ ";
        Node itr = head;
        while ( itr != null ) {
            result += itr.data;
            if ( itr.next != null ) {
                result += ", ";
            }
            itr = itr.next;
        }
        result += " ]";
        return result;
    }
}