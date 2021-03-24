package graphs;

import java.util.*;


public class GraphSearch {
    private Graph g;
    public Set<Vertex> foundHandled = new LinkedHashSet<>();

    Set<Vertex> depthFirstSearch(Graph g, Vertex s) {
        this.g = g;
        recursiveDfs(s);
        return foundHandled ;
    }


    private void recursiveDfs(Vertex s) {
        List<Vertex> adjVertices = this.g.getAdjVertices(s);
        if( !foundHandled.contains(s)){
            foundHandled.add(s);
            for (Vertex v: adjVertices)
                recursiveDfs(v); {

            }
        }
    }
}
