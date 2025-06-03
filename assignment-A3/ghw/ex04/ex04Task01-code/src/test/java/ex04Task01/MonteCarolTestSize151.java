package ex04Task01;
import ex04Task01.implementations.HashFunctions;
import ex04Task01.implementations.HashTable;
import ex04Task01.interfaces.Dictionary;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

public class MonteCarolTestSize151 {
    
    static final int SIZE          = 151;  // size of hash table
    static final int UNIVERSE_KEYS = 1000; // range of universe of keys
    static final int ACTUAL_KEYS   = 100;  // range of actual keys
    static final int MAX_WIDTH     = 50;   // max length of lines of hash table when printed
    static final int STRING_LENGTH = 10;   // length of string as the value in key/value pair
    int[] keysArray;                       // the array of the universe of keys
    Dictionary<Integer, String> htBest;
    Dictionary<Integer, String> htMul;
    Dictionary<Integer, String> htDiv;

    @BeforeEach
    public void init() {

        // initialize hash tables w.r.t. different hash functions
        htBest = new HashTable<Integer, String >(SIZE, Integer.class, String.class, HashFunctions.hashBest);
        htMul  = new HashTable<Integer, String >(SIZE, Integer.class, String.class, HashFunctions.hashMul);
        htDiv  = new HashTable<Integer, String >(SIZE, Integer.class, String.class, HashFunctions.hashDiv);

        // generate an array with shuffled integers
        keysArray = new int[UNIVERSE_KEYS];
        for ( int i = 0; i < UNIVERSE_KEYS; i ++ ) { keysArray[i] = i; }
        RandomTools.shuffleArray(keysArray); 

        // insert key/value pairs into hash tables
        for ( int i = 0; i < ACTUAL_KEYS; i ++ ) {
            String randomStr = RandomTools.generateRandomString(STRING_LENGTH);
            htBest.insert(keysArray[i], randomStr);
            htMul.insert(keysArray[i], randomStr);
            htDiv.insert(keysArray[i], randomStr);
        }
    }

    @Test
    public void MonteCarolTest() {
        
        // print the hash tables
        System.out.println("Hash Table for hash function hashBest");
        System.out.println(htBest.toString(MAX_WIDTH));
        System.out.println("Hash Table for hash function hashMul");
        System.out.println(htMul.toString(MAX_WIDTH));
        System.out.println("Hash Table for hash function hashDiv");
        System.out.println(htDiv.toString(MAX_WIDTH));
        
        // conduct successful searches
        int successSum1 = 0;
        int successSum2 = 0;
        int successSum3 = 0;    
        for ( int i = 0; i < ACTUAL_KEYS; i ++ ) {
            int keyToSearch = keysArray[i];            
            int probeNum1 = ((HashTable<Integer, String>) htBest).searchCount(keyToSearch);
            int probeNum2 = ((HashTable<Integer, String>) htMul).searchCount(keyToSearch);
            int probeNum3 = ((HashTable<Integer, String>) htDiv).searchCount(keyToSearch);
            successSum1 += probeNum1;
            successSum2 += probeNum2;
            successSum3 += probeNum3;            
            String prompt = "";
            prompt += "Successful   search for key: " + String.format("%-3d", keyToSearch) + ",   ";
            prompt += "probe number for hashBest: " + String.format("%-3d", probeNum1) + ",   ";
            prompt += "probe number for hashMul: " + String.format("%-3d", probeNum2) + ",   ";
            prompt += "probe number for hashDiv: " + String.format("%-3d", probeNum3);
            System.out.println(prompt);
        }
        
        // conduct unsuccessful searches
        int unsuccessSum1 = 0;
        int unsuccessSum2 = 0;
        int unsuccessSum3 = 0;
        for ( int i = ACTUAL_KEYS; i < UNIVERSE_KEYS; i ++ ) {
            int keyToSearch = keysArray[i];
            assertEquals(-1, htBest.search(keyToSearch));
            assertEquals(-1, htMul.search(keyToSearch));
            assertEquals(-1, htDiv.search(keyToSearch));
            int probeNum1 = ((HashTable<Integer, String>) htBest).searchCount(keyToSearch);
            int probeNum2 = ((HashTable<Integer, String>) htMul).searchCount(keyToSearch);
            int probeNum3 = ((HashTable<Integer, String>) htDiv).searchCount(keyToSearch);
            unsuccessSum1 += probeNum1;
            unsuccessSum2 += probeNum2;
            unsuccessSum3 += probeNum3;
            String prompt = "";
            prompt += "Unsuccessful search for key: " + String.format("%-3d", keyToSearch) + ",   ";
            prompt += "probe number for hashBest: " + String.format("%-3d", probeNum1) + ",   ";
            prompt += "probe number for hashMul: " + String.format("%-3d", probeNum2) + ",   ";
            prompt += "probe number for hashDiv: " + String.format("%-3d", probeNum3);
            System.out.println(prompt);
        }

        System.out.println();
        System.out.println("Average probe number for hashBest in successful searches: " + String.format("%-6.3f", (double) successSum1 / ACTUAL_KEYS));
        System.out.println("Average probe number for hashMul  in successful searches: " + String.format("%-6.3f", (double) successSum2 / ACTUAL_KEYS));
        System.out.println("Average probe number for hashDiv  in successful searches: " + String.format("%-6.3f", (double) successSum3 / ACTUAL_KEYS));

        System.out.println();
        System.out.println("Average probe number for hashBest in unsuccessful searches: " + String.format("%-6.3f", (double) unsuccessSum1 / (UNIVERSE_KEYS - ACTUAL_KEYS)) );
        System.out.println("Average probe number for hashMul  in unsuccessful searches: " + String.format("%-6.3f", (double) unsuccessSum2 / (UNIVERSE_KEYS - ACTUAL_KEYS)) );
        System.out.println("Average probe number for hashDiv  in unsuccessful searches: " + String.format("%-6.3f", (double) unsuccessSum3 / (UNIVERSE_KEYS - ACTUAL_KEYS)) );

        // compare largest primary cluster
        System.out.println();
        System.out.println("Largest primary cluster of hashBest: " + htBest.largestPrimaryCluster());
        System.out.println("Largest primary cluster of hashMul : " + htMul.largestPrimaryCluster());
        System.out.println("Largest primary cluster of hashDiv : " + htDiv.largestPrimaryCluster());
    }
}