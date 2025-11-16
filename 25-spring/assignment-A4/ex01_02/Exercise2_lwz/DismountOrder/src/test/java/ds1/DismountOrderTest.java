package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DismountOrderTest {
    
    @Test
    public void testDismountingSequence() {
        // Create a graph with component dependencies
        Graph<String> g = new Graph<>();

        g.addVertex("Motherboard");
        g.addVertex("CPU");
        g.addVertex("RAM");
        g.addVertex("Power Supply");
        g.addVertex("GPU");
        g.addVertex("Case Cover");

        g.addEdge("Motherboard", "CPU");
        g.addEdge("Motherboard", "RAM");
        g.addEdge("Motherboard", "Power Supply");
        g.addEdge("Power Supply", "GPU");
        g.addEdge("GPU", "Case Cover");
        g.addEdge("RAM", "Case Cover");
        g.addEdge("CPU", "Case Cover");

        // Run the dismounting algorithm
        DismountOrder<String> algo = new DismountOrder<>();
        Sequence<String> result = algo.dismounting(g);

        int idxCaseCover = result.indexOf("Case Cover");
        int idxGPU = result.indexOf("GPU");
        int idxRAM = result.indexOf("RAM");
        int idxCPU = result.indexOf("CPU");
        int idxPower = result.indexOf("Power Supply");
        int idxMB = result.indexOf("Motherboard");

        assertTrue(idxCaseCover < idxGPU);
        assertTrue(idxCaseCover < idxRAM);
        assertTrue(idxCaseCover < idxCPU);
        assertTrue(idxGPU < idxPower);
        assertTrue(idxCPU < idxMB);
        assertTrue(idxRAM < idxMB);
        assertTrue(idxPower < idxMB);
    }

}
