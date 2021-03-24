package graphs;

import java.util.*;


public class Graph {

    private Map<Vertex, ArrayList<Vertex>> adjVertices = new HashMap<>();

    // add an element to the vertices set
    void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    // add an edge to the graph
    void addEdge(String label1, String label2) {

        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        if(!adjVertices.containsKey(v1)){
            return;
        }
        if(!adjVertices.containsKey(v2)){
            return;
        }
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);

    }

    
    List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    List<Vertex> getAdjVertices(Vertex vlabel) {
        return adjVertices.get(vlabel);
    }

    Set<Vertex> genericSearch(Graph g, Vertex s) {
        if(s == null){
            return null;
        }

        // check to see if it graph doesnt have edges
        if(g.adjVertices.get(s).isEmpty()){
            return null;
        }

        Set<Vertex> foundHandled = new HashSet<>();
        Set<Vertex> foundNotHandled = new HashSet<>();
        foundNotHandled.add(s);

        // exit when foundNotHandled is empty
        Iterator<Vertex> it = foundNotHandled.iterator();
        while (it.hasNext()) {
            // LI1: foreach found node v, we know that v is 
            //     reachable from s, because we have traced out 
            //     a path s-> v from s to it
            // LI2: If a node has beren handled, then all of 
            //     its neighbors have been found
            
            // let u be some node from foundNotHandled
            Vertex u = it.next();

            if (!foundHandled.contains(u)) {
                // for each v connected to u
                List<Vertex> vlist = adjVertices.get(u);
                for (Vertex v : vlist) {
                    foundNotHandled.add(v);
                }

                // move u from foundNotHandled to foundHandled
                foundHandled.add(u);
            }
            foundNotHandled.remove(u);
            it = foundNotHandled.iterator();
        }
        return foundHandled;
    }

    HashMap<Integer, ArrayList<Vertex>> ShortestPathBreadthFirst(Graph g, Vertex s) {
        // check to see if it graph doesnt have edges
        if(g.adjVertices.get(s).isEmpty()){
            return null;
        }
        // check for null Vertex
        if(s == null){
            return null;
        }

       Set<Vertex> foundHandled = new HashSet<>();
       Queue<Vertex> foundNotHandled = new LinkedList<>();
       HashMap<Integer, ArrayList<Vertex>> path = new HashMap<>();
       int d = 0;
       foundNotHandled.add(s);


        while (!foundNotHandled.isEmpty()) {
           Vertex u = foundNotHandled.peek();
           ArrayList<Vertex> vlist = g.adjVertices.get(u);

           for (Vertex v : vlist) {
               if (!foundHandled.contains(v)) {

                   foundNotHandled.add(v);
                   d = g.adjVertices.get(u).size() + 1; // D(v) = D(u) + 1;
                   path.put(d, vlist);
               }

               // move u from foundNotHandled to foundHandled
               foundHandled.add(u);
               foundNotHandled.remove(u);
           }

       }
       return path;
   }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Vertex, ArrayList<Vertex>> entry : adjVertices.entrySet()) {
            sb.append(entry.getKey()).append("->")
                    .append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
