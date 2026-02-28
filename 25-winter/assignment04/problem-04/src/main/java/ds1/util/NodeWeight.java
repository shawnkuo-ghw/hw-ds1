package ds1.util;

public class NodeWeight {
    int dest;
    float weight;
    NodeWeight(int dest, float weight) {
        this.dest = dest;
        this.weight = weight;
    }
    public int getDest() {
        return dest;
    }
    public float getWeight() {
        return weight;
    }
    // toString method
    @Override
    public String toString() {
        return "(" + dest + ", " + weight + ")";
    }

}