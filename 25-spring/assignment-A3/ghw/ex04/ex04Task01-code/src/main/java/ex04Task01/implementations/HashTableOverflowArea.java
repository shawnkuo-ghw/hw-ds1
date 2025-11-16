package ex04Task01.implementations;
import java.util.function.BiFunction;
import ex04Task01.interfaces.Dictionary;
import ex04Task01.interfaces.List;

/**
 * The hash table implementation of interface {@code Dictionary}.
 */
public class HashTableOverflowArea<K, V> implements Dictionary<K, V>{
    
    private int tableSize;
    private int elemCount;
    private Class<K> keyClass;
    private Class<V> valClass;
    private List<Element<K, V>> hashTable;
    private final BiFunction<K, Integer, Integer> hashFunction;

    public HashTableOverflowArea(int tableSize, Class<K> keyClass, Class<V> valClass,  BiFunction<K, Integer, Integer> hashFunction) {
        this.keyClass = keyClass;
        this.valClass = valClass;
        this.tableSize = tableSize;
        this.hashFunction = hashFunction;
        hashTable = new LinkedList<Element<K, V>>(tableSize);
    }

    @Override
    public V get(Object key) {
        if ( !keyClass.isInstance(key) ) { throw new IllegalArgumentException("HashTable.get(): key must be of type " + keyClass.getName()); }
        K typedKey = keyClass.cast(key);
        int elemIndex = this.search(typedKey);
        return (elemIndex != -1) ? hashTable.get(elemIndex).getValue() : null;
    }

    @Override
    public int insert(Object key, Object value) {
        if ( this.elemCount == this.tableSize ) { throw new IllegalStateException("HashTable.insert(): hash table overflow."); }
        if ( !keyClass.isInstance(key) )        { throw new IllegalArgumentException("HashTable.insert(): key must be of type " + keyClass.getName()); }
        if ( !valClass.isInstance(value) )      { throw new IllegalArgumentException("HashTable.insert(): value must be of type " + valClass.getName()); }
        boolean ifProbing = true;
        K typedKey = keyClass.cast(key);
        V typedVal = valClass.cast(value);
        int i = 0; // probe number
        int q = 0; // index of hash table associated with key w.r.t. hash function
        do {
            q = ( hashFunction.apply(typedKey, tableSize) + i ) % tableSize;
            if ( hashTable.get(q) == null ) {
                hashTable.setAt(q, new Element<K, V>(typedKey, typedVal));
                ifProbing = false;
            } else {
                i ++;
            }
        } while ( ifProbing && i < tableSize );
        this.elemCount ++;
        return q;
    }
    
    @Override
    public int search(Object key) {
        if ( !keyClass.isInstance(key) ) { throw new IllegalArgumentException("HashTable.search(): key must be of type " + keyClass.getName()); }
        K typedKey = keyClass.cast(key);
        int i = 1; // probe number 
        int q = 0; // index of hash table associated with key w.r.t. hash function 
        boolean ifFound = false;
        do {
            q = ( hashFunction.apply(typedKey, tableSize) + i ) % tableSize;
            if ( hashTable.get(q) != null && hashTable.get(q).getKey().equals(typedKey) ) {
                ifFound = true;
            } else {
                i ++;
            }
        } while ( !ifFound && hashTable.get(q) != null && i <= tableSize );
        return ifFound ? q : -1;
    }

    public int searchCount(Object key) {
        if ( !keyClass.isInstance(key) ) { throw new IllegalArgumentException("HashTable.search(): key must be of type " + keyClass.getName()); }
        K typedKey = keyClass.cast(key);
        int i = 1; // probe number 
        int q = 0; // index of hash table associated with key w.r.t. hash function 
        boolean ifFound = false;
        do {
            q = ( hashFunction.apply(typedKey, tableSize) + i ) % tableSize;
            if ( hashTable.get(q) != null && hashTable.get(q).getKey().equals(typedKey) ) {
                ifFound = true;
            } else {
                i ++;
            }
        } while ( !ifFound && hashTable.get(q) != null && i <= tableSize );
        return i;
    }

    @Override
    public boolean defined(K key) { return this.search(key) != -1; }

    @Override
    public int largestPrimaryCluster() { return hashTable.largestPrimaryCluster(); }

    @Override
    public String toString(int maxLineWidth) {
        String strRep    = "";
        String nilStr    = "NIL";
        String titleStr  = "Hash Table";
        int lineWidth    = maxLineWidth;
        int titleLeft    = ( lineWidth - titleStr.length() ) / 2;
        int titleRight   = lineWidth - ( titleLeft + titleStr.length() );
        String sepLine   = "+" + "-".repeat(lineWidth) + "+\n";
        String titleLine = "|" + " ".repeat(titleLeft) + "Hash Table" + " ".repeat(titleRight) + "|\n";
        strRep += sepLine + titleLine + sepLine;
        int i = 0;
        while ( i < tableSize ) {
            if ( hashTable.get(i) == null ) {
                int leftWidth  = ( lineWidth - nilStr.length() ) / 2;
                int rightWidth = lineWidth - ( leftWidth + nilStr.length() );
                strRep += "|" + " ".repeat(leftWidth) + nilStr + " ".repeat(rightWidth) + "|\n" + sepLine;
            } else {
                String elemStr = hashTable.get(i).toString();
                int leftWidth  = ( lineWidth - elemStr.length() ) / 2;
                int rightWidth = lineWidth - ( leftWidth + elemStr.length() );
                strRep += "|" + " ".repeat(leftWidth) + elemStr + " ".repeat(rightWidth) + "|\n" + sepLine;
            }
            i ++;
        }
        return strRep;
    }
}