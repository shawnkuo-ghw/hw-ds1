

public class PalindromeCollectorImp implements PalindromeCollectorofChar {

    private static final int DEFAULT_CAPACITY = 16;

    private char[] collector;
    private int capacity; // max number of characters that the collector can have
    private int head; // index of the first element
    private int tail; // index of the last element
    private int size; // current used size of the collector

    /**
     * create a collector with a given capacity
     * capacity must be positive
     */
    public PalindromeCollectorImp(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity should be positive");
        }
        this.capacity = capacity;
        this.collector = new char[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    /**
     * create a collector with a default capacity
     */
    public PalindromeCollectorImp() {
        this(DEFAULT_CAPACITY);
    }

    /*
     * decrease the index in a circular way
     */
    private int dec(int index) {
        return (index - 1 + capacity) % capacity;
    }
    /*
     * increase the index in a circular way
     */
    private int inc(int index) {
        return (index + 1) % capacity;
    }

    @Override
    public void addFirst(char ch) {
        if(ch < 'a' || ch > 'z') {
            throw new IllegalArgumentException("ch should be lowercase from a to z");
        }
        if(size == capacity) {
            throw new IllegalStateException("collector should not be full");
        }
        head = dec(head);
        collector[head] = ch;
        size++;
        if(!repOK()) {
            throw new IllegalStateException("collector doesn't satisfy repok()");
        }
    }

    @Override
    public void addLast(char ch) {
        if(ch < 'a' || ch > 'z') {
            throw new IllegalArgumentException("ch should be lowercase from a to z");
        }
        if(size == capacity) {
            throw new IllegalStateException("collector should not be full");
        }
        collector[tail] = ch;
        tail = inc(tail);
        size++;
        if(!repOK()) {
            throw new IllegalStateException("collector doesn't satisfy repok()");
        }
    }

    @Override
    public char removeFirst() {
        if(isEmpty()) {
            throw new IllegalStateException("collector should not be empty");
        }
        char value = collector[head];
        head = inc(head);
        size--;
        if(!repOK()) {
            throw new IllegalStateException("collector doesn't satisfy repok()");
        }
        return value;
    }

    @Override
    public char removeLast() {
        if(isEmpty()) {
            throw new IllegalStateException("collector should not be empty");
        }
        tail = dec(tail);
        char value = collector[tail];
        size--;
        if(!repOK()) {
            throw new IllegalStateException("collector doesn't satisfy repok()");
        }
        return value;
    }

    /*
     * return the index = head + index in a circular way
     */
    private int indexFromHead(int index) {
        return (head + index) % capacity;
    }

    @Override
    public boolean isPalindrome() {
        for (int i = 0; i < size / 2; i++) {
            char left = collector[indexFromHead(i)];
            char right = collector[indexFromHead(size - 1 - i)];
            if (left != right) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * This method will help run the test
     * return the content of the collector in an array
     */
    public char[] toArray() {
        char[] result = new char[size];
        for (int i = 0; i < size; i++) {
            result[i] = collector[indexFromHead(i)];
        }
        return result;
    }

    /**
     * Representation invariant: 
     * 1. capacity > 0 
     * 2. the length of collector is capacity
     * 3. 0 <= size <= capacity;
     * 4. head and tail are within [0, capacity)
     * 5. tail == (head + size) mod capacity
     * 6. any character is in ['a','z'].
     */
    public boolean repOK() {
        if (collector == null || capacity <= 0 || collector.length != capacity) {
            return false;
        }
        if (size < 0 || size > capacity) {
            return false;
        }
        if (head < 0 || head >= capacity || tail < 0 || tail >= capacity) {
            return false;
        }
        if (tail != (head + size) % capacity) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            char ch = collector[indexFromHead(i)];
            if (ch < 'a' || ch > 'z') {
                return false;
            }
        }
        return true;
    }
}