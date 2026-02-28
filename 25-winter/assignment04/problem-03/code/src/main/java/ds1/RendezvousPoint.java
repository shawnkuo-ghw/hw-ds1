package ds1;

import java.util.Map;

public class RendezvousPoint {

    private final WeightedGraphAL graph;

    public RendezvousPoint(WeightedGraphAL graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph must not be null");
        }
        this.graph = graph;
    }

    public Integer findRendezvousPoint(Integer n1, Integer n2) {
        if (n1 == null || n2 == null) {
            throw new IllegalArgumentException("n1 and n2 can not be null");
        }
        if (graph.vertexCount() == 0) {
            throw new IllegalStateException("graph can not be empty");
        }
        if (n1 < 0 || n1 >= graph.vertexCount() || n2 < 0 || n2 >= graph.vertexCount()) {
            throw new IllegalArgumentException("n1 and n2 are not valid index");
        }

        Map<Integer, Integer> distTo1 = graph.dijkstra(n1).distTo;
        Map<Integer, Integer> distTo2 = graph.dijkstra(n2).distTo;
        int bestDist = Integer.MAX_VALUE;
        Integer candidate = null;
        
        for (int v = 0; v < graph.vertexCount(); v++) {
            int d1 = distTo1.get(v);
            int d2 = distTo2.get(v);
            int m = Math.max(d1, d2);
            if (m < bestDist) {
                bestDist = m;
                candidate = v;
            }
        }
        return candidate;
    }
}


