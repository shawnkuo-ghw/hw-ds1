package ds1.util;

public interface Sequence<T> {
    // Inserts methods for sequences
    T at(int i);
    int length();
    void insertAt(int i, T elem);
    void removeAt(int i);
    void insertFront(T elem);
    void insertRear(T elem);
    void insertSorted(T elem);
    int indexOf(T elem);
    void updateAt(int i, T elem);
    T[] toArray();  
}

