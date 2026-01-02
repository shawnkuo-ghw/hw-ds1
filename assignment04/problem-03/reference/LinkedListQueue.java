package ds1;

// Helper class with proper generic typing
class QueueNode<T> {
    T data;
    QueueNode<T> next;

    // O(1)
    QueueNode(T data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListQueue<T> implements Queue<T> {
    private QueueNode<T> head;
    private QueueNode<T> tail;

    // O(1)
    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
    }

    // O(1)
    @Override
    public void enqueue(T elem) {
        QueueNode<T> newNode = new QueueNode<>(elem);
        if (this.isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // O(1)
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue cannot be empty");
        }
        T elem = head.data;
        head = head.next;
        
        // If the queue is now empty, reset tail to null
        if (head == null) {
            tail = null;
        }
        return elem;
    }

    // O(1)
    @Override
    public T front() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue cannot be empty");
        }
        return head.data;
    }

    // O(1)
    @Override
    public T rear() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue cannot be empty");
        }
        return tail.data;
    }

    // O(1)
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    // O(1)
    // A linked list based queue is technically never full
    @Override
    public boolean isFull() {
        return false;
    }
}