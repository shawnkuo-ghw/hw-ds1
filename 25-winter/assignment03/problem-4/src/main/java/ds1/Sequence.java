package ds1;

interface Sequence<T> {
    // Inserts methods for sequences
    T at(int i);
    int length();
    void insertAt(int i, T elem);
    void removeAt(int i);
    void insertFront(T elem);
    void insertRear(T elem);
    void insertSorted(T elem);
    SequenceIterator<T> getIterator(); // Returns an iterator for the sequence
}


