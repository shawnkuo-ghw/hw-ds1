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
    public void pushHead(int elem) {
        if ( isFull() ) {
            throw new RuntimeException("Stack is full.");
        }
        head ++;
        arr[head] = elem;
    }
    
    @Override
	public void pushTail(int elem) {
        if ( isFull() ) {
            throw new RuntimeException("Stack is full.");
        }
        tail --;
        arr[tail] = elem;
    }
    
    @Override
	public int popHead() {
        if ( isEmptyHead() ) {
            throw new RuntimeException("Head-side stack is empty.");
        }
        int poped = arr[head];
        head --;
        return poped;
    }
    
    @Override
    public int popTail() {
        if ( isEmptyTail() ) {
            throw new RuntimeException("Tail-side stack is empty.");
        }
        int poped = arr[tail];
        tail ++;
        return poped;
    }
    
    @Override
	public boolean isEmptyHead() { return head == -1; }
    
    @Override
	public boolean isEmptyTail() { return tail == size; }
    
    @Override
    public int topHead() {
        if ( isEmptyHead() ) {
            throw new RuntimeException("Head-side stack is empty.");
        }
        return arr[head];
    }
    
    @Override
    public int topTail() {
        if ( isEmptyTail() ) {
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

    @Override
    public int headIdx() { return head; }
    @Override
    public int tailIdx() { return tail; }

    /**
     * Check whether the head-side stack is sorted descendingly
     * Time Complexity: O(n)
     * @return true if it is, false otherwise
     */
    public boolean isSortedDescendinglyHead() {
        boolean isSorted = true;
        int i = 0;
        while ( head > 0 && isSorted && i < head )
        {
            if ( arr[i] < arr[i+1] ) {
                isSorted = false;
            } else {
                i ++;
            }
        }
        return isSorted;
    }
}