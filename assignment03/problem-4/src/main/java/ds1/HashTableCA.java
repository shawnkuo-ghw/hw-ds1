package ds1;

class KeyValuePair {
    int key;
    int value;
    KeyValuePair(int k, int v) {
        this.key = k;
        this.value = v;
    }
}

class Bucket {
    private Sequence<KeyValuePair> list = new ListoverLinkedList<KeyValuePair>();

    public Bucket() {
        list = new ListoverLinkedList<KeyValuePair>();
    }

    public void add(int k, int v) {
        list.insertFront(new KeyValuePair(k,v));
    }
}

public class HashTableCA {

    // implement a hash table using separate chaining
    // the hash table stores integers
    // the hash table is implemented using an array of buckets
    // the array is called table and has length m
    // the number of elements currently stored in the hash table
    // the number of buckets in the hash table
    private int size;
    private int numberOfBuckets;
    private Bucket[] table;

    public HashTableCA(int m) {
        this.numberOfBuckets = m;
        this.table = new Bucket[m];
        for (int i = 0; i < m; i++) {
            this.table[i] = new Bucket();
        }
    }

    int hash(int x) {
        return Math.abs(x) % this.numberOfBuckets;
    }

    public void insert(int k) {
        insert(k,k);
    }

    public void insert(int k, int v) {
        int pseudokey = this.hash(k);
        table[pseudokey].add(k,v);
        size++;
    }

    // computes the load factor of the hash table
    // the load factor is the ratio of the number of elements stored in the hash
    // table to the number of buckets
    public double loadFactor() {
        return (double) this.size / this.numberOfBuckets;
    }
}


