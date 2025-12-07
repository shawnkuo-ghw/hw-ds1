class QueueNode {
    IndexNode data;
    QueueNode next;

    QueueNode(IndexNode data) {
        this.data = data;
    }
}

public class LinkedListQueue implements Queue {
    QueueNode head;
    QueueNode tail;

    public LinkedListQueue() {
        head = null;
        tail = null;
    }

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

    public IndexNode front() {
        if (head == null) {
            throw new RuntimeException("Queue is empty");
        }
        return head.data;
    }

    public IndexNode rear() {
        if (tail == null) {
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
}

