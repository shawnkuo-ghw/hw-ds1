package hw1;

public class DoubleStackOfIntOnArray implements DoubleStackOfInt {

    // Default size of array
    private static final int DEFAULT_SIZE = 10;
    
    // Class attributes
    private int[] arr;
    private int head;
    private int tail;
    private int size;

    // Class Methods
    public DoubleStackOfIntOnArray() {
        size = DEFAULT_SIZE;
        arr = new int[size];
        head = -1;
        tail = size;
    }
    
    public DoubleStackOfIntOnArray(int arrSize) {
        size = arrSize;
        arr = new int[size];
        head = -1;
        tail = size;
    }
    
    @Override
    public void push_head(int elem) {
        if ( isFull() ) {
            throw new RuntimeException("Stack is full.");
        }
        head ++;
        arr[head] = elem;
        System.out.println(toString());
    }
    
    @Override
	public void push_tail(int elem) {
        if ( isFull() ) {
            throw new RuntimeException("Stack is full.");
        }
        tail --;
        arr[tail] = elem;
        System.out.println(toString());
    }
    
    @Override
	public int pop_head() {
        if ( empty_head() ) {
            throw new RuntimeException("Head-side stack is empty.");
        }
        int poped = arr[head];
        head --;
        return poped;
    }
    
    @Override
    public int pop_tail() {
        if ( empty_tail() ) {
            throw new RuntimeException("Tail-side stack is empty.");
        }
        int poped = arr[tail];
        tail ++;
        return poped;
    }
    
    @Override
	public boolean empty_head() { return head == -1; }
    
    @Override
	public boolean empty_tail() { return tail == size; }
    
    @Override
    public int top_head() {
        if ( empty_head() ) {
            throw new RuntimeException("Head-side stack is empty.");
        }
        return arr[head];
    }
    
    @Override
    public int top_tail() {
        if ( empty_head() ) {
            throw new RuntimeException("Tail-side stack is empty.");
        }
        return arr[tail];
    }

    /**
     * Represent the double stack with a string
     */
    public String toString() {
        String result = "stack: ";
        int i = 0;
        while ( i < size ) {
            if ( i <= head || i >= tail ) {
                result += "[" + arr[i] + "]";
            } else {
                result += "[ ]";
            }
            result += " ";
            i ++;
        } 
        return result;
    }

    @Override
    public boolean isFull() { return head == tail - 1; }
}