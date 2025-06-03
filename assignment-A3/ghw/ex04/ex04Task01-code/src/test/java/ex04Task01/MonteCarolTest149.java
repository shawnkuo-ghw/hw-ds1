package ex04Task01;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.*;

import ex04Task01.implementations.HashFunctions;
import ex04Task01.implementations.HashTable;
import ex04Task01.implementations.HashFunctions.*;
import ex04Task01.interfaces.Dictionary;

public class MonteCarolTest149 {

    static final int SIZE          = 149; // size of hash table
    static final int MAX_WIDTH     = 50;  // max length of lines of hash table when printed
    static final int UNIVERSE_KEYS = 200; // universe of keys: 0 ~ 299
    static final int ACTUAL_KEYS   = 100; // actual keys: 0 ~ 99
    static final int STRING_LENGTH = 10;  // length of string as the value in key/value pair
    Dictionary<Integer, String> ht1;
    Dictionary<Integer, String> ht2;
    Dictionary<Integer, String> ht3;

    @BeforeEach
    public void init() {
        ht1 = new HashTable<Integer, String>(SIZE, Integer.class, String.class, HashFunctions.hash1);
        ht2 = new HashTable<Integer, String>(SIZE, Integer.class, String.class, HashFunctions.hash2);
        ht3 = new HashTable<Integer, String>(SIZE, Integer.class, String.class, HashFunctions.hash3);
        for ( int i = 0; i < ACTUAL_KEYS; i ++ ) {
            String randomStr = RandomString.generate(STRING_LENGTH);
            ht1.insert(i, randomStr);
            ht2.insert(i, randomStr);
            ht3.insert(i, randomStr);
        }
    }

    @Test
    public void SuccessfulSearchTest() {
        System.out.println("Hash Table for hash function hash1");
        System.out.println(ht1.toString(MAX_WIDTH));
        System.out.println("Hash Table for hash function hash2");
        System.out.println(ht2.toString(MAX_WIDTH));
        System.out.println("Hash Table for hash function hash3");
        System.out.println(ht3.toString(MAX_WIDTH));
        for ( int i = 0; i < ACTUAL_KEYS; i ++ ) {
            int probeNum1 = ((HashTable<Integer, String>) ht1).searchCount(i);
            int probeNum2 = ((HashTable<Integer, String>) ht2).searchCount(i);
            int probeNum3 = ((HashTable<Integer, String>) ht3).searchCount(i);
            String prompt = "";
            prompt += "Successful search for key: " + String.format("%-3d", i) + ", ";
            prompt += "probe number for hash1: " + String.format("%-3d", probeNum1) + ", ";
            prompt += "probe number for hash2: " + String.format("%-3d", probeNum2) + ", ";
            prompt += "probe number for hash3: " + String.format("%-3d", probeNum3);
            System.out.println(prompt);
        }
    }

    @Test
    public void UnsuccessfulSearchTest() {
        for ( int i = ACTUAL_KEYS; i < UNIVERSE_KEYS; i ++ ) {
            assertEquals(-1, ht1.search(i));
            assertEquals(-1, ht2.search(i));
            assertEquals(-1, ht3.search(i));
            int probeNum1 = ((HashTable<Integer, String>) ht1).searchCount(i);
            int probeNum2 = ((HashTable<Integer, String>) ht2).searchCount(i);
            int probeNum3 = ((HashTable<Integer, String>) ht3).searchCount(i);
            String prompt = "";
            prompt += "Unsuccessful search for key: " + String.format("%-3d", i) + ", ";
            prompt += "probe number for hash1: " + String.format("%-3d", probeNum1) + ", ";
            prompt += "probe number for hash2: " + String.format("%-3d", probeNum2) + ", ";
            prompt += "probe number for hash3: " + String.format("%-3d", probeNum3);
            System.out.println(prompt);
        }
    }

    @Test
    public void largestPrimaryClusterTest() {
        
    }
}
