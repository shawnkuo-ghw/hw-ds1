package ds1;
import java.util.Set;

public class problem1 {
    public static final int numberOfCities = 8;

    private static int cityToIndex(char ch) {
        return (int) (ch - 'A');
    }

    //build the graph
    public static WeightedGraphAL graphOfCities() {
        WeightedGraphAL graph = new WeightedGraphAL(numberOfCities);
        graph.setUndirected();
        graph.addEdge(cityToIndex('A'), cityToIndex('B'), 10);
        graph.addEdge(cityToIndex('A'), cityToIndex('C'), 5);
        graph.addEdge(cityToIndex('B'), cityToIndex('C'), 12);
        graph.addEdge(cityToIndex('B'), cityToIndex('D'), 4);
        graph.addEdge(cityToIndex('C'), cityToIndex('D'), 8);
        graph.addEdge(cityToIndex('C'), cityToIndex('E'), 3);
        graph.addEdge(cityToIndex('D'), cityToIndex('E'), 15);
        graph.addEdge(cityToIndex('D'), cityToIndex('F'), 6);
        graph.addEdge(cityToIndex('E'), cityToIndex('F'), 8);
        graph.addEdge(cityToIndex('E'), cityToIndex('G'), 7);
        graph.addEdge(cityToIndex('F'), cityToIndex('G'), 9);
        graph.addEdge(cityToIndex('F'), cityToIndex('H'), 11);
        graph.addEdge(cityToIndex('G'), cityToIndex('H'), 2);
        return graph;
    }

    //problem 1.1
    public static Set<Edge> part1() {
        WeightedGraphAL graph = graphOfCities();
        return graph.kruskal(graph);
    }

    //problem 1.2
    public static Set<Edge> part2() {
        WeightedGraphAL graph = graphOfCities();
        Edge mandatoryEdge = new Edge(cityToIndex('B'), cityToIndex('C'), 12);
        return graph.kruskalWithOneMandatoryEdge(graph, mandatoryEdge);
    }

    //the following functions are used to test
    public static int cost(Set<Edge> edges) {
        int cost = 0;
        for (Edge edge : edges) {
            cost += edge.getWeight();
        }
        return cost;
    }

    public static boolean containsEdge(Set<Edge> edges, char a, char b, int targetWeight) {
        int u = cityToIndex(a);
        int v = cityToIndex(b);
        for (Edge edge : edges) {
            if (edge.getWeight() == targetWeight) {
                int uFromEdge = edge.getSrc();
                int vToEdge = edge.getDest();
                if ((uFromEdge == u && vToEdge == v) || (uFromEdge == v && vToEdge == u)) {
                    return true;
                }
            }
        }
        return false;
    }
}
