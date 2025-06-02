package ex04Task01;

public class HashFunctions {

    public static int hash1(int key, int tableSize) {
        double A = 0.6180339887; // (√5 - 1) / 2, golden ratio fraction
        double frac = (key * A) % 1;
        return (int) (tableSize * frac);
    }

    public static int hash2(int key, int tableSize) {
        int prime = 31; // A small prime multiplier
        return (key * prime) % tableSize;
    }

    public static int hash3(int key, int tableSize) {
        key ^= (key >>> 20) ^ (key >>> 12);
        key = key ^ (key >>> 7) ^ (key >>> 4);
        return key % tableSize;
    }
    
    public int hashBest(int key,int tableSize) {
        key ^= (key >>> 16); // Scramble lower and higher bits
        return Math.abs(key) % tableSize; // Prime table size
    }

    public int hashMul(int key, int tableSize) {
        double A = 0.61803398875; // (√5 - 1)/2 (Knuth's golden ratio)
        double product = key * A;
        return (int) Math.abs(tableSize * (product - (int) product));
    }

    public int hashDiv(int key, int tableSize) {
        return Math.abs(key) % tableSize; // Must use a prime size!
    }
}
