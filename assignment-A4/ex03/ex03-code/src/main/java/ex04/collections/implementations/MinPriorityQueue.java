package ex04.collections.implementations;
import java.util.NoSuchElementException;

import ex04.collections.interfaces.List;
import ex04.collections.interfaces.PriorityQueue;

/**
 * The minimal implementation of interface {@code PriorityQueue}
 * @param <Key> the type of keys in priority queue
 * @see ex04.collections.interfaces.PriorityQueue
 */
public class MinPriorityQueue<Key extends Comparable<Key> > implements PriorityQueue<Key>
{
    private List<Key> minPQ;

    /*
     * Auxiliary methods
     */

    private static int PARENT(int i)
    { return (i - 1) / 2; }

    private static int LEFT(int i)
    { return 2 * i + 1; }

    private static int RIGHT(int i)
    { return 2 * i + 2; }

    private boolean greater(int i, int j)
    { return minPQ.get(i).compareTo(minPQ.get(j)) > 0; }

    private boolean MinPriorityQueuePropertyCheck()
    {
        boolean checkResult = true;
        if ( !minPQ.empty() && minPQ.size() > 1 ) {
            int i = 1;
            while ( checkResult & i < minPQ.size() ) {
                Key child = minPQ.get(i);
                Key parent = minPQ.get(PARENT(i));
                if ( parent.compareTo(child) > 0 ) {
                    checkResult = false;
                } else {
                    i ++;
                }
            }
        }
        return checkResult;        
    }

    private void swim(int k)
    {
        while ( k > 0 && this.greater(PARENT(k), k) ) {
            minPQ.swap(PARENT(k), k);
            k = PARENT(k);
        }        
    }

    private void sink(int k)
    {
        boolean hasChildren = true;
        boolean lessThanChildren = true;
        while ( hasChildren & lessThanChildren ) {
            if ( LEFT(k) >= minPQ.size() ) {
                hasChildren = false;
            } else {
                // find the index among k, LEFT(k) and RIGHT(k) (if any) with the largest key
                int largest = LEFT(k);
                if ( RIGHT(k) < minPQ.size() && this.greater(largest, RIGHT(k)) ) {
                    largest = RIGHT(k);
                }
                // swap elements pointed by k and the largest, respectively, if needed
                if ( greater(k, largest) ) {
                    minPQ.swap(k, largest);
                    k = largest;
                } else {
                    lessThanChildren = false;
                }
            }
        }
    }

    /*
     * Class Methods
     */

    // Constructors
    public MinPriorityQueue()
    { minPQ = new LinkedList<Key>(); }

    @Override
    public int size()
    { return minPQ.size(); }

    @Override
    public boolean isEmpty()
    { return minPQ.size() == 0; }

    @Override
    public Key top()
    {
        if ( isEmpty() ) {
            throw new NoSuchElementException("minPriorityQueue.top(): minPQ is empty");
        }
        return minPQ.first();
    }

    @Override
    public void enqueue(Key newKey)
    {
        minPQ.append(newKey);
        swim(minPQ.size() - 1);
        if ( !MinPriorityQueuePropertyCheck() ) {
            throw new IllegalStateException("minPriorityQueue.enqueue(): minPQ property is not satisfied.");
        }
    }

    @Override
    public Key dequeue()
    {
        if ( isEmpty() ) {
            throw new NoSuchElementException("minPriorityQueue.dequeue(): minPQ is empty.");
        }
        Key popedKey = minPQ.first();
        if ( size() > 1 ) {
            minPQ.swap(0, minPQ.size() - 1);
            minPQ.removeLast();
            sink(0);
        } else {
            minPQ.removeFirst();
        }

        if ( !MinPriorityQueuePropertyCheck() ) {
            throw new IllegalStateException("minPriorityQueue.dequeue(): minPQ property is not satisfied.");
        }
        return popedKey;
    }
}