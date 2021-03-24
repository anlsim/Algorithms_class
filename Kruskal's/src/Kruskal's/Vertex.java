
package disjointset;

public class Vertex {
    int label;
    
    public Vertex( Integer label ) {
        this.label = label;
    }
    
    public Vertex( Vertex v ) {
        this.label = v.label;
    }
    
    public int getLabel() { return label; }

    @Override
    public boolean equals( Object o ) {
        Vertex v = (Vertex) o;
        return this.label == v.label;
    }

    @Override
    public int hashCode() {
        Integer il = label;
        return il.hashCode();
    }
    
    @Override
    public String toString() {
        return String.valueOf(label);
    }
}
