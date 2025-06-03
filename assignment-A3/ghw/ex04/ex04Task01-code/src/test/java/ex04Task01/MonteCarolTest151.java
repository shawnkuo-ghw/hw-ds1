package ex04Task01;
import ex04Task01.implementations.HashFunctions;
import ex04Task01.implementations.HashTable;
import ex04Task01.implementations.HashFunctions.*;
import ex04Task01.interfaces.Dictionary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class MonteCarolTest151 {
    
    static final int SIZE          = 151; // size of hash table
    static final int MAX_WIDTH     = 50;  // max length of lines of hash table when printed
    static final int UNIVERSE_KEYS = 200; // universe of keys: 0 ~ 299
    static final int ACTUAL_KEYS   = 100; // actual keys: 0 ~ 99
    static final int STRING_LENGTH = 10;  // length of string as the value in key/value pair
    Dictionary<Integer, String> htBest;
    Dictionary<Integer, String> htMul;
    Dictionary<Integer, String> htDiv;

    @BeforeEach
    public void init() {
        htBest = new HashTable<Integer, String >(SIZE, Integer.class, String.class, HashFunctions.hashBest);
        htMul  = new HashTable<Integer, String >(SIZE, Integer.class, String.class, HashFunctions.hashMul);
        htDiv  = new HashTable<Integer, String >(SIZE, Integer.class, String.class, HashFunctions.hashDiv);
        for ( int i = 0; i < ACTUAL_KEYS; i ++ ) {
            String randomStr = RandomString.generate(STRING_LENGTH);
            htBest.insert(i, randomStr);
            htMul.insert(i, randomStr);
            htDiv.insert(i, randomStr);
        }
    }

    @Test
    public void SuccessfulSearchTest() {
        System.out.println("Hash Table for hash function hashBest");
        System.out.println(htBest.toString(MAX_WIDTH));
        System.out.println("Hash Table for hash function hashMul");
        System.out.println(htMul.toString(MAX_WIDTH));
        System.out.println("Hash Table for hash function hashDiv");
        System.out.println(htDiv.toString(MAX_WIDTH));
        for ( int i = 0; i < ACTUAL_KEYS; i ++ ) {
            int probeNum1 = ((HashTable<Integer, String>) htBest).searchCount(i);
            int probeNum2 = ((HashTable<Integer, String>) htMul).searchCount(i);
            int probeNum3 = ((HashTable<Integer, String>) htDiv).searchCount(i);
            String prompt = "";
            prompt += "Successful   search for key: " + String.format("%-3d", i) + ", ";
            prompt += "probe number for hashBest: " + String.format("%-3d", probeNum1) + ", ";
            prompt += "probe number for hashMul : " + String.format("%-3d", probeNum2) + ", ";
            prompt += "probe number for hashDiv : " + String.format("%-3d", probeNum3);
            System.out.println(prompt);
        }
    }

    @Test
    public void UnsuccessfulSearchTest() {
        for ( int i = ACTUAL_KEYS; i < UNIVERSE_KEYS; i ++ ) {
            assertEquals(-1, htBest.search(i));
            assertEquals(-1, htMul.search(i));
            assertEquals(-1, htDiv.search(i));
            int probeNum1 = ((HashTable<Integer, String>) htBest).searchCount(i);
            int probeNum2 = ((HashTable<Integer, String>) htMul).searchCount(i);
            int probeNum3 = ((HashTable<Integer, String>) htDiv).searchCount(i);
            String prompt = "";
            prompt += "Unsuccessful search for key: " + String.format("%-3d", i) + ", ";
            prompt += "probe number for hashBest: " + String.format("%-3d", probeNum1) + ", ";
            prompt += "probe number for hashMul : " + String.format("%-3d", probeNum2) + ", ";
            prompt += "probe number for hashDiv : " + String.format("%-3d", probeNum3);
            System.out.println(prompt);
        }
    }
}
