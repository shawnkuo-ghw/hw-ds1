package ds1;

public class AddressBalancePair
{
    public final String address;
    public final int balance;

    /*
     * Constructor
     */
    public AddressBalancePair(String address, int balance) {
        this.address = address;
        this.balance = balance;
    }

    /*
     * Getters
     */
    public String getAddress() {
        return address;
    }

    public int getBalance() {
        return balance;
    }

    /*
     * Operations
     */
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

    /*
     * toString for debugging
     */
    @Override
    public String toString() {
        return "{" + address + ": " + balance + "}";
    }
}