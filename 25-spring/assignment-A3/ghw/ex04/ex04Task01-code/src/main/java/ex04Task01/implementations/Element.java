package ex04Task01.implementations;

public class Element<K, V> {
    
    private K key;
    private V value;

    public Element(K key, V value) {
        this.key = (K) key;
        this.value = (V) value;
    }

    public K getKey() { return this.key; }

    public V getValue() { return this.value; }

    public String toString() { return "( key: " + key.toString() + ", value: " + value.toString() + " )"; }
}
