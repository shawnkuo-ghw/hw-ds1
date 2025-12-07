class QueueNode {
    IndexNode data;
    QueueNode next;
    //O(1)
    QueueNode(IndexNode data) {
        this.data = data;
    }
}

public class LinkedListQueue implements Queue {
    QueueNode head;
    QueueNode tail;
    //O(1)
    public LinkedListQueue() {
        head = null;
        tail = null;
    }
    //O(1)
    public void enqueue(IndexNode elem) {
        QueueNode newNode = new QueueNode(elem);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }
    //O(1)
    public IndexNode dequeue() {
        if (head == null) {
            throw new RuntimeException("Queue is empty");
        }
        IndexNode elem = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return elem;
    }
    //O(1)
    public IndexNode front() {
        if (head == null) {
            throw new RuntimeException("Queue is empty");
        }
        return head.data;
    }
    //O(1)
    public IndexNode rear() {
        if (tail == null) {
            throw new RuntimeException("Queue is empty");
        }
        return tail.data;
    }
    //O(1)
    public boolean isEmpty() {
        return head == null;
    }
    //O(1)
    public boolean isFull() {
        return false;
    }
}

