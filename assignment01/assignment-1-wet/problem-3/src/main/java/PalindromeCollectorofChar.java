
public interface PalindromeCollectorofChar {
    /*
     * Precondition: 
     * 1. ch should be lower case and be in [a,z] 
     * 2. collector is not full
     * Postcondition: 
     * 1. the length of new collector increase by 1
     * 2. the first element of new collector = ch
     * 3. the new collector ranges from the second element to the end equals original collector
     */
    public void addFirst(char ch);

    /*
     * Precondition: 
     * 1. ch should be lower case and be in [a,z] 
     * 2. collector is not full
     * Postcondition: 
     * 1. the length of new collector increase by 1
     * 2. the last element of new collector = ch
     * 3. the new collector ranges from the first element to the previous of last element equals original collector
     */
    public void addLast(char ch);

    /*
     * Precondition: the collector is not empty
     * Postcondition: 
     * 1. the length of new collector decrease by 1
     * 2. the first element of new collector = the second element of original collector
     * 3. the new collector equals the original collector range from the second elements to the end
     * 4. return the character that is the first element of collector
     */
    public char removeFirst();

    /*
     * Precondition: the collector is not empty
     * Postcondition: 
     * 1. the length of new collector decrease by 1
     * 2. the last element of new collector = the previous of last elemente of original collector
     * 3. the new collector equals the original collector range from the second elements to the end
     * 4. return the character that is the last element of collector
     */
    public char removeLast();

    /*
     * Precondition: None
     * Postcondition:
     * 1. return true iff the collector reads the same forward and backward
     * 2. the collector is not modified
     */
    public boolean isPalindrome();

    /*
     * Precondition: None
     * Postcondition:
     * 1. return true iff size() == 0
     * 2. the collector is not modified
     */
    public boolean isEmpty();

    /*
     * Precondition: None
     * Postcondition:
     * 1. return the number of characters in the collector
     * 2. the collector is not modified
     */
    public int size();
}
