package ds1;

public class ArrayOverStack<T> implements Stack<T>{
    private Object[] arr;
    private int top;

    // invariant: -1 <= top < arr.length
    // invariant: elements are stored from index 0 to top

    //O(1)
    public ArrayOverStack(int capacity){
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity must be positive");
        arr = new Object[capacity];
        top = -1;
    }

    // push element to the stack
    //O(1)
    public void push(T element){
        if(isFull())
            throw new IllegalStateException("stack cannot be full");
        top++;
        arr[top] = element;
    }

    // pop element from the stack
    //O(1)
    @SuppressWarnings("unchecked")
    public T pop(){
        if (isEmpty()){
            throw new IllegalStateException("stack cannot be empty");
        }
        T element = (T) arr[top];
        top--;
        return element;
    }

    // read the top element
    //O(1)
    @SuppressWarnings("unchecked")
    public T readTop(){
        if(isEmpty()) 
            throw new IllegalStateException("stack canot be empty");
        return (T) arr[top];
    }

    //O(1)
    public boolean isEmpty(){
        return top == -1;
    }

    //O(1)
    public boolean isFull(){
        return top == arr.length - 1;
    }

    //O(1)
    public int size(){
        return top + 1;
    }

    //O(n)
    public boolean contains(T element) {
        for (int i = 0; i <= top; i++) {
            @SuppressWarnings("unchecked")
            T current = (T) arr[i];
            if (current == element) {
                return true;
            }
        }
        return false;
    }

    //O(1)
    public boolean repOK(){
        if (arr == null || arr.length <= 0)
            return false;
        if (top < -1)
            return false;
        if (top >= arr.length)
            return false;
        return true;
    }
}
