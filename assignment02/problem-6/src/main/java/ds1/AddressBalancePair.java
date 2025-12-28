package ds1;
/** 
 * AddressBalancePair.java
 * This class represents a pair of address and its corresponding balance.
 */

public class AddressBalancePair implements Comparable<AddressBalancePair> {
    public final String address;
    public final int balance;

    public AddressBalancePair(String address, int balance) {
        this.address = address;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AddressBalancePair that = (AddressBalancePair) obj;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }

    // toString for debugging
    @Override
    public String toString() {
        return "{" + address + ": " + balance + "}";
    }

    @Override
    public int compareTo(AddressBalancePair o) {
        return this.address.compareTo(o.address);
    }

}
