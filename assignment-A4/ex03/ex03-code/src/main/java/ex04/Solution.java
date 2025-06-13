package ex04;
import java.util.Arrays;
import java.util.NoSuchElementException;

import ex04.collections.interfaces.*;
import ex04.collections.implementations.*;;

public class Solution {

    public static List<Station> convenientPath(Graph<Station> graph, Station start, Station target1, Station target2)
    {
        PriorityQueue<List<Station>> pathsMinPQ = new MinPriorityQueue<List<Station>>(); 
        List<Station> path = new LinkedList<Station>();
        boolean[] visited = new boolean[graph.size()];
        for ( int i =  0; i < graph.size(); i ++ ) {
            if ( i == start.getNumber() ) {
                visited[i] = true;
            } else {
                visited[i] = false;
            }
        }
        DFS(pathsMinPQ, path, start, graph, start, target1, false, target2, false, visited);
        System.out.println("candidate paths number: " + pathsMinPQ.size());
        List<Station> result = null;
        try {
            result = pathsMinPQ.dequeue();
        } catch (NoSuchElementException e) {
            String errMsg = (
                "Error: There are no paths starting at " + start.getName() + 
                " and passing through " + target1.getName() + " and " + target2.getName() + "."
            );
            System.err.println(errMsg);
        }   
        return result;
    }
    
    private static void DFS(
        PriorityQueue<List<Station>> paths, // minimal priority queue that stores all paths passing through start, target1 and target2
        List<Station> currPath,             // current path
        Station currStation,                // current station
        Graph<Station> graph,               // graph
        Station start,                      // vertex to start from
        Station target1,                    // target1 to pass through
        boolean ifTar1Visited,              // boolean flag of whether target1 has been visited
        Station target2,                    // target2 to pass through
        boolean ifTar2Visited,              // boolean flag of whether target1 has been visited
        boolean[] visited                   // the list recording whether each vertex has been visited
    ) {
        currPath.append(currStation);
        System.out.println( "current path:    " + currPath.toString());
        String visitedStr = "current visited: ";
        for ( int i = 0; i < graph.size(); i ++ ) {
            visitedStr += visited[i] ? "true " : "false ";
        }
        System.out.println(visitedStr);
        
        // check whether current station is target1 or target2
        if ( currStation.equals(target1) ) { ifTar1Visited = true; }
        if ( currStation.equals(target2) ) { ifTar2Visited = true; }
        // if target1 and target2 have both been visited, then current path is a possible valid path
        if ( ifTar1Visited && ifTar2Visited ) {
            System.out.println("current panth enqueue!");
            paths.enqueue((List<Station>) new LinkedList<Station>((LinkedList<Station>) currPath));
        }
        // DFS algorithm
        List<Station> currNeighbours = graph.neighbours(currStation);
        ListIterator<Station> neighbourItr = currNeighbours.getIterator();
        while ( neighbourItr.hasNext() ) {
            Station neighbourStation = neighbourItr.getNext();
            if ( !visited[neighbourStation.getNumber()] ) {
                visited[neighbourStation.getNumber()] = true;
                DFS(paths, currPath, neighbourStation, graph, start, target1, ifTar1Visited, target2, ifTar2Visited, visited);
                visited[neighbourStation.getNumber()] = false;
            }
        }
        currPath.removeLast();
    }
}
