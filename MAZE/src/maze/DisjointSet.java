
package maze;


public class DisjointSet {
    private int[] nodes;

    public DisjointSet( int elementCt ) {
        nodes = new int[ elementCt ];
        
        for( int i = 0; i < nodes.length; i++ ) {
            nodes[ i ] = -1;
        }
    }
    
    /**
     * union-by-height
     *
     * Union two disjoint sets using the height heuristic.
     * For simplicity, assume root1 and root2 are distinct
     * and represent set names.
     * 
     * (pg 341) Path compression is not entirely compatible 
     * with union-by-height, because path compression can 
     * change the heights of the trees
     * 
     * @param root1 the root of set 1
     * @param root2 the root of set 2
     */
    public void union_by_height( int root1, int root2 ) {
        if(nodes[root2] < nodes[root1]) {   // root2 is deeper
            nodes[root1] = root2;           // make root2 the new root
        } else {
            if( nodes[root1] == nodes[root2]) {
                nodes[root1]--;             // update height if same
            }
             nodes[root2] = root1;          // make root1 new root
        }
    }
    
    //making sure to union 2 sets by finding the root first
    public void union( int e1, int e2 ) {
        nodes[ e2 ] = e1;
    }
    
    /**
     * Perform a find with path compression.
     * Error checks omitted again for simplicity.
     * @param x the element being searched for.
     * @return the set containing x
     */
    public int find( int x ) {
        if( nodes[x] < 0) {
            return x;
        } else{
            return nodes[x] = find(nodes[x]);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(int i = 0; i < nodes.length; i++) {
            sb.append(nodes[i]).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
