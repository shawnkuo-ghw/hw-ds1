package ds1.utils;

import ds1.Transaction;

class Node {
    Transaction data;
    Node next;
    Node(Transaction data) {
        this.data = data;
    }
}
public class LinkedListQueue implements Queue{
    Node head;
    Node tail;
    int size;
    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public void enqueue(Transaction elem) {
        Node newNode = new Node(elem);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    public Transaction dequeue() {
        if (head == null) {
            throw new RuntimeException("Queue is empty");
        }
        Transaction elem = head.data;
        head = head.next;
        size--;
        return elem;
    }
    public Transaction front() {
        if (head == null) {
            throw new RuntimeException("Queue is empty");
        }
        return head.data;
    }
    public Transaction rear() {
        if (head == null) {
            throw new RuntimeException("Queue is empty");
        }
        return tail.data;
    }
    public boolean isEmpty() {
        return head == null;
    }
    public boolean isFull() {
        return false;
    }

    public int size() {
        return size;
    }

    public Transaction[] toArray(){
        Transaction[] queueAsArray = new Transaction[size];
        int i = 0;
        Node currNode = head;
        while(currNode != null){
            queueAsArray[i] = currNode.data;
            currNode = currNode.next;
            i++;
        }
        return queueAsArray;
    }

    @Override
    public void remove(int index) {
        // Remove element at index while maintaining order
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            dequeue();
            return;
        }
        Node curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        size--;
    }

}
