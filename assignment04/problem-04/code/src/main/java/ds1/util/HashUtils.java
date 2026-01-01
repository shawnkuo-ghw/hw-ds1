package ds1.util;
 
public class HashUtils {
    public static String hash(String input) {
        if (input == null) return "0";
        // Simulating a cryptographic hash using standard Java hashCode
        int h = 0;
        for (char c : input.toCharArray()) {
            h = 31 * h + c;
        }
        return Integer.toHexString(h);
    }
}

