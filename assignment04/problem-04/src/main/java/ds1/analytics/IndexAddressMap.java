package ds1.analytics;
import ds1.util.*;

class IndexAddressPair implements Comparable<IndexAddressPair>
{
    private Integer index;
    private String address;
    IndexAddressPair(String newAddress, Integer newIndex) {
        index = newIndex;
        address = newAddress;
    }
    public String getAddress() { return this.address; }
    public Integer getIndex() { return this.index; }
    @Override
    public int compareTo(IndexAddressPair o) {
        // compare address by index or search address by index
        if (o.index != null) return Integer.compare(this.index, o.index);
        // index and address are both null
        else throw new IllegalArgumentException("Illegal Argument Error");
    }
}

public class IndexAddressMap
{
    AVLTree<IndexAddressPair> map;

    public IndexAddressMap() {
        map = new AVLTree<IndexAddressPair>();
    }
    
    public void insert(Integer index, String address) {
        map.insert(
            new IndexAddressPair(address, index)
        );
    }

    // Get address by index
    // O(log V)
    public String getAddress(Integer index) {
        IndexAddressPair pair = map.searchGet(
            new IndexAddressPair(null, index) // find address by index
        );
        if (pair == null ) return null;
        else return pair.getAddress();
    }

    // Get index by address
    // O(V)
    public Integer getIndex(String address) {
        Sequence<IndexAddressPair> pairs = map.inorder();
        Integer index = null;
        SequenceIterator<IndexAddressPair> itr = pairs.getIterator();
        boolean hasFound = false;
        while ( !hasFound && itr.hasNext() ) {
            IndexAddressPair currPair = itr.next();
            String currAddr = currPair.getAddress();
            if ( address.compareTo(currAddr) == 0 ) {
                index = currPair.getIndex();
                hasFound = true;
            }
        }
        return index;
    }
}