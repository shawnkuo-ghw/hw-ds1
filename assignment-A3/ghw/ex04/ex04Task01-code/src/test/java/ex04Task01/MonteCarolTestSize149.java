package ex04Task01;
import ex04Task01.implementations.HashFunctions;
import ex04Task01.implementations.HashTableLinearProbing;
import ex04Task01.interfaces.Dictionary;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

public class MonteCarolTestSize149 {

    static final int SIZE          = 149;  // size of hash table
    static final int UNIVERSE_KEYS = 1000; // range of universe of keys
    static final int ACTUAL_KEYS   = 100;  // range of actual keys
    static final int MAX_WIDTH     = 50;   // max length of lines of hash table when printed
    static final int STRING_LENGTH = 10;   // length of string as the value in key/value pair
    int[] keysArray;                       // the array of the universe of keys
    Dictionary<Integer, String> ht1;
    Dictionary<Integer, String> ht2;
    Dictionary<Integer, String> ht3;

    @BeforeEach
    public void init() {

        // initialize hash tables w.r.t. different hash functions
        ht1 = new HashTableLinearProbing<Integer, String>(SIZE, Integer.class, String.class, HashFunctions.hash1);
        ht2 = new HashTableLinearProbing<Integer, String>(SIZE, Integer.class, String.class, HashFunctions.hash2);
        ht3 = new HashTableLinearProbing<Integer, String>(SIZE, Integer.class, String.class, HashFunctions.hash3);
        
        // generate an array with shuffled integers
        keysArray = new int[UNIVERSE_KEYS];
        for ( int i = 0; i < UNIVERSE_KEYS; i ++ ) { keysArray[i] = i; }
        RandomTools.shuffleArray(keysArray);
        
        // insert key-value pairs into hash tables
        for ( int i = 0; i < ACTUAL_KEYS; i ++ ) {
            String randomStr = RandomTools.generateRandomString(STRING_LENGTH);
            ht1.insert(keysArray[i], randomStr);
            ht2.insert(keysArray[i], randomStr);
            ht3.insert(keysArray[i], randomStr);
        }
    }

    @Test
    public void MonteCarolTest() {

        // print the hash tables
        System.out.println("Hash Table for hash function hash1:");
        System.out.println(ht1.toString(MAX_WIDTH));
        System.out.println("Hash Table for hash function hash2:");
        System.out.println(ht2.toString(MAX_WIDTH));
        System.out.println("Hash Table for hash function hash3:");
        System.out.println(ht3.toString(MAX_WIDTH));

        // conduct successful searches
        int successSum1 = 0;
        int successSum2 = 0;
        int successSum3 = 0;
        for ( int i = 0; i < ACTUAL_KEYS; i ++ ) {
            int keyToSearch = keysArray[i];
            int probeNum1 = ((HashTableLinearProbing<Integer, String>) ht1).searchCount(keyToSearch);
            int probeNum2 = ((HashTableLinearProbing<Integer, String>) ht2).searchCount(keyToSearch);
            int probeNum3 = ((HashTableLinearProbing<Integer, String>) ht3).searchCount(keyToSearch);
            successSum1 += probeNum1;
            successSum2 += probeNum2;
            successSum3 += probeNum3;
            String prompt = "";
            prompt += "Successful   search for key: " + String.format("%-3d", keyToSearch) + ",   ";
            prompt += "probe number for hash1: " + String.format("%-3d", probeNum1) + ",   ";
            prompt += "probe number for hash2: " + String.format("%-3d", probeNum2) + ",   ";
            prompt += "probe number for hash3: " + String.format("%-3d", probeNum3);
            System.out.println(prompt);
        }

        // conduct unsuccessful searches
        int unsuccessSum1 = 0;
        int unsuccessSum2 = 0;
        int unsuccessSum3 = 0;
        for ( int i = ACTUAL_KEYS; i < UNIVERSE_KEYS; i ++ ) {
            int keyToSearch = keysArray[i];
            assertEquals(-1, ht1.search(keyToSearch));
            assertEquals(-1, ht2.search(keyToSearch));
            assertEquals(-1, ht3.search(keyToSearch));
            int probeNum1 = ((HashTableLinearProbing<Integer, String>) ht1).searchCount(keyToSearch);
            int probeNum2 = ((HashTableLinearProbing<Integer, String>) ht2).searchCount(keyToSearch);
            int probeNum3 = ((HashTableLinearProbing<Integer, String>) ht3).searchCount(keyToSearch);
            unsuccessSum1 += probeNum1;
            unsuccessSum2 += probeNum2;
            unsuccessSum3 += probeNum3;
            String prompt = "";
            prompt += "Unsuccessful search for key: " + String.format("%-3d", keyToSearch) + ",   ";
            prompt += "probe number for hash1: " + String.format("%-3d", probeNum1) + ",   ";
            prompt += "probe number for hash2: " + String.format("%-3d", probeNum2) + ",   ";
            prompt += "probe number for hash3: " + String.format("%-3d", probeNum3);
            System.out.println(prompt);
        }

        System.out.println();
        System.out.println("Average probe number for hash1 in successful searches: " + String.format("%-6.3f", (double) successSum1 / ACTUAL_KEYS));
        System.out.println("Average probe number for hash2 in successful searches: " + String.format("%-6.3f", (double) successSum2 / ACTUAL_KEYS));
        System.out.println("Average probe number for hash3 in successful searches: " + String.format("%-6.3f", (double) successSum3 / ACTUAL_KEYS));

        System.out.println();
        System.out.println("Average probe number for hash1 in unsuccessful searches: " + String.format("%-6.3f", (double) unsuccessSum1 / (UNIVERSE_KEYS - ACTUAL_KEYS)) );
        System.out.println("Average probe number for hash2 in unsuccessful searches: " + String.format("%-6.3f", (double) unsuccessSum2 / (UNIVERSE_KEYS - ACTUAL_KEYS)) );
        System.out.println("Average probe number for hash3 in unsuccessful searches: " + String.format("%-6.3f", (double) unsuccessSum3 / (UNIVERSE_KEYS - ACTUAL_KEYS)) );
        
        // compare largest primary cluster
        System.out.println();
        System.out.println("Largest primary cluster of hash1: " + ht1.largestPrimaryCluster());
        System.out.println("Largest primary cluster of hash2: " + ht2.largestPrimaryCluster());
        System.out.println("Largest primary cluster of hash3: " + ht3.largestPrimaryCluster());
    }
}