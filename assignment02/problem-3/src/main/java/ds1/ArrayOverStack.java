package ds1;

public class ArrayOverStack<T> implements Stack<T> {
    private Object[] arr;
    private int top;

    // invariant: -1 <= top < arr.length
    // invariant: elements are stored from index 0..top

    //O(1)
    public ArrayOverStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        arr = new Object[capacity];
        top = -1;
    }

    // push element onto the stack
    //O(1)
    public void push(T elem) {
        if (isFull()) {
            throw new RuntimeException("Stack is full");
        }
        top++;
        arr[top] = elem;
    }

    // pop element from the stack
    //O(1)
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T elem = (T) arr[top];
        arr[top] = null; // help GC
        top--;
        return elem;
    }

    // read the top element without removal
    //O(1)
    @SuppressWarnings("unchecked")
    public T readTop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return (T) arr[top];
    }

    //O(1)
    public boolean isEmpty() {
        return top == -1;
    }

    //O(1)
    public boolean isFull() {
        return top == arr.length - 1;
    }

    //O(1)
    public int size() {
        return top + 1;
    }

    //O(1)
    public boolean repOK() {
        if (arr == null || arr.length <= 0) {
            return false;
        }
        if (top < -1) {
            return false;
        }
        if (top >= arr.length) {
            return false;
        }
        return true;
    }

}
