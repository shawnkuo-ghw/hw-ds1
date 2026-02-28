package ds1.util;

public class HashTable {

    private static class KeyValuePair {
        String key;
        int value;
        KeyValuePair next;

        KeyValuePair(String key, int value, KeyValuePair next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private KeyValuePair[] table;
    private int size;

    private static final int DEFAULT_CAPACITY = 100;

    public HashTable(int capacity) {
        if (capacity < 1) capacity = 1;
        this.table = new KeyValuePair[capacity];
        this.size = 0;
    }

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public int size() {
        return size;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public Integer get(String key) {
        if (key == null) return null;
        int index = indexFor(key, table.length);
        KeyValuePair e = table[index];
        while (e != null) {
            if (key.equals(e.key)) {
                return e.value;
            }
            e = e.next;
        }
        return null;
    }

    public void put(String key, int value) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }

        int index = indexFor(key, table.length);
        KeyValuePair e = table[index];
        while (e != null) {
            if (key.equals(e.key)) {
                e.value = value;
                return;
            }
            e = e.next;
        }
        table[index] = new KeyValuePair(key, value, table[index]);
        size++;
    }

    private static int indexFor(String key, int mod) {
        String str = HashUtils.hash(key);
        int h = 0;
        for (int i = 0; i < str.length(); i++) {
            h = 31 * h + str.charAt(i);
        }
        if (h < 0) h = -h;
        return h % mod;
    }
}

