package ds1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// Now we include Edges to the GraphAL class
// to incorporate the weight of the edges
class Edge {
    private int src;
    private int dest;
    private int weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    int getSrc() {
        return src;
    }

    int getDest() {
        return dest;
    }

    int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(" + src + "," + dest + "," + weight + ")";
    }
}

// Now the AdjacencyList class includes the weight of the edges
// and the source of the edges
class AdjacencyList {
    private Sequence<Edge> elems;
    private int source;

    AdjacencyList(int source) {
        this.source = source;
        elems = new ListoverLinkedList<Edge>();
    }

    void add(int dest, int weight) {
        elems.insertFront(new Edge(source, dest, weight));
    }

    Sequence<Edge> getList() {
        return elems;
    }

    int size() {
        return elems.length();
    }
}

class DijkstraResult {
    Map<Integer, Integer> edgeTo;
    Map<Integer, Integer> distTo;

    DijkstraResult(Map<Integer, Integer> edgeTo, Map<Integer, Integer> distTo) {
        this.edgeTo = edgeTo;
        this.distTo = distTo;
    }
}

public class WeightedGraphAL {
    // implememts a weighted directed or indirected  graph using an adjacency list
    // each vertex has a list of adjacent vertices
    // the list is implemented using a linked list
    // the graph is implemented using an array of linked lists

    private int numVertices;
    private AdjacencyList[] adjLists;
    private Boolean[] visited;
    private Boolean undirected = false;

    public WeightedGraphAL(int numVertices) {
        this.numVertices = numVertices;
        adjLists = new AdjacencyList[numVertices];
        visited = new Boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjLists[i] = new AdjacencyList(i);
            visited[i] = false;
        }
    }

    public void setUndirected() {
        undirected = true;
    }

    // add an edge to the graph without weight
    // we assume a weight of 0
    public void addEdge(int src, int dest) {
        adjLists[src].add(dest, 0);
        if (undirected)
            adjLists[dest].add(src, 0);

    }

    // add an edge to the graph with weight
    public void addEdge(int src, int dest, int weight) {
        adjLists[src].add(dest, weight);
        if (undirected)
            adjLists[dest].add(src, weight);
    }

    public Sequence<Edge> neighbors(int vertex) {
        return adjLists[vertex].getList();
    }

    public void print() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + ": ");
            SequenceIterator<Edge> iter = neighbors(i).getIterator();
            while (iter.hasNext()) {
                Edge edge = iter.next();
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }
    
    // implement dijkstra's algorithm using a priority queue
    // use a map vDist to store the distance of each vertex from the source
    // use a map edgeTo to store the edge that connects each vertex to the source
    // use a priority queue Q  to store the vertices to be processed
    // use a map to store the position of each vertex in the priority queue
    public DijkstraResult dijkstra(int source) {
        Map<Integer, Integer> vDist = new HashMap<Integer, Integer>();
        Map<Integer, Integer> edgeTo = new HashMap<Integer, Integer>();
        Map<Integer, Integer> position = new HashMap<Integer, Integer>();
        vDist.put(source, 0);
        for (int i = 0; i < numVertices; i++) {
            if (i != source) {
                vDist.put(i, Integer.MAX_VALUE);
            }
        }
        position.put(source, 0);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(numVertices, (v1, v2) -> vDist.get(v1) - vDist.get(v2));
        queue.add(source);

        // Continue the implementation here
        for (int i = 0; i < numVertices; i++) {
            if (i != source) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visited[u] = true;
            int distU = vDist.get(u);
            if (distU != Integer.MAX_VALUE) {
                SequenceIterator<Edge> iter = neighbors(u).getIterator();
                while (iter.hasNext()) {
                    Edge edge = iter.next();
                    int v = edge.getDest();
                    int weight = edge.getWeight(); 
                    int oldDist = vDist.get(v);         
                    int newDist = distU + weight;
                    if (newDist < oldDist) {
                        vDist.put(v, newDist);
                        edgeTo.put(v, u);
                        queue.add(v);
                    }
                }
            }
        }
        
        return new DijkstraResult(edgeTo, vDist);
    }

    // implement Kruskal's algorithm using a priority queue to get the edges sorted by weight
    // use disjoint sets to check if adding an edge creates a cycle
    public Set<Edge> kruskal(WeightedGraphAL g) {
        Set<Edge> mst = new HashSet<Edge>();
        // add all edges to a priority queue
        Set<Edge> edges = new HashSet<Edge>();
        for (int i = 0; i < numVertices; i++) {
            SequenceIterator<Edge> iter = neighbors(i).getIterator();
            while (iter.hasNext()) {
                Edge edge = iter.next();
                edges.add(edge);
            }
        }
        // create a priority queue to store the edges sorted by weight
        PriorityQueue<Edge> edgesPriorityQueue = new PriorityQueue<Edge>(edges.size(), (e1, e2) -> e1.getWeight() - e2.getWeight());
        for (Edge edge : edges) {
            edgesPriorityQueue.add(edge);
        }
        DisjointSet disjointSet = new DisjointSetArray<>(numVertices);
        // Continue the implementation here
        for (int i = 0; i < numVertices; i++) {
            disjointSet.makeSet(i);
        }
        while (!edgesPriorityQueue.isEmpty() && mst.size() < numVertices - 1) {
            Edge edge = edgesPriorityQueue.poll();
            int src = edge.getSrc();
            int dest = edge.getDest();
            int root1 = disjointSet.find(src);
            int root2 = disjointSet.find(dest);
            if (root1 != root2) {
                mst.add(edge);
                disjointSet.union(src, dest);
            }
        }
        return mst;
    }

    public Set<Edge> kruskalWithOneMandatoryEdge(WeightedGraphAL g, Edge mandatoryEdge) {
        Set<Edge> mst = new HashSet<Edge>();
        // add all edges to a priority queue
        Set<Edge> edges = new HashSet<Edge>();
        for (int i = 0; i < numVertices; i++) {
            SequenceIterator<Edge> iter = neighbors(i).getIterator();
            while (iter.hasNext()) {
                Edge edge = iter.next();
                edges.add(edge);
            }
        }
        // create a priority queue to store the edges sorted by weight
        PriorityQueue<Edge> edgesPriorityQueue = new PriorityQueue<Edge>(edges.size(), (e1, e2) -> e1.getWeight() - e2.getWeight());
        for (Edge edge : edges) {
            edgesPriorityQueue.add(edge);
        }
        DisjointSet disjointSet = new DisjointSetArray<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            disjointSet.makeSet(i);
        }

        if (mandatoryEdge != null) {
            int src = mandatoryEdge.getSrc();
            int dest = mandatoryEdge.getDest();
            mst.add(mandatoryEdge);
            disjointSet.union(src, dest);
        }

        while (!edgesPriorityQueue.isEmpty() && mst.size() < numVertices - 1) {
            Edge edge = edgesPriorityQueue.poll();
            int src = edge.getSrc();
            int dest = edge.getDest();

            boolean isMandatory = false;
            if (mandatoryEdge != null) {
                int mSrc = mandatoryEdge.getSrc();
                int mDest = mandatoryEdge.getDest();
                int mWeight = mandatoryEdge.getWeight();
                if (edge.getWeight() == mWeight && ((src == mSrc && dest == mDest) || (src == mDest && dest == mSrc)))
                    isMandatory = true;
            }

            if (!isMandatory) {
                int root1 = disjointSet.find(src);
                int root2 = disjointSet.find(dest);
                if (root1 != root2) {
                    mst.add(edge);
                    disjointSet.union(src, dest);
                }
            }
        }
        return mst;
    }
}
