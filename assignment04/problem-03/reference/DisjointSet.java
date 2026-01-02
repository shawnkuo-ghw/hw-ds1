package ds1;

public interface DisjointSet<K> {
    void makeSet(K elem);
    int find(K elem);
    void union(K elem1, K elem2);
}
