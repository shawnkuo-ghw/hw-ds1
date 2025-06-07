package ds1;

import java.util.*;

public class HashFuncComp {
    static final int NUM_KEYS = 100;
    static final int NUM_RUNS = 2000;

    public static Set<Integer> buildUniKeys(int num){
        Set<Integer> keysSet = new HashSet<>();
        Random rand = new Random();
        while (keysSet.size() < num) {
            keysSet.add(rand.nextInt(Integer.MAX_VALUE));
        }
        return keysSet;
    }

    public static int[] buildEmptyTable(int size){
        int[] table = new int[size];
        Arrays.fill(table, -1); //We use `-1` for empty slot, `-2` for deleted one
        return table;
    }

    public static double measureSuccessProbes(Set<Integer> keysSet, int hash, int[] table, int tableSize){
        int successProbes = 0;
        //For key we know should be in the table, #steps for searches = #steps for insertion
        for (int key : keysSet) {
            successProbes += insert(table, key, hash, tableSize);
        }
        return (double) successProbes / NUM_KEYS;
    }

    public static double measureUnsuccessProbes(Set<Integer> existKeys, int hashType, int[] table, int tableSize) {
        int unsuccessProbes = 0;
        Random rand = new Random();
        int UN_TEST_TIMES = 100;
        for (int i = 0; i < UN_TEST_TIMES; i++) {
            int fakeKey;
            do {
                fakeKey = rand.nextInt(Integer.MAX_VALUE);
            } while (existKeys.contains(fakeKey));
            unsuccessProbes += search(table, fakeKey, hashType, tableSize);
        }
        return (double) unsuccessProbes / UN_TEST_TIMES;
    }

    public static int measurePrimaryCluster(int[] table) {
        int max = 0;
        int current = 0;
        for (int value : table) {
            if (value != -1) {
                current++;
            } else {
                max = Math.max(max, current);
                current = 0;
            }
        }
        return Math.max(max, current);
    }

    private static int insert(int[] table, int key, int hash, int tableSize) {
        int probes = 1;
        int idx = hash(key, hash, tableSize);
        while (table[idx] != -1) {
            idx = (idx + 1) % tableSize; //linear probing
            probes++;
        }
        table[idx] = key;
        return probes;
    }

    private static int search(int[] table, int key, int hash, int tableSize) {
        int probes = 1;
        int idx = hash(key, hash, tableSize);
        while (table[idx] != -1) {
            if (table[idx] == key) {
                return probes;
            }
            idx = (idx + 1) % tableSize;
            probes++;
        }
        return probes;
    }

    private static int hash(int key, int type, int tableSize) {
        switch (type) {
            case 0: return Math.floorMod(hash1(key, tableSize), tableSize);
            case 1: return Math.floorMod(hash2(key, tableSize), tableSize);
            case 2: return Math.floorMod(hash3(key, tableSize), tableSize);
            case 3: return Math.floorMod(hashBest(key, tableSize), tableSize);
            case 4: return Math.floorMod(hashMul(key, tableSize), tableSize);
            case 5: return Math.floorMod(hashDiv(key, tableSize), tableSize);
        }    
        return -1;
    }

    public static int hash1(int key, int tableSize) {
        double A = 0.6180339887; // (√5 - 1) / 2, golden ratio fraction
        double frac = (key * A) % 1;
        return (int)(tableSize * frac);
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

    public static int hashBest(int key,int tableSize) {
        key ^= (key >>> 16); // Scramble lower and higher bits
        return Math.abs(key) % tableSize; // Prime table size
    }

    public static int hashMul(int key, int tableSize) {
        double A = 0.61803398875; // (√5 - 1)/2 (Knuth's golden ratio)
        double product = key * A;
        return (int) Math.abs(tableSize * (product - (int) product));
    }

    public static int hashDiv(int key, int tableSize) {
        return Math.abs(key) % tableSize; // Must use a prime size!
    }


    public static void main(String[] args){
        int[] tableSizes = {149, 149, 149, 151, 151, 151};
        String[] hashFunc = {"hash1", "hash2", "hash3", "hashBest", "hashMul", "hashDiv"};
        double[] successProbesCounter = new double[6];
        double[] unsuccessProbeCounter = new double[6];
        double[] largestPrimaryCluster = new double[6];

        for (int i = 0; i < NUM_RUNS; i++) {
            Set<Integer> keysSet = buildUniKeys(NUM_KEYS); //the set of keys

            for (int h = 0; h < 6; h++) {
                int tableSize = tableSizes[h];
                int[] table = buildEmptyTable(tableSize);

                double successProbe = measureSuccessProbes(keysSet, h, table, tableSize); //insert at the same time
                double unsuccessProbe = measureUnsuccessProbes(keysSet, h, table, tableSize);
                double primaryCluster = measurePrimaryCluster(table);

                successProbesCounter[h] += successProbe;
                unsuccessProbeCounter[h] += unsuccessProbe;
                largestPrimaryCluster[h] += primaryCluster;
            }
        }

        System.out.printf("%-20s %-20s %-18s %-20s%n", "HashFunction", "SuccessProbes", "UnscsProbes", "LargestCluster");
        for (int m = 0; m < 6; m++) {
            System.out.printf("%-20s %-20.3f %-18.3f %-20.3f%n", hashFunc[m],
                                                                        successProbesCounter[m]/NUM_RUNS, 
                                                                        unsuccessProbeCounter[m]/NUM_RUNS, 
                                                                        largestPrimaryCluster[m]/NUM_RUNS);
        }
    }

}