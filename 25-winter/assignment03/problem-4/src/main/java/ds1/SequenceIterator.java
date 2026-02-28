package ds1;

// Interface for a sequence iterator
// Example usage:
// Given a Sequence<Integer> s
// SequenceIterator<Integer> iter = s.getIterator();
// while (iter.hasNext()) {
//     System.out.println(iter.next());
// }
public interface SequenceIterator<T> {
    // Returns true if the iteration has more elements
    boolean hasNext();

    // Returns the next element in the iteration
    T next();
}


