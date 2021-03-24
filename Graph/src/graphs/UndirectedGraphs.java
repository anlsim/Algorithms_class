package graphs;

public class UndirectedGraphs {
    public static void main(String[] args) {
        Graph g = createGraph1();
        System.out.println(g);
        
        System.out.println("the found and handled vertices:");


        GraphSearch thaSearch = new GraphSearch();
        System.out.println("DFS:");
        System.out.println(thaSearch.depthFirstSearch(g,  new Vertex("A")));

    }



    private static Graph createGraph1() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B","D");
        graph.addEdge("D", "E");
        graph.addEdge("F", "G");
        
        return graph;
    }
    
    
    }
}
