package ds1;

class StackNode<T> {
    T data;
    StackNode<T> next;

    StackNode(T data) {
        this.data = data;
    }
}

public class LinkedListStack<T> implements Stack<T> {
    StackNode<T> head;
    int size;

    public LinkedListStack() {
        head = null;
        size = 0;
    }

    public void push(T elem) {
        StackNode<T> newNode = new StackNode<T>(elem);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T elem = head.data;
        head = head.next;
        size--;
        return elem;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}


