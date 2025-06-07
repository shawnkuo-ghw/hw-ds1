package ds1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class OverflowHash {
    static int primarySize = 101;
    static int overflowSize = 20;
    static int overflowIdx = 0;
    static int[] primaryArea = new int[primarySize];
    static int[] overflowArea = new int[overflowSize];
    static int NUM_KEYS_Of = (int) (primarySize * 0.67);
    static final int NUM_RUNS = 2000;

    public static Set<Integer> buildUniKeys(int num){
        Set<Integer> keysSet = new HashSet<>();
        Random rand = new Random();
        while (keysSet.size() < num) {
            keysSet.add(rand.nextInt(Integer.MAX_VALUE));
        }
        return keysSet;
    }

    public static double insertKeysFailures(Set<Integer> keysSet, int hash) {
        int failures = 0;
        for (int key : keysSet) {
            if (!insert(key, hash)) {
                failures++;
            }
        }
        return (double) failures;
    }

    public static double measureSuccessfulProbesWithOverflow(Set<Integer> keysSet, int hash) {
        int successProbes = 0;
        for (int key : keysSet) {
            int idx = hash(key, hash);
            int probes = 1;
            if (!Objects.equals(primaryArea[idx], key)) {
                for (int i = 0; i < overflowSize; i++) {
                    probes++;
                    if (Objects.equals(overflowArea[i], key)) {
                        break;
                    }
                }
            }
            successProbes += probes;
        }
        return (double) successProbes / NUM_KEYS_Of;
    }

    public static double measureUnsuccessfulProbesWithOverflow(Set<Integer> existKeys, int hash) {
        int unsuccessProbes = 0;
        Random rand = new Random();
        int UN_TEST_TIMES = 100;
        for (int i = 0; i < UN_TEST_TIMES; i++) {
            int fakeKey;
            do {
                fakeKey = rand.nextInt(Integer.MAX_VALUE);
            } while (existKeys.contains(fakeKey));
            int probes = 1;
            int idx = hash(fakeKey, hash);
            if (primaryArea[idx] != -1) {
                probes += overflowIdx;
            }
            unsuccessProbes += probes;
        }
        return (double) unsuccessProbes / UN_TEST_TIMES;
    }

    private static boolean insert(int key, int hash) {
        int idx = hash(key, hash);
        if (primaryArea[idx] < 0) {
            primaryArea[idx] = key;
            return true;
        } else {
            if (overflowIdx < overflowSize) {
                overflowArea[overflowIdx] = key;
                overflowIdx++;
                return true;
            } else {
                return false;
            }
        }
    }

    private static int hash(int key, int type) {
        switch (type) {
            case 0: return Math.floorMod(hash1(key, primarySize), primarySize);
            case 1: return Math.floorMod(hash2(key, primarySize), primarySize);
            case 2: return Math.floorMod(hash3(key, primarySize), primarySize);
            case 3: return Math.floorMod(hashBest(key, primarySize), primarySize);
            case 4: return Math.floorMod(hashMul(key, primarySize), primarySize);
            case 5: return Math.floorMod(hashDiv(key, primarySize), primarySize);
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
        String[] hashFunc = {"hash1", "hash2", "hash3", "hashBest", "hashMul", "hashDiv"};
        double[] successProbesCounter_Of = new double[6];
        double[] unsuccessProbeCounter_Of = new double[6];
        double[] totalInsertFailures = new double[6];

        for (int i = 0; i < NUM_RUNS; i++) {
            Set<Integer> keysSet = buildUniKeys(NUM_KEYS_Of); //the set of keys

            for (int h = 0; h < 6; h++) {
                Arrays.fill(primaryArea, -1);  //we use -1 standing for emptyness
                Arrays.fill(overflowArea, -1);

                double InsertionFailure = insertKeysFailures(keysSet, h);
                double successProbe = measureSuccessfulProbesWithOverflow(keysSet, h); //insert at the same time
                double unsuccessProbe = measureUnsuccessfulProbesWithOverflow(keysSet, h);

                successProbesCounter_Of[h] += successProbe;
                unsuccessProbeCounter_Of[h] += unsuccessProbe;
                totalInsertFailures[h] += InsertionFailure;
            }
        }

        System.out.printf("%-20s %-20s %-18s %-20s%n", "HashFunction", "SuccessProbes_OF", "UnscsProbes_OF", "InsertFailures");
        for (int m = 0; m < 6; m++) {
            System.out.printf("%-20s %-20.3f %-18.3f %-20.3f%n", hashFunc[m],
                                                                        successProbesCounter_Of[m]/NUM_RUNS, 
                                                                        unsuccessProbeCounter_Of[m]/NUM_RUNS, 
                                                                        totalInsertFailures[m]/NUM_RUNS);
        }
    }
}