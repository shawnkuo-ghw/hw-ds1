package ds1;

interface Sequence<E> {
    // Inserts methods for sequences
    E at(int i);
    int length();
    void insertAt(int i, E elem);
    void removeAt(int i);
    void insertFront(E elem);
    void insertRear(E elem);
    void insertSorted(E elem);
    void start();
    void advance();
    Boolean containsAll(Sequence<E> seq);
    boolean isEnd();
    E examine();
    int indexOf(E elem);
    SequenceIterator<E> getIterator(); // Returns an iterator for the sequence

}