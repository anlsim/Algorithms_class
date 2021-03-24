package disjointset;

import disjointset.DisjointSet;


public class UsingKruskalsAlgorithm {

    public static void main(String[] args) {
        Graph g = new Graph();
        
        // add vertices and edges to the graph
        buildGraph(g);
        System.out.println(g);  // output the graph
        
        // calculate the minimum-spanning tree
        MstAlgorithms.Kruskals(g);

        // output the minimum-spanning tree and the cost
        System.out.println("minimum-spanning tree:");
        System.out.println(MstAlgorithms.getMst());
        System.out.println("Cost:");
        System.out.println(MstAlgorithms.getMstCost());
        disjointSetTest();

    }
    
    private static void buildGraph( Graph g ) {
        g.addVertex(0);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addVertex(7);

        g.addEdge(1,0,10);
        g.addEdge(0,7,2);
        g.addEdge(7,6,4);
        g.addEdge(2,7,1);
        g.addEdge(7,3,2);
        g.addEdge(4,2,9);
        g.addEdge(5,4,5);
        g.addEdge(6,5,5);
    }

    private static void buildGraph1( Graph g ) {
        g.addVertex(0);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);

        g.addEdge(0,1,10);
        g.addEdge(0,2,6);
        g.addEdge(0,3,5);
        g.addEdge(1,3,15);
        g.addEdge(2,3,4);
    }
    
   
}
