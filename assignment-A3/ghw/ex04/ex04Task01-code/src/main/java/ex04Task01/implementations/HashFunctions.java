package ex04Task01.implementations;
import java.util.function.BiFunction;

public class HashFunctions {

    public static final BiFunction<Integer, Integer, Integer> hash1 = (key, tableSize) -> {
        double A = 0.6180339887; // (√5 - 1) / 2, golden ratio fraction
        double frac = (key * A) % 1;
        return (int) (tableSize * frac);
    };

    public static final BiFunction<Integer, Integer, Integer> hash2 = (key, tableSize) -> {
        int prime = 31; // A small prime multiplier
        return (key * prime) % tableSize;
    };

    public static final BiFunction<Integer, Integer, Integer> hash3 = (key, tableSize) -> {
        key ^= (key >>> 20) ^ (key >>> 12);
        key = key ^ (key >>> 7) ^ (key >>> 4);
        return key % tableSize;
    };
    
    static public final BiFunction<Integer, Integer, Integer> hashBest = (key, tableSize) -> {
        key ^= (key >>> 16); // Scramble lower and higher bits
        return Math.abs(key) % tableSize; // Prime table size
    };

    static public final BiFunction<Integer, Integer, Integer> hashMul = (key, tableSize) -> {
        double A = 0.61803398875; // (√5 - 1)/2 (Knuth's golden ratio)
        double product = key * A;
        return (int) Math.abs(tableSize * (product - (int) product));
    };

    static public final BiFunction<Integer, Integer, Integer> hashDiv = (key, tableSize) -> {
        return Math.abs(key) % tableSize; // Must use a prime size!
    };
}