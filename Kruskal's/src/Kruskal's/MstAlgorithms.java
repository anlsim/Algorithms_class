
package disjointset;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.*;

import java.util.Collections;

public class MstAlgorithms {
    private static int mstCost = 0;
    private static Set<Edge> mst = null;

    public static int getMstCost() {
        return mstCost;
    }

    public static Set<Edge> getMst() {
        return mst;
    }

    public static Set<Edge> Kruskals(Graph g) {

        mst = new LinkedHashSet<>();
        DisjointSet disSet = new DisjointSet(g.adjVertices.size());
        //Using list to sort the edges
        List<Edge> sortingList = new ArrayList<>(g.edges);

        //Comparing weights on edges to sort
        Collections.sort(sortingList, Comparator.comparingInt(o -> o.getWeight()));

            for (Edge e : sortingList) {
                int uSet = disSet.find(e.getU().label);
                int vSet = disSet.find(e.getV().label);
                if (uSet != vSet) {
                    mst.add(e);
                    disSet.union(uSet, vSet);
                    mstCost += e.getWeight();
                }

            }
        
        return mst;
    }


}
