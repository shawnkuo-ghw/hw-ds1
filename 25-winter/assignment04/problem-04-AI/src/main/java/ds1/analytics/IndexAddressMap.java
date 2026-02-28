package ds1.analytics;
import ds1.util.*;

public class IndexAddressMap
{
    private HashTable addressToIndex;
    private String[] indexToAddress;

    public IndexAddressMap() {
        addressToIndex = new HashTable();
        indexToAddress = new String[16];
    }
    
    public void insert(Integer index, String address) {
        if (index == null || address == null) {
            throw new IllegalArgumentException("index and address must not be null");
        }
        if (index < 0) {
            throw new IllegalArgumentException("index must be non-negative");
        }
        if (index >= indexToAddress.length) {
            int newCap = indexToAddress.length * 2 + 1;
            if (newCap <= index) newCap = index + 1;
            String[] next = new String[newCap];
            for (int i = 0; i < indexToAddress.length; i++) {
                next[i] = indexToAddress[i];
            }
            indexToAddress = next;
        }
        indexToAddress[index] = address;
        addressToIndex.put(address, index);
    }

    public String getAddress(Integer index) {
        if (index == null || index < 0 || index >= indexToAddress.length) return null;
        return indexToAddress[index];
    }

    public Integer getIndex(String address) {
        if (address == null) return null;
        return addressToIndex.get(address);
    }
}