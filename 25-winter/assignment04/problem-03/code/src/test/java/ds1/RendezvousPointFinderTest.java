package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RendezvousPointFinderTest {

    @Test
    void findRendezvousPointPositiveTest() {
        WeightedGraphAL graph = new WeightedGraphAL(5);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 1);
        graph.addEdge(0, 4, 10);
        graph.addEdge(1, 4, 10);

        RendezvousPoint rgraph = new RendezvousPoint(graph);
        //the input are n1 and n2
        Integer r = rgraph.findRendezvousPoint(0, 1);
        assertEquals(2, r);
    }

    @Test
    void findRendezvousPointNegativeTest3() {
        WeightedGraphAL graph = new WeightedGraphAL(5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 3);
        RendezvousPoint rgraph = new RendezvousPoint(graph);
        //the input are n1 and n2
        Integer r = rgraph.findRendezvousPoint(0, 2);
        assertEquals(2, r);
    }

    @Test
    void findRendezvousPointNegativeTest1() {
        WeightedGraphAL graph = new WeightedGraphAL(1);
        RendezvousPoint rgraph = new RendezvousPoint(graph);
        assertThrows(IllegalArgumentException.class, () -> rgraph.findRendezvousPoint(null, 0));
        assertThrows(IllegalArgumentException.class, () -> rgraph.findRendezvousPoint(2, 0));
        assertThrows(IllegalArgumentException.class, () -> rgraph.findRendezvousPoint(-1, 0));
    }

    @Test
    void findRendezvousPointNegativeTest2() {
        WeightedGraphAL graph = new WeightedGraphAL(0);
        RendezvousPoint rgraph = new RendezvousPoint(graph);
        assertThrows(IllegalStateException.class, () -> rgraph.findRendezvousPoint(0, 0));
    }
}


